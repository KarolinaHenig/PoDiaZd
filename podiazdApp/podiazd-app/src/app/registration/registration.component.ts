import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  email = new FormControl('')
  name = new FormControl('')
  surname = new FormControl('')
  password = new FormControl('')
  hidePassword = true
  repeatPassword = new FormControl('')
  hideRepeatPassword = true

  constructor(private http: HttpClient) { }

  register() {
    this.http.post('http://localhost:8080/api/v1/registration', {
      "name": this.name.value,
      "surname": this.surname.value,
      "email": this.email.value,
      "password": this.password.value,
      "repeatPassword": this.repeatPassword.value
    }).subscribe(data => {
      console.log(data)
    })
    console.log("register")
  }

  ngOnInit(): void {
  }

  getErrorMessage() {
    return ""
  }

}
