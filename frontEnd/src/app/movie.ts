export class Movie {
	public id: number;
	public name: string;
	public adult: false;
	public backdropPath: string;
	public belongsToCollection: {
		id: number;
		name: string;
		posterPath: string;
		backdropPath: string;
	};
	public budget: number;
	public genres: [
		{
			id: number;
			name: string;
		}
	];
	public homepage: string;
	public imdbId: string;
	public originalLanguage: string;
	public originalTitle: string;
	public overview: string;
	public popularity: number;
	public posterPath: string;
	public productionCompanies: [
		{
			name: string;
			id: number;
		}
	];
	public productionCountries: [
		{
			iso_3166_1: string;
			name: string;
		}
	];
	public releaseDate: Date;
	public revenue: number;
	public runtime: number;
	public spokenLanguages: [
		{
			iso_639_1: string;
			name: string;
		}
	];
	public status: string;
	public tagline: string;
	public title: string;
	public video: boolean;
	public voteAverage: number;
	public voteCount: number;
}