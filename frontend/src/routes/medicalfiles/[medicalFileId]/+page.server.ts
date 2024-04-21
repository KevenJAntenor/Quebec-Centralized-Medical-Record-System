import type { PageServerLoad, RequestEvent } from './$types';
import { API_URL } from '../../../constants';
import { fail } from '@sveltejs/kit';

export const load: PageServerLoad = async ({ fetch, params }) => {
    const { medicalFileId } = params;
    const res = await fetch(`${API_URL}/medical-files/${medicalFileId}`);
    return {
        medicalFile: await res.json(),
    };
};

export const actions: object = {
    createMedicalVisit: async ({ fetch, request }: RequestEvent) => {
        const data = await request.formData();

        const establishment = data.get('establishment');
        const doctor = data.get('doctor');
        const dateOfVisit = data.get('dateOfVisit');
        const diagnostic = data.get('diagnostic');
        const treatment = data.get('treatment');

        if (!establishment) return fail(400, {
            success: false,
            message: 'Please enter the establishment',
            establishment,
        });

        if (!doctor) return fail(400, {
            success: false,
            message: 'Please enter the doctor',
            doctor,
        });

        if (!dateOfVisit) return fail(400, {
            success: false,
            message: 'Please enter the date of visit',
            dateOfVisit,
        });

        if (!diagnostic) return fail(400, {
            success: false,
            message: 'Please enter the diagnostic',
            diagnostic,
        });

        if (!treatment) return fail(400, {
            success: false,
            message: 'Please enter the treatment',
            treatment,
        });

        const medicalFileId = data.get('medicalFileId')
        const res = await fetch(`${API_URL}/medical-files/${medicalFileId}/medical-visits`, {
            method: 'POST',
            body: JSON.stringify({ establishment, doctor, dateOfVisit, diagnostic, treatment }),
        });
        return res;
    },
};

