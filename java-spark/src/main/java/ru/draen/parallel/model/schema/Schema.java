package ru.draen.parallel.model.schema;

import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructType;

import java.io.Serializable;

public interface Schema<T> extends Serializable {
    StructType sparkSchema();
    T fromRow(Row row);
    Row toRow(T item);
}
