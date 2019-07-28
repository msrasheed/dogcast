import { SimpleArtist } from './simple-artist';
import { ExternalURL } from './external-url';
import { Image } from './image';
import { Restrictions } from './restrictions'

export class SimpleAlbum {
  constructor(
    public album_group: string,
    public album_type: string,
    public artists: SimpleArtist[],
    public available_markets: string[],
    public external_urls: ExternalURL[],
    public href: string,
    public id: string,
    public images: Image[],
    public name: string,
    public release_date: string,
    public release_date_precision: string,
    public restrictions: Restrictions,
    public type: string,
    public uri: string,
  ) {}
}
