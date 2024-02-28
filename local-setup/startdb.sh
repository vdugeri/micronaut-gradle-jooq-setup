#!/bin/sh

# This script is used for local development only, and should be run from `gradlew startdb`
set -e
. .env

# Stops any existing containers that may conflict
docker-compose down

if [ -n "$(netstat -l | grep -e \"$POSTGRES_PORT\W\")" ]
  then
	echo "Database port $POSTGRES_PORT currently in use."
	exit 1
fi

# Run only postgres in detached mode
docker-compose up -d postgres

# Wait until postgres containers are ready
until docker exec order-substitution-postgres pg_isready; do sleep 3 ; done