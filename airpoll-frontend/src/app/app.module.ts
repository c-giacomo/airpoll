import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './component/home/home.component';
import { WhoamiComponent } from './component/whoami/whoami.component';
import { DataComponent } from './component/data/data.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { CookieInterceptor } from 'src/interceptors/interceptors';
import { AirpollService } from './services/airpoll.service';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { NgxSpinnerModule } from 'ngx-spinner';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    WhoamiComponent,
    DataComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    AppRoutingModule,
    InfiniteScrollModule,
    NgxSpinnerModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: CookieInterceptor,
      multi: true
    },
    AirpollService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
