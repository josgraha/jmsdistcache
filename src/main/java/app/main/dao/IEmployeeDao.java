package app.main.dao;

import app.main.model.Department;
import app.main.model.Employee;

public interface IEmployeeDao {
	
	void saveEmployee(Employee emp);

	Employee getEmployee(int empId);

	void updateEmployee(Employee employee, int empId);

	void deleteEmployee(int empId);

	Department getEmployeeDept(int empId, int deptId);

	void saveEmployeeDept(int empId, Department dept);

	void updateEmployeeDept(int empId, Department dept, int deptId);

	void deleteEmployeeDept(int empId, int deptId);

}
