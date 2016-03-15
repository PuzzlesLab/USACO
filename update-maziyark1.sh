#!/bin/sh

git pull origin master

now="$(date): update all by MaziarKosarifar"

git add -A README.md
git add -A MaziarKosarifar/*
git add -A update-maziyark1.sh

git commit -m "$now"

git push origin master 