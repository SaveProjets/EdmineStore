package fr.edminecoreteam.store.gui;

import java.util.ArrayList;

import fr.edminecoreteam.api.utils.builder.ItemBuilder;
import fr.edminecoreteam.store.account.AccountInfo;
import fr.edminecoreteam.store.data.SectionSQL;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.edminecoreteam.store.settings.SettingInfo;

public class StoreRankGui implements Listener 
{
	private static String getStoreStatus(String type) { return SectionSQL.getSectionStatus(type); }
	
	public static void gui(Player p) {
		SettingInfo settingInfo = new SettingInfo(p);
		AccountInfo accountInfo = new AccountInfo(p);
        
        if (settingInfo.getLang() == 0)
        {
        	Inventory inv = Bukkit.createInventory(null, 54, "§8Boutique");
    		p.openInventory(inv);

			ItemStack deco = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 3)
					.setName("§r")
					.addEnchant(Enchantment.DAMAGE_ALL, 1)
					.hideEnchantments()
					.toItemStack();
			inv.setItem(0, deco);
			inv.setItem(8, deco);
			inv.setItem(9, deco);
			inv.setItem(45, deco);
			inv.setItem(53, deco);
			inv.setItem(36, deco);


			ArrayList<String> loreeconomies = new ArrayList<String>();
			loreeconomies.add("");
			loreeconomies.add(" §dInformations:");
			loreeconomies.add(" §f▶ §7Fragments d'âmes: §b" + accountInfo.getFragmentsDames() + "§b✵");
			loreeconomies.add(" §f▶ §7Eclats divins: §d" + accountInfo.getEclatsDivins() + "§d❁");
			loreeconomies.add(" §f▶ §7Argent: §a" + accountInfo.getArgent() + "§a✪");
			loreeconomies.add("");
			ItemStack economies = new ItemBuilder(Material.DOUBLE_PLANT, 1)
					.setName("§e§lVotre Économie")
					.setLore(loreeconomies)
					.toItemStack();
			inv.setItem(4, economies);


			ArrayList<String> loremainStore = new ArrayList<String>();
			loremainStore.add("");
			loremainStore.add(" §aDescription:");
			loremainStore.add(" §f▶ §7Visionnez les articles");
			loremainStore.add(" §f  §7du jour et les offres");
			loremainStore.add(" §f  §7incomparables de la semaine.");
			loremainStore.add("");
			loremainStore.add("§8➡ §fCliquez pour y accéder.");
			ItemStack mainStore = new ItemBuilder(Material.SKULL, 1)
					.setName("§e§lBoutique")
					.setLore(loremainStore)
					.setSkullUrl("http://textures.minecraft.net/texture/1e5c65227cc46ea405b8a8b15d4ac9889446fe5d5fb0619f51398deb2411a")
					.toItemStack();
			inv.setItem(18, mainStore);

			ArrayList<String> lorerank = new ArrayList<String>();
			lorerank.add("");
			lorerank.add(" §aDescription:");
			lorerank.add(" §f▶ §7Visionnez les abonnements");
			lorerank.add(" §f  §7des différents grades");
			lorerank.add(" §f  §7disponibles sur le serveur.");
			lorerank.add("");
			if (getStoreStatus("ranks").equalsIgnoreCase("enable")) {
				lorerank.add("§8➡ §fCliquez pour y accéder.");
			} else {
				lorerank.add("§8➡ §cActuellement indisponible...");
			}
			ItemStack rank = new ItemBuilder(Material.SKULL, 1)
					.setName("§6§lGrades")
					.setSkullUrl("http://textures.minecraft.net/texture/81d1437d1713c5f2e880dc95c39459710dbcb4f73b5104d688dfda77bc8bb9")
					.setLore(lorerank)
					.toItemStack();
			inv.setItem(27, rank);

			ArrayList<String> lorekeysEDMBox = new ArrayList<String>();
			lorekeysEDMBox.add("");
			lorekeysEDMBox.add(" §aDescription:");
			lorekeysEDMBox.add(" §f▶ §7Visionnez les différentes");
			lorekeysEDMBox.add(" §f  §7clés disponibles pour les");
			lorekeysEDMBox.add(" §f  §7différentes BOX(s).");
			lorekeysEDMBox.add("");
			if (getStoreStatus("keys").equalsIgnoreCase("enable")) {
				lorekeysEDMBox.add("§8➡ §fCliquez pour y accéder.");
			} else {
				lorekeysEDMBox.add("§8➡ §cActuellement indisponible...");
			}
			ItemStack keysEDMBox = new ItemBuilder(Material.SKULL, 1)
					.setName("§5§lClés EDM-BOX")
					.setSkullUrl("http://textures.minecraft.net/texture/149e48c0df7995e91db5bd3c930e5bcc0abcfaf31273732aeabf33c5d86491")
					.setLore(lorekeysEDMBox)
					.toItemStack();
			inv.setItem(17, keysEDMBox);

			ArrayList<String> loreboosters = new ArrayList<String>();
			loreboosters.add("");
			loreboosters.add(" §aDescription:");
			loreboosters.add(" §f▶ §7Boostez vos différentes");
			loreboosters.add(" §f  §7moneys du serveur suivant");
			loreboosters.add(" §f  §7le booster utilisé.");
			loreboosters.add("");
			if (getStoreStatus("boosters").equalsIgnoreCase("enable")) {
				loreboosters.add("§8➡ §fCliquez pour y accéder.");
			} else {
				loreboosters.add("§8➡ §cActuellement indisponible...");
			}
			ItemStack boosters = new ItemBuilder(Material.SKULL, 1)
					.setName("§c§lBoosters")
					.setSkullUrl("http://textures.minecraft.net/texture/1e5c65227cc46ea405b8a8b15d4ac9889446fe5d5fb0619f51398deb2411a")
					.setLore(loreboosters)
					.toItemStack();
			inv.setItem(26, boosters);


			ArrayList<String> loremoney = new ArrayList<String>();
			loremoney.add("");
			loremoney.add(" §aDescription:");
			loremoney.add(" §f▶ §7Vous manquez de monnaies ?");
			loremoney.add(" §f▶ §7Visionnez les différentes");
			loremoney.add(" §f  §7monnaies disponibles.");
			loremoney.add("");
			if (getStoreStatus("packs").equalsIgnoreCase("enable")) {
				loremoney.add("§8➡ §fCliquez pour y accéder.");
			} else {
				loremoney.add("§8➡ §cActuellement indisponible...");
			}
			ItemStack money = new ItemBuilder(Material.SKULL, 1)
					.setName("§a§lPacks")
					.setSkullUrl("http://textures.minecraft.net/texture/299c799b38ab1999c354332a74b3a49687012738225682d58159be2b8a2b")
					.setLore(loremoney)
					.toItemStack();
			inv.setItem(35, money);

			ArrayList<String> loregames = new ArrayList<String>();
			loregames.add("");
			loregames.add(" §aDescription:");
			loregames.add(" §f▶ §7Visionnez les différents");
			loregames.add(" §f  §7articles disponibles");
			loregames.add(" §f  §7sur les jeux du serveur.");
			loregames.add("");
			if (getStoreStatus("games").equalsIgnoreCase("enable")) {
				loregames.add("§8➡ §fCliquez pour y accéder.");
			} else {
				loregames.add("§8➡ §cActuellement indisponible...");
			}
			ItemStack games = new ItemBuilder(Material.SKULL, 1)
					.setName("§b§lJeux")
					.setSkullUrl("http://textures.minecraft.net/texture/4cef7c3691caa8af9d09acd2beca675a24d1be7473e45cb6daf4ff26814d23")
					.setLore(loregames)
					.toItemStack();
			inv.setItem(44, games);
	                

	                
	                /*ItemStack shop = new ItemStack(Material.NAME_TAG, 1);
	                ItemMeta shopM = shop.getItemMeta();
	                shopM.setDisplayName("§6§lTIPS §f┃ §6Grades");
	                ArrayList<String> loreshop = new ArrayList<String>();
	                loreshop.add("");
	                loreshop.add(" §aDescription:");
	                loreshop.add(" §f▶ §7Vous pouvez acheter");
	                loreshop.add(" §f  §7votre grade sur le");
	                loreshop.add(" §f  §7site officiel du serveur");
	                loreshop.add(" §f  §7ou bien in-game.");
	                loreshop.add("");
	                shopM.setLore(loreshop);
	                shop.setItemMeta(shopM);
	                inv.setItem(22, shop);*/

			ArrayList<String> lorevip = new ArrayList<String>();
			lorevip.add("");
			lorevip.add(" §aDescription:");
			lorevip.add(" §f▶ §7Le grade VIP permet l'accès aux");
			lorevip.add(" §f  §7fonctionnalités exclusives telles que");
			lorevip.add(" §f  §7des kits et récompenses uniques.");
			lorevip.add("");
			lorevip.add(" §dInformations:");
			if (accountInfo.getFragmentsDames() >= 5000.0) {
				lorevip.add(" §f▶ §7Prix (Fa.): §b5.000 ✵ §8/§7mois");
			} else {
				lorevip.add(" §f▶ §7Prix (Fa.): §b5.000 ✵ §8/§7mois");
			}
			if (accountInfo.getArgent() >= 5) {
				lorevip.add(" §f▶ §7Prix (Arg.): §a5 ✪ §8/§7trimestre");
			} else {
				lorevip.add(" §f▶ §7Prix (Arg.): §a5 ✪ §8/§7trimestre");
			}
			lorevip.add("");
			if (accountInfo.getArgent() < 5 && accountInfo.getFragmentsDames() < 5000.0) {
				lorevip.add("§8➡ §cVous n'avez pas assez d'Arg./Fa. ...");
			} else {
				lorevip.add("§8➡ §fCliquez pour acheter.");
			}
			ItemStack vip = new ItemBuilder(Material.SKULL, 1)
					.setName("§7Grade: §f§lVIP")
					.setSkullUrl("http://textures.minecraft.net/texture/24b953b2c0e952574f1ed29c81e82e53bcdb1ba683259c20daeef7d554a2a798")
					.setLore(lorevip)
					.toItemStack();
			inv.setItem(20, vip);

			ArrayList<String> loresupervip = new ArrayList<String>();
			loresupervip.add("");
			loresupervip.add(" §aDescription:");
			loresupervip.add(" §f▶ §7Le grade SUPER-VIP détient de");
			loresupervip.add(" §f  §7meilleures fonctionnalités et avantages");
			loresupervip.add(" §f  §7que son prédécesseur, le VIP.");
			loresupervip.add("");
			loresupervip.add(" §dInformations:");
			if (accountInfo.getArgent() >= 10) {
				loresupervip.add(" §f▶ §7Prix (Arg.): §a10 ✪ §8/§7trimestre");
				loresupervip.add("");
				loresupervip.add("§8➡ §fCliquez pour acheter.");
			} else {
				loresupervip.add(" §f▶ §7Prix (Arg.): §a10 ✪ §8/§7trimestre");
				loresupervip.add("");
				loresupervip.add("§8➡ §cVous n'avez pas assez d'argents...");
			}
			ItemStack supervip = new ItemBuilder(Material.SKULL, 1)
					.setName("§7Grade: §e§lSUPER-VIP")
					.setSkullUrl("http://textures.minecraft.net/texture/844498a0fe278956e3d04135ef4b1343d0548a7e208c61b1fb6f3b4dbc240da8")
					.setLore(loresupervip)
					.toItemStack();
			inv.setItem(21, supervip);

			ArrayList<String> loresupreme = new ArrayList<String>();
			loresupreme.add("");
			loresupreme.add(" §aDescription:");
			loresupreme.add(" §f▶ §7Le grade SUPREME, un bon plan ?");
			loresupreme.add(" §f▶ §7Ce n'est pas le meilleur, mais ce grade");
			loresupreme.add(" §f  §7répondra largement à toutes vos attentes.");
			loresupreme.add("");
			loresupreme.add(" §dInformations:");
			if (accountInfo.getArgent() >= 20) {
				loresupreme.add(" §f▶ §7Prix (Arg.): §a20 ✪ §8/§7trimestre");
				loresupreme.add("");
				loresupreme.add("§8➡ §fCliquez pour acheter.");
			} else {
				loresupreme.add(" §f▶ §7Prix (Arg.): §a20 ✪ §8/§7trimestre");
				loresupreme.add("");
				loresupreme.add("§8➡ §cVous n'avez pas assez d'argents...");
			}
			ItemStack supreme = new ItemBuilder(Material.SKULL, 1)
					.setName("§7Grade: §a§lSUPREME §f§l┃ §e✯")
					.setSkullUrl("http://textures.minecraft.net/texture/4ba55671f97ff3bfc5be335ae92cd9749abd619e7afc2a6673597b80b755c741")
					.setLore(loresupreme)
					.toItemStack();
			inv.setItem(22, supreme);

			ArrayList<String> loreultra = new ArrayList<String>();
			loreultra.add("");
			loreultra.add(" §aDescription:");
			loreultra.add(" §f▶ §7Le grade ULTRA permettra");
			loreultra.add(" §f  §7de vous élever parmi les dieux.");
			loreultra.add(" §f▶ §7Avec lui, détenez les pouvoirs");
			loreultra.add(" §f  §7divins dont vous aurez besoin.");
			loreultra.add("");
			loreultra.add(" §dInformations:");
			if (accountInfo.getArgent() >= 25) {
				loreultra.add(" §f▶ §7Prix (Arg.): §a25 ✪ §8/§7trimestre");
				loreultra.add("");
				loreultra.add("§8➡ §fCliquez pour acheter.");
			} else {
				loreultra.add(" §f▶ §7Prix (Arg.): §a25 ✪ §8/§7trimestre");
				loreultra.add("");
				loreultra.add("§8➡ §cVous n'avez pas assez d'argents...");
			}
			ItemStack ultra = new ItemBuilder(Material.SKULL, 1)
					.setName("§7Grade: §b§lULTRA §f§l┃ §e✯")
					.setSkullUrl("http://textures.minecraft.net/texture/31f7cdfea2d21cd5f6ebbf48481761c6cbdf36d00fe64083686e9aeaa3f1f217")
					.setLore(loreultra)
					.toItemStack();
			inv.setItem(23, ultra);

			ArrayList<String> loreelite = new ArrayList<String>();
			loreelite.add("");
			loreelite.add(" §aDescription:");
			loreelite.add(" §f▶ §7Le grade ELITE, la crème de");
			loreelite.add(" §f  §7la crème des grades, il octroie");
			loreelite.add(" §f  §7des avantages plus inédits jusqu'à");
			loreelite.add(" §f  §7aujourd'hui, alors, qu'attendez-vous ?");
			loreelite.add("");
			loreelite.add(" §dInformations:");
			if (accountInfo.getArgent() >= 35) {
				loreelite.add(" §f▶ §7Prix (Arg.): §a35 ✪ §8/§7trimestre");
				loreelite.add("");
				loreelite.add("§8➡ §fCliquez pour acheter.");
			} else {
				loreelite.add(" §f▶ §7Prix (Arg.): §a35 ✪ §8/§7trimestre");
				loreelite.add("");
				loreelite.add("§8➡ §cVous n'avez pas assez d'argents...");
			}
			ItemStack elite = new ItemBuilder(Material.SKULL, 1)
					.setName("§7Grade: §3§lELITE §f§l┃ §e✯")
					.setSkullUrl("http://textures.minecraft.net/texture/337b96a45a8f0c6fd7dfab2a85e1a384daca4b806eb892a87e27f52a9f91c084")
					.setLore(loreelite)
					.toItemStack();
			inv.setItem(24, elite);

			ArrayList<String> loreinfovip = new ArrayList<String>();
			loreinfovip.add("");
			loreinfovip.add(" §bAvantages:");
			loreinfovip.add(" §f▶ §7...");
			loreinfovip.add("");
			loreinfovip.add("§8➡ §fCliquez pour en voir plus.");
			ItemStack infovip = new ItemBuilder(Material.BOOK, 1)
					.setName("§7Infos: §f§lVIP")
					.setLore(loreinfovip)
					.toItemStack();
			inv.setItem(29, infovip);

			ArrayList<String> loreinfosupervip = new ArrayList<String>();
			loreinfosupervip.add("");
			loreinfosupervip.add(" §bAvantages:");
			loreinfosupervip.add(" §f▶ §7...");
			loreinfosupervip.add("");
			loreinfosupervip.add("§8➡ §fCliquez pour en voir plus.");
			ItemStack infosupervip = new ItemBuilder(Material.BOOK, 1)
					.setName("§7Infos: §e§lSUPER-VIP")
					.setLore(loreinfosupervip)
					.toItemStack();
			inv.setItem(30, infosupervip);

			ArrayList<String> loreinfosupreme = new ArrayList<String>();
			loreinfosupreme.add("");
			loreinfosupreme.add(" §bAvantages:");
			loreinfosupreme.add(" §f▶ §7...");
			loreinfosupreme.add("");
			loreinfosupreme.add("§8➡ §fCliquez pour en voir plus.");
			ItemStack infosupreme = new ItemBuilder(Material.BOOK, 1)
					.setName("§7Infos: §a§lSUPREME §f§l┃ §e✯")
					.setLore(loreinfosupreme)
					.toItemStack();
			inv.setItem(31, infosupreme);

			ArrayList<String> loreinfoultra = new ArrayList<String>();
			loreinfoultra.add("");
			loreinfoultra.add(" §bAvantages:");
			loreinfoultra.add(" §f▶ §7...");
			loreinfoultra.add("");
			loreinfoultra.add("§8➡ §fCliquez pour en voir plus.");
			ItemStack infoultra = new ItemBuilder(Material.BOOK, 1)
					.setName("§7Infos: §b§lULTRA §f§l┃ §e✯")
					.setLore(loreinfoultra)
					.toItemStack();
			inv.setItem(32, infoultra);

			ArrayList<String> loreinfoelite = new ArrayList<String>();
			loreinfoelite.add("");
			loreinfoelite.add(" §bAvantages:");
			loreinfoelite.add(" §f▶ §7...");
			loreinfoelite.add("");
			loreinfoelite.add("§8➡ §fCliquez pour en voir plus.");
			ItemStack infoelite = new ItemBuilder(Material.BOOK, 1)
					.setName("§7Infos: §3§lELITE §f§l┃ §e✯")
					.setLore(loreinfoelite)
					.toItemStack();
			inv.setItem(33, infoelite);

        }
        
	}
}
