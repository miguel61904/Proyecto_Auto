import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { usuariosData } from '../models/usuarios.interface';

interface AuthData{
  email:string;
  passwor:string;
}

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  constructor(private http:HttpClient) { }

  private apiUsuarios = "http://localhost:8080/api/auth/login";

  postUsuarios(data: AuthData): Observable<any>{
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post<any>(this.apiUsuarios, data, {headers})
  }

}
