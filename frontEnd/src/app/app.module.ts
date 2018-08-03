import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { HttpInterceptor, HTTP_INTERCEPTORS} from "@angular/common/http";

import { AuthService } from './services/auth.service';
import { AlertService } from './services/alert.service';
import { MoviesService } from './services/movie.service';
import { SearchService } from './services/search.service';

import { AlertComponent } from './alert/alert.component';
import { AppComponent } from './app.component';
import { DiscoverMoviesComponent } from './discover-movies/discover-movies.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { MovieComponent } from './movie/movie.component';
import { RegisterComponent } from './register/register.component';
import { SearchResultsComponent } from './search-results/search-results.component';
import { SearchComponent } from './search/search.component';
import { WatchlistComponent } from './watchlist/watchlist.component';

import { AuthGuard } from './services/auth-guard.service';
import {MovieHttpClient} from './services/moviehttpclient.service';

  

@NgModule({
  declarations: [
    AppComponent,
  SearchResultsComponent,
  SearchComponent,
  MovieComponent,
  WatchlistComponent,
  HomeComponent,
  DiscoverMoviesComponent,
    LoginComponent,
    RegisterComponent,
    LogoutComponent,
    AlertComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    FormsModule,
    RouterModule
  ],
  providers: [MoviesService, SearchService, AuthGuard, AlertService,AuthService,MovieHttpClient],
  bootstrap: [AppComponent]
})
export class AppModule {}