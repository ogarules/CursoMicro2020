import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditorialsListComponent } from './editorials-list.component';

describe('EditorialsListComponent', () => {
  let component: EditorialsListComponent;
  let fixture: ComponentFixture<EditorialsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditorialsListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditorialsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
