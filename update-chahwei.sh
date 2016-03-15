#!/bin/sh
git pull origin master
now="$(date): update all by chahwei"

git add -A README.md
git add -A chahwei/*
git add -A update-chahwei.sh

git commit -m "$now"
git push origin master