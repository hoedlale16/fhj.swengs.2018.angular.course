import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Actor} from '../api/actor';
import {ActivatedRoute, Router} from '@angular/router';
import {ActorService} from '../actor.service';

@Component({
  selector: 'app-actor-form',
  templateUrl: './actor-form.component.html',
  styleUrls: ['./actor-form.component.scss']
})
export class ActorFormComponent implements OnInit {

  actor: Actor;
  shouldNavigateToList: boolean;

  constructor(private actorService: ActorService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {

    this.actor = {
      firstName: '',
      lastName: ''
    };

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.actorService.getById(id)
        .subscribe((response) => {
          this.actor = <Actor>response;
        });
    }
  }

  saveActor() {

    if (this.actor.id) {
      this.actorService.update(this.actor)
        .subscribe(() => {
          alert('updated successfully');
          this.navigateToList();
        });
    } else {
      this.actorService.create(this.actor)
        .subscribe(() => {
          alert('created successfully');
          this.navigateToList();
        });
    }

  }

  navigateToList() {
    if (this.shouldNavigateToList) {
      this.router.navigate(['/actor-list']);
    }
  }

  setShouldNavigateToList() {
    this.shouldNavigateToList = true;
  }

}
