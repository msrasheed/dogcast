import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { QueryRestfulService } from '@services/query-restful.service';
import { PagingObject, Track } from '@spotify/objects';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  private searchField: FormControl;
  public albumResults: PagingObject;
  public trackResults: PagingObject;
  public artistResults: PagingObject;
  public playlistResults: PagingObject;

  public showDropdown: boolean = false;
  public showResults: boolean = false;
  public loading: boolean = false;

  constructor(public queryServ: QueryRestfulService) {
  }

  ngOnInit() {
    this.searchField = new FormControl();
    this.searchField.valueChanges.pipe(
      debounceTime(500),
      distinctUntilChanged()
    ).subscribe(query => {
        console.log(query);
        if (query != "") {
          this.loading = true;
          this.queryServ.getQuery(query).subscribe(value => {
            console.log(value);
            if (value["albums"]) this.albumResults = value["albums"];
            if (value["tracks"]) this.trackResults = value["tracks"];
            if (value["artists"]) this.artistResults = value["artists"];
            if (value["playlists"]) this.playlistResults = value["playlists"];
            this.loading = false;
            this.showResults = true;
            this.showDropdown = true;
          });
        }
        else {
          this.showResults = false;
          this.showDropdown = false;
        }
    });
  }

  getArtistString(track: Track) {
    return track.artists.map(x => x.name).join(', ');
  }
}
