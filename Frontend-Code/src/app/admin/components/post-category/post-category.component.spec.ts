import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PasoCategoryComponent } from './paso-category.component';

describe('PasoCategoryComponent', () => {
  let component: PasoCategoryComponent;
  let fixture: ComponentFixture<PasoCategoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PasoCategoryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PasoCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
