package employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import employee.controller.input.NewEmpInput;
import employee.dto.EmployeeDto;
import employee.service.EmpService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmpService empService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmp(@RequestBody NewEmpInput emp) {
        EmployeeDto employeeDto = empService.addEmp(objectMapper.convertValue(emp, EmployeeDto.class));
        return ResponseEntity.ok(employeeDto);
    }

    @PatchMapping
    public ResponseEntity<EmployeeDto> updateEmp(@RequestBody EmployeeDto emp) {
        EmployeeDto employeeDto = empService.updateEmp(emp);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(empService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmp(@PathVariable("id") Long id) {
        return ResponseEntity.ok(empService.getEmpById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeEmp(@PathVariable("id") Long id) {
        empService.removeEmp(id);
        return ResponseEntity.ok(String.format("Employee with ID %s removed", id));
    }
}