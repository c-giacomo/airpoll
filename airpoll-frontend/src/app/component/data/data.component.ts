import { NULL_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { Paging } from 'src/app/configuration/paging';
import { AirpollData } from 'src/app/model/airpollData';
import { City } from 'src/app/model/city';
import { Country } from 'src/app/model/country';
import { AirpollService } from 'src/app/services/airpoll.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.css']
})
export class DataComponent implements OnInit {

  constructor(
    private airpollService: AirpollService,
    private spinner: NgxSpinnerService
  ) { }

  airpoll: AirpollData[];
  country: Country[];
  city: City[];

  selectedCity: City[];

  filteredCountry: Country = null;
  filteredCity: City = null;

  paging: Paging = new Paging();
  countryCode: Number;
  cityCode: Number;
  
  ngOnInit(): void {
    this.spinner.show();
    this.airpollService.list(this.countryCode, this.cityCode, this.paging.page).subscribe(success => {
      this.airpoll = success;
      this.spinner.hide();
    });
    this.airpollService.countryList().subscribe(success => {
      this.country = success;
    });
    this.airpollService.cityList().subscribe(success => {
      this.city = success;
      this.selectedCity = success;
    });
  }

  onScroll() {
    console.log('scrolled');
    this.paging.increase();
    this.loadNextData();
  }

  loadNextData() {
    this.airpollService.list(this.countryCode, this.cityCode, this.paging.page).subscribe(success => {
      if (environment.mode === 'projection')
        this.airpoll.push(...success);
      else
        this.airpoll = success;
    });
  }

  loadFilteredData() {
    this.spinner.show();
    this.airpollService.list(this.countryCode, this.cityCode, this.paging.page).subscribe(success => {
      this.airpoll = success;
      this.spinner.hide();
    });
  }

  country_onChange() {
    this.loadFilteredCity(this.filteredCountry.id);
    this.cityCode = null;
    this.paging.reset();
    this.countryCode = this.filteredCountry.id;
    this.loadFilteredData();
  }

  city_onChange() {
    this.cityCode = this.filteredCity.id;
    this.paging.reset();
    this.loadFilteredData();
  }

  loadFilteredCity(countryCode: Number) {
    this.selectedCity = [];
    this.city.forEach(x => {
      if (x.id_country === countryCode) {
        this.selectedCity.push(x);
      }
    });
  }

}
