import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { token } from '../auth-guard';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-rate-car-repair-shop',
  templateUrl: './rate-car-repair-shop.component.html',
  styleUrls: ['./rate-car-repair-shop.component.scss']
})
export class RateCarRepairShopComponent implements OnInit {
  isLinear = true;
  showInfo = true;
  firstFormGroup!: FormGroup;
  secondFormGroup!: FormGroup;
  thirdFormGroup!: FormGroup;
  voivodeships: Array<any> = [];
  counties: Array<any> = [];
  cities: Array<any> = [];

  constructor(private http: HttpClient, private _formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.firstFormGroup = this._formBuilder.group({
      voivodeship: ['', Validators.required],
      county: ['', Validators.required],
      city: ['', Validators.required],
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
    });
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

}
