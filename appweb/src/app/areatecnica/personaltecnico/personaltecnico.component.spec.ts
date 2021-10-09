import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonaltecnicoComponent } from './personaltecnico.component';

describe('PersonaltecnicoComponent', () => {
  let component: PersonaltecnicoComponent;
  let fixture: ComponentFixture<PersonaltecnicoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PersonaltecnicoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonaltecnicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
