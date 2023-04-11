package com.clevertec.strezhik.servlets.filters;

import com.clevertec.strezhik.servlets.utils.PostmanUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/findAll")
public class FindAllFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String pageSize = PostmanUtils.getParam((HttpServletRequest) request, "pageSize");
        if (Integer.parseInt(pageSize) < 21) {
            chain.doFilter(request, response);
        } else throw new RuntimeException("You can not enter pageSize greater than 20");
    }
}
