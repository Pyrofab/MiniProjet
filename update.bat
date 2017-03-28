@echo off
if [%1]==[] (
	echo "Veuillez ajouter un message de commit en argument."
	exit /B 1
) else (
	git pull
	git config --global credential.helper cache
	git config --global credential.helper 'cache --timeout=3600'
	git add src/* *.sh
	git commit -a -m %1
	git push origin
)
