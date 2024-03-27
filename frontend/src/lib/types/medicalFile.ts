import type { Patient } from "./patient";
import type { MedicalVisit } from "./medicalVisit";

export type MedicalFile = {
    id?: number;
    insuranceNumber?: string;
    patient?: Patient;
    // TODO: Add more fields (lists of medical records and visits)
    medicalVisitList?: MedicalVisit[];
}

