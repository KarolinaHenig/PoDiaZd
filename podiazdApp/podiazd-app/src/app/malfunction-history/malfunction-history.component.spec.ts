import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MalfunctionHistoryComponent } from './malfunction-history.component';

describe('MalfunctionHistoryComponent', () => {
  let component: MalfunctionHistoryComponent;
  let fixture: ComponentFixture<MalfunctionHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MalfunctionHistoryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MalfunctionHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
