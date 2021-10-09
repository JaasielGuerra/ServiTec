import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdenesservicioComponent } from './ordenesservicio.component';

describe('OrdenesservicioComponent', () => {
  let component: OrdenesservicioComponent;
  let fixture: ComponentFixture<OrdenesservicioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrdenesservicioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrdenesservicioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
