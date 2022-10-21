import { Component, Input, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
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
  constructor(private _formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.firstFormGroup = this._formBuilder.group({
      brand: ['', Validators.required],
      model: ['', Validators.required],
      generation: ['', Validators.required],
      bodyType: ['', Validators.required],
      typeOfFuel: ['', Validators.required],
      engineCapacity: ['', Validators.required],
      yearOfProduction: ['', Validators.required],
    });
    this.secondFormGroup = this._formBuilder.group({
      category: ['', Validators.required],
      mulfunction: ['', Validators.required],
    });
    this.thirdFormGroup = this._formBuilder.group({
      zipCode: ['', Validators.required],
      secondCtrl: ['', Validators.required],
    });
  }
  
}
