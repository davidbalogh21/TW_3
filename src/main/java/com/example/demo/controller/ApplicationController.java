package com.example.demo.controller;

import com.example.demo.config.UserValidator;
import com.example.demo.model.Product;
import com.example.demo.model.ProductUser;
import com.example.demo.model.Receipt;
import com.example.demo.model.User;
import com.example.demo.service.product.ProductServiceImpl;
import com.example.demo.service.product_user.ProductUserService;
import com.example.demo.service.product_user.ProductUserServiceImpl;
import com.example.demo.service.receipt.ReceiptService;
import com.example.demo.service.user.UserService;
import com.example.demo.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ApplicationController {
    private final ReceiptService receiptService;
    private final UserService userService;
    private final SecurityService securityService;
    private final UserValidator userValidator;
    private final ProductServiceImpl productService;
    private final ProductUserServiceImpl productUserService;

    @Autowired
    public ApplicationController(ReceiptService receiptService, UserService userService, SecurityService securityService, UserValidator userValidator, ProductServiceImpl productService, ProductUserServiceImpl productUserService) {
        this.receiptService = receiptService;
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
        this.productService = productService;
        this.productUserService = productUserService;
    }

    /***
     * @param model supply attributes used for rendering views
     * @param principal is the currently logged in user
     * @return what view to use
     */
    @GetMapping("/receipts")
    public String showTable(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Receipt> receipts = receiptService.getReceiptsByUser(user);

        model.addAttribute("receipts", receipts);
        return "receipts";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "welcome";
    }

    @GetMapping("/products")
    public String showProductsTable(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        switch (user.getRole()) {
            case "MANUFACTURER":
                List<ProductUser> products = productUserService.getProductsByUser(user);
                model.addAttribute("productList", products);
                return "products_manufacturer";

            case "DEALER":
                List<ProductUser> myProducts = productUserService.getProductsByUser(user);
                model.addAttribute("myProductList", myProducts);

                List<User> manufacturers = userService.findByRole("MANUFACTURER");
                List<ProductUser> productsOfManufacturers = productUserService.getProductsByUserRole(manufacturers);
                model.addAttribute("productList", productsOfManufacturers);
                return "products_dealer";

            case "CLIENT":
                List<User> dealers = userService.findByRole("DEALER");
                List<ProductUser> productsOfDealers = productUserService.getProductsByUserRole(dealers);
                model.addAttribute("productList", productsOfDealers);
                return "products_client";
        }
        return "welcome";
    }

    @PostMapping( "/processDealerPurchase")
    public String processPurchase(@RequestParam int stockData, Long id, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        ProductUser foundProductUser = productUserService.getProductUserById(id);

        ProductUser newProductUser = new ProductUser(foundProductUser.getPrice(), stockData, user, foundProductUser.getProduct());

        Receipt newReceipt = new Receipt(foundProductUser.getPrice() * stockData, stockData, LocalDate.now(), foundProductUser.getProduct().getName(), foundProductUser.getPrice(), foundProductUser.getUser().getUsername(), user);
        receiptService.save(newReceipt);

        productUserService.save(newProductUser);
        return "redirect:/products";
    };

    @PostMapping( "/updateDealerPrice")
    public String processUpdate(@RequestParam Double priceData, Long id, Principal principal) {
        ProductUser foundProductUser = productUserService.getProductUserById(id);

        foundProductUser.setPrice(priceData);
        productUserService.save(foundProductUser);
        return "redirect:/products";
    };

    @PostMapping( "/processClientPurchase")
    public String processClientPurchase(@RequestParam int buyQuantity, Long id, Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());

        ProductUser foundProductUser = productUserService.getProductUserById(id);
        foundProductUser.setStock(foundProductUser.getStock() - buyQuantity);
        productUserService.save(foundProductUser);

        Receipt newReceipt = new Receipt(foundProductUser.getPrice() * buyQuantity, buyQuantity, LocalDate.now(), foundProductUser.getProduct().getName(), foundProductUser.getPrice(), foundProductUser.getUser().getUsername(), user);
        receiptService.save(newReceipt);

        return "redirect:/receipts";
    };
}
