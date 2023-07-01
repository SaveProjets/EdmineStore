package fr.edminecoreteam.store.task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.edminecoreteam.store.edorm.MySQL;

public class ShopOfTheWeekData 
{
	private int article_id;
	private String reduction_date;
	private String deadline_date;
	
	public ShopOfTheWeekData(int article_id, String reduction_date, String deadline_date) {
		this.article_id = article_id;
		this.reduction_date = reduction_date;
		this.deadline_date = deadline_date;
	}
	
	public ShopOfTheWeekData() {
		//Aucun constructeur
	}
	
	public void createArticle() 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("INSERT INTO ed_shop_of_week (article_id, reduction_date, deadline_date) VALUES (?, ?, ?)");
            preparedStatement.setInt(1, article_id); /*article_id*/
            preparedStatement.setString(2, reduction_date); /*reduction_date*/
            preparedStatement.setString(3, deadline_date); /*deadline_date*/
            preparedStatement.execute();
            preparedStatement.close();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
	
	public void deleteAllArticles() {
	    try {
	        PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("DELETE FROM ed_shop_of_week");
	        preparedStatement.executeUpdate();
	        preparedStatement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public boolean hisReductionExist() 
	{
	    try 
	    {
	        PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT deadline_date FROM ed_shop_of_week ORDER BY article_id ASC LIMIT 1");
	        ResultSet rs = preparedStatement.executeQuery();
	        return rs.next();
	    }
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public String getDeadLine()
	{
		try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT deadline_date FROM ed_shop_of_week ORDER BY article_id ASC LIMIT 1");
            String result = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) 
            {
            	result = rs.getString("deadline_date");
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
	
	public List<Integer> getRandomArticles() {
	    List<Integer> result = new ArrayList<>();
	    try {
	        PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT article_id FROM ed_shop_articles ORDER BY RAND() LIMIT ?");
	        preparedStatement.setInt(1, 10);
	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            result.add(rs.getInt("article_id"));
	        }
	        preparedStatement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	
	public List<Integer> getAllArticleIds() {
	    List<Integer> articleIdList = new ArrayList<>();
	    try {
	        PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT article_id FROM ed_shop_of_week");
	        ResultSet resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            int articleId = resultSet.getInt("article_id");
	            articleIdList.add(articleId);
	        }
	        preparedStatement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return articleIdList;
	}
}
