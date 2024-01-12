package hw.responseobj.service;

import hw.responseobj.domain.Student;
import hw.responseobj.exception.CustomException;
import hw.responseobj.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student findById(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<Student> findByGrade(int grade) {
        return studentRepository.findAllByGrade(grade);
    }
}
