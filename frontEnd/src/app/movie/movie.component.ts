import { Location } from "@angular/common";
import { Component, Input, OnInit } from "@angular/core";
import { ActivatedRoute, ParamMap, Router } from "@angular/router";
import "rxjs/add/operator/switchMap";
import { MoviesService } from "../services/movie.service";
import { SearchService } from "../services/search.service";
import { Movie } from "./../movie";
@Component({
	selector: "app-movie",
	templateUrl: "./movie.component.html",
	styleUrls: ["./movie.component.css"]
})
export class MovieComponent implements OnInit {
	public movie: Movie;
	public recommended: object[] = [];
	public cast: object[] = [];
	public inWatchList = true;
	constructor(
		private router: Router,
		private moviesService: MoviesService,
		private searchService: SearchService,
		private route: ActivatedRoute,
		private location: Location
	) {}
	public goToDetail(id: number) {
		const link = ["/movie", id];
		this.router.navigate(link);
	}
	public addToWatchList(
		id: number,
		name: string,
		comments: string,
		posterPath: string,
		releaseDate: Date,
		voteAverage: number,
		voteCount: number
	) {
		this.moviesService.addToWatchListMovie(
			id,
			name,
			comments,
			posterPath,
			releaseDate,
			voteAverage,
			voteCount
		);
		this.inWatchList = true;
	}
	public updateComments(id: number, comments: string) {
		this.moviesService.updateMovieComments(id, comments);
	}
	public ngOnInit() {
		this.route.paramMap
			.switchMap((params: ParamMap) =>
				this.searchService.getMovieById(+params.get("id"))
			)
			.subscribe(movie => (this.movie = movie));
		this.route.params
			.map(params => params.id)
			.switchMap(id => this.searchService.getRecommendedMovies(id))
			.subscribe(
				response => (this.recommended = response.results.splice(0, 6))
			);
		this.route.params
			.map(params => params.id)
			.switchMap(id => this.searchService.getCast(id))
			.subscribe(response => (this.cast = response.cast.splice(0, 3)));
		this.route.params
			.map(params => params.id)
			.switchMap(id => this.moviesService.checkWatchList(id))
			.subscribe(response => {
				if (response === undefined) {
					this.inWatchList = false;
				}
			});
	}
	public removeFromWatchList(id: number) {
		this.moviesService
			.removeMovieFromWatchList(id)
			.then(() => (this.inWatchList = false));
	}
}