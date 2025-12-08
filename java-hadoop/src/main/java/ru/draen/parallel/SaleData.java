package ru.draen.parallel;

import lombok.Data;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
public class SaleData implements Writable {
    private Double price;
    private Long quantity;

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeDouble(price);
        dataOutput.writeLong(quantity);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        price = dataInput.readDouble();
        quantity = dataInput.readLong();
    }

    @Override
    public String toString() {
        return price + "," + quantity;
    }
}
