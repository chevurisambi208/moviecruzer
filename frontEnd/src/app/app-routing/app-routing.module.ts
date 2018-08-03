import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";


import { AuthGuard } from "./../services/auth-guard.service";
import { DiscoverMoviesComponent } from "./../discover-movies/discover-movies.component";
import { HomeComponent } from "./../home/home.component";
import { LoginComponent } from "./../login/login.component";
import { LogoutComponent } from "./../logout/logout.component";
import { MovieComponent } from "./../movie/movie.component";
import { RegisterComponent } from "./../register/register.component";
import { SearchResultsComponent } from "./../search-results/search-results.component";
import { SearchComponent } from "./../search/search.component";
import { WatchlistComponent } from "./../watchlist/watchlist.component";
import { AlertComponent } from "../alert/alert.component";

const routes: Routes = [
	{path: "search-results",component: SearchResultsComponent,canActivate: [AuthGuard]},
	{ path: "search", component: SearchComponent, canActivate: [AuthGuard] },
	{ path: "movie/:id", component: MovieComponent, canActivate: [AuthGuard] },
	{path: "watchlist",component: WatchlistComponent,canActivate: [AuthGuard]},
	{ path: "home", component: HomeComponent, canActivate: [AuthGuard] },
	{path: "discover-movies",component: DiscoverMoviesComponent,canActivate: [AuthGuard]},
	{ path: "login", component: LoginComponent },
	{ path: " ", redirectTo :'/login',pathMatch : 'full' },
	{ path: "register", component: RegisterComponent },
	{ path: "logout", component: LogoutComponent },
	{ path: "alert", component: AlertComponent }
];
@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule {}