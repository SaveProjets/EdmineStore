package fr.edminecoreteam.store.item;

import fr.edminecoreteam.store.data.SectionSQL;
import fr.edminecoreteam.store.utils.CustomSounds;
import fr.edminecoreteam.store.utils.SkullNBT;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import fr.edminecoreteam.store.gui.StoreMainGui;
import fr.edminecoreteam.store.settings.SettingInfo;

public class StoreItem implements Listener 
{
	private static ItemStack getSkull(String url) { return SkullNBT.getSkull(url); }
	private static String getStoreStatus(String type) { return SectionSQL.getSectionStatus(type); }
	
	@EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        ItemStack it = e.getCurrentItem();
        if (it == null) {  return; }
        	if (it.getType() == Material.SKULL_ITEM)
        	{
        		if(it.getItemMeta().getDisplayName().equalsIgnoreCase("§e§lBoutique §7• Clique"))
        		{
        			e.setCancelled(true);
        			if (getStoreStatus("main").equalsIgnoreCase("enable"))
                	{
        				CustomSounds.openShopSound(p);
            			StoreMainGui.gui(p);
                	}
        			else
        			{
        				p.sendMessage("§cErreur, il semble que la boutique soit indisponible pour le moment...");
        			}
        		}
        		if(it.getItemMeta().getDisplayName().equalsIgnoreCase("§e§lStore §7• Click"))
        		{
        			e.setCancelled(true);
        			if (getStoreStatus("main").equalsIgnoreCase("enable"))
                	{
        				CustomSounds.openShopSound(p);
            			StoreMainGui.gui(p);
                	}
        			else
        			{
        				p.sendMessage("§cError, it seems that the store is unavailable at the moment...");
        			}
        		}
        		if(it.getItemMeta().getDisplayName().equalsIgnoreCase("§e§lTienda §7• Clic"))
        		{
        			e.setCancelled(true);
        			if (getStoreStatus("main").equalsIgnoreCase("enable"))
                	{
        				CustomSounds.openShopSound(p);
            			StoreMainGui.gui(p);
                	}
        			else
        			{
        				p.sendMessage("§cError, parece que la tienda no está disponible en este momento...");
        			}
        		}
        		if(it.getItemMeta().getDisplayName().equalsIgnoreCase("§e§lGeschäft §7• Sklick"))
        		{
        			e.setCancelled(true);
        			if (getStoreStatus("main").equalsIgnoreCase("enable"))
                	{
        				CustomSounds.openShopSound(p);
            			StoreMainGui.gui(p);
                	}
        			else
        			{
        				p.sendMessage("§cFehler, es scheint, dass der Laden momentan nicht verfügbar ist...");
        			}
        		}
        	}
    }
	
	@EventHandler
    public void onInteract(PlayerInteractEvent e) {
		
        Player p = e.getPlayer();
        ItemStack it = e.getItem();
        if (it == null) { return; }
        Action a = e.getAction();
        if (it.getType() == Material.SKULL_ITEM 
        		&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§e§lBoutique §7• Clique")
        			&& (a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK || a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK)) 
        {
        	e.setCancelled(true);
        	if (getStoreStatus("main").equalsIgnoreCase("enable"))
        	{
				CustomSounds.openShopSound(p);
    			StoreMainGui.gui(p);
        	}
			else
			{
				p.sendMessage("§cErreur, il semble que la boutique soit indisponible pour le moment...");
			}
        }
        if (it.getType() == Material.SKULL_ITEM 
        		&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§e§lStore §7• Click")
        			&& (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK || a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK)) 
        {
        	e.setCancelled(true);
        	if (getStoreStatus("main").equalsIgnoreCase("enable"))
        	{
				CustomSounds.openShopSound(p);
    			StoreMainGui.gui(p);
        	}
			else
			{
				p.sendMessage("§cError, it seems that the store is unavailable at the moment...");
			}
        }
        if (it.getType() == Material.SKULL_ITEM 
        		&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§e§lTienda §7• Clic")
        			&& (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK || a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK)) 
        {
        	e.setCancelled(true);
        	if (getStoreStatus("main").equalsIgnoreCase("enable"))
        	{
				CustomSounds.openShopSound(p);
    			StoreMainGui.gui(p);
        	}
			else
			{
				p.sendMessage("§cError, parece que la tienda no está disponible en este momento...");
			}
        }
        if (it.getType() == Material.SKULL_ITEM 
        		&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§e§lGeschäft §7• Sklick") 
        			&& (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK || a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK)) 
        {
        	e.setCancelled(true);
        	if (getStoreStatus("main").equalsIgnoreCase("enable"))
        	{
				CustomSounds.openShopSound(p);
    			StoreMainGui.gui(p);
        	}
			else
			{
				p.sendMessage("§cFehler, es scheint, dass der Laden momentan nicht verfügbar ist...");
			}
        }  
    }
	
	@EventHandler
	private void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		SettingInfo settingInfo = new SettingInfo(p);
		
		if (settingInfo.getLang() == 0)
		{
			ItemStack store = getSkull("http://textures.minecraft.net/texture/7406e45318e9a4a6bfe132f202fe3ceac15d11eaedbef1eb06a376db433090a8");
            SkullMeta storeM = (SkullMeta)store.getItemMeta();
            storeM.setDisplayName("§e§lBoutique §7• Clique");
            store.setItemMeta((ItemMeta)storeM);
            p.getInventory().setItem(4, store);
		}
		if (settingInfo.getLang() == 1)
		{
			ItemStack store = getSkull("http://textures.minecraft.net/texture/7406e45318e9a4a6bfe132f202fe3ceac15d11eaedbef1eb06a376db433090a8");
            SkullMeta storeM = (SkullMeta)store.getItemMeta();
            storeM.setDisplayName("§e§lStore §7• Click");
            store.setItemMeta((ItemMeta)storeM);
            p.getInventory().setItem(4, store);
		}
		if (settingInfo.getLang() == 2)
		{
			ItemStack store = getSkull("http://textures.minecraft.net/texture/7406e45318e9a4a6bfe132f202fe3ceac15d11eaedbef1eb06a376db433090a8");
            SkullMeta storeM = (SkullMeta)store.getItemMeta();
            storeM.setDisplayName("§e§lTienda §7• Clic");
            store.setItemMeta((ItemMeta)storeM);
            p.getInventory().setItem(4, store);
		}
		if (settingInfo.getLang() == 3)
		{
			ItemStack store = getSkull("http://textures.minecraft.net/texture/7406e45318e9a4a6bfe132f202fe3ceac15d11eaedbef1eb06a376db433090a8");
            SkullMeta storeM = (SkullMeta)store.getItemMeta();
            storeM.setDisplayName("§e§lGeschäft §7• Sklick");
            store.setItemMeta((ItemMeta)storeM);
            p.getInventory().setItem(4, store);
		}
	}
}
