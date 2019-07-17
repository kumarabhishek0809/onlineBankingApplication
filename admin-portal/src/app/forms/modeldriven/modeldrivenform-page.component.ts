import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';


@Component({
    selector: 'modeldrivenform-form',
    templateUrl: './modeldrivenform-page.html',
    styles: [`input.ng-invalid{border-left:5px solid red;} 
    input.ng-valid{border-left:5px solid green;}`]

})
export class ModelDrivenForm implements OnInit {

    userForm: FormGroup;
    constructor(private _formBuilder: FormBuilder) {

    }
    ngOnInit() {
        this.userForm = this._formBuilder.group({
            name: ['Kumar', [Validators.required, Validators.minLength(4), Validators.maxLength(10)]],
            email: [],
            address: this._formBuilder.group({
                street: [],
                city: [],
                postalCode: [null, [Validators.pattern('^[1-9][0-9]{4}$')]]
            })
        });
    }

    /*
    userForm: FormGroup = new FormGroup({
        name: new FormControl('Kumar', [Validators.required, Validators.minLength(4), Validators.maxLength(10)]),
        email: new FormControl('kumar.abhishek0809@gmail.com'),
        address: new FormGroup({
            street: new FormControl('Street 4'),
            city: new FormControl('Discovery Garden'),
            postalCode: new FormControl(null, [Validators.pattern('^[1-9][0-9]{4}$')])
        })
    });
    */

    onSubmit() {
        console.log(this.userForm.value);
    }

}

