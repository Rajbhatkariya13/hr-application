import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dao.*;
public class EmployeeGetCountTestCase
{
public static void main(String gg[])
{
try
{
System.out.println("Total number of employees: "+new EmployeeDAO().getCount());
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}