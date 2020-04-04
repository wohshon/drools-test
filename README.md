###	Sample drools project to test out agenda-flow, salience and using Generics


	mvn clean package exec:java -Dmaven.test.skip=true

Client (App.java) is setup to run rules in AccountRules1.drl followed by AccountRules2.drl using agenda-group

Order within drl file is managed via saliance value

Data Object uses Generics and the class type; child variables are retrieved in the drl files 

Output :

```

***Test with Ordinary account, accountrules2 should be invoked first, followed by salience****
Agenda-group accountrules2 invoked, saliance 100
Account status: ordinary-3
Agenda-group accountrules1 invoked, saliance 50
Account status: ordinary-2
Agenda-group accountrules1 invoked, saliance 45 
Account status: specialordinary-1
Agenda-group accountrules1 invoked, saliance 20
Account status: ordinary-1
****Test with special account, accountrules1 should be invoked first, followed by salience****
Agenda-group accountrules1 invoked, saliance 45 
Account status: specialordinary-1
Agenda-group accountrules1 invoked, saliance 30
Account status: special-3
Agenda-group accountrules2 invoked, saliance 10
Account status: special-1
Agenda-group accountrules2 invoked, saliance -5
Account status: special-2

```
