package restapi_demo.repository.implementation;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import restapi_demo.domain.Teacher;
import restapi_demo.repository.TeacherRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherRepositoryJPA implements TeacherRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TeacherRepositoryJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Iterable<Teacher> findAll() {
        return entityManager.createQuery
                ("SELECT s FROM Teacher s", Teacher.class)
                .getResultList();
    }

    @Override
    public Iterable<Teacher> findByFirstName(String first_name) {
        return entityManager.createQuery
                ("SELECT s FROM Teacher s WHERE LOWER(s.first_name) = LOWER(:first_name) ", Teacher.class)
                .setParameter("first_name", first_name)
                .getResultList();
    }

    @Override
    public Iterable<Teacher> findByLastName(String last_name) {
        return entityManager.createQuery
                ("SELECT s FROM Teacher s WHERE LOWER(s.first_name) = LOWER(:last_name) ", Teacher.class)
                .setParameter("last_name", last_name)
                .getResultList();

    }

    @Override
    public Teacher findById(long id) {
        return entityManager.createQuery
                ("SELECT s FROM Teacher s WHERE LOWER(s.id) = LOWER(:id) ", Teacher.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void save(Teacher teacher) {
        entityManager.persist(teacher);
//        entityManager.flush();
//        System.out.println(teacher);
    }
}
