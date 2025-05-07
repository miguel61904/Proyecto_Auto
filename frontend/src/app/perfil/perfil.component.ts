import { Component,ElementRef, OnInit, Renderer2,ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { UsuariosService } from '../services/usuarios.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.css'
})
export class PerfilComponent {
  condicion = false;
  @ViewChild('sectp')sectpInTS!:ElementRef;
  constructor(private rutap:Router, private prueba:Renderer2, private usuarios:UsuariosService){
  }

}
