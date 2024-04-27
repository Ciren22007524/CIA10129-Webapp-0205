package com.ren.productcategory.service;

import java.util.List;
import java.util.Set;

import com.Entity.ServicePicture;
import com.Entity.ServiceRobot;

public interface ProductCategoryService_interface {

	public ServicePicture.ProductCategoryVO addProductCategory(String pCatNo);

	public List<ServicePicture.ProductCategoryVO> getAll();

	public ServicePicture.ProductCategoryVO getOneProductCatagory(Integer pCatNo);

	public Set<Product> getProductsBypCatNo(Integer pCatNo);

	public ServicePicture.ProductCategoryVO updateProductCategory(Integer pCatNo, String pCatName);
	
	public void deleteProductCategory(Integer pCatNo);
	
}
