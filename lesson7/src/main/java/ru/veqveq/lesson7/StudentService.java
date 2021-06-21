package ru.veqveq.lesson7;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public void saveOrUpdate(Student student) {
        studentRepository.save(student);
    }

    public void remove(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Student by id: " + id + " not found"));
    }
}
