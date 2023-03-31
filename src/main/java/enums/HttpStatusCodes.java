package enums;

public enum HttpStatusCodes {
    SUCCESSFULLY_RETURN(200),
    SUCCESSFULLY_CREATED(201),
    SUCCESS_NO_CONTENT(204),
    BAD_REQUEST(400),
    UNAUTHORIZED_ERROR(401),
    NOT_FOUND(404)
    ;

  private final int code;

  HttpStatusCodes(int code) {
      this.code = code;
  }

  public int getHttpStatusCode() {
      return code;
  }
}
