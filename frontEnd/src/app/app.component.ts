import { Component, ViewChild } from "@angular/core";
import { Router, RouterLink } from "@angular/router";
import { SearchService } from "./services/search.service";
import { LoginComponent } from "./login/login.component";
@Component({
	selector: "app-root",
	templateUrl: "./app.component.html",
	styleUrls: ["./app.component.css"]
})
export class AppComponent {
	public title = "Movie Cruiser";
	public isUserLoggedIn = false;
	public searchQuery: string;
	constructor(private searchService: SearchService, private router: Router) {
		
	}
	ngOnInit(){
		this.isUserLoggedIn = localStorage.getItem("jwt_token") === null ? false : true;
	}
	public navigateTo() {
		const link = ["/search-results"];
		this.router.navigate(link);
	}
	public search() {
		this.searchService.find(this.searchQuery).then(response => {
			this.searchService.sharedSearchResult = response.results;
			this.navigateTo();
		});
	}
}