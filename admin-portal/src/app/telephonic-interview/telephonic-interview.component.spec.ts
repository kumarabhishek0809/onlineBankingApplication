import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TelephonicInterviewComponent } from './telephonic-interview.component';

describe('TelephonicInterviewComponent', () => {
  let component: TelephonicInterviewComponent;
  let fixture: ComponentFixture<TelephonicInterviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TelephonicInterviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TelephonicInterviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
