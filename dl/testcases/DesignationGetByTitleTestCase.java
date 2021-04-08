import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
class DesignationGetByTitleTestCase
{
public static void main(String gg[])
{
String designation=gg[0];
try
{
DesignationDTOInterface designationDTO;
DesignationDAO designationDAO=new DesignationDAO();
designationDTO=designationDAO.getByTitle(designation);
System.out.println("Designation : "+designationDTO.getTitle()+" with code: "+designationDTO.getCode());
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}