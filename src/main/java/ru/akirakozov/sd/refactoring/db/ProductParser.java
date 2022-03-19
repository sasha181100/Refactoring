package ru.akirakozov.sd.refactoring.db;

import ru.akirakozov.sd.refactoring.domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductParser extends Parser<Product> {
    @Override
    public Product parseImpl(ResultSet resultSet) throws SQLException {
        return new Product(
                resultSet.getString("name"),
                resultSet.getInt("price")
        );
    }
}