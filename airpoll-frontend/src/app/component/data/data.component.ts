import { Component, OnInit } from '@angular/core';
import { AirpollData } from 'src/app/model/airpollData';
import { AirpollService } from 'src/app/services/airpoll-service.service';

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.css']
})
export class DataComponent implements OnInit {

  constructor(
    private airpollService: AirpollService
  ) { }

  airpoll: AirpollData[];

  ngOnInit(): void {
    this.airpollService.list().subscribe(success => {
      this.airpoll = success;
    });
  }

}
