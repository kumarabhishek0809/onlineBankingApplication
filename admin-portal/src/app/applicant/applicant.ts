import { isDate } from 'util';


export interface IApplicant {
    name: String;
    email: String;
    phoneNumber: String;
    id: Number,
    currentState: String;
}



export class Applicant implements IApplicant {
    public id: Number;
    public currentState: String
    constructor(public name: String,
        public phoneNumber: String, public email: String
    ) {

    }
}

