package com.infotech.client;

import com.infotech.entities.Department;
import com.infotech.entities.Employee;
import com.infotech.entities.Project;
import com.infotech.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

public class DynamicFetchingUsingProfileTest {

    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Dynamic fetching using hibernate profiles example
            String userName="ericm";
            session.enableFetchProfile("employee.projects");
            Employee employee = session.bySimpleNaturalId(Employee.class).load(userName);

            if (employee != null) {
                System.out.println("Employee details::::::");
                System.out.println(employee.getId() + "\t" + employee.getEmployeeName() + "\t" + employee.getUsername()
                        + "\t" + employee.getPassword() + "\t" + employee.getAccessLevel());

                System.out.println("Employee projects details::::::");
                List<Project> projects = employee.getProjects();
                for (Project project : projects) {
                    System.out.println(project.getId()+"\t"+project.getProjectName());
                }
                System.out.println("Employee's department details::::::");
                Department department = employee.getDepartment();
                if (department != null)
                    System.out.println(department.getId() + "\t" + department.getDeptName());
                else
                    System.out.println("Department details not found");

            } else {
                System.out.println("Employee not found with  provided credential");
            }
        }
    }
}