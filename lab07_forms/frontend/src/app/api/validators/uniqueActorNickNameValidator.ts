import {Directive, forwardRef, Injectable} from '@angular/core';
import {AbstractControl, AsyncValidator, NG_ASYNC_VALIDATORS, ValidationErrors} from '@angular/forms';
import {ActorService} from '../../actor.service';
import {Observable} from 'rxjs';
import {catchError, map} from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class UniqueActorNickNameValidator implements AsyncValidator {
  constructor(private actorService: ActorService) {}

  validate(
    ctrl: AbstractControl
  ): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
    return this.actorService.isNickNameTaken(ctrl.value).pipe(
      map(isTaken => (isTaken ? { uniqueNickName: true } : null)),
      catchError(() => null)
    );
  }
}


@Directive({
  selector: '[appUniqueActorNickName]',
  providers: [
    {
      provide: NG_ASYNC_VALIDATORS,
      useExisting: forwardRef(() => UniqueActorNickNameValidator),
      multi: true
    }
  ]
})
export class UniqueActorNickNameDirective {
  constructor(private validator: UniqueActorNickNameValidator) {}

  validate(control: AbstractControl) {
    this.validator.validate(control);
  }
}
