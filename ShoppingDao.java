package com.virtusa.shoppersden.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;

import javax.servlet.RequestDispatcher;

import com.virtusa.shoppersden.dao.*;
import com.virtusa.shoppersden.models.*;

import com.virtusa.shoppersden.util.DBUtils;

public class ShoppingDao {
	public boolean doLogin(LoginDetails login)  {
	String username = login.getUserName();
	String password = login.getPassword();
	boolean status = false;
	//String str="";
	Connection con;
	try {
		con = DBUtils.getConnection();
		PreparedStatement ps=con.prepareStatement("SELECT name,password FROM shoppersden.users where name=? and password=? ");
		ps.setString(1,username);
		ps.setString(2, password);
		ResultSet rs=ps.executeQuery(); 
		System.out.println("hi");
		status=rs.next(); 
		System.out.println(status);
		
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return status;
	}
		
	public String doRegistration(CustomerDetails details)  {
		String name=details.getCustomerName();
		String email=details.getCustomerEmail();
		String pwd=details.getCustomerPassword();
		String address=details.getCustomerAddress();
		String DOB=details.getDob();
		String contact=details.getContact();
		String city=details.getCity();
		String state=details.getState();
		Connection con ;
		
		
		
		String str="insert into shoppersden.users(name,email,password,address,DOB,contact,city,state)  values(?,?,?,?,?,?,?,?)";
		String str1="select name from shoppersden.users where email=?";
		int count=0;
		try {
			con=DBUtils.getConnection();
			PreparedStatement pstmt1;
			pstmt1 = con.prepareStatement(str1);
			pstmt1.setString(1, email);
			ResultSet rs=pstmt1.executeQuery();
			boolean status=rs.next();
			if(status) {
				
				return details.getCustomerEmail();	
		}
		else
		{
			PreparedStatement pstmt=con.prepareStatement(str);
			pstmt.setString(1, name);
			pstmt.setString(2,email);
			pstmt.setString(3, pwd);
			pstmt.setString(4,address);
			pstmt.setString(5, DOB);
			pstmt.setString(6, contact);
			pstmt.setString(7, city);
			pstmt.setString(8, state);
			count=pstmt.executeUpdate();
			
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return details.getCustomerName();
		
	}
	public boolean doAdminLogin(LoginDetails login)  {
		String name=login.getUserName();
		String pwd=login.getPassword();
		//String str1=null;
		String str="select a_nae,a_pwd from shoppersden.admin where a_nae=? and a_pwd=?";
		Connection con;
		boolean status = false;
		try {
			con = DBUtils.getConnection();
			PreparedStatement ps=con.prepareStatement(str);
			ps.setString(1,name);
			ps.setString(2, pwd);
			ResultSet rs=ps.executeQuery(); 
			status=rs.next();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
		//return str1;
		
		
		
	}
   
	public String doCategoryDetails(CategoryDetails details) {
		int id=details.getCid();
		String name=details.getCname();
		String desc=details.getCdesc();
		String str="insert into shoppersden.category values(?,?,?)";
		Connection con;
		try {
			con = DBUtils.getConnection();
			PreparedStatement pstmt=con.prepareStatement(str);
			pstmt.setInt(1, id);
			pstmt.setString(2,name);
			pstmt.setString(3, desc);
			int count=pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return details.getCname();
	}
    public String doDeleteCategoryDetails(CategoryDetails details)  {
		int id=details.getCid();
		String str="delete from shoppersden.category where cid="+id;
		Connection con;
		try {
			con = DBUtils.getConnection();
			Statement stmt=con.createStatement();
			int count=stmt.executeUpdate(str);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return details.getCname();
	}
    public String doAddItems(ProductDetails details) {
    	int  p_c_id=details.getP_c_id();
		int pid=details.getPid();
		String pname=details.getPname();
		String pdesc=details.getPdesc();
		float price=details.getPrice();
		int qnty=details.getQnty();
		String img_url=details.getImg_url();
		String date=details.getDate();
		
		String str="insert into shoppersden.products(pdate,img_url,p_c_id,pdesc,pid,pname,price,qnty)  values(?,?,?,?,?,?,?,?)";
		Connection con;
		try {
			con = DBUtils.getConnection();
			PreparedStatement pstmt=con.prepareStatement(str);
			pstmt.setString(1, date);
			pstmt.setString(2,img_url);
			pstmt.setInt(3, p_c_id);
			pstmt.setString(4,pdesc);
			pstmt.setInt(5, pid);
			pstmt.setString(6, pname);
			pstmt.setFloat(7,price);
			pstmt.setInt(8,qnty );
			int count=pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return details.getPname();
	}
    public int doDeleteItems(ProductDetails details) {
    	int id=details.getPid();
		String str="delete from shoppersden.category where pid="+id;
		Connection con;
		try {
				con = DBUtils.getConnection();
			
			Statement stmt=con.createStatement();
			int count=stmt.executeUpdate(str);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		return details.getPid();
  	}
    public int doUpdateItem(int pcid, int pid, int upid) {
		String str="update shoppersden.products set pid="+upid+" where p_c_id="+pcid+" and pid="+pid;
		Connection con;
		try {
				con = DBUtils.getConnection();
				Statement stmt=con.createStatement();
			//PreparedStatement pstmt=con.prepareStatement(str);
			//pstmt.setInt(1, upid);
			//pstmt.setInt(2, pcid);
			//pstmt.setInt(3, pid);
			int count=stmt.executeUpdate(str);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return upid;
	}
    public int doUpdatePCIDItem(int pcid, int pid, int upcid) {
    	String str="update shoppersden.products set p_c_id="+upcid+" where pid="+pid+" and p_c_id="+pcid;
		Connection con;
		try {
				con = DBUtils.getConnection();
				Statement stmt=con.createStatement();
			int count=stmt.executeUpdate(str);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return upcid;
	}
    public String doUpdatePNameItem(int pcid, int pid, String pname) {
    	String str="update shoppersden.products set pname="+pname+" where pid="+pid+" and p_c_id="+pcid;
		Connection con;
		try {
				con = DBUtils.getConnection();
				Statement stmt=con.createStatement();
			int count=stmt.executeUpdate(str);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pname;
	}
    public String doUpdatePDescItem(int pcid, int pid, String pdesc) {
    	String str="update shoppersden.products set pdesc="+pdesc+" where pid="+pid+" and p_c_id="+pcid;
		Connection con;
		try {
				con = DBUtils.getConnection();
				Statement stmt=con.createStatement();
			int count=stmt.executeUpdate(str);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pdesc;
	}
    public Float doUpdatePriceItem(int pcid, int pid, Float price) {
    	String str="update shoppersden.products set price="+price+" where pid="+pid+" and p_c_id="+pcid;
		Connection con;
		try {
				con = DBUtils.getConnection();
				Statement stmt=con.createStatement();
			int count=stmt.executeUpdate(str);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return price;
	}
    public int doUpdateQuantItem(int pcid, int pid, int quant) {
    	String str="update shoppersden.products set qnty="+quant+" where pid="+pid+" and p_c_id="+pcid;
		Connection con;
		try {
			con = DBUtils.getConnection();
			Statement stmt=con.createStatement();
			int count=stmt.executeUpdate(str);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return quant;
	}
    public String doUpdateDateItem(int pcid, int pid, String date) {
    	String str="update shoppersden.products set pdate="+date+" where pid="+pid+" and p_c_id="+pcid;
		Connection con;
		try {
				con = DBUtils.getConnection();
			PreparedStatement pstmt=con.prepareStatement(str);
			pstmt.setString(1, date);
			pstmt.setInt(2, pid);
			pstmt.setInt(3, pcid);
			int count=pstmt.executeUpdate(str);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
    public String doUpdateImageURLItem(int pcid, int pid, String image_url) {
    	String str="update shoppersden.products set img_url="+image_url+" where pid="+pid+" and p_c_id="+pcid;
		Connection con;
		try {
				con = DBUtils.getConnection();
			PreparedStatement pstmt=con.prepareStatement(str);
			pstmt.setString(1, image_url);
			pstmt.setInt(2, pid);
			pstmt.setInt(3, pcid);
			int count=pstmt.executeUpdate(str);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image_url;
	}
    
}
