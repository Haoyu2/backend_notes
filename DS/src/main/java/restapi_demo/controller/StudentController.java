package restapi_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapi_demo.domain.Student;
import restapi_demo.domain.Teacher;
import restapi_demo.domain.TeacherStudentMapping;
import restapi_demo.domain.dto.StudentResponseDTO;
import restapi_demo.repository.StudentRepository;
import restapi_demo.repository.TeacherRepository;
import restapi_demo.repository.TeacherStudentRepository;
import restapi_demo.service.StudentService;
import restapi_demo.service.TeacherStudentService;

import java.util.List;

@RestController
@RequestMapping(path = "students",
        produces = "application/json")
@CrossOrigin(origins = "*")
public class StudentController {
    private StudentService service;
    private TeacherStudentService teacherStudentService;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;

    @Autowired
    public StudentController(
            @Qualifier("studentServiceJPA") StudentService service,
            @Qualifier("teacherStudentServiceJPA") TeacherStudentService teacherStudentService,
            @Qualifier("studentRepositoryJPA") StudentRepository repository,
            @Qualifier("teacherRepositoryJPA") TeacherRepository teacherRepository
    ) {
        this.service = service;
        this.teacherStudentService = teacherStudentService;
        this.studentRepository = repository;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        return new ResponseEntity(
                service.findAll(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable("id") long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/first_name/{first_name}")
    public ResponseEntity<StudentResponseDTO> getStudentByFirstName(@PathVariable("first_name") String first_name) {
        return new ResponseEntity(service.findByFirstName(first_name), HttpStatus.ACCEPTED);
    }

    @GetMapping("last_name/{last_name}")
    public ResponseEntity<StudentResponseDTO> getStudentByLastName(@PathVariable("last_name") String last_name) {
        return new ResponseEntity(service.findByFirstName(last_name), HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<StudentResponseDTO> postStudent(
            @RequestParam("first_name") String first_name,
            @RequestParam("last_name") String last_name
    ) {
        Student student = Student.builder().first_name(first_name).last_name(last_name).build();
        service.insertStudent(student);
        return new ResponseEntity(new StudentResponseDTO(student.getFirst_name() + student.getLast_name()), HttpStatus.OK);
    }

    @PostMapping("/{s_id}/{t_id}")
    public ResponseEntity<StudentResponseDTO> postStudent(
            @PathVariable("s_id") long s_id,
            @PathVariable("t_id") long t_id

    ) {
        Teacher teacher = teacherRepository.findById(t_id);
        Student student = studentRepository.findById(s_id);
        TeacherStudentMapping ts = TeacherStudentMapping
                .builder()
                .student(student)
                .teacher(teacher)
                .build();

        return new ResponseEntity(
                teacherStudentService.insertTeacherStudent(ts),
                HttpStatus.OK
        );
    }


    @PatchMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> postStudent(
            @PathVariable("id") long id,
            @RequestParam("first_name") String first_name,
            @RequestParam("last_name") String last_name
    ) {
        Student student = studentRepository.findById(id);
        student.setFirst_name(first_name);
        student.setLast_name(last_name);
        studentRepository.save(student);
        return new ResponseEntity(new StudentResponseDTO(student.getFirst_name() + student.getLast_name()), HttpStatus.OK);
    }
}
