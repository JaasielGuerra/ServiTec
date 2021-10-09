import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CatalogosordenesComponent } from './catalogosordenes.component';

describe('CatalogosordenesComponent', () => {
  let component: CatalogosordenesComponent;
  let fixture: ComponentFixture<CatalogosordenesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CatalogosordenesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CatalogosordenesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
