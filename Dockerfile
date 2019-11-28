FROM jboss/wildfly
COPY target/Homework19.war /opt/jboss/wildfly/standalone/deployments/
