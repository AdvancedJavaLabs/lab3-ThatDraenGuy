FROM bde2020/hadoop-base:2.0.0-hadoop3.2.1-java8

COPY java-hadoop/target/java-hadoop-1.0-SNAPSHOT.jar /opt/hadoop/applications/job.jar
COPY *.csv /opt/hadoop/data/

ENV JAR_FILEPATH="/opt/hadoop/applications/job.jar"
ENV CLASS_TO_RUN="ru.draen.parallel.App"
ENV PARAMS="/data/input /data/output"

ADD java-hadoop/docker/run.sh /run.sh
RUN chmod a+x /run.sh

CMD ["/run.sh"]
