import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
class DesignationGetByCodeTestCase
{
public static void main(String gg[])
{
int code=Integer.parseInt(gg[0]);
try
{
DesignationDTOInterface designationDTO;
DesignationDAO designationDAO=new DesignationDAO();
designationDTO=designationDAO.getByCode(code);
System.out.println("Designation : "+designationDTO.getTitle()+" with code: "+designationDTO.getCode());
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}