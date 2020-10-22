# keycloak One Time Pin implementation
Intended to extend [Keycloak](https://www.keycloak.org/) features through an In-memory One Time Pin generator. This project implements [Keycloak one time pin api](https://github.com/raitonbl/keycloak-one-time-pin)

## Building

Ensure you have JDK 8 (or newer), Maven 3.5.4 (or newer) and Git installed

    java -version
    mvn -version
    git --version

How to build:

    mvn clean package

## Deployment    

In order to deploy the implementation , Keycloak must be stopped and the generated jar should be deployed on **KEYCLOAK_HOME/providers/** (for containers) or on **KEYCLOAK_HOME/standalone/deployments/**.
Its important that [API](https://github.com/raitonbl/keycloak-one-time-pin) version matches the plugin version and is also deployed on Keycloak.

Start **Keycloak** , [More details](https://www.keycloak.org/documentation.html)

