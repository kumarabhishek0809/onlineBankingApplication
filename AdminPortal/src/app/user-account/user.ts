export interface IPrimaryAccount {
    accountBalance: Number;
    id: String;
    accountNumber: Number;
}


export interface ISavingAccount {
    accountBalance: Number;
    id: String;
    accountNumber: Number;
}

export interface IUser {

    userId: Number;
    username: String;
    password: String;
    firstName: String;
    lastName: String;
    email: String;
    phone: String;
    enabled: boolean;
    primaryAccount: IPrimaryAccount;
    savingsAccount: ISavingAccount;
}



export class User implements IUser {
    constructor(public userId: Number,
        public username: String,
        public password: String,
        public firstName: String,
        public lastName: String,
        public email: String,
        public phone: String,
        public enabled: boolean,
        public primaryAccount: IPrimaryAccount,
        public savingsAccount: ISavingAccount) {

    }
}

