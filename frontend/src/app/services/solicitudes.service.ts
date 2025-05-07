import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { datosVacante } from '../models/solicitudes.interface';

@Injectable({
  providedIn: 'root'
})
export class SolicitudesService {

  private apiSolicitudes = "http://localhost:8080/api/solicitudes/solicitudes";

  constructor(private http:HttpClient) {
  }
  getSolicitudes(): Observable<any>{
    return this.http.get<any>(this.apiSolicitudes);
  }

  getDataVacante(): Observable<datosVacante[]>{
    return this.http.get<datosVacante[]>(this.apiSolicitudes)
  }

  postData(data:datosVacante): Observable<datosVacante>{
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post<datosVacante>(this.apiSolicitudes, data)
  }
}
