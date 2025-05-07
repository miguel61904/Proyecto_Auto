import { DialogRef } from '@angular/cdk/dialog';
import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrl: './modal.component.css'
})
export class ModalComponent {
  constructor(private ruta:Router, public dialogRef:MatDialogRef<ModalComponent>){
    
  }

  cerrarModal(){
    this.ruta.navigate(["pantalla"]);
    this.dialogRef.close();
  }
  guardarModal(){
    this.ruta.navigate(["pantalla"]);
    alert("Se ha guardado correctamente");
    this.dialogRef.close();
  }

}

