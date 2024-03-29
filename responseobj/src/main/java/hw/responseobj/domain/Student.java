package hw.responseobj.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Student {
    public Long id;
    private final String name;
    private final int grade;
}
