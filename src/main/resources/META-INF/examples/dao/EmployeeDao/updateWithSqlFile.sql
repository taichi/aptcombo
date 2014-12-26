update Employee 
set 
  NAME = /* employee.name */'test', 
  AGE = /* employee.age */10, 
  DEPARTMENT_ID = /* employee.departmentId */1,
  HIREDATE = /* employee.hiredate */date'2010-01-01', 
  JOB_TYPE = /* employee.jobType */'SALESMAN', 
  SALARY = /* employee.salary */300, 
  UPDATETIMESTAMP = /* employee.updateTimestamp */timestamp'2010-01-01 12:34:56', 
  VERSION = /* employee.version */1
where
  ID = /* employee.id */1