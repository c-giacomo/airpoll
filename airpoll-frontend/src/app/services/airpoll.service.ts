import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Observable } from 'rxjs';
import { AirpollData } from '../model/airpollData';
import { Country } from '../model/country';
import { City } from '../model/city';

@Injectable({
  providedIn: 'root'
})
export class AirpollService {

  constructor(private http: HttpClient) { }

  list(countryId: Number, cityId: Number, page: Number): Observable<AirpollData[]> {
    let params = new HttpParams();
    params = params.append('countryId', !countryId ? '' : countryId.toString());
    params = params.append('cityId', !cityId ? '' : cityId.toString());
    params = params.append('page', page.toString());
    return this.http.get<AirpollData[]>('data', { params: params });
  }

  countryList(): Observable<Country[]> {
    return this.http.get<Country[]>('country');
  }

  cityList(): Observable<City[]> {
    return this.http.get<City[]>('city');
  }
}
