import type { PageServerLoad } from './$types';
import { API_URL } from '../../constants';
export const load: PageServerLoad = async ({ fetch }) => {
    const res = await fetch(`${API_URL}/medical-files`);
    return {
        medicalFiles: await res.json(),
    };
};
