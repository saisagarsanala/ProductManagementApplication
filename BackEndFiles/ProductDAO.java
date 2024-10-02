package com.sathya.servlet;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class ProductDAO {
//	data access object
	
	public static int save(product pro){
		
		
		Connection con=null;
		PreparedStatement  ps = null;
		int count =0;
		
		try {
			con = ProductDB.dbconnect();
			
			ps = con.prepareStatement("insert into product_data values( ? , ? , ? , ? ,? ,? ,? ,? ,?,? )");	
			ps.setString(1,pro.getProid());
			ps.setString(2, pro.getProname());
			ps.setDouble(3, pro.getProPrice());
			ps.setString(4,pro.getProBrand());
			ps.setString(5,pro.getProMadein());
			ps.setDate(6, pro.getProMfgDate());
			ps.setDate(7,pro.getProExpDate());
			ps.setBytes(8,pro.getImageWork());
			ps.setBytes(9,pro.getProAudio());
			ps.setBytes(10,pro.getProVideo());
			
			
			
			count =  ps.executeUpdate();
			System.out.println("rowCount "+count);
			
			
			}catch(SQLException s) {
				
				s.printStackTrace();
				
			}finally {
				
				try {
				if(con!=null && ps!=null) {
					con.close();
					ps.close();
					}
				}catch(SQLException s) {
					s.printStackTrace();
				}
				}
			return count;
		
	}
	public static List<product>findAll() {
		
		List<product> prolist = new ArrayList<product>();
//		Product product = null;
		
		try(Connection connection = ProductDB.dbconnect();
			Statement statement = connection.createStatement()) {
			
			ResultSet resultSet = statement.executeQuery("Select * from product_data");
			
			while(resultSet.next()) {
				
//				new Product(resultSet.getString(1),resultSet.getString(2),resultSet.getDouble(3),resultSet.getString(4),
//						resultSet.getString(5),resultSet.getDate(6),resultSet.getDate(7),null);
				
				product product = new product();
				
				product.setProid(resultSet.getString(1));
				product.setProname(resultSet.getString(2));
				product.setProPrice(resultSet.getDouble(3));
				product.setProBrand(resultSet.getString(4));
				product.setProMadein(resultSet.getString(5));
				product.setProMfgDate(resultSet.getDate(6));
				product.setProExpDate(resultSet.getDate(7));
				product.setImageWork(resultSet.getBytes(8));
				product.setProAudio(resultSet.getBytes(9));
				product.setProVideo(resultSet.getBytes(10));
				
				
				
				prolist.add(product);
			}
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return prolist;
	}
	public  static int DeletebyId(String ProId) {
		
		int DeleteResult = 0;
		Connection connection= null;
		PreparedStatement ps= null;
		
		try {
			connection = ProductDB.dbconnect();
			ps = connection.prepareStatement("delete from product_data where PROID=?");
			ps.setString(1,ProId);
			DeleteResult = ps.executeUpdate();
			
		}
		catch(SQLException e){
			e.printStackTrace();
			
		}
		return DeleteResult;
		
		
	}
	public static product FindbyId(String proid) {
		
		product product = null;
		
		try (Connection connection = ProductDB.dbconnect();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from product_data where proid=?"))
		{
			
			
			preparedStatement.setString(1,proid);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				
				
				 product = new product();
				
				product.setProid(resultSet.getString(1));
				product.setProname(resultSet.getString(2));
				product.setProPrice(resultSet.getDouble(3));
				product.setProBrand(resultSet.getString(4));
				product.setProMadein(resultSet.getString(5));
				product.setProMfgDate(resultSet.getDate(6));
				product.setProExpDate(resultSet.getDate(7));
				product.setImageWork(resultSet.getBytes(8));
				product.setProAudio(resultSet.getBytes(9));
				product.setProVideo(resultSet.getBytes(10));	
				
				}			
		}
		catch(SQLException e){
		e.printStackTrace();	
		}
		
		
		
		return product;
		
	}
	
	public int updateById(product product) {
		
		int updateResult=0;
		//get the connection.
		try(Connection connection = ProductDB.dbconnect();
			PreparedStatement preparedStatement = connection.prepareStatement("update product_data set proName = ?,proprice = ?,proBrand = ?,proMadeIn = ?,proMfgDate=?,proExpDate=?, proImage=? , proAudio=? , proVideo=? where proid = ?")){
					
			
			preparedStatement.setString(1, product.getProname());
			preparedStatement.setDouble(2, product.getProPrice());
			preparedStatement.setString(3, product.getProBrand());
			preparedStatement.setString(4, product.getProMadein());
			preparedStatement.setDate(5, product.getProMfgDate());
			preparedStatement.setDate(6, product.getProExpDate());
			preparedStatement.setBytes(7, product.getImageWork());
			preparedStatement.setBytes(8, product.getProAudio());
			preparedStatement.setBytes(9, product.getProVideo());
			preparedStatement.setString(10, product.getProid());
			
			
			updateResult = preparedStatement.executeUpdate();
			
			}
		catch(SQLException s) {
			s.printStackTrace();
		}
		return updateResult;
	}

}
