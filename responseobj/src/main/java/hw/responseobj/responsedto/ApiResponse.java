package hw.responseobj.responsedto;


import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private Status status;
    private Metadata metadata;
    private T results;

    public static <T> ApiResponse<T> makeResponse(T result) {
        return new ApiResponse<>(new Status(2000, "OK"), new Metadata(1), result);
    }

    public static <T> ApiResponse<List<T>> makeResponse(List<T> results) {
        return new ApiResponse<>(new Status(2000, "OK"), new Metadata(results.size()), results);
    }

    @Getter
    @RequiredArgsConstructor
    private static class Metadata {
        private final int resultCount;
    }
}
