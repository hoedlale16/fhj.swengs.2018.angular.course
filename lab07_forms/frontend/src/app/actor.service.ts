import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Actor} from './api/actor';
import {map} from 'rxjs/operators';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ActorService {

  constructor(private http: HttpClient) {
  }

  getById(id: string) {
    return this.http.get('/api/dto/actors/' + id).pipe(map((actor: Actor) => {
      this.prepareActorForSaving(actor);
      return actor;
    }));
  }

  getAll() {
    return this.http.get('/api/actors');
  }

  delete(actor) {
    return this.http.delete('/api/actors/' + actor.id);
  }

  prepareActorForSaving(actor: Actor) {
    if (actor.dayOfBirth instanceof String) {
      actor.dayOfBirth = new Date(actor.dayOfBirth);
    }
  }

  update(actor: Actor) {
    this.prepareActorForSaving(actor);
    return this.http.put('/api/dto/actors/' + actor.id, actor);
  }

  create(actor: Actor) {
    this.prepareActorForSaving(actor);
    return this.http.post('/api/dto/actors', actor);
  }


  isNickNameTaken(nickName: string): Observable<boolean> {
    return this.http.get('/api/dto/actors/search/findFirstByNickName/' + nickName).pipe(map((actor: Actor) => {
      return actor ? true : false;
    }));
  }
}
