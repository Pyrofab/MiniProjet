git config --global credential.helper cache
git config --global credential.helper 'cache --timeout=3600'
git add *.java
git commit -m $1
git push origin
