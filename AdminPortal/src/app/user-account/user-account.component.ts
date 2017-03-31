import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-account',
  templateUrl: './user-account.component.html',
  styleUrls: ['./user-account.component.css']
})
export class UserAccountComponent implements OnInit {

  userList: Object[];
  constructor(private _userService: UserService, private _router: Router) {
    this.getUsers();
  }

  getUsers() {
    console.log('Inside User Service');
    this._userService.getUsers().subscribe(
      res => { this.userList = JSON.parse(JSON.parse(JSON.stringify(res))._body); },
      error => console.log(error)
    );
  }

  onSelectPrimary(userName: String) {
    this._router.navigate(['/primaryTranacation', userName]);
  }

  onSelectSecondary(userName: String) {
    this._router.navigate(['/secondaryTranacation', userName]);
  }

  enableUser(userName: String) {
    this._userService.enableUser(userName).subscribe();
    location.reload();
  }

  disableUser(userName: String) {
    this._userService.disableUser(userName).subscribe();
    location.reload();
  }
  ngOnInit() {
  }

}
