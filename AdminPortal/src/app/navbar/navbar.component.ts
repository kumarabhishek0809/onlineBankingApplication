import { Router } from '@angular/router';

import { LoginService } from '../login.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  loggedIn: boolean;

  constructor(private _loginService: LoginService, private router: Router) {
    if (localStorage.getItem('PortalAdminHasLoggedIn') == '') {
      this.loggedIn = false;
    } else {
      this.loggedIn = true;
    }
  }

  logout() {
    this._loginService.logout().subscribe(
      res => {
        localStorage.setItem('PortalAdminHasLoggedIn', '');
      },
      err => console.log(err));
    location.reload();
    this.router.navigate(['/login']);
  }

  getDisplay() {
    return !this.loggedIn ? 'none' : '';
  }
  ngOnInit() {
  }

}
