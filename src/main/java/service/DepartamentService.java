package service;

import model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartamentService {
    Employee maxSalary(int deptId);

    Employee minSalary(int deptId);

    List<Employee> findAllByDept(int deptId);

    Map<Integer, List<Employee>> groopByDept();


}
