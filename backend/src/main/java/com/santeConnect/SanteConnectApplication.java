package com.santeConnect;

import com.santeConnect.domain.entities.Coordinate;
import com.santeConnect.domain.entities.MedicalFile;
import com.santeConnect.domain.entities.MedicalVisit;
import com.santeConnect.domain.entities.MedicalHistory;
import com.santeConnect.domain.entities.Patient;
import com.santeConnect.domain.users.AppUser;
import com.santeConnect.repository.entities.*;
import com.santeConnect.repository.users.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
@EnableMethodSecurity
public class SanteConnectApplication implements CommandLineRunner {


    private final MedicalFileRepository medicalFileRepository;

    private final PatientRepository patientRepository;

    private final CoordinateRepository coordinateRepository;

    private final MedicalVisitRepository medicalVisitRepository;

    private final MedicalHistoryRepository medicalHistoryRepository;

    private final AppUserRepository appUserRepository;

    public SanteConnectApplication(MedicalFileRepository medicalFileRepository,
            PatientRepository patientRepository,
            CoordinateRepository coordinateRepository,
            MedicalVisitRepository medicalVisitRepository,
            AppUserRepository appUserRepository,
            MedicalHistoryRepository medicalHistoryRepository) {
        this.medicalFileRepository = medicalFileRepository;
        this.patientRepository = patientRepository;
        this.coordinateRepository = coordinateRepository;
        this.medicalVisitRepository = medicalVisitRepository;
        this.appUserRepository = appUserRepository;
        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SanteConnectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        MedicalVisit visit1 = new MedicalVisit("Hôpital Général de Montréal", "Dr. Marie Dubois",
                formatter.parse("2022-01-01"),
                "Diagnostique de grippe",
                "Traitement symptomatique", "Suivi de symptômes de grippe", "Le patient récupère bien");

        MedicalVisit visit2 = new MedicalVisit("Clinique Médicale de Québec", "Dr. Jean Renaud",
                formatter.parse("2022-02-01"),
                "Examen annuel",
                "Vaccination recommandée", "Résumé de l'examen annuel", "Aucune complication");

        MedicalVisit visit3 = new MedicalVisit("CHUM Centre Ville", "Dr. Sophie Girard", formatter.parse("2022-03-01"),
                "Diagnostic de l'hypertension",
                "Prescription d'antihypertenseurs", "Suivi régulier de la pression artérielle",
                "Le patient doit surveiller son alimentation");

        MedicalVisit visit4 = new MedicalVisit("Hôpital Jean-Talon", "Dr. Éric Lavoie", formatter.parse("2022-04-01"),
                "Douleur abdominale",
                "Échographie abdominale", "Résumé des symptômes et des traitements",
                "Suivi en gastro-entérologie recommandé");

        MedicalVisit visit5 = new MedicalVisit("Institut de Cardiologie de Montréal", "Dr. Chantal Bergeron",
                formatter.parse("2022-05-01"),
                "Examen cardiaque",
                "Test d'effort", "Bon fonctionnement cardiaque", "Continuer les exercices réguliers");

        MedicalVisit visit6 = new MedicalVisit("Centre Hospitalier de l'Université de Montréal", "Dr. Louis Tremblay",
                formatter.parse("2022-06-01"),
                "Allergies saisonnières",
                "Antihistaminiques", "Traitement des allergies", "Les symptômes diminuent");

        MedicalVisit visit7 = new MedicalVisit("Clinique Médicale Laval", "Dr. Anne Lavigne",
                formatter.parse("2022-07-01"),
                "Diagnostic de l'asthme",
                "Inhalateurs à corticoïdes", "Gestion de l'asthme", "Éviter les allergènes connus");

        MedicalVisit visit8 = new MedicalVisit("Hôpital Saint-Luc", "Dr. François Martin",
                formatter.parse("2022-08-01"),
                "Fracture du poignet",
                "Immobilisation et physiothérapie", "Résumé du traitement", "Récupération attendue sans complications");

        MedicalVisit visit9 = new MedicalVisit("Hôpital de Verdun", "Dr. Isabelle Choquette",
                formatter.parse("2022-09-01"),
                "Diagnostic de la migraine",
                "Traitement préventif", "Gestion des crises de migraine", "À revoir si l'augmentation de la fréquence");

        MedicalVisit visit10 = new MedicalVisit("Hôpital Maisonneuve-Rosemont", "Dr. Rémi Beaulieu",
                formatter.parse("2022-10-01"),
                "Bilan de santé général",
                "Aucun traitement nécessaire", "Patient en bonne santé générale", "Maintenir un mode de vie sain");

        MedicalVisit visit11 = new MedicalVisit("CHU Sainte-Justine", "Dr. Lucie Bouchard",
                formatter.parse("2022-11-01"),
                "Évaluation pédiatrique",
                "Vaccins mis à jour", "Développement normal", "Suivi annuel recommandé");

        MedicalVisit visit12 = new MedicalVisit("Hôpital de la Cité-de-la-Santé", "Dr. Marc Antoine",
                formatter.parse("2022-12-01"),
                "Éruption cutanée",
                "Crèmes topiques corticostéroïdes", "Traitement de l'eczéma", "Réexamen si pas d'amélioration");

        MedicalVisit visit13 = new MedicalVisit("Hôpital Général Juif", "Dr. Christine Dufour",
                formatter.parse("2023-01-01"),
                "Consultation pour l'arthrite",
                "Anti-inflammatoires non stéroïdiens", "Suivi de l'arthrite",
                "Adaptation du mode de vie et physiothérapie");
        MedicalVisit visit14 = new MedicalVisit("Polyclinique Concordia", "Dr. Guillaume Tessier",
                formatter.parse("2023-02-01"),
                "Surveillance de la thyroïde",
                "Tests réguliers de la fonction thyroïdienne", "Fonction thyroïdienne stable",
                "Continuer le lévothyroxine");

        MedicalVisit visit15 = new MedicalVisit("Clinique Médicale DIX30", "Dr. Nathalie Gagnon",
                formatter.parse("2023-03-01"),
                "Examen dermatologique",
                "Biopsie de peau", "Suivi d'un naevus suspect", "Attendre les résultats de la biopsie");

        MedicalVisit visit16 = new MedicalVisit("Hôpital Général Lakeshore", "Dr. Olivier Roy",
                formatter.parse("2023-04-01"),
                "Contrôle de l'hypertension",
                "Ajustement des médicaments antihypertenseurs", "Stabilisation de la pression artérielle",
                "Mesure fréquente à domicile");

        MedicalVisit visit17 = new MedicalVisit("Centre Médical Rive-Sud", "Dr. Aline Fontaine",
                formatter.parse("2023-05-01"),
                "Examen préventif pour le cancer du sein",
                "Mammographie", "Pas de signe de cancer", "Prochain examen dans deux ans");

        MedicalVisit visit18 = new MedicalVisit("Hôpital de Lachine", "Dr. Philippe Lemieux",
                formatter.parse("2023-06-01"),
                "Traitement de l'anxiété",
                "Thérapie cognitivo-comportementale", "Amélioration de la gestion du stress",
                "Continuer les séances hebdomadaires");

        MedicalVisit visit19 = new MedicalVisit("Clinique de Neurologie de Montréal", "Dr. Isabelle Rousseau",
                formatter.parse("2023-07-01"),
                "Évaluation pour des épisodes de vertige",
                "IRM cérébrale", "Exclusion de causes neurologiques graves",
                "Surveillance et réévaluation si aggravation");

        MedicalVisit visit20 = new MedicalVisit("Hôpital Notre-Dame", "Dr. René Lachance",
                formatter.parse("2023-08-01"),
                "Diagnostic de diabète type 2",
                "Planification alimentaire et médicaments hypoglycémiants", "Gestion du diabète",
                "Contrôles glycémiques réguliers");

        MedicalVisit visit21 = new MedicalVisit("Hôpital Saint-Luc", "Dr. Caroline Dumas",
                formatter.parse("2023-09-01"),
                "Examen de suivi pour l'arthrose",
                "Ajustement des analgésiques", "Gestion de la douleur", "Évaluation périodique de la mobilité");

        MedicalVisit visit22 = new MedicalVisit("Clinique Médicale du Centre-Ville", "Dr. François Bélanger",
                formatter.parse("2023-10-01"),
                "Consultation pour fatigue chronique",
                "Tests de laboratoire étendus", "Investigation des causes potentielles",
                "Suivi des résultats et ajustements du traitement");

        MedicalVisit visit23 = new MedicalVisit("Hôpital Sacré-Cœur de Montréal", "Dr. Émilie Lapointe",
                formatter.parse("2023-11-01"),
                "Suivi post-opératoire de la cataracte",
                "Évaluation de la vision", "Récupération conforme aux attentes", "Prochain contrôle dans 6 mois");

        MedicalVisit visit24 = new MedicalVisit("Hôpital de Verdun", "Dr. Louis Bernard", formatter.parse("2023-12-01"),
                "Consultation pour des symptômes grippaux",
                "Traitement symptomatique", "Gestion à domicile", "Repos et hydratation recommandés");

        MedicalVisit visit25 = new MedicalVisit("Polyclinique Médicale Pierre-Boucher", "Dr. Serge Dupuis",
                formatter.parse("2024-01-01"),
                "Bilan de santé pour certification sportive",
                "Examen physique complet", "Aptitude physique confirmée", "Prêt pour la compétition");

        MedicalVisit visit26 = new MedicalVisit("CHU Sainte-Justine", "Dr. Gabrielle Martin",
                formatter.parse("2024-02-01"),
                "Suivi de croissance pour enfant",
                "Mesures de croissance et développement", "Développement normal pour l'âge",
                "Prochain suivi dans 2 mois");

        Coordinate coordinate1 = new Coordinate();
        coordinate1.setAddress("2020 Rue Saint-Urbain, Montréal, QC H2X 4E1, Canada");
        coordinate1.setPersonalPhoneNumber("+1(514)123-1234");
        coordinate1.setWorkPhoneNumber("+1(438)123-4567");
        coordinate1.setEmail("john-doe@uqam.com");

        Coordinate coordinate2 = new Coordinate();
        coordinate2.setAddress("1234 Rue Saint-Denis, Montréal, QC H2X 3J6, Canada");
        coordinate2.setPersonalPhoneNumber("+1(514)234-5678");
        coordinate2.setWorkPhoneNumber("+1(438)234-5678");
        coordinate2.setEmail("jane-smith@uqam.com");

        Coordinate coordinate3 = new Coordinate();
        coordinate3.setAddress("1987 Rue Sherbrooke Ouest, Montréal, QC H3H 1E5, Canada");
        coordinate3.setPersonalPhoneNumber("+1(514)345-6789");
        coordinate3.setWorkPhoneNumber("+1(438)345-6789");
        coordinate3.setEmail("james-doe@mcgill.ca");

        Coordinate coordinate4 = new Coordinate();
        coordinate4.setAddress("350 Rue Saint Paul E, Montréal, QC H2Y 1H2, Canada");
        coordinate4.setPersonalPhoneNumber("+1(514)456-7890");
        coordinate4.setWorkPhoneNumber("+1(438)456-7890");
        coordinate4.setEmail("jack-smith@umontreal.ca");

        Coordinate coordinate5 = new Coordinate();
        coordinate5.setAddress("475 Boulevard De Maisonneuve E, Montréal, QC H2L 5C4, Canada");
        coordinate5.setPersonalPhoneNumber("+1(514)567-8901");
        coordinate5.setWorkPhoneNumber("+1(438)567-8901");
        coordinate5.setEmail("jessica-doe@concordia.ca");

        Coordinate coordinate6 = new Coordinate();
        coordinate6.setAddress("3800 Rue Sherbrooke E, Montréal, QC H1X 2A2, Canada");
        coordinate6.setPersonalPhoneNumber("+1(514)678-9012");
        coordinate6.setWorkPhoneNumber("+1(438)678-9012");
        coordinate6.setEmail("julia-smith@etsmtl.ca");

        Coordinate coordinate7 = new Coordinate();
        coordinate7.setAddress("1400 Boulevard de la Côte-Vertu, Saint-Laurent, QC H4L 4X3, Canada");
        coordinate7.setPersonalPhoneNumber("+1(514)789-0123");
        coordinate7.setWorkPhoneNumber("+1(438)789-0123");
        coordinate7.setEmail("jeremy-doe@vaniercollege.qc.ca");

        Coordinate coordinate8 = new Coordinate();
        coordinate8.setAddress("21 275 Lakeshore Road, Sainte-Anne-de-Bellevue, QC H9X 3L9, Canada");
        coordinate8.setPersonalPhoneNumber("+1(514)890-1234");
        coordinate8.setWorkPhoneNumber("+1(438)890-1234");
        coordinate8.setEmail("jennifer-smith@johnabbott.qc.ca");

        Coordinate coordinate9 = new Coordinate();
        coordinate9.setAddress("7275 Rue Sherbrooke E, Montréal, QC H1N 1E9, Canada");
        coordinate9.setPersonalPhoneNumber("+1(514)901-2345");
        coordinate9.setWorkPhoneNumber("+1(438)901-2345");
        coordinate9.setEmail("jade-doe@cegepmontpetit.ca");

        Coordinate coordinate10 = new Coordinate();
        coordinate10.setAddress("6220 Rue Sherbrooke Ouest, Montréal, QC H4B 1N1, Canada");
        coordinate10.setPersonalPhoneNumber("+1(514)012-3456");
        coordinate10.setWorkPhoneNumber("+1(438)012-3456");
        coordinate10.setEmail("jonathan-smith@mcgill.ca");

        Coordinate coordinate11 = new Coordinate();
        coordinate11.setAddress("2000 Boulevard du Souvenir, Laval, QC H7N 5Y3, Canada");
        coordinate11.setPersonalPhoneNumber("+1(514)123-4567");
        coordinate11.setWorkPhoneNumber("+1(438)123-4567");
        coordinate11.setEmail("juliet-doe@uqam.ca");

        Coordinate coordinate12 = new Coordinate();
        coordinate12.setAddress("1345 Avenue du Mont-Royal E, Montréal, QC H2J 1Y8, Canada");
        coordinate12.setPersonalPhoneNumber("+1(514)234-5678");
        coordinate12.setWorkPhoneNumber("+1(438)234-5678");
        coordinate12.setEmail("jordan-smith@hec.ca");

        Coordinate coordinate13 = new Coordinate();
        coordinate13.setAddress("3400 Boulevard Edouard-Montpetit, Montréal, QC H3T 1J4, Canada");
        coordinate13.setPersonalPhoneNumber("+1(514)345-6789");
        coordinate13.setWorkPhoneNumber("+1(438)345-6789");
        coordinate13.setEmail("jasmine-doe@umontreal.ca");

        coordinateRepository.saveAll(Arrays.asList(coordinate1, coordinate2));
        coordinateRepository.saveAll(Arrays.asList(coordinate3, coordinate4));
        coordinateRepository.saveAll(Arrays.asList(coordinate5, coordinate6));
        coordinateRepository.saveAll(Arrays.asList(coordinate7, coordinate8));
        coordinateRepository.saveAll(Arrays.asList(coordinate9, coordinate10));
        coordinateRepository.saveAll(Arrays.asList(coordinate11, coordinate12));
        coordinateRepository.save(coordinate13);

        Patient patient1 = new Patient();
        patient1.setLastName("Doe");
        patient1.setFirstName("John");
        patient1.setDateOfBirth("1990-01-01");
        patient1.setGender("Male");
        patient1.setKnownParent1("Jane Doe");
        patient1.setKnownParent2("Joe Doe");
        patient1.setCityOfBirth("New York");
        patient1.setCoordinate(coordinate1);

        Patient patient2 = new Patient();
        patient2.setLastName("Smith");
        patient2.setFirstName("Jane");
        patient2.setDateOfBirth("1992-02-02");
        patient2.setGender("Female");
        patient2.setKnownParent1("John Smith");
        patient2.setKnownParent2("Jill Smith");
        patient2.setCityOfBirth("Los Angeles");
        patient2.setCoordinate(coordinate2);

        Patient patient3 = new Patient();
        patient3.setLastName("Doe");
        patient3.setFirstName("James");
        patient3.setDateOfBirth("1993-03-03");
        patient3.setGender("Male");
        patient3.setKnownParent1("Jenny Doe");
        patient3.setKnownParent2("Jack Doe");
        patient3.setCityOfBirth("Chicago");
        patient3.setCoordinate(coordinate3);

        Patient patient4 = new Patient();
        patient4.setLastName("Smith");
        patient4.setFirstName("Jack");
        patient4.setDateOfBirth("1988-04-04");
        patient4.setGender("Male");
        patient4.setKnownParent1("Julia Smith");
        patient4.setKnownParent2("Jacob Smith");
        patient4.setCityOfBirth("Houston");
        patient4.setCoordinate(coordinate4);

        Patient patient5 = new Patient();
        patient5.setLastName("Doe");
        patient5.setFirstName("Jessica");
        patient5.setDateOfBirth("1995-05-05");
        patient5.setGender("Female");
        patient5.setKnownParent1("Joanne Doe");
        patient5.setKnownParent2("Jeremy Doe");
        patient5.setCityOfBirth("Philadelphia");
        patient5.setCoordinate(coordinate5);

        Patient patient6 = new Patient();
        patient6.setLastName("Smith");
        patient6.setFirstName("Julia");
        patient6.setDateOfBirth("1991-06-06");
        patient6.setGender("Female");
        patient6.setKnownParent1("Jasmine Smith");
        patient6.setKnownParent2("Jeffrey Smith");
        patient6.setCityOfBirth("Phoenix");
        patient6.setCoordinate(coordinate6);

        Patient patient7 = new Patient();
        patient7.setLastName("Doe");
        patient7.setFirstName("Jeremy");
        patient7.setDateOfBirth("1989-07-07");
        patient7.setGender("Male");
        patient7.setKnownParent1("Jade Doe");
        patient7.setKnownParent2("Jason Doe");
        patient7.setCityOfBirth("San Antonio");
        patient7.setCoordinate(coordinate7);

        Patient patient8 = new Patient();
        patient8.setLastName("Smith");
        patient8.setFirstName("Jennifer");
        patient8.setDateOfBirth("1990-08-08");
        patient8.setGender("Female");
        patient8.setKnownParent1("Janet Smith");
        patient8.setKnownParent2("Jerry Smith");
        patient8.setCityOfBirth("San Diego");
        patient8.setCoordinate(coordinate8);

        Patient patient9 = new Patient();
        patient9.setLastName("Doe");
        patient9.setFirstName("Jade");
        patient9.setDateOfBirth("1994-09-09");
        patient9.setGender("Female");
        patient9.setKnownParent1("Jill Doe");
        patient9.setKnownParent2("Joseph Doe");
        patient9.setCityOfBirth("Dallas");
        patient9.setCoordinate(coordinate9);

        Patient patient10 = new Patient();
        patient10.setLastName("Smith");
        patient10.setFirstName("Jonathan");
        patient10.setDateOfBirth("1992-10-10");
        patient10.setGender("Male");
        patient10.setKnownParent1("Joanna Smith");
        patient10.setKnownParent2("John Smith Sr.");
        patient10.setCityOfBirth("San Jose");
        patient10.setCoordinate(coordinate10);

        Patient patient11 = new Patient();
        patient11.setLastName("Doe");
        patient11.setFirstName("Juliet");
        patient11.setDateOfBirth("1987-11-11");
        patient11.setGender("Female");
        patient11.setKnownParent1("June Doe");
        patient11.setKnownParent2("Jordan Doe");
        patient11.setCityOfBirth("Austin");
        patient11.setCoordinate(coordinate11);

        Patient patient12 = new Patient();
        patient12.setLastName("Smith");
        patient12.setFirstName("Jordan");
        patient12.setDateOfBirth("1996-12-12");
        patient12.setGender("Male");
        patient12.setKnownParent1("Jeanette Smith");
        patient12.setKnownParent2("Joseph Smith");
        patient12.setCityOfBirth("Jacksonville");
        patient12.setCoordinate(coordinate12);

        Patient patient13 = new Patient();
        patient13.setLastName("Doe");
        patient13.setFirstName("Jasmine");
        patient13.setDateOfBirth("1997-01-13");
        patient13.setGender("Female");
        patient13.setKnownParent1("Jessica Doe");
        patient13.setKnownParent2("James Doe");
        patient13.setCityOfBirth("San Francisco");
        patient13.setCoordinate(coordinate13);

        patientRepository.saveAll(Arrays.asList(patient1, patient2));
        patientRepository.saveAll(Arrays.asList(patient3, patient4));
        patientRepository.saveAll(Arrays.asList(patient5, patient6));
        patientRepository.saveAll(Arrays.asList(patient7, patient8));
        patientRepository.saveAll(Arrays.asList(patient9, patient10));
        patientRepository.saveAll(Arrays.asList(patient11, patient12));
        patientRepository.save(patient13);

        MedicalHistory history1 = new MedicalHistory("Diagnostique de grippe", "Traitement symptomatique",
                "Dr. Marie Dubois",
                formatter.parse("2022-01-01"), formatter.parse("2022-01-31"));
        MedicalHistory history2 = new MedicalHistory("Examen annuel", "Vaccination recommandée", "Dr. Jean Renaud",
                formatter.parse("2022-02-01"), formatter.parse("2022-02-28"));
        MedicalHistory history3 = new MedicalHistory("Diagnostic de l'hypertension", "Prescription d'antihypertenseurs",
                "Dr. Sophie Girard",
                formatter.parse("2022-03-01"), formatter.parse("2022-03-31"));
        MedicalHistory history4 = new MedicalHistory("Douleur abdominale", "Échographie abdominale", "Dr. Éric Lavoie",
                formatter.parse("2022-04-01"), formatter.parse("2022-04-30"));
        MedicalHistory history5 = new MedicalHistory("Examen cardiaque", "Test d'effort", "Dr. Chantal Bergeron",
                formatter.parse("2022-05-01"), formatter.parse("2022-05-31"));
        MedicalHistory history6 = new MedicalHistory("Allergies saisonnières", "Antihistaminiques",
                "Dr. Louis Tremblay",
                formatter.parse("2022-06-01"), formatter.parse("2022-06-30"));
        MedicalHistory history7 = new MedicalHistory("Diagnostic de l'asthme", "Inhalateurs à corticoïdes",
                "Dr. Anne Lavigne",
                formatter.parse("2022-07-01"), formatter.parse("2022-07-31"));
        MedicalHistory history8 = new MedicalHistory("Fracture du poignet", "Immobilisation et physiothérapie",
                "Dr. François Martin",
                formatter.parse("2022-08-01"), formatter.parse("2022-08-31"));
        MedicalHistory history9 = new MedicalHistory("Diagnostic de la migraine", "Traitement préventif",
                "Dr. Isabelle Choquette",
                formatter.parse("2022-09-01"), formatter.parse("2022-09-30"));
        MedicalHistory history10 = new MedicalHistory("Bilan de santé général", "Aucun traitement nécessaire",
                "Dr. Rémi Beaulieu",
                formatter.parse("2022-10-01"), formatter.parse("2022-10-31"));
        MedicalHistory history11 = new MedicalHistory("Évaluation pédiatrique", "Vaccins mis à jour",
                "Dr. Lucie Bouchard",
                formatter.parse("2022-11-01"), formatter.parse("2022-11-30"));
        MedicalHistory history12 = new MedicalHistory("Éruption cutanée", "Crèmes topiques corticostéroïdes",
                "Dr. Marc Antoine",
                formatter.parse("2022-12-01"), formatter.parse("2022-12-31"));
        MedicalHistory history13 = new MedicalHistory("Consultation pour l'arthrite",
                "Anti-inflammatoires non stéroïdiens", "Dr. Christine Dufour",
                formatter.parse("2023-01-01"), formatter.parse("2023-01-31"));

        MedicalFile medicalFile1 = new MedicalFile();
        medicalFile1.setInsuranceNumber("ABCD12345678");
        medicalFile1.setPatient(patient1);
        MedicalFile medicalFile2 = new MedicalFile();
        medicalFile2.setInsuranceNumber("BCDE12345678");
        medicalFile2.setPatient(patient2);
        MedicalFile medicalFile3 = new MedicalFile();
        medicalFile3.setInsuranceNumber("CDEF12345678");
        medicalFile3.setPatient(patient3);
        MedicalFile medicalFile4 = new MedicalFile();
        medicalFile4.setInsuranceNumber("DEFG12345678");
        medicalFile4.setPatient(patient4);
        MedicalFile medicalFile5 = new MedicalFile();
        medicalFile5.setInsuranceNumber("EFGH12345678");
        medicalFile5.setPatient(patient5);
        MedicalFile medicalFile6 = new MedicalFile();
        medicalFile6.setInsuranceNumber("FGHI12345678");
        medicalFile6.setPatient(patient6);
        MedicalFile medicalFile7 = new MedicalFile();
        medicalFile7.setInsuranceNumber("GHIJ12345678");
        medicalFile7.setPatient(patient7);
        MedicalFile medicalFile8 = new MedicalFile();
        medicalFile8.setInsuranceNumber("HIJK12345678");
        medicalFile8.setPatient(patient8);
        MedicalFile medicalFile9 = new MedicalFile();
        medicalFile9.setInsuranceNumber("IJKL12345678");
        medicalFile9.setPatient(patient9);
        MedicalFile medicalFile10 = new MedicalFile();
        medicalFile10.setInsuranceNumber("JKLM12345678");
        medicalFile10.setPatient(patient10);
        MedicalFile medicalFile11 = new MedicalFile();
        medicalFile11.setInsuranceNumber("KLMN12345678");
        medicalFile11.setPatient(patient11);
        MedicalFile medicalFile12 = new MedicalFile();
        medicalFile12.setInsuranceNumber("LMNO12345678");
        medicalFile12.setPatient(patient12);
        MedicalFile medicalFile13 = new MedicalFile();
        medicalFile13.setInsuranceNumber("MNOP12345678");
        medicalFile13.setPatient(patient13);

        List<MedicalVisit> medicalVisits1 = Arrays.asList(visit1, visit2);
        List<MedicalVisit> medicalVisits2 = Arrays.asList(visit3, visit4);

        List<MedicalHistory> medicalHistories1 = Arrays.asList(history1);
        List<MedicalHistory> medicalHistories2 = Arrays.asList(history2);

        medicalFile3.setMedicalVisitList(Arrays.asList(visit5, visit6));
        medicalFile3.setMedicalHistoryList(Arrays.asList(history3));

        medicalFile4.setMedicalVisitList(Arrays.asList(visit7, visit8));
        medicalFile4.setMedicalHistoryList(Arrays.asList(history4));

        medicalFile5.setMedicalVisitList(Arrays.asList(visit9, visit10));
        medicalFile5.setMedicalHistoryList(Arrays.asList(history5));

        medicalFile6.setMedicalVisitList(Arrays.asList(visit11, visit12));
        medicalFile6.setMedicalHistoryList(Arrays.asList(history6));

        medicalFile7.setMedicalVisitList(Arrays.asList(visit13, visit14));
        medicalFile7.setMedicalHistoryList(Arrays.asList(history7));

        medicalFile8.setMedicalVisitList(Arrays.asList(visit15, visit16));
        medicalFile8.setMedicalHistoryList(Arrays.asList(history8));

        medicalFile9.setMedicalVisitList(Arrays.asList(visit17, visit18));
        medicalFile9.setMedicalHistoryList(Arrays.asList(history9));

        medicalFile10.setMedicalVisitList(Arrays.asList(visit19, visit20));
        medicalFile10.setMedicalHistoryList(Arrays.asList(history10));

        medicalFile11.setMedicalVisitList(Arrays.asList(visit21, visit22));
        medicalFile11.setMedicalHistoryList(Arrays.asList(history11));

        medicalFile12.setMedicalVisitList(Arrays.asList(visit23, visit24));
        medicalFile12.setMedicalHistoryList(Arrays.asList(history12));

        medicalFile13.setMedicalVisitList(Arrays.asList(visit25, visit26));
        medicalFile13.setMedicalHistoryList(Arrays.asList(history13));

        visit1.setMedicalFile(medicalFile1);
        visit2.setMedicalFile(medicalFile1);
        history1.setMedicalFile(medicalFile1);

        visit3.setMedicalFile(medicalFile2);
        visit4.setMedicalFile(medicalFile2);
        history2.setMedicalFile(medicalFile2);

        visit5.setMedicalFile(medicalFile3);
        visit6.setMedicalFile(medicalFile3);
        history3.setMedicalFile(medicalFile3);

        visit7.setMedicalFile(medicalFile4);
        visit8.setMedicalFile(medicalFile4);
        history4.setMedicalFile(medicalFile4);

        visit9.setMedicalFile(medicalFile5);
        visit10.setMedicalFile(medicalFile5);
        history5.setMedicalFile(medicalFile5);

        visit11.setMedicalFile(medicalFile6);
        visit12.setMedicalFile(medicalFile6);
        history6.setMedicalFile(medicalFile6);

        visit13.setMedicalFile(medicalFile7);
        visit14.setMedicalFile(medicalFile7);
        history7.setMedicalFile(medicalFile7);

        visit15.setMedicalFile(medicalFile8);
        visit16.setMedicalFile(medicalFile8);
        history8.setMedicalFile(medicalFile8);

        visit17.setMedicalFile(medicalFile9);
        visit18.setMedicalFile(medicalFile9);
        history9.setMedicalFile(medicalFile9);

        visit19.setMedicalFile(medicalFile10);
        visit20.setMedicalFile(medicalFile10);
        history10.setMedicalFile(medicalFile10);

        visit21.setMedicalFile(medicalFile11);
        visit22.setMedicalFile(medicalFile11);
        history11.setMedicalFile(medicalFile11);

        visit23.setMedicalFile(medicalFile12);
        visit24.setMedicalFile(medicalFile12);
        history12.setMedicalFile(medicalFile12);

        visit25.setMedicalFile(medicalFile13);
        visit26.setMedicalFile(medicalFile13);
        history13.setMedicalFile(medicalFile13);

        medicalFileRepository.save(medicalFile3);
        medicalFileRepository.save(medicalFile4);
        medicalFileRepository.save(medicalFile5);
        medicalFileRepository.save(medicalFile6);
        medicalFileRepository.save(medicalFile7);
        medicalFileRepository.save(medicalFile8);
        medicalFileRepository.save(medicalFile9);
        medicalFileRepository.save(medicalFile10);
        medicalFileRepository.save(medicalFile11);
        medicalFileRepository.save(medicalFile12);
        medicalFileRepository.save(medicalFile13);

        medicalFileRepository.save(medicalFile1);
        medicalFileRepository.save(medicalFile2);

        medicalVisitRepository.saveAll(medicalVisits1);
        medicalVisitRepository.saveAll(medicalVisits2);

        medicalHistoryRepository.saveAll(medicalHistories1);
        medicalHistoryRepository.saveAll(medicalHistories2);

        // Username user, password: user
        appUserRepository.save(new AppUser("user",
                "$2a$10$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9q0e4bRadue",
                "USER"));

        appUserRepository.save(new AppUser("admin",
                "$2a$10$8cjz47bjbR4Mn8GMg9IZx.vyjhLXR/SKKMSZ9.mP9vpMu0ssKi8GW",
                "ADMIN"));
    }
}
