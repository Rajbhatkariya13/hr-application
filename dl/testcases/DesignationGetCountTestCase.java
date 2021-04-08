import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
class DesignationGetCountTestCase
{
public static void main(String gg[])
{
try
{
DesignationDAO designationDAO=new DesignationDAO();
int count=designationDAO.getCount();
System.out.println("No. of Designations : "+count);
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}