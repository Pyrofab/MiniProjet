#!/bin/sh
if [[ $# -eq 0 ]]; then
  echo "Veuillez ajouter un message de commit en argument."
  exit
fi
git pull
git config --global credential.helper cache
git config --global credential.helper 'cache --timeout=3600'
git add src/* *.sh *.bat
git commit -a -m $1
git push origin
