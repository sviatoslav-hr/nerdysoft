import {User} from './user';

export class Task {
  constructor(
    public id?: string,
    public title?: string,
    public description?: string,
    public author?: User,
    public users?: User[]
  ) {
  }
}
