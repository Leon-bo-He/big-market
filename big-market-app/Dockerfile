# Base image
FROM openjdk:8-jre-slim

# Maintainer
LABEL maintainer="bobo"

# Configuration
ENV PARAMS=""

# Timezone
ENV TZ=America/Los_Angeles
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Add application
ADD target/big-market-app.jar /big-market-app.jar

ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /big-market-app.jar $PARAMS"]