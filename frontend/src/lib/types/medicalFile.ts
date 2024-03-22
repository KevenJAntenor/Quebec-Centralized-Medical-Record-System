import type { Patient } from "./patient";

export type MedicalFile = {
    id?: number;
    insuranceNumber?: string;
    patient?: Patient;
    // TODO: Add more fields (lists of medical records and visits)
  }
  