import { NULL_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { AirpollData } from 'src/app/model/airpollData';
import { City } from 'src/app/model/city';
import { Country } from 'src/app/model/country';
import { AirpollService } from 'src/app/services/airpoll.service';

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

  page: Number = 1;
  countryCode: Number;
  cityCode: Number;
  
  ngOnInit(): void {
    this.spinner.show();
    this.airpollService.list(this.countryCode, this.cityCode, this.page).subscribe(success => {
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
    // this.page++;
    // this.loadNextData(this.page);
  }

  loadNextData(page: number) {
    this.airpollService.list(this.countryCode, this.cityCode, page).subscribe(success => {
      this.airpoll.push(...success);
    });
  }

  country_onChange() {
    this.loadFilteredCity(this.filteredCountry.id);
  }

  city_onChange() {
    console.log(this.filteredCity);
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
