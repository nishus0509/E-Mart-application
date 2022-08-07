/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emart.dao;

import emart.dbutil.DBConnection;
import emart.pojo.ProductsPojo;
import emart.pojo.UserProfile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nishu
 */
public class OrderDAO {
    public static String getNextOrderId()throws SQLException
 {
    Connection conn=DBConnection.getConnection();
    Statement st=conn.createStatement();
    ResultSet rs=st.executeQuery("Select max(order_id) from orders");
    rs.next();
    String ordId=rs.getString(1);
    if(ordId==null)
        return "P101";
    int ordno=Integer.parseInt(ordId.substring(2));
    ordno++;
    return "O-"+ordno;
 }
    public static boolean addOrder(ArrayList<ProductsPojo> al,String ordId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Insert into order values(?,?,?,?)");
        int count=0;
        for(ProductsPojo p:al)
        {
            ps.setString(1, ordId);
            ps.setString(2, p.getProductId());
            ps.setInt(3, p.getQuantity());
            ps.setString(4, UserProfile.getUserid());
            count=count+ps.executeUpdate();
        }
        return count==al.size();
    }
    public static List<String> getAllorderId() throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select order_id from orders order by order_id");
        ArrayList<String> allorderId=new ArrayList<String>();
        while(rs.next())
        {
            allorderId.add(rs.getString(1));
        }
        return allorderId;
    }
    public static List<ProductsPojo> getOrderDetails(String o_id) throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from products where p_id in(select p_id from orders where order_id=?)");
        ps.setString(1,o_id);
        ResultSet rs=ps.executeQuery();
        ArrayList <ProductsPojo> ProductsList=new ArrayList<>();
        while(rs.next()){
             // System.out.println(" "+rs.getString(1)+" "+rs.getString(2) );
            ProductsPojo p=new ProductsPojo();
            p.setProductId(rs.getString(1));
            p.setProductName(rs.getString(2));
            p.setProductCompany(rs.getString(3));
            p.setProductPrice(rs.getDouble(4));
          
            p.setOurPrice(rs.getDouble(5));
            p.setTax(rs.getInt(6));
            p.setQuantity(rs.getInt(7));
            ProductsList.add(p);
        }
       // System.out.println(ProductsList);
        return ProductsList;
    }
}
