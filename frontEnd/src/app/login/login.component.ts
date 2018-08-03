import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { AlertService } from './../services/alert.service';
import { AuthService } from '../services/auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  
})
export class LoginComponent implements OnInit {

  userId: string;
  password: string;
  loading: string;
  
  
constructor(private authsvc : AuthService,private router: Router,private alertService: AlertService) {}
  
ngOnInit() {}

  loginUser() {
    this.authsvc.login(this.userId,this.password).
    then(obj=>
    {
      if(obj["token"]){
        this.authsvc.setToken(obj["token"]);
        this.authsvc.setUserId(this.userId);
        this.router.navigate(['/home']);
        window.location.reload();
      }
      else{
        console.log("No token found");
      }
    });
  }
}

      