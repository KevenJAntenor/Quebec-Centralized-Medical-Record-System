#!/bin/bash

# Populate the tables
echo "Populating the database..."

sqlite3 medical_file.db <<EOF

-- Insert a single coordinate
INSERT INTO coordinate (street, city, postal_code, Country, email, work_phone, personal_phone)
VALUES 
('123 Main St', 'New York', '10001', 'USA', 'johnsmith@example.com', '123-456-7890', '098-765-4321');
INSERT INTO coordinate (street, city, postal_code, Country, email, work_phone, personal_phone)
VALUES
('456 Elm St', 'Los Angeles', '90001', 'USA', 'maria@gmail,com', '123-456-7890', '098-765-4321');

-- Insert a single patient
INSERT INTO patient (last_name, first_name, date_of_birth, gender, known_parent1, known_parent2, city_of_birth, coordinate_id)
VALUES 
('Smith', 'John', '1980-01-01', 'Male', 'Smith Sr.', 'Smith Jr.', 'New York', (SELECT id FROM coordinate WHERE street = '123 Main St'));
INSERT INTO patient (last_name, first_name, date_of_birth, gender, known_parent1, known_parent2, city_of_birth, coordinate_id)
VALUES
('Garcia', 'Maria', '1985-01-11', 'Female', 'Garcia Sr.', 'Garcia Jr.', 'Los Angeles', (SELECT id FROM coordinate WHERE street = '456 Elm St'));

-- Insert a single medical file
INSERT INTO medical_file (health_insurance_number, patient_id)
VALUES ('AHIN12345678', (SELECT id FROM patient WHERE last_name = 'Smith' AND first_name = 'John'));

-- Insert a single doctor
INSERT INTO doctor (last_name, first_name, specialty)
VALUES ('Doe', 'Jane', 'Endocrinology');

-- Insert a single medical history
INSERT INTO medical_history (diagnosis, treatment, start_date, end_date, doctor_id, medical_file_id)
VALUES ('Diabetes', 'Insulin therapy', '2022-01-01', '2022-12-31', (SELECT id FROM doctor WHERE last_name = 'Doe' AND first_name = 'Jane'), (SELECT id FROM medical_file WHERE health_insurance_number = 'AHIN12345678'));

-- Insert a single medical visit
INSERT INTO medical_visit (establishment, visit_date, diagnosis, treatment, summary, doctor_id, medical_file_id)
VALUES ('NYU Langone Health', '2022-01-01', 'Diabetes', 'Insulin therapy', 'Annual checkup', (SELECT id FROM doctor WHERE last_name = 'Doe' AND first_name = 'Jane'), (SELECT id FROM medical_file WHERE health_insurance_number = 'AHIN12345678'));


-- Insert a two notes
INSERT INTO notes (note, medical_visit_id)
VALUES ('Patient is doing well', (SELECT id FROM medical_visit WHERE establishment = 'NYU Langone Health' AND visit_date = '2022-01-01'));
INSERT INTO notes (note, medical_visit_id)
VALUES ('Patient is doing worse', (SELECT id FROM medical_visit WHERE establishment = 'NYU Langone Health' AND visit_date = '2022-01-01'));

EOF

echo "Database populated."