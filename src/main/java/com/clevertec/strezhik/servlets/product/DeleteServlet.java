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

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    private final ProductService service = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDelete(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = PostmanUtils.getParam(req, "id");
        service.delete(id);
    }
}
