#!/bin/bash

# Query the database
echo "Querying the database..."

sqlite3 medical_file.db <<EOF

.headers on
.mode column

-- Query for the patient, coordinate, and medical file
SELECT patient.*, coordinate.*, medical_file.*
FROM patient
INNER JOIN coordinate ON patient.coordinate_id = coordinate.id
INNER JOIN medical_file ON patient.id = medical_file.patient_id
WHERE medical_file.id = 1; -- Replace 1 with the ID of the medical file you want to query

-- Query for the medical history
SELECT medical_history.*
FROM medical_history
INNER JOIN medical_file ON medical_history.medical_file_id = medical_file.id
WHERE medical_file.id = 1; -- Replace 1 with the ID of the medical file you want to query

-- Query for the medical visits
SELECT medical_visit.*
FROM medical_visit
INNER JOIN medical_file ON medical_visit.medical_file_id = medical_file.id
WHERE medical_file.id = 1; -- Replace 1 with the ID of the medical file you want to query

EOF

echo "Query completed."