package fr.edminecoreteam.store.command;

import fr.edminecoreteam.store.Main;
import fr.edminecoreteam.store.purchase.PurchaseGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuyCommand implements CommandExecutor {

	private static Main main = Main.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (sender instanceof Player) 
		{
            Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("buy"))
        	{
        		if (args.length == 0) 
        		{
                	return false;
                }
        		if (args.length == 1)
        		{
        			Integer getID = Integer.valueOf(args[0]);
        			main.addIDPurchase(player, getID);
        			PurchaseGui.gui(player);
        		}
        	}
		}
		return false;
	}

}
