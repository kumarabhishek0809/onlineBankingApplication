export interface ISavingsTransaction {
    date: Date;
    description: String;
    type: String;
    status: String;
    amount: Number;
    availableBalance: Number;
}

export class SavingsTransaction implements ISavingsTransaction {
    constructor(public date: Date,
        public description: String,
        public type: String,
        public status: String,
        public amount: Number,
        public availableBalance: Number) {

    }
}