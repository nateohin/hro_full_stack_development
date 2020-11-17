import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(public router: Router) { }

  authenticate(username: string, password: string): boolean {
    if (username === 'test' && password === 'test') {
      sessionStorage.setItem('authenticatedUser', username);
      return true;
    }
    return false;
  }

  isUserLoggedIn(): boolean {
    const user = sessionStorage.getItem('authenticatedUser');
    return !(user === null);
  }

  logout(): void {
    sessionStorage.removeItem('authenticatedUser');
  }

}
