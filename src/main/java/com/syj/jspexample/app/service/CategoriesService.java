package com.syj.jspexample.app.service;

import com.syj.jspexample.app.entity.Categories;
import com.syj.jspexample.app.repository.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    @Transactional(readOnly = true)
    public Categories one(Long id) {
        return categoriesRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Transactional(readOnly = true)
    public Categories one(String name) {
        return categoriesRepository
                .findByName(name)
                .orElseThrow(RuntimeException::new);
    }



}
