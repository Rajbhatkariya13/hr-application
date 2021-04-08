import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
class DesignationDeleteTestCase
{
public static void main(String gg[])
{
int code=Integer.parseInt(gg[0]);
try
{
DesignationDAO designationDAO=new DesignationDAO();
designationDAO.delete(code);
System.out.println("Designation with code: "+code+" deleted.");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}