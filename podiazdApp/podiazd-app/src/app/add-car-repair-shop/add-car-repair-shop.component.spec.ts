import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCarRepairShopComponent } from './add-car-repair-shop.component';

describe('AddCarRepairShopComponent', () => {
  let component: AddCarRepairShopComponent;
  let fixture: ComponentFixture<AddCarRepairShopComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddCarRepairShopComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddCarRepairShopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
