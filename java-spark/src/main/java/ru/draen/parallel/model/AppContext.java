package ru.draen.parallel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.SparkSession;
import ru.draen.parallel.hadoop.HDFSHelper;

@Getter
@AllArgsConstructor
public class AppContext {
    private final SparkContext sparkCtx;
    private final SparkSession sparkSs;
    private final HDFSHelper hdfsHelper;
}
