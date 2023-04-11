package com.clevertec.strezhik.service.impl;

import com.clevertec.strezhik.dao.impl.ProductDAO;
import com.clevertec.strezhik.dao.DAO;
import com.clevertec.strezhik.entity.Product;
import com.clevertec.strezhik.entity.Type;
import com.clevertec.strezhik.service.ProductService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final DAO<Product> productDao = new ProductDAO();

    @Override
    public void save(String id, String marking, String type, String is_on_discount,
                     String description, String price) {
        Product product = new Product();
        product.setId(Integer.parseInt(id));
        product.setMarking(marking);
        product.setType(Type.valueOf(type));
        product.setIsOnDiscount(Boolean.parseBoolean(is_on_discount));
        product.setDescription(description);
        product.setPrice(new BigDecimal(price));
        productDao.save(product);
    }

    @Override
    public Product selectById(String id) {
        return productDao.selectById(Integer.parseInt(id));
    }

    @Override
    public void update(String id, String marking, String type, String is_on_discount,
                       String description, String price) {
        int product_id = Integer.parseInt(id);
        Product product_after = new Product();
        Product product_before = productDao.selectById(product_id);
        product_after.setId(product_id);
        if (marking != null) {
            product_after.setMarking(marking);
        } else product_after.setMarking(product_before.getMarking());
        if (type != null) {
            product_after.setType(Type.valueOf(type));
        } else product_after.setType(product_before.getType());
        if (is_on_discount != null) {
            product_after.setIsOnDiscount(Boolean.parseBoolean(is_on_discount));
        } else product_after.setIsOnDiscount(product_before.isIsOnDiscount());
        if (description != null) {
            product_after.setDescription(description);
        } else product_after.setDescription(product_before.getDescription());
        if (price != null) {
            product_after.setPrice(new BigDecimal(price));
        } else product_after.setPrice(product_before.getPrice());
        productDao.update(product_after);
    }

    @Override
    public void delete(String id) {
        productDao.delete(Integer.parseInt(id));
    }

    public List<Product> findPortion(String pageNumber, String pageSize) {
        int productsPerPage;
        int productToSkip;
        if (pageSize != null) {
            productsPerPage = Integer.parseInt(pageSize);
        } else {
            productsPerPage = 2;
        }
        int pageNumberInt = Integer.parseInt(pageNumber);
        if (pageNumberInt == 1) {
            productToSkip = 0;
        } else {
            productToSkip = (pageNumberInt - 1) * productsPerPage;
        }
        try {
            return productDao.findPortion(productToSkip, productsPerPage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
