// @ts-ignore
import type { PageServerLoad } from './$types';
import { API_URL } from '../../../constants';
import type { MedicalFile } from '$lib/types/medicalFile';
import { fail, redirect } from '@sveltejs/kit';

// @ts-ignore
export const load: PageServerLoad = async ({ fetch, params }) => {
    const { medicalFileId } = params;
    const res = await fetch(`${API_URL}/api/medicalFiles/${medicalFileId}`, {
        credentials: 'include',
    });
    return {
        medicalFile: await res.json(),
    };
};
