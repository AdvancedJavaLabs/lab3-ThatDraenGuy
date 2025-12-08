package ru.draen.parallel;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();

        FileSystem.get(conf).delete(new Path("/temp"), true);

        Job job = Job.getInstance(conf, "java-hadoop");
        job.setJarByClass(App.class);
        job.setMapperClass(SaleMapper.class);
        job.setReducerClass(SaleReducer.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(SaleData.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(job, new Path(args[1]));
//        FileOutputFormat.setOutputPath(job, new Path(args[2]));
        FileOutputFormat.setOutputPath(job, new Path("/temp"));

        if (!job.waitForCompletion(true)) {
            System.exit(1);
        }

        Job sort = Job.getInstance(conf, "java-hadoop-sort");
        sort.setJarByClass(App.class);
        sort.setMapperClass(SortMapper.class);
        sort.setReducerClass(SortReducer.class);
        sort.setInputFormatClass(TextInputFormat.class);
        sort.setMapOutputKeyClass(SortKey.class);
        sort.setMapOutputValueClass(Text.class);
        sort.setOutputKeyClass(NullWritable.class);
        sort.setOutputValueClass(Text.class);
        sort.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(sort, new Path("/temp"));
        FileOutputFormat.setOutputPath(sort, new Path(args[2]));
        System.exit(sort.waitForCompletion(true) ? 0 : 1);
    }
}
