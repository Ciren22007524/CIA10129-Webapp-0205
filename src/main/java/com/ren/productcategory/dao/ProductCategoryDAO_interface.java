package com.ren.productcategory.dao;

import java.util.List;
import java.util.Set;

import com.Entity.ServicePicture;
import com.Entity.ServiceRobot;

public interface ProductCategoryDAO_interface {

    // 新增商品類別
    public void insert(ServicePicture.ProductCategoryVO productCategoryVO);
    // 透過商品類別編號查詢商品類別
    public ServicePicture.ProductCategoryVO findByPrimaryKey(Integer pCatNo);
    // 查詢全部商品類別
    public List<ServicePicture.ProductCategoryVO> getAll();
    //查詢某類別的衣服(一對多)(回傳 Set)
    public Set<Product> getProductsBypCatNo(Integer pCatNo);
    // 更新商品類別資料
    public void update(ServicePicture.ProductCategoryVO productCategoryVO);
    // 根據商品類別編號刪除
    public void delete(Integer pCatNo);

}
