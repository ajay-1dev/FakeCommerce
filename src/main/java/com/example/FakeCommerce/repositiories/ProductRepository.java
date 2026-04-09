package com.example.FakeCommerce.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.FakeCommerce.schema.Product;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p JOIN FETCH p.category")
    List<Product> getAllProducts();

    @Query("Select p from Product p join fetch p.category where p.id=:id")
    List<Product> getProductByIds(@Param("id")Long id);

    @Query("select p from Product p join fetch p.category c where c.id = :id")
    List<Product> getProductBycategories(@Param("id") long id);

    @Query("SELECT p FROM Product p JOIN FETCH p.category WHERE p.id = :id")
    Optional<Product> findByProductId(@Param("id")Long id);
    
}
