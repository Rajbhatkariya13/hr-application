import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.common.*;
import java.text.*;
public class EmployeeGetByAadharCardNumberTestCase
{
public static void main(String gg[])
{
try
{
String aadharCardNumber=Keyboard.getString("Enter the Aadhar Card number : ");
EmployeeDTOInterface employeeDTO=new EmployeeDAO().getByAadharCardNumber(aadharCardNumber);
System.out.println("Employee Id: "+employeeDTO.getEmployeeId());
System.out.println("Name: "+employeeDTO.getName());
System.out.println("Designation Code: "+employeeDTO.getDesignationCode());
System.out.println("D.O.B. : "+ new SimpleDateFormat("dd/MM/yyyy").format(employeeDTO.getDateOfBirth()));
System.out.println("Basic Salary: "+employeeDTO.getBasicSalary().toPlainString());
System.out.println("Gender: "+employeeDTO.getGender());
System.out.println("PAN Number: "+employeeDTO.getPANNumber());
System.out.println("Aadhar Card Number: "+employeeDTO.getAadharCardNumber());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}