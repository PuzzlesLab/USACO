#!/bin/sh
git pull origin master
now="$(date): update all by kamwoh"

git add -A README.md
git add -A kamwoh/*
git add -A update-kamwoh.sh

git commit -m "$now"
git push origin master