package com.thinking.machines.hr.bl.interfaces;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
public interface EmployeeManagerInterface 
{
public enum ORDER_BY{EMPLOYEE_ID,NAME,DESIGNATION};
public final ORDER_BY EMPLOYEE_ID=ORDER_BY.EMPLOYEE_ID;
public final ORDER_BY NAME=ORDER_BY.NAME;
public final ORDER_BY DESIGNATION=ORDER_BY.DESIGNATION;
public void add(EmployeeInterface employee) throws BLException;
public void update(EmployeeInterface employee) throws BLException;
public void delete(String employeeId) throws BLException;
public int getCount() throws BLException;
public List<EmployeeInterface> getEmployees(ORDER_BY ...orderBy) throws BLException;
public EmployeeInterface getByEmployeeId(String employeeId) throws BLException;
public EmployeeInterface getByPANNumber(String panNumber) throws BLException;
public EmployeeInterface getByAadharCardNumber(String aadharCardNumber) throws BLException;
public boolean designationCodeExists(int designationCode) throws BLException;
public boolean employeeIdExists(String employeeId) throws BLException;
public boolean panNumberExists(String panNumber) throws BLException;
public boolean aadharCardNumberExists(String aadharCardNumber) throws BLException;
public int getEmployeesCountWithDesignation(int code) throws BLException;
public List<EmployeeInterface> getEmployeesWithDesignation(int code) throws BLException;

}

/*
Map<String, EmployeeInterface> employeeIdWiseMap;
Map<String, EmployeeInterface> panNumberWiseMap;
Map<String, EmployeeInterface> aadharCardNumberWiseMap;
Map<int, list<EmployeeInterface>> designationCodeWiseEmployeesMap;
List<EmployeeInterface>EmployeeIdWiseList;
List<EmployeeInterface> nameWiseList;
List<EmployeeInterface> designationCodeWiseList;
List<EmployeeInterface> designationWiseList;

String employee id
String name
DesignationInterface designation;
Enum GENDER  gender;
BigDecimal basicSalary;
Date dateOfBirth;
boolean isIndian;
String PAN;
String aadhar;
*/