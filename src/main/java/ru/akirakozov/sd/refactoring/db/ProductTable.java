package ru.akirakozov.sd.refactoring.db;

import ru.akirakozov.sd.refactoring.domain.Product;

import java.util.List;
import java.util.Optional;

public class ProductTable extends Table<Product> {
    public ProductTable(String file) {
        super(file, "PRODUCT");
    }

    @Override
    protected String getTableDesc() {
        return "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " PRICE          INT     NOT NULL)";
    }

    @Override
    protected String getInsertTupleTemplate() {
        return "(NAME, PRICE)";
    }

    @Override
    protected String getObjectInsertTuple(Product object) {
        return "(\"" + object.getName() + "\", " + object.getPrice() + ")";
    }

    public List<Product> selectAllOrderedByPriceDesc() {
        return select("*", "ORDER BY PRICE DESC", "", Parsers.PRODUCT_PARSER);
    }

    public int getSumPrice() {
        return getFirstOptional(select("SUM(price)", "", "", Parsers.INTEGER_PARSER)).orElse(0);
    }

    private <K> Optional<K> getFirstOptional(List<K> list) {
        if (list == null || list.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(list.get(0));
        }
    }

    public Optional<Product> getMaxPriceProduct() {
        return getFirstOptional(select("*", "ORDER BY PRICE DESC", "LIMIT 1", Parsers.PRODUCT_PARSER));
    }

    public Optional<Product> getMinPriceProduct() {
        return getFirstOptional(select("*", "ORDER BY PRICE", "LIMIT 1", Parsers.PRODUCT_PARSER));
    }
}