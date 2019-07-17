import { ActivatedRoute, Params } from '@angular/router';
import { UserService } from '../services/user.service';
import { SavingsTransaction } from './savingstransaction';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-savingstransaction',
  templateUrl: './savingstransaction.component.html',
  styleUrls: ['./savingstransaction.component.css']
})
export class SavingstransactionComponent implements OnInit {

  username: String;
  savingsTransactions: SavingsTransaction[];
  errorMessage: String;

  constructor(private _userService: UserService, private _route: ActivatedRoute) {
    this._route.params.forEach((params: Params) => {
      this.username = params['username'];
    });
  }

  ngOnInit() {
    console.log('SavingstransactionComponent');
    this._userService.getSavingsTransactionList(this.username).subscribe(
      response => this.savingsTransactions = response,
      errorMessage => this.errorMessage = errorMessage
    );
  }

}
