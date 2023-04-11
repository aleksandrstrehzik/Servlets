package com.clevertec.strezhik.servlets;

import com.clevertec.strezhik.utils.JDBCConnection;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class LiquibaseListener implements ServletContextListener {

    private final JDBCConnection jdbcConnection = new JDBCConnection();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try (Liquibase liquibase = new Liquibase("/liquibase/changeLog.xml",
                new ClassLoaderResourceAccessor(),
                new JdbcConnection(jdbcConnection.getConnection()))) {
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (LiquibaseException | SQLException e) {
            e.printStackTrace();
        }
    }
}
