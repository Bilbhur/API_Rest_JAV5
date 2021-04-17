package com.etna.project.service;

import com.etna.project.dao.ProductCategoryRepository;
import com.etna.project.entity.Country;
import com.etna.project.entity.Product;
import com.etna.project.entity.ProductCategory;
import com.etna.project.exception.CustomConflictException;
import com.etna.project.exception.CustomResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService implements IModelService<ProductCategory> {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> getList(Integer page, Integer limit) {
        PageRequest pageable = PageRequest.of(page, limit);
        return productCategoryRepository.getListByPage(pageable);
    }

    @Override
    public ProductCategory getOneById(Integer id) {
        return productCategoryRepository.findById(id.longValue()).orElseThrow(CustomResourceNotFoundException::new);
    }

    @Override
    public ProductCategory create(ProductCategory entity) {
        ProductCategory productCategory = productCategoryRepository.getProductCategoryByCategoryName(entity.getCategoryName());
        if (null != productCategory)
            throw new CustomConflictException();

        return productCategoryRepository.save(entity);
    }

    @Override
    public ProductCategory update(Integer id, ProductCategory entity) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            ProductCategory productCategory = productCategoryRepository.findById(id.longValue()).orElseThrow(CustomResourceNotFoundException::new);

            productCategoryRepository.delete(productCategory);
        } catch (Exception e) {
            throw new CustomResourceNotFoundException();
        }
        return true;
    }
}
