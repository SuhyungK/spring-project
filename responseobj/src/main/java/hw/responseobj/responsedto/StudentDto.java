package hw.responseobj.responsedto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StudentDto {
    private final String name;
    private final int grade;
}
