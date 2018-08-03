import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { AlertService } from './../services/alert.service';
import { AuthService } from '../services/auth.service';
import { User } from '../User';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  
})
export class RegisterComponent implements OnInit {
 
  lname: string;
  userId: string;
  fname: string;
  password: string;
  
  constructor(private authsvc:AuthService, private router: Router,private alertService: AlertService ) {}
  
  public ngOnInit() {}
  
  user=new User();
   registerUser() {
    this.authsvc.registerUser(this.userId,this.password,this.fname,this.lname).then
      (obj=>{
        if(obj){
          this.router.navigate(['/login']);
        }
        else{
          console.log("no token found");
        }
      });
      
  }
}