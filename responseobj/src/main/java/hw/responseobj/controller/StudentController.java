package hw.responseobj.controller;


import hw.responseobj.domain.Student;
import hw.responseobj.exception.CustomException;
import hw.responseobj.exception.ErrorCode;
import hw.responseobj.exception.ErrorResponse;
import hw.responseobj.exception.InputRestriction;
import hw.responseobj.responsedto.ApiResponse;
import hw.responseobj.responsedto.StudentDto;
import hw.responseobj.service.StudentService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static hw.responseobj.responsedto.ApiResponse.makeResponse;

@Slf4j
@RequestMapping("/api/student")
@Controller
@RequiredArgsConstructor
public class StudentController {
    private final int MAX_GRADE = 6;
    private final StudentService studentService;

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ErrorResponse customExceptionHandler(HttpServletResponse response, CustomException e) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        int code = 5000;
        String message = e.getMessage();
        InputRestriction data = e.getInputRestriction();
        return new ErrorResponse(code, message, data);
    }

    @PostMapping
    public void addScore(@ModelAttribute Student student, HttpServletResponse response) throws IOException {
        if (student.getGrade() >= MAX_GRADE) {
            throw new CustomException(
                    ErrorCode.SERVER_ERROR,
                    "grade 는 6 이상을 입력 할 수 없습니다.",
                    new InputRestriction(MAX_GRADE)
            );
        }
        studentService.save(student);
        response.sendRedirect("/api/student/" + student.getId());
//        return "redirect:/api/student/" + student.getId();
//        return makeResponse(student);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ApiResponse<StudentDto> getStudentInfo(@PathVariable Long id) {
        StudentDto studentDto = studentService.findById(id);
        return makeResponse(studentDto);
    }

    @GetMapping("/list")
    @ResponseBody
    public ApiResponse<List<StudentDto>> scores() {
        return makeResponse(studentService.findAll());
    }

    @GetMapping("/error")
    @ResponseBody
    public ApiResponse<List<StudentDto>> error() {
        method();
        return makeResponse(studentService.findAll());
    }

    @PostMapping("/grade")
    @ResponseBody
    public ApiResponse<List<StudentDto>> findStudentByGrade(int grade) {
        List<StudentDto> findStudentsByGrade = studentService.findByGrade(grade);
        return makeResponse(findStudentsByGrade);
    }

    private void method() {
        try {
            inner();
            log.info("에러");
        } catch (Exception e) {
            throw new CustomException(ErrorCode.SERVER_ERROR, "grade 는 6 이상을 입력 할 수 없습니다.", null);
        }
    }

    private void inner() {
        throw new CustomException(ErrorCode.SERVER_ERROR, "grade 는 6 이상을 입력 할 수 없습니다.", new InputRestriction((MAX_GRADE)));
    }
}
