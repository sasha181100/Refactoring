package ru.akirakozov.sd.refactoring.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegerParser extends Parser<Integer> {
    @Override
    public Integer parseImpl(ResultSet resultSet) throws SQLException {
        return resultSet.getInt(1);
    }
}