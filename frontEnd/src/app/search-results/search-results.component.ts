import { Component, DoCheck, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SearchService } from '../services/search.service';
@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit, DoCheck {
  public searchResults: object[] = [];
  constructor(private searchService: SearchService, private router: Router) {}
  public ngOnInit() {
    this.searchResults = this.searchService.sharedSearchResult;
  }
  public ngDoCheck() {
    if (this.searchResults !== this.searchService.sharedSearchResult) {
      this.searchResults = this.searchService.sharedSearchResult;
    }
  }
  public goToDetail(id: number) {
    const link = ['/movie', id];
    this.router.navigate(link);
  }
}