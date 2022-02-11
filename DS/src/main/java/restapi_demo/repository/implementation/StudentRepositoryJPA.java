package restapi_demo.repository.implementation;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import restapi_demo.domain.Student;
import restapi_demo.repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepositoryJPA implements StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public StudentRepositoryJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Iterable<Student> findAllPagination(int size, int index) {
        return entityManager.createQuery
                ("SELECT s FROM Student s", Student.class)
                .setFirstResult(size * index)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public Iterable<Student> findAll() {
        return entityManager.createQuery
                ("SELECT s FROM Student s", Student.class)
                .getResultList();
    }

    @Override
    public Iterable<Student> findByFirstName(String first_name) {
        return entityManager.createQuery
                ("SELECT s FROM Student s WHERE LOWER(s.first_name) = LOWER(:first_name) ", Student.class)
                .setParameter("first_name", first_name)
                .getResultList();
    }

    @Override
    public Iterable<Student> findByLastName(String last_name) {
        return entityManager.createQuery
                ("SELECT s FROM Student s WHERE LOWER(s.first_name) = LOWER(:last_name) ", Student.class)
                .setParameter("last_name", last_name)
                .getResultList();

    }

    @Override
    public Student findById(long id) {
        return entityManager.createQuery
                ("SELECT s FROM Student s WHERE LOWER(s.id) = LOWER(:id) ", Student.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
//        entityManager.flush();
//        System.out.println(student);
    }


}
