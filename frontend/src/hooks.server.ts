import { redirect, type Handle, type HandleFetch, type RequestEvent } from '@sveltejs/kit';

const redirectOnInvalidSession = (token: string | undefined, event: RequestEvent<Partial<Record<string, string>>, string | null>) => {
  const unguardedRoutes = ['/login'];
  if (!token && !unguardedRoutes.find((route) => event.url.pathname.startsWith(route))) {
    const inSession = event.cookies.get('in_sess') === '1';
    return redirect(302, `/login`);
  }
};

export const handle: Handle = async ({ event, resolve }) => {
  const token = event.cookies.get('token');
  redirectOnInvalidSession(token, event);
  event.locals.token = token;
  const response = await resolve(event);
  return response;
};

// export const handleFetch: HandleFetch = async ({ event, fetch, request }) => {

//     const token = event.cookies.get('token') || event.locals.token;
//     const requestCookies = event.request.headers.get('cookie');
    
//     redirectOnInvalidSession(token, event);

//     if (requestCookies) {
//       request.headers.set('cookie', requestCookies);
//     }

//     const modifiedRequest = new Request(request);


//   return fetch(modifiedRequest);
// };
