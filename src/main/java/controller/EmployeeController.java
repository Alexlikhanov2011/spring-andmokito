package controller;


import exception.NotValidArgumentExeption;
import model.Employee;
import org.apache.commons.lang3.StringUtils;
import service.EmloyeeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.EmloyeeServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmloyeeServiceImpl service;

    public EmployeeController(EmloyeeServiceImpl service) {
        this.service = service;
    }



    @GetMapping("/add")
    public void add(@RequestParam String lastName,
                    @RequestParam String firstName,
                    @RequestParam double salary,
                    @RequestParam int departamentId) {
        chek(firstName, lastName);
        service.addEmployee(lastName, firstName,salary,departamentId);
    }


    @GetMapping("/get")
    public Employee get(@RequestParam String lastName, @RequestParam String firstName) {
        chek(firstName, lastName);
        return service.findEmployee(lastName, firstName);
    }


    @GetMapping("/remove")
    public boolean remove(@RequestParam String lastName, @RequestParam String firstName) {
        chek(firstName, lastName);
        return service.removeEmployee(lastName, firstName);
    }


    @GetMapping("/all")
    public Collection<Employee> getAll() {
        return service.getAll();
    }
    private void chek (String... args){
        for (String arg : args) {
            if (!StringUtils.isAlpha(arg)) throw new NotValidArgumentExeption();

        }
                
                
    }
}
