package hw.responseobj.controller;


import hw.responseobj.domain.Student;
import hw.responseobj.exception.CustomException;
import hw.responseobj.exception.ErrorCode;
import hw.responseobj.exception.ErrorResponse;
import hw.responseobj.exception.InputRestriction;
import hw.responseobj.responsedto.ApiResponse;
import hw.responseobj.service.StudentService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hw.responseobj.responsedto.ApiResponse.makeResponse;

@Slf4j
@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
    private final int MAX_GRADE = 6;
    private final StudentService studentService;

    @ExceptionHandler(CustomException.class)
    public ErrorResponse customExceptionHandler(HttpServletResponse response, CustomException e) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        int code = 5000;
        String message = e.getMessage();
        InputRestriction data = e.getInputRestriction();
        return new ErrorResponse(code, message, data);
    }

    @PostMapping
    public ApiResponse<Student> addScore(@ModelAttribute Student student) {
        if (student.getGrade() >= MAX_GRADE) {
            throw new CustomException(
                    ErrorCode.SERVER_ERROR,
                    "grade 는 6 이상을 입력 할 수 없습니다.",
                    new InputRestriction(MAX_GRADE)
            );
        }
        studentService.save(student);
        return makeResponse(student);
    }

    @GetMapping("/{id}")
    public ApiResponse<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findById(id);
        return makeResponse(student);
    }

    @GetMapping("/list")
    public ApiResponse<List<Student>> scores() {
        return makeResponse(studentService.findAll());
    }

    @GetMapping("/error")
    public ApiResponse<List<Student>> error() {
        method();
        return makeResponse(studentService.findAll());
    }

    @PostMapping("/grade")
    public ApiResponse<List<Student>> findStudentByGrade(int grade) {
        List<Student> findStudentsByGrade = studentService.findByGrade(grade);
        return makeResponse(findStudentsByGrade);
    }

    public void method() {
        try {
            inner();
            log.info("에러");
        } catch (Exception e) {
            throw new CustomException(ErrorCode.SERVER_ERROR, "grade 는 6 이상을 입력 할 수 없습니다.", null);
        }
    }

    public void inner() {
        throw new CustomException(ErrorCode.SERVER_ERROR, "grade 는 6 이상을 입력 할 수 없습니다.", new InputRestriction((MAX_GRADE)));
    }
}
