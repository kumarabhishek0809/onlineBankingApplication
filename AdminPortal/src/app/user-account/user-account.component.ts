import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { User } from '../user-account/user';

@Component({
	selector: 'app-user-account',
	templateUrl: './user-account.component.html',
	styleUrls: ['./user-account.component.css']
})
export class UserAccountComponent implements OnInit {

	private userList: User[];
	private errorMessage: String;

	constructor(private userService: UserService,
		private router: Router) {

	}

	getUsers(): User[] {
		return this.userList;
	}

	onSelectPrimary(username: String) {
		console.log('Inside onSelectPrimary' + username);
		this.router.navigate(['/primaryTransaction', username]);
	}

	onSelectSavings(username: String) {
		console.log('Inside onSelectSavings' + username);
		this.router.navigate(['/savingsTransactions', username]);
	}

	enableUser(username: string) {
		console.log('enableUser');
		this.userService.enableUser(username).subscribe();
		location.reload();
	}

	disableUser(username: string) {
		console.log('disableUser');
		this.userService.disableUser(username).subscribe();
		location.reload();
	}


	ngOnInit() {
		this.userService.getUsers()
			.subscribe(
			responseUser => this.userList = responseUser,
			responseError => this.errorMessage = responseError);
	}

}
