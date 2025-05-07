import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InicioComponent } from './inicio/inicio.component';
import { PrincipalComponent } from './principal/principal.component';
import { RouterModule, Routes } from '@angular/router';
import { Router } from 'express';
import { PerfilComponent } from './perfil/perfil.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { ModalComponent } from './modal/modal.component';
import { FormsModule } from '@angular/forms';
import { DataTablesModule } from "angular-datatables";
import {NgChartsModule} from 'ng2-charts'
import {AnalycticsComponent} from './analyctics/analyctics.component';
import { SolicitudesComponent } from './solicitudes/solicitudes.component'
import { ReactiveFormsModule } from '@angular/forms';
import { AggSolicitudesComponent } from './agg-solicitudes/agg-solicitudes.component';

const appRutas: Routes = [
  { path: "pantalla" , component:PrincipalComponent},
  { path: "" , component:InicioComponent},
  { path: "perfil" , component:PerfilComponent},
  { path: "solicitudes/:id", component:SolicitudesComponent}
];
@NgModule({
  declarations: [
    AppComponent,
    InicioComponent,
    PrincipalComponent,
    PerfilComponent,
    ModalComponent,
    AnalycticsComponent,
    AggSolicitudesComponent,
    SolicitudesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(appRutas),
    MatDialogModule,
    MatButtonModule,
    FormsModule,
    DataTablesModule,
    NgChartsModule,
    ReactiveFormsModule
  ],
  providers: [
    provideClientHydration(),
    provideAnimationsAsync(),
    provideHttpClient(withFetch())
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }