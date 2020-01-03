import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {TaskForm} from '../models/dto/task-form';
import {Observable} from 'rxjs';
import {Task} from '../models/entity/task';
import {ApiUrls} from '../const/api-urls';
import {TaskSharing} from '../models/entity/task-sharing';
import {TaskShareForm} from '../models/dto/task-share-form';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(
    private http: HttpClient
  ) {
  }

  public getAll(): Observable<Task[]> {
    return this.http.get<Task[]>(ApiUrls.TASKS, httpOptions);
  }

  public save(taskForm: TaskForm): Observable<Task> {
    return this.http.post<Task>(ApiUrls.TASKS, taskForm, httpOptions);
  }

  public get(taskId: string): Observable<Task> {
    return this.http.get<Task>(ApiUrls.TASKS + taskId, httpOptions);
  }

  public delete(taskId: string): Observable<Task> {
    return this.http.post<Task>(ApiUrls.TASKS + taskId, httpOptions);
  }

  public getAllShared(): Observable<TaskSharing[]> {
    return this.http.get<Task[]>(ApiUrls.TASKS_SHARED, httpOptions);
  }

  public share(taskShareForm: TaskShareForm): Observable<Task> {
    return this.http.post<Task>(ApiUrls.TASKS_SHARE, taskShareForm, httpOptions);
  }

  public accept(taskId: string): Observable<Task> {
    return this.http.post<Task>(ApiUrls.TASKS_ACCEPT + taskId, httpOptions);
  }

  public decline(taskId: string): Observable<Task> {
    return this.http.post<Task>(ApiUrls.TASKS_DECLINE + taskId, httpOptions);
  }


}
