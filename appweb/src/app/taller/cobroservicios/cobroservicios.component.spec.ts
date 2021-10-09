import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CobroserviciosComponent } from './cobroservicios.component';

describe('CobroserviciosComponent', () => {
  let component: CobroserviciosComponent;
  let fixture: ComponentFixture<CobroserviciosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CobroserviciosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CobroserviciosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
