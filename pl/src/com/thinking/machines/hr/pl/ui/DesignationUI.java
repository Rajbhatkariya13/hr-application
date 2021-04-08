package com.thinking.machines.hr.pl.ui;
import com.thinking.machines.hr.pl.model.*;
import com.thinking.machines.hr.pl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.pojo.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.JFileChooser.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
public class DesignationUI extends JFrame
{
private JLabel titleLabel;
private JLabel searchCaptionLabel;
private JLabel exceptionLabel;
private JTextField searchTextField;
private JButton clearButton;
private JTable table;
private DesignationModel model;
private Container container;
private JScrollPane jsp;
private JPanel innerPanel;
private JLabel designationCaptionLabel;
private JLabel designationLabel;
private JTextField addTextField;
private JPanel innerPanel2;
private JButton a;
private JButton e;
private JButton c;
private JButton d;
private JButton p;
private char flag='V';
public DesignationUI()
{
model=new DesignationModel();
titleLabel=new JLabel("Designation Master");
searchCaptionLabel=new JLabel("Search");
exceptionLabel=new JLabel("");
searchTextField=new JTextField(20);
clearButton=new JButton(new ImageIcon(getClass().getResource("/images/clear.png")));
table=new JTable(model);
table.setRowHeight(20);
JTableHeader tableHeader=table.getTableHeader();
tableHeader.setFont(new Font("Times New Roman",Font.BOLD,20));
tableHeader.setResizingAllowed(false);
jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
Font titleFont=new Font("Verdana",Font.BOLD,24);
titleLabel.setFont(titleFont);
exceptionLabel.setFont(new Font("Arial",Font.BOLD,12));
exceptionLabel.setForeground(Color.red);
Font dataFont=new Font("Times New Roman",Font.PLAIN,20);
searchCaptionLabel.setFont(dataFont);
searchTextField.setFont(dataFont);
clearButton.setFont(new Font("Times New Roman",Font.BOLD,8));
table.setFont(dataFont);

innerPanel=new JPanel();
designationCaptionLabel=new JLabel("Designation");
designationCaptionLabel.setFont(new Font("Times New Roman",Font.BOLD,22));
designationLabel=new JLabel("");
addTextField=new JTextField();
addTextField.setVisible(false);
addTextField.setFont(new Font("Times New Roman",Font.PLAIN,22));
designationLabel.setFont(new Font("Times New Roman",Font.PLAIN,22));
designationLabel.setForeground(Color.blue);
innerPanel2=new JPanel();
a=new JButton(new ImageIcon(getClass().getResource("/images/add.png")));
e=new JButton(new ImageIcon(getClass().getResource("/images/edit.png")));
c=new JButton(new ImageIcon(getClass().getResource("/images/cancel.png")));
d=new JButton(new ImageIcon(getClass().getResource("/images/delete.png")));
p=new JButton(new ImageIcon(getClass().getResource("/images/print.png")));
innerPanel2.add(a);
innerPanel2.add(e);
innerPanel2.add(c);
innerPanel2.add(d);
innerPanel2.add(p);
innerPanel2.setSize(600,150);
innerPanel2.setLayout(null);
innerPanel2.setBorder(BorderFactory.createLineBorder(Color.gray));
innerPanel.add(designationCaptionLabel);
innerPanel.add(designationLabel);
innerPanel.add(addTextField);
innerPanel.add(innerPanel2);
innerPanel.setSize(650,300);
innerPanel.setLayout(null);
innerPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
container=getContentPane();
container.setLayout(null);
int w=700;
int h=600;
int lm=20;
int tm=10;
titleLabel.setBounds(lm,tm,300,30);
exceptionLabel.setBounds(lm+80+470,tm+30+10,200,20);
searchCaptionLabel.setBounds(lm,tm+30+30,80,30);
searchTextField.setBounds(lm+80+10,tm+30+30,520,30);
clearButton.setBounds(lm+80+10+520+2,tm+30+30,40,30);
jsp.setBounds(lm,tm+30+30+30+10,650,150);
innerPanel.setBounds(lm,tm+30+30+30+10+150+20,650,200);
designationCaptionLabel.setBounds(lm+10,tm+10,150,30);
designationLabel.setBounds(lm+10+150+20,tm+10,300,30);
addTextField.setBounds(lm+10+150+20,tm+10,200,30);
innerPanel2.setBounds(lm+10,tm+10+30+30,590,100);
a.setBounds(lm+50,tm+10,70,60);
e.setBounds(lm+50+70+20,tm+10,70,60);
c.setBounds(lm+50+70+20+70+20,tm+10,70,60);
d.setBounds(lm+50+70+20+70+20+70+20,tm+10,70,60);
p.setBounds(lm+50+70+20+70+20+70+20+70+20,tm+10,70,60);
container.add(titleLabel);
container.add(exceptionLabel);
container.add(searchCaptionLabel);
container.add(searchTextField);
container.add(clearButton);
container.add(jsp);
container.add(innerPanel);

//  if there is no data in table then disable all buttons

if(model.getRowCount()<=0) 
{
e.setEnabled(false);
c.setEnabled(false);
d.setEnabled(false);
p.setEnabled(false);
jsp.setEnabled(false);
searchTextField.setEnabled(false);
clearButton.setEnabled(false);
}

// if there are exist some records

if(model.getRowCount()>0)
{
a.setEnabled(true);
e.setEnabled(true);
c.setEnabled(false);
d.setEnabled(true);
p.setEnabled(true);
jsp.setEnabled(true);
searchTextField.setEnabled(true);
clearButton.setEnabled(true);
}

// add listener to add button

a.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
if(flag=='V')
{
searchTextField.setEnabled(false);
clearButton.setEnabled(false);
jsp.setEnabled(false);
table.setEnabled(false);
e.setEnabled(false);
d.setEnabled(false);
p.setEnabled(false);
c.setEnabled(true);
a.setIcon(new ImageIcon(getClass().getResource("/images/save.png")));
designationLabel.setVisible(false);
addTextField.setVisible(true);
addTextField.requestFocus(true);
flag='A';
}
else
{
String addTextFieldData=addTextField.getText().trim();
if(addTextFieldData.length()==0) JOptionPane.showMessageDialog(null,"Designation Required");
DesignationInterface designation=new Designation();
designation.setCode(0);
designation.setTitle(addTextFieldData);
try
{
model.add(designation);
addTextField.setText("");
searchTextField.setEnabled(true);
clearButton.setEnabled(true);
jsp.setEnabled(true);
table.setEnabled(true);
e.setEnabled(true);
d.setEnabled(true);
p.setEnabled(true);
c.setEnabled(false);
a.setIcon(new ImageIcon(getClass().getResource("/images/add.png")));
int select=model.getRowCount()-1;
table.setRowSelectionInterval(select,select);
table.scrollRectToVisible(table.getCellRect(select,2,false)); 
addTextField.setVisible(false);
designationLabel.setVisible(true);
flag='V';
}catch(ModelException me)
{
addTextField.setText("");
JOptionPane.showMessageDialog(null,me.getMessage());
}
}
}
});

// add listener to cancel button

c.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ev)
{
searchTextField.setEnabled(true);
clearButton.setEnabled(true);
jsp.setEnabled(true);
table.setEnabled(true);
a.setEnabled(true);
e.setEnabled(true);
d.setEnabled(true);
p.setEnabled(true);
c.setEnabled(false);
a.setIcon(new ImageIcon(getClass().getResource("/images/add.png")));
e.setIcon(new ImageIcon(getClass().getResource("/images/edit.png")));
addTextField.setText("");
addTextField.setVisible(false);
designationLabel.setVisible(true);
flag='V';
}
});

//add documentListener to searchTextField

searchTextField.getDocument().addDocumentListener(new DocumentListener()
{
public void changedUpdate(DocumentEvent de)
{
}
public void insertUpdate(DocumentEvent de)
{
searchDesignation();
}
public void removeUpdate(DocumentEvent de)
{
searchDesignation();
}
});

//add actionListener to delete
d.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ev)
{
int rowIndex=table.getSelectedRow();
try
{
if(rowIndex==-1)
{
JOptionPane.showMessageDialog(null,"Please select row !");
return;
}
DesignationInterface designation=model.getDesignationAt(rowIndex);
String title=designation.getTitle();
int selection=JOptionPane.showConfirmDialog(null,"Do you want to delete designation : "+title+" ?","",JOptionPane.YES_NO_OPTION);
if(selection==1) return;
model.delete(designation);
designationLabel.setText("");
}catch(ModelException me)
{
JOptionPane.showMessageDialog(null,me.getMessage(),"",JOptionPane.ERROR_MESSAGE);
return;
}
}
});

//add actionListener to edit
e.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ev)
{
int rowIndex=table.getSelectedRow();
if(rowIndex==-1)
{
JOptionPane.showMessageDialog(null,"Please select row !");
return;
}
try
{
DesignationInterface designation=model.getDesignationAt(rowIndex);
String title=designation.getTitle();
int code=designation.getCode();
if(flag=='V')
{
searchTextField.setEnabled(false);
clearButton.setEnabled(false);
jsp.setEnabled(false);
table.setEnabled(false);
a.setEnabled(false);
e.setEnabled(true);
d.setEnabled(false);
p.setEnabled(false);
c.setEnabled(true);
e.setIcon(new ImageIcon(getClass().getResource("/images/update.png")));
designationLabel.setVisible(false);
addTextField.setVisible(true);
addTextField.setText(title);
addTextField.requestFocus(true);
flag='E';
}
else
{
//done done
String addTextFieldData=addTextField.getText().trim();
if(addTextFieldData.length()==0) JOptionPane.showMessageDialog(null,"Designation Required.");
DesignationInterface vDesignation=new Designation();
vDesignation.setTitle(addTextFieldData);
vDesignation.setCode(code);
model.update(vDesignation);
addTextField.setText("");
searchTextField.setEnabled(true);
clearButton.setEnabled(true);
jsp.setEnabled(true);
table.setEnabled(true);
a.setEnabled(true);
e.setEnabled(true);
d.setEnabled(true);
p.setEnabled(true);
c.setEnabled(false);
e.setIcon(new ImageIcon(getClass().getResource("/images/edit.png")));
int select=rowIndex;
table.setRowSelectionInterval(select,select);
table.scrollRectToVisible(table.getCellRect(select,2,false)); 
addTextField.setVisible(false);
designationLabel.setVisible(true);
flag='V';
}
}catch(ModelException me)
{
JOptionPane.showMessageDialog(null,me.getMessage(),"",JOptionPane.ERROR_MESSAGE);
return;
}
}
});


// add actionListener to print
p.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ev)
{
JFileChooser jfc=new JFileChooser();
FileNameExtensionFilter filter=new FileNameExtensionFilter("Pdf files","pdf");
jfc.setFileFilter(filter);
int select=jfc.showSaveDialog(null);
String fileName="";
String filePath="";
if(select==JFileChooser.APPROVE_OPTION) 
{
fileName=jfc.getSelectedFile().getAbsolutePath();
if(fileName.endsWith(".pdf")==false) fileName+=".pdf";
filePath=jfc.getSelectedFile().getParent();
System.out.println(filePath);
System.out.println(fileName);
File file=new File(filePath);
if(file.exists()==false)
{
JOptionPane.showMessageDialog(null,"Invalid path.","",JOptionPane.ERROR_MESSAGE);
return;
}
file=new File(fileName);
int override=-1;
if(file.exists()) 
{
override=JOptionPane.showConfirmDialog(null,"File exists. Do you want to overwrite it ?","",JOptionPane.YES_NO_OPTION);
if(override==1) return;
}
try
{
model.exportToPdf(file);
}catch(ModelException me)
{
JOptionPane.showMessageDialog(null,me.getMessage(),"",JOptionPane.ERROR_MESSAGE);
return;
}
}
}// actionPerformed ends
});

// add action listener to clear button
clearButton.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ev)
{
searchTextField.setText("");
}
});



ListSelectionModel listSelectionModel=table.getSelectionModel();
table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
listSelectionModel.addListSelectionListener(new ListSelectionListener()
{
public void valueChanged(ListSelectionEvent e)
{
int row=table.getSelectedRow();
try
{
DesignationInterface designation=model.getDesignationAt(row);
designationLabel.setText(designation.getTitle());
}catch(ModelException me)
{
}

}
});


setSize(w,h);
setTitle("HR Automation Tool");
setDefaultCloseOperation(EXIT_ON_CLOSE);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setLocation((d.width/2)-(w/2),(d.height/2)-(h/2)); 
}
public void searchDesignation()
{
DesignationInterface designation;
int index=-1;
try
{
designation=model.getDesignation(searchTextField.getText(),false,false);
index=model.indexOf(designation);
table.setRowSelectionInterval(index,index);
table.scrollRectToVisible(table.getCellRect(index,2,false)); 
exceptionLabel.setText("");
}catch(ModelException me)
{
exceptionLabel.setText("Not found");
}
}
}