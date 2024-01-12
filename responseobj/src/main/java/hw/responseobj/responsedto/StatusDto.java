package hw.responseobj.responsedto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
@Getter
public enum StatusDto {
    SUCCESS(2000, "OK");

    private final int code;
    private final String message;

}
