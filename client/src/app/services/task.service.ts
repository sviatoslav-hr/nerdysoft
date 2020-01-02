import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TaskForm} from '../models/dto/task-form';
import {Observable} from 'rxjs';
import {Task} from '../models/entity/task';
import {ApiUrls} from '../const/api-urls';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(
    private http: HttpClient
  ) {
  }

  public getAllForPrincipal(): Observable<Task[]> {
    return this.http.get<Task[]>(ApiUrls.TASK);
  }

  public save(taskForm: TaskForm): Observable<Task> {
    return this.http.post<Task>(ApiUrls.TASK, taskForm);
  }

  public get(taskId: string): Observable<Task> {
    return this.http.get<Task>(ApiUrls.TASK + taskId);
  }

  public delete(taskId: string): Observable<Task> {
    return this.http.delete<Task>(ApiUrls.TASK + taskId);
  }

}
