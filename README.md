# Generate Number - 
#### Pull the code from the github repo -
git clone https://github.com/niteshkumarn55/generatenumbers.git

#### Maven Clean and install

mvn clean install

#### Execute the jar

java -jar ./target/generatenumber-1.0.jar

#### api's

- http://localhost:8080/api/generate  -> POST
    {
        "goal":10,
        "step": 2
    }

-   http://localhost:8080/api/tasks/635d0fe8-06b3-4706-aad3-aff1358186d7/status -> GET

- http://localhost:8080/api/tasks/635d0fe8-06b3-4706-aad3-aff1358186d7?action=get_numlist -GET


