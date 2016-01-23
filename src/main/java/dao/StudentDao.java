package dao;

import model.Student;

import java.util.List;


public interface StudentDao {

    int getCountPages();

    void deleteStudent (int studentId);

    void addStudent(Student student);

    List<Student> getAllStudent(int offset, int numberPerPage);

}
