Invite Nearby Customers
==================================

Getting Started
---------

The application is written in Kotlin and built/run with Gradle  
Source can be found at [src/main/kotlin/com/marklynch/invitenearbycustomers/](src/main/kotlin/com/marklynch/invitenearbycustomers/)  
The main entry point for the application is the  function main in [src/main/kotlin/com/marklynch/invitenearbycustomers/Main.kt](src/main/kotlin/com/marklynch/invitenearbycustomers/Main.kt)  
There are 2 data classes - [Customer](src/main/kotlin/com/marklynch/invitenearbycustomers/data/Customer.kt) and [Geolocation](src/main/kotlin/com/marklynch/invitenearbycustomers/data/Geolocation.kt), and 1 utility class - [FileReader](src/main/kotlin/com/marklynch/invitenearbycustomers/io/FileReader.kt)  
Tests can be found in [src/test/kotlin/com/marklynch/invitenearbycustomers/](src/test/kotlin/com/marklynch/invitenearbycustomers/)  
The default customer.txt input file read when running the app is [src/main/resources/customers.txt](src/main/resources/customers.txt)  

Prerequisites for running
---------

The application and tests can be run in the IDEs or in a console  
[Java (version 8 minimum)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)  
[Gradle (version 4.1 minimum)](https://gradle.org/install/)  
Optional to run in IDEs - Android Studio or IntelliJ IDEA can be used to run the application  
	[Android Studio (3.5 recommended)](https://developer.android.com/studio)  
	[IntelliJ IDEA (2019.2.4 recommended)](https://www.jetbrains.com/idea/download)  

Running in AndroidStudio or IntelliJ IDEA 
---------

1. Import the project  
	a. File -> Open  
	b. In the file explorer select the root folder cloned or downloaded from github (this folder contains build.gradle, .gitignore, settings.gradle etc...)  
	c. Click OK  
2. Find the Project view, it may be minimised on the left, if it's not in the window you can open it with View Tool Windows -> Project  
3. In the Project view select Project from the dropdown menu at the top  
4. In the Project view right click on src/main/kotlin/com/marklynch/invitenearbycustomers/Main.kt, then select "|> Run 'com.marklynch.invite...'"  
5. Results of the application will be shown in the Run view, which will make itself visible  

Running the tests in AndroidStudio or IntelliJ IDEA 
---------

1. To run the unit tests - In the Project view right click on the top level folder and click "|> Run all tests"  
2. Results of the tests will be shown in the Run view, which will make itself visible

Running in Console
---------

1. Navigate to the root folder cloned or downloaded from github (this folder contains build.gradle, .gitignore, settings.gradle etc...)  
2. To run the application -  
	On Mac or Linux use the command: ./gradlew run  
	On Windows use the command: gradlew run  
	
Running the tests in Console
---------

1. To run the unit tests in the root folder -  
	On Mac or Linux use the command: ./gradlew test  
	On Windows use the command: gradlew test  
	
Notes
---------
	
An alternative file can be passed to the application when running  
	For the IDEs instructions are [here](https://intellij-support.jetbrains.com/hc/en-us/community/posts/115000792310-Pass-arguments-when-running-the-app)  
	For console run for example "./gradlew run ./src/assets/othercusomters"
	
Sample output can be found at [here](output.txt)






