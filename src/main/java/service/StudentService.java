package service;

import model.Student;

import java.util.List;

public interface StudentService {

    int getCountPages();

    void deleteStudent(int studentId);

    void addStudent(Student student);

    List<Student> getAllStudent(int pageNumber);
}
