import { HttpClient } from '@angular/common/http';
import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';
import { token } from '../auth-guard';
import { filter, map, startWith } from 'rxjs';
import { MatStepper } from '@angular/material/stepper';
import { MAT_DIALOG_DATA, MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule, NgForOf } from '@angular/common';
import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';


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
  sortedMalfunctions: Array<any> = [];
  selectedCarMalfunction: any;
  voivodeships: Array<any> = [];
  filteredVoivodeships: Array<any> = [];
  counties: Array<any> = [];
  filteredCounties: Array<any> = [];
  cities: Array<any> = [];
  filteredCities: Array<any> = [];
  carRepairShops: Array<any> = [];
  openDialogBoolean = false;
  opinions: Array<any> = [];


  displayedColumns: string[] = ['carRepairShopName', 'address', 'averageRate', 'averageRateCar', 'averageRateMalfunction', 'opinion'];

  constructor(private http: HttpClient, private _formBuilder: FormBuilder, public dialog: MatDialog) { }

  openDialog(carRepairShop: any): void {
    this.http.post('http://localhost:8080/api/v1/car-repair-shop/opinions', {
      "carRepairShopId": carRepairShop.id
    },
      {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((data: any) => {
        const dialogRef = this.dialog.open(DialogOverviewOpinionsDialog, {
          data: { opinions: data.opinions, carRepairShopName:carRepairShop.carRepairShopName }
        });

        dialogRef.afterClosed().subscribe(result => {
          console.log('The dialog was closed');
          this.openDialogBoolean = false;
        });
        this.openDialogBoolean = true;
      })

  }

  onSelectMalfunction(malfunction: any, stepper: MatStepper): void {
    this.selectedCarMalfunction = malfunction;
    stepper.next();
  }

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
      keyWords: ['']
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
        this.filteredBrands = brandValue.brands as Array<any>
      });

    this.firstFormGroup.controls['brand'].valueChanges.pipe(
      startWith(""),
      filter(value => typeof value == "string"),
      map((value: any) => this.brands.filter(brand => brand.brandName.toLowerCase().startsWith(value.toLowerCase())))
    ).subscribe(value => this.filteredBrands = value)

    this.firstFormGroup.controls['brand'].valueChanges.subscribe(brandValue => {
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
        this.firstFormGroup.controls['model'].setValue('')
      });
    });

    this.firstFormGroup.controls['model'].valueChanges.pipe(
      startWith(""),
      filter(value => typeof value == "string"),
      map((value: any) => this.models.filter(model => model.modelName.toLowerCase().startsWith(value.toLowerCase())))
    ).subscribe(value => this.filteredModels = value)

    this.firstFormGroup.controls['model'].valueChanges.subscribe(modelValue => {
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
        this.firstFormGroup.controls['modification'].setValue('')
        this.firstFormGroup.controls['generation'].setValue('')
      });
    });

    this.firstFormGroup.controls['generation'].valueChanges.pipe(
      startWith(""),
      filter(value => typeof value == "string"),
      map((value: any) => {
        return this.generations.filter(generation => generation.generationName.toLowerCase().startsWith(value.toLowerCase()))
      })
    ).subscribe(value => { this.filteredGenerations = value })

    this.firstFormGroup.controls['generation'].valueChanges.subscribe(generationValue => {
      this.http.post('http://localhost:8080/api/v1/find-car-repair-shop/bodyTypes', {
        "generationValue": generationValue.id
      }, {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((bodyTypeValue: any) => {
        if (bodyTypeValue.bodyTypes) {
          this.bodyTypes = bodyTypeValue.bodyTypes as Array<any>
          this.filteredBodyTypes = bodyTypeValue.bodyTypes as Array<any>
        }
        this.firstFormGroup.controls['bodyType'].setValue('')
      });
    });

    this.firstFormGroup.controls['bodyType'].valueChanges.pipe(
      startWith(""),
      filter(value => typeof value == "string"),
      map((value: any) => {
        return this.bodyTypes.filter(bodyType => bodyType.bodyTypeName.toLowerCase().startsWith(value.toLowerCase()))
      })
    ).subscribe(value => { this.filteredBodyTypes = value })

    this.firstFormGroup.controls['generation'].valueChanges.subscribe(generationValue => {
      this.http.post('http://localhost:8080/api/v1/find-car-repair-shop/modifications', {
        "generationValue": generationValue.id
      }, {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((modificationValue: any) => {
        if (modificationValue.modifications) {
          this.modifications = modificationValue.modifications as Array<any>
          this.filteredModifications = modificationValue.modifications as Array<any>
        }
        this.firstFormGroup.controls['modification'].setValue('')
      });
    });

    this.firstFormGroup.controls['bodyType'].valueChanges.subscribe(() => {
      this.firstFormGroup.controls['modification'].setValue('')
    });

    this.firstFormGroup.controls['modification'].valueChanges.pipe(
      startWith(""),
      filter(value => typeof value == "string"),
      map((value: any) => {
        return this.modifications.filter(modifications => modifications.modificationName.toLowerCase().startsWith(value.toLowerCase()))
      })
    ).subscribe(value => { this.filteredModifications = value })

    this.firstFormGroup.controls['modification'].valueChanges.subscribe(() => {
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

    this.secondFormGroup.controls['category'].valueChanges.pipe(
      startWith(""),
      filter(value => typeof value == "string"),
      map((value: any) => {
        return this.categories.filter(categories => categories.categoryName.toLowerCase().startsWith(value.toLowerCase()))
      })
    ).subscribe(value => this.filteredCategories = value)


    this.secondFormGroup.controls['category'].valueChanges.subscribe((categoryValue) => {
      this.http.post('http://localhost:8080/api/v1/find-car-repair-shop/malfunctions', {
        "category": categoryValue.id,
        "modificationValue": this.firstFormGroup.controls['modification'].value.id
      },
        {
          headers: {
            "Authorization": "Bearer " + token
          }
        }).subscribe((malfunctionValue: any) => {
          this.malfunctions = malfunctionValue.malfunctions as Array<any>
          this.sortedMalfunctions = this.malfunctions
        });
    });

    this.secondFormGroup.controls['keyWords'].valueChanges.subscribe((keyWordsValue) => {
      this.sortedMalfunctions = this.malfunctions.sort((a: any, b: any) => {
        let aText = a.malfunctionName + " - " + a.description
        let bText = b.malfunctionName + " - " + b.description
        let aIndexOf = aText.toLowerCase().indexOf(keyWordsValue.toLowerCase())
        let bIndexOf = bText.toLowerCase().indexOf(keyWordsValue.toLowerCase())

        if (aIndexOf > -1 && bIndexOf > -1) {
          if (aIndexOf == bIndexOf) {
            return 0
          }
          return aIndexOf < bIndexOf ? -1 : 1
        }

        if (aIndexOf > -1) {
          return -1
        } else if (bIndexOf > -1) {
          return 1
        } else {
          return 0
        }
      }) as Array<any>
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

    this.thirdFormGroup.controls['voivodeship'].valueChanges.subscribe(voivodeshipValue => {
      this.http.post('http://localhost:8080/api/v1/rate-car-repair-shop/counties', {
        "voivodeshipValue": voivodeshipValue.id
      }, {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((countyValue: any) => {
        this.counties = countyValue.counties as Array<any>
        this.filteredCounties = countyValue.counties as Array<any>
        this.thirdFormGroup.controls['county'].setValue('')
      });
    });

    this.thirdFormGroup.controls['voivodeship'].valueChanges.pipe(
      startWith(""),
      filter(value => typeof value == "string"),
      map((value: any) => this.voivodeships.filter(voivodeship => voivodeship.voivodeshipName.toLowerCase().startsWith(value.toLowerCase())))
    ).subscribe(value => this.filteredVoivodeships = value)

    this.thirdFormGroup.controls['county'].valueChanges.subscribe(countyValue => {
      this.http.post('http://localhost:8080/api/v1/rate-car-repair-shop/cities', {
        "countyValue": countyValue.id
      }, {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((cityValue: any) => {
        this.cities = cityValue.cities as Array<any>
        this.filteredCities = countyValue.cities as Array<any>
        this.thirdFormGroup.controls['city'].setValue('')
      });
    });

    this.thirdFormGroup.controls['county'].valueChanges.pipe(
      startWith(""),
      filter(value => typeof value == "string"),
      map((value: any) => this.counties.filter(county => county.countyName.toLowerCase().startsWith(value.toLowerCase())))
    ).subscribe(value => this.filteredCounties = value)

    this.thirdFormGroup.controls['city'].valueChanges.subscribe(cityValue => {
      if (!cityValue.id)
        return

      this.http.post('http://localhost:8080/api/v1/car-repair-shop/displayCarRepairShop', {
        "cityId": cityValue.id,
        "brandId": this.firstFormGroup.controls['brand'].value.id,
        "modelId": this.firstFormGroup.controls['model'].value.id,
        "malfunctionId": this.selectedCarMalfunction.id
      }, {
        headers: {
          "Authorization": "Bearer " + token
        }
      }).subscribe((carRepairShopValue: any) => {
        this.carRepairShops = carRepairShopValue.carRepairShops as Array<any>
      });
    });

    this.thirdFormGroup.controls['city'].valueChanges.pipe(
      startWith(""),
      filter(value => typeof value == "string"),
      map((value: any) => this.cities.filter(city => city.cityName.toLowerCase().startsWith(value.toLowerCase())))
    ).subscribe(value => this.filteredCities = value)
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
  displayMalfunctionTextItems(malfunction: any) {
    let text = malfunction.malfunctionName + " - " + malfunction.description as string

    let keyWordsValue = this.secondFormGroup.controls["keyWords"].value as string
    if (!keyWordsValue) {
      return [
        {
          text: text,
          highlight: false,
        }
      ]
    }

    let keywordFrom = text.toLowerCase().indexOf(keyWordsValue.toLowerCase())
    if (keywordFrom == -1) {
      return [
        {
          text: text,
          highlight: false,
        }
      ]
    }

    let keywordTo = keywordFrom + keyWordsValue.length

    let beforeKeyword = text.substring(0, keywordFrom)
    let keyword = text.substring(keywordFrom, keywordTo)
    let afterKeyword = text.substring(keywordTo, text.length)

    return [
      {
        text: beforeKeyword,
        highlight: false,
      },
      {
        text: keyword,
        highlight: true,
      },
      {
        text: afterKeyword,
        highlight: false,
      }
    ]
  }
}

export interface DialogData {
  opinions: Array<any>
  carRepairShopName: any
}

@Component({
  selector: 'dialog-overview-opinions',
  templateUrl: `dialog-overview-opinions.html`,
  standalone: true,
  imports: [NgForOf, MatListModule, MatCardModule],
})
export class DialogOverviewOpinionsDialog {
  constructor(
    public dialogRef: MatDialogRef<DialogOverviewOpinionsDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
