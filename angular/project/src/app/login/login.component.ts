import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  username = '';
  password = '';
  errorMessage = '';
  invalidLogin = false;

  constructor(public router: Router, public authenticationService: AuthService) { }

  ngOnInit(): void {
  }

  public handleLogin(): void {

    console.log(this.username);

    if (this.authenticationService.authenticate(this.username, this.password)) {
      this.router.navigate(['client']);
      this.invalidLogin = false;
    } else {
      this.errorMessage = 'Verkeerde gebruikersnaam en of wachtwoord';
      this.invalidLogin = true;
    }

  }

}
