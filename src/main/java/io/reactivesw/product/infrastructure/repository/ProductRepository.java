package io.reactivesw.product.infrastructure.repository;

import io.reactivesw.product.domain.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Davis on 16/12/14.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
