package hw.responseobj.repository;

import hw.responseobj.domain.Student;
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
        studentMap.put(++sequense, student);
        return student;
    }

    public Student findById(Long id) {
        return studentMap.get(id);
    }

    public List<Student> findAll() {
        return studentMap.values()
                         .stream()
                         .sorted(Comparator.comparingInt(Student::getGrade))
                         .toList();
    }

    public List<Student> findAllByGrade(int grade) {
        return studentMap.values()
                  .stream()
                  .filter(student -> student.getGrade() == grade)
                  .toList();
    }
}
