import {Task} from './task';
import {User} from './user';

export class TaskSharing {
  constructor(
    public id?: string,
    public task?: Task,
    public sender?: User,
    public receiver?: User,
    public dateTime?: Date
  ) {
  }
}
