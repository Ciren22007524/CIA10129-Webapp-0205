package com.ren.product.dao;

import com.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

//    // 根據關鍵字搜尋產品
//    @Transactional
//    List<Product> findBypNameContaining(String keyword);
//    @Transactional
//    List<Product> findBypNameContainingAndProductCategoryPcatNo(String keyword, Integer pCatNo);
//
//    @Transactional
//    List<Product> findByProductCategorypCatNo(Integer pCatNo);
//    // 新增方法以獲取所有上架的商品
//    @Transactional
//    List<Product> findBypStat(Integer pStat);
//    // 新增方法以根據商品編號查找商品
//    @Transactional
//    Optional<Product> findBypNo(Integer pNo);

//    @Transactional
//    @Modifying
//    @Query("SELECT p FROM product p WHERE " +
//            "(:pName IS NULL OR p.pName = :pName) AND " +
//            "(:pInfo IS NULL OR p.pInfo = :pInfo) AND " +
//            "(:pSize IS NULL OR p.pSize = :pSize) AND " +
//            "(:pColor IS NULL OR p.pColor = :pColor) AND " +
//            "(:pPrice IS NULL OR p.pPrice = :pPrice) AND " +
//            "(:pStat IS NULL OR p.pStat = :pStat) AND " +
//            "(:pSalQty IS NULL OR p.pSalQty = :pSalQty) AND " +
//            "(:pComPeople IS NULL OR p.pComPeople = :pComPeople) AND " +
//            "(:pComScore IS NULL OR p.pComScore = :pComScore)")
//    List<Product> findByAttributes(
////                @Param("pNo") Integer pNo,
////                @Param("pCatNo") Integer pCatNo,
//            @Param("pName") String pName,
//            @Param("pInfo") String pInfo,
//            @Param("pSize") Integer pSize,
//            @Param("pColor") String pColor,
//            @Param("pPrice") BigDecimal pPrice,
//            @Param("pStat") Byte pStat,
//            @Param("pSalQty") Integer pSalQty,
//            @Param("pComPeople") Integer pComPeople,
//            @Param("pComScore") Integer pComScore
//    );

}
