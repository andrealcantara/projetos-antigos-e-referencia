package br.com.devtree.endpoint;

import br.com.devtree.error.CustomErrorType;
import br.com.devtree.error.ResourceNotFoundException;
import br.com.devtree.model.Student;
import br.com.devtree.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1")
public class StudentEndpoint {

    private final StudentRepository studentDAO;

    @Autowired
    public StudentEndpoint(StudentRepository studentDao) {
        this.studentDAO = studentDao;
    }

    @GetMapping(path = "/protected/students")
    public ResponseEntity<?> listAll(
            Pageable pageable
    ) {
        return new ResponseEntity<>(studentDAO.findAll(
                pageable
        ), HttpStatus.OK);
    }

    @GetMapping(path="/protected/students/findByName/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){
        return new ResponseEntity<>(studentDAO.findByNameIgnoreCaseContaining(name),HttpStatus.OK);
    }
    @GetMapping(path = "/protected/students/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Student student = verifyIfStudentExist(id);
         return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping(path = "/admin/students")
    public ResponseEntity<?> save(@Valid @RequestBody Student student) {
        Student aux = studentDAO.save(student);
        return new ResponseEntity<>(aux, HttpStatus.CREATED);
    }

    @DeleteMapping(path="/admin/students/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        verifyIfStudentExist(id);
        studentDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path="/admin/students")
    public ResponseEntity<?> update(@RequestBody Student student) {
        verifyIfStudentExist(student.getId());
        studentDAO.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Student verifyIfStudentExist(Long id) {
        if(id == null)
            throw new ResourceNotFoundException("Student not found with ID null");

        return studentDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found by ID: "+id));
    }
}