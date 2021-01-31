#!/bin/bash

echo "BUILDING PROJECT AND RUNNIGN TEST"
if ./mvnw clean verify; then
    echo "BUILDING DOCKER IMAGES"
    cd devEnv/
    if docker-compose build; then
        echo "UP AND RUNNING CONTAINERS"
        docker-compose up -d;
        docker-compose ps;
        echo -n "Do you want all container logs? (By default only will show app container logs) [y/n] "
        read option
        if [[ "$option" == "y" ]]; then
            echo "SHOWING ALL CONTAINERS LOGS";
            docker-compose logs -f;
        else
            echo "SHOWING ONLY APP CONTAINER LOGS";
            docker-compose logs -f app;
        fi
    else
        echo "THERE WAS A ERROR TRYING TO BUILD DOCKER IMAGES"
    fi
else
    echo "TEST FAILED, BUILD FAILED"
    echo "====END===="
fi
