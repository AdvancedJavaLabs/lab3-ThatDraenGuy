DOCKER_NETWORK = lab3-thatdraenguy_default
ENV_FILE = hadoop.env
HADOOP_CONTAINER = bde2020/hadoop-base:2.0.0-hadoop3.2.1-java8

job:
	docker build -t files-job -f ./job/Dockerfile .
	docker run --network ${DOCKER_NETWORK} --env-file ${ENV_FILE} ${HADOOP_CONTAINER} hdfs dfs -rm -r /data/output
	docker run --network ${DOCKER_NETWORK} --env-file ${ENV_FILE} files-job
	docker run --network ${DOCKER_NETWORK} --env-file ${ENV_FILE} ${HADOOP_CONTAINER} hdfs dfs -ls /data/output
	docker run --network ${DOCKER_NETWORK} --env-file ${ENV_FILE} ${HADOOP_CONTAINER} hdfs dfs -cat /data/output/*.csv

.PHONY: job