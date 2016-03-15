#!/bin/sh
git pull origin master
now="$(date): update all by oka"

git add -A README.md
git add -A oka/*
git add -A update-oka.sh

git commit -m "$now"
git push origin master