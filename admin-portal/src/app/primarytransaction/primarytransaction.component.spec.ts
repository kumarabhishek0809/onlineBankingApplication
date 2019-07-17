import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrimarytransactionComponent } from './primarytransaction.component';

describe('PrimarytransactionComponent', () => {
  let component: PrimarytransactionComponent;
  let fixture: ComponentFixture<PrimarytransactionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrimarytransactionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrimarytransactionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
