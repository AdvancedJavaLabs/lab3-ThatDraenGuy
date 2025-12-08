package ru.draen.parallel;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SortReducer extends Reducer<SortKey, Text, NullWritable, Text> {
    private Text out = new Text();

    @Override
    protected void reduce(SortKey key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text t : values) {
            out.set(t);
            context.write(NullWritable.get(), out);
            return;
        }
    }
}
