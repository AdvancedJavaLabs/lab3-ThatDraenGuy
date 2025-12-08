package ru.draen.parallel;

import org.apache.spark.sql.*;

import static org.apache.spark.sql.functions.sum;

public class App {
    public static void main(String[] args) {
        SparkSession ss = SparkSession.builder().getOrCreate();

        Dataset<Row> data = ss.read()
                .schema("transaction_id LONG, product_id LONG, category STRING, price DOUBLE, quantity LONG")
                .format("csv")
                .option("header","true")
                .load(args[0]);

        Column category = data.col("category");
        Column price = data.col("price");
        Column quantity = data.col("quantity");
        Column revenue = sum(price).name("revenue");
        Column quantitySum = sum(quantity).name("quantitySum");
        data.groupBy(category)
                .agg(revenue, quantitySum)
                .sort(revenue.desc())
                .write()
                .mode("overwrite")
                .format("csv")
                .option("header","true")
                .save(args[1]);

    }
}
