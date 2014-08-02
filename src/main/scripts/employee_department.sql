CREATE TABLE `employee_department` (
  `empId`          int(11),
  `deptId`         int(11),
  CONSTRAINT `FK_EMP_ID`  FOREIGN KEY (`empId`)   REFERENCES `employee`(`empId`),
  CONSTRAINT `FK_DEPT_ID`     FOREIGN KEY (`deptId`)      REFERENCES `department`(`deptId`)
);