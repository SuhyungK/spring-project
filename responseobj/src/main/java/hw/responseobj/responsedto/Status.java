package hw.responseobj.responsedto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Status {
    private final int code;
    private final String message;
}
