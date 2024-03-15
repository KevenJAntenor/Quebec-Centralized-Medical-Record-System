#!/bin/bash

# Cleanup: Drop the tables in reverse order of creation to avoid foreign key constraint issues
echo "Cleaning up the database..."

sqlite3 medical_file.db <<EOF

DROP TABLE IF EXISTS notes;
DROP TABLE IF EXISTS doctor;
DROP TABLE IF EXISTS medical_visit;
DROP TABLE IF EXISTS medical_history;
DROP TABLE IF EXISTS coordinate;
DROP TABLE IF EXISTS patient;
DROP TABLE IF EXISTS medical_file;

EOF

echo "Cleanup completed."

# Create the SQLite database file

echo "Creating the database file..."

touch medical_file.db

# Create the table structure
sqlite3 medical_file.db <<EOF

CREATE TABLE medical_file (
    id INTEGER PRIMARY KEY,
    insurance_number TEXT,
    patient_id INTEGER,
    FOREIGN KEY (patient_id) REFERENCES patient(id)
);

# For faster search by health insurance number
CREATE INDEX idx_insurance_number ON medical_file(insurance_number);

CREATE TABLE patient (
    id INTEGER PRIMARY KEY,
    last_name TEXT,
    first_name TEXT,
    date_of_birth TEXT,
    gender TEXT,
    known_parent1 TEXT,
    known_parent2 TEXT,
    city_of_birth TEXT,
    coordinate_id INTEGER,
    FOREIGN KEY (coordinate_id) REFERENCES coordinate(id)

);

CREATE TABLE coordinate (
    id INTEGER PRIMARY KEY,
    street TEXT,
    city TEXT,
    postal_code TEXT,
    Country TEXT,
    email TEXT,
    work_phone TEXT,
    personal_phone TEXT
);

CREATE TABLE medical_history (
    id INTEGER PRIMARY KEY,
    diagnosis TEXT,
    treatment TEXT,
    start_date TEXT,
    end_date TEXT,
    doctor_id INTEGER,
    medical_file_id INTEGER,
    FOREIGN KEY (doctor_id) REFERENCES doctor(id),
    FOREIGN KEY (medical_file_id) REFERENCES medical_file(id)
);

CREATE TABLE medical_visit (
    id INTEGER PRIMARY KEY,
    establishment TEXT,
    visit_date TEXT,
    diagnosis TEXT,
    treatment TEXT,
    summary TEXT,
    doctor_id INTEGER,
    medical_file_id INTEGER,
    FOREIGN KEY (doctor_id) REFERENCES doctor(id),
    FOREIGN KEY (medical_file_id) REFERENCES medical_file(id)
);

CREATE TABLE doctor (
    id INTEGER PRIMARY KEY,
    last_name TEXT,
    first_name TEXT,
    specialty TEXT
);

CREATE TABLE notes (
    id INTEGER PRIMARY KEY,
    note TEXT,
    medical_visit_id INTEGER,
    FOREIGN KEY (medical_visit_id) REFERENCES medical_visit(id)
);
EOF

echo "Database file created."