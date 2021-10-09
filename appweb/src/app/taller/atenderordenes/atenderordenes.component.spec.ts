import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtenderordenesComponent } from './atenderordenes.component';

describe('AtenderordenesComponent', () => {
  let component: AtenderordenesComponent;
  let fixture: ComponentFixture<AtenderordenesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtenderordenesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AtenderordenesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
