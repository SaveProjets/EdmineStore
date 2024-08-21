package fr.edminecoreteam.store.purchase;

import java.util.ArrayList;

import fr.edminecoreteam.store.Main;
import fr.edminecoreteam.store.data.StoreInfo;
import fr.edminecoreteam.store.utils.ItemStackSerializer;
import fr.edminecoreteam.store.utils.SkullNBT;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.edminecoreteam.store.account.AccountInfo;
import fr.edminecoreteam.store.account.EcoSysInfo;
import fr.edminecoreteam.store.command.StoreMessage;

public class PurchaseGui implements Listener
{
	private static Main main = Main.getInstance();
	private static ItemStack getSkull(String url) { return SkullNBT.getSkull(url); }

	@EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        ItemStack it = e.getCurrentItem();
        if (it == null) {  return; }
        if (inv.getName().equalsIgnoreCase("§8Confirmer l'achat ?")) 
        {
        	e.setCancelled(true);
        	if (it.getType() == Material.INK_SACK)
        	{
        		if(it.getItemMeta().getDisplayName() == "§a§lConfirmer")
        		{
        			PurchaseData data = new PurchaseData(p.getName());
        			StoreInfo storeInfo = new StoreInfo(main.getIDPurchase(p));
        			AccountInfo pInfo = new AccountInfo(p);
        			EcoSysInfo ecoInfo = new EcoSysInfo(p);
        			if (!data.getArticle(main.getIDPurchase(p)).equalsIgnoreCase(p.getName()))
        			{
        				if (storeInfo.getTypeOfPay().equalsIgnoreCase("f.a"))
            			{
        					if (storeInfo.getIsInReduction().equalsIgnoreCase("disable"))
	        				{
        						if (pInfo.getFragmentsDames() >= storeInfo.getArticlePrice())
                				{
                					if (storeInfo.getArticleType().equalsIgnoreCase("cosmetics"))
                					{
                						data.addArticle(main.getIDPurchase(p), null);
                    					ecoInfo.removeFragmentsDames(storeInfo.getArticlePrice());
                    					StoreMessage.getPurchase(p, main.getIDPurchase(p));
                					}
                					if (storeInfo.getArticleType().equalsIgnoreCase("games"))
                					{
                						data.addArticle(main.getIDPurchase(p), null);
                    					ecoInfo.removeFragmentsDames(storeInfo.getArticlePrice());
                    					StoreMessage.getPurchase(p, main.getIDPurchase(p));
                					}
                					if (storeInfo.getArticleType().equalsIgnoreCase("packs"))
                					{
                						data.addArticle(main.getIDPurchase(p), null);
                						for (String pack : main.getConfig().getConfigurationSection("packs").getKeys(false))
                						{
                							for (String content : main.getConfig().getConfigurationSection("packs." + pack + ".content").getKeys(false))
                                    		{
                                    			int contentid = main.getConfig().getInt("packs." + pack + ".content." + content);
                                    			if (!data.hasArticle(contentid))
                                    			{
                                    				data.addArticle(contentid, null);
                                    			}
                                    		}
                						}
                    					ecoInfo.removeFragmentsDames(storeInfo.getArticlePrice());
                    					StoreMessage.getPurchase(p, main.getIDPurchase(p));
                					}
                				}
                				else
                				{
                					p.sendMessage("§cErreur, vous n'avez pas assez de Fragments d'âmes...");
                				}
	        				}
        					else if (storeInfo.getIsInReduction().equalsIgnoreCase("enable"))
        					{
        						Float price = storeInfo.getArticlePrice();
                    			int reduction = storeInfo.getReductionPercentage();
                    			Float firstResult = price*reduction/100;
                    			Float finalPrice = price-firstResult;
                    			if (pInfo.getFragmentsDames() >= finalPrice)
                				{
                					if (storeInfo.getArticleType().equalsIgnoreCase("cosmetics"))
                					{
                						data.addArticle(main.getIDPurchase(p), null);
                    					ecoInfo.removeFragmentsDames(finalPrice);
                    					StoreMessage.getPurchase(p, main.getIDPurchase(p));
                					}
                					if (storeInfo.getArticleType().equalsIgnoreCase("games"))
                					{
                						data.addArticle(main.getIDPurchase(p), null);
                    					ecoInfo.removeFragmentsDames(finalPrice);
                    					StoreMessage.getPurchase(p, main.getIDPurchase(p));
                					}
                					if (storeInfo.getArticleType().equalsIgnoreCase("packs"))
                					{
                						data.addArticle(main.getIDPurchase(p), null);
                						for (String pack : main.getConfig().getConfigurationSection("packs").getKeys(false))
                						{
                							for (String content : main.getConfig().getConfigurationSection("packs." + pack + ".content").getKeys(false))
                                    		{
                                    			int contentid = main.getConfig().getInt("packs." + pack + ".content." + content);
                                    			if (!data.hasArticle(contentid))
                                    			{
                                    				data.addArticle(contentid, null);
                                    			}
                                    		}
                						}
                    					ecoInfo.removeFragmentsDames(finalPrice);
                    					StoreMessage.getPurchase(p, main.getIDPurchase(p));
                					}
                				}
                				else
                				{
                					p.sendMessage("§cErreur, vous n'avez pas assez de Fragments d'âmes...");
                				}
        					}
            			}
            			if (storeInfo.getTypeOfPay().equalsIgnoreCase("a"))
            			{
            				if (storeInfo.getIsInReduction().equalsIgnoreCase("disable"))
	        				{
            					if (pInfo.getArgent() >= storeInfo.getArticlePrice())
                				{
                					if (storeInfo.getArticleType().equalsIgnoreCase("cosmetics"))
                					{
                						data.addArticle(main.getIDPurchase(p), null);
                    					ecoInfo.removeArgent(storeInfo.getArticlePrice());
                    					StoreMessage.getPurchase(p, main.getIDPurchase(p));
                					}
                					if (storeInfo.getArticleType().equalsIgnoreCase("games"))
                					{
                						data.addArticle(main.getIDPurchase(p), null);
                    					ecoInfo.removeArgent(storeInfo.getArticlePrice());
                    					StoreMessage.getPurchase(p, main.getIDPurchase(p));
                					}
                					if (storeInfo.getArticleType().equalsIgnoreCase("packs"))
                					{
                						data.addArticle(main.getIDPurchase(p), null);
                						for (String pack : main.getConfig().getConfigurationSection("packs").getKeys(false))
                						{
                							for (String content : main.getConfig().getConfigurationSection("packs." + pack + ".content").getKeys(false))
                                    		{
                                    			int contentid = main.getConfig().getInt("packs." + pack + ".content." + content);
                                    			if (!data.hasArticle(contentid))
                                    			{
                                    				data.addArticle(contentid, null);
                                    			}
                                    		}
                						}
                    					ecoInfo.removeArgent(storeInfo.getArticlePrice());
                    					StoreMessage.getPurchase(p, main.getIDPurchase(p));
                					}
                				}
                				else
                				{
                					p.sendMessage("§cErreur, vous n'avez pas assez de Argents...");
                				}
	        				}
        					else if (storeInfo.getIsInReduction().equalsIgnoreCase("enable"))
        					{
        						Float price = storeInfo.getArticlePrice();
                    			int reduction = storeInfo.getReductionPercentage();
                    			Float firstResult = price*reduction/100;
                    			Float finalPrice = price-firstResult;
                    			if (pInfo.getArgent() >= finalPrice)
                				{
                					if (storeInfo.getArticleType().equalsIgnoreCase("cosmetics"))
                					{
                						data.addArticle(main.getIDPurchase(p), null);
                    					ecoInfo.removeArgent(finalPrice);
                    					StoreMessage.getPurchase(p, main.getIDPurchase(p));
                					}
                					if (storeInfo.getArticleType().equalsIgnoreCase("games"))
                					{
                						data.addArticle(main.getIDPurchase(p), null);
                    					ecoInfo.removeArgent(finalPrice);
                    					StoreMessage.getPurchase(p, main.getIDPurchase(p));
                					}
                					if (storeInfo.getArticleType().equalsIgnoreCase("packs"))
                					{
                						data.addArticle(main.getIDPurchase(p), null);
                						for (String pack : main.getConfig().getConfigurationSection("packs").getKeys(false))
                						{
                							for (String content : main.getConfig().getConfigurationSection("packs." + pack + ".content").getKeys(false))
                                    		{
                                    			int contentid = main.getConfig().getInt("packs." + pack + ".content." + content);
                                    			if (!data.hasArticle(contentid))
                                    			{
                                    				data.addArticle(contentid, null);
                                    			}
                                    		}
                						}
                    					ecoInfo.removeArgent(finalPrice);
                    					StoreMessage.getPurchase(p, main.getIDPurchase(p));
                					}
                				}
                				else
                				{
                					p.sendMessage("§cErreur, vous n'avez pas assez de Argents...");
                				}
        					}
            			}
            			if (storeInfo.getTypeOfPay().equalsIgnoreCase("e.d"))
            			{
            				if (storeInfo.getIsInReduction().equalsIgnoreCase("disable"))
	        				{
            					if (pInfo.getEclatsDivins() >= storeInfo.getArticlePrice())
                				{
                					if (storeInfo.getArticleType().equalsIgnoreCase("cosmetics"))
                					{
                						data.addArticle(main.getIDPurchase(p), null);
                    					ecoInfo.removeEclatsDivins(storeInfo.getArticlePrice());
                    					StoreMessage.getPurchase(p, main.getIDPurchase(p));
                					}
                					if (storeInfo.getArticleType().equalsIgnoreCase("games"))
                					{
                						data.addArticle(main.getIDPurchase(p), null);
                    					ecoInfo.removeEclatsDivins(storeInfo.getArticlePrice());
                    					StoreMessage.getPurchase(p, main.getIDPurchase(p));
                					}
                					if (storeInfo.getArticleType().equalsIgnoreCase("packs"))
                					{
                						data.addArticle(main.getIDPurchase(p), null);
                						for (String pack : main.getConfig().getConfigurationSection("packs").getKeys(false))
                						{
                							for (String content : main.getConfig().getConfigurationSection("packs." + pack + ".content").getKeys(false))
                                    		{
                                    			int contentid = main.getConfig().getInt("packs." + pack + ".content." + content);
                                    			if (!data.hasArticle(contentid))
                                    			{
                                    				data.addArticle(contentid, null);
                                    			}
                                    		}
                						}
                    					ecoInfo.removeEclatsDivins(storeInfo.getArticlePrice());
                    					StoreMessage.getPurchase(p, main.getIDPurchase(p));
                					}
                				}
                				else
                				{
                					p.sendMessage("§cErreur, vous n'avez pas assez de D'êclats divins...");
                				}
	        				}
        					else if (storeInfo.getIsInReduction().equalsIgnoreCase("enable"))
        					{
        						Float price = storeInfo.getArticlePrice();
                    			int reduction = storeInfo.getReductionPercentage();
                    			Float firstResult = price*reduction/100;
                    			Float finalPrice = price-firstResult;
                    			if (pInfo.getEclatsDivins() >= finalPrice)
                				{
                					if (storeInfo.getArticleType().equalsIgnoreCase("cosmetics"))
                					{
                						data.addArticle(main.getIDPurchase(p), null);
                    					ecoInfo.removeEclatsDivins(finalPrice);
                    					StoreMessage.getPurchase(p, main.getIDPurchase(p));
                					}
                					if (storeInfo.getArticleType().equalsIgnoreCase("games"))
                					{
                						data.addArticle(main.getIDPurchase(p), null);
                    					ecoInfo.removeEclatsDivins(finalPrice);
                    					StoreMessage.getPurchase(p, main.getIDPurchase(p));
                					}
                					if (storeInfo.getArticleType().equalsIgnoreCase("packs"))
                					{
                						data.addArticle(main.getIDPurchase(p), null);
                						for (String pack : main.getConfig().getConfigurationSection("packs").getKeys(false))
                						{
                							for (String content : main.getConfig().getConfigurationSection("packs." + pack + ".content").getKeys(false))
                                    		{
                                    			int contentid = main.getConfig().getInt("packs." + pack + ".content." + content);
                                    			if (!data.hasArticle(contentid))
                                    			{
                                    				data.addArticle(contentid, null);
                                    			}
                                    		}
                						}
                    					ecoInfo.removeEclatsDivins(finalPrice);
                    					StoreMessage.getPurchase(p, main.getIDPurchase(p));
                					}
                				}
                				else
                				{
                					p.sendMessage("§cErreur, vous n'avez pas assez de D'êclats divins...");
                				}
        					}
            			}
        			}
        			else
            		{
            			p.sendMessage("§cErreur, il semble que vous possédez déjà cette article...");
            		}
        			p.closeInventory();
        			main.removeIDPurchase(p);
        		}
        		if(it.getItemMeta().getDisplayName() == "§c§lAnnuler")
        		{
        			p.closeInventory();
        			main.removeIDPurchase(p);
        		}
        	}
        }
    }
	
	public static void gui(Player p)
	{
		Inventory inv = Bukkit.createInventory(null, 45, "§8Confirmer l'achat ?");
		AccountInfo pInfo = new AccountInfo(p);
		new BukkitRunnable() {
            int t = 0;
                
	        public void run() {
	        	
	        	if (!p.getOpenInventory().getTitle().equalsIgnoreCase("§8Confirmer l'achat ?")) { cancel(); }
		            	ItemStack deco = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4);
		                ItemMeta decoM = deco.getItemMeta();
		                decoM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		                decoM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		                decoM.setDisplayName("§r");
		                deco.setItemMeta(decoM);
		                inv.setItem(0, deco); inv.setItem(8, deco); inv.setItem(9, deco); inv.setItem(17, deco);
		                inv.setItem(27, deco); inv.setItem(35, deco); inv.setItem(36, deco); inv.setItem(44, deco);
	        		
                ++t;
                if (t == 10) {
                    run();
                }
            }
        }.runTaskTimer((Plugin)main, 0L, 10L);
        
        ItemStack economies = new ItemStack(Material.DOUBLE_PLANT, 1);
        ItemMeta economiesM = economies.getItemMeta();
        economiesM.setDisplayName("§e§lVotre Économie");
        ArrayList<String> loreeconomies = new ArrayList<String>();
        loreeconomies.add("");
        loreeconomies.add(" §dInformations:");
        loreeconomies.add(" §f▶ §7Fragments d'âmes: §b" + pInfo.getFragmentsDames() + "§b✵");
        loreeconomies.add(" §f▶ §7Eclats divins: §d" + pInfo.getEclatsDivins() + "§d❁");
        loreeconomies.add(" §f▶ §7Argent: §a" + pInfo.getArgent() + "§a✪");
        loreeconomies.add("");
        economiesM.setLore(loreeconomies);
        economies.setItemMeta(economiesM);
        inv.setItem(4, economies);
        
        StoreInfo storeInfo = new StoreInfo(main.getIDPurchase(p));
    		if (storeInfo.getIsInReduction().equalsIgnoreCase("enable"))
    		{
    			Float price = storeInfo.getArticlePrice();
    			int reduction = storeInfo.getReductionPercentage();
    			Float firstResult = price*reduction/100;
    			Float finalPrice = price-firstResult;
    			ItemStack store = null;
                ItemMeta storeM = null;
    			
    			if (!storeInfo.getArticleItem().equalsIgnoreCase("skull"))
    			{
    				store = ItemStackSerializer.deserialize(storeInfo.getArticleItem());
                    storeM = store.getItemMeta();
    			}
    			else
    			{
    				String head = storeInfo.getArticleSkull();
    				store = getSkull("http://textures.minecraft.net/texture/" + head);
                    storeM = store.getItemMeta();
    			}
    			
                storeM.setDisplayName(storeInfo.getArticleName().replace("_", " ").replace("&", "§") + " §c§l" + storeInfo.getReductionPercentage() + "§c§l%" + " §c§lREDUCTION !");
                ArrayList<String> lorestore = new ArrayList<String>();
                lorestore.add("");
                lorestore.add(" §dInformation:");
                if (storeInfo.getArticleRarity() == 1)
	            {
                	lorestore.add(" §f▶ §7Rareté: §6✯§8✯✯✯✯");
	            }
	            else if (storeInfo.getArticleRarity() == 2)
	            {
	            	lorestore.add(" §f▶ §7Rareté: §6✯✯§8✯✯✯");
	            }
	            else if (storeInfo.getArticleRarity() == 3)
	            {
	            	lorestore.add(" §f▶ §7Rareté: §6✯✯✯§8✯✯");
	            }
	            else if (storeInfo.getArticleRarity() == 4)
	            {
	            	lorestore.add(" §f▶ §7Rareté: §6✯✯✯✯§8✯");
	            }
	            else if (storeInfo.getArticleRarity() == 5)
	            {
	            	lorestore.add(" §f▶ §7Rareté: §6✯✯✯✯✯");
	            }
                if (storeInfo.getTypeOfPay().equalsIgnoreCase("f.a"))
	            {
                	lorestore.add(" §f▶ §7Moyen de paiement: §bFragments d'âmes §b✵");
                	lorestore.add(" §f▶ §7Prix: §8§m" + price + "§8§m✵§r §b" + finalPrice + "§b✵");
	            }
	            else if (storeInfo.getTypeOfPay().equalsIgnoreCase("e.d"))
	            {
	            	lorestore.add(" §f▶ §7Moyen de paiement: §dEclats divins §d❁");
	            	lorestore.add(" §f▶ §7Prix: §8§m" + price + "§8§m❁§r §d" + finalPrice + "§d❁");
	            }
	            else if (storeInfo.getTypeOfPay().equalsIgnoreCase("a"))
	            {
	            	lorestore.add(" §f▶ §7Moyen de paiement: §aArgent §a✪");
	            	lorestore.add(" §f▶ §7Prix: §8§m" + price + "§8§m✪§r §a" + finalPrice + "§a✪");
	            }
                if (storeInfo.getArticleType().equalsIgnoreCase("packs"))
                {
                	lorestore.add("");
                    lorestore.add(" §bContenus:");
                    for (String pack : main.getConfig().getConfigurationSection("packs").getKeys(false))
                    {
                    	int referenceid = main.getConfig().getInt("packs." + pack + ".referenceid");
                    	if (referenceid == main.getIDPurchase(p))
                    	{
                    		for (String content : main.getConfig().getConfigurationSection("packs." + pack + ".content").getKeys(false))
                    		{
                    			int contentid = main.getConfig().getInt("packs." + pack + ".content." + content);
                    			PurchaseData pdata = new PurchaseData(p.getName());
                    			StoreInfo storeContentInfo = new StoreInfo(contentid);
                    			if (pdata.hasArticle(contentid))
                    			{
                    				lorestore.add(" §f▶ " + storeContentInfo.getArticleName().replace("&", "§").replace("_", " ") + "§7: §aPossédé");
                    			}
                    			else
                    			{
                    				lorestore.add(" §f▶ " + storeContentInfo.getArticleName().replace("&", "§").replace("_", " ") + "§7.");
                    			}
                    		}
                    	}
                    }
                }
                lorestore.add("");
                storeM.setLore(lorestore);
                store.setItemMeta(storeM);
                inv.setItem(22, store);
    		}
    		else
    		{
    			Float price = storeInfo.getArticlePrice();
    			ItemStack store = null;
                ItemMeta storeM = null;
    			
                if (!storeInfo.getArticleItem().equalsIgnoreCase("skull"))
    			{
    				store = ItemStackSerializer.deserialize(storeInfo.getArticleItem());
                    storeM = store.getItemMeta();
    			}
    			else
    			{
    				String head = storeInfo.getArticleSkull();
    				store = getSkull("http://textures.minecraft.net/texture/" + head);
                    storeM = store.getItemMeta();
    			}
                
                storeM.setDisplayName(storeInfo.getArticleName().replace("_", " ").replace("&", "§"));
                ArrayList<String> lorestore = new ArrayList<String>();
                lorestore.add("");
                lorestore.add(" §dInformation:");
                if (storeInfo.getArticleRarity() == 1)
	            {
                	lorestore.add(" §f▶ §7Rareté: §6✯§8✯✯✯✯");
	            }
	            else if (storeInfo.getArticleRarity() == 2)
	            {
	            	lorestore.add(" §f▶ §7Rareté: §6✯✯§8✯✯✯");
	            }
	            else if (storeInfo.getArticleRarity() == 3)
	            {
	            	lorestore.add(" §f▶ §7Rareté: §6✯✯✯§8✯✯");
	            }
	            else if (storeInfo.getArticleRarity() == 4)
	            {
	            	lorestore.add(" §f▶ §7Rareté: §6✯✯✯✯§8✯");
	            }
	            else if (storeInfo.getArticleRarity() == 5)
	            {
	            	lorestore.add(" §f▶ §7Rareté: §6✯✯✯✯✯");
	            }
                if (storeInfo.getTypeOfPay().equalsIgnoreCase("f.a"))
	            {
                	lorestore.add(" §f▶ §7Moyen de paiement: §bFragments d'âmes §b✵");
                	lorestore.add(" §f▶ §7Prix: §b" + price + "§b✵");
	            }
	            else if (storeInfo.getTypeOfPay().equalsIgnoreCase("e.d"))
	            {
	            	lorestore.add(" §f▶ §7Moyen de paiement: §dEclats divins §d❁");
	            	lorestore.add(" §f▶ §7Prix: §d" + price + "§d❁");
	            }
	            else if (storeInfo.getTypeOfPay().equalsIgnoreCase("a"))
	            {
	            	lorestore.add(" §f▶ §7Moyen de paiement: §aArgent §a✪");
	            	lorestore.add(" §f▶ §7Prix: §a" + price + "§a✪");
	            }
                if (storeInfo.getArticleType().equalsIgnoreCase("packs"))
                {
                	lorestore.add("");
                    lorestore.add(" §bContenus:");
                    for (String pack : main.getConfig().getConfigurationSection("packs").getKeys(false))
                    {
                    	int referenceid = main.getConfig().getInt("packs." + pack + ".referenceid");
                    	if (referenceid == main.getIDPurchase(p))
                    	{
                    		for (String content : main.getConfig().getConfigurationSection("packs." + pack + ".content").getKeys(false))
                    		{
                    			int contentid = main.getConfig().getInt("packs." + pack + ".content." + content);
                    			PurchaseData pdata = new PurchaseData(p.getName());
                    			StoreInfo storeContentInfo = new StoreInfo(contentid);
                    			if (pdata.hasArticle(contentid))
                    			{
                    				lorestore.add(" §f▶ " + storeContentInfo.getArticleName().replace("&", "§").replace("_", " ") + "§7: §aPossédé");
                    			}
                    			else
                    			{
                    				lorestore.add(" §f▶ " + storeContentInfo.getArticleName().replace("&", "§").replace("_", " ") + "§7.");
                    			}
                    		}
                    	}
                    }
                }
                lorestore.add("");
                storeM.setLore(lorestore);
                store.setItemMeta(storeM);
                inv.setItem(22, store);
    		}
        
        
        ItemStack Facile = new ItemStack(Material.INK_SACK, 1, (short)10);
        ItemMeta FacileM = Facile.getItemMeta();
        FacileM.setDisplayName("§a§lConfirmer");
        Facile.setItemMeta(FacileM);
        inv.setItem(20, Facile);
        
        ItemStack Difficile = new ItemStack(Material.INK_SACK, 1, (short)1);
        ItemMeta DifficileM = Difficile.getItemMeta();
        DifficileM.setDisplayName("§c§lAnnuler");
        Difficile.setItemMeta(DifficileM);
        inv.setItem(24, Difficile);
		
        
        p.openInventory(inv);
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
	}
}
