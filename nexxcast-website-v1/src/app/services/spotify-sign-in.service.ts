import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SpotifySignInService {

  constructor(public http: HttpClient) {
    
  }
}
