#!/bin/bash

# Function to generate a random string
generate_random_string() {
  LC_ALL=C tr -dc 'a-zA-Z' < /dev/urandom | fold -w 4 | head -n 1
}

# Function to generate a random number
generate_random_number() {
  shuf -i 10000000-99999999 -n 1
}

# POST medical files
insuranceNumber=$(generate_random_string)$(generate_random_number)
medicalFile1=$(curl -s -X POST -H "Content-Type: application/json" -d '{"insuranceNumber": "'$insuranceNumber'", "patient": {"lastName": "Doe", "firstName": "John", "dateOfBirth": "1990-01-01", "gender": "Male", "knownParent1": "Jane Doe", "knownParent2": "Joe Doe", "cityOfBirth": "New York"}}' http://localhost:8080/api/medicalFiles)

# Extract medical file IDs
medicalFile1_id=$(echo $medicalFile1 | jq -r '._links.self.href | split("/") | last')

# GET and verify medical files
medicalFile1_get=$(curl -s -X GET http://localhost:8080/api/medicalFiles/$medicalFile1_id)

if [ "$medicalFile1" == "$medicalFile1_get" ]; then
  echo "Verification successful: The posted and retrieved medical files are equal."
else
  echo "Verification failed: The posted and retrieved medical files are not equal."
fi