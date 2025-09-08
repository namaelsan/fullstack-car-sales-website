import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-delete-dialog',
  standalone: true,
  imports: [CommonModule, MatDialogModule],
  templateUrl: './delete-dialog.component.html',
  styleUrls: ['./delete-dialog.component.css']
})
export class DeleteDialogComponent {

    constructor (public dialogRef: MatDialogRef<DeleteDialogComponent>) {}
  

  onYes() {
    this.dialogRef.close("yes");
  }

  onNo() {
    this.dialogRef.close();
  }

}
