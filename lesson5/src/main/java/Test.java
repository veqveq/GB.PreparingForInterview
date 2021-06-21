import dao.StudentDAO;
import models.Student;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();

        for (int i = 0; i < 1000; i++) {
            studentDAO.saveOrUpdate(new Student("Student " + i, i));
        }

        System.out.println(studentDAO.findById(23L));

        List<Student> students = studentDAO.findAll();
        students.forEach(System.out::println);

        studentDAO.deleteById(23L);
        System.out.println(studentDAO.findById(23L));

        Student student = studentDAO.findById(24L);
        student.setMark(2);
        studentDAO.saveOrUpdate(student);
        System.out.println(studentDAO.findById(24L));
    }
}
