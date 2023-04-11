package com.clevertec.strezhik.dao.impl;

import com.clevertec.strezhik.dao.DAO;
import com.clevertec.strezhik.entity.Product;
import com.clevertec.strezhik.entity.Type;
import com.clevertec.strezhik.utils.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements DAO<Product> {

    private final JDBCConnection jdbcConnection = new JDBCConnection();
    private final String table = "servlet.product";

    @Override
    public void save(Product product) {
        String sql = " INSERT INTO " + table + " (id, marking, type, is_on_discount, description, price) values (?,?,?,?,?,?)";
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            statement.setInt(1, product.getId());
            statement.setString(2, product.getMarking());
            statement.setString(3, product.getType().toString());
            statement.setBoolean(4, product.isIsOnDiscount());
            statement.setString(5, product.getDescription());
            statement.setBigDecimal(6, product.getPrice());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product selectById(int id) {
        String sql = "SELECT * FROM servlet.product WHERE id = ?";
        Product product = null;
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                product = new Product();
                buildProduct(product, resultSet);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE " + table + " SET marking = ?, type = ?, " +
                "is_on_discount = ? , description = ?, price = ?  WHERE id = ?";
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);

            statement.setString(1, product.getMarking());
            statement.setString(2, product.getType().toString());
            statement.setBoolean(3, product.isIsOnDiscount());
            statement.setString(4, product.getDescription());
            statement.setBigDecimal(5, product.getPrice());
            statement.setInt(6, product.getId());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM " + table + " WHERE id = ?";
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findPortion(int start, int total) throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM servlet.product LIMIT " + total + " OFFSET " + start;
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                buildProduct(product, resultSet);
                products.add(product);
            }
        }
        return products;
    }


    private void buildProduct(Product product, ResultSet resultSet) throws SQLException {
        product.setId(resultSet.getInt(1));
        product.setMarking(resultSet.getString(2));
        product.setType(Type.valueOf(resultSet.getString(3)));
        product.setIsOnDiscount(resultSet.getBoolean(4));
        product.setDescription(resultSet.getString(5));
        product.setPrice(resultSet.getBigDecimal(6));
    }
}
