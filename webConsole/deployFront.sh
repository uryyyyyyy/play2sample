#!/usr/bin/env bash

rm -r -f ./public/
cp -R ./front/public/ ./public/
cp ./front/index.html ./public/
cp ./front/login.html ./public/