@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac -cp "C:\Users\Andrew\myproject\CS 2113\ip\src\main\java" -Xlint:none -d ..\bin "C:\Users\Andrew\myproject\CS 2113\ip\src\main\java\NekoBot.java"
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM run the program, feed commands from input.txt file and redirect the output to ACTUAL.TXT
java -classpath ..\bin NekoBot < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
