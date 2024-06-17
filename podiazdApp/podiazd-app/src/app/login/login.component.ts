import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})


export class LoginComponent implements OnInit {
  email = new FormControl('', Validators.required)
  password = new FormControl('')
  hide = true
  token = null as string | null
  status = ""
  constructor(private http: HttpClient, private router: Router) { }

  login() {
    this.http.post('http://localhost:8080/api/v1/login', {

      "email": this.email.value,
      "password": this.password.value

    }).subscribe((data: any) => {
      console.log(data)
      this.token = data.token as string
      localStorage.setItem("token", this.token)
      this.router.navigateByUrl('/home-page')
    },
      (error) => {
        console.log(error)
        this.status = error.status as string
      }
    )
  }

  onEmailChange() {
    if (this.status == '403') {
      this.status = ''
    }
  }
  ngOnInit(): void {
  }

  getErrorMessageMail() {
    return "Podaj adres email"
  }
  getErrorMessagePassword() {
    return "Podaj hasło"
  }
  getErrorMessageWrongEmail() {
    return "Nieprawidłowy adres email lub hasło"
  }
}
