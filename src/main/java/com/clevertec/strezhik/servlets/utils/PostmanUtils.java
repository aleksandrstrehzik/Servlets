package com.clevertec.strezhik.servlets.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class PostmanUtils {

    public static String getParam(HttpServletRequest req, String fieldName) {
        return Optional.ofNullable(req.getParameter(fieldName))
                .filter(StringUtils::isNotBlank)
                .orElse(null);
    }
}
