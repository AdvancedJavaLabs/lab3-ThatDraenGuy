package ru.draen.parallel;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SaleMapper extends Mapper<Object, Text, Text, SaleData> {
    private Text outKey = new Text();
    private SaleData outValue = new SaleData();

    @Override
    protected void map(Object key, Text value, Mapper<Object, Text, Text, SaleData>.Context context) throws IOException, InterruptedException {
        if (value.toString().startsWith("transaction_id")) return;
        String[] fields = value.toString().split(",");
        outKey.set(fields[2]);
        outValue.setPrice(Double.parseDouble(fields[3]));
        outValue.setQuantity(Long.parseLong(fields[4]));
        context.write(outKey, outValue);
    }
}
