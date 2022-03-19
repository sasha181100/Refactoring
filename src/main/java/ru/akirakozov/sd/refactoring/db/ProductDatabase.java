package ru.akirakozov.sd.refactoring.db;

import ru.akirakozov.sd.refactoring.domain.Product;

import java.util.List;

public class ProductDatabase extends Table<Product> {
    public ProductDatabase() {
        super("PRODUCT",
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        " NAME           TEXT    NOT NULL, " +
                        " PRICE          INT     NOT NULL)",
                "(NAME, PRICE)",
                Parsers.PRODUCT_PARSER);
    }

    @Override
    protected String getObjectInsertTuple(Product object) {
        return "(" + object.getName() + ", " + object.getPrice() + ")";
    }

    public List<Product> selectAllOrderedByPriceDesc() {
        return select("*", "ORDER BY PRICE DESC", "", Parsers.PRODUCT_PARSER);
    }

    public int getSumPrice() {
        return select("SUM(price)", "", "", Parsers.INTEGER_PARSER).get(0);
    }

    public Product getMaxPriceProduct() {
        return select("*", "ORDER BY PRICE DESC", "LIMIT 1", Parsers.PRODUCT_PARSER).get(0);
    }

    public Product getMinPriceProduct() {
        return select("*", "ORDER BY PRICE", "LIMIT 1", Parsers.PRODUCT_PARSER).get(0);
    }
}