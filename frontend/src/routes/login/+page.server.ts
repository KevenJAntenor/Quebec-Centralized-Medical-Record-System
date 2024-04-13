import { redirect, fail } from '@sveltejs/kit';
import type { RequestEvent } from '../$types';
import { API_URL } from '../../constants';

export const actions: object = {
  default: async ({ cookies, fetch, request }: RequestEvent) => {
    const data = await request.formData();
    const username = data.get('username');
    const password = data.get('password');

    if (!username) return fail(400, {
      success: false,
      message: 'Please enter a username',
      username,
    });

    if (!password) return fail(400, {
      success: false,
      message: 'Please enter a password',
      username,
    });

    const res = await fetch(`${API_URL}/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password }),
    });

    if (res.status === 400 || res.status === 401) return fail(res.status, {
      success: false,
      message: 'Your credentials are invalid',
      username,
    });

    if (res.ok) {      
      const token = res.headers.get('Authorization');

      if (!token) {
        throw new Error('Token is missing from Authorization header');
      }

      cookies.set('token', token, {
        path: '/',
        httpOnly: true,
        sameSite: 'strict',
        secure: false,
        maxAge: 60 * 60, // 1h
      });

      cookies.set('in_sess', '1', {
        path: '/',
        httpOnly: true,
        sameSite: 'strict',
        secure: false,
        maxAge: 60 * 60, // 1h
      });

      redirect(302, '/medicalfiles');
    }

    return fail(500, {
      success: false,
      message: 'Internal server error',
      username,
    });
  },
};
