# API-RestAssured

Fake REST APIs created using JSON Server is essential for running this automated test

- Download node js, if not installed and verify the version
$ node -v
v16.13.1

$ npm -v
9.8.1

- Create a json file as db.json and open cmd in that path
{
  "pizza": [
       "cost": 19,
      "type": "Aragosta",
      "takeaway": {
        "online": "no",
        "glovo": "no"
      }
}

{
 "employees": [
	"id": 2,
	"first_name": "Gab",
	"last_name": "Marine",
	"email": "gm@jfk.eu.it",
	"gender": "Male",
	"skills": [
	{
		"name": "Testing",
		"proficiency": "Medium"
	}
 
 }
  
- Run the below command from command prompt
json-server --watch db.json

By Default it will take localhost:3000,
Now we can open URL http://localhost:3000/pizza http://localhost:3000/Employees or  in the browser 


Allure must be installed 

LAUNCH COMMANDS from LINE:
mvn test -Dsuitename="./suites/allurereportapitest.xml"
allure serve


