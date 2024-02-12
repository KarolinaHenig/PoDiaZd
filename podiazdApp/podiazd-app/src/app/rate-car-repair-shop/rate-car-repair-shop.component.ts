import { HttpClient } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { token } from '../auth-guard';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatStepper, } from '@angular/material/stepper';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/internal/Observable';
import { startWith } from 'rxjs/internal/operators/startWith';
import { filter } from 'rxjs/internal/operators/filter';
import { map } from 'rxjs/internal/operators/map';
import { tap } from 'rxjs';
import { StarRatingComponent } from '../star-rating/star-rating.component';

@Component({
  selector: 'app-rate-car-repair-shop',
  templateUrl: './rate-car-repair-shop.component.html',
  styleUrls: ['./rate-car-repair-shop.component.scss']
})
export class RateCarRepairShopComponent implements OnInit {
  isLinear = true;
  firstFormGroup!: FormGroup;
  secondFormGroup!: FormGroup;
  thirdFormGroup!: FormGroup;
  fourthFormGroup!: FormGroup;
  voivodeships: Array<any> = [];
  filteredVoivodeships: Array<any> = [];
  counties: Array<any> = [];
  filteredCounties: Array<any> = [];
  cities: Array<any> = [];
  filteredCities: Array<any> = [];
  carRepairShops: Array<any> = [];
  brands: Array<any> = [];
  filteredBrands: Array<any> = [];
  models: Array<any> = [];
  filteredModels: Array<any> = [];
  generations: Array<any> = [];
  filteredGenerations: Array<any> = [];
  bodyTypes: Array<any> = [];
  filteredBodyTypes: Array<any> = [];
  modifications: Array<any> = [];
  filteredModifications: Array<any> = [];
  categories: Array<any> = [];
  filteredCategories: Array<any> = [];
  malfunctions: Array<any> = [];
  selectedCarRepairShop: any;
  selectedCarMalfunction: any;
  openDialogBoolean = false;
  rating = 0;

  constructor(private http: HttpClient, private _formBuilder: FormBuilder, public dialog: MatDialog, private router: Router) { }
  openDialog(carRepairShop: any, city: any, voivodeship: any): void {
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      data: { carRepairShop, city, voivodeship },
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.openDialogBoolean = false;
    });
    this.openDialogBoolean = true;
  }

  onSelect(carRepairShop: any, stepper: MatStepper): void {
    this.selectedCarRepairShop = carRepairShop;
    if (!this.openDialogBoolean) {
      stepper.next();
    }
  }

  onSelectMalfunction(malfunction: any, stepper: MatStepper): void {
    this.selectedCarMalfunction = malfunction;
    if (!this.openDialogBoolean) {
      stepper.next();
    }
  }

  ngOnInit(): void {
    this.firstFormGroup = this._formBuilder.group({
      voivodeship: ['', Validators.required],
      county: ['', Validators.required],
      city: ['', Validators.required],
    });

    this.secondFormGroup = this._formBuilder.group({
      brand: ['', Validators.required],
      model: ['', Validators.required],
      generation: ['', Validators.required],
      bodyType: ['', Validators.required],
      modification: ['', Validators.required]
    });

    this.thirdFormGroup = this._formBuilder.group({
      category: ['', Validators.required],
    });

    this.fourthFormGroup = this._formBuilder.group({
      opinion: [''],
    });

    this.http.post('http://localhost:8080/api/v1/rate-car-repair-shop/voivodeships', {},
      {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((voivodeshipValue: any) => {
        this.voivodeships = voivodeshipValue.voivodeships as Array<any>
        this.filteredVoivodeships = voivodeshipValue.voivodeships as Array<any>
      });

    this.firstFormGroup.controls['voivodeship'].valueChanges.subscribe(voivodeshipValue => {
      if (typeof voivodeshipValue == "string") {
        return
      }

      this.http.post('http://localhost:8080/api/v1/rate-car-repair-shop/counties', {
        "voivodeshipValue": voivodeshipValue.id
      }, {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((countyValue: any) => {
        this.counties = countyValue.counties as Array<any>
        this.filteredCounties = countyValue.counties as Array<any>
        this.firstFormGroup.controls['county'].setValue('')
      });
    });

    this.firstFormGroup.controls['voivodeship'].valueChanges.pipe(
      startWith(""),
      filter(value => typeof value == "string"),
      map((value: any) => this.voivodeships.filter(voivodeship => voivodeship.voivodeshipName.toLowerCase().startsWith(value.toLowerCase())))
    ).subscribe(value => this.filteredVoivodeships = value)

    this.firstFormGroup.controls['county'].valueChanges.subscribe(countyValue => {
      this.http.post('http://localhost:8080/api/v1/rate-car-repair-shop/cities', {
        "countyValue": countyValue.id
      }, {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((cityValue: any) => {
        this.cities = cityValue.cities as Array<any>
        this.filteredCities = cityValue.cities as Array<any>
        this.firstFormGroup.controls['city'].setValue('')
      });
    });

    this.firstFormGroup.controls['county'].valueChanges.pipe(
      startWith(""),
      filter(value => typeof value == "string"),
      map((value: any) => this.counties.filter(county => county.countyName.toLowerCase().startsWith(value.toLowerCase())))
    ).subscribe(value => this.filteredCounties = value)

    this.firstFormGroup.controls['city'].valueChanges.subscribe(countyValue => {
      this.http.post('http://localhost:8080/api/v1/car-repair-shop/select', {
        "cityId": this.firstFormGroup.controls['city'].value.id,
      }, {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((carRepairShopValue: any) => {
        this.carRepairShops = carRepairShopValue.carRepairShops as Array<any>
      });
    });

    this.firstFormGroup.controls['city'].valueChanges.pipe(
      startWith(""),
      filter(value => typeof value == "string"),
      map((value: any) => this.cities.filter(city => city.cityName.toLowerCase().startsWith(value.toLowerCase())))
    ).subscribe(value => this.filteredCities = value)

    this.http.post('http://localhost:8080/api/v1/find-car-repair-shop/brands', {},
      {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((brandValue: any) => {
        this.brands = brandValue.brands as Array<any>
        this.filteredBrands = brandValue.brands as Array<any>
      });

      
    this.secondFormGroup.controls['brand'].valueChanges.pipe(
      startWith(""),
      filter(value => typeof value == "string"),
      map((value: any) => this.brands.filter(brand => brand.brandName.toLowerCase().startsWith(value.toLowerCase())))
    ).subscribe(value => this.filteredBrands = value)

    this.secondFormGroup.controls['brand'].valueChanges.subscribe(brandValue => {
      if (typeof brandValue == "string") {
        return
      }
      this.http.post('http://localhost:8080/api/v1/find-car-repair-shop/models', {
        "brandValue": brandValue.id
      }, {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((modelValue: any) => {
        this.models = modelValue.models as Array<any>
        this.filteredModels = modelValue.models as Array<any>
        this.secondFormGroup.controls['model'].setValue('')
      });
    });

    this.secondFormGroup.controls['model'].valueChanges.pipe(
      startWith(""),
      filter(value => typeof value == "string"),
      map((value: any) => this.models.filter(model => model.modelName.toLowerCase().startsWith(value.toLowerCase())))
    ).subscribe(value => this.filteredModels = value)

    this.secondFormGroup.controls['model'].valueChanges.subscribe(modelValue => {
      this.http.post('http://localhost:8080/api/v1/find-car-repair-shop/generations', {
        "modelValue": modelValue.id
      }, {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((generationValue: any) => {
        if (generationValue.generations) {
          this.generations = generationValue.generations as Array<any>
          this.filteredGenerations = generationValue.generations as Array<any>
        } 
        this.secondFormGroup.controls['modification'].setValue('')
        this.secondFormGroup.controls['generation'].setValue('')
      });
    });

    this.secondFormGroup.controls['generation'].valueChanges.pipe(
      startWith(""),
      filter(value => typeof value == "string"),
      map((value: any) => {
        return this.generations.filter(generation => generation.generationName.toLowerCase().startsWith(value.toLowerCase()))
      })
    ).subscribe(value => {this.filteredGenerations = value})

    this.secondFormGroup.controls['generation'].valueChanges.subscribe(generationValue => {
      this.http.post('http://localhost:8080/api/v1/find-car-repair-shop/bodyTypes', {
        "generationValue": generationValue.id
      }, {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((bodyTypeValue: any) => {
        if(bodyTypeValue.bodyTypes){
          this.bodyTypes = bodyTypeValue.bodyTypes as Array<any>
          this.filteredBodyTypes = bodyTypeValue.bodyTypes as Array<any>
        }
        this.secondFormGroup.controls['bodyType'].setValue('')
      });
    });
    
    this.secondFormGroup.controls['bodyType'].valueChanges.pipe(
      startWith(""),
      filter(value => typeof value == "string"),
      map((value: any) => {
       return this.bodyTypes.filter(bodyType => bodyType.bodyTypeName.toLowerCase().startsWith(value.toLowerCase()))
      })
    ).subscribe(value => {this.filteredBodyTypes = value})
    
    this.secondFormGroup.controls['generation'].valueChanges.subscribe(generationValue => {
      this.http.post('http://localhost:8080/api/v1/find-car-repair-shop/modifications', {
        "generationValue": generationValue.id
      }, {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((modificationValue: any) => {
        if(modificationValue.modifications){
          this.modifications = modificationValue.modifications as Array<any>
          this.filteredModifications = modificationValue.modifications as Array<any>
        }
        this.secondFormGroup.controls['modification'].setValue('')
      });
    });

    this.secondFormGroup.controls['bodyType'].valueChanges.subscribe(() => {
      this.secondFormGroup.controls['modification'].setValue('')
    });
    
    this.secondFormGroup.controls['modification'].valueChanges.pipe(
      startWith(""),
      filter(value => typeof value == "string"),
      map((value: any) => {
       return this.modifications.filter(modifications => modifications.modificationName.toLowerCase().startsWith(value.toLowerCase()))
      })
    ).subscribe(value => {this.filteredModifications = value})

    this.secondFormGroup.controls['modification'].valueChanges.subscribe(() => {
    this.http.post('http://localhost:8080/api/v1/find-car-repair-shop/categories', {},
      {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((categoryValue: any) => {
        this.categories = categoryValue.categories as Array<any>
        this.filteredCategories = categoryValue.categories as Array<any>
      });
    });

    this.thirdFormGroup.controls['category'].valueChanges.pipe(
      startWith(""),
      filter(value => typeof value == "string"),
      map((value: any) => {
        return this.categories.filter(categories => categories.categoryName.toLowerCase().startsWith(value.toLowerCase()))
    })
      ).subscribe(value => this.filteredCategories = value)

    
  this.thirdFormGroup.controls['category'].valueChanges.subscribe((categoryValue) => {
    this.http.post('http://localhost:8080/api/v1/find-car-repair-shop/malfunctions', {
      "category" : categoryValue.id,
      "modificationValue" : this.secondFormGroup.controls['modification'].value.id
    }, 
      {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((malfunctionValue: any) => {
        this.malfunctions = malfunctionValue.malfunctions as Array<any>
      });
    });

  }

  rate() {
    this.http.post('http://localhost:8080/api/v1/car-repair-shop/rate', {
      "carRepairShop": this.selectedCarRepairShop,
      "brand": this.secondFormGroup.controls['brand'].value,
      "model": this.secondFormGroup.controls['model'].value,
      "generation": this.secondFormGroup.controls['generation'].value,
      "bodyType": this.secondFormGroup.controls['bodyType'].value,
      "modification": this.secondFormGroup.controls['modification'].value,
      "malfunction": this.selectedCarMalfunction,
      "rate": this.rating,
      "opinion": this.fourthFormGroup.controls['opinion'].value
    }, {
      headers: {
        "Authorization": "Bearer " + token
      }
    }).subscribe(data => {
      console.log(data)
      this.router.navigateByUrl('/home-page')
    })
    console.log("rate")
  }

  setRating(rating: number) {
    this.rating = rating
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
}

export interface DialogData {
  carRepairShop: any;
  city: any;
  voivodeship: any;
}

@Component({
  selector: 'dialog-overview-example-dialog',
  templateUrl: 'dialog-overview-example-dialog.html',
  standalone: true,
  imports: [MatDialogModule, MatFormFieldModule, MatInputModule, FormsModule, MatButtonModule],
})

export class DialogOverviewExampleDialog {
  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
