import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalculateResultsComponent } from './calculate-results.component';

describe('CalculateResultsComponent', () => {
  let component: CalculateResultsComponent;
  let fixture: ComponentFixture<CalculateResultsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CalculateResultsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CalculateResultsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
