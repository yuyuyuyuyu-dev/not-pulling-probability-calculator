import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyAppbarComponent } from './my-appbar.component';

describe('MyAppbarComponent', () => {
  let component: MyAppbarComponent;
  let fixture: ComponentFixture<MyAppbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MyAppbarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MyAppbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
