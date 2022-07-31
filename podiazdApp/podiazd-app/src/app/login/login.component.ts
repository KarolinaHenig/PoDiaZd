import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {
  email = new FormControl('')
  hide = true
  constructor() { }

  ngOnInit(): void {
  }

  getErrorMessage() {
    return ""
  }
}

