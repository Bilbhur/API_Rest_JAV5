package com.etna.project.dao;

import com.etna.project.entity.Product;
import com.etna.project.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@CrossOrigin("http://localhost:4200")
//@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    @Query("SELECT p FROM ProductCategory p ORDER BY p.id ASC")
    public List<ProductCategory> getListByPage(Pageable pageable);

    @Query("SELECT p FROM ProductCategory p WHERE p.categoryName = :categoryName")
    public ProductCategory getProductCategoryByCategoryName(String categoryName);
}
