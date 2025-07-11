package pe.edu.vallegrande.cristopher.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.vallegrande.cristopher.dto.StudentDto;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/api")
public class StudentController {
    
    @GetMapping("/student")
    public ResponseEntity<StudentDto> getStudent() {
        StudentDto student = new StudentDto(
            "47277882  ",
            "cristopher",
            "guzman velarde",
            LocalDateTime.now()
        );
        
        return ResponseEntity.ok(student);
    }
}
