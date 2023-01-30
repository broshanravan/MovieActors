*********************************************************************************
*								MovieActors										*
*********************************************************************************


Packaging the application:
	To run the application first the application needs to be packaged to a Jar file.
	To do so, navigate to the root folder of he application and run the command:

			gradle fatJar to be re-looked into
			
However this application is only a library which has to be either plugged into a larger application,
or it needs a GUI to be operate as and stand alone app.
For the purpose of testing the file "MovieActorsList" has been added to the root of the project.
Running the unit Test on the maintenance class will be able to demonstrate	the	operation.
There is also a main file being created in case it was necessary to test the app on its own with new values,in which case this
 needs to be used from an IDE and the main method needs to be updated accordingly

Note for the DOB format it was decided to use String as the format in the file did not mach any 
standard date format pattern (such as dd/mm/yyy, mm/dd/yy,
dd-MMM-yyyy ...);

It was presumed that the actor Id is unique and hence before adding the actor it will be determined if the Id is already in use;

Note: Because there could be more than one Actor with one name, the function retrieving the actor by name will return a collection.
The file.
Also to run the application via IDE the fileMovieActorWorld with a  void main main has been added. or alternativly you could navigate to the folder ther your jar file is and run

Java -jar movieActors