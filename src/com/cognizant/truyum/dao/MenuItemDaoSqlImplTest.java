package com.cognizant.truyum.dao;
import com.cognizant.truyum.model.*;
import com.cognizant.truyum.util.Dateutil;

import java.util.*;

public class MenuItemDaoSqlImplTest {
	
	void testGetMenuItemListAdmin()
	{
		MenuItemDao menuItemDao=new MenuItemDaoSqllmpl();
		List<MenuItem>menuItemList=menuItemDao.getMenuItemListAdmin();
		for(MenuItem x:menuItemList)
		System.out.println(x);
	}
	void testGetMenuItemListCustomer()
	{
		MenuItemDao menuItemDao=new MenuItemDaoSqllmpl();
		List<MenuItem> menuItemList=null;
		try {
			menuItemList = menuItemDao.getMenuItemListCustomer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(MenuItem x:menuItemList)
		System.out.println(x);
	}
	void testModifyMenuItem() throws Exception
	{
		MenuItemDaoSqllmpl menuItemDao=new MenuItemDaoSqllmpl();
		MenuItem menuItem=new MenuItem(1,"Sandwitch","Snacks",(float)39.00,true,true,Dateutil.convertToDate("15/03/2017"));
		menuItemDao.editMenuItem(menuItem);
		menuItemDao.getMenuItem(menuItem.getId());
		System.out.println("Item Modified");
	}
	public static void main(String[] args) {
	
	
		MenuItemDaoSqlImplTest test=new MenuItemDaoSqlImplTest();
		test.testGetMenuItemListAdmin();
	
		try {
			test.testModifyMenuItem();
			System.out.println("Customer's List");
			test.testGetMenuItemListCustomer();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
