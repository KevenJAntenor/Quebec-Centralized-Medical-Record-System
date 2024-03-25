// @ts-nocheck
import type { PageServerLoad } from './$types';
import { API_URL } from '../../constants';
export const load = async ({ fetch }: Parameters<PageServerLoad>[0]) => {
    const res = await fetch(`${API_URL}/medical-files`, {
        credentials: 'include',
    });
    return {
        medicalFiles: await res.json(),
    };
};