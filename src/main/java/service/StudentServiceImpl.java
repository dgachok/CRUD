package service;

import dao.StudentDao;
import model.Student;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentDao studentDao;
    private static int ITEM_PER_PAGE = 10;

    @Override
    public void deleteStudent(int studentId) {
        studentDao.deleteStudent(studentId);
    }

    @Override
    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    @Override
    public List<Student> getAllStudent(int pageNumber) {
        int offset = (pageNumber - 1) * ITEM_PER_PAGE;
        return studentDao.getAllStudent(offset, ITEM_PER_PAGE);
    }

    @Override
    public int getCountPages(){
        int count = studentDao.getCountPages();
        return ((count + ITEM_PER_PAGE) - 1) / ITEM_PER_PAGE;
    }

}
