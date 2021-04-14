package com.etna.project.service;

import com.etna.project.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IModelService<T> {

    public List<T> getList(Integer page, Integer limit);

    Page<Product> getListByCategoryId(Long id, Integer page, Integer limit);

    public T getOneById(Integer id);
    public T create(T entity);
    public T update(Integer id, T entity);
    public Boolean delete(Integer id);
}
