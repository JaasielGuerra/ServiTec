import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultainventarioComponent } from './consultainventario.component';

describe('ConsultainventarioComponent', () => {
  let component: ConsultainventarioComponent;
  let fixture: ComponentFixture<ConsultainventarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultainventarioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultainventarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
