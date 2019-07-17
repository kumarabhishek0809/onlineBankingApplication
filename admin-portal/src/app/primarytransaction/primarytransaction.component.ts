import { UserService } from '../services/user.service';
import { ActivatedRoute, Params } from '@angular/router';
import { PrimaryTransaction } from './primarytransaction';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-primarytransaction',
  templateUrl: './primarytransaction.component.html',
  styleUrls: ['./primarytransaction.component.css']
})
export class PrimarytransactionComponent implements OnInit {

  private username: String;
  private primaryTransactions: PrimaryTransaction[];
  private errorMessage: String;
  public primaryTransactionExists: Boolean = false;
  constructor(private userService: UserService,
    private route: ActivatedRoute) {
    this.route.params.forEach((params: Params) => {
      this.username = params['username'];
    });
  }

  ngOnInit() {
    this.userService.getPrimaryTransactionList(this.username)
      .subscribe(
      responsePrimaryTransactions => this.primaryTransactions = responsePrimaryTransactions,
      responseError => this.errorMessage = responseError);
    if (!this.primaryTransactions) {
      this.primaryTransactionExists = false;
    }
  }

}
