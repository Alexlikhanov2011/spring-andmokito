package service;

import exception.EmployeeAllreadyAddedException;
import exception.EmployeeNotFoundException;
import exception.EmployeeStorageIsFullException;
import model.Employee;
import org.assertj.core.api.Assumptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmloyeeServiceImplTest {
EmloyeeServiceImpl emloyeeService = new EmloyeeServiceImpl() {
};

    @Test
    void addEmployee() {
        emloyeeService.addEmployee("test", "test2", 10_000d,1);
       Collection<Employee> allEmployes = emloyeeService.getAll();
        assertEquals(1, allEmployes.size());
        var employee = allEmployes.iterator().next();
        assertEquals("Test", employee.getLastName());
        assertEquals("Test2", employee.getFirstName());
        assertEquals(10_000, employee.getSalary());
        assertEquals(1, employee.getDepartament());
    }

    @Test
    void addStorageIsFull() {
        for (int i = 0; i < 10; i++) {
            emloyeeService.addEmployee("test_" + i,"test_test_" + i, 0d, 0);
        }
        assertThrows(EmployeeStorageIsFullException.class, ()-> emloyeeService.addEmployee("test_","test_test_", 0d, 0));
    }

    @Test
    void addAllReadyExist() {
        emloyeeService.addEmployee("test","test", 0d, 0);
   assertThrows( EmployeeAllreadyAddedException.class, ()-> emloyeeService.addEmployee("test", "test", 0, 0));
    }

    @Test
    void findEmployee() {
        emloyeeService.addEmployee("testtest","test",10_000,1);
    var actual = emloyeeService.findEmployee("testtest","test");
   assertEquals("Test", actual.getFirstName());
        assertEquals("Testtest", actual.getLastName());
        assertEquals(10_000, actual.getSalary());
        assertEquals(1, actual.getDepartament());
    }
    @Test
    void findEmployeeNotExist() {
        assertThrows(EmployeeNotFoundException.class, ()-> emloyeeService.findEmployee("testtest","test"));

           }

    @Test
    void removeEmployee() {
        emloyeeService.addEmployee("testtest", "test", 10,1);
    assertEquals(1,emloyeeService.getAll().size());
    assertTrue(emloyeeService.removeEmployee("testtest","test"));
   assertThrows(EmployeeNotFoundException.class, ()->emloyeeService.removeEmployee("not_exist","not_exist"));
    }

    @Test
    void getAll() {
        emloyeeService.addEmployee("test_test_1","test_1", 100,1);
        emloyeeService.addEmployee("test_test_2","test_2", -100,1);
        emloyeeService.addEmployee("test_test_3","test_3", 100,-1);

        var all = emloyeeService.getAll();
        org.assertj.core.api.Assertions.assertThat(all.size()).isEqualTo(3);
        org.assertj.core.api.Assertions.assertThat(all)
                .containsExactlyInAnyOrder(
                        new Employee("Test_3","Test_test_3", 100,-1),
                        new Employee("Test_1","Test_test_1", 100,1),
                        new Employee("Test_2","Test_test_2", -100,1));

    }
}