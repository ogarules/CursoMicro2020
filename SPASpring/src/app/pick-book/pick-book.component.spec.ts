import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PickBookComponent } from './pick-book.component';

describe('PickBookComponent', () => {
  let component: PickBookComponent;
  let fixture: ComponentFixture<PickBookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PickBookComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PickBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
