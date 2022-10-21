import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FindCarRepairShopComponent } from './find-car-repair-shop.component';

describe('FindCarRepairShopComponent', () => {
  let component: FindCarRepairShopComponent;
  let fixture: ComponentFixture<FindCarRepairShopComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FindCarRepairShopComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FindCarRepairShopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
