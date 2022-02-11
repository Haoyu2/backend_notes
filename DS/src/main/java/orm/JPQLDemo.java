package orm;


import org.springframework.beans.factory.annotation.Autowired;
import orm.domain.Student;
import orm.domain.Teacher;
import orm.domain.TeacherStudent;

import javax.persistence.*;
import java.util.List;
import java.util.Properties;

public class JPQLDemo {
    @Autowired
    static EntityManager em;

    public static Student getStudent(int id){
        return em.find(Student.class, id);
    }

    public static List<Student> getStudents(String first_name){
        String jpql =  "SELECT s FROM Student s WHERE s.first_name = ?1";
        TypedQuery<Student> query = em.createQuery(jpql, Student.class);
        query.setParameter(1, first_name);
        return query.getResultList();
    }

    public static List<TeacherStudent> getTeacherStudent(int stu_id){
        String jpql = String.format(
                "SELECT ts.t_first_name, ts.t_last_name, ts.s_first_name, ts.s_last_name " +
                "FROM teacher_student ts left join student s" +
                        "WHERE s.id = %d "  , stu_id        );
        TypedQuery<TeacherStudent> query = em.createQuery(jpql, TeacherStudent.class);
        return query.getResultList();
    }



    public static void removeTeacherStudents(int stu_id){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        String ds = "DELETE FROM student c where c.id =  " + stu_id;
        em.createQuery(ds).executeUpdate();
        List<TeacherStudent> teacherStudents = getTeacherStudent(stu_id);
        for (TeacherStudent ts : teacherStudents){
            String jpql = "DELETE FROM teacher_student ts where ts.id =  " + ts.getId();
            em.createQuery(jpql).executeUpdate();
        }
        tx.commit();
    }





    private static Properties getProperties(){
        return null;
    }
    public static void scratch(){
        String name = "DB Connection";
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(name);
    }




    public static void main(String[] args) {
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
