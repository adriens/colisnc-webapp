import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IColis } from './colis.model';

@Injectable({ providedIn: 'root' })
export class ColisService {

  constructor(private http: HttpClient) { }

  load(id: string): Observable<IColis[]> {
    return this.http.get<IColis[]>(`api/colis/${id}`);
  }
}
