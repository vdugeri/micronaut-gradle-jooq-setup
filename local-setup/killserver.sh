#!/bin/sh

# kill debug port
port="${DEBUG_PORT:=8081}"
pid=$(lsof -i tcp:"$port" | awk '{print $2}' | tail -n1)

if [[ "$pid" ]]; then
  kill -9 $pid
  echo "Killed debugger $pid."
else
  echo "No need to kill. The debugger is not running."
fi

# kill app port
port="${APP_PORT:=8080}"
pid=$(lsof -i tcp:"$port" | awk '{print $2}' | tail -n1)

if [[ "$pid" ]]; then
  kill -9 $pid
  echo "Killed server $pid."
else
  echo "No need to kill. The server is not running."
fi


