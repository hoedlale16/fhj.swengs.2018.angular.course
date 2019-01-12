import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Actor} from './api/actor';

@Injectable({
  providedIn: 'root'
})
export class ActorService {

  constructor(private http: HttpClient) {
  }

  getById(id: string) {
    return this.http.get('/api/actors/' + id);
  }

  getAll() {
    return this.http.get('/api/actors');
  }

  delete(actor) {
    return this.http.delete('/api/actors/' + actor.id);
  }

  update(actor: Actor) {
    return this.http.put('/api/actors/' + actor.id, actor);
  }

  create(actor: Actor) {
    return this.http.post('/api/actors', actor);
  }

}
