package com.example.FakeCommerce.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.FakeCommerce.schema.Product;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategory(String category);

    //custom native query
    @Query(nativeQuery = true,value = "SELECT DISTINCT category FROM Products")
    List<String> findAllCategories();

    @Query("SELECT p FROM Product p JOIN FETCH p.category")
    List<Product> getAllProducts();

    @Query("Select p from Product p join fetch p.category where p.id=:id")
    List<Product> getProductByIds(@Param("id")Long id);
}
