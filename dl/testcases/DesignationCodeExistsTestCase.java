import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
class DesignationCodeExistsTestCase
{
public static void main(String gg[])
{
int code=Integer.parseInt(gg[0]);
try
{
boolean found;
DesignationDAO designationDAO=new DesignationDAO();
found=designationDAO.codeExists(code);
if(found==true) System.out.println("designation with code : "+code+" exists.");
else System.out.println("designation with code : "+code+" does not exists.");
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}