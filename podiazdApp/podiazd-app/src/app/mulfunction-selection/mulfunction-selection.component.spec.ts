import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MulfunctionSelectionComponent } from './mulfunction-selection.component';

describe('MulfunctionSelectionComponent', () => {
  let component: MulfunctionSelectionComponent;
  let fixture: ComponentFixture<MulfunctionSelectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MulfunctionSelectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MulfunctionSelectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
