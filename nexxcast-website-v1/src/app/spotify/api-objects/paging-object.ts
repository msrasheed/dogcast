import { Artist } from './artist';
import { SimpleAlbum } from './simple-album';
import { Track } from './track';
import { Playlist } from './playlist';

export class PagingObject {
  constructor(
    public href: string,
    public items: Artist[] | SimpleAlbum[] | Track[] | Playlist[],
    public limit: number,
    public next: string,
    public offset: number,
    public previous: string,
    public total: number,
  ) {}
}
