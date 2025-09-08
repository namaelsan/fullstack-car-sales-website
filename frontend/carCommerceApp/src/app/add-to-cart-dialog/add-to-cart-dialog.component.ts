import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-add-to-cart-dialog',
  standalone: true,
  imports: [CommonModule, MatDialogModule],
  templateUrl: './add-to-cart-dialog.component.html',
  styleUrls: ['./add-to-cart-dialog.component.css']
})
export class AddToCartDialogComponent {

  constructor(public dialogRef: MatDialogRef<AddToCartDialogComponent>) {}

  onYes() {
    this.dialogRef.close("yes");
  }

  onNo() {
    this.dialogRef.close();
  }


}
