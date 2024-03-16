#!/bin/bash

# Function to generate a random string
generate_random_string() {
  LC_ALL=C tr -dc 'a-zA-Z' < /dev/urandom | fold -w 4 | head -n 1
}

# Function to generate a random number
generate_random_number() {
  shuf -i 10000000-99999999 -n 1
}

#POST patients
patient=$(curl -s -X POST -H "Content-Type: application/json" -d '{"lastName": "Doe","firstName": "John","dateOfBirth": "1980-01-01","gender": "Male","knownParent1": "Jane Doe","knownParent2": "John Doe Sr.","cityOfBirth": "New York"}' http://localhost:8080/api/patients)

# Extract patient ID
patient_id=$(echo $patient | jq -r '._links.self.href | split("/") | last')

# POST medical files
insuranceNumber=$(generate_random_string)$(generate_random_number)
medicalFile1=$(curl -s -X POST -H "Content-Type: application/json" -d '{"insuranceNumber": "'$insuranceNumber'", "patient": "http://localhost:8080/api/patients/'$patient_id'"}' http://localhost:8080/api/medicalFiles)

# Extract medical file IDs
medicalFile1_id=$(echo $medicalFile1 | jq -r '._links.self.href | split("/") | last')

# GET and verify medical files
medicalFile1_get=$(curl -s -X GET http://localhost:8080/api/medicalFiles/$medicalFile1_id)

if [ "$medicalFile1" == "$medicalFile1_get" ]; then
  echo "Verification successful: The posted and retrieved medical files are equal."
else
  echo "Verification failed: The posted and retrieved medical files are not equal."
fi

#check if posted patient is equal to the retrieved patient
patient_get=$(curl -s -X GET http://localhost:8080/api/patients/$patient_id)

if [ "$patient" == "$patient_get" ]; then
  echo "Verification successful: The posted and retrieved patients are equal."
else
  echo "Verification failed: The posted and retrieved patients are not equal."
fi

#check if posted patient is equal to the linked patient in the medical file
patient_linked=$(echo $medicalFile1_get | jq -r '._links.patient.href')
get_patient=$(curl -s -X GET $patient_linked http://localhost:8080/api/patients)

if [ "$get_patient" == "$patient" ]; then
  echo "Verification successful: The posted patient is equal to the linked patient in the medical file."
else
  echo "Verification failed: The posted patient is not equal to the linked patient in the medical file."
  echo "Posted patient: $patient"
    echo "Linked patient: $patient_linked"
    
fi