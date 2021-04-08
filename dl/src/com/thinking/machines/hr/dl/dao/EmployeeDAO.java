package com.thinking.machines.hr.dl.dao;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import java.util.*;
import java.text.*;
import java.io.*;
import java.util.*;
import java.math.*;
public class EmployeeDAO implements EmployeeDAOInterface 
{
/*
public void add(EmployeeDTOInterface employeeDTO) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
int vId=100000;
String IdString="";
int count=0;
String countString="";
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
IdString=String.format("%-10d",vId);
countString=String.format("%-10d",count);
randomAccessFile.writeBytes(IdString+"\n");
randomAccessFile.writeBytes(countString+"\n");
}
int designationCode=employeeDTO.getDesignationCode();
DesignationDAO designationDAO=new DesignationDAO();
if(!(designationDAO.codeExists(designationCode)))
{
randomAccessFile.close();
throw new DAOException("designation code: "+designationCode+" does not exists.");
}
randomAccessFile.seek(0);
int lastGeneratedId=Integer.parseInt(randomAccessFile.readLine().trim());
count=Integer.parseInt(randomAccessFile.readLine().trim());
String vPanNumber=employeeDTO.getPANNumber();
String vAadharCardNumber=employeeDTO.getAadharCardNumber();
String fPanNumber;
String fAadharCardNumber;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
fPanNumber=randomAccessFile.readLine();
if(vPanNumber.equalsIgnoreCase(fPanNumber))
{
randomAccessFile.close();
throw new DAOException("Employee already exists with Pan Number: "+vPanNumber);
}
fAadharCardNumber=randomAccessFile.readLine();
if(vAadharCardNumber.equalsIgnoreCase(fAadharCardNumber))
{
randomAccessFile.close();
throw new DAOException("Employee already exists with Aadhar Number: "+vAadharCardNumber);
}
}
vId=lastGeneratedId+1;
String vName=employeeDTO.getName();
int vCode=employeeDTO.getDesignationCode();
Date vdob=employeeDTO.getDateOfBirth();
BigDecimal vSalary=employeeDTO.getBasicSalary();
boolean vIndian=employeeDTO.isIndian();
String vGender=employeeDTO.getGender();
String dateString;
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
dateString=sdf.format(vdob);
randomAccessFile.writeBytes("EMP"+vId);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vName);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vCode));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(dateString);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vSalary.toPlainString());
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vIndian));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vGender);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vPanNumber);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vAadharCardNumber);
randomAccessFile.writeBytes("\n");
IdString=String.format("%-10d",vId);
count++;
countString=String.format("%-10d",count);
randomAccessFile.seek(0);
randomAccessFile.writeBytes(IdString+"\n"+countString+"\n");
randomAccessFile.close();
employeeDTO.setEmployeeId("EMP"+IdString.trim());
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
*/
public void add(EmployeeDTOInterface employeeDTO) throws DAOException
{
String vName=employeeDTO.getName();
int vDesignationCode=employeeDTO.getDesignationCode();
Date vDateOfBirth=employeeDTO.getDateOfBirth();
BigDecimal vBasicSalary=employeeDTO.getBasicSalary();
boolean vIsIndian=employeeDTO.isIndian();
String vGender=employeeDTO.getGender();
String vPanNumber=employeeDTO.getPANNumber();
String vAadharCardNumber=employeeDTO.getAadharCardNumber();
boolean isCodeExists=new DesignationDAO().codeExists(vDesignationCode);
if(isCodeExists==false) throw new DAOException("Invalid designation code : "+vDesignationCode);
try
{
String newEmployeeId;
int lastGeneratedId=100000;
int count=0;
File file=new File(EMPLOYEE_DATA_FILE);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.writeBytes(String.format("%-10d",lastGeneratedId));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.format("%-10d",count));
randomAccessFile.writeBytes("\n");
randomAccessFile.seek(0);
}
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
lastGeneratedId=Integer.parseInt(randomAccessFile.readLine().trim());
count=Integer.parseInt(randomAccessFile.readLine().trim());
if(count==0)
{
lastGeneratedId++;
count++;
newEmployeeId="EMP"+lastGeneratedId;
randomAccessFile.writeBytes("EMP"+lastGeneratedId);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vName.trim());
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vDesignationCode));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(simpleDateFormat.format(vDateOfBirth));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vBasicSalary.toPlainString());
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vIsIndian));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vGender);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vPanNumber.trim());
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vAadharCardNumber.trim());
randomAccessFile.writeBytes("\n");
randomAccessFile.seek(0);
randomAccessFile.writeBytes(String.format("%-10d",lastGeneratedId));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.format("%-10d",count));
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
employeeDTO.setEmployeeId(newEmployeeId);
return;
}
boolean foundPanNumber=false;
boolean foundAadharCardNumber=false;
String fPanNumber;
String fAadharCardNumber;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(int i=1;i<=7;i++) randomAccessFile.readLine();
fPanNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(foundPanNumber==false && fPanNumber.equals(vPanNumber))
{
foundPanNumber=true;
}
if(foundAadharCardNumber==false && fAadharCardNumber.equals(vAadharCardNumber))
{
foundAadharCardNumber=true;
}
if(foundPanNumber==true &&foundAadharCardNumber==true)
{
randomAccessFile.close();
break;
}
}
if(foundPanNumber==true && foundAadharCardNumber==true)
{
throw new DAOException("Employee with PAN Number : "+vPanNumber+" and Aadhar Number : "+vAadharCardNumber+" already exists.");
}
if(foundPanNumber==true)
{
randomAccessFile.close();
throw new DAOException("Employee with PAN Number : "+vPanNumber+" already exists.");
}
if(foundAadharCardNumber==true)
{
randomAccessFile.close();
throw new DAOException("Employee with Aadhar card Number : "+vAadharCardNumber+" already exists.");
}
lastGeneratedId++;
count++;
newEmployeeId="EMP"+lastGeneratedId;
randomAccessFile.writeBytes("EMP"+lastGeneratedId);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vName.trim());
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vDesignationCode));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(simpleDateFormat.format(vDateOfBirth));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vBasicSalary.toPlainString());
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vIsIndian));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vGender);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vPanNumber.trim());
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vAadharCardNumber.trim());
randomAccessFile.writeBytes("\n");
randomAccessFile.seek(0);
randomAccessFile.writeBytes(String.format("%-10d",lastGeneratedId));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.format("%-10d",count));
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
employeeDTO.setEmployeeId(newEmployeeId);
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public void update(EmployeeDTOInterface employeeDTO) throws DAOException
{
String vEmployeeId=employeeDTO.getEmployeeId();
String vName=employeeDTO.getName();
int vDesignationCode=employeeDTO.getDesignationCode();
if(!(new DesignationDAO().codeExists(vDesignationCode))) throw new DAOException("Invalid Designation Code: "+vDesignationCode);
Date vDateOfBirth=employeeDTO.getDateOfBirth();
BigDecimal vBasicSalary=employeeDTO.getBasicSalary();
Boolean vIsIndian=employeeDTO.isIndian();
String vGender=employeeDTO.getGender();
String vPanNumber=employeeDTO.getPANNumber();
String vAadharCardNumber=employeeDTO.getAadharCardNumber();
if(vEmployeeId.length()<=3) throw new DAOException("Invalid Employee Id: "+vEmployeeId);
int employeeIdNumericPart;
try
{
employeeIdNumericPart=Integer.parseInt(vEmployeeId.substring(3));
}catch(NumberFormatException numberFormatException)
{
throw new DAOException("Invalid Employee Id: "+vEmployeeId);
}
if(employeeIdNumericPart<=100000) throw new DAOException("Invalid Employee Id: "+vEmployeeId);
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(!file.exists()) throw new DAOException("Invalid Employee Id: "+vEmployeeId);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid Employee Id: "+vEmployeeId);
}
int lastGeneratedId=Integer.parseInt(randomAccessFile.readLine().trim());
if(employeeIdNumericPart>lastGeneratedId)
{
randomAccessFile.close();
throw new DAOException("Invalid Employee Id: "+vEmployeeId);
}
randomAccessFile.readLine();
boolean foundEmployeeId=false;
boolean foundPANNumber=false;
boolean foundAadharCardNumber=false;
String fEmployeeId;
String fPanNumber;
String fAadharCardNumber;
int i;
long positionOfRecordToUpdate=0;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
if(foundEmployeeId==false)
{
positionOfRecordToUpdate=randomAccessFile.getFilePointer();
}
fEmployeeId=randomAccessFile.readLine();
for(i=1;i<=6;i++) randomAccessFile.readLine();
fPanNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(foundEmployeeId==false && fEmployeeId.equals(vEmployeeId))
{
foundEmployeeId=true;
}
if(foundPANNumber==false && fEmployeeId.equals(vEmployeeId)==false && fPanNumber.equalsIgnoreCase(vPanNumber))
{
foundPANNumber=true;
}
if(foundAadharCardNumber==false && fEmployeeId.equals(vEmployeeId)==false && fAadharCardNumber.equalsIgnoreCase(vAadharCardNumber))
{
foundAadharCardNumber=true;
}
if(foundEmployeeId && foundPANNumber && foundAadharCardNumber) break;
}
if(foundEmployeeId==false)
{
randomAccessFile.close();
throw new DAOException("Invalid Employee Id: "+vEmployeeId);
}
if(foundPANNumber==true && foundAadharCardNumber==false)
{
randomAccessFile.close();
throw new DAOException("Employee with PAN Number: "+vPanNumber+" exists.");
}
if(foundAadharCardNumber==true && foundPANNumber==false)
{
randomAccessFile.close();
throw new DAOException("Employee with Aadhar Card Number: "+vAadharCardNumber+" exists.");
}
if(foundAadharCardNumber && foundPANNumber)
{
randomAccessFile.close();
throw new DAOException("Employee with PAN Number: "+vPanNumber+" and Aadhar Card Number: "+vAadharCardNumber+" exists.");
}
randomAccessFile.seek(positionOfRecordToUpdate);
for(i=1;i<=9;i++) randomAccessFile.readLine();
File tmpFile=new File("tmp.tmp");
tmpFile.delete();
RandomAccessFile tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
}
randomAccessFile.seek(positionOfRecordToUpdate);
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
randomAccessFile.writeBytes(vEmployeeId);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vName.trim());
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vDesignationCode));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(simpleDateFormat.format(vDateOfBirth));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vBasicSalary.toPlainString());
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vIsIndian));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vGender);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vPanNumber.trim());
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vAadharCardNumber.trim());
randomAccessFile.writeBytes("\n");
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
long currentIndex=randomAccessFile.getFilePointer();
randomAccessFile.setLength(currentIndex);
randomAccessFile.close();
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public void delete(String employeeId) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(!file.exists()) throw new DAOException("Invalid Employee Id: "+employeeId);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0) 
{
randomAccessFile.close();
throw new 	DAOException("Invalid Employee Id: "+employeeId);
}
randomAccessFile.readLine();
int count=Integer.parseInt(randomAccessFile.readLine().trim());
String fEmployeeId;
boolean found=false;
int i;
long positionOfRecordToDelete=0;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
positionOfRecordToDelete=randomAccessFile.getFilePointer();
fEmployeeId=randomAccessFile.readLine();
for(i=1;i<=8;i++) randomAccessFile.readLine();
if(fEmployeeId.equals(employeeId))
{
found=true;
break;
}
}
if(found==false)
{
randomAccessFile.close();
throw new DAOException("Invalid Employee Id: "+employeeId);
}
File tmpFile=new File("tmp.tmp");
if(tmpFile.exists()) tmpFile.delete();
RandomAccessFile tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
}
randomAccessFile.seek(positionOfRecordToDelete);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine());
randomAccessFile.writeBytes("\n");
}
randomAccessFile.setLength(randomAccessFile.getFilePointer());
randomAccessFile.seek(0);
randomAccessFile.readLine();
count--;
randomAccessFile.writeBytes(String.format("%-10d",count));
randomAccessFile.close();
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public int getCount() throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) return 0;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0) 
{
randomAccessFile.close();
return 0;
}
randomAccessFile.readLine();
int count=Integer.parseInt(randomAccessFile.readLine().trim());
return count;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public List<EmployeeDTOInterface> getAll() throws DAOException
{
List<EmployeeDTOInterface> employees;
employees=new LinkedList<>();
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) return employees;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return employees;
}
EmployeeDTOInterface employeeDTO;
randomAccessFile.readLine();
randomAccessFile.readLine();
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(randomAccessFile.readLine());
employeeDTO.setName(randomAccessFile.readLine());
employeeDTO.setDesignationCode(Integer.parseInt(randomAccessFile.readLine()));
try
{
employeeDTO.setDateOfBirth(simpleDateFormat.parse(randomAccessFile.readLine()));
}catch(ParseException parseException)
{
//NO required any code
}
employeeDTO.setBasicSalary(new BigDecimal(randomAccessFile.readLine()));
employeeDTO.isIndian(new Boolean(randomAccessFile.readLine()));
String gender=randomAccessFile.readLine();
if(gender.equals("Male"))
{
employeeDTO.setGender(EmployeeDTOInterface.MALE);
}
else
{
employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
}
employeeDTO.setPANNumber(randomAccessFile.readLine());
employeeDTO.setAadharCardNumber(randomAccessFile.readLine());
employees.add(employeeDTO);
}
randomAccessFile.close();
return employees;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public EmployeeDTOInterface getByEmployeeId(String employeeId) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) throw new DAOException("Invalid Employee Id: "+employeeId);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0) 
{
randomAccessFile.close();
throw new DAOException("Invalid Employee Id: "+employeeId);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
EmployeeDTOInterface employeeDTO;
String fEmployeeId;
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
int i;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId=randomAccessFile.readLine();
if(fEmployeeId.equalsIgnoreCase(employeeId)==false)
{
for(i=1;i<=8;i++) randomAccessFile.readLine();
continue;
}
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(randomAccessFile.readLine());
employeeDTO.setDesignationCode(Integer.parseInt(randomAccessFile.readLine()));
try
{
employeeDTO.setDateOfBirth(simpleDateFormat.parse(randomAccessFile.readLine()));
}catch(ParseException parseException)
{
//no code required
}
employeeDTO.setBasicSalary(new BigDecimal(randomAccessFile.readLine()));
employeeDTO.isIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
if(randomAccessFile.readLine().equals("Male"))
{
employeeDTO.setGender(EmployeeDTOInterface.MALE);
}
else
{
employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
}
employeeDTO.setPANNumber(randomAccessFile.readLine());
employeeDTO.setAadharCardNumber(randomAccessFile.readLine());
randomAccessFile.close();
return employeeDTO;
}
randomAccessFile.close();
throw new DAOException("Invalid Employee Id: "+employeeId);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public EmployeeDTOInterface getByPANNumber(String panNumber) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) throw new DAOException("Invalid PAN number: "+panNumber);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid PAN number: "+panNumber);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String  fEmployeeId;
String fName;
int fDesignationCode;
Date fDateOfBirth=null;
BigDecimal fBasicSalary;
boolean fIsIndian;
String fGender;
String fPANNumber;
String fAadharCardNumber;
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
EmployeeDTOInterface employeeDTO;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fDesignationCode=Integer.parseInt(randomAccessFile.readLine());
try
{
fDateOfBirth=simpleDateFormat.parse(randomAccessFile.readLine());
}catch(ParseException parseException)
{
//do nothing
}
fBasicSalary=new BigDecimal(randomAccessFile.readLine());
fIsIndian=Boolean.parseBoolean(randomAccessFile.readLine());
fGender=randomAccessFile.readLine();
fPANNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(fPANNumber.equalsIgnoreCase(panNumber))
{
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(fName);
employeeDTO.setDesignationCode(fDesignationCode);
employeeDTO.setDateOfBirth(fDateOfBirth);
employeeDTO.setBasicSalary(fBasicSalary);
employeeDTO.isIndian(fIsIndian);
if(fGender.equals("Male")) employeeDTO.setGender(EmployeeDTOInterface.MALE);
else employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
employeeDTO.setPANNumber(fPANNumber);
employeeDTO.setAadharCardNumber(fAadharCardNumber);
randomAccessFile.close();
return employeeDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid PAN number: "+panNumber);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public EmployeeDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) throw new DAOException("Invalid Aadhar Card number: "+aadharCardNumber);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid Aadhar Card number: "+aadharCardNumber);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String  fEmployeeId;
String fName;
int fDesignationCode;
Date fDateOfBirth=null;
BigDecimal fBasicSalary;
boolean fIsIndian;
String fGender;
String fPANNumber;
String fAadharCardNumber;
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
EmployeeDTOInterface employeeDTO;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fDesignationCode=Integer.parseInt(randomAccessFile.readLine());
try
{
fDateOfBirth=simpleDateFormat.parse(randomAccessFile.readLine());
}catch(ParseException parseException)
{
//do nothing
}
fBasicSalary=new BigDecimal(randomAccessFile.readLine());
fIsIndian=Boolean.parseBoolean(randomAccessFile.readLine());
fGender=randomAccessFile.readLine();
fPANNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(fAadharCardNumber.equals(aadharCardNumber))
{
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(fName);
employeeDTO.setDesignationCode(fDesignationCode);
employeeDTO.setDateOfBirth(fDateOfBirth);
employeeDTO.setBasicSalary(fBasicSalary);
employeeDTO.isIndian(fIsIndian);
if(fGender.equalsIgnoreCase("Male")) employeeDTO.setGender(EmployeeDTOInterface.MALE);
else employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
employeeDTO.setPANNumber(fPANNumber);
employeeDTO.setAadharCardNumber(fAadharCardNumber);
randomAccessFile.close();
return employeeDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid Aadhar Card number: "+aadharCardNumber);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public boolean employeeIdExists(String employeeId) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String  fEmployeeId;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId=randomAccessFile.readLine();
if(fEmployeeId.equalsIgnoreCase(employeeId))
{
randomAccessFile.close();
return true;
}
for(int i=1;i<=8;i++) randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
//done done
public boolean designationCodeExists(int designationCode) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fDesignationCode;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
randomAccessFile.readLine();
fDesignationCode=Integer.parseInt(randomAccessFile.readLine());
if(fDesignationCode==designationCode)
{
randomAccessFile.close();
return true;
}
for(int i=1;i<=6;i++) randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}



public boolean panNumberExists(String panNumber) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String  fPANNumber;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(int i=1;i<=7;i++) randomAccessFile.readLine();
fPANNumber=randomAccessFile.readLine();
if(fPANNumber.equalsIgnoreCase(panNumber))
{
randomAccessFile.close();
return true;
}
randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String  fAadharCardNumber;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(int i=1;i<=8;i++) randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(fAadharCardNumber.equalsIgnoreCase(aadharCardNumber))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
}