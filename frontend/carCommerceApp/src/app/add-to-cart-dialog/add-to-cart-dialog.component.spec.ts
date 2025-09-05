import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddToCartDialogComponent } from './add-to-cart-dialog.component';

describe('AddToCartDialogComponent', () => {
  let component: AddToCartDialogComponent;
  let fixture: ComponentFixture<AddToCartDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [AddToCartDialogComponent]
    });
    fixture = TestBed.createComponent(AddToCartDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
