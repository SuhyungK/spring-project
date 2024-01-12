package hw.responseobj.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private ErrorCode code;
    private String message;
    private InputRestriction inputRestriction;

    public CustomException (ErrorCode code, String message, InputRestriction data) {
        this.code = code;
        this.message = message;
        this.inputRestriction = data;
    }
}
