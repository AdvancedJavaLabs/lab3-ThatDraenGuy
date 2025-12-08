package ru.draen.parallel;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.SparkSession;
import ru.draen.parallel.hadoop.HDFSHelper;
import ru.draen.parallel.model.AppConfig;
import ru.draen.parallel.model.AppContext;
import ru.draen.parallel.process.SalesProcessor;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        SparkSession ss = SparkSession.builder().getOrCreate();
        SparkContext sparkCtx = ss.sparkContext();
        Configuration hadoopConf = sparkCtx.hadoopConfiguration();

        AppContext ctx = new AppContext(sparkCtx, ss, new HDFSHelper(FileSystem.get(hadoopConf)));
        AppConfig cfg = new AppConfig();
        cfg.setInputPath(args[0]);
        cfg.setOutputPath(args[1]);

        new SalesProcessor(ctx, cfg).process();
    }
}
