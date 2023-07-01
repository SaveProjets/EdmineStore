package fr.edminecoreteam.store.gui;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;


import fr.edminecoreteam.store.Main;
import fr.edminecoreteam.store.account.AccountInfo;
import fr.edminecoreteam.store.data.SectionSQL;
import fr.edminecoreteam.store.data.StoreInfo;
import fr.edminecoreteam.store.purchase.PurchaseData;
import fr.edminecoreteam.store.settings.SettingInfo;
import fr.edminecoreteam.store.task.ShopOfTheWeekData;
import fr.edminecoreteam.store.utils.ItemStackSerializer;
import fr.edminecoreteam.store.utils.SkullNBT;

public class StoreMainGui implements Listener 
{
	
	private static Main api = Main.getInstance();
	private static ItemStack getSkull(String url) { return SkullNBT.getSkull(url); }
	private static String getStoreStatus(String type) { return SectionSQL.getSectionStatus(type); }
	
	
	
	public static void gui(Player p) {
		SettingInfo settingInfo = new SettingInfo(p);
		AccountInfo accountInfo = new AccountInfo(p);
        
        if (settingInfo.getLang() == 0)
        {
        	Inventory inv = Bukkit.createInventory(null, 54, "§8Boutique");
    		p.openInventory(inv);
    		
            new BukkitRunnable() {
                int t = 0;   
    	        public void run() {
    	        	
    	        	if (!p.getOpenInventory().getTitle().equalsIgnoreCase("§8Boutique")) { cancel(); }
    	        	
    	        	ItemStack deco = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4);
		            ItemMeta decoM = deco.getItemMeta();
		            decoM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		            decoM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		            decoM.setDisplayName("§r");
		            deco.setItemMeta(decoM);
		            inv.setItem(0, deco); inv.setItem(8, deco); inv.setItem(9, deco);
		            inv.setItem(45, deco); inv.setItem(53, deco); inv.setItem(36, deco);
		            
		            
		            ItemStack economies = new ItemStack(Material.DOUBLE_PLANT, 1);
		            ItemMeta economiesM = economies.getItemMeta();
	                economiesM.setDisplayName("§e§lVotre Économie");
	                ArrayList<String> loreeconomies = new ArrayList<String>();
	                loreeconomies.add("");
	                loreeconomies.add(" §dInformations:");
	                loreeconomies.add(" §f▶ §7Fragments d'âmes: §b" + accountInfo.getFragmentsDames() + "§b✵");
	                loreeconomies.add(" §f▶ §7Eclats divins: §d" + accountInfo.getEclatsDivins() + "§d❁");
	                loreeconomies.add(" §f▶ §7Argent: §a" + accountInfo.getArgent() + "§a✪");
	                loreeconomies.add("");
	                economiesM.setLore(loreeconomies);
	                economies.setItemMeta(economiesM);
	                inv.setItem(4, economies);
		            
		            
	            	ItemStack mainStore = getSkull("http://textures.minecraft.net/texture/12694ede96b218511602153f665398be7c9669caf672c4bc8f35bcd517e28c3");
	                SkullMeta mainStoreM = (SkullMeta) mainStore.getItemMeta();
	                mainStoreM.setDisplayName("§e§lBoutique");
	                ArrayList<String> loremainStore = new ArrayList<String>();
	                loremainStore.add("");
	                loremainStore.add(" §aDescription:");
	                loremainStore.add(" §f▶ §7Visionnez les articles");
	                loremainStore.add(" §f  §7du jour et les offres");
	                loremainStore.add(" §f  §7incomparables de la semaine.");
	                loremainStore.add("");
	                loremainStore.add("§8➡ §fCliquez pour y accéder.");
	                mainStoreM.setLore(loremainStore);
	                mainStore.setItemMeta(mainStoreM);
	                inv.setItem(18, mainStore);
	                
	                ItemStack rank = getSkull("http://textures.minecraft.net/texture/5593da74e9688413c237f3ce324d7085aca88dfa4b7257c2da0bdfc34563077");
	                SkullMeta rankM = (SkullMeta) rank.getItemMeta();
	                rankM.setDisplayName("§6§lGrades");
	                ArrayList<String> lorerank = new ArrayList<String>();
	                lorerank.add("");
	                lorerank.add(" §aDescription:");
	                lorerank.add(" §f▶ §7Visionnez les abonnements");
	                lorerank.add(" §f  §7des différents grades");
	                lorerank.add(" §f  §7disponibles sur le serveur.");
	                lorerank.add("");
	                if (getStoreStatus("ranks").equalsIgnoreCase("enable"))
	                {
	                	lorerank.add("§8➡ §fCliquez pour y accéder.");
	                }
	                else
	                {
	                	lorerank.add("§8➡ §cActuellement indisponible...");
	                }
	                rankM.setLore(lorerank);
	                rank.setItemMeta(rankM);
	                inv.setItem(27, rank);
	                
	                ItemStack keysEDMBox = getSkull("http://textures.minecraft.net/texture/149e48c0df7995e91db5bd3c930e5bcc0abcfaf31273732aeabf33c5d86491");
	                SkullMeta keysEDMBoxM = (SkullMeta) keysEDMBox.getItemMeta();
	                keysEDMBoxM.setDisplayName("§5§lClés EDM-BOX");
	                ArrayList<String> lorekeysEDMBox = new ArrayList<String>();
	                lorekeysEDMBox.add("");
	                lorekeysEDMBox.add(" §aDescription:");
	                lorekeysEDMBox.add(" §f▶ §7Visionnez les différentes");
	                lorekeysEDMBox.add(" §f  §7clés disponibles pour les");
	                lorekeysEDMBox.add(" §f  §7différentes BOX(s).");
	                lorekeysEDMBox.add("");
	                if (getStoreStatus("keys").equalsIgnoreCase("enable"))
	                {
	                	lorekeysEDMBox.add("§8➡ §fCliquez pour y accéder.");
	                }
	                else
	                {
	                	lorekeysEDMBox.add("§8➡ §cActuellement indisponible...");
	                }
	                keysEDMBoxM.setLore(lorekeysEDMBox);
	                keysEDMBox.setItemMeta(keysEDMBoxM);
	                inv.setItem(17, keysEDMBox);
	                
	                ItemStack boosters = getSkull("http://textures.minecraft.net/texture/1e5c65227cc46ea405b8a8b15d4ac9889446fe5d5fb0619f51398deb2411a");
	                SkullMeta boostersM = (SkullMeta) boosters.getItemMeta();
	                boostersM.setDisplayName("§c§lBoosters");
	                ArrayList<String> loreboosters = new ArrayList<String>();
	                loreboosters.add("");
	                loreboosters.add(" §aDescription:");
	                loreboosters.add(" §f▶ §7Boostez vos différentes");
	                loreboosters.add(" §f  §7moneys du serveur suivant");
	                loreboosters.add(" §f  §7le booster utilisé.");
	                loreboosters.add("");
	                if (getStoreStatus("boosters").equalsIgnoreCase("enable"))
	                {
	                	loreboosters.add("§8➡ §fCliquez pour y accéder.");
	                }
	                else
	                {
	                	loreboosters.add("§8➡ §cActuellement indisponible...");
	                }
	                boostersM.setLore(loreboosters);
	                boosters.setItemMeta(boostersM);
	                inv.setItem(26, boosters);
	                
	                ItemStack money = getSkull("http://textures.minecraft.net/texture/299c799b38ab1999c354332a74b3a49687012738225682d58159be2b8a2b");
	                SkullMeta moneyM = (SkullMeta) money.getItemMeta();
	                moneyM.setDisplayName("§a§lPacks");
	                ArrayList<String> loremoney = new ArrayList<String>();
	                loremoney.add("");
	                loremoney.add(" §aDescription:");
	                loremoney.add(" §f▶ §7Vous manquez de monnaies ?");
	                loremoney.add(" §f▶ §7Visionnez les différentes");
	                loremoney.add(" §f  §7monnaies disponibles.");
	                loremoney.add("");
	                if (getStoreStatus("packs").equalsIgnoreCase("enable"))
	                {
	                	loremoney.add("§8➡ §fCliquez pour y accéder.");
	                }
	                else
	                {
	                	loremoney.add("§8➡ §cActuellement indisponible...");
	                }
	                moneyM.setLore(loremoney);
	                money.setItemMeta(moneyM);
	                inv.setItem(35, money);
	                
	                ItemStack games = getSkull("http://textures.minecraft.net/texture/4cef7c3691caa8af9d09acd2beca675a24d1be7473e45cb6daf4ff26814d23");
	                SkullMeta gamesM = (SkullMeta) games.getItemMeta();
	                gamesM.setDisplayName("§b§lJeux");
	                ArrayList<String> loregames = new ArrayList<String>();
	                loregames.add("");
	                loregames.add(" §aDescription:");
	                loregames.add(" §f▶ §7Visionnez les différents");
	                loregames.add(" §f  §7articles disponibles");
	                loregames.add(" §f  §7sur les jeux du serveur.");
	                loregames.add("");
	                if (getStoreStatus("games").equalsIgnoreCase("enable"))
	                {
	                	loregames.add("§8➡ §fCliquez pour y accéder.");
	                }
	                else
	                {
	                	loregames.add("§8➡ §cActuellement indisponible...");
	                }
	                gamesM.setLore(loregames);
	                games.setItemMeta(gamesM);
	                inv.setItem(44, games);
	                
	                
	                ItemStack shopOfTheWeek = new ItemStack(Material.NAME_TAG, 1);
	                ItemMeta shopOfTheWeekM = shopOfTheWeek.getItemMeta();
	                shopOfTheWeekM.setDisplayName("§eBoutique de la semaine");
	                ArrayList<String> loreshopOfTheWeek = new ArrayList<String>();
	                loreshopOfTheWeek.add("");
	                loreshopOfTheWeek.add(" §aDescription:");
	                loreshopOfTheWeek.add(" §f▶ §7Les offres de cette");
	                loreshopOfTheWeek.add(" §f  §7boutique changent");
	                loreshopOfTheWeek.add(" §f  §7toutes les semaines.");
	                loreshopOfTheWeek.add("");
	                loreshopOfTheWeek.add(" §dInformations:");
	                loreshopOfTheWeek.add(" §f▶ §7Le contenu de cette");
	                loreshopOfTheWeek.add(" §f  §7boutique est §dexclusif§7.");
	                loreshopOfTheWeek.add("");
	                shopOfTheWeekM.setLore(loreshopOfTheWeek);
	                shopOfTheWeek.setItemMeta(shopOfTheWeekM);
	                inv.setItem(49, shopOfTheWeek);
	                
	                int slot = 20;
                	int articlesCount = 0;
                	int articlesPerPage = 10;
                	ShopOfTheWeekData weekData = new ShopOfTheWeekData();
                    for (int articles : weekData.getAllArticleIds()) {
                    	++articlesCount;
                    	StoreInfo storeInfo = new StoreInfo(articles);
                    	
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
                    			
                                storeM.setDisplayName(storeInfo.getArticleName().replace("_", " ").replace("&", "§") + " §c§l" + storeInfo.getReductionPercentage() + "%" + " REDUCTION !");
                                ArrayList<String> lorestore = new ArrayList<String>();
                                if (storeInfo.getArticleType().equalsIgnoreCase("games"))
                                {
                                	lorestore.add(storeInfo.getArticleSubType()
                                			.replace("dac", "§8Dé à coudre")
                                			.replace("deadsurvivorskins", "§8Dead Survivor")
                                			.replace("deadsurvivorweapons", "§8Dead Survivor"));
                                }
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
                                lorestore.add("");
                                PurchaseData data = new PurchaseData(p.getName());
                                if (!data.getArticle(articles).equalsIgnoreCase(p.getName()))
                                {
                                	if (storeInfo.getTypeOfPay().equalsIgnoreCase("f.a"))
                        			{
                        				if (accountInfo.getFragmentsDames() >= finalPrice)
                        				{
                        					lorestore.add("§8➡ §fCliquez pour acheter.");
                        				}
                        				else
                        				{
                        					lorestore.add("§8➡ §cVous n'avez pas assez de Fragments d'âmes...");
                        				}
                        			}
                        			if (storeInfo.getTypeOfPay().equalsIgnoreCase("a"))
                        			{
                        				if (accountInfo.getArgent() >= finalPrice)
                        				{
                        					lorestore.add("§8➡ §fCliquez pour acheter.");
                        				}
                        				else
                        				{
                        					lorestore.add("§8➡ §cVous n'avez pas assez de D'argents...");
                        				}
                        			}
                        			if (storeInfo.getTypeOfPay().equalsIgnoreCase("e.d"))
                        			{
                        				if (accountInfo.getEclatsDivins() >= finalPrice)
                        				{
                        					lorestore.add("§8➡ §fCliquez pour acheter.");
                        				}
                        				else
                        				{
                        					lorestore.add("§8➡ §cVous n'avez pas assez de D'êclats divins...");
                        				}
                        			}
                                }
                                else if (data.getArticle(articles).equalsIgnoreCase(p.getName()))
                                {
                                	lorestore.add("§8➡ §aVous possédez cet article.");
                                }
                                storeM.setLore(lorestore);
                                store.setItemMeta(storeM);
                                
                                
                                if (articlesCount == articlesPerPage)
                                {
                                	inv.setItem(slot, store);
                                }
                                else if (articlesCount != articlesPerPage)
                                {
                                	inv.setItem(slot, store);
                                }
                                if (slot == 24) {
                                	slot += 4;
                                }
                                if (slot != 25 || slot != 33  && slot < 33) {
                                	slot += 1;
                                }
                                if (slot == 34) {
                                	return;
                                }
                    		}
		            
		            
                    ++t;
                    if (t == 5) {
                    	t = 0;
                        run();
                    }
                }
            }.runTaskTimer((Plugin)api, 0L, 15L);
            
            
            new BukkitRunnable() {
                int t = 0;   
    	        public void run() {
    	        	
    	        	if (!p.getOpenInventory().getTitle().equalsIgnoreCase("§8Boutique")) { cancel(); }
    	        	
		            
                    ++t;
                    if (t == 100) {
                    	t = 0;
                        run();
                    }
                }
            }.runTaskTimer((Plugin)api, 0L, 15L);
        }
        
	}
}
