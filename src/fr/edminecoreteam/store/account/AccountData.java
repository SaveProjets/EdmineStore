package fr.edminecoreteam.store.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.bukkit.entity.Player;

import fr.edminecoreteam.store.edorm.MySQL;

public class AccountData 
{
	private Player p;
	
    public AccountData(Player p) 
    {
        this.p = p;
    }
    
    public void createAccount() 
    {
        if (!hasaccount()) 
        {
            try 
            {
            	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);  
                Date resultdate = new Date();
                String date = sdf.format(resultdate);
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("INSERT INTO ed_accounts (player_name, player_uuid, player_fragments_d_ames, player_eclats_divins, player_argent, player_level, player_xp_need_to_level_up, player_time_of_played, player_first_connection, player_parrain, player_finish_quetes, player_finish_succes, player_guild_name, player_total_cosmetics, player_total_played_partys) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, p.getName()); /*player_name*/
                preparedStatement.setString(2, p.getUniqueId().toString().replaceAll("-", "")); /*player_uuid*/
                preparedStatement.setFloat(3, 100.0f); /*player_fragments_d_ames*/
                preparedStatement.setFloat(4, 0.0f); /*player_eclats_divins*/
                preparedStatement.setFloat(5, 0.0f); /*player_argent*/
                preparedStatement.setInt(6, 1); /*player_level*/
                preparedStatement.setInt(7, 500); /*player_xp_need_to_level_up*/
                preparedStatement.setString(8, null); /*player_time_of_played*/
                preparedStatement.setString(9, date); /*player_first_connection*/
                preparedStatement.setString(10, null); /*player_parrain*/
                preparedStatement.setInt(11, 0); /*player_finish_quetes*/
                preparedStatement.setInt(12, 0); /*player_finish_succes*/
                preparedStatement.setString(13, null); /*player_guild_name*/
                preparedStatement.setInt(14, 0); /*player_total_cosmetics*/
                preparedStatement.setInt(15, 0); /*player_total_played_partys*/
                preparedStatement.execute();
                preparedStatement.close();
            }
            catch (SQLException e) 
            {
                e.toString();
            }
        }
    }
    
    public boolean hasaccount() 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_id FROM ed_accounts WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        }
        catch (SQLException e) 
        {
            e.toString();
            return false;
        }
    }
    
    public void updateUUIDAccount() 
    {
    	if (hasaccount()) 
        {
            try 
            {
            	PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_accounts SET player_uuid = ? WHERE player_name = ?");
                preparedStatement.setString(1, p.getUniqueId().toString().replaceAll("-", ""));
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e) 
            {
                e.toString();
            }
        }
	}
    
    public int getAccountID() 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_id FROM ed_accounts WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) 
            {
                id = rs.getInt("player_id");
            }
            preparedStatement.close();
            return id;
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            return 0;
        }
    }
    
    public String isOnline() 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT isOnline FROM ed_login WHERE player_name = ?");
            preparedStatement.setString(1, toString());
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) 
            {
                id = rs.getInt("player_id");
            }
            preparedStatement.close();
            if (id == 1)
            {
            	return "§aEn-Ligne";
            }
            else
            {
            	return "§cHors-Ligne";
            }
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            return "";
        }
    }
}
