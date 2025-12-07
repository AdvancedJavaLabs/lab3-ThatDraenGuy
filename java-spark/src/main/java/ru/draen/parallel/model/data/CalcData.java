package ru.draen.parallel.model.data;

import lombok.Data;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.types.StructType;
import ru.draen.parallel.model.schema.BaseSchema;
import ru.draen.parallel.model.schema.Schema;
import scala.collection.immutable.Seq;

import java.io.Serializable;

@Data
public class CalcData implements Serializable {
    public static final Schema<CalcData> SCHEMA = new BaseSchema<CalcData>() {
        @Override
        public StructType sparkSchema() {
            return StructType.fromDDL("category STRING, revenue DOUBLE, quantity LONG");
        }

        @Override
        public CalcData fromRow(Row row) {
            throw new NotImplementedException();
        }

        @Override
        public Row toRow(CalcData item) {
            return RowFactory.create(
                    item.category,
                    item.revenue,
                    item.quantity
            );
        }
    };

    private String category;
    private Double revenue;
    private Long quantity;

    public static CalcData empty() {
        CalcData data = new CalcData();
        data.setRevenue(0.0);
        data.setQuantity(0L);
        return data;
    }
}
