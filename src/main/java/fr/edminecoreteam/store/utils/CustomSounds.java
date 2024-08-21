package fr.edminecoreteam.store.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.edminecoreteam.store.Main;

public class CustomSounds 
{
	private static Main core = Main.getInstance();
	
	public static void openShopSound(Player p)
	{
		new BukkitRunnable() {
            int t = 0; 
	        public void run() {
	        	
	        	if (!p.isOnline()) { cancel(); }
	        	
		        
		        if (t == 0)
		        {
		        	p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 0.7f);
		        }
		        if (t == 1)
		        {
		        	p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.3f);
		        }
		        if (t == 2)
		        {
		        	p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.6f);
		        }
		        ++t;
                if (t == 3) {
                    run();
                }
            }
        }.runTaskTimer((Plugin)core, 0L, 3L);
	}
	
	public static void purchaseSound(Player p)
	{
		new BukkitRunnable() {
            int t = 0; 
	        public void run() {
	        	
	        	if (!p.isOnline()) { cancel(); }
	        	
		        
		        if (t == 0)
		        {
		        	p.playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 1.0f, 1.0f);
		        }
		        if (t == 1)
		        {
		        	p.playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 1.0f, 1.0f);
		        }
		        if (t == 2)
		        {
		        	p.playSound(p.getLocation(), Sound.EXPLODE, 1.0f, 1.0f);
		        }
		        ++t;
                if (t == 3) {
                    run();
                }
            }
        }.runTaskTimer((Plugin)core, 0L, 20L);
	}
}
