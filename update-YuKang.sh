#!/bin/sh
git pull origin master
now="$(date): update all by YuKang"

git add -A README.md
git add -A YuKang/*
git add -A update-YuKang.sh

git commit -m "$now"
git push origin master