package fr.edminecoreteam.store.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import fr.edminecoreteam.store.data.SectionSQL;
import fr.edminecoreteam.store.data.StoreInfo;
import fr.edminecoreteam.store.utils.CustomSounds;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class StoreMessage 
{
	public static void getHelp(Player player)
	{
		player.sendMessage("");
        player.sendMessage(" §7» §e§lCentre d'aide §e(ED-Store):");
        player.sendMessage("");
        player.sendMessage(" §7• §d/§fstore §8§l» §7Gestion du store.");
        player.sendMessage(" §7• §d/§fstore list §c[type] §8§l» §7Liste des articles.");
        player.sendMessage(" §7• §d/§fstore info §d[id] §8§l» §7Liste des articles.");
        player.sendMessage(" §7• §d/§fstore article §8§l» §7Gestion des articles.");
        player.sendMessage(" §7• §d/§fstore maintenance §8§l» §7Maintenances d'articles ou store.");
        player.sendMessage(" §7• §d/§fstore reduction §8§l» §7Gestion des réductions.");
        player.sendMessage(" §7• §d/§fstore section §8§l» §7Gestion des boutiques.");
        player.sendMessage("");
        CustomSounds.openShopSound(player);
	}
	
	public static void getHelpArticle(Player player)
	{
		player.sendMessage("");
        player.sendMessage(" §7» §e§lCentre d'aide §e(ED-Store):");
        player.sendMessage("");
        player.sendMessage(" §7• §d/§fstore §8§l» §7Gestion du store.");
        player.sendMessage(" §7• §d/§fstore article create §c[type] §e[name] §f[moneyType] §a[price] §6[rarity] §b[item] §8§l» §7Créer un article.");
        player.sendMessage(" §7• §d/§fstore article remove §d[id] §8§l» §7Supprimer un article.");
        player.sendMessage(" §7• §d/§fstore article update name §d[id] §f[name] §8§l» §7Update le nom d'un article.");
        player.sendMessage(" §7• §d/§fstore article update page §d[id] §f[page] §8§l» §7Update la page de l'article.");
        player.sendMessage(" §7• §d/§fstore article update subtype §d[id] §f[subType] §8§l» §7Update le sous-type d'un article.");
        player.sendMessage(" §7• §d/§fstore article update moneytype §d[id] §f[moneyType] §8§l» §7Update le prix d'un article.");
        player.sendMessage(" §7• §d/§fstore article update price §d[id] §a[price] §8§l» §7Update le prix d'un article.");
        player.sendMessage(" §7• §d/§fstore article update reduction §d[id] §5[reduction] §8§l» §7Update le pourcentage de l'article.");
        player.sendMessage(" §7• §d/§fstore article update rarity §d[id] §6[rarity] §8§l» §7Update la rareté de l'article.");
        player.sendMessage(" §7• §d/§fstore article update item §d[id] §b[item] §8§l» §7Update l'item de l'article.");
        player.sendMessage(" §7• §d/§fstore article update itemslot §d[id] §e[itemID] §8§l» §7Update l'itemSlot de l'article.");
        player.sendMessage(" §7• §d/§fstore article update skull §d[id] §b[url] §8§l» §7Update l'url du skull de l'article.");
        player.sendMessage("");
        CustomSounds.openShopSound(player);
	}
	
	public static void getHelpMaintenance(Player player)
	{
		player.sendMessage("");
        player.sendMessage(" §7» §e§lCentre d'aide §e(ED-Store):");
        player.sendMessage("");
        player.sendMessage(" §7• §d/§fstore §8§l» §7Gestion du store.");
        player.sendMessage(" §7• §d/§fstore maintenance §d[id] §aon§8/§coff §8§l» §7Activer ou désactiver un article.");
        player.sendMessage(" §7• §d/§fstore maintenance section §c[type] §aon§8/§coff §8§l» §7Activer ou désactiver une section de boutique.");
        player.sendMessage("");
        CustomSounds.openShopSound(player);
	}
	
	public static void getHelpReduction(Player player)
	{
		player.sendMessage("");
        player.sendMessage(" §7» §e§lCentre d'aide §e(ED-Store):");
        player.sendMessage("");
        player.sendMessage(" §7• §d/§fstore §8§l» §7Gestion du store.");
        player.sendMessage(" §7• §d/§fstore reduction set §d[id] §aon§8/§coff §8§l» §7Activer ou non une réduction sur un article.");
        player.sendMessage(" §7• §d/§fstore reduction section set §c[type] §aon§8/§coff §8§l» §7Activer ou non une réduction sur une section.");
        player.sendMessage("");
        CustomSounds.openShopSound(player);
	}
	
	public static void getHelpSection(Player player)
	{
		player.sendMessage("");
        player.sendMessage(" §7» §e§lCentre d'aide §e(ED-Store):");
        player.sendMessage("");
        player.sendMessage(" §7• §d/§fstore §8§l» §7Gestion du store.");
        player.sendMessage(" §7• §d/§fstore section list §8§l» §7Liste des sections.");
        player.sendMessage(" §7• §d/§fstore section add §c[type] §8§l» §7Ajouter une section.");
        player.sendMessage(" §7• §d/§fstore section remove §c[type] §8§l» §7Ajouter une section.");
        player.sendMessage(" §7• §d/§fstore section reduction §c[type] §5[reduction] §8§l» §7Update le pourcentage de la section.");
        player.sendMessage(" §7• §d/§fstore section page set §c[type] §e[page] §8§l» §7Update le nombre de page de la section.");
        player.sendMessage("");
        CustomSounds.openShopSound(player);
	}
	
	public static void getInfoArticle(Player player, int getID)
	{
		StoreInfo storeInfo = new StoreInfo(getID);
		String name = storeInfo.getArticleName().replace("_", " ");
		player.sendMessage("");
        player.sendMessage(" §7» §e§lInformations d'un article §e(ED-Store):");
        player.sendMessage("");
        player.sendMessage(" §7• §fCatégorie: §c" + storeInfo.getArticleType());
        player.sendMessage(" §7• §fNom: §e" + name);
        player.sendMessage(" §7• §fID de l'article: §d" + getID);
        if (storeInfo.getTypeOfPay().equalsIgnoreCase("f.a"))
        {
        	player.sendMessage(" §7• §fType de monnaie: §bFragments d'âmes ✵");
        	player.sendMessage(" §7• §fPrix: §b" + storeInfo.getArticlePrice() + "§b✵");
        }
        else if (storeInfo.getTypeOfPay().equalsIgnoreCase("e.d"))
        {
        	player.sendMessage(" §7• §fType de monnaie: §dEclats divins ❁");
        	player.sendMessage(" §7• §fPrix: §d" + storeInfo.getArticlePrice() + "§d❁");
        }
        else if (storeInfo.getTypeOfPay().equalsIgnoreCase("a"))
        {
        	player.sendMessage(" §7• §fType de monnaie: §aArgent ✪");
        	player.sendMessage(" §7• §fPrix: §a" + storeInfo.getArticlePrice() + "§a✪");
        }
        if (storeInfo.getIsInReduction() == "enable")
        {
        	player.sendMessage(" §7• §fRéduction: §aactivé");
        }
        else
        {
        	player.sendMessage(" §7• §fRéduction: §cdésactivé");
        }
        player.sendMessage(" §7• §fPourcentage de réduction: §a" + storeInfo.getReductionPercentage() + "§a%");
        if (storeInfo.getArticleRarity() == 1)
        {
        	player.sendMessage(" §7• §fRareté: §6✯§8✯✯✯✯");
        }
        else if (storeInfo.getArticleRarity() == 2)
        {
        	player.sendMessage(" §7• §fRareté: §6✯✯§8✯✯✯");
        }
        else if (storeInfo.getArticleRarity() == 3)
        {
        	player.sendMessage(" §7• §fRareté: §6✯✯✯§8✯✯");
        }
        else if (storeInfo.getArticleRarity() == 4)
        {
        	player.sendMessage(" §7• §fRareté: §6✯✯✯✯§8✯");
        }
        else if (storeInfo.getArticleRarity() == 5)
        {
        	player.sendMessage(" §7• §fRareté: §6✯✯✯✯✯");
        }
        player.sendMessage(" §7• §fItem: §e" + storeInfo.getArticleItem());
        player.sendMessage(" §7• §fItem slot: §9" + storeInfo.getArticleItemSlot());
        player.sendMessage(" §7• §fTête custom (url): §5" + storeInfo.getArticleSkull());
        player.sendMessage("");
        CustomSounds.openShopSound(player);
	}
	
	public static void getPurchase(Player player, int getID)
	{
		StoreInfo storeInfo = new StoreInfo(getID);
		String name = storeInfo.getArticleName().replace("_", " ").replace("&", "§");
		player.sendMessage("");
        player.sendMessage(" §7» §e§lAchat réussi ! §e(ED-Store):");
        player.sendMessage("");
        player.sendMessage(" §7• §fArticle: §e" + name);
        if (storeInfo.getTypeOfPay().equalsIgnoreCase("f.a"))
        {
        	player.sendMessage(" §7• §fType de monnaie: §bFragments d'âmes ✵");
        	player.sendMessage(" §7• §fPrix: §b" + storeInfo.getArticlePrice() + "§b✵");
        }
        else if (storeInfo.getTypeOfPay().equalsIgnoreCase("e.d"))
        {
        	player.sendMessage(" §7• §fType de monnaie: §dEclats divins ❁");
        	player.sendMessage(" §7• §fPrix: §d" + storeInfo.getArticlePrice() + "§d❁");
        }
        else if (storeInfo.getTypeOfPay().equalsIgnoreCase("a"))
        {
        	player.sendMessage(" §7• §fType de monnaie: §aArgent ✪");
        	player.sendMessage(" §7• §fPrix: §a" + storeInfo.getArticlePrice() + "§a✪");
        }
        if (storeInfo.getIsInReduction().equalsIgnoreCase("enable"))
        {
        	player.sendMessage(" §7• §fRéduction: §aactivée");
        	player.sendMessage(" §7• §fPourcentage de réduction: §a" + storeInfo.getReductionPercentage() + "§a%");
        }
        else
        {
        	player.sendMessage(" §7• §fRéduction: §cdésactivée");
        }
        if (storeInfo.getArticleRarity() == 1)
        {
        	player.sendMessage(" §7• §fRareté: §6✯§8✯✯✯✯");
        }
        else if (storeInfo.getArticleRarity() == 2)
        {
        	player.sendMessage(" §7• §fRareté: §6✯✯§8✯✯✯");
        }
        else if (storeInfo.getArticleRarity() == 3)
        {
        	player.sendMessage(" §7• §fRareté: §6✯✯✯§8✯✯");
        }
        else if (storeInfo.getArticleRarity() == 4)
        {
        	player.sendMessage(" §7• §fRareté: §6✯✯✯✯§8✯");
        }
        else if (storeInfo.getArticleRarity() == 5)
        {
        	player.sendMessage(" §7• §fRareté: §6✯✯✯✯✯");
        }
        player.sendMessage("");
        CustomSounds.purchaseSound(player);
	}
	
	public static void getSectionList(Player player)
	{
			List<String> sectionEnable = new ArrayList<String>();
			List<String> sectionDisable = new ArrayList<String>();
			for (String sections : SectionSQL.getSectionList("enable")) 
			{
				sectionEnable.add(sections);
            }
			for (String sections : SectionSQL.getSectionList("disable")) 
			{
				sectionDisable.add(sections);
            }
				int sectionCount = sectionEnable.size() + sectionDisable.size();
				player.sendMessage("");
				player.sendMessage(" §7» §e§lListe des Sections §e(ED-Store):");
				player.sendMessage("");
				player.sendMessage(" §7• §fNombre de sections: §e" + sectionCount);
				player.sendMessage("");
				TextComponent sectionOnlineList = new TextComponent(" §7➡ §fSections Activées: ");
				int counterSectionEnable = 0;
				int finalCountSectionEnable = sectionEnable.size();
                for (String sections : sectionEnable)
 				{
 					TextComponent tokenComponent = new TextComponent("§a" + sections);
 					tokenComponent.setBold(true);
 					tokenComponent.setUnderlined(true);
 					tokenComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Statut: §a" + SectionSQL.getSectionStatus(sections))));
 					tokenComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/store list " + sections));

 					sectionOnlineList.addExtra(tokenComponent);
 					counterSectionEnable++;
 					if (counterSectionEnable == finalCountSectionEnable)
 					{
 						sectionOnlineList.addExtra("§7.");
 					}
 					else
 					{
 						sectionOnlineList.addExtra("§7, ");
 					}
 				}
                player.spigot().sendMessage(sectionOnlineList);
                
                TextComponent sectionDisableList = new TextComponent(" §7➡ §fSections Désactivées: ");
				int counterSectionDisable = 0;
				int finalCountSectionDisable = sectionDisable.size();
                for (String sections : sectionDisable)
 				{
 					TextComponent tokenComponent = new TextComponent("§c" + sections);
 					tokenComponent.setBold(true);
 					tokenComponent.setUnderlined(true);
 					tokenComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Statut: §c" + SectionSQL.getSectionStatus(sections))));
 					tokenComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/store list " + sections));

 					sectionDisableList.addExtra(tokenComponent);
 					counterSectionDisable++;
 					if (counterSectionDisable == finalCountSectionDisable)
 					{
 						sectionDisableList.addExtra("§7.");
 					}
 					else
 					{
 						sectionDisableList.addExtra("§7, ");
 					}
 				}
                player.spigot().sendMessage(sectionDisableList);
                player.sendMessage("");
                player.sendMessage(" §8• §7Visionnez les sections disponibles sur la boutique.");
                player.sendMessage("");
			
                CustomSounds.openShopSound(player);
	}
	
	public static void getArticlesListByGroup(Player player, String type)
	{
			List<Integer> articleEnable = new ArrayList<Integer>();
			List<Integer> articleDisable = new ArrayList<Integer>();
			for (Integer articles : SectionSQL.getArticleListByType(type, "enable")) 
			{
				articleEnable.add(articles);
            }
			for (Integer articles : SectionSQL.getArticleListByType(type, "disable")) 
			{
				articleDisable.add(articles);
            }
				int articleCount = articleEnable.size() + articleDisable.size();
				player.sendMessage("");
				player.sendMessage(" §7» §e§lListe des Articles §e(ED-Store):");
				player.sendMessage("");
				player.sendMessage(" §7• §fNombre d'articles: §e" + articleCount);
				player.sendMessage("");
				TextComponent articleOnlineList = new TextComponent(" §7➡ §fArticles Activées: ");
				int counterArticleEnable = 0;
				int finalCountArticleEnable = articleEnable.size();
                for (Integer article : articleEnable)
 				{
                	StoreInfo storeInfo = new StoreInfo(article);
 					TextComponent tokenComponent = new TextComponent("§a" + article);
 					tokenComponent.setBold(true);
 					tokenComponent.setUnderlined(true);
 					tokenComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Nom: §a" + storeInfo.getArticleName().replace("_", " "))));
 					tokenComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/store info " + article));

 					articleOnlineList.addExtra(tokenComponent);
 					counterArticleEnable++;
 					if (counterArticleEnable == finalCountArticleEnable)
 					{
 						articleOnlineList.addExtra("§7.");
 					}
 					else
 					{
 						articleOnlineList.addExtra("§7, ");
 					}
 				}
                player.spigot().sendMessage(articleOnlineList);
                
                TextComponent articleDisableList = new TextComponent(" §7➡ §fArticles Désactivées: ");
				int counterArticlesDisable = 0;
				int finalCountArticlesDisable = articleDisable.size();
                for (Integer article : articleDisable)
 				{
                	StoreInfo storeInfo = new StoreInfo(article);
 					TextComponent tokenComponent = new TextComponent("§c" + article);
 					tokenComponent.setBold(true);
 					tokenComponent.setUnderlined(true);
 					tokenComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Nom: §c" + storeInfo.getArticleName().replace("_", " "))));
 					tokenComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/store info " + article));

 					articleDisableList.addExtra(tokenComponent);
 					counterArticlesDisable++;
 					if (counterArticlesDisable == finalCountArticlesDisable)
 					{
 						articleDisableList.addExtra("§7.");
 					}
 					else
 					{
 						articleDisableList.addExtra("§7, ");
 					}
 				}
                player.spigot().sendMessage(articleDisableList);
                player.sendMessage("");
                player.sendMessage(" §8• §7Visionnez les articles disponibles sur la boutique.");
                player.sendMessage("");
			
                CustomSounds.openShopSound(player);
	}
}
