@ECHO OFF
COLOR F0
ECHO .
ECHO To use CoffeeScript, first install Node.js from nodejs.org
ECHO Next, download the CoffeeScript module by opening a command prompt and typing: npm install -g coffee-script
ECHO That's it.
ECHO .
ECHO Hint: to run a CoffeeScript watcher, call this batch file like "{batchfile}.bat ../coffee ../app
ECHO Hint: to run a CoffeeScript watcher and deployer, call this batch file like "{batchfile}.bat ../coffee ../app ../deployfolder
ECHO .
ECHO .

IF [%1]==[] GOTO failure
IF [%2]==[] GOTO failure
IF [%3]==[] GOTO watchonly

call cake -s %1 -o %2 -d %3 watchany
pause
GOTO :eof

:watchonly
call cake -s %1 -o %2 watchany
pause
GOTO :eof

:failure
ECHO You mast pass at least two parameters to this batch file.
ECHO First parameter must be the CoffeeScript source directory path.
ECHO Second parameter must be the JavaScript output directory path.
ECHO Optional third parameter is the deploy directory.
pause