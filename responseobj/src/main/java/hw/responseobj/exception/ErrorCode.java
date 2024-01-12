package hw.responseobj.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    SERVER_ERROR("5000");
    private final String code;
}
