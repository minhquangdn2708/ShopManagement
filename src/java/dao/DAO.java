/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.sun.jdi.connect.spi.Connection;
import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Product;
import model.Status;

/**
 *
 * @author FPTSHOP
 */
public class DAO {
    
    public List<Product> getAllProduct(){
        List<Product> list = new ArrayList<>();
        String sql = "select productCode, productName, productStatus, productPrice, productOldPrice, productImages  from Product ";
        try {
            java.sql.Connection connection = new DBContext().getConnect();
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String imagesString = rs.getString(6);
                String[] data = imagesString.split(",");
                List<String> listImages = new ArrayList<>();
                for(String string : data){
                    string = string.replaceAll("'", "");
                    listImages.add(string);
                }
                list.add(new Product(rs.getString(1), 
                        rs.getString(2), 
                       rs.getString(3), 
                       rs.getString(4), 
                     rs.getString(5), 
                      listImages));
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("e");
        }
        return list;
    }
    
    public List<Category> getAllCategory(){
        List<Category> list = new ArrayList<>();
        String sql = "select * from Category";
        try {
            java.sql.Connection connection = new DBContext().getConnect();
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                list.add(new Category(rs.getString(1), 
                        rs.getString(2)));
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("e");
        }
        return list;
    }
    
    public List<Product> getAllProductByCategoryID(String categoryID){
        List<Product> list = new ArrayList<>();
        String sql = "select productCode, productName, productStatus, productPrice, productOldPrice, productImages  from Product where categoryID = ?";
        try {
            java.sql.Connection connection = new DBContext().getConnect();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, categoryID);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String imagesString = rs.getString(6);
                String[] data = imagesString.split(",");
                List<String> listImages = new ArrayList<>();
                for(String string : data){
                    string = string.replaceAll("'", "");
                    listImages.add(string);
                }
                list.add(new Product(rs.getString(1), 
                        rs.getString(2), 
                       rs.getString(3), 
                       rs.getString(4), 
                     rs.getString(5), 
                      listImages));
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("e");
        }
        return list;
    }
    
    public List<Status> getAllStatus(){
        List<Status> list = new ArrayList<>();
        String sql = "select * from Status";
        try {
            java.sql.Connection connection = new DBContext().getConnect();
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                list.add(new Status(rs.getString(1), 
                        rs.getString(2)));
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("e");
        }
        return list;
    }
    
    public List<Product> getAllProductByStatus(String statusName){
        List<Product> list = new ArrayList<>();
        String sql = "select productCode, productName, productStatus, productPrice, productOldPrice, productImages  from Product where productStatus = ?";
        try {
            java.sql.Connection connection = new DBContext().getConnect();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, statusName);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String imagesString = rs.getString(6);
                String[] data = imagesString.split(",");
                List<String> listImages = new ArrayList<>();
                for(String string : data){
                    string = string.replaceAll("'", "");
                    listImages.add(string);
                }
                list.add(new Product(rs.getString(1), 
                        rs.getString(2), 
                       rs.getString(3), 
                       rs.getString(4), 
                     rs.getString(5), 
                      listImages));
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("e");
        }
        return list;
    }
    public static void main(String[] args) {
       DAO dao = new DAO();
       int sum = 0;
       for(Product product : dao.getAllProductByStatus("Available")){
           sum++;
           System.out.println(product);
       }
        
//       for(Category category : dao.getAllCategory()){
//           sum++;
//           System.out.println(category);
//       }
        
        System.out.println("sum = " + sum);
    }
    
}
