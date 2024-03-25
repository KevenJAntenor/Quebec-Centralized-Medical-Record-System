#!/bin/bash

username="user"
password="ce2d6e82-1a67-4bbe-94ad-3b331fc81ae8"

auth=$(echo -n "$username:$password" | base64)

# Function to generate a random string
generate_random_string() {
  LC_ALL=C tr -dc 'a-zA-Z' < /dev/urandom | fold -w 4 | head -n 1
}

# Function to generate a random number
generate_random_number() {
  shuf -i 10000000-99999999 -n 1
}

#POST patients
patient=$(curl -s -X POST -H "Authorization: Basic $auth" -H "Content-Type: application/json" -d '{"lastName": "Doe","firstName": "John","dateOfBirth": "1980-01-01","gender": "Male","knownParent1": "Jane Doe","knownParent2": "John Doe Sr.","cityOfBirth": "New York"}' http://localhost:8080/api/patients)

# Extract patient ID
patient_id=$(echo $patient | jq -r '._links.self.href | split("/") | last')

# POST medical files
insuranceNumber=$(generate_random_string)$(generate_random_number)
medicalFile1=$(curl -s -X POST -H "Authorization: Basic $auth" -H "Content-Type: application/json" -d '{"insuranceNumber": "'$insuranceNumber'", "patient": "http://localhost:8080/api/patients/'$patient_id'"}' http://localhost:8080/api/medicalFiles)

# Extract medical file IDs
medicalFile1_id=$(echo $medicalFile1 | jq -r '._links.self.href | split("/") | last')

# GET and verify medical files
medicalFile1_get=$(curl -s -X GET -H "Authorization: Basic $auth" http://localhost:8080/api/medicalFiles/$medicalFile1_id)

if [ "$medicalFile1" == "$medicalFile1_get" ]; then
  echo "Verification successful: The posted and retrieved medical files are equal."
  echo "Posted medical file: $medicalFile1"
    echo "Retrieved medical file: $medicalFile1_get"

else
  echo "Verification failed: The posted and retrieved medical files are not equal."
  echo "Posted medical file: $medicalFile1"
    echo "Retrieved medical file: $medicalFile1_get"
fi

#check if posted patient is equal to the retrieved patient
patient_get=$(curl -s -X GET -H "Authorization: Basic $auth" http://localhost:8080/api/patients/$patient_id)

if [ "$patient" == "$patient_get" ]; then
  echo "Verification successful: The posted and retrieved patients are equal."
  echo "Posted patient: $patient"
    echo "Retrieved patient: $patient_get"
else
  echo "Verification failed: The posted and retrieved patients are not equal."
    echo "Posted patient: $patient"
        echo "Retrieved patient: $patient_get"
fi

#check if posted patient is equal to the linked patient in the medical file
patient_linked=$(echo $medicalFile1_get | jq -r '._links.patient.href')
get_patient=$(curl -s -X GET -H "Authorization: Basic $auth" "$patient_linked")

if [ "$get_patient" == "$patient" ]; then
  echo "Verification successful: The posted patient is equal to the linked patient in the medical file."
    echo "Posted patient: $patient"
    echo "Linked patient: $patient_linked"
else
  echo "Verification failed: The posted patient is not equal to the linked patient in the medical file."
  echo "Posted patient: $patient"
    echo "Linked patient: $patient_linked"
    
fi