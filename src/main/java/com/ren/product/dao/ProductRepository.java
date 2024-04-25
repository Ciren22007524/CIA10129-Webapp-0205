package com.ren.product.dao;

import com.ren.product.model.ProductVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<ProductVO, Integer> {

    @Transactional
    @Modifying
    @Query(value = "delete from product where pNo =?1", nativeQuery = true)
    void deleteBypNo(Integer pNo);

}
