#!/bin/bash

declare project_dir=$(dirname $0)
declare dc_main=${project_dir}/docker-compose.yml
declare dc_existing=${project_dir}/docker-compose-existing-image.yml

function restart() {
    stop
    start
}

function stop() {
    echo "Stopping Event Service...."
    docker-compose -f ${dc_main} stop
    docker-compose -f ${dc_main} rm -f
}

function start() {
    echo "Starting Event service...."
    build_api
    docker-compose -f ${dc_main}  up --build --force-recreate -d
}

function build_api() {
    mvn clean package
}

function run_existing_image() {
    echo "Starting Existing Docker Images...."
    docker-compose -f ${dc_existing}  up --build --force-recreate -d
}

action="start"

if [[ "$#" != "0"  ]]
then
    action=$@
fi

eval ${action}