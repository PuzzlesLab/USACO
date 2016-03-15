#!/bin/sh
git pull origin master
now="$(date): update all by chia yi"

git add -A README.md
git add -A chiayi/*
git add -A update-chiayi.sh

git commit -m "$now"
git push origin master