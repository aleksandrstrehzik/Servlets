package com.clevertec.strezhik.servlets.product;

import com.clevertec.strezhik.service.ProductService;
import com.clevertec.strezhik.service.impl.ProductServiceImpl;
import com.clevertec.strezhik.servlets.utils.PostmanUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet("/findAll")
public class FindAllServlet extends HttpServlet {

    private final ProductService service = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = PostmanUtils.getParam(req, "page");
        String pageSize = PostmanUtils.getParam(req, "pageSize");
        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json");
        try (PrintWriter writer = resp.getWriter()) {
            service.findPortion(page, pageSize).stream()
                    .map(p -> {
                        try {
                            return objectMapper.writeValueAsString(p) + "\n";
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .forEach(writer::write);
        }

    }
}
