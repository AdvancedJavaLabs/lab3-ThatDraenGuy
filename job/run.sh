#!/bin/bash

$HADOOP_HOME/bin/hdfs dfs -mkdir -p /data/input/
$HADOOP_HOME/bin/hdfs dfs -rm -r /data/input/*
$HADOOP_HOME/bin/hdfs dfs -copyFromLocal -f /opt/hadoop/data/* /data/input/

#$HADOOP_HOME/bin/hadoop jar $JAR_FILEPATH $CLASS_TO_RUN $PARAMS
/opt/spark/bin/spark-submit --class $CLASS_TO_RUN --master yarn --deploy-mode cluster $JAR_FILEPATH $PARAMS
