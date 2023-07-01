package fr.edminecoreteam.store.edorm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.plugin.Plugin;

import fr.edminecoreteam.store.Main;
public class MySQL 
{
	private MySQL instance;
	private Main api;
	private String urlBase;
	private String host;
	private String database;
	private String userName;
	private String password;
	private static Connection connection;
	
	public MySQL(Main api, String urlBase, String host, String database, String userName, String password) {
		this.api = api;
		this.urlBase = urlBase;
		this.host = host;
		this.database = database;
		this.userName = userName;
		this.password = password;
	}
	
	public static Connection getConnection() { return MySQL.connection; }
	
	public void connexion() {
        if (!this.isOnline()) {
            try {
            	instance = this;
            	
                MySQL.connection = DriverManager.getConnection(String.valueOf(this.urlBase) + this.host + "/" + this.database, this.userName, this.password);
                
                SQLTasks start = new SQLTasks(api, instance);
                start.runTaskTimer((Plugin)this.api, 0L, 20L);
                
                message("connectMSG");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void deconnexion() {
        if (this.isOnline()) {
            try {
                MySQL.connection.close();
                
                message("disconnectMSG");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
	public boolean isOnline() {
		try 
		{
			if (MySQL.connection == null || MySQL.connection.isClosed()) 
			{
				return false;
			}
			else if (MySQL.connection != null || !MySQL.connection.isClosed())
			{
				return true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void creatingTableStore() {
        try 
        {
        	PreparedStatement stm = MySQL.connection.prepareStatement("CREATE TABLE IF NOT EXISTS ed_shop_articles (`article_type` varchar(255) NOT NULL, `article_id` int(11), `article_name` varchar(255), `type_of_pay` varchar(255), `article_price` float, `article_is_in_reduction` varchar(255), `article_reduction_percentage` int(11), `article_rarity` int(11), `article_item` varchar(255), `article_item_slot` int(11), `article_skull` varchar(255), `article_status` varchar(255), `article_page` int(11)) CHARACTER SET utf8");
            stm.execute();
            stm.close();
            System.out.println("ED-NETWORK API");
			System.out.println("DATABASE: ed_shop_articles loaded.");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void creatingTableStoreType() {
        try 
        {
        	PreparedStatement stm = MySQL.connection.prepareStatement("CREATE TABLE IF NOT EXISTS ed_shop_type (`section_type` varchar(255) NOT NULL, `section_is_in_reduction` varchar(255), `section_reduction_percentage` int(11), `section_status` varchar(255), `section_pages` int(11), PRIMARY KEY (`section_type`), UNIQUE(`section_type`), INDEX(`section_type`)) CHARACTER SET utf8");
            stm.execute();
            stm.close();
            System.out.println("ED-NETWORK API");
			System.out.println("DATABASE: ed_shop_type loaded.");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void creatingTableStorePurchase() {
        try 
        {
        	PreparedStatement stm = MySQL.connection.prepareStatement("CREATE TABLE IF NOT EXISTS ed_shop_purchase (`player_name` varchar(255) NOT NULL, `article_id` int(11), `purchase_date` varchar(255), `deadline_date` varchar(255)) CHARACTER SET utf8");
            stm.execute();
            stm.close();
            System.out.println("ED-NETWORK API");
			System.out.println("DATABASE: ed_shop_purchase loaded.");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void creatingTableShopOfTheWeek() {
        try 
        {
        	PreparedStatement stm = MySQL.connection.prepareStatement("CREATE TABLE IF NOT EXISTS ed_shop_of_week (`article_id` int(11) NOT NULL, `reduction_date` varchar(255), `deadline_date` varchar(255)) CHARACTER SET utf8");
            stm.execute();
            stm.close();
            System.out.println("ED-NETWORK API");
			System.out.println("DATABASE: ed_shop_of_week loaded.");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	private void message(String condition) {
		if (condition == "connectMSG")
		{
			System.out.println("+--------------------+");
			System.out.println("ED-NETWORK API");
			System.out.println("ORM: Enable");
			System.out.println("ORM-DATABASE: Connected");
			System.out.println("+--------------------+");
		}
		if (condition == "disconnectMSG")
		{
			System.out.println("+--------------------+");
			System.out.println("ED-NETWORK API");
			System.out.println("ORM: Disable");
			System.out.println("ORM-DATABASE: Disconnected");
			System.out.println("+--------------------+");
		}
	}
	
	
}
