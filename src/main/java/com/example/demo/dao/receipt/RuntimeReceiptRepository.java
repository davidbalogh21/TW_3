package com.example.demo.dao.receipt;

import com.example.demo.model.Receipt;
import com.example.demo.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository("runtime")
public class RuntimeReceiptRepository implements ReceiptRepository {
    List<Receipt> receipts = new ArrayList<>();

    @Override
    public List<Receipt> findAll() {
        return receipts;
    }

    @Override
    public List<Receipt> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Receipt> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Receipt> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        receipts.removeIf(receipt -> receipt.getId().equals(aLong));
    }

    @Override
    public void delete(Receipt entity) {
        receipts.remove(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Receipt> entities) {
        entities.forEach(receipt -> receipts.remove(receipt));
    }

    @Override
    public void deleteAll() {
        receipts.removeAll(receipts);
    }

    @Override
    public <S extends Receipt> S save(S entity) {
        this.receipts.add(entity);
        return (S) receipts.get(receipts.size());
    }

    @Override
    public <S extends Receipt> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Receipt> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Receipt> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Receipt> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Receipt> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Receipt getOne(Long aLong) {
        return null;
    }

    @Override
    public Receipt getById(Long aLong) {
        return receipts.stream().filter(receipt -> receipt.getId().equals(aLong)).findFirst().get();
    }

    @Override
    public <S extends Receipt> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Receipt> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Receipt> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Receipt> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Receipt> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Receipt> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public List<Receipt> getAllByLocation(String location) {
        return null;
    }

    @Override
    public List<Receipt> getAllByUser(User user) {
        List<Receipt> collect = receipts
                .stream()
                .filter(receipt -> receipt.getUser().getUsername().equals(user.getUsername()))
                .collect(Collectors.toList());
        return collect;
    }

}
