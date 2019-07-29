import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { SearchComponent } from './search/search.component';
import { SearchItemComponent } from './search-item/search-item.component';


@NgModule({
  declarations: [SearchComponent, SearchItemComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [SearchComponent]
})
export class CoreModule { }
