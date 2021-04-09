import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { AirpollData } from '../model/airpollData';

@Injectable({
  providedIn: 'root'
})
export class AirpollService {

  constructor(private http: HttpClient) { }

  list(): Observable<AirpollData[]> {
    return this.http.get<AirpollData[]>('data');
  }
}
