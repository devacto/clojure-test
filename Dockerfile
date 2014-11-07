FROM dockerfile/java
MAINTAINER Victor Wibisono <mail@victor.ac>

RUN sudo apt-get update

ADD target/clojure-test-0.1.0-SNAPSHOT-standalone.jar /srv/clojure-test.jar

EXPOSE 3000

CMD ["java", "-jar", "/srv/clojure-test.jar"]
