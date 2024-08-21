package fr.edminecoreteam.store.utils;

import fr.edminecoreteam.store.Main;
import fr.edminecoreteam.store.data.StoreInfo;

import java.util.HashMap;

public class BoutiqueListListener {

    // Store //
    public static HashMap<Integer, String> storeList = new HashMap<>();
    public static HashMap<Integer, Integer> storeRarety = new HashMap<>();
    public static HashMap<Integer, String> storeSkull = new HashMap<>();

    public static void getBoutiqueList() {
        for (String articles : Main.getInstance().getConfig().getConfigurationSection("packs.pack1").getKeys(false)){
            Integer articleID = Main.getInstance().getConfig().getInt("packs.pack1." + articles + ".referenceid");
            StoreInfo storeInfo = new StoreInfo(articleID);

            storeRarety.put(articleID, storeInfo.getArticleRarity());
            storeList.put(articleID, storeInfo.getArticleName());
            storeSkull.put(articleID, storeInfo.getArticleSkull());

        }
    }
}
