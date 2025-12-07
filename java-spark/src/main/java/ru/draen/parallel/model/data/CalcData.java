package ru.draen.parallel.model.data;

import lombok.Data;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructType;
import ru.draen.parallel.model.schema.BaseSchema;
import ru.draen.parallel.model.schema.Schema;

@Data
public class CalcData {
    public static final Schema<CalcData> SCHEMA = new BaseSchema<CalcData>() {
        @Override
        public StructType sparkSchema() {
            return StructType.fromDDL("struct<category STRING, revenue DOUBLE, quantity INT>");
        }

        @Override
        public CalcData fromRow(Row row) {
            throw new NotImplementedException();
        }

        @Override
        public Row toRow(CalcData item) {
            throw new NotImplementedException();
        }
    };

    private String category;
    private Double revenue;
    private Long quantity;
}
