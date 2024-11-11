import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RazaoComponent } from './razao.component';

describe('RazaoComponent', () => {
  let component: RazaoComponent;
  let fixture: ComponentFixture<RazaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RazaoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RazaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
