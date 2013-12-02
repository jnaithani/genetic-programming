@echo off
rem COMPILE
echo Compiling classes ...
if not exist .\classes mkdir classes
javac -classpath .\lib\jcommon-1.0.21.jar;.\lib\jfreechart-1.0.17.jar -d classes src\GeneticProgrammingMain.java src\data\*.java src\utilities\*.java

rem BUILD
echo Building gps.jar ...
jar cvfm gps.jar src\Manifest.txt -C classes .

rem END
echo Done.
echo To execute program: java -jar gps.jar
echo Note: Thirdparty libraries should be located under the 'lib' folder
