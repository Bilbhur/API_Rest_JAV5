package com.etna.project.service;

import com.etna.project.dao.ProductRepository;
import com.etna.project.entity.Country;
import com.etna.project.entity.Product;
import com.etna.project.entity.ProductCategory;
import com.etna.project.exception.CustomConflictException;
import com.etna.project.exception.CustomInternalServerErrorException;
import com.etna.project.exception.CustomResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService implements IModelService<Product> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getList(Integer page, Integer limit) {
        PageRequest pageable = PageRequest.of(page, limit);
        return productRepository.getListByPage(pageable);
    }

    @Override
    public Product getOneById(Integer id) {
        return productRepository.findById(id.longValue()).orElseThrow(CustomResourceNotFoundException::new);
    }

    @Override
    public Product create(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public Product update(Integer id, Product entity) {
        Product product = productRepository.findById(id.longValue()).orElseThrow(CustomResourceNotFoundException::new);

        product.setDescription(entity.getDescription());
        product.setCategory(entity.getCategory());
        product.setSku(entity.getSku());
        product.setName(entity.getName());
        product.setUnitPrice(product.getUnitPrice());
        product.setUnitsInStock(entity.getUnitsInStock());
        product.setActive(entity.isActive());
        product.setImageUrl(entity.getImageUrl());

        try {
            productRepository.save(product);
        } catch (Exception e) {
            throw new CustomInternalServerErrorException();
        }
        return product;
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            Product product = productRepository.findById(id.longValue()).orElseThrow(CustomResourceNotFoundException::new);

            productRepository.delete(product);
        } catch (Exception e) {
            throw new CustomResourceNotFoundException();
        }
        return true;
    }
}
