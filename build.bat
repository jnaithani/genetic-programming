@echo off
rem COMPILE
echo Compiling classes ...
if not exist .\classes mkdir classes
javac -classpath .\lib\jcommon-1.0.21.jar;.\lib\jfreechart-1.0.17.jar -d classes src\GeneticProgrammingMain.java src\data\*.java src\utilities\*.java

rem BUILD
echo Building gps.jar ...
jar cvfm gps.jar src\Manifest.txt -C classes .

rem PACKAGE
echo Packing gps.zip ...
if not exist .\deliv mkdir deliv
jar cvfM deliv\gps.zip gps.jar settings.properties trainingdata.txt run.bat lib/*  deliv/readme.txt

rem END
echo Done.
echo To execute program, do the following: 
echo    	cd deliv
echo    	jar xvf gps.zip
echo    	java -jar gps.jar
