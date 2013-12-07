*** To install the Genetic Programming System on Windows

Download file into a directory

Unzip the file.

	jar xvf gps.zip

Or use WinZip/7Zip

*** To execute:

Open a command window

Make sure JDK/JRE 1.6/1.7 is installed and is in the environment path

Change directory to where the files have been extracted and execute the following script:

	run 

To re-direct output into a file called 'output.txt':

	run > output.txt 

*** Settings.properties

Program settings are located and described in settings.properties

*** Training Data

The Training Data is located in trainingdata.txt

The target function used to generate the training data is:

	(-3x^2 + 7)/2

*** Output

The program ouput displays on the console

Execution details and X-Y charts are generated:

	- initial_population_fitness.jpg
	- final_population_fitness.jpg
	- bestfitness_generation.jpg
        - xy_graph.jpg
