import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  minPw = 8
  email = new FormControl('')
  name = new FormControl('')
  surname = new FormControl('')
  password = new FormControl('', [Validators.required, Validators.minLength(this.minPw)])
  hidePassword = true
  repeatPassword = new FormControl('', [Validators.required, Validators.minLength(this.minPw)])
  hideRepeatPassword = true

  constructor(private http: HttpClient, private router: Router) {
    this.repeatPassword.addValidators(
      this.createCompareValidator(
        this.password,
        this.repeatPassword
      )
    )
  }

  createCompareValidator(controlOne: AbstractControl, controlTwo: AbstractControl) {
    return () => {
      if (controlOne.value !== controlTwo.value)
        return { matchError: true }
      return null
    }

  }
  
  register() {
    this.http.post('http://localhost:8080/api/v1/registration', {
      "name": this.name.value,
      "surname": this.surname.value,
      "email": this.email.value,
      "password": this.password.value,
      "repeatPassword": this.repeatPassword.value
    }).subscribe(data => {
      console.log(data)
      this.router.navigateByUrl('/home-page')
    })
    console.log("register")
  }

  onPassword() {
    this.repeatPassword.updateValueAndValidity()
  }

  ngOnInit(): void {

  }

  getErrorMessageMail() {
    return "Wpisz adres email"
  }
  getErrorMessageName() {
    return "Wpisz imię"
  }
  getErrorMessageSurname() {
    return "Wpisz nazwisko"
  }
  getErrorMessagePassword() {
    return "Wpisz hasło"
  }
  getErrorMessageMinLenght() {
    return "Hasło musi posiadać conajmniej 8 znaków"
  }

  getErrorPasswordMismatch() {
    return "Hasła różnią sie"
  }
}
