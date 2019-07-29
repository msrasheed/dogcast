import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-search-item',
  templateUrl: './search-item.component.html',
  styleUrls: ['./search-item.component.css']
})
export class SearchItemComponent implements OnInit {

  @Input('imageURL') imageURL: string;
  @Input('mainText') mainText: string;
  @Input('subText') subText: string;
  @Input('votable') votable: boolean = true;

  constructor() { }

  ngOnInit() {
  }

}
