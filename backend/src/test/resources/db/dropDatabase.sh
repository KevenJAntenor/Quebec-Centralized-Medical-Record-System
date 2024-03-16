#!/bin/bash

# Connect to the SQLite database file
sqlite3 medical_file.db <<EOF

DROP TABLE IF EXISTS notes;
DROP TABLE IF EXISTS doctor;
DROP TABLE IF EXISTS medical_visit;
DROP TABLE IF EXISTS medical_history;
DROP TABLE IF EXISTS coordinate;
DROP TABLE IF EXISTS patient;
DROP TABLE IF EXISTS medical_file;

EOF