package fr.edminecoreteam.store.gui;

import fr.edminecoreteam.store.Main;
import fr.edminecoreteam.store.account.AccountInfo;
import fr.edminecoreteam.store.data.SectionSQL;
import fr.edminecoreteam.store.data.StoreData;
import fr.edminecoreteam.store.purchase.PurchaseData;
import fr.edminecoreteam.store.purchase.PurchaseGui;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class StoreInteractionGui implements Listener 
{
	private static String getStoreStatus(String type) { return SectionSQL.getSectionStatus(type); }
	private static Main main = Main.getInstance();
	
	@EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        ItemStack it = e.getCurrentItem();
        if (it != null)
        {
	        if (inv.getName().contains("§8Boutique"))
	        {
	        	e.setCancelled(true);
	        	String lastName = it.getItemMeta().getDisplayName();
	        	
	        	StoreData dataStore = new StoreData(lastName);
	        	int ID = dataStore.getArticleIDByName();
	        	StoreData Store = new StoreData(ID);
	        	String finalName = "";
	        	if (Store.getIsInReduction().equalsIgnoreCase("disable"))
				{
	        		finalName = dataStore.name();
				}
	        	else if (Store.getIsInReduction().equalsIgnoreCase("enable"))
	        	{
	        		finalName = lastName;
	        	}
	        	AccountInfo pInfo = new AccountInfo(p);
	        	PurchaseData data = new PurchaseData(p.getName());
	        	
	        	if(lastName.equalsIgnoreCase(finalName))
	    		{
	        		if (!data.getArticle(ID).equalsIgnoreCase(p.getName()))
	    			{
	        			if (Store.getTypeOfPay().equalsIgnoreCase("f.a"))
	        			{
	        				if (Store.getIsInReduction().equalsIgnoreCase("disable"))
	        				{
	        					if (pInfo.getFragmentsDames() >= Store.getArticlePrice())
		        				{
		        					main.addIDPurchase(p, ID);
		            				PurchaseGui.gui(p);
		        				}
		        				else
		        				{
		        					p.sendMessage("§cErreur, vous n'avez pas assez de Fragments d'âmes...");
		        					p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
		        				}
	        				}
	        				else if (Store.getIsInReduction().equalsIgnoreCase("enable"))
	        				{
	        					Float price = Store.getArticlePrice();
                    			int reduction = Store.getReductionPercentage();
                    			Float firstResult = price*reduction/100;
                    			Float finalPrice = price-firstResult;
                    			if (pInfo.getFragmentsDames() >= finalPrice)
		        				{
		        					main.addIDPurchase(p, ID);
		            				PurchaseGui.gui(p);
		        				}
		        				else
		        				{
		        					p.sendMessage("§cErreur, vous n'avez pas assez de Fragments d'âmes...");
		        					p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
		        				}
	        				}
	        			}
	        			if (Store.getTypeOfPay().equalsIgnoreCase("a"))
	        			{
	        				if (Store.getIsInReduction().equalsIgnoreCase("disable"))
	        				{
	        					if (pInfo.getArgent() >= Store.getArticlePrice())
		        				{
		        					main.addIDPurchase(p, ID);
		            				PurchaseGui.gui(p);
		        				}
		        				else
		        				{
		        					p.sendMessage("§cErreur, vous n'avez pas assez de Argents...");
		        					p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
		        				}
	        				}
	        				else if (Store.getIsInReduction().equalsIgnoreCase("enable"))
	        				{
	        					Float price = Store.getArticlePrice();
                    			int reduction = Store.getReductionPercentage();
                    			Float firstResult = price*reduction/100;
                    			Float finalPrice = price-firstResult;
                    			if (pInfo.getArgent() >= finalPrice)
    	        				{
    	        					main.addIDPurchase(p, ID);
    	            				PurchaseGui.gui(p);
    	        				}
    	        				else
    	        				{
    	        					p.sendMessage("§cErreur, vous n'avez pas assez de Argents...");
    	        					p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
    	        				}
	        				}
	        			}
	        			if (Store.getTypeOfPay().equalsIgnoreCase("e.d"))
	        			{
	        				if (Store.getIsInReduction().equalsIgnoreCase("disable"))
	        				{
	        					if (pInfo.getEclatsDivins() >= Store.getArticlePrice())
		        				{
		        					main.addIDPurchase(p, ID);
		            				PurchaseGui.gui(p);
		        				}
		        				else
		        				{
		        					p.sendMessage("§cErreur, vous n'avez pas assez de D'êclats divins...");
		        					p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
		        				}
	        				}
	        				else if (Store.getIsInReduction().equalsIgnoreCase("enable"))
	        				{
	        					Float price = Store.getArticlePrice();
                    			int reduction = Store.getReductionPercentage();
                    			Float firstResult = price*reduction/100;
                    			Float finalPrice = price-firstResult;
                    			if (pInfo.getEclatsDivins() >= finalPrice)
		        				{
		        					main.addIDPurchase(p, ID);
		            				PurchaseGui.gui(p);
		        				}
		        				else
		        				{
		        					p.sendMessage("§cErreur, vous n'avez pas assez de D'êclats divins...");
		        					p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
		        				}
	        				}
	        			}
	    			}
	    		}
	        	if(it.getItemMeta().getDisplayName() == "§8➡ §7Page Suivante")
        		{
	        		p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
	        		int page = main.getPage(p) + 1;
	        		main.removePage(p);
    				main.addPage(p, page);
    				SubTypeGui.gui(p, main.getPageType(p), 1, main.getPage(p), SectionSQL.getSectionPages(main.getPageType(p)));
    				
        		}
	        	if(it.getItemMeta().getDisplayName() == "§8⬅ §7Page Précédente")
        		{
	        		p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
	        		int page = main.getPage(p) - 1;
	        		main.removePage(p);
    				main.addPage(p, page);
    				SubTypeGui.gui(p, main.getPageType(p), 1, main.getPage(p), SectionSQL.getSectionPages(main.getPageType(p)));
    				
        		}
	        		if(it.getItemMeta().getDisplayName() == "§e§lBoutique")
	        		{
	        			if (getStoreStatus("main").equalsIgnoreCase("enable"))
	                	{
	        				p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
	        				main.removePage(p);
	        				main.addPage(p, 1);
	        				main.removePageType(p);
	        				main.addPageType(p, "main");
	            			StoreMainGui.gui(p);
	                	}
	        			else
	        			{
	        				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
	        				return;
	        			}
	        		}
	        		if(it.getItemMeta().getDisplayName() == "§6§lGrades")
	        		{
	        			if (getStoreStatus("ranks").equalsIgnoreCase("enable"))
	                	{
	        				p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
	        				main.removePage(p);
	        				main.addPage(p, 1);
	        				main.removePageType(p);
	        				main.addPageType(p, "ranks");
	        				StoreRankGui.gui(p);
	                	}
	        			else
	        			{
	        				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
	        				return;
	        			}
	        		}
	        		if(it.getItemMeta().getDisplayName() == "§c§lBoosters")
	        		{
	        			if (getStoreStatus("boosters").equalsIgnoreCase("enable"))
	                	{
	        				p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
	        				main.removePage(p);
	        				main.addPage(p, 1);
	        				main.removePageType(p);
	        				main.addPageType(p, "boosters");
	        				StoreBoosterGui.gui(p, main.getPage(p), SectionSQL.getSectionPages("boosters"));
	                	}
	        			else
	        			{
	        				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
	        				return;
	        			}
	        		}
	        		if(it.getItemMeta().getDisplayName() == "§5§lClés EDM-BOX")
	        		{
	        			if (getStoreStatus("keys").equalsIgnoreCase("enable"))
	                	{
	        				p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
	        				main.removePage(p);
	        				main.addPage(p, 1);
	        				main.removePageType(p);
	        				main.addPageType(p, "keys");
	        				StoreKeysGui.gui(p, main.getPage(p), SectionSQL.getSectionPages("keys"));
	                	}
	        			else
	        			{
	        				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
	        				return;
	        			}
	        		}
	        		if(it.getItemMeta().getDisplayName() == "§a§lPacks")
	        		{
	        			if (getStoreStatus("packs").equalsIgnoreCase("enable"))
	                	{
	        				p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
	        				main.removePage(p);
	        				main.addPage(p, 1);
	        				main.removePageType(p);
	        				main.addPageType(p, "packs");
	        				StorePackGui.gui(p, main.getPage(p), SectionSQL.getSectionPages("packs"));
	                	}
	        			else
	        			{
	        				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
	        				return;
	        			}
	        		}
	        		if(it.getItemMeta().getDisplayName() == "§b§lJeux")
	        		{
	        			if (getStoreStatus("games").equalsIgnoreCase("enable"))
	                	{
	        				p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
	        				main.removePage(p);
	        				main.addPage(p, 1);
	        				main.removePageType(p);
	        				main.addPageType(p, "games");
	        				StoreGamesGui.gui(p);
	                	}
	        			else
	        			{
	        				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
	        				return;
	        			}
	        		}
	        		if(it.getItemMeta().getDisplayName() == "§6§lDé à coudre")
	        		{
	        			if (getStoreStatus("dac").equalsIgnoreCase("enable"))
	                	{
	        				p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
	        				main.removePage(p);
	        				main.addPage(p, 1);
	        				main.removePageType(p);
	        				main.addPageType(p, "dac");
	        				SubTypeGui.gui(p, "dac", 1, main.getPage(p), SectionSQL.getSectionPages("dac"));
	                	}
	        			else
	        			{
	        				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
	        				return;
	        			}
	        		}
	        		if(it.getItemMeta().getDisplayName() == "§4§lDead Survivor")
	        		{
	        			if (getStoreStatus("deadsurvivorskins").equalsIgnoreCase("enable") || getStoreStatus("deadsurvivorweapons").equalsIgnoreCase("enable"))
	                	{
	        				p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
	        				StoreDeadSurvivorGui.gui(p);
	                	}
	        			else
	        			{
	        				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
	        				return;
	        			}
	        		}
	        		if(it.getItemMeta().getDisplayName() == "§4§lArmes Dead Survivor")
	        		{
	        			if (getStoreStatus("deadsurvivorweapons").equalsIgnoreCase("enable"))
	                	{
	        				p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
	        				main.removePage(p);
	        				main.addPage(p, 1);
	        				main.removePageType(p);
	        				main.addPageType(p, "deadsurvivorweapons");
	        				SubTypeGui.gui(p, "deadsurvivorweapons", 14, main.getPage(p), SectionSQL.getSectionPages("deadsurvivorweapons"));
	                	}
	        			else
	        			{
	        				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
	        				return;
	        			}
	        		}
	        		if(it.getItemMeta().getDisplayName() == "§4§lSkins Dead Survivor")
	        		{
	        			if (getStoreStatus("deadsurvivorskins").equalsIgnoreCase("enable"))
	                	{
	        				p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
	        				main.removePage(p);
	        				main.addPage(p, 1);
	        				main.removePageType(p);
	        				main.addPageType(p, "deadsurvivorskins");
	        				SubTypeGui.gui(p, "deadsurvivorskins", 14, main.getPage(p), SectionSQL.getSectionPages("deadsurvivorskins"));
	                	}
	        			else
	        			{
	        				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
	        				return;
	        			}
	        		}
	        }
	        if (inv.getName().contains("§8Menu Principal"))
	        {
	        	if(it.getItemMeta().getDisplayName() == "§6§lBlocs Dé à coudre")
        		{
        			if (getStoreStatus("dac").equalsIgnoreCase("enable"))
                	{
        				p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
        				main.removePage(p);
        				main.addPage(p, 1);
        				main.removePageType(p);
        				main.addPageType(p, "dac");
        				SubTypeGui.gui(p, "dac", 1, main.getPage(p), SectionSQL.getSectionPages("dac"));
                	}
        			else
        			{
        				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
        				return;
        			}
        		}
	        	if(it.getItemMeta().getDisplayName() == "§4§lArmes Dead Survivor")
        		{
        			if (getStoreStatus("deadsurvivorweapons").equalsIgnoreCase("enable"))
                	{
        				p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
        				main.removePage(p);
        				main.addPage(p, 1);
        				main.removePageType(p);
        				main.addPageType(p, "deadsurvivorweapons");
        				SubTypeGui.gui(p, "deadsurvivorweapons", 14, main.getPage(p), SectionSQL.getSectionPages("deadsurvivorweapons"));
                	}
        			else
        			{
        				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
        				return;
        			}
        		}
        		if(it.getItemMeta().getDisplayName() == "§4§lSkins Dead Survivor")
        		{
        			if (getStoreStatus("deadsurvivorskins").equalsIgnoreCase("enable"))
                	{
        				p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
        				main.removePage(p);
        				main.addPage(p, 1);
        				main.removePageType(p);
        				main.addPageType(p, "deadsurvivorskins");
        				SubTypeGui.gui(p, "deadsurvivorskins", 14, main.getPage(p), SectionSQL.getSectionPages("deadsurvivorskins"));
                	}
        			else
        			{
        				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
        				return;
        			}
        		}
	        }
        }
    }
}
