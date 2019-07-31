import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { SearchComponent } from './search/search.component';
import { SearchItemComponent } from './search-item/search-item.component';
import { QueueComponent } from './queue/queue.component';
import { QueueItemComponent } from './queue-item/queue-item.component';


@NgModule({
  declarations: [SearchComponent, SearchItemComponent, QueueComponent, QueueItemComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [SearchComponent]
})
export class CoreModule { }
