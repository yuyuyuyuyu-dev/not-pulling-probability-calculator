import { Component, EventEmitter, Output } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { InputValues } from './types/InputValues';

@Component({
    selector: 'app-input-fields',
    imports: [MatFormFieldModule, MatInputModule, ReactiveFormsModule],
    templateUrl: './input-fields.component.html',
    styleUrl: './input-fields.component.scss'
})
export class InputFieldsComponent {
  odds = new FormControl('');
  pulls = new FormControl('');

  @Output() onChangedEvent = new EventEmitter<InputValues>();

  onChanged() {
    this.onChangedEvent.emit({
      odds: Number(this.odds.value),
      pulls: Number(this.pulls.value),
    });
  }
}
