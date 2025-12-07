FROM bde2020/hadoop-base:2.0.0-hadoop3.2.1-java8

ENV SPARK_HOME=${SPARK_HOME:-"/opt/spark"}
ENV SPARK_VERSION=3.5.7

RUN mkdir -p ${SPARK_HOME} \
    && curl https://dlcdn.apache.org/spark/spark-${SPARK_VERSION}/spark-${SPARK_VERSION}-bin-hadoop3.tgz -o spark-${SPARK_VERSION}-bin-hadoop3.tgz \
    && tar xvzf spark-${SPARK_VERSION}-bin-hadoop3.tgz --directory /opt/spark --strip-components 1 \
    && rm -rf spark-${SPARK_VERSION}-bin-hadoop3.tgz

COPY java-spark/target/java-spark-1.0-SNAPSHOT.jar /opt/hadoop/applications/job.jar
COPY *.csv /opt/hadoop/data/

ENV JAR_FILEPATH="/opt/hadoop/applications/job.jar"
ENV CLASS_TO_RUN="ru.draen.parallel.App"
ENV PARAMS="/data/input /data/output"

ADD job/run.sh /run.sh
RUN chmod a+x /run.sh

CMD ["/run.sh"]
