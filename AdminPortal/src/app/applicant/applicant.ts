import { isDate } from 'util';


export interface IApplicant {
    name: String;
    email: String;
    phoneNumber: String;
    id: Number;
}



export class Applicant implements IApplicant {
    public id: Number;
    constructor(public name: String,
        public phoneNumber: String, public email: String) {

    }
}

