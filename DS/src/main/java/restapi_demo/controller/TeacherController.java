package restapi_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapi_demo.domain.Teacher;
import restapi_demo.domain.dto.TeacherResponseDTO;
import restapi_demo.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping(path = "Teachers",
        produces = "application/json")
@CrossOrigin(origins = "*")
public class TeacherController {
    private TeacherService service;

    @Autowired
    public TeacherController(@Qualifier("teacherServiceJPA") TeacherService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeacherResponseDTO>> getAllTeachers() {
        return new ResponseEntity(
                service.findAll(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> getTeacherById(@PathVariable("id") long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/first_name/{first_name}")
    public ResponseEntity<TeacherResponseDTO> getTeacherByFirstName(@PathVariable("first_name") String first_name) {
        return new ResponseEntity(service.findByFirstName(first_name), HttpStatus.ACCEPTED);
    }

    @GetMapping("last_name/{last_name}")
    public ResponseEntity<TeacherResponseDTO> getTeacherByLastName(@PathVariable("last_name") String last_name) {
        return new ResponseEntity(service.findByFirstName(last_name), HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<TeacherResponseDTO> postTeacher(
            @RequestParam("first_name") String first_name,
            @RequestParam("last_name") String last_name
    ) {
        Teacher teacher = Teacher.builder().first_name(first_name).last_name(last_name).build();
        service.insertTeacher(teacher);
        return new ResponseEntity(new TeacherResponseDTO(teacher.getFirst_name() + teacher.getLast_name()), HttpStatus.OK);
    }
}
