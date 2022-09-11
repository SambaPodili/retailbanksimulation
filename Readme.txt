This application is to simulate interaction with a retail bank. The test cases are written and it covers ~97%


Step 1: set the java home and path in command prompt

Step 2: Build the application using maven. Once issued below command, it will run the testcase and generate jar file.
		mvn clean install

Step 2: Execute jar file 
	java -jar ocbc-assignment-0.0.1-SNAPSHOT.jar

Step 3: Enter the commands as mentioned below . 

> login Alice
Hello, Alice!
Your balance is 0.

> topup 100
Your balance is 100.

> login Bob
Hello, Bob!
Your balance is 0.

> topup 80
Your balance is 80.

> pay Alice 50
Transferred 50 to Alice.
Your balance is 30.

> pay Alice 100
Transferred 30 to Alice.
Your balance is 0.
Owing 70 to Alice.

> topup 30
Transferred 30 to Alice.
Your balance is 0.
Owing 40 to Alice.

> login Alice
Hello, Alice!
Owing 40 from Bob.
Your balance is 210.

> pay Bob 30
Owing 10 from Bob.
Your balance is 210.

> login Bob
Hello, Bob!
Your balance is 0.
Owing 10 to Alice.

> topup 100
Transferred 10 to Alice.
Your balance is 90.
