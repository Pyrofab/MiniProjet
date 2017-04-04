@echo off
if [%1]==[] (
	git pull
	git config --global credential.helper cache
	git config --global credential.helper 'cache --timeout=3600'
	git add src/* *.sh
	git commit -a -m %1
	git push origin
) else (
	git pull
	git config --global credential.helper cache
	git config --global credential.helper 'cache --timeout=3600'
	git add src/* *.sh
	git commit -a -m %1
	git push origin
)
