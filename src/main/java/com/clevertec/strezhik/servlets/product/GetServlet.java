package com.clevertec.strezhik.servlets.product;

import com.clevertec.strezhik.entity.Product;
import com.clevertec.strezhik.service.ProductService;
import com.clevertec.strezhik.service.impl.ProductServiceImpl;
import com.clevertec.strezhik.servlets.utils.PostmanUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/get")
public class GetServlet extends HttpServlet {

    private final ProductService service = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String id = PostmanUtils.getParam(req, "id");
        Product product = service.selectById(id);
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(product);
        try (PrintWriter writer = resp.getWriter()) {
            writer.write(s);
        }
    }
}
