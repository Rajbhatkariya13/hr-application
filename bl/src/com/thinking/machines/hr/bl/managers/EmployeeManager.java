package com.thinking.machines.hr.bl.managers;
import com.thinking.machines.common.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import java.math.*;
import java.util.*;
public class EmployeeManager implements EmployeeManagerInterface
{
private Map<Integer,List<EmployeeInterface>> designationCodeWiseEmployees;
private Map<String, EmployeeInterface> employeeIdWiseMap;
private Map<String, EmployeeInterface> panNumberWiseMap;
private Map<String, EmployeeInterface> aadharCardNumberWiseMap;
private List<EmployeeInterface> employeeIdWiseList;
private List<EmployeeInterface> nameWiseList;
private List<EmployeeInterface> designationCodeWiseList;
private List<EmployeeInterface> designationWiseList;
private final static EmployeeManager employeeManager;
private EmployeeManager()
{
populateDataStructures();
}
static
{
employeeManager=new EmployeeManager();
}
public static EmployeeManager getEmployeeManager()
{
return employeeManager;
}
private void populateDataStructures()
{
this.designationCodeWiseEmployees=new HashMap<>();
this.employeeIdWiseMap=new HashMap<>();
this.panNumberWiseMap=new HashMap<>();
this.aadharCardNumberWiseMap=new HashMap<>();
this.employeeIdWiseList=new ArrayList<>();
this.nameWiseList=new ArrayList<>();
this.designationCodeWiseList=new ArrayList<>();
this.designationWiseList=new ArrayList<>();
List<EmployeeInterface> employeeList;
try
{
EmployeeDAO employeeDAO=new EmployeeDAO();
List<EmployeeDTOInterface> list=employeeDAO.getAll();
Employee employee;
DesignationManager designationManager=DesignationManager.getDesignationManager();
DesignationInterface designation;
int code;
String gender="";
for(EmployeeDTOInterface dlEmployee:list)
{
employee=new Employee();
code=dlEmployee.getDesignationCode();
designation=designationManager.getByCode(code);
POJOCopier.copy(employee,dlEmployee);
gender=dlEmployee.getGender();
if(gender.equals("Male")) employee.setGender(EmployeeInterface.MALE);
else employee.setGender(EmployeeInterface.FEMALE);
employee.setBasicSalary(dlEmployee.getBasicSalary());
employee.setDesignation(designation);
if(designationCodeWiseEmployees.containsKey(code))
{
employeeList=this.designationCodeWiseEmployees.get(code);
employeeList.add(employee);
}
else
{
employeeList=new ArrayList<>();
employeeList.add(employee);
designationCodeWiseEmployees.put(code,employeeList);
}
employeeIdWiseMap.put(employee.getEmployeeId(),employee);
panNumberWiseMap.put(employee.getPANNumber(),employee);
aadharCardNumberWiseMap.put(employee.getAadharCardNumber(),employee);
employeeIdWiseList.add(employee);
nameWiseList.add(employee);
designationCodeWiseList.add(employee);
designationWiseList.add(employee);
}
Collections.sort(employeeIdWiseList);
Collections.sort(nameWiseList,new Comparator<EmployeeInterface>(){
public int compare(EmployeeInterface left,EmployeeInterface right)
{
String leftName=left.getName().toUpperCase();
String rightName=right.getName().toUpperCase();
return leftName.compareTo(rightName);
}
});

Collections.sort(designationCodeWiseList,new Comparator<EmployeeInterface>(){
public int compare(EmployeeInterface left,EmployeeInterface right)
{
int leftDesignationCode=left.getDesignation().getCode();
int rightDesignationCode=right.getDesignation().getCode();

return leftDesignationCode-rightDesignationCode;
}
});

Collections.sort(designationWiseList,new Comparator<EmployeeInterface>(){
public int compare(EmployeeInterface left,EmployeeInterface right)
{
String leftDesignation=left.getDesignation().getTitle().toUpperCase();
String rightDesignation=right.getDesignation().getTitle().toUpperCase();

return leftDesignation.compareTo(rightDesignation);
}
});

}catch(DAOException daoException)
{
return;
}
catch(BLException blException)
{
return;
}
}
/*
public void testDS()
{
Collection<List<EmployeeInterface>> values=designationCodeWiseEmployees.values();
for(List<EmployeeInterface> value : values)
{
for(EmployeeInterface valu:value)
{
System.out.println(valu.getName());
}
}
}
*/

public void add(EmployeeInterface employee) throws BLException
{
BLException blException;
blException=new BLException();
if(employee==null)
{
blException.setGenericException("employee should not be null.");
throw blException;
}
DesignationInterface vDesignation=employee.getDesignation();
if(vDesignation==null)
{
blException.setGenericException("designation should not be null");
throw blException;
}
DesignationManager designationManager=DesignationManager.getDesignationManager();
String vEmployeeId=employee.getEmployeeId();
String vName=employee.getName();
int vCode=vDesignation.getCode();
String vTitle=vDesignation.getTitle();
Date vDateOfBirth=employee.getDateOfBirth();
String vGender=employee.getGender();
BigDecimal vSalary=employee.getBasicSalary();
String vPanNumber=employee.getPANNumber();
String vAadharCardNumber=employee.getAadharCardNumber();

if(vName==null) blException.addException("NAME","Name should not be null.");
if(vName.length()==0) blException.addException("NAME","Name length should not be zero.");
if(vName.length()>=48) blException.addException("NAME","Name should not exceed 48 characters.");
if(vCode<=0) blException.addException("CODE","Invalid Designation code."); 
if(designationManager.codeExists(vCode)==false) blException.addException("CODE","Designation Code does not exists.");
if(vTitle==null) blException.addException("TITLE","Designation title should not be null.");
if(vTitle!=null)
{
if(vTitle.length()==0) blException.addException("TITLE","Designation title length should not be zero.");
if(designationManager.titleExists(vTitle)==false) blException.addException("TITLE","Designation does not exists.");
String fTitle=designationManager.getByCode(vCode).getTitle();
if(vTitle.equalsIgnoreCase(fTitle)==false)  blException.addException("TITLE","Designation Code and Title mismatch.");
}
if(vDateOfBirth==null) blException.addException("DOB","Date of bith should not be null");
if(vGender==null) blException.addException("GENDER","Gender should not be null");
if(vSalary==null) blException.addException("SALARY","Salary should not be null");
if(vPanNumber==null) blException.addException("PAN_NUMBER","PAN number should not be null");
if(vPanNumber!=null)
{
if(vPanNumber.length()==0) blException.addException("PAN_NUMBER","PAN number length should not be zero.");
if(panNumberWiseMap.containsKey(vPanNumber)) blException.addException("PAN_NUMBER","employee with this PAN number already exists.");
}
if(vAadharCardNumber==null) blException.addException("AADHAR_CARD_NUMBER","Aadhar card number should not be null.");
if(vAadharCardNumber!=null)
{
if(vAadharCardNumber.length()==0) blException.addException("AADHAR_CARD_NUMBER","Aadhar card number lenght should not be zero.");
if(aadharCardNumberWiseMap.containsKey(vAadharCardNumber)) blException.addException("AADHAR_CARD_NUMBER","employee with this Aadhar card number already exists.");
}
if(blException.hasGenericException()) throw blException;
if(blException.hasException("ID")) throw blException;
if(blException.hasException("NAME")) throw blException;
if(blException.hasException("TITLE")) throw blException;
if(blException.hasException("CODE")) throw blException;
if(blException.hasException("SALARY")) throw blException;
if(blException.hasException("DOB")) throw blException;
if(blException.hasException("GENDER")) throw blException;
if(blException.hasException("PAN_NUMBER")) throw blException;
if(blException.hasException("AADHAR_CARD_NUMBER")) throw blException;
try
{
EmployeeDAO employeeDAO=new EmployeeDAO();
EmployeeDTO employeeDTO=new EmployeeDTO();
POJOCopier.copy(employeeDTO,employee);
employeeDTO.setBasicSalary(vSalary);
if(vGender.equals("Male")) employeeDTO.setGender(EmployeeDTOInterface.MALE);
else employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
employeeDTO.setDesignationCode(vCode);
employeeDAO.add(employeeDTO);
employee.setEmployeeId(employeeDTO.getEmployeeId());
List<EmployeeInterface> employeeList;
if(designationCodeWiseEmployees.containsKey(vCode))
{
employeeList=this.designationCodeWiseEmployees.get(vCode);
employeeList.add(employee);
}
else
{
employeeList=new ArrayList<>();
employeeList.add(employee);
designationCodeWiseEmployees.put(vCode,employeeList);
}
employeeIdWiseMap.put(employee.getEmployeeId(),employee);
panNumberWiseMap.put(employee.getPANNumber(),employee);
aadharCardNumberWiseMap.put(employee.getAadharCardNumber(),employee);
employeeIdWiseList.add(employee);
nameWiseList.add(employee);
designationCodeWiseList.add(employee);
designationWiseList.add(employee);

Collections.sort(nameWiseList,new Comparator<EmployeeInterface>(){
public int compare(EmployeeInterface left,EmployeeInterface right)
{
String leftName=left.getName().toUpperCase();
String rightName=right.getName().toUpperCase();
return leftName.compareTo(rightName);
}
});

Collections.sort(designationWiseList,new Comparator<EmployeeInterface>(){
public int compare(EmployeeInterface left,EmployeeInterface right)
{
String leftDesignation=left.getDesignation().getTitle().toUpperCase();
String rightDesignation=right.getDesignation().getTitle().toUpperCase();

return leftDesignation.compareTo(rightDesignation);
}
});

}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}



public void update(EmployeeInterface employee) throws BLException
{
BLException blException;
blException=new BLException();
if(employee==null)
{
blException.setGenericException("employee should not be null.");
throw blException;
}
DesignationInterface vDesignation=employee.getDesignation();
if(vDesignation==null)
{
blException.setGenericException("designation should not be null");
throw blException;
}
DesignationManager designationManager=DesignationManager.getDesignationManager();
String vEmployeeId=employee.getEmployeeId();
String vName=employee.getName();
int vCode=vDesignation.getCode();
String vTitle=vDesignation.getTitle();
Date vDateOfBirth=employee.getDateOfBirth();
String vGender=employee.getGender();
BigDecimal vSalary=employee.getBasicSalary();
String vPanNumber=employee.getPANNumber();
String vAadharCardNumber=employee.getAadharCardNumber();

if(vEmployeeId==null) blException.addException("ID","Employee Id should not be null.");
if(vEmployeeId!=null)
{
if(vEmployeeId.length()==0) blException.addException("ID","Employee Id length should not be zero.");
if(employeeIdWiseMap.containsKey(vEmployeeId)==false) blException.addException("ID","Employee does not exists.");
}
if(vName==null) blException.addException("NAME","Name should not be null.");
if(vName.length()==0) blException.addException("NAME","Name length should not be zero.");
if(vName.length()>=48) blException.addException("NAME","Name should not exceed 48 characters.");
if(vCode<=0) blException.addException("CODE","Invalid Designation code."); 
if(designationManager.codeExists(vCode)==false) blException.addException("CODE","Designation Code does not exists.");
if(vTitle==null) blException.addException("TITLE","Designation title should not be null.");
if(vTitle!=null)
{
if(vTitle.length()==0) blException.addException("TITLE","Designation title length should not be zero.");
if(designationManager.titleExists(vTitle)==false) blException.addException("TITLE","Designation does not exists.");
String fTitle=designationManager.getByCode(vCode).getTitle();
if(vTitle.equalsIgnoreCase(fTitle)==false)  blException.addException("TITLE","Designation Code and Title mismatch.");
}
if(vDateOfBirth==null) blException.addException("DOB","Date of bith should not be null");
if(vGender==null) blException.addException("GENDER","Gender should not be null");
if(vSalary==null) blException.addException("SALARY","Salary should not be null");
if(vPanNumber==null) blException.addException("PAN_NUMBER","PAN number should not be null");
if(vPanNumber!=null)
{
if(vPanNumber.length()==0) blException.addException("PAN_NUMBER","PAN number length should not be zero.");
}
if(vAadharCardNumber==null) blException.addException("AADHAR_CARD_NUMBER","Aadhar card number should not be null.");
if(vAadharCardNumber!=null)
{
if(vAadharCardNumber.length()==0) blException.addException("AADHAR_CARD_NUMBER","Aadhar card number lenght should not be zero.");
}
if(panNumberWiseMap.containsKey(vPanNumber) && aadharCardNumberWiseMap.containsKey(vAadharCardNumber))
{
if(panNumberWiseMap.get(vPanNumber).getEmployeeId().equals(vEmployeeId)==false && aadharCardNumberWiseMap.get(vAadharCardNumber).getEmployeeId().equals(vEmployeeId)==false) blException.addException("PAN_NUMBER","Employee with PAN Number : "+vPanNumber+" and Aadhar Card Number : "+vAadharCardNumber+" already exists.");
}
if(panNumberWiseMap.containsKey(vPanNumber) && aadharCardNumberWiseMap.containsKey(vAadharCardNumber)==false)
{
if(panNumberWiseMap.get(vPanNumber).getEmployeeId().equals(vEmployeeId)==false) blException.addException("PAN_NUMBER","Employee with PAN Number : "+vPanNumber+" already exists.");
}
if(aadharCardNumberWiseMap.containsKey(vAadharCardNumber) && panNumberWiseMap.containsKey(vPanNumber)==false)
{
if(aadharCardNumberWiseMap.get(vAadharCardNumber).getEmployeeId().equals(vEmployeeId)==false) blException.addException("AADHAR_CARD_NUMBER","Employee with Aadhar Card Number : "+vAadharCardNumber+" already exists.");
}
if(blException.hasGenericException()) throw blException;
if(blException.hasException("ID")) throw blException;
if(blException.hasException("NAME")) throw blException;
if(blException.hasException("TITLE")) throw blException;
if(blException.hasException("CODE")) throw blException;
if(blException.hasException("SALARY")) throw blException;
if(blException.hasException("DOB")) throw blException;
if(blException.hasException("GENDER")) throw blException;
if(blException.hasException("PAN_NUMBER")) throw blException;
if(blException.hasException("AADHAR_CARD_NUMBER")) throw blException;

try
{
EmployeeDAO employeeDAO=new EmployeeDAO();
EmployeeDTO employeeDTO=new EmployeeDTO();
POJOCopier.copy(employeeDTO,employee);
employeeDTO.setBasicSalary(vSalary);
if(vGender.equals("Male")) employeeDTO.setGender(EmployeeDTOInterface.MALE);
else employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
employeeDTO.setDesignationCode(vCode);
employeeDAO.update(employeeDTO);
EmployeeInterface fEmployee=employeeIdWiseMap.get(vEmployeeId);
String fName=fEmployee.getName();
String fDesignation=fEmployee.getDesignation().getTitle();

//POJOCopier.copy(fEmployee,employee);
List<EmployeeInterface> employeeList=designationCodeWiseEmployees.get(fEmployee.getDesignation().getCode());
employeeList.remove(fEmployee);
if(designationCodeWiseEmployees.containsKey(vCode))
{
employeeList=designationCodeWiseEmployees.get(vCode);
employeeList.add(employee);
}
else
{
employeeList=new ArrayList<>();
employeeList.add(employee);
designationCodeWiseEmployees.put(vCode,employeeList);
}
panNumberWiseMap.remove(fEmployee.getPANNumber());
aadharCardNumberWiseMap.remove(fEmployee.getAadharCardNumber());
POJOCopier.copy(fEmployee,employee);
if(vGender=="Male") fEmployee.setGender(EmployeeInterface.MALE);
else fEmployee.setGender(EmployeeInterface.FEMALE);
fEmployee.setBasicSalary(vSalary);
panNumberWiseMap.put(fEmployee.getPANNumber(),fEmployee);
aadharCardNumberWiseMap.put(fEmployee.getAadharCardNumber(),fEmployee);

Collections.sort(nameWiseList,new Comparator<EmployeeInterface>(){
public int compare(EmployeeInterface left,EmployeeInterface right)
{
String leftName=left.getName().toUpperCase();
String rightName=right.getName().toUpperCase();
return leftName.compareTo(rightName);
}
});

Collections.sort(designationWiseList,new Comparator<EmployeeInterface>(){
public int compare(EmployeeInterface left,EmployeeInterface right)
{
String leftDesignation=left.getDesignation().getTitle().toUpperCase();
String rightDesignation=right.getDesignation().getTitle().toUpperCase();

return leftDesignation.compareTo(rightDesignation);
}
});

}catch(DAOException daoException)
{
return;
}

/*
this.designationCodeWiseEmployees=new HashMap<>();
this.employeeIdWiseMap=new HashMap<>();
this.panNumberWiseMap=new HashMap<>();
this.aadharCardNumberWiseMap=new HashMap<>();
this.employeeIdWiseList=new ArrayList<>();
this.nameWiseList=new ArrayList<>();
this.designationCodeWiseList=new ArrayList<>();
this.designationWiseList=new ArrayList<>();
*/
}
public void delete(String employeeId) throws BLException
{
BLException blException;
blException=new BLException();
if(employeeId==null)
{
blException.addException("ID","Employee Id should not be null.");
throw blException;
}
if(employeeId.length()==0) blException.addException("ID","Employee Id should not be zero.");
if(employeeIdWiseMap.containsKey(employeeId)==false) blException.addException("ID","Employee Id does not exists.");
if(blException.hasException("ID")) throw blException;
try
{
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
employeeDAO.delete(employeeId);
EmployeeInterface employee=employeeIdWiseMap.get(employeeId);
int designationCode=employee.getDesignation().getCode();
List<EmployeeInterface> list=designationCodeWiseEmployees.get(designationCode);
list.remove(employee);
employeeIdWiseMap.remove(employeeId);
panNumberWiseMap.remove(employee.getPANNumber());
aadharCardNumberWiseMap.remove(employee.getAadharCardNumber());
employeeIdWiseList.remove(employee);
nameWiseList.remove(employee);
designationCodeWiseList.remove(employee);
designationWiseList.remove(employee);
}catch(DAOException daoException)
{
return;
}
}
public int getCount() throws BLException
{
return this.employeeIdWiseMap.size();
}
public List<EmployeeInterface> getEmployees(EmployeeManagerInterface.ORDER_BY ...orderBy) throws BLException
{
List<EmployeeInterface> list=nameWiseList;
if(orderBy.length==0)
{
list=nameWiseList;
}
if(orderBy.length==1)
{
if(orderBy[0]==EmployeeManagerInterface.EMPLOYEE_ID) list=employeeIdWiseList;
if(orderBy[0]==EmployeeManagerInterface.NAME) list=nameWiseList;
if(orderBy[0]==EmployeeManagerInterface.DESIGNATION) list=designationWiseList;
}
if(orderBy.length==0 || orderBy.length==1 )
{
List<EmployeeInterface> employees=new LinkedList<>();
EmployeeInterface employee;
for(EmployeeInterface e:list)
{
employee=new Employee();
POJOCopier.copy(employee,e);
employee.setBasicSalary(e.getBasicSalary());
if(e.getGender().equals("Male")) employee.setGender(EmployeeInterface.MALE);
else employee.setGender(EmployeeInterface.FEMALE);
employees.add(employee);
}
return employees;
}
int flag=0;
if(orderBy.length>=2)
{
if(orderBy[0]==EmployeeManagerInterface.EMPLOYEE_ID)
{
list=employeeIdWiseList;
flag=0;
}
if(orderBy[0]==EmployeeManagerInterface.NAME && orderBy[1]==EmployeeManagerInterface.EMPLOYEE_ID)
{
list=nameWiseList;
flag=1;
}
if(orderBy[0]==EmployeeManagerInterface.NAME && orderBy[1]==EmployeeManagerInterface.DESIGNATION)
{
list=nameWiseList;
flag=2;
}
if(orderBy[0]==EmployeeManagerInterface.DESIGNATION && orderBy[1]==EmployeeManagerInterface.EMPLOYEE_ID)
{
list=designationWiseList;
flag=3;
}
if(orderBy[0]==EmployeeManagerInterface.DESIGNATION && orderBy[1]==EmployeeManagerInterface.NAME)
{
list=designationWiseList;
flag=4;
}
}
List<EmployeeInterface> employees=new LinkedList<>();
EmployeeInterface employee;
for(EmployeeInterface e:list)
{
employee=new Employee();
POJOCopier.copy(employee,e);
employee.setBasicSalary(e.getBasicSalary());
if(e.getGender().equals("Male")) employee.setGender(EmployeeInterface.MALE);
else employee.setGender(EmployeeInterface.FEMALE);
employees.add(employee);
}
if(flag==0)
{
return employees;
}
if(flag==1)
{
Collections.sort(employees,new Comparator<EmployeeInterface>()
{
public int compare(EmployeeInterface left,EmployeeInterface right)
{
if(left.getName().toUpperCase().equals(right.getName().toUpperCase()))
{
return left.getEmployeeId().compareTo(right.getEmployeeId());
}
return 1;
}
});
}
if(flag==2)
{
Collections.sort(employees,new Comparator<EmployeeInterface>()
{
public int compare(EmployeeInterface left,EmployeeInterface right)
{
if(left.getName().toUpperCase().equals(right.getName().toUpperCase()))
{
return left.getDesignation().getTitle().compareTo(right.getDesignation().getTitle());
}
return 1;
}
});
}
if(flag==3)
{
Collections.sort(employees,new Comparator<EmployeeInterface>()
{
public int compare(EmployeeInterface left,EmployeeInterface right)
{
if(left.getDesignation().getTitle().equalsIgnoreCase(right.getDesignation().getTitle()))
{
return left.getEmployeeId().compareTo(right.getEmployeeId());
}
return 1;
}
});
}
if(flag==4)
{
Collections.sort(employees,new Comparator<EmployeeInterface>()
{
public int compare(EmployeeInterface left,EmployeeInterface right)
{
if(left.getDesignation().getTitle().equalsIgnoreCase(right.getDesignation().getTitle()))
{
return left.getName().toUpperCase().compareTo(right.getName().toUpperCase());
}
return 1;
}
});
}
return employees;
}

public List<EmployeeInterface> testSorting()
{
List<EmployeeInterface> list=nameWiseList;
List<EmployeeInterface> employees=new LinkedList<>();
EmployeeInterface employee;
for(EmployeeInterface e:list)
{
employee=new Employee();
POJOCopier.copy(employee,e);
employee.setBasicSalary(e.getBasicSalary());
if(e.getGender().equals("Male")) employee.setGender(EmployeeInterface.MALE);
else employee.setGender(EmployeeInterface.FEMALE);
employees.add(employee);
}
Collections.sort(employees,new Comparator<EmployeeInterface>()
{
public int compare(EmployeeInterface left,EmployeeInterface right)
{
if(left.getName().toUpperCase().equals(right.getName().toUpperCase()))
{
return left.getDesignation().getTitle().compareTo(right.getDesignation().getTitle());
}
return 1;
}
});
return employees;
}
public EmployeeInterface getByEmployeeId(String employeeId) throws BLException
{
BLException blException=new BLException();
if(employeeId==null)
{
blException.addException("ID","Employee ID should not be null.");
throw blException;
}
if(employeeId.length()==0) blException.addException("ID","Employee Id length should not be zero.");
if(employeeIdWiseMap.containsKey(employeeId)==false) blException.addException("ID","Employee Id does not exists.");
if(blException.hasGenericException()) throw blException;
if(blException.hasException("ID")) throw blException;
EmployeeInterface employee=employeeIdWiseMap.get(employeeId);
EmployeeInterface sourceEmployee=new Employee();
POJOCopier.copy(sourceEmployee,employee);
sourceEmployee.setBasicSalary(employee.getBasicSalary());
String gender=employee.getGender();
if(gender.equals("Male")) sourceEmployee.setGender(EmployeeInterface.MALE);
else sourceEmployee.setGender(EmployeeInterface.FEMALE);
return sourceEmployee;
}
public EmployeeInterface getByPANNumber(String panNumber) throws BLException
{
BLException blException=new BLException();
if(panNumber==null)
{
blException.addException("PAN_NUMBER","PAN Number should not be null.");
throw blException;
}
if(panNumber.length()==0) blException.addException("PAN_NUMBER","PAN Number length should not be zero.");
if(panNumberWiseMap.containsKey(panNumber)==false) blException.addException("PAN_NUMBER","PAN Number does not exists.");
if(blException.hasGenericException()) throw blException;
if(blException.hasException("PAN_NUMBER")) throw blException;
EmployeeInterface employee=panNumberWiseMap.get(panNumber);
EmployeeInterface sourceEmployee=new Employee();
POJOCopier.copy(sourceEmployee,employee);
sourceEmployee.setBasicSalary(employee.getBasicSalary());
String gender=employee.getGender();
if(gender.equals("Male")) sourceEmployee.setGender(EmployeeInterface.MALE);
else sourceEmployee.setGender(EmployeeInterface.FEMALE);
return sourceEmployee;
}
public EmployeeInterface getByAadharCardNumber(String aadharCardNumber) throws BLException
{
BLException blException=new BLException();
if(aadharCardNumber==null)
{
blException.addException("AADHAR_CARD_NUMBER","Aadhar Card Number should not be null.");
throw blException;
}
if(aadharCardNumber.length()==0) blException.addException("AADHAR_CARD_NUMBER","Aadhar Card Number length should not be zero.");
if(aadharCardNumberWiseMap.containsKey(aadharCardNumber)==false) blException.addException("AADHAR_CARD_NUMBER","Aadhar Card Number does not exists.");
if(blException.hasGenericException()) throw blException;
if(blException.hasException("AADHAR_CARD_NUMBER")) throw blException;
EmployeeInterface employee=aadharCardNumberWiseMap.get(aadharCardNumber);
EmployeeInterface sourceEmployee=new Employee();
POJOCopier.copy(sourceEmployee,employee);
sourceEmployee.setBasicSalary(employee.getBasicSalary());
String gender=employee.getGender();
if(gender.equals("Male")) sourceEmployee.setGender(EmployeeInterface.MALE);
else sourceEmployee.setGender(EmployeeInterface.FEMALE);
return sourceEmployee;
}
public boolean designationCodeExists(int designationCode) throws BLException
{
BLException blException=new BLException();
if(designationCode<=0)
{
 blException.addException("CODE","Invalid Designation Code.");
throw blException;
}
DesignationManagerInterface designationManager=DesignationManager.getDesignationManager();
boolean designationExists=designationManager.codeExists(designationCode);
return designationExists;
}
public boolean employeeIdExists(String employeeId) throws BLException
{
BLException blException=new BLException();
if(employeeId==null)
{
blException.addException("ID","Employee ID should not be null.");
throw blException;
}
if(employeeId.length()==0) 
{
blException.addException("ID","Employee Id length should not be zero.");
throw blException;
}
return employeeIdWiseMap.containsKey(employeeId);
}
public boolean panNumberExists(String panNumber) throws BLException
{
BLException blException=new BLException();
if(panNumber==null)
{
blException.addException("PAN_NUMBER","PAN Number should not be null.");
throw blException;
}
if(panNumber.length()==0) 
{
blException.addException("PAN_NUMBER","PAN Number length should not be zero.");
throw blException;
}
return panNumberWiseMap.containsKey(panNumber);

}
public boolean aadharCardNumberExists(String aadharCardNumber) throws BLException
{
BLException blException=new BLException();
if(aadharCardNumber==null)
{
blException.addException("AADHAR_CARD_NUMBER","Aadhar Card Number should not be null.");
throw blException;
}
if(aadharCardNumber.length()==0) 
{
blException.addException("AADHAR_CARD_NUMBER","Aadhar Card Number length should not be zero.");
throw blException;
}
return aadharCardNumberWiseMap.containsKey(aadharCardNumber);
}
public int getEmployeesCountWithDesignation(int code) throws BLException
{
BLException blException;
blException=new BLException();
if(code<=0) blException.addException("CODE","Invalid designaiton code.");
DesignationManagerInterface designationManager=DesignationManager.getDesignationManager();
if(designationManager.codeExists(code)==false) blException.addException("CODE","Designation Code does not exists.");
if(blException.hasException("CODE")) throw blException;
List<EmployeeInterface> list=designationCodeWiseEmployees.get(code);
if(list==null) return 0;
return list.size();
}
public List<EmployeeInterface> getEmployeesWithDesignation(int code) throws BLException
{
BLException blException;
blException=new BLException();
if(code<=0) blException.addException("CODE","Invalid designaiton code.");
DesignationManagerInterface designationManager=DesignationManager.getDesignationManager();
if(designationManager.codeExists(code)==false) blException.addException("CODE","Designation Code does not exists.");
if(blException.hasException("CODE")) throw blException;
List<EmployeeInterface> list;
if(designationCodeWiseEmployees.containsKey(code))
{
list=designationCodeWiseEmployees.get(code);
}
else
{
list=new ArrayList<>();
}
return list;
}
}