import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Config } from '../config';

@Injectable({
  providedIn: 'root'
})
export class SpotifySignInService {

  public bcSpotifySignIn: BroadcastChannel;

  constructor(public http: HttpClient) {
    this.bcSpotifySignIn = new BroadcastChannel("nexxcast-spotify-signin");
    this.bcSpotifySignIn.onmessage = function(ev) {
      console.log(ev);
    }
  }

  getState() {
    let apiURL = Config.baseURL + "host/spotifyauth";

    return this.http.post(apiURL, {}).toPromise();
  }

  signIn(config: object) {
    let apiURL = "https://accounts.spotify.com/authorize?";

    apiURL += "client_id=" + config["client_id"];
    apiURL += "&response_type=" + "code";
    apiURL += "&redirect_uri=" + config["redirect_uri"];
    apiURL += "&scope=" + config["scope"];
    apiURL += "&state=" + config["state"];
    apiURL += "&show_dialog=" + "true";

    window.open(encodeURI(apiURL), "_blank", "width=400,height=500");
  }
}
