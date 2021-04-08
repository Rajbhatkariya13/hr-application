import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import java.text.*;
import java.util.*;
public class EmployeeGetAllTestCase
{
public static void main(String gg[])
{

SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
try
{
List<EmployeeDTOInterface> employees;
employees=new EmployeeDAO().getAll();
for(EmployeeDTOInterface employee:employees)
{
System.out.println("Employee Id : "+employee.getEmployeeId());
System.out.println("Name: "+employee.getName());
System.out.println("Designation code: "+employee.getDesignationCode());
System.out.println("D.O.B. :"+simpleDateFormat.format(employee.getDateOfBirth()));
System.out.println("Salary : "+employee.getBasicSalary().toPlainString());
System.out.println("isIndian : "+employee.isIndian());
System.out.println("Gender : "+employee.getGender());
System.out.println("Pan number: "+employee.getPANNumber());
System.out.println("Aadhar card number: "+employee.getAadharCardNumber());
System.out.println("---------------------------------------------------------------------------");
}
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}