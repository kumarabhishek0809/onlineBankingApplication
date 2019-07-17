"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var forms_1 = require('@angular/forms');
//import { FormControl } from '@angular/forms';
var core_1 = require('@angular/core');
var ModelDrivenForm = (function () {
    function ModelDrivenForm(_formBuilder) {
        this._formBuilder = _formBuilder;
    }
    ModelDrivenForm.prototype.ngOnInit = function () {
        this.userForm = this._formBuilder.group({
            name: ['Kumar', [forms_1.Validators.required, forms_1.Validators.minLength(4), forms_1.Validators.maxLength(10)]],
            email: [],
            address: this._formBuilder.group({
                street: [],
                city: [],
                postalCode: [null, [forms_1.Validators.pattern('^[1-9][0-9]{4}$')]]
            })
        });
    };
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
    ModelDrivenForm.prototype.onSubmit = function () {
        console.log(this.userForm.value);
    };
    ModelDrivenForm = __decorate([
        core_1.Component({
            selector: 'modeldrivenform-form',
            templateUrl: 'app/forms/modeldriven/modeldrivenform-page.html',
            styles: ["input.ng-invalid{border-left:5px solid red;} \n    input.ng-valid{border-left:5px solid green;}"]
        }), 
        __metadata('design:paramtypes', [forms_1.FormBuilder])
    ], ModelDrivenForm);
    return ModelDrivenForm;
}());
exports.ModelDrivenForm = ModelDrivenForm;
//# sourceMappingURL=modeldrivenform-page.component.js.map