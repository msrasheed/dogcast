import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Config } from 'config';
import { PagingObject } from '@spotify/objects';

@Injectable({
  providedIn: 'root'
})
export class QueryRestfulService {

  public typeConfig = {
    album: false,
    artist: false,
    playlist: false,
    track: true
  };

  constructor(public http: HttpClient) { }

  getQuery(query: string) {
    let apiURL = Config.baseURL + "SearchServlet?q=" + query + "&type=";

    for (let key in this.typeConfig) {
      if (this.typeConfig[key]) {
        //apiURL += key + ",";
      }
    }

    return this.http.get<PagingObject>(apiURL)
  }
}
