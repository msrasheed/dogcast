import { ExternalURL } from './external-url';
import { Followers } from './followers';
import { Image } from './image';

export class PublicUser {
  constructor(
    public display_name: string,
    public external_urls: ExternalURL,
    public followers: Followers,
    public href: string,
    public id: string,
    public images: Image[],
    public type: string,
    public uri: string,
  ) {}
}
