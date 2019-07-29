import { ExternalURL } from './external-url';

export class TrackLink {
  constructor(
    public external_urls: ExternalURL,
    public href: string,
    public id: string,
    public type: string,
    public uri: string,
  ) {}
}
