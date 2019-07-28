import { SimpleAlbum } from './simple-album';
import { SimpleArtist } from './simple-artist';
import { ExternalID } from './external-id';
import { ExternalURL } from './external-url';
import { TrackLink } from './track-link';
import { Restrictions } from './restrictions';

export class Track {
  constructor(
    public album: SimpleAlbum,
    public artists: SimpleArtist[],
    public available_markets: string[],
    public disc_number: number,
    public duration_ms: number,
    public explicit: boolean,
    public external_ids: ExternalID,
    public external_urls: ExternalURL,
    public href: string,
    public id: string,
    public is_playable: boolean,
    public linked_from: TrackLink,
    public restrictions: Restrictions,
    public name: string,
    public popularity: number,
    public preview_url: string,
    public track_number: number,
    public type: string,
    public uri: string,
    public is_local: boolean,
  ) {}
}
