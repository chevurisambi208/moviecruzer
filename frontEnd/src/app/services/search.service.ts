import { Injectable } from "@angular/core";
import { Http } from "@angular/http";
import "rxjs/add/operator/map";
@Injectable()
export class SearchService {
	public sharedSearchResult: object[] = [];
	private searchUrl = "https://api.themoviedb.org/3/search/movie?api_key=" +
		"ef47563a6cb08d01735805b6afd84a9f&language=en-US&page=1&include_adult=false&query=";
	private movieUrl = "https://api.themoviedb.org/3/movie/";
	private discoverMoviesUrl = "https://api.themoviedb.org/3/discover/movie?api_key=" +
		"ef47563a6cb08d01735805b6afd84a9f&language=en-US&sort_by=popularity.desc" +
		"&include_adult=false&include_video=false&page=1";
	private apikey = "?api_key=ef47563a6cb08d01735805b6afd84a9f&language=en-US";
	
	private nowPlayingUrl = "https://api.themoviedb.org/3/movie/" +
		"now_playing?api_key=ef47563a6cb08d01735805b6afd84a9f&language=en-US&page=1";
	constructor(private http: Http) {}
	public find(term: string) {
		const url = `${this.searchUrl}${term}`;
		return this.http
			.get(url)
			.toPromise()
			.then(response => response.json())
			.catch(this.handleError);
	}
	
	public getRecommendedMovies(id: number) {
		const url = this.movieUrl + id + "/recommendations" + this.apikey;
		return this.http.get(url).map(result => result.json());
	}
	public discoverMovies() {
		return this.http.get(this.discoverMoviesUrl).map(result => result.json());
	}
	public getNowPlayingMovies() {
		return this.http.get(this.nowPlayingUrl).map(response => response.json());
	}
	public getMovieById(id: number) {
		const url = this.movieUrl + id + this.apikey;
		return this.http.get(url).map(response => response.json());
	}
	public getCast(id: number) {
		const url = this.movieUrl + id + "/credits" + this.apikey;
		return this.http.get(url).map(result => result.json());
	}
	private handleError(error: any): Promise<any> {
		return Promise.reject(error.message || error);
	}
}