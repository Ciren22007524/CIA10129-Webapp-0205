package com.ren.product.service;

import com.ren.product.dao.ProductHibernateDAOImpl;
import com.ren.product.dao.ProductRepository;
import com.ren.product.model.ProductVO;
import com.ren.product.dao.ProductDAO_interface;
import com.ren.productcategory.model.ProductCategoryVO;
import com.ren.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService_interface {
    private final int SUCCESS = 1;
    private final int FAILURE = -1;

    @Autowired
    ProductRepository repository;

    @Override
    public ProductVO addProduct(ProductVO productVO) {
        repository.save(productVO);
        return
    }

    @Override
    public ProductVO getOneProduct(Integer pNo) {
        Optional<ProductVO> optional = repository.findById(pNo);
        return optional.orElse(null);
    }

    @Override
    public List<ProductVO> getAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductVO> getProductsByCompositeQuery(Map<String, String[]> map) {
        return null;
    }

    @Override
    public ProductVO updateProduct(ProductVO productVO) {
        repository.save(productVO);
    }

    @Override
    public void deleteProduct(Integer pNo) {
        if (repository.existsById(pNo)) {
            repository.deleteBypNo(pNo);
        }
    }
}
