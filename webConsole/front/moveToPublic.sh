#!/usr/bin/env bash

# ライブラリを配置する
rm -r ./public/libs
mkdir -p ./public/libs/
cp ./node_modules/jquery/dist/jquery.min.js ./public/libs/