package hw.responseobj.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InputRestriction {
    private final int maxGrade;
}