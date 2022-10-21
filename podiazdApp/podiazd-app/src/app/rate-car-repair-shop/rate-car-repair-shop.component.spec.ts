import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RateCarRepairShopComponent } from './rate-car-repair-shop.component';

describe('RateCarRepairShopComponent', () => {
  let component: RateCarRepairShopComponent;
  let fixture: ComponentFixture<RateCarRepairShopComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RateCarRepairShopComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RateCarRepairShopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
