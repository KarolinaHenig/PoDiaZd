import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { token } from '../auth-guard';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-car-repair-shop',
  templateUrl: './add-car-repair-shop.component.html',
  styleUrls: ['./add-car-repair-shop.component.scss']
})
export class AddCarRepairShopComponent implements OnInit {
  firstFormGroup!: FormGroup;
  carRepairShopName = new FormControl('');
  voivodeships: Array<any> = [];
  counties: Array<any> = [];
  cities: Array<any> = [];
  street = new FormControl('');
  houseNumber = new FormControl('');
  phoneNumber = new FormControl('', [Validators.pattern("[0-9]{9}")]);
  email = new FormControl('');
  disableTextbox = false;

  constructor(private http: HttpClient, private _formBuilder: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.firstFormGroup = this._formBuilder.group({
      voivodeship: ['', Validators.required],
      county: [{ value: '', disabled: true }, Validators.required],
      city: [{ value: '', disabled: true }, Validators.required]
    });

    this.http.post('http://localhost:8080/api/v1/rate-car-repair-shop/voivodeships', {},
      {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((voivodeshipValue: any) => {
        this.voivodeships = voivodeshipValue.voivodeships as Array<any>
      });

    this.firstFormGroup.controls['voivodeship'].valueChanges.subscribe(voivodeshipValue => {
      this.http.post('http://localhost:8080/api/v1/rate-car-repair-shop/counties', {
        "voivodeshipValue": voivodeshipValue.id
      }, {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((countyValue: any) => {
        this.counties = countyValue.counties as Array<any>
        this.firstFormGroup.controls['county'].setValue('')

      });
      if (voivodeshipValue) {
        this.firstFormGroup.controls['county'].enable()
      } else {
        this.firstFormGroup.controls['county'].disable()
        this.firstFormGroup.controls['county'].setValue('')
      }
    });

    this.firstFormGroup.controls['county'].valueChanges.subscribe(countyValue => {
      this.http.post('http://localhost:8080/api/v1/rate-car-repair-shop/cities', {
        "countyValue": countyValue.id
      }, {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((cityValue: any) => {
        this.cities = cityValue.cities as Array<any>
        this.firstFormGroup.controls['city'].setValue('')
      });
      if (countyValue) {
        this.firstFormGroup.controls['city'].enable()
      } else {
        this.firstFormGroup.controls['city'].disable()
        this.firstFormGroup.controls['city'].setValue('')
      }
    });
  }

  add() {
    this.http.post('http://localhost:8080/api/v1/car-repair-shop/add', {
      "carRepairShopName": this.carRepairShopName.value,
      "voivodeship": this.firstFormGroup.controls['voivodeship'].value,
      "county": this.firstFormGroup.controls['county'].value,
      "city": this.firstFormGroup.controls['city'].value,
      "street": this.street.value,
      "houseNumber": this.houseNumber.value,
      "phoneNumber": this.phoneNumber.value,
      "email": this.email.value
    }, {
      headers: {
        "Authorization": "Bearer " + token
      }
    }).subscribe(data => {
      console.log(data)
      this.router.navigateByUrl('/home-page')
    })
    console.log("add")
  }

  displayVoivodeship(voivodeships: any) {
    return voivodeships.voivodeshipName
  }

  displayCounty(counties: any) {
    return counties.countyName
  }

  displayCity(cities: any) {
    return cities.cityName
  }

  getErrorMessageName() {
    return "Please inform car repair name"
  }

  getErrorMessageVoivodeship() {
    return "Please inform your voivodeship"
  }

  getErrorMessageCounty() {
    return "Please inform county"
  }

  getErrorMessageCity() {
    return "Please inform city"
  }

  getErrorMessageStreet() {
    return "Please inform street"
  }

  getErrorMessageHouseNumber() {
    return "Please inform house number"
  }

  getErrorMessageMail() {
    return "Please inform car repair shop email"
  }

  getErrorMessagePhoneNumber() {
    return "Please inform car repair shop phone number"
  }

}
