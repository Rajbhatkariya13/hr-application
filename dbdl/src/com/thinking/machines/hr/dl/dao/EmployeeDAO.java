package com.thinking.machines.hr.dl.dao;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import java.util.*;
import java.text.*;
import java.io.*;
import java.util.*;
import java.math.*;
public class EmployeeDAO implements EmployeeDAOInterface 
{
public EmployeeDAO()
{
//some code
}
public void add(EmployeeDTOInterface employeeDTO) throws DAOException
{
throw new DAOException("Not yet implemented.");
}
public void update(EmployeeDTOInterface employeeDTO) throws DAOException
{
throw new DAOException("Not yet implemented.");
}
public void delete(String employeeId) throws DAOException
{
throw new DAOException("Not yet implemented.");
}
public int getCount() throws DAOException
{
throw new DAOException("Not yet implemented.");
}
public List<EmployeeDTOInterface> getAll() throws DAOException
{
throw new DAOException("Not yet implemented.");
}
public EmployeeDTOInterface getByEmployeeId(String employeeId) throws DAOException
{
throw new DAOException("Not yet implemented.");
}
public EmployeeDTOInterface getByPANNumber(String panNumber) throws DAOException
{
throw new DAOException("Not yet implemented.");
}
public EmployeeDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException
{
throw new DAOException("Not yet implemented.");
}
public boolean designationCodeExists(int designationCode) throws DAOException
{
throw new DAOException("Not yet implemented.");
}
public boolean employeeIdExists(String employeeId) throws DAOException
{
throw new DAOException("Not yet implemented.");
}
public boolean panNumberExists(String panNumber) throws DAOException
{
throw new DAOException("Not yet implemented.");
}
public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException
{
throw new DAOException("Not yet implemented.");
}
}