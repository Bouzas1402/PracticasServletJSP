# Use latest jboss/base-jdk:11 image as the base
FROM jboss/base-jdk:11

# Set the WILDFLY_VERSION env variable
ENV WILDFLY_VERSION 23.0.2.Final
ENV WILDFLY_NAME  wildfly-preview
ENV JBOSS_HOME /opt/jboss/wildfly

USER root

# Add the WildFly distribution to /opt, and make wildfly the owner of the extracted tar content
# Make sure the distribution is available from a well-known place
RUN cd $HOME \
    && curl -L -O https://download.jboss.org/wildfly/$WILDFLY_VERSION/$WILDFLY_NAME-$WILDFLY_VERSION.tar.gz \
    && tar xf $WILDFLY_NAME-$WILDFLY_VERSION.tar.gz \
    && mv $HOME/$WILDFLY_NAME-$WILDFLY_VERSION $JBOSS_HOME \
    && rm $WILDFLY_NAME-$WILDFLY_VERSION.tar.gz \
    && chown -R jboss:0 ${JBOSS_HOME} \
    && chmod -R g+rw ${JBOSS_HOME}

# Ensure signals are forwarded to the JVM process correctly for graceful shutdown
ENV LAUNCH_JBOSS_IN_BACKGROUND true

USER jboss

ENV ADMIN_USER admin
ENV ADMIN_PASS admin

RUN $JBOSS_HOME/bin/add-user.sh -u $ADMIN_USER -p $ADMIN_PASS -g admin --silent

# Expose the ports in which we're interested
EXPOSE 8080 9990 8787

COPY mysql-connector-java-8.0.25.jar $JBOSS_HOME/modules/system/layers/base/com/mysql/main/
COPY module.xml $JBOSS_HOME/modules/system/layers/base/com/mysql/main/
COPY standalone.xml $JBOSS_HOME/standalone/configuration/

# Set the default command to run on boot
# This will boot WildFly in standalone mode and bind to all interfaces
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "--debug", "*:8787"] 