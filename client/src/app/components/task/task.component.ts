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
  newTaskForm: TaskForm;
  editTaskForm: TaskForm;
  tasks: Task[];
  sharedTasks: TaskSharing[];
  isTaskEditing = false;
  isTaskShareLoading = false;

  constructor(
    private taskService: TaskService,
    private authenticationService: AuthenticationService
  ) {
  }

  ngOnInit() {
    this.getSharedTasks();
  }

  private saveTask() {
    this.newTaskForm.title = this.newTaskForm.title.trim();
    this.newTaskForm.description = this.newTaskForm.description.trim();
    this.newTaskForm.authorId = this.authenticationService.user.id;
    this.taskService.save(this.newTaskForm).subscribe(value => {
      this.tasks.unshift(value);
      this.clearNewTask();
      this.getTasks();
    }, error => console.log(error));
    this.newTaskForm = new TaskForm();
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
      this.getTasks();
    }, error => console.log(error));
  }

  private saveEditedTask(task: Task) {
    this.editTaskForm.title = this.editTaskForm.title.trim();
    this.editTaskForm.description = this.editTaskForm.description.trim();
    task.title = this.editTaskForm.title;
    task.description = this.editTaskForm.description;
    this.taskService.save(this.editTaskForm).subscribe(editedTask => {
      this.cancelTaskEditing(task);
      this.getTasks();
    }, error => console.log(error));
  }

  private getSharedTasks() {
    this.taskService.getAllShared().subscribe(sharedTasks => {
      sharedTasks.forEach(value => value.dateTime = new Date(value.dateTime));
      if (!this.sharedTasks || !deepEqual(this.sharedTasks, sharedTasks)) {
        this.isTaskShareLoading = false;
        this.sharedTasks = sharedTasks;
        this.getTasks();
      }
      if (this.authenticationService.authenticated) {
        setTimeout(() => this.getSharedTasks(), 3000);
      }
    }, error => {
      console.log(error);
      if (this.authenticationService.authenticated) {
        setTimeout(() => this.getSharedTasks(), 5000);
      }
    });
  }

  private shareTask(task: Task, receiverEmail: string) {
    this.taskService.share({taskId: task.id, receiverEmail}).subscribe(data => {
    }, error => console.log(error));
    task.share = false;
  }

  private acceptTask(taskId: string) {
    this.isTaskShareLoading = true;
    this.taskService.accept(taskId).subscribe(data => {
    }, error => {
      this.isTaskShareLoading = false;
      console.log(error);
    });
  }

  private declineTask(taskId: string) {
    this.isTaskShareLoading = true;
    this.taskService.decline(taskId).subscribe(data => {
      this.getTasks();
    }, error => {
      this.isTaskShareLoading = false;
      console.log(error);
    });
  }

  private editTask(task: Task) {
    task.edit = true;
    this.editTaskForm = new TaskForm();
    this.editTaskForm.id = task.id;
    this.editTaskForm.title = task.title;
    this.editTaskForm.description = task.description;
    this.isTaskEditing = true;
  }

  private cancelTaskEditing(task: Task) {
    task.edit = false;
    this.editTaskForm = null;
    this.isTaskEditing = false;
  }

  clearNewTask() {
    this.newTaskForm = null;
  }

  setUpNewTask() {
    this.newTaskForm = new TaskForm();
  }

  isNewTaskFormValid(): boolean {
    return this.newTaskForm.description && this.newTaskForm.title
      && !!this.newTaskForm.description.trim() && !!this.newTaskForm.title.trim();
  }
}
