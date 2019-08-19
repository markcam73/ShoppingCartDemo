package com.Romael.ShoppingCart.service;

import com.Romael.ShoppingCart.model.Products;
import com.Romael.ShoppingCart.repository.CartRepository;
import com.Romael.ShoppingCart.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

@Service
@Transactional
public class CartService implements CartRepository {

    private ProductsRepository productsRepository;

    @Autowired
    public CartService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    private Map<Products,Integer> products = new HashMap<>();


    //
    @Override
    public void addProduct(Products product) {
        if (products.containsKey(product)){
            products.replace(product, products.get(product) + 1);
        } else{
            products.put(product,1);
        }

    }

    //
    @Override
    public void removeProduct(Products product) {
        if(products.containsKey(product)){
            products.remove(product);
        }
    }

    //
    @Override
    public void checkOut(){

        Products product;
        for(Map.Entry<Products, Integer> entry : products.entrySet()){

            product = productsRepository.getOne(entry.getKey().getProductid());

            if(product.getStock() < entry.getValue()){

            }
            else
                entry.getKey().setStock(product.getStock() - entry.getValue());

        }
        productsRepository.saveAll(products.keySet());
        productsRepository.flush();
        products.clear();
    }

    //
    @Override
    public Map<Products, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(products);
    }

    //
    @Override
    public Double getTotal() {
        Double itemTotal;
        Double cartTotal = 0.00;
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");

        for(Map.Entry<Products, Integer> entry : products.entrySet()){
            itemTotal = entry.getKey().getPrice() * entry.getValue();
            cartTotal += itemTotal;
        }
        decimalFormat.format(cartTotal);
        return cartTotal;
    }
}
