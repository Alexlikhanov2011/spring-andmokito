package controller;

import model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.DepartamentServiceImpl;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departament")
public class DepartamentController {
    private final DepartamentServiceImpl service;

    private DepartamentController(DepartamentServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/max-salary")
    private Employee max(@RequestParam int departamentId) {
        return service.maxSalary(departamentId);
    }


    @GetMapping("/min-salary")
    private Employee min(@RequestParam int departamentId) {
        return service.minSalary(departamentId);
    }

    @GetMapping(path = "/all", params = {"department"})
    private List<Employee> all(@RequestParam int departamentId) {
        return service.findAllByDept(departamentId);
    }

    @GetMapping("/All")
    private Map<Integer, List<Employee>> allsalary() {
        return service.groopByDept();
    }
}
