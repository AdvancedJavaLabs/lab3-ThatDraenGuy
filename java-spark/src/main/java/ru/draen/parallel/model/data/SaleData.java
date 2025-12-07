package ru.draen.parallel.model.data;

import lombok.Data;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructType;
import ru.draen.parallel.model.schema.BaseSchema;
import ru.draen.parallel.model.schema.Schema;

@Data
public class SaleData {
    public static final Schema<SaleData> SCHEMA = new BaseSchema<SaleData>() {
        @Override
        public StructType sparkSchema() {
            return StructType.fromDDL("struct<transaction_id INT, product_id INT, category STRING, price DOUBLE, quantity INT>");
        }

        @Override
        public SaleData fromRow(Row row) {
            throw new NotImplementedException();
        }

        @Override
        public Row toRow(SaleData item) {
            throw new NotImplementedException();
        }
    };

    private Long transactionId;
    private Long productId;
    private String category;
    private Double price;
    private Long quantity;
}
