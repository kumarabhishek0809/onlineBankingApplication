import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppingRecipeComponent } from './shopping-recipe.component';

describe('ShoppingRecipeComponent', () => {
  let component: ShoppingRecipeComponent;
  let fixture: ComponentFixture<ShoppingRecipeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShoppingRecipeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShoppingRecipeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
