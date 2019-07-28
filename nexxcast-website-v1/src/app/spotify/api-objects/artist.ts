import { Followers } from './followers';
import { Image } from './image';

export class Artist {
  constructor(
    public external_urls: string,
    public followers: Followers,
    public genres: string[],
    public href: string,
    public id: string,
    public images: Image[],
    public name: string,
    public popularity: number,
    public type: string,
    public uri: string
  ) {
  }
}
