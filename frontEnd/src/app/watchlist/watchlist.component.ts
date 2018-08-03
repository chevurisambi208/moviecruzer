import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { Observable } from "rxjs/Rx";
import { MoviesService } from "../services/movie.service";
import { Movie } from "./../movie";
@Component({
	selector: "app-watchlist",
	templateUrl: "./watchlist.component.html",
	styleUrls: ["./watchlist.component.css"]
})
export class WatchlistComponent implements OnInit {
	public watchList: any[];
	constructor(private moviesService: MoviesService, private router: Router) {}
	public ngOnInit() {
		this.moviesService.getWatchList().then(res => {
			if (res) {
				this.watchList = res;
			}
		});
	}
	public refreshMovies() {
		this.moviesService.getWatchList().then(res => {
			if (res) {
				this.watchList = res;
			}
		});
	}
	public updateComments(id: number, comments: string) {
		this.moviesService.updateMovieComments(id, comments);
	}
	public goToDetail(id: number) {
		const link = ["/movie", id];
		this.router.navigate(link);
	}
	public removeFromWatchList(id: number) {
		this.moviesService
			.removeMovieFromWatchList(id)
			.then(() => this.refreshMovies());
	}
}