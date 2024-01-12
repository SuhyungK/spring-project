package hw.responseobj;

import hw.responseobj.domain.Student;
import hw.responseobj.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final StudentRepository studentRepository;

    @PostConstruct
    public void init() {
        studentRepository.save(new Student("김유정", 3));
        studentRepository.save(new Student("송강", 5));
        studentRepository.save(new Student("박서준", 1));
    }
}
