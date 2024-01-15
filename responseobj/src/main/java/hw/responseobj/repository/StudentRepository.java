package hw.responseobj.repository;

import hw.responseobj.domain.Student;
import hw.responseobj.responsedto.StudentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class StudentRepository {
    Long sequense = 0L;
    public Map<Long, Student> studentMap = new HashMap<>();

    public Student save(Student student) {
        student.setId(++sequense);
        studentMap.put(sequense, student);
        return student;
    }

    public StudentDto findById(Long id) {

        Student findStudent = studentMap.get(id);
        return new StudentDto(findStudent.getName(), findStudent.getGrade());
    }

    public List<StudentDto> findAll() {
        return studentMap.values()
                .stream()
                .sorted(Comparator.comparingInt(Student::getGrade))
                .map(student -> new StudentDto(student.getName(), student.getGrade()))
                .toList();
    }

    public List<StudentDto> findAllByGrade(int grade) {
        return studentMap.values()
                .stream()
                .filter(student -> student.getGrade() == grade)
                .map(student -> new StudentDto(student.getName(), student.getGrade()))
                .toList();
    }
}
