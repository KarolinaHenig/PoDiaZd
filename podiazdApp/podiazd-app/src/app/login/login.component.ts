import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {
  email = new FormControl('')
  password = new FormControl('')
  hide = true

  constructor(private http: HttpClient) { }

  login() {
    this.http.post('localhost:8080/api/v1/login', {
      "email": this.email,
      "password": this.password
    })
  }

  ngOnInit(): void {
  }

  getErrorMessage() {
    return ""
  }
}
