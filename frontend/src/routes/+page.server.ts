import type { PageServerLoad } from './$types';
import { API_URL } from '../constants';

export const load: PageServerLoad = async ({ locals }) => {
    let backendData;
    const token = locals.token;
    try {
        const response = await fetch(API_URL, {
            method: 'GET',
            headers: {     
                'Authorization': `${token}`
            }
        });
        backendData = await response.text();
    } catch (error: unknown) {
        backendData = `Make sure your backend is running! Request failed with error: ${error}`;
    }
    return {
        backendData,
        message: (token) ? 'you are logged in!' : 'you are not logged in!',
    };
};
