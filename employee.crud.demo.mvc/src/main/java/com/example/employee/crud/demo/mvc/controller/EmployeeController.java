package com.example.employee.crud.demo.mvc.controller;

import com.example.employee.crud.demo.mvc.entity.Employee;
import com.example.employee.crud.demo.mvc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model model){
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "list-employees";
    }
    @GetMapping("/addForm")
    public String showAddForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "showAddForm";

    }
    @PostMapping("/processAddForm")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.save(employee);
        return "redirect:/employee/list";
    }
    @GetMapping("/updateForm")
    public String showUpdateForm(Model model, @RequestParam int id){
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "showUpdateForm";
    }
    @PostMapping("processUpdateForm")
    public String updateEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.save(employee);
        return "redirect:/employee/list";
    }
    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam int id){
        employeeService.deleteById(id);
        return "redirect:/employee/list";
    }
}
