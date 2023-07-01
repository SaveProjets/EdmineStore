package fr.edminecoreteam.store.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.edminecoreteam.store.account.RankInfo;
import fr.edminecoreteam.store.data.SectionSQL;
import fr.edminecoreteam.store.data.StoreInfo;

public class StoreCommand implements CommandExecutor {
	
	
	private boolean hasPermission(Player p)
    {
    	RankInfo rankInfo = new RankInfo(p);
    	if (rankInfo.getRankModule() >= 15)
    	{
    		return true;
    	}
    	p.sendMessage("§cErreur, vous n'avez pas la permission...");
		return false;
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (sender instanceof Player) 
		{
            Player player = (Player)sender;
            if (hasPermission(player))
            {
            	if (cmd.getName().equalsIgnoreCase("store"))
            	{
            		if (args.length == 0) 
            		{
                    	StoreMessage.getHelp(player);
                    }
            		if (args.length == 1 && args[0].equalsIgnoreCase("help")) 
            		{
            			StoreMessage.getHelp(player);
                    }
            		if (args.length == 1 && args[0].equalsIgnoreCase("list")) 
            		{
            			StoreMessage.getSectionList(player);
                    }
            		if (args.length == 1 && args[0].equalsIgnoreCase("article")) 
            		{
            			StoreMessage.getHelpArticle(player);
                    }
            		if (args.length == 1 && args[0].equalsIgnoreCase("maintenance")) 
            		{
            			StoreMessage.getHelpMaintenance(player);
                    }
            		if (args.length == 1 && args[0].equalsIgnoreCase("reduction")) 
            		{
            			StoreMessage.getHelpReduction(player);
                    }
            		if (args.length == 1 && args[0].equalsIgnoreCase("section")) 
            		{
            			StoreMessage.getHelpSection(player);
                    }
            		if (args.length == 2 && args[0].equalsIgnoreCase("section") && args[1].equalsIgnoreCase("list")) 
            		{
            			StoreMessage.getSectionList(player);
                    }
            		if (args.length == 2) 
            		{
            			if (args[0].equalsIgnoreCase("list"))
            			{
            				String getType = String.valueOf(args[1]);
    						if (getType != null)
    						{
    							StoreMessage.getArticlesListByGroup(player, getType);
    						}
            			}
            			if (args[0].equalsIgnoreCase("info"))
            			{
            				Integer getID = Integer.valueOf(args[1]);
    						if (getID >= 100000 && getID <= 999999)
    						{
    							StoreMessage.getInfoArticle(player, getID);
    						}
            			}
                    }
            		if (args.length == 3) 
            		{
            			if (args[0].equalsIgnoreCase("section"))
            			{
            				if (args[1].equalsIgnoreCase("add"))
                			{
            					String getType = String.valueOf(args[2]);
        						if (getType != null)
        						{
        							SectionSQL.createSection(getType);
        							player.sendMessage("§aSuccès ! §fVous avez créer la section §e" + getType + "§f avec succès.");
        						}
                			}
            			}
            			if (args[0].equalsIgnoreCase("section"))
            			{
            				if (args[1].equalsIgnoreCase("remove"))
                			{
            					String getType = String.valueOf(args[2]);
        						if (getType != null)
        						{
        							SectionSQL.removeSection(getType);
        							player.sendMessage("§aSuccès ! §fVous avez supprimer la section §c" + getType + "§f avec succès.");
        						}
                			}
            			}
            			if (args[0].equalsIgnoreCase("article"))
            			{
            				if (args[1].equalsIgnoreCase("remove"))
                			{
            					Integer getID = Integer.valueOf(args[2]);
        						if (getID >= 100000 && getID <= 999999)
        						{
        							StoreInfo storeInfo = new StoreInfo(getID);
        							String name = storeInfo.getArticleName().replace("_", " ");
        							storeInfo.removeArticle();
        							player.sendMessage("§aSuccès ! §fVous avez supprimer l'article §e" + name + "§f avec succès.");
        						}
                			}
            			}
            			if (args[0].equalsIgnoreCase("maintenance"))
            			{
            				Integer getID = Integer.valueOf(args[1]);
    						if (getID >= 100000 && getID <= 999999)
    						{
    							if (args[2].equalsIgnoreCase("on"))
    							{
    								StoreInfo storeInfo = new StoreInfo(getID);
    								storeInfo.updateStatus("enable");
    								player.sendMessage("§aSuccès ! §fVous avez défini le statut de l'article §e" + storeInfo.getArticleName().replace("_", " ") +  "§f sur §a" + storeInfo.getArticleStatus() + "§f avec succès.");
    							}
    							else if (args[2].equalsIgnoreCase("off"))
    							{
    								StoreInfo storeInfo = new StoreInfo(getID);
    								storeInfo.updateStatus("disable");
    								player.sendMessage("§aSuccès ! §fVous avez défini le statut de l'article §e" + storeInfo.getArticleName().replace("_", " ") +  "§f sur §c" + storeInfo.getArticleStatus() + "§f avec succès.");
    							}
    						}
            			}
                    }
            		if (args.length == 4)
            		{
            			if (args[0].equalsIgnoreCase("reduction"))
            			{
            				if (args[1].equalsIgnoreCase("set"))
                			{
            					Integer getID = Integer.valueOf(args[2]);
        						if (getID >= 100000 && getID <= 999999)
        						{
        							if (args[3].equalsIgnoreCase("on"))
        							{
        								StoreInfo storeInfo = new StoreInfo(getID);
        								storeInfo.updateIsInReduction("enable");
        								player.sendMessage("§aSuccès ! §fVous avez activer la réduction de l'article §e" + storeInfo.getArticleName().replace("_", " ") + "§f avec un pourcentage de réduction de §a" + storeInfo.getReductionPercentage() + "§f avec succès.");
        							}
        							else if (args[3].equalsIgnoreCase("off"))
        							{
        								StoreInfo storeInfo = new StoreInfo(getID);
        								storeInfo.updateIsInReduction("disable");
        								player.sendMessage("§aSuccès ! §fVous avez désactiver la réduction de l'article §c" + storeInfo.getArticleName().replace("_", " ") + "§f avec succès.");
        							}
        						}
                			}
            			}
            			if (args[0].equalsIgnoreCase("maintenance"))
            			{
            				if (args[1].equalsIgnoreCase("section"))
                			{
            					String getType = String.valueOf(args[2]);
        						if (getType != null)
        						{
        							if (args[3].equalsIgnoreCase("on"))
        							{
        								SectionSQL.updateSectionStatus(getType, "enable");
        								player.sendMessage("§aSuccès ! §fVous avez activer la section §e" + getType + "§f avec succès.");
        							}
        							else if (args[3].equalsIgnoreCase("off"))
        							{
        								SectionSQL.updateSectionStatus(getType, "disable");
        								player.sendMessage("§aSuccès ! §fVous avez désactiver la section §c" + getType + "§f avec succès.");
        							}
        						}
                			}
            			}
            		}
            		if (args.length == 5) 
            		{
            			if (args[0].equalsIgnoreCase("article"))
            			{
            				if (args[1].equalsIgnoreCase("update"))
                			{
            					if (args[2].equalsIgnoreCase("page"))
                    			{
            						Integer getID = Integer.valueOf(args[3]);
            						if (getID >= 100000 && getID <= 999999)
            						{
            							Integer newValues = Integer.valueOf(args[4]);
            							
            							StoreInfo storeInfo = new StoreInfo(getID);
            							String lastName = storeInfo.getArticleName().replace("_", " ");
            							storeInfo.updateArticlePage(newValues);
            							player.sendMessage("§aSuccès ! §fLa page de l'article §e" + lastName +  "§f a été définie sur §a" + storeInfo.getArticlePage() + "§f avec succès.");
            						}
                    			}
            					if (args[2].equalsIgnoreCase("subtype"))
                    			{
            						Integer getID = Integer.valueOf(args[3]);
            						if (getID >= 100000 && getID <= 999999)
            						{
            							String newValues = String.valueOf(args[4]);
            							
            							StoreInfo storeInfo = new StoreInfo(getID);
            							String lastName = storeInfo.getArticleName().replace("_", " ");
            							storeInfo.updateArticleSubType(newValues);
            							player.sendMessage("§aSuccès ! §fLe SubType de l'article §e" + lastName +  "§f a été définie sur §a" + storeInfo.getArticleSubType().replace("_", " ") + "§f avec succès.");
            						}
                    			}
            					if (args[2].equalsIgnoreCase("name"))
                    			{
            						Integer getID = Integer.valueOf(args[3]);
            						if (getID >= 100000 && getID <= 999999)
            						{
            							String newValues = String.valueOf(args[4]);
            							
            							StoreInfo storeInfo = new StoreInfo(getID);
            							String lastName = storeInfo.getArticleName().replace("_", " ");
            							storeInfo.updateArticleName(newValues);
            							player.sendMessage("§aSuccès ! §fLe Nom de l'article §e" + lastName +  "§f a été définie sur §a" + storeInfo.getArticleName().replace("_", " ") + "§f avec succès.");
            						}
                    			}
            					if (args[2].equalsIgnoreCase("moneytype"))
                    			{
            						Integer getID = Integer.valueOf(args[3]);
            						if (getID >= 100000 && getID <= 999999)
            						{
            							String newValues = String.valueOf(args[4]);
            							if (newValues.equalsIgnoreCase("f.a") || newValues.equalsIgnoreCase("e.d") || newValues.equalsIgnoreCase("a"))
            							{
            								StoreInfo storeInfo = new StoreInfo(getID);
            								storeInfo.updateTypeOfPay(newValues);
            								player.sendMessage("§aSuccès ! §fLe Type de monnaie de l'article §e" + storeInfo.getArticleName().replace("_", " ") +  "§f a été définie sur §a" + storeInfo.getTypeOfPay() + "§f avec succès.");
            							}
            						}
                    			}
            					if (args[2].equalsIgnoreCase("price"))
                    			{
            						Integer getID = Integer.valueOf(args[3]);
            						if (getID >= 100000 && getID <= 999999)
            						{
            							Float newValues = Float.valueOf(args[4]);
            							if (newValues != null)
            							{
            								StoreInfo storeInfo = new StoreInfo(getID);
            								storeInfo.updatePrice(newValues);
            								player.sendMessage("§aSuccès ! §fLe prix de l'article §e" + storeInfo.getArticleName().replace("_", " ") +  "§f a été définie sur §a" + storeInfo.getArticlePrice() + "§f avec succès.");
            							}
            						}
                    			}
            					if (args[2].equalsIgnoreCase("reduction"))
                    			{
            						Integer getID = Integer.valueOf(args[3]);
            						if (getID >= 100000 && getID <= 999999)
            						{
            							Integer newValues = Integer.valueOf(args[4]);
            							if (newValues >= 0 && newValues <= 100)
            							{
            								StoreInfo storeInfo = new StoreInfo(getID);
            								storeInfo.updateReductionPercentage(newValues);
            								player.sendMessage("§aSuccès ! §fLa réduction de l'article §e" + storeInfo.getArticleName().replace("_", " ") +  "§f a été définie sur §a" + storeInfo.getReductionPercentage() + "%§f avec succès.");
            							}
            						}
                    			}
            					if (args[2].equalsIgnoreCase("rarity"))
                    			{
            						Integer getID = Integer.valueOf(args[3]);
            						if (getID >= 100000 && getID <= 999999)
            						{
            							Integer newValues = Integer.valueOf(args[4]);
            							if (newValues >= 0 && newValues <= 5)
            							{
            								StoreInfo storeInfo = new StoreInfo(getID);
            								storeInfo.updateRarity(newValues);
            								player.sendMessage("§aSuccès ! §fLa rareté de l'article §e" + storeInfo.getArticleName().replace("_", " ") +  "§f a été définie sur §a" +  storeInfo.getArticleRarity() + "✯§f avec succès.");
            							}
            						}
                    			}
            					if (args[2].equalsIgnoreCase("item"))
                    			{
            						Integer getID = Integer.valueOf(args[3]);
            						if (getID >= 100000 && getID <= 999999)
            						{
            							String newValues = String.valueOf(args[4]);
            							if (newValues != null)
            							{
            								StoreInfo storeInfo = new StoreInfo(getID);
            								storeInfo.updateItem(newValues);
            								player.sendMessage("§aSuccès ! §fL'item de l'article §e" + storeInfo.getArticleName().replace("_", " ") +  "§f a été définie sur §a" +  storeInfo.getArticleItem() + "§f avec succès.");
            							}
            						}
                    			}
            					if (args[2].equalsIgnoreCase("itemslot"))
                    			{
            						Integer getID = Integer.valueOf(args[3]);
            						if (getID >= 100000 && getID <= 999999)
            						{
            							Integer newValues = Integer.valueOf(args[4]);
            							if (newValues != null)
            							{
            								StoreInfo storeInfo = new StoreInfo(getID);
            								storeInfo.updateItemSlot(newValues);
            								player.sendMessage("§aSuccès ! §fLe slot de l'item de l'article §e" + storeInfo.getArticleName().replace("_", " ") +  "§f a été définie sur §a" +  storeInfo.getArticleItemSlot() + "§f avec succès.");
            							}
            						}
                    			}
            					if (args[2].equalsIgnoreCase("skull"))
                    			{
            						Integer getID = Integer.valueOf(args[3]);
            						if (getID >= 100000 && getID <= 999999)
            						{
            							String newValues = String.valueOf(args[4]);
            							if (newValues != null)
            							{
            								StoreInfo storeInfo = new StoreInfo(getID);
            								storeInfo.updateSkull(newValues);
            								player.sendMessage("§aSuccès ! §fLa tête de l'item de l'article §e" + storeInfo.getArticleName().replace("_", " ") +  "§f a été définie sur §a" +  storeInfo.getArticleSkull() + "§f avec succès.");
            							}
            						}
                    			}
                			}
            			}
            			if (args[0].equalsIgnoreCase("reduction"))
            			{
            				if (args[1].equalsIgnoreCase("section"))
                			{
            					if (args[2].equalsIgnoreCase("set"))
                    			{
            						String getType = String.valueOf(args[3]);
            						if (getType != null)
            						{
            							if (args[4].equalsIgnoreCase("on"))
            							{
            								SectionSQL.updateSectionIsInReduction(getType, "enable");
            								player.sendMessage("§aSuccès ! §fVous avez activer la réduction de la section §e" + getType +  "§f avec un pourcentage de réduction de §a" + SectionSQL.getSectionReduction(getType) + "§f avec succès.");
            							}
            							else if (args[4].equalsIgnoreCase("off"))
            							{
            								SectionSQL.updateSectionIsInReduction(getType, "disable");
            								player.sendMessage("§aSuccès ! §fVous avez désactiver la réduction de la section §c" + getType +  "§f avec succès.");
            							}
            						}
                    			}
                			}
            			}
            			if (args[0].equalsIgnoreCase("section"))
            			{
            				if (args[1].equalsIgnoreCase("page"))
                			{
            					if (args[2].equalsIgnoreCase("set"))
                    			{
            						String getType = String.valueOf(args[3]);
            						if (getType != null)
            						{
            								Integer getPage = Integer.valueOf(args[4]);
            								SectionSQL.updateSectionPages(getType, getPage);
            								player.sendMessage("§aSuccès ! §fVous avez update le nombre de page de la section §e" + getType +  "§f sur §a" + getPage + "§f avec succès.");
            						}
                    			}
                			}
            			}
                    }
            		if (args.length == 8) 
            		{
            			if (args[0].equalsIgnoreCase("article"))
            			{
            				if (args[1].equalsIgnoreCase("create"))
                			{
            					if (args[2].equalsIgnoreCase("keys") 
            							|| args[2].equalsIgnoreCase("boosters") 
            								|| args[2].equalsIgnoreCase("moneys")
            									|| args[2].equalsIgnoreCase("games")
            										|| args[2].equalsIgnoreCase("cosmetics")
            											|| args[2].equalsIgnoreCase("packs"))
                    			{
            						if (args[4].equalsIgnoreCase("f.a")
            							|| args[4].equalsIgnoreCase("e.d")
            								|| args[4].equalsIgnoreCase("a"))
                        			{
            							Integer argnumber = Integer.valueOf(args[6]);
            							if (argnumber >= 0 && argnumber <= 5)
                                			{
            									String type = String.valueOf(args[2]);
            									String name = String.valueOf(args[3]);
            									String moneyType = String.valueOf(args[4]);
            									Float price = Float.valueOf(args[5]);
            									Integer rarity = argnumber;
            									String item = String.valueOf(args[7]);
            									
            									StoreInfo storeInfo = new StoreInfo(type, name, moneyType, price, rarity, item);
            									storeInfo.createArticle();
            									player.sendMessage("");
            						            player.sendMessage(" §7» §6§lCréation d'articles §6(ED-Store):");
            						            player.sendMessage("");
            						            player.sendMessage(" §7• §fCatégorie: §c" + type);
            						            player.sendMessage(" §7• §fNom: §e" + name);
            						            player.sendMessage(" §7• §fID de l'article: §e" + storeInfo.returnID());
            						            if (moneyType.equalsIgnoreCase("f.a"))
            						            {
            						            	player.sendMessage(" §7• §fType de monnaie: §bFragments d'âmes ✵");
            						            	player.sendMessage(" §7• §fPrix: §b" + price + "§b✵");
            						            }
            						            else if (moneyType.equalsIgnoreCase("e.d"))
            						            {
            						            	player.sendMessage(" §7• §fType de monnaie: §dEclats divins ❁");
            						            	player.sendMessage(" §7• §fPrix: §d" + price + "§d❁");
            						            }
            						            else if (moneyType.equalsIgnoreCase("a"))
            						            {
            						            	player.sendMessage(" §7• §fType de monnaie: §aArgent ✪");
            						            	player.sendMessage(" §7• §fPrix: §a" + price + "§a✪");
            						            }
            						            if (rarity == 1)
            						            {
            						            	player.sendMessage(" §7• §fRareté: §6✯§8✯✯✯✯");
            						            }
            						            else if (rarity == 2)
            						            {
            						            	player.sendMessage(" §7• §fRareté: §6✯✯§8✯✯✯");
            						            }
            						            else if (rarity == 3)
            						            {
            						            	player.sendMessage(" §7• §fRareté: §6✯✯✯§8✯✯");
            						            }
            						            else if (rarity == 4)
            						            {
            						            	player.sendMessage(" §7• §fRareté: §6✯✯✯✯§8✯");
            						            }
            						            else if (rarity == 5)
            						            {
            						            	player.sendMessage(" §7• §fRareté: §6✯✯✯✯✯");
            						            }
            						            player.sendMessage(" §7• §fItem: §b" + item);
            						            player.sendMessage("");
            						            player.sendMessage("§6⚠[INFO] §fVous devez finir la config de votre article et utiliser la commande §6⬇");
            						            player.sendMessage(" §7• §d/§fstore maintenance §d[id] §aon§8/§coff");
                                			}
                        			}
                    			}
                			}
            			}
                    }
            	}
            }
		}
		return false;
	}

}
