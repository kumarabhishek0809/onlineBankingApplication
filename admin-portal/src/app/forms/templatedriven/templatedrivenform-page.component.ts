import { Component } from '@angular/core';


@Component({
    selector: 'templatedrivenform-form',
    templateUrl: './templatedrivenform-page.html',
    styles: [`input.ng-invalid{border-left:5px solid red;} 
    input.ng-valid{border-left:5px solid green;}`]

})
export class TemplateDrivenForm {
    myName: String = 'Kumar Abhishek';
    onSubmit(value: any) {
        console.log(value);
    }
}

