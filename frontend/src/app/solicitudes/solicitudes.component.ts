import { Component, OnInit, ViewChild,ElementRef, Input, AfterViewInit, Renderer2 } from '@angular/core';
import { SolicitudesService } from '../services/solicitudes.service';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { param } from 'jquery';
import { datosVacante } from '../models/solicitudes.interface';

@Component({
  selector: 'app-solicitudes',
  templateUrl: './solicitudes.component.html',
  styleUrl: './solicitudes.component.css'
})
export class SolicitudesComponent implements OnInit {
  @ViewChild('select')select!:ElementRef;
  @ViewChild('aggEvaluacion')aggEvaluacionInTS!: ElementRef;
  evaluacion = "";
  constructor(private solicitudes: SolicitudesService, private route: Router, 
    private url: ActivatedRoute, private render: Renderer2){

  }
  datosV: datosVacante[] = [];
  id2 = 0;
  idVacante = ""
  id: String | null = null;
  vacante: any={
  };
  ngOnInit(): void {
      this.solicitudes.getSolicitudes().subscribe(resp =>{
        this.vacante = resp
        console.log(this.vacante[0].evento)
        const idRuta: String[] =[]
        this.url.paramMap.subscribe(params =>{
          this.id = params.get('id')
        })
        this.id2 = Number(this.id)
        this.solicitudes.getDataVacante().subscribe({
        next: (datosv: datosVacante[])=>{
          this.datosV = datosv;
           this.id2 = Number(this.id)
          console.log(datosv[this.id2 -1])
          }
        })
        for(let i = 0; i <= this.vacante.length; i++){
          idRuta[i] = this.vacante[i].id.toString()
          console.log(idRuta[i])
          if (this.id == idRuta[i]){
            this.route.navigate(['solitudes/', idRuta])
            console.log(this.id)
            break
          }else{
            console.log("ingrese una url correcta")
            this.route.navigate(['pantalla'])
          }
        }if(this.vacante[this.id2 -1].habilidades.length > 0){
          const div = document.getElementsByClassName('aggEvaluacion')[0];
          console.log(this.id2)
          for(let i = 0; i <= this.vacante[this.id2 -1].habilidades.length; i++){
            const box = this.render.createElement("div");
            const boton = this.render.createElement('button');
            const text = this.render.createText(this.vacante[this.id2 -1].habilidades[i].nombre)
            this.render.setStyle(boton, "color","black");

            this.render.setAttribute(boton, "id", "btn_delete");
            this.render.setAttribute(box, "id", "box_chip");

            this.render.appendChild(box, text);
            this.render.appendChild(box,boton);
            this.render.appendChild(div, box);
          }
        }else{
          console.log("¿Que haga acá?");
          
        }
      })  
  }
  cerrarSoli(){
    this.route.navigate(['pantalla']);
  }
  aggEvaluacion(){

  }
}
