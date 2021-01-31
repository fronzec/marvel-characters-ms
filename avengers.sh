#!/bin/bash

echo "RUNNING APP"
if ./mvnw spring-boot:run; then
    echo "Project started"
else
    echo "Project can't run"
fi