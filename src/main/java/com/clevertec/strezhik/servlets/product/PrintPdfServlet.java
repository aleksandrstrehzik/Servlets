package com.clevertec.strezhik.servlets.product;

import com.clevertec.strezhik.service.StoreService;
import com.clevertec.strezhik.service.impl.StoreServiceImpl;
import com.clevertec.strezhik.servlets.utils.PostmanUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/pdf")
public class PrintPdfServlet extends HttpServlet {

    private final StoreService storeService = new StoreServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String order = PostmanUtils.getParam(req, "order");
        storeService.createCheck(order);
        resp.setHeader("Content-Disposition", "attachment; filename=\"Check.pdf\"");
        resp.setContentType("application/pdf");
        try (InputStream resourceAsStream = PrintPdfServlet.class.getClassLoader().getResourceAsStream("/temp/r.pdf");
             ServletOutputStream outputStream = resp.getOutputStream()) {
            outputStream.write(resourceAsStream.readAllBytes());
        }
    }
}
