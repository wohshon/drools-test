###	Sample drools project to test out agenda-flow, salience and using Generics


	mvn clean package exec:java -Dmaven.test.skip=true

Client (App.java) is setup to run rules in AccountRules1.drl followed by AccountRules2.drl using agenda-group

Order within drl file is managed via saliance value

Data Object uses Generics and the class type; child variables are retrieved in the drl files 
