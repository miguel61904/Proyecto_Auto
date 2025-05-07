import { Component, ElementRef, OnInit, Renderer2, TemplateRef, ViewChild, AfterViewInit, OnDestroy, ComponentFactoryResolver } from '@angular/core';
import { Route } from '@angular/router';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ModalComponent } from "../modal/modal.component";
import { Config } from 'datatables.net';
import { SolicitudesService } from '../services/solicitudes.service';
import { data, event } from 'jquery';
import { Subject } from 'rxjs';
import { AggSolicitudesComponent } from '../agg-solicitudes/agg-solicitudes.component';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrl: './principal.component.css',
})
export class PrincipalComponent implements OnInit, AfterViewInit{
  condicion = false;
  abierta = false;
  @ViewChild('costado')costadoInTS!:ElementRef;
  @ViewChild('icono')iconoInTS!:ElementRef;
  dtOptions: Config = {};
  dtTrigger = new Subject<Config>();
  constructor(private ruta:Router, public dialog:MatDialog, private prueba:Renderer2, private solicitud:SolicitudesService){
  }


  ngOnInit(): void {
    this.dtOptions = {
      ajax: (dataTablesParameters: any, callback) =>{
        this.solicitud.getSolicitudes().subscribe((resp) =>{ console.log(resp);
          callback({
            data: resp
          })
          console.log(resp);
        });
      },
      
      columns: [{
        title: 'Estado',
        data: 'estado'
      }, {
        title: 'Vacante',
        data: 'perfil'
      },{
        title: 'Fecha',
        data: 'fechaSolicitud'
      },{
        title: 'Fecha cierre',
        data: 'fechaCierre'
      },{
        title: 'ID',
        data: 'evento'
      },
        {
          title: 'Actions',
          data: null,
          orderable:false,
          render:(data,type,row) =>{
            return `<button class="btn btn-primary action-btn" data-id="${row.id}">Ver mas</button>`
          }
      }
    ]};
  }

  
  ngAfterViewInit(): void{
    $('table').on('click', '.action-btn', (event) =>{
      const id = $(event.currentTarget).data('id');
      this.accion(id)
    })
  }

  accion(id:any):void{
    this.ruta.navigate(['solicitudes/', id])
  }
  verDetalle(id: string){
    this.ruta.navigate(['solicitudes/', id])
    alert("buenas noches")
  }
    perfil(){
      this.ruta.navigate(['perfil']);
  }
    ayuda(){
      this.dialog.open(ModalComponent);
    }
    aggSolicitudes(){
      this.dialog.open(AggSolicitudesComponent)
    }
    aside(){
      this.abierta = !this.abierta;
      const costado = this.costadoInTS.nativeElement;
      const icono = this.iconoInTS.nativeElement;
      if(this.abierta){
        this.prueba.addClass(costado, 'abierto')
        this.prueba.setStyle(costado, "display", "inline");
        this.prueba.setStyle(icono, "display", "none");
        this.condicion = true;
        document.body.style.overflow = this.abierta ? 'hidden' : 'auto';
      }else{
        this.prueba.removeClass(costado, 'abierto')
        this.prueba.setStyle(costado, "display", "inline");
        this.prueba.setStyle(icono, "display", "inline");
        this.condicion = false;
        document.body.style.overflow = 'auto';
      }
        
    }
    cerrar(){
      this.aside()
    }
    salir(){
      this.ruta.navigate(['']);
    }
}
