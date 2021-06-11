package dao;

import models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import java.util.List;

public class StudentDAO {
    private final SessionFactory factory;

    public StudentDAO() {
        this.factory = Configurator.get();
    }

    public void saveOrUpdate(Student student) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(student);
            session.getTransaction().commit();
        }
    }

    public void delete(Student student) {
        deleteById(student.getId());
    }

    public void deleteById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Student student = session.createQuery(String.format("SELECT s FROM Student s WHERE s.id = %d", id), Student.class).getSingleResult();
            session.delete(student);
            session.getTransaction().commit();
        } catch (NoResultException e) {
            System.out.println("Не удалось выполнить удаление по ID = " + id);
        }
    }

    public Student findById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Student student = session.createQuery(String.format("SELECT s FROM Student s WHERE s.id = %d", id), Student.class).getSingleResult();
            session.getTransaction().commit();
            return student;
        } catch (NoResultException e) {
            System.out.println("Не найден ID = " + id);
            return null;
        }
    }

    public List<Student> findAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Student> result = session.createQuery(("from Student"), Student.class).getResultList();
            session.getTransaction().commit();
            return result;
        } catch (NoResultException e) {
            System.out.println("Нет данных в базе");
            return null;
        }
    }
}
