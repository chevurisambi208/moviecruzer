import { Injectable } from "@angular/core";
import { Http, Headers, Jsonp} from "@angular/http";
import * as jwt_decode from "jwt-decode";
export const TOKEN_NAME: string="jwt_token";
import "rxjs/Rx";


@Injectable()
export class AuthService{

constructor(private http:Http){}
private headers = new Headers({
    "Content-Type": "application/json","Accept": "application/json"
});
private serviceUrl = "http://localhost:8089/api/v1/userservice/";
userId: string="";

getToken(): string {
    return localStorage.getItem(TOKEN_NAME);
}
setToken(token:string): void {
     localStorage.setItem(TOKEN_NAME, token);
}

setUserId(userId:string){
    this.userId=userId;
}
getUserId(){
    return this.userId;
}

deleteToken():void{
    localStorage.removeItem(TOKEN_NAME);
}
getTokenExpirationDate(token: string): Date{
    const decoded: any =jwt_decode(token);
    if(decoded.exp===undefined) return null; 
    const date=new Date(0);
    date.setUTCSeconds(decoded.exp);
    return date;
}

isTokenExpired(token?:string):boolean{
    if(!token) token =this.getToken();
    if(!token) return true;
    const date =this.getTokenExpirationDate(token);
    if(date === undefined || date===null) return false;
    return !(date.valueOf()> new Date().valueOf());
}

 login(user_Id:string,password:string) {
    const url=this.serviceUrl+"login/";
    const json=JSON.stringify({userId:user_Id, password:password});
    return this.http.post(url , json ,{ headers: this.headers}).toPromise()
            .then(res => res.json()).catch((err)=>{return "Unable to fetch token";});           
}

  registerUser(userId:string,password:string,fname:string,lname:string) {
    const url=this.serviceUrl+"register/";
    const json=JSON.stringify({userId:userId, password:password,firstName:fname,lastName:lname});
    return this.http.post(url , json ,{ headers: this.headers}).toPromise()
            .then(res => res.json()).catch((err)=>{return "Unable to fetch token";});           
  }
  private handleError(error: any) {
    // console.error('An error occurred', error); // for demo purposes only
}
  
}