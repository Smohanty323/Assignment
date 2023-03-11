Restful-booker API Test Automation

Pre-Requsite - 
 
 Software Instalation -
 1. Java - version 8 or more
 2. Maven 3.5 above 
 3. Eclipse(if wished to check project in IDE)
 4. TestNg in eclipse (if wish to execute in Eclipse)
 5. Chrome browser version 111 - (for automatically report genereate)
 
 Execution - 
 
 There multiple way to run the project - 
 
 Command prompt execution -  
 1. Open project directory in CMD and give command- mvn test
      - this will execute entire project 
	  
 2. to run only one scenario in cmd - check for the tags in feature file present in src/test/resource/BookingFeaturefiles/regression.feature file
    then select any command - ex- @deleteBooking
	Open project directory in CMD and give command- mvn test -Dcucumber.options="--tags @deleteBooking"
	
 Inside IDE execution 
 1. Right click on project and run as >> mvn test
 2. open src/test/java/com.test.runner/TestRunner.java - check for the tags present 
    to run entire feature file (all test cases) add '@regression' 
	to run any one feature file add any one tag present in src/test/resource/BookingFeaturefiles/regression.feature file
	Then right click on the TestRunner.java file and run as TestNGtest 
	
Repoting 
1. I have provided one after test solution - where no need to ope any reports to validate, it will automatically open in chrome browser (cucmber html report)
2. user can find the same report after execution refresh the project and then report/cucumber-html-report/overview-features.html
 here user can click on the API documentation for restful-booker link under Feature section 
