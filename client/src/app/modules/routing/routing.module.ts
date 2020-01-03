import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from '../../components/home/home.component';
import {NotFoundComponent} from '../../components/not-found/not-found.component';
import {TaskComponent} from '../../components/task/task.component';

const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: '404', component: NotFoundComponent},
  { path: 'tasks', component: TaskComponent},
  { path: '**', redirectTo: '/404'}
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ], exports: [
    RouterModule
  ]
})
export class RoutingModule { }
