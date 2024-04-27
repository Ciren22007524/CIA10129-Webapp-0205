package com.ren.productcategory.dao;

import com.Entity.ProductCategory;
import com.ren.product.dao.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

}
