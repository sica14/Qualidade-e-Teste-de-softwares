FROM justb4/jmeter:latest AS jmeter

FROM jenkins/jenkins:lts-jdk17

USER root

ARG JMETER_VERSION=5.5

COPY --from=jmeter /opt/apache-jmeter-${JMETER_VERSION} /opt/apache-jmeter-${JMETER_VERSION}

RUN apt-get update \
    && apt-get install -y --no-install-recommends git maven \
    && ln -s "/opt/apache-jmeter-${JMETER_VERSION}/bin/jmeter" /usr/local/bin/jmeter \
    && rm -rf /var/lib/apt/lists/*

USER jenkins
