package hw.responseobj.exception;

import hw.responseobj.responsedto.Status;
import hw.responseobj.responsedto.StatusDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ErrorResponse {
    private final Status status;
    private final Data data;

    public ErrorResponse(int code, String meesage, InputRestriction inputRestriction) {
        this.status = new Status(code, meesage);
        this.data = new Data(inputRestriction);
    }

    @Getter
    @RequiredArgsConstructor
    private static class Data {
        private final InputRestriction inputRestriction;
    }
}

