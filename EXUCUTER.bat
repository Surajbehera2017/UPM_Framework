cd /d D:/Suraj/Selemnium_TC/UPM

for /f "delims=" %%i in (TC_NAME.txt) do set c=%%i
echo %c%
java -jar UPM_LATEST.jar 

exit