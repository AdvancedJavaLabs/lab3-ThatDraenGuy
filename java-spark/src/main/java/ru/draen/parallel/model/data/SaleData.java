package ru.draen.parallel.model.data;

import lombok.Data;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructType;
import ru.draen.parallel.model.schema.BaseSchema;
import ru.draen.parallel.model.schema.Schema;

import java.io.Serializable;

@Data
public class SaleData implements Serializable {
    public static final Schema<SaleData> SCHEMA = new BaseSchema<SaleData>() {
        @Override
        public StructType sparkSchema() {
            return StructType.fromDDL("transaction_id LONG, product_id LONG, category STRING, price DOUBLE, quantity LONG");
        }

        @Override
        public SaleData fromRow(Row row) {
            SaleData data = new SaleData();
            data.setTransactionId(get(row, "transaction_id"));
            data.setProductId(get(row, "product_id"));
            data.setCategory(get(row, "category"));
            data.setPrice(get(row, "price"));
            data.setQuantity(get(row, "quantity"));
            return data;
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
