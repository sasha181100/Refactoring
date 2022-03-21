package ru.akirakozov.sd.refactoring.db;

import java.util.List;

public abstract class Table<T> {
    protected final String name;
    private final Database database;

    protected Table(String file, String name) {
        this.database = new Database(file);
        this.name = name;
    }

    protected abstract String getTableDesc();

    public void create() {
        database.executeUpdate(
                "CREATE TABLE IF NOT EXISTS " + name + getTableDesc());
    }

    protected abstract String getInsertTupleTemplate();

    protected abstract String getObjectInsertTuple(T object);

    public void insert(T object) {
        database.executeUpdate("INSERT INTO " + name + " " +
                getInsertTupleTemplate() + " VALUES " + getObjectInsertTuple(object));
    }

    protected <K> List<K> select(
            String what,
            String orderBy,
            String limit,
            Parser<K> parser) {
        return database.executeQueryAndProcess(
                "SELECT " + what + " FROM " + name + " " + orderBy + " " + limit,
                parser);
    }

    public int countAll() {
        return select("COUNT(*)", "", "", Parsers.INTEGER_PARSER).get(0);
    }
}