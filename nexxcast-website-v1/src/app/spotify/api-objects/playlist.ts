import { ExternalURL } from './external-url';
import { Image } from './image';
import { PublicUser } from './public-user';

export class Playlist {
  constructor(
    public collaborative: boolean,
    public external_urls: ExternalURL,
    public href: string,
    public id: string,
    public images: Image[],
    public name: string,
    public owner: PublicUser,
    public isPublic: boolean,
    public snapshot_id: string,
    public tracks: object,
    public type: string,
    public uri: string,
  ) {}
}
