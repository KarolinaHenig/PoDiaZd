import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarRepairShopSelectionComponent } from './car-repair-shop-selection.component';

describe('CarRepairShopSelectionComponent', () => {
  let component: CarRepairShopSelectionComponent;
  let fixture: ComponentFixture<CarRepairShopSelectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarRepairShopSelectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CarRepairShopSelectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
