import { Routes } from '@angular/router';
import { SolicitudesComponent } from './solicitudes/solicitudes.component';
import { InicioComponent } from './inicio/inicio.component';

export const routes: Routes = [
  { path: 'solicitudes', component: SolicitudesComponent },
  { path: '', component: InicioComponent },
];
