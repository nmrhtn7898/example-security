package com.syj.jspexample.app.repository;

import com.syj.jspexample.app.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    @Transactional(readOnly = true)
    Optional<Categories> findByName(String name);

}
