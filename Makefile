DOCKER_NETWORK = lab3-thatdraenguy_default
ENV_FILE = hadoop.env
HADOOP_CONTAINER = bde2020/hadoop-base:2.0.0-hadoop3.2.1-java8

java-hadoop:
	docker build -t java-hadoop-job:latest -f ./java-hadoop/Dockerfile .
	docker run --network ${DOCKER_NETWORK} --env-file ${ENV_FILE} ${HADOOP_CONTAINER} hdfs dfs -rm -r -f /data/output
	docker run --network ${DOCKER_NETWORK} --env-file ${ENV_FILE} java-hadoop-job
	docker run --network ${DOCKER_NETWORK} --env-file ${ENV_FILE} ${HADOOP_CONTAINER} hdfs dfs -ls /data/output
	docker run --network ${DOCKER_NETWORK} --env-file ${ENV_FILE} ${HADOOP_CONTAINER} hdfs dfs -cat /data/output/*


java-spark:
	docker build -t java-spark-job:latest -f ./java-spark/Dockerfile .
	docker run --network ${DOCKER_NETWORK} --env-file ${ENV_FILE} ${HADOOP_CONTAINER} hdfs dfs -rm -r -f /data/output
	docker run --network ${DOCKER_NETWORK} --env-file ${ENV_FILE} java-spark-job
	docker run --network ${DOCKER_NETWORK} --env-file ${ENV_FILE} ${HADOOP_CONTAINER} hdfs dfs -ls /data/output
	docker run --network ${DOCKER_NETWORK} --env-file ${ENV_FILE} ${HADOOP_CONTAINER} hdfs dfs -cat /data/output/*.csv

java-spark-short:
	docker build -t java-spark-short-job:latest -f ./java-spark-short/Dockerfile .
	docker run --network ${DOCKER_NETWORK} --env-file ${ENV_FILE} ${HADOOP_CONTAINER} hdfs dfs -rm -r -f /data/output
	docker run --network ${DOCKER_NETWORK} --env-file ${ENV_FILE} java-spark-short-job
	docker run --network ${DOCKER_NETWORK} --env-file ${ENV_FILE} ${HADOOP_CONTAINER} hdfs dfs -ls /data/output
	docker run --network ${DOCKER_NETWORK} --env-file ${ENV_FILE} ${HADOOP_CONTAINER} hdfs dfs -cat /data/output/*.csv

.PHONY: java-hadoop java-spark java-spark-short