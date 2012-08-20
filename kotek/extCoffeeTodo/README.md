# ExtJS-DeftJS-CoffeeScript-Grails Todo Application

# About

Simple Todo app that demonstrates using ExtJS, DeftJS, and CoffeeScript as the front-end for a Grails application.

* http://grails.org/
* http://www.sencha.com/products/extjs/
* http://deftjs.org/
* http://coffeescript.org/

# Building the CoffeeScript

The repository has all compiled JavaScript committed under /web-app/app. However, if you wish to modify and compile the .coffee files:

* The /build folder contains a batch file and a Cakefile.
* On Windows, you can run the batch file and pass in arguments to specify the location of the source CoffeeScript and compiled JavaScript output directories.
* To run the batch file directory, run "coffeescript parameterized watcher.bat ../src/coffee ../web-app/app"
* To set up the compiler as an External Tool in Intellij IDEA, use a setup like:
    * Program: $ProjectFileDir$\build\coffeescript parameterized watcher.bat
    * Parameters: ../src/coffee ../web-app/app
    * Working directory: $ProjectFileDir$\build
* To run the Cakefile manually, go to the folder in a console window and run "cake -s ../src/coffee -o ../web-app/app watchany"

