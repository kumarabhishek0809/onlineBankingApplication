import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SavingstransactionComponent } from './savingstransaction.component';

describe('SavingstransactionComponent', () => {
  let component: SavingstransactionComponent;
  let fixture: ComponentFixture<SavingstransactionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SavingstransactionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SavingstransactionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
