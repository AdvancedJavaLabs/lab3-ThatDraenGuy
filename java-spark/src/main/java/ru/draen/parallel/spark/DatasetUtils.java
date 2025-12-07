package ru.draen.parallel.spark;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import ru.draen.parallel.model.AppContext;
import ru.draen.parallel.model.schema.Schema;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DatasetUtils {
    public static<T> JavaRDD<T> readData(AppContext ctx, String path, Schema<T> schema) {
        Dataset<Row> dataset = ctx.getSparkSs().read()
                .schema(schema.sparkSchema())
                .format("csv")
                .option("header","true")
                .load(path);

        return dataset.javaRDD().map(schema::fromRow);
    }

    public static<T> void writeData(AppContext ctx, String path, Schema<T> schema, JavaRDD<T> data) {
        Dataset<Row> dataset = ctx.getSparkSs()
                .createDataFrame(data.map(schema::toRow), schema.sparkSchema());
        dataset.write()
                .mode("overwrite")
                .format("csv")
                .save(path);
    }
}
