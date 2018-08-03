import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SearchService } from '../services/search.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public nowPlayingMovies: object[] = [];

  constructor(private searchService: SearchService, private router: Router) {}
  public goToDetail(id: number) {
    const link = ['/movie', id];
    this.router.navigate(link);
  }

  public ngOnInit() {
    this.searchService.getNowPlayingMovies().subscribe(response => {
      this.nowPlayingMovies = response.results.splice(0, 3);
    });
  }
}