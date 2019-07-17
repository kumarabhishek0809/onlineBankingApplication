import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkappointmentComponent } from './workappointment.component';

describe('WorkappointmentComponent', () => {
  let component: WorkappointmentComponent;
  let fixture: ComponentFixture<WorkappointmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkappointmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkappointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
