package io.reactivesw.product.infrastructure.repository;

import io.reactivesw.product.domain.model.ProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Davis on 16/12/14.
 */
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
}
