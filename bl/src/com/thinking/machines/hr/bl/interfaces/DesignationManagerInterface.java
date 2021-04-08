package com.thinking.machines.hr.bl.interfaces;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
public interface DesignationManagerInterface
{
public enum ATTRIBUTE{CODE,TITLE};
public final ATTRIBUTE CODE=ATTRIBUTE.CODE;
public final ATTRIBUTE TITLE=ATTRIBUTE.TITLE;
public void add(DesignationInterface designation) throws BLException;
public void update(DesignationInterface designation) throws BLException;
public void delete(int code) throws BLException;
public List<DesignationInterface> getDesignations(ATTRIBUTE ...orderBy) throws BLException;
public DesignationInterface getByCode(int code) throws BLException;
public DesignationInterface getByTitle(String title) throws BLException;
public boolean codeExists(int code) throws BLException;
public boolean titleExists(String title) throws BLException;
public int getCount() throws BLException;
public boolean isDesignationAttachedToAnEmployee(int code) throws BLException;
}