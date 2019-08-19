package com.Romael.ShoppingCart.repository;

import com.Romael.ShoppingCart.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
    Optional<Products> findById(Integer productid);

}
