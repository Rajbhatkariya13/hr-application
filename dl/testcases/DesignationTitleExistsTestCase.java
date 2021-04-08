import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
class DesignationTitleExistsTestCase
{
public static void main(String gg[])
{
String title=gg[0];
try
{
boolean found;
DesignationDAO designationDAO=new DesignationDAO();
found=designationDAO.titleExists(title);
if(found==true) System.out.println("designation with title : "+title+" exists.");
else System.out.println("designation with title : "+title+" does not exists.");
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}