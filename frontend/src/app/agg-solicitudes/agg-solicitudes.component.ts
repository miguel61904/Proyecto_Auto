import { Component, Renderer2 } from '@angular/core';
import { datosVacante } from '../models/solicitudes.interface';
import { SolicitudesService } from '../services/solicitudes.service';
import { NgModel } from '@angular/forms';
 
@Component({
  selector: 'app-agg-solicitudes',
  templateUrl: './agg-solicitudes.component.html',
  styleUrl: './agg-solicitudes.component.css'
})
export class AggSolicitudesComponent {
  index = 1;
  nuevaHabilidad = "";
  nuevaVacante: datosVacante =({
    estado: 'Aceptado',
    evaluacion: [
      {
        id: 0,
        nombre: ''
      }
    ],
    evc: '',
    evento: '',
    fechaSolicitud: '',
    habilidades: [
      {
        id: this.index,
        nombre: this.nuevaHabilidad
      }
    ],
    medellin: false,
    observaciones: '',
    perfil: '',
    rgs: '',
    tipoSolicitud: 'Nuevo',
    fechaCierre: '2024-12-31',
    fechaPostulado: '2024-12-31',
    fechaPropuesta: '2024-12-31',
    fechaReal: '2024-12-31',
    fechaResultado: '2024-12-31',
  })
  constructor(private solicitudes: SolicitudesService, private render:Renderer2){
  }
 
  agregarV(){
    this.solicitudes.postData(this.nuevaVacante).subscribe(
      resp =>{
        console.log("respuesta", resp)
      },
      error =>{
        console.log('Error', error)
      }
    )}
    aggChip(){
      const espacio = document.getElementsByClassName("div-habilidades") [0];
      if(espacio){
        let idChip = this.index.toString();
        const div = this.render.createElement("div");
 
        const texto = this.render.createText(this.nuevaHabilidad);
        const boton = this.render.createElement("button-icon");
        this.render.setAttribute(div, "class", "chip")
        this.render.setAttribute(boton, "id", "btn-icon")
        this.render.setAttribute(div, "id", idChip)
 
        this.render.appendChild(div, texto);
        this.render.appendChild(div, boton);
        this.render.appendChild(espacio, div);
       
        this.index += 1
        console.log(this.index);
       
 
        boton.addEventListener('click', () => {
          const close = div.parentNode;
          close.removeChild(div);
 
        });
       
       
      }
    }
   
}
 
 