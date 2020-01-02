export class Alert {
  constructor(
    public type: AlertType,
    public message: string
  ) {
  }

  public static danger(message: string) {
    return new Alert(AlertType.DANGER, message);
  }

  public static success(message: string) {
    return new Alert(AlertType.SUCCESS, message);
  }
}

enum AlertType {
  PRIMARY = 'primary',
  SECONDARY = 'secondary',
  SUCCESS = 'success',
  DANGER = 'danger',
  WARNING = 'warning',
  INFO = 'info',
  LIGHT = 'light',
  DARK = 'dark'
}
