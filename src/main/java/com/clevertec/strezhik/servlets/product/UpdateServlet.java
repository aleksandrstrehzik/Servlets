package com.clevertec.strezhik.servlets.product;

import com.clevertec.strezhik.service.ProductService;
import com.clevertec.strezhik.service.impl.ProductServiceImpl;
import com.clevertec.strezhik.servlets.utils.PostmanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

    private final ProductService service = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = PostmanUtils.getParam(req, "id");
        String marking = PostmanUtils.getParam(req, "marking");
        String type = PostmanUtils.getParam(req, "type");
        String is_on_discount = PostmanUtils.getParam(req, "is_on_discount");
        String description = PostmanUtils.getParam(req, "description");
        String price = PostmanUtils.getParam(req, "price");
        service.update(id, marking, type, is_on_discount, description, price);
    }
}
