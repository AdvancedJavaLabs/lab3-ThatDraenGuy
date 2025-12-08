#!/bin/bash

$HADOOP_HOME/bin/hdfs dfs -mkdir -p /data/input/
$HADOOP_HOME/bin/hdfs dfs -rm -r /data/input/*
$HADOOP_HOME/bin/hdfs dfs -copyFromLocal -f /opt/hadoop/data/* /data/input/

$HADOOP_HOME/bin/hadoop jar $JAR_FILEPATH $CLASS_TO_RUN $PARAMS
