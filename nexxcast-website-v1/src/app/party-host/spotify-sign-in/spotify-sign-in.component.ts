import { Component, OnInit } from '@angular/core';
import { SpotifySignInService } from '@services/spotify-sign-in.service';

@Component({
  selector: 'app-spotify-sign-in',
  templateUrl: './spotify-sign-in.component.html',
  styleUrls: ['./spotify-sign-in.component.css']
})
export class SpotifySignInComponent implements OnInit {

  private spotifyRedirectInfo: object;
  private stateLoaded: boolean = false;

  constructor(public spotAuthHttp: SpotifySignInService) { }

  ngOnInit() {
    this.spotAuthHttp.getState().then(
      res => {
        console.log(res);
        this.spotifyRedirectInfo = res;
        this.stateLoaded = true;
      },
      msg => {
        console.log(msg);
      }
    )
  }

  signIn() {
    this.spotAuthHttp.signIn(this.spotifyRedirectInfo);
  }
}
