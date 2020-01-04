import {Component, OnInit} from '@angular/core';
import {TaskForm} from '../../models/dto/task-form';
import {TaskService} from '../../services/task.service';
import {AuthenticationService} from '../../services/authentication/authentication.service';
import {Task} from 'src/app/models/entity/task';
import {TaskSharing} from '../../models/entity/task-sharing';
import {faCheck, faEdit, faShare, faTimes} from '@fortawesome/free-solid-svg-icons';
import * as deepEqual from 'deep-equal';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
  icons = {
    faTimes,
    faCheck,
    faEdit,
    faShare
  };
  taskForm: TaskForm;
  tasks: Task[];
  sharedTasks: TaskSharing[];

  constructor(
    private taskService: TaskService,
    private authenticationService: AuthenticationService
  ) {
    this.taskForm = new TaskForm();
  }

  ngOnInit() {
    this.getSharedTasks();
  }

  private saveTask() {
    this.taskForm.authorId = this.authenticationService.user.id;
    this.taskService.save(this.taskForm).subscribe(value => {
      console.log(value);
      this.getTasks();
    }, error => console.log(error));
    this.taskForm = new TaskForm();
  }

  private getTasks() {
    this.taskService.getAll().subscribe(
      value => {
        value.forEach(task => task.dateTime = new Date(task.dateTime));
        this.tasks = value;
      },
      error => console.log(error));
  }

  private deleteTask(taskId: string) {
    this.taskService.delete(taskId).subscribe(deletedTask => {
      console.log(deletedTask);
      this.getTasks();
    }, error => console.log(error));
  }

  private getSharedTasks() {
    this.taskService.getAllShared().subscribe(sharedTasks => {
      sharedTasks.forEach(value => value.dateTime = new Date(value.dateTime));
      if (!this.sharedTasks || !deepEqual(this.sharedTasks, sharedTasks)) {
        this.sharedTasks = sharedTasks;
        this.getTasks();
      }
      if (this.authenticationService.authenticated) {
        setTimeout(() => this.getSharedTasks(), 5000);
      }
    }, error => {
      console.log(error);
      if (this.authenticationService.authenticated) {
        setTimeout(() => this.getSharedTasks(), 5000);
      }
    });
  }

  private shareTask(taskId: string, receiverEmail: string) {
    this.taskService.share({taskId, receiverEmail}).subscribe(data => {
      console.log(data);
    }, error => console.log(error));
  }

  private acceptTask(taskId: string) {
    this.taskService.accept(taskId).subscribe(data => {
      console.log(data);
    }, error => console.log(error));
  }

  private declineTask(taskId: string) {
    this.taskService.decline(taskId).subscribe(data => {
      console.log(data);
      this.getTasks();
    }, error => console.log(error));
  }
}
