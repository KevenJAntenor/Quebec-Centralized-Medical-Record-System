CREATE TABLE coordinate (
    id BIGINT PRIMARY KEY,
    address TEXT,
    personal_phone_number TEXT,
    work_phone_number TEXT,
    email TEXT
);

CREATE TABLE medical_file (
    id BIGINT PRIMARY KEY,
    insurance_number TEXT,
    patient_id BIGINT 
);

CREATE TABLE medical_history (
    id BIGINT PRIMARY KEY,
    diagnostic TEXT,
    treatment TEXT,
    doctor TEXT,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    medical_file_id BIGINT
);

CREATE TABLE medical_visit (
    id BIGINT PRIMARY KEY,
    establishment TEXT,
    doctor TEXT,
    date_of_visit TIMESTAMP,
    diagnostic TEXT,
    treatment TEXT,
    summary TEXT,
    notes TEXT,
    medical_file_id BIGINT
);

CREATE TABLE patient (
    id BIGINT PRIMARY KEY,
    last_name TEXT,
    first_name TEXT,
    date_of_birth TEXT,
    gender TEXT,
    known_parent1 TEXT,
    known_parent2 TEXT,
    city_of_birth TEXT,
    coordinate_id BIGINT
);

CREATE TABLE app_user(
    id BIGINT PRIMARY KEY,
    username TEXT,
    password TEXT,
    role TEXT
);



INSERT INTO coordinate (id, address, personal_phone_number, work_phone_number, email) VALUES
(1, '2020 Rue Saint-Urbain, Montréal, QC H2X 4E1, Canada', '+1(514)123-1234', '+1(438)123-4567', 'john-doe@uqam.com'),
(2, '1234 Rue Saint-Denis, Montréal, QC H2X 3J6, Canada', '+1(514)234-5678', '+1(438)234-5678', 'jane-smith@uqam.com'),
(3, '1987 Rue Sherbrooke Ouest, Montréal, QC H3H 1E5, Canada', '+1(514)345-6789', '+1(438)345-6789', 'james-doe@mcgill.ca'),
(4, '350 Rue Saint Paul E, Montréal, QC H2Y 1H2, Canada', '+1(514)456-7890', '+1(438)456-7890', 'jack-smith@umontreal.ca'),
(5, '475 Boulevard De Maisonneuve E, Montréal, QC H2L 5C4, Canada', '+1(514)567-8901', '+1(438)567-8901', 'jessica-doe@concordia.ca'),
(6, '3800 Rue Sherbrooke E, Montréal, QC H1X 2A2, Canada', '+1(514)678-9012', '+1(438)678-9012', 'julia-smith@etsmtl.ca'),
(7, '1400 Boulevard de la Côte-Vertu, Saint-Laurent, QC H4L 4X3, Canada', '+1(514)789-0123', '+1(438)789-0123', 'jeremy-doe@vaniercollege.qc.ca'),
(8, '21 275 Lakeshore Road, Sainte-Anne-de-Bellevue, QC H9X 3L9, Canada', '+1(514)890-1234', '+1(438)890-1234', 'jennifer-smith@johnabbott.qc.ca'),
(9, '7275 Rue Sherbrooke E, Montréal, QC H1N 1E9, Canada', '+1(514)901-2345', '+1(438)901-2345', 'jade-doe@cegepmontpetit.ca'),
(10, '6220 Rue Sherbrooke Ouest, Montréal, QC H4B 1N1, Canada', '+1(514)012-3456', '+1(438)012-3456', 'jonathan-smith@mcgill.ca'),
(11, '2000 Boulevard du Souvenir, Laval, QC H7N 5Y3, Canada', '+1(514)123-4567', '+1(438)123-4567', 'juliet-doe@uqam.ca'),
(12, '1345 Avenue du Mont-Royal E, Montréal, QC H2J 1Y8, Canada', '+1(514)234-5678', '+1(438)234-5678', 'jordan-smith@hec.ca'),
(13, '3400 Boulevard Edouard-Montpetit, Montréal, QC H3T 1J4, Canada', '+1(514)345-6789', '+1(438)345-6789', 'jasmine-doe@umontreal.ca');

INSERT INTO patient (id, last_name, first_name, date_of_birth, gender, known_parent1, known_parent2, city_of_birth, coordinate_id) VALUES
(1, 'Doe', 'John', '1990-01-01', 'Male', 'Jane Doe', 'Joe Doe', 'New York', 1),
(2, 'Smith', 'Jane', '1992-02-02', 'Female', 'John Smith', 'Jill Smith', 'Los Angeles', 2),
(3, 'Doe', 'James', '1993-03-03', 'Male', 'Jenny Doe', 'Jack Doe', 'Chicago', 3),
(4, 'Smith', 'Jack', '1988-04-04', 'Male', 'Julia Smith', 'Jacob Smith', 'Houston', 4),
(5, 'Doe', 'Jessica', '1995-05-05', 'Female', 'Joanne Doe', 'Jeremy Doe', 'Philadelphia', 5),
(6, 'Smith', 'Julia', '1991-06-06', 'Female', 'Jasmine Smith', 'Jeffrey Smith', 'Phoenix', 6),
(7, 'Doe', 'Jeremy', '1989-07-07', 'Male', 'Jade Doe', 'Jason Doe', 'San Antonio', 7),
(8, 'Smith', 'Jennifer', '1990-08-08', 'Female', 'Janet Smith', 'Jerry Smith', 'San Diego', 8),
(9, 'Doe', 'Jade', '1994-09-09', 'Female', 'Jill Doe', 'Joseph Doe', 'Dallas', 9),
(10, 'Smith', 'Jonathan', '1992-10-10', 'Male', 'Joanna Smith', 'John Smith Sr.', 'San Jose', 10),
(11, 'Doe', 'Juliet', '1987-11-11', 'Female', 'June Doe', 'Jordan Doe', 'Austin', 11),
(12, 'Smith', 'Jordan', '1996-12-12', 'Male', 'Jeanette Smith', 'Joseph Smith', 'Jacksonville', 12),
(13, 'Doe', 'Jasmine', '1997-01-13', 'Female', 'Jessica Doe', 'James Doe', 'San Francisco', 13);

INSERT INTO medical_file (id, insurance_number, patient_id) VALUES
(1, 'ABCD12345678', 1),
(2, 'BCDE12345678', 2),
(3, 'CDEF12345678', 3),
(4, 'DEFG12345678', 4),
(5, 'EFGH12345678', 5),
(6, 'FGHI12345678', 6),
(7, 'GHIJ12345678', 7),
(8, 'HIJK12345678', 8),
(9, 'IJKL12345678', 9),
(10, 'JKLM12345678', 10),
(11, 'KLMN12345678', 11),
(12, 'LMNO12345678', 12),
(13, 'MNOP12345678', 13);

INSERT INTO medical_visit (id, establishment, doctor, date_of_visit, diagnostic, treatment, summary, notes, medical_file_id) VALUES
(1, 'Hôpital Général de Montréal', 'Dr. Marie Dubois', '2022-01-01', 'Diagnostique de grippe', 'Traitement symptomatique', 'Suivi de symptômes de grippe', 'Le patient récupère bien', 1),
(2, 'Clinique Médicale de Québec', 'Dr. Jean Renaud', '2022-02-01', 'Examen annuel', 'Vaccination recommandée', 'Résumé de lexamen annuel', 'Aucune complication', 2),
(3, 'CHUM Centre Ville', 'Dr. Sophie Girard', '2022-03-01', 'Diagnostic de lhypertension', 'Prescription dantihypertenseurs', 'Suivi régulier de la pression artérielle', 'Le patient doit surveiller son alimentation', 3),
(4, 'Hôpital Jean-Talon', 'Dr. Éric Lavoie', '2022-04-01', 'Douleur abdominale', 'Échographie abdominale', 'Résumé des symptômes et des traitements', 'Suivi en gastro-entérologie recommandé', 4),
(5, 'Institut de Cardiologie de Montréal', 'Dr. Chantal Bergeron', '2022-05-01', 'Examen cardiaque', 'Test deffort', 'Bon fonctionnement cardiaque', 'Continuer les exercices réguliers', 5),
(6, 'Centre Hospitalier de l\Université de Montréal', 'Dr. Louis Tremblay', '2022-06-01', 'Allergies saisonnières', 'Antihistaminiques', 'Traitement des allergies', 'Les symptômes diminuent', 6),
(7, 'Clinique Médicale Laval', 'Dr. Anne Lavigne', '2022-07-01', 'Diagnostic de l\asthme', 'Inhalateurs à corticoïdes', 'Gestion de l\asthme', 'Éviter les allergènes connus', 7),
(8, 'Hôpital Saint-Luc', 'Dr. François Martin', '2022-08-01', 'Fracture du poignet', 'Immobilisation et physiothérapie', 'Résumé du traitement', 'Récupération attendue sans complications', 8),
(9, 'Hôpital de Verdun', 'Dr. Isabelle Choquette', '2022-09-01', 'Diagnostic de la migraine', 'Traitement préventif', 'Gestion des crises de migraine', 'À revoir si l\augmentation de la fréquence', 9),
(10, 'Hôpital Maisonneuve-Rosemont', 'Dr. Rémi Beaulieu', '2022-10-01', 'Bilan de santé général', 'Aucun traitement nécessaire', 'Patient en bonne santé générale', 'Maintenir un mode de vie sain', 10),
(11, 'CHU Sainte-Justine', 'Dr. Lucie Bouchard', '2022-11-01', 'Évaluation pédiatrique', 'Vaccins mis à jour', 'Développement normal', 'Suivi annuel recommandé', 11),
(12, 'Hôpital de la Cité-de-la-Santé', 'Dr. Marc Antoine', '2022-12-01', 'Éruption cutanée', 'Crèmes topiques corticostéroïdes', 'Traitement de l\eczéma', 'Réexamen si pas damélioration', 12),
(13, 'Hôpital Général Juif', 'Dr. Christine Dufour', '2023-01-01', 'Consultation pour larthrite', 'Anti-inflammatoires non stéroïdiens', 'Suivi de larthrite', 'Adaptation du mode de vie et physiothérapie', 13),
(14, 'Polyclinique Concordia', 'Dr. Guillaume Tessier', '2023-02-01', 'Surveillance de la thyroïde', 'Tests réguliers de la fonction thyroïdienne', 'Fonction thyroïdienne stable', 'Continuer le lévothyroxine', 13),
(15, 'Clinique Médicale DIX30', 'Dr. Nathalie Gagnon', '2023-03-01', 'Examen dermatologique', 'Biopsie de peau', 'Suivi dun naevus suspect', 'Attendre les résultats de la biopsie', 12),
(16, 'Hôpital Général Lakeshore', 'Dr. Olivier Roy', '2023-04-01', 'Contrôle de lhypertension', 'Ajustement des médicaments antihypertenseurs', 'Stabilisation de la pression artérielle', 'Mesure fréquente à domicile', 11),
(17, 'Centre Médical Rive-Sud', 'Dr. Aline Fontaine', '2023-05-01', 'Examen préventif pour le cancer du sein', 'Mammographie', 'Pas de signe de cancer', 'Prochain examen dans deux ans', 10),
(18, 'Hôpital de Lachine', 'Dr. Philippe Lemieux', '2023-06-01', 'Traitement de lanxiété', 'Thérapie cognitivo-comportementale', 'Amélioration de la gestion du stress', 'Continuer les séances hebdomadaires', 9),
(19, 'Clinique de Neurologie de Montréal', 'Dr. Isabelle Rousseau', '2023-07-01', 'Évaluation pour des épisodes de vertige', 'IRM cérébrale', 'Exclusion de causes neurologiques graves', 'Surveillance et réévaluation si aggravation', 8),
(20, 'Hôpital Notre-Dame', 'Dr. René Lachance', '2023-08-01', 'Diagnostic de diabète type 2', 'Planification alimentaire et médicaments hypoglycémiants', 'Gestion du diabète', 'Contrôles glycémiques réguliers', 7),
(21, 'Hôpital Saint-Luc', 'Dr. Caroline Dumas', '2023-09-01', 'Examen de suivi pour larthrose', 'Ajustement des analgésiques', 'Gestion de la douleur', 'Évaluation périodique de la mobilité', 6),
(22, 'Clinique Médicale du Centre-Ville', 'Dr. François Bélanger', '2023-10-01', 'Consultation pour fatigue chronique', 'Tests de laboratoire étendus', 'Investigation des causes potentielles', 'Suivi des résultats et ajustements du traitement', 5),
(23, 'Hôpital Sacré-Cœur de Montréal', 'Dr. Émilie Lapointe', '2023-11-01', 'Suivi post-opératoire de la cataracte', "Évaluation de la vision", 'Récupération conforme aux attentes', 'Prochain contrôle dans 6 mois', 4),
(24, 'Hôpital de Verdun', 'Dr. Louis Bernard', '2023-12-01', 'Consultation pour des symptômes grippaux', 'Traitement symptomatique', 'Gestion à domicile', 'Repos et hydratation recommandés', 3),
(25, 'Polyclinique Médicale Pierre-Boucher', 'Dr. Serge Dupuis', '2024-01-01', 'Bilan de santé pour certification sportive', 'Examen physique complet', 'Aptitude physique confirmée', 'Prêt pour la compétition', 2),
(26, 'CHU Sainte-Justine', 'Dr. Gabrielle Martin', '2024-02-01', 'Suivi de croissance pour enfant', 'Mesures de croissance et développement', 'Développement normal pour lâge', 'Prochain suivi dans 2 mois', 1);

INSERT INTO medical_history (id, diagnostic, treatment, doctor, start_date, end_date, medical_file_id) VALUES
(1, "Diagnostique de grippe", "Traitement symptomatique", "Dr. Marie Dubois", "2022-01-01", "2022-01-31", 1),
(2, "Examen annuel", "Vaccination recommandée", "Dr. Jean Renaud", "2022-02-01", "2022-02-28", 2),
(3, "Diagnostic de l hypertension", "Prescription d antihypertenseurs", "Dr. Sophie Girard", "2022-03-01", "2022-03-31", 3),
(4, "Douleur abdominale", "Échographie abdominale", "Dr. Éric Lavoie", "2022-04-01", "2022-04-30", 4),
(5, "Examen cardiaque", "Test d effort", "Dr. Chantal Bergeron", "2022-05-01", "2022-05-31", 5),
(6, "Allergies saisonnières", "Antihistaminiques", "Dr. Louis Tremblay", "2022-06-01", "2022-06-30", 6),
(7, "Diagnostic de l asthme", "Inhalateurs à corticoïdes", "Dr. Anne Lavigne", "2022-07-01", "2022-07-31", 7),
(8, "Fracture du poignet", "Immobilisation et physiothérapie", "Dr. François Martin", "2022-08-01", "2022-08-31", 8),
(9, "Diagnostic de la migraine", "Traitement préventif", "Dr. Isabelle Choquette", "2022-09-01", "2022-09-30", 9),
(10, "Bilan de santé général", "Aucun traitement nécessaire", "Dr. Rémi Beaulieu", "2022-10-01", "2022-10-31", 10),
(11, "Évaluation pédiatrique", "Vaccins mis à jour", "Dr. Lucie Bouchard", "2022-11-01", "2022-11-30", 11),
(12, "Éruption cutanée", "Crèmes topiques corticostéroïdes", "Dr. Marc Antoine", "2022-12-01", "2022-12-31", 12),
(13, "Consultation pour l arthrite", "Anti-inflammatoires non stéroïdiens", "Dr. Christine Dufour", "2023-01-01", "2023-01-31", 13),
(14, "Surveillance de la thyroïde", "Tests réguliers de la fonction thyroïdienne", "Dr. Guillaume Tessier", "2023-02-01", "2023-02-28", 13),
(15, "Examen dermatologique", "Biopsie de peau", "Dr. Nathalie Gagnon", "2023-03-01", "2023-03-31", 12),
(16, "Contrôle de l hypertension", "Ajustement des médicaments antihypertenseurs", "Dr. Olivier Roy", "2023-04-01", "2023-04-30", 11),
(17, "Examen préventif pour le cancer du sein", "Mammographie", "Dr. Aline Fontaine", "2023-05-01", "2023-05-31", 10),
(18, "Traitement de l anxiété", "Thérapie cognitivo-comportementale", "Dr. Philippe Lemieux", "2023-06-01", "2023-06-30", 9),
(19, "Évaluation pour des épisodes de vertige", "IRM cérébrale", "Dr. Isabelle Rousseau", "2023-07-01", "2023-07-31", 8),
(20, "Diagnostic de diabète type 2", "Planification alimentaire et médicaments hypoglycémiants", "Dr. René Lachance", "2023-08-01", "2023-08-31", 7),
(21, "Examen de suivi pour l arthrose", "Ajustement des analgésiques", "Dr. Caroline Dumas", "2023-09-01", "2023-09-30", 6),
(22, "Consultation pour fatigue chronique", "Tests de laboratoire étendus", "Dr. François Bélanger", "2023-10-01", "2023-10-31", 5),
(23, "Suivi post-opératoire de la cataracte", "Évaluation de la vision", "Dr. Émilie Lapointe", "2023-11-01", "2023-11-30", 4),
(24, "Consultation pour des symptômes grippaux", "Traitement symptomatique", "Dr. Louis Bernard", "2023-12-01", "2023-12-31", 3),
(25, "Bilan de santé pour certification sportive", "Examen physique complet", "Dr. Serge Dupuis", "2024-01-01", "2024-01-31", 2),
(26, "Suivi de croissance pour enfant", "Mesures de croissance et développement", "Dr. Gabrielle Martin", "2024-02-01", "2024-02-28", 1);

INSERT INTO app_user (id, username, password, role) VALUES
(1, "user", "$2a$10$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9q0e4bRadue", "USER"),
(2, "admin", "$2a$10$8cjz47bjbR4Mn8GMg9IZx.vyjhLXR/SKKMSZ9.mP9vpMu0ssKi8GW", "ADMIN");

