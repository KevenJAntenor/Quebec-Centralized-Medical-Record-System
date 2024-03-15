package com.santeConnect.adapter.db;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;

import com.santeConnect.adapter.api.MedicalFileRepoSqlite;
import com.santeConnect.domain.MedicalFile;
import com.santeConnect.domain.MedicalFileRepo;
import com.santeConnect.domain.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DBRider
@DBUnit(caseSensitiveTableNames = true,caseInsensitiveStrategy = Orthography.LOWERCASE)
public class MedicalFileRepoSqliteTest {

    private DataSource dataSource;

    @SuppressWarnings("unused") // Used by DBRider framework
    private final ConnectionHolder connectionHolder
            = () -> dataSource.getConnection();

    @BeforeEach
    void setupConnection() {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:medical_file.db");
        this.dataSource = ds;
    }

    @Test
    @DataSet(value = "adapters/data/createMedicalFile.json")
    @ExpectedDataSet(value = "adapters/data/expectedMedicalFile.json")
    void storesNewMedicalFile() {}

    @Test
    @DataSet(value = "adapters/data/createMedicalFileWithPatient.json")
    @ExpectedDataSet(value = "adapters/data/expectedMedicalFileWithPatient.json")
    void storesNewMedicalFileWithPatientId() {}

    @Test
    @DataSet(value = "adapters/data/createPatient.json")
    @ExpectedDataSet(value = "adapters/data/expectedPatient.json")
    void storesNewPatient() {}

    @Test
    @DataSet(value = {"adapters/data/createPatient.json", "adapters/data/createMedicalFileWithPatient.json"})
//    @DataSet(value = "adapters/data/createMedicalFileWithPatient.json",
//            cleanBefore = true)
    void getMedicalFileFromInsurance() {
        MedicalFileRepo medicalFiles = new MedicalFileRepoSqlite(dataSource);
        var insuranceNumber = "123456";
        var patient = new Patient("Doe", "John");
        var medicalFileExpected = new MedicalFile(insuranceNumber, patient);

        Optional<MedicalFile> medicalFileActual = medicalFiles.getFromInsuranceNumber(insuranceNumber);

        // TODO maybe make hash functions to compare only if needed in production
        assertThat(medicalFileActual.isPresent()).isTrue();
        assertThat(medicalFileActual.get().getInsuranceNumber()).isEqualTo(medicalFileExpected.getInsuranceNumber());
        assertThat(medicalFileActual.get().getPatient().getFirstName())
                .isEqualTo(medicalFileExpected.getPatient().getFirstName());
        assertThat(medicalFileActual.get().getPatient().getLastName())
                .isEqualTo(medicalFileExpected.getPatient().getLastName());
    }
}
