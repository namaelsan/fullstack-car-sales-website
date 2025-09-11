import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrandsPageComponent } from './brands-page.component';

describe('BrandsPageComponent', () => {
  let component: BrandsPageComponent;
  let fixture: ComponentFixture<BrandsPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [BrandsPageComponent]
    });
    fixture = TestBed.createComponent(BrandsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
