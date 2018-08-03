import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { SearchService } from "../services/search.service";
@Component({
	selector: "app-discover-movies",
	templateUrl: "./discover-movies.component.html",
	styleUrls: ["./discover-movies.component.css"]
})
export class DiscoverMoviesComponent implements OnInit {
  public discMovies: object[] = [];
  
	constructor(private searchService: SearchService, private router: Router) {}
  
  public ngOnInit() {
		this.searchService.discoverMovies().subscribe(response => {
			this.discMovies = response.results;
		});
	}
	public goToDetail(id: number) {
		const link = ["/movie", id];
		this.router.navigate(link);
	}
}