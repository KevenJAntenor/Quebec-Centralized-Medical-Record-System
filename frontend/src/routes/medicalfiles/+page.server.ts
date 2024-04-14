import type { PageServerLoad } from './$types';
import { API_URL } from '../../constants';
export const load: PageServerLoad = async ({ fetch, locals }) => {
    const res = await fetch(`${API_URL}/medical-files`, {
        headers: {
            'Authorization': `${locals.token}`
        }
    });
    return {
        medicalFiles: await res.json(),
    };
};
