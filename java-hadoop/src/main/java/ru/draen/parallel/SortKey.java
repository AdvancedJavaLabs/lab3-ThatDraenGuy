package ru.draen.parallel;

import lombok.Data;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
public class SortKey implements WritableComparable<SortKey> {
    private Double revenue;
    private Text category;

    @Override
    public int compareTo(SortKey o) {
        return o.revenue.compareTo(revenue);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        category.write(dataOutput);
        dataOutput.writeDouble(revenue);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        category = new Text();
        category.readFields(dataInput);
        revenue = dataInput.readDouble();
    }
}
