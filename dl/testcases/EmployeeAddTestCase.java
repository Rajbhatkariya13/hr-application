import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.common.*;
import java.util.*;
import java.text.*;
import java.math.*;
class EmployeeAddTestCase
{
public static void main(String gg[])
{
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
try
{
EmployeeDTOInterface employee=new EmployeeDTO();
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
employee.setName(Keyboard.getString("Enter Name: "));
employee.setDesignationCode(Keyboard.getInt("Enter designation code: "));
String dateOfBirth=Keyboard.getString("Enter D.O.B. (dd/MM/yyyy) : ");
employee.setDateOfBirth(simpleDateFormat.parse(dateOfBirth));
employee.setBasicSalary(new BigDecimal(Keyboard.getString("Enter Salary: ")));
String isIndian=Keyboard.getString("is Indian (Y/N) :");
if(isIndian.equals("Y")==false && isIndian.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
if(isIndian.equals("Y")) employee.isIndian(true);
else employee.isIndian(false);
String gender=Keyboard.getString("Enter gender (M/F) : ");
if(gender.equals("M")==false && gender.equals("F")==false)
{
System.out.println("Invalid input");
return;
}
if(gender.equals("M")) employee.setGender(EmployeeDTOInterface.MALE);
else employee.setGender(EmployeeDTOInterface.FEMALE);
employee.setPANNumber(Keyboard.getString("Enter PAN number: "));
employee.setAadharCardNumber(Keyboard.getString("Enter Aadhar card number: "));
employeeDAO.add(employee);
System.out.println("Employee added with Employee Id: "+employee.getEmployeeId());

/*
employee.setEmployeeId("2");
employee.setName("Mahima JAISWAL");
employee.setDesignationCode(1);
Date date=new Date();
employee.setDateOfBirth(date);
employee.setBasicSalary(new BigDecimal("300002.33"));
employee.isIndian(true);
employee.setGender(EmployeeDTOInterface.MALE);
employee.setPANNumber("8hh446GGJ7");
employee.setAadharCardNumber("52234277");
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
employeeDAO.add(employee);
System.out.println("Employee added with Employee id: "+employee.getEmployeeId());
*/
}catch(DAOException daoException)
{
System.out.println(daoException);
}
catch(ParseException parseException)
{
System.out.println(parseException);
}
}
}