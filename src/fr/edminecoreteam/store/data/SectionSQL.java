package fr.edminecoreteam.store.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.edminecoreteam.store.edorm.MySQL;

public class SectionSQL 
{
	public static void createSection(String sectionName) 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("INSERT INTO ed_shop_type (section_type, section_is_in_reduction, section_reduction_percentage, section_status) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, sectionName); /*section_type*/
            preparedStatement.setString(2, "disable"); /*section_is_in_reduction*/
            preparedStatement.setInt(3, 0); /*section_reduction_percentage*/
            preparedStatement.setString(4, "disable"); /*section_status*/
            preparedStatement.execute();
            preparedStatement.close();
        }
        catch (SQLException e) 
        {
            e.toString();
        }
    }
    
    public static void removeSection(String sectionName) 
    {
        try 
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("DELETE FROM ed_shop_type WHERE section_type = ?");
            ps.setString(1, sectionName);
            ps.execute();
            ps.close();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public static String getSectionIsInReduction(String type) 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT section_is_in_reduction FROM ed_shop_type WHERE section_type = ?");
            preparedStatement.setString(1, type);
            String result = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) 
            {
            	result = rs.getString("section_is_in_reduction");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            return "";
        }
    }
    
    public static String getSectionStatus(String type) 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT section_status FROM ed_shop_type WHERE section_type = ?");
            preparedStatement.setString(1, type);
            String result = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) 
            {
            	result = rs.getString("section_status");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            return "";
        }
    }
    
    public static int getSectionReduction(String type) 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT section_reduction_percentage FROM ed_shop_type WHERE section_type = ?");
            preparedStatement.setString(1, type);
            int result = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) 
            {
            	result = rs.getInt("section_reduction_percentage");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static int getSectionPages(String type) 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT section_pages FROM ed_shop_type WHERE section_type = ?");
            preparedStatement.setString(1, type);
            int result = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) 
            {
            	result = rs.getInt("section_pages");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static void updateSectionIsInReduction(String type, String value) 
    {
        try 
        {
        	PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_shop_type SET section_is_in_reduction = ? WHERE section_type = ?");
            preparedStatement.setString(1, value);
            preparedStatement.setString(2, type);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) 
        {
            e.toString();
        }
	}
    
    public static void updateSectionPages(String type, int value) 
    {
        try 
        {
        	PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_shop_type SET section_pages = ? WHERE section_type = ?");
            preparedStatement.setInt(1, value);
            preparedStatement.setString(2, type);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) 
        {
            e.toString();
        }
	}
    
    public static void updateSectionStatus(String type, String value) 
    {
        try 
        {
        	PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_shop_type SET section_status = ? WHERE section_type = ?");
            preparedStatement.setString(1, value);
            preparedStatement.setString(2, type);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) 
        {
            e.toString();
        }
	}
    
    public static void updateSectionReduction(String type, int value) 
    {
        try 
        {
        	PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_shop_type SET section_reduction_percentage = ? WHERE section_type = ?");
            preparedStatement.setInt(1, value);
            preparedStatement.setString(2, type);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) 
        {
            e.toString();
        }
	}
    
    public static List<String> getSectionList(String status) 
    {
	     List<String> List = new ArrayList<String>();
	        try 
	        {
	            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT section_type FROM ed_shop_type WHERE section_status = ?");
	            ps.setString(1, status);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	            	List.add(rs.getString("section_type"));
	            }
	            ps.close();
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	            return List;
	        }
	        return List;
	}
    
    public static List<Integer> getArticleListByType(String type, String status) 
    {
	     List<Integer> List = new ArrayList<Integer>();
	        try 
	        {
	            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT article_id FROM ed_shop_articles WHERE article_type = ? and article_status = ?");
	            ps.setString(1, type);
	            ps.setString(2, status);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	            	List.add(rs.getInt("article_id"));
	            }
	            ps.close();
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	            return List;
	        }
	        return List;
	}
    
    public static List<Integer> getArticleListBySubType(String type, String status) 
    {
	     List<Integer> List = new ArrayList<Integer>();
	        try 
	        {
	            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT article_id FROM ed_shop_articles WHERE article_sub_type = ? and article_status = ?");
	            ps.setString(1, type);
	            ps.setString(2, status);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	            	List.add(rs.getInt("article_id"));
	            }
	            ps.close();
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	            return List;
	        }
	        return List;
	}
}
