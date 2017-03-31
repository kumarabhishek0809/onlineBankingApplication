import { LoginService } from '../login.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loggedIn: Boolean = false;
  username: String;
  password: String;
  constructor(private _loginService: LoginService) {
    if (localStorage.getItem('PortalAdminHasLoggedIn') === ''
      || localStorage.getItem('PortalAdminHasLoggedIn') == null) {
      console.log(localStorage);
      this.loggedIn = false;
    } else {
      console.log('Else Block Loged True' + localStorage);
      this.loggedIn = true;
    }
  }

  onSubmit() {
    console.log('user' + this.username);
    console.log('password' + this.password);
    this._loginService.sendCredential(this.username, this.password).subscribe(
      res => {
        this.loggedIn = true;
        localStorage.setItem('PortalAdminHasLoggedIn', 'true');
        location.reload();
      },
      err => console.log(err)
    );
  }

  ngOnInit() {
    localStorage.setItem('PortalAdminHasLoggedIn', '');
  }

}
