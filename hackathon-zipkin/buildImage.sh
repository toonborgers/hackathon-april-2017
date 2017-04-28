#!/usr/bin/env bash

VERSION=$1

if [[ $# -eq 0 ]] ; then
    echo 'Version required'
    exit 1
fi

./gradlew assemble

rm -f *.jar

cp build/libs/*.jar zipkin.jar

docker build -t toonborgers/hackathon-zipkin:$VERSION .