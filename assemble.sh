#!/bin/bash

echo "BUILDING PROJECT AND RUNNIGN TEST"
if ./mvnw clean package; then
    echo "Project was packaged"
else
    echo "Fail on BUILDING"
    echo "====END===="
fi