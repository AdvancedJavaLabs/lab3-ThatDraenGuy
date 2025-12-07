package ru.draen.parallel.model.schema;

import org.apache.spark.sql.Row;

import java.util.function.Function;

public abstract class BaseSchema<T> implements Schema<T> {

    protected <F> F get(Row row, String key) {
        return get(row, key, Function.identity());
    }

    protected<F, R> R get(Row row, String key, Function<F, R> mapper) {
        int index = row.fieldIndex(key);
        return mapper.apply(row.getAs(index));
    }
}
