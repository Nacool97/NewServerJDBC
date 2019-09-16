package com.cognizant.truyum.dao;
import defaultpackage.*;
import java.sql.Connection;
import java.sql.Date;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cognizant.truyum.model.MenuItem;
import com.mysql.jdbc.Statement;

public class MenuItemDaoSqllmpl implements MenuItemDao {

	@Override
	public ArrayList<MenuItem> getMenuItemListCustomer() throws Exception {
		Connection conn=ConnectionHandler.getConnection();
		ArrayList<MenuItem>menuItemList=new ArrayList<MenuItem>();
		PreparedStatement ps=conn.prepareStatement("select*from menu_item where me_active='Yes' AND me_date_of_launch<='2019-09-13'");
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			boolean active,free;
			if(rs.getString("me_active").equalsIgnoreCase("yes"))
			active=true;
			else
				active=false;
			if(rs.getString("me_free_delivery").equalsIgnoreCase("yes"))
				free=true;
				else
					free=false;
			MenuItem menu=new MenuItem(rs.getLong("me_id"),rs.getNString("me_name"),rs.getNString("me_category"),rs.getFloat("me_price"),active,free,rs.getDate("me_date_of_launch"));
			menuItemList.add(menu);
		}
		return menuItemList;
	}

	@Override
	public ArrayList<MenuItem> getMenuItemListAdmin() {
		Connection conn=ConnectionHandler.getConnection();
		ArrayList<MenuItem> menuItemList=new ArrayList<MenuItem>(); 
		try {
			PreparedStatement stmt=conn.prepareStatement("select*from menu_item");
			ResultSet rs=stmt.executeQuery();  
			while(rs.next()){  
				boolean active,free;
				if(rs.getString("me_active").equalsIgnoreCase("yes"))
				active=true;
				else
					active=false;
				if(rs.getString("me_free_delivery").equalsIgnoreCase("yes"))
					free=true;
					else
						free=false;
				MenuItem menu=new MenuItem(rs.getLong("me_id"),rs.getNString("me_name"),rs.getNString("me_category"),rs.getFloat("me_price"),active,free,rs.getDate("me_date_of_launch"));
				menuItemList.add(menu);
			}  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuItemList;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
	
		
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		MenuItem menu=null;
		
		Connection conn=ConnectionHandler.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement("select * from menu_item where me_id=?");
			ps.setLong(1, menuItemId);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				boolean active,free;
				if(rs.getString("me_active").equalsIgnoreCase("yes"))
				active=true;
				else
					active=false;
				if(rs.getString("me_free_delivery").equalsIgnoreCase("yes"))
					free=true;
					else
						free=false;
				menu=new MenuItem(rs.getLong("me_id"),rs.getNString("me_name"),rs.getNString("me_category"),rs.getFloat("me_price"),active,free,rs.getDate("me_date_of_launch"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return menu;
	}
	public void editMenuItem(MenuItem menuItem) {
		long id = menuItem.getId();
		java.util.Date udate=menuItem.getDateOflaunch();
		java.sql.Date sdate=new java.sql.Date(udate.getTime());
		Connection conn=ConnectionHandler.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement("update menu_item set me_name=?,me_category=?,me_price=?,me_active=?,me_free_delivery=?,me_date_of_launch=? where me_id = ? ");
			ps.setString(1,menuItem.getName());
			ps.setString(2, menuItem.getCategory());
			ps.setFloat(3, menuItem.getPrice());
			ps.setString(4, (menuItem.getActive()?"Yes":"No"));
			ps.setString(5, (menuItem.getFreeDelivery()?"Yes":"No"));
			ps.setDate(6, sdate);
			
			ps.setLong(7, menuItem.getId());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}