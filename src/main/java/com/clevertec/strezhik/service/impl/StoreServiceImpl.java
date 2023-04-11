package com.clevertec.strezhik.service.impl;

import com.clevertec.strezhik.dao.impl.CardDAO;
import com.clevertec.strezhik.dao.impl.ProductDAO;
import com.clevertec.strezhik.dao.DAO;
import com.clevertec.strezhik.entity.Card;
import com.clevertec.strezhik.entity.Product;
import com.clevertec.strezhik.service.CheckService;
import com.clevertec.strezhik.service.PrintPdfService;
import com.clevertec.strezhik.service.StoreService;
import com.clevertec.strezhik.service.exception.IncorrectInput;
import com.clevertec.strezhik.service.exception.NullInput;
import com.clevertec.strezhik.service.utils.CheckUtil;

import java.util.Map;

public class StoreServiceImpl implements StoreService {

    private final CheckService checkService = new SimpleCheckService();
    private final DAO<Product> productDAO = new ProductDAO();
    private final DAO<Card> cardDAO = new CardDAO();
    private final PrintPdfService printPdfService = new PrintPdfServiceImpl(checkService);

    @Override
    public void createCheck(String order) {
        if (order != null) {
            String[] orderArray = order.split(" ");
            Map<Product, Integer> shoppingСart = putProductsInCart(orderArray);
            if (CheckUtil.IsCardPresent(orderArray)) {
                Card card = cardDAO.selectById(CheckUtil.getDiscountCardNumber(orderArray));
                printPdfService.printPdf(card);
            } else printPdfService.printPdf(null);
        } else throw new NullInput();
    }

    @Override
    public Map<Product, Integer> putProductsInCart(String[] d) {
        int[] idArray = CheckUtil.getIdArray(d);
        int[] numArray = CheckUtil.getNumArray(d);
        Map<Product, Integer> shoppingСart = checkService.getShoppingСart();
        for (int i = 0; i < idArray.length; i++) {
            Product product = productDAO.selectById(idArray[i]);
            if (product != null) {
                shoppingСart.put(product, numArray[i]);
            }
            else throw new IncorrectInput();
        }
        return shoppingСart;
    }
}
