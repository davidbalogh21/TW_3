package com.example.demo.controller;

import com.example.demo.model.Receipt;
import com.example.demo.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api")
public class ApiController {
    private final ReceiptService receiptService;

    @Autowired
    public ApiController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    /***
     * @return a list of all receipts from your data source just by calling http://localhost:8085/api/receipts
     */
    @GetMapping(path = "/receipts")
    public List<Receipt> getReceipts() {
        return receiptService.getReceipts();
    }

    /***
     * @param location is a variable in the request URI mapping
     *                 ex of http request:
     *                 - http://localhost:8085/api/La doi pasi
     *                 - http://localhost:8085/api/Lidl
     * @return the list of all receipts by a specific location
     */
    @GetMapping(path = "/{location}")
    public List<Receipt> getReceiptsByLocation(@PathVariable("location") String location) {
        return receiptService.getReceiptsByLocation(location);
    }

    /***
     * This function does exact the same thing as getReceiptsByLocation() but use @RequestParam
     *
     * @param name is a parameter in the request URI mapping.
     *             In this example we have only one parameter, but there can be more.
     *             ex of http request:
     *             - http://localhost:8085/api/location?name=La doi pasi
     *             - http://localhost:8085/api/location?name=Lidl
     * @return the list of all receipts by a specific location
     */
    @GetMapping(path = "/location")
    public List<Receipt> getReceiptsByLocationParam(@RequestParam String name) {
        return receiptService.getReceiptsByLocation(name);
    }

    @PostMapping
    public void addReceipt(@RequestBody Receipt receipt) {
        receiptService.save(receipt);
    }
}
