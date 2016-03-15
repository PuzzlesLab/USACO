#!/bin/sh

git pull origin master

now="$(date): update all by Shahin"

git add -A README.md
git add -A Shahin/*
git add -A update-Shahin.sh

git commit -m "$now"

git push origin master 