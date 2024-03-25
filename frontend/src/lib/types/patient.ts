import type { Coordinate } from './coordinate';

export type Patient = {
    id?: number;
    lastName?: string;
    firstName?: string;
    dateOfBirth?: string;
    gender?: string;
    knownParent1?: string;
    knownParent2?: string;
    cityOfBirth?: string;
    coordinate?: Coordinate;
  }