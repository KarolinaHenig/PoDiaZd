import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { token } from '../auth-guard';

@Component({
  selector: 'app-find-car-repair-shop',
  templateUrl: './find-car-repair-shop.component.html',
  styleUrls: ['./find-car-repair-shop.component.scss']
})
export class FindCarRepairShopComponent implements OnInit {
  isLinear = true;
  showInfo = true;
  firstFormGroup!: FormGroup;
  secondFormGroup!: FormGroup;
  thirdFormGroup!: FormGroup;
  brands: Array<any> = [];
  models: Array<any> = [];
  generations: Array<any> = [];
  bodyTypes: Array<any> = [];
  modifications: Array<any> = [];
  categories: Array<any> = [];
  voivodeships: Array<any> = [];
  counties: Array<any> = [];
  cities: Array<any> = [];

  constructor(private http: HttpClient, private _formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.firstFormGroup = this._formBuilder.group({
      brand: ['', Validators.required],
      model: ['', Validators.required],
      generation: ['', Validators.required],
      bodyType: ['', Validators.required],
      modification: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      category: ['', Validators.required],
      malfunctionDescription: ['', Validators.required],
    });
    this.thirdFormGroup = this._formBuilder.group({
      voivodeship: ['', Validators.required],
      county: ['', Validators.required],
      city: ['', Validators.required],
    });

    this.http.post('http://localhost:8080/api/v1/find-car-repair-shop/brands', {},
      {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((brandValue: any) => {
        this.brands = brandValue.brands as Array<any>
      });

    this.firstFormGroup.controls['brand'].valueChanges.subscribe(brandValue => {
      this.http.post('http://localhost:8080/api/v1/find-car-repair-shop/models', {
        "brandValue": brandValue.id
      }, {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((modelValue: any) => {
        this.models = modelValue.models as Array<any>
        this.firstFormGroup.controls['model'].setValue('')
      });
    });

    this.firstFormGroup.controls['model'].valueChanges.subscribe(modelValue => {
      this.http.post('http://localhost:8080/api/v1/find-car-repair-shop/generations', {
        "modelValue": modelValue.id
      }, {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((generationValue: any) => {
        this.generations = generationValue.generations as Array<any>
        this.firstFormGroup.controls['generation'].setValue('')
      });
    });

    this.firstFormGroup.controls['generation'].valueChanges.subscribe(generationValue => {
      this.http.post('http://localhost:8080/api/v1/find-car-repair-shop/bodyTypes', {
        "generationValue": generationValue.id
      }, {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((bodyTypeValue: any) => {
        this.bodyTypes = bodyTypeValue.bodyTypes as Array<any>
        this.firstFormGroup.controls['bodyType'].setValue('')
      });
    });

    this.firstFormGroup.controls['generation'].valueChanges.subscribe(generationValue => {
      this.http.post('http://localhost:8080/api/v1/find-car-repair-shop/modifications', {
        "generationValue": generationValue.id
      }, {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((modificationValue: any) => {
        this.modifications = modificationValue.modifications as Array<any>
        this.firstFormGroup.controls['modification'].setValue('')
      });
    });

    this.http.post('http://localhost:8080/api/v1/find-car-repair-shop/categories', {},
      {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((categoryValue: any) => {
        this.categories = categoryValue.categories as Array<any>
      });

  
      this.http.post('http://localhost:8080/api/v1/rate-car-repair-shop/voivodeships', {},
      {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((voivodeshipValue: any) => {
        this.voivodeships = voivodeshipValue.voivodeships as Array<any>
      });
  
      this.thirdFormGroup.controls['voivodeship'].valueChanges.subscribe(voivodeshipValue => {
        this.http.post('http://localhost:8080/api/v1/rate-car-repair-shop/counties', {
          "voivodeshipValue": voivodeshipValue.id
        }, {
          headers: {
            "Authorization": "Bearer " + token
          }
        }).subscribe((countyValue: any) => {
          this.counties = countyValue.counties as Array<any>
          this.thirdFormGroup.controls['county'].setValue('')
        });
      });
  
      this.thirdFormGroup.controls['county'].valueChanges.subscribe(countyValue => {
        this.http.post('http://localhost:8080/api/v1/rate-car-repair-shop/cities', {
          "countyValue": countyValue.id
        }, {
          headers: {
            "Authorization": "Bearer " + token
          }
        }).subscribe((cityValue: any) => {
          this.cities = cityValue.cities as Array<any>
          this.thirdFormGroup.controls['city'].setValue('')
        });
      });
  }

  displayBrand(brand: any) {
    return brand.brandName
  }
  displayModel(model: any) {
    return model.modelName
  }
  displayGeneration(generation: any) {
    return generation.generationName
  }
  displayBodyType(bodyTypes: any) {
    return bodyTypes.bodyTypeName
  }
  displayModification(modifications: any) {
    return modifications.modificationName
  }
  displayCategory(categories: any) {
    return categories.categoryName
  }
  displayMalfunctionDescription(malfunctionDescriptions: any) {
    return malfunctionDescriptions.description
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
