package ru.draen.parallel;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SortMapper extends Mapper<Object, Text, SortKey, Text> {
    private SortKey sortKey = new SortKey();
    private Text out = new Text();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] values = value.toString().split(",");
        out.set(value);
        sortKey.setCategory(new Text(values[0]));
        sortKey.setRevenue(Double.parseDouble(values[1]));
        context.write(sortKey, out);
    }
}
