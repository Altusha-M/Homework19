FROM jboss/wildfly
COPY target/deployment/Homework19.war /opt/jboss/wildfly/standalone/deployments/
