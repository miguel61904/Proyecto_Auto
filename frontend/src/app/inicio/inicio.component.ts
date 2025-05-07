import { Component, OnInit} from '@angular/core';
import { Router } from "@angular/router";
import { UsuariosService } from '../services/usuarios.service';
import { log } from 'console';
import { FormBuilder, FormGroup } from '@angular/forms';
import { error } from 'jquery';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css',
})

export class InicioComponent{
  authForm: FormGroup;
  constructor(private router:Router, private usuario:UsuariosService, private fb: FormBuilder){
    this.authForm = this.fb.group({
      email:[''],
      password:['']
    })
  }
  onSubmit(): void{
    const formData = this.authForm.value;
    this.usuario.postUsuarios(formData).subscribe(
      resp =>{
        console.log("respuesta", resp)
        this.router.navigate(['/pantalla'])
      },
      error =>{
        console.log('Error', error)
      }
    )
  } 
}


