package ru.draen.parallel;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SaleReducer extends Reducer<Text, SaleData, NullWritable, Text> {
    private Text out = new Text();

    @Override
    protected void reduce(Text key, Iterable<SaleData> values, Reducer<Text, SaleData, NullWritable, Text>.Context context) throws IOException, InterruptedException {
        double sumPrice = 0.0;
        long sumQuantity = 0;
        for (SaleData item : values) {
            sumPrice += item.getPrice();
            sumQuantity += item.getQuantity();
        }

        out.set(key.toString() + "," + sumPrice + "," + sumQuantity);
        context.write(NullWritable.get(), out);
    }

}
