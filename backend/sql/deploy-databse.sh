#!/bin/bash

# Create the SQLite database file in the root directory
echo "Creating the database file in the root directory..."
touch ../medical_file.db

# Run the SQL script to create the tables
echo "Creating the tables in the root directory database..."
sqlite3 ../medical_file.db < setupDatabase.sql

# Create the SQLite database file in the src/test/resources/adapters/db/ directory
echo "Creating the database file in the src/test/resources/adapters/db/ directory..."
mkdir -p ../src/test/resources/adapters/db/
touch ../src/test/resources/adapters/db/medical_file.db

# Run the SQL script to create the tables
echo "Creating the tables in the src/test/resources/adapters/db/ database..."
sqlite3 ../src/test/resources/adapters/db/medical_file.db < setupDatabase.sql

echo "Tables created in production database and test database"