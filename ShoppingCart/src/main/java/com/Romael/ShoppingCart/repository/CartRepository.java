package com.Romael.ShoppingCart.repository;

import com.Romael.ShoppingCart.model.Products;

import java.math.BigDecimal;
import java.util.Map;

public interface CartRepository {

    void addProduct(Products product);

    void removeProduct(Products product);

    Map<Products,Integer> getProductsInCart();

    void checkOut();

    Double getTotal();
}
