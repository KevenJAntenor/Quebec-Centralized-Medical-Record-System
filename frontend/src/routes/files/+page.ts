import type { PageLoad } from './$types';
import { API_URL } from '../../constants';
export const load: PageLoad = async ({ fetch }) => {
    const res = await fetch(`${API_URL}/medicalFiles`, {
        credentials: 'include',
    });
    return {
        medicalFiles: await res.json(),
    };
};