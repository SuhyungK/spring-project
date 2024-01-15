package hw.responseobj.service;

import hw.responseobj.domain.Student;
import hw.responseobj.repository.StudentRepository;
import hw.responseobj.responsedto.StudentDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    StudentService service;

    @Autowired
    StudentRepository repository;

    @AfterEach
    void clean() {
        repository.studentMap.clear();
    }

    @Test
    @DisplayName("정상 저장 되는지 확인")
    void save_success_check() {
        Student student1 = new Student("Kim", 1);
        Student student2 = new Student("Yoon", 6);

        service.save(student1);
        service.save(student2);


        List<StudentDto> allStudents = repository.findAll();
        assertThat(allStudents.size())
                  .isEqualTo(2);
    }

}