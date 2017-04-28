#!/usr/bin/env bash


for i in hackathon* ; do
  echo "Building $i"
  cd $i
  ./buildImage.sh 1
  docker push toonborgers/$i:1
  cd ..
done