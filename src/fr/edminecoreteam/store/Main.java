package fr.edminecoreteam.store;

import java.util.HashMap;

import fr.edminecoreteam.store.utils.BoutiqueListListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.edminecoreteam.store.command.BuyCommand;
import fr.edminecoreteam.store.command.StoreCommand;
import fr.edminecoreteam.store.edorm.MySQL;
import fr.edminecoreteam.store.edorm.SQLState;
import fr.edminecoreteam.store.gui.StoreBoosterGui;
import fr.edminecoreteam.store.gui.StoreGamesGui;
import fr.edminecoreteam.store.gui.StoreInteractionGui;
import fr.edminecoreteam.store.gui.StoreKeysGui;
import fr.edminecoreteam.store.gui.StoreMainGui;
import fr.edminecoreteam.store.gui.StorePackGui;
import fr.edminecoreteam.store.gui.StoreRankGui;
import fr.edminecoreteam.store.gui.SubTypeGui;
import fr.edminecoreteam.store.item.StoreItem;
import fr.edminecoreteam.store.purchase.PurchaseGui;
import fr.edminecoreteam.store.task.ShopOfTheWeek;

public class Main extends JavaPlugin
{
    private static Main instance;
    private HashMap<Player, Integer> playerIDPurchase = new HashMap<>();
    private HashMap<Player, Integer> playerPage = new HashMap<>();
    private HashMap<Player, String> playerPageType = new HashMap<>();

    public MySQL database;
    private SQLState sqlState;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        MySQLConnect();
        loadListeners();
        loadCommands();
        loadBoutiqueList();
    }

    @Override
    public void onDisable() {
        MySQLDisconnect();
    }

    private void loadBoutiqueList(){
        BoutiqueListListener.getBoutiqueList();
    }
    private void loadListeners()
    {
        Main.instance = this;
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents((Listener)new StoreItem(), (Plugin)this);
        pm.registerEvents((Listener)new StoreInteractionGui(), (Plugin)this);

        pm.registerEvents((Listener)new StoreMainGui(), (Plugin)this);
        pm.registerEvents((Listener)new StoreRankGui(), (Plugin)this);
        pm.registerEvents((Listener)new StoreBoosterGui(), (Plugin)this);
        pm.registerEvents((Listener)new StoreKeysGui(), (Plugin)this);
        pm.registerEvents((Listener)new StorePackGui(), (Plugin)this);
        pm.registerEvents((Listener)new StoreGamesGui(), (Plugin)this);

        pm.registerEvents((Listener)new SubTypeGui(), (Plugin)this);
        pm.registerEvents((Listener)new PurchaseGui(), (Plugin)this);

        ShopOfTheWeek start = new ShopOfTheWeek(this);
        start.runTaskTimer((Plugin)this, 0L, 20L);
    }

    private void loadCommands()
    {
        this.getCommand("store").setExecutor((CommandExecutor)new StoreCommand());
        this.getCommand("buy").setExecutor((CommandExecutor)new BuyCommand());
    }

    /*
     * Méthode de connexion au serveur SQL.
     *
     * "jdbc:mysql://", "45.140.165.235", "22728-database", "22728-database", "S5bV5su4p9"
     */
    public void MySQLConnect()
    {
        instance = this;

        (this.database = new MySQL(instance, "jdbc:mysql://", "localhost", "edmine-db", "edmine-db", "@3VrvjB_zrP@eY!9")).connexion();

        database.creatingTableStore();
        database.creatingTableStoreType();
        database.creatingTableStorePurchase();
        database.creatingTableShopOfTheWeek();
    }

    public int getIDPurchase(Player player) {
        if (playerIDPurchase.containsKey(player)) {
            return playerIDPurchase.get(player);
        }
        return 0;
    }

    public void addIDPurchase(Player player, int value) {
        playerIDPurchase.put(player, value);
    }

    // Méthode pour supprimer un joueur et sa valeur int associée de la carte
    public void removeIDPurchase(Player player) {
        playerIDPurchase.remove(player);
    }

    public int getPage(Player player) {
        if (playerPage.containsKey(player)) {
            return playerPage.get(player);
        }
        return 0;
    }

    public void addPage(Player player, int value) {
        playerPage.put(player, value);
    }


    public void removePage(Player player) {
        playerPage.remove(player);
    }

    public String getPageType(Player player) {
        if (playerPageType.containsKey(player)) {
            return playerPageType.get(player);
        }
        return "";
    }

    public void addPageType(Player player, String value) {
        playerPageType.put(player, value);
    }


    public void removePageType(Player player) {
        playerPageType.remove(player);
    }

    /*
     * Méthode de déconnexion au serveur SQL.
     */
    private void MySQLDisconnect()
    {
        database.deconnexion();
    }

    /*
     * Méthode de statut de l'instance MySQL
     * State List: DISCONECTED 0, CONECTED 1.
     */
    public void setSQLState(SQLState sqlState)
    {
        this.sqlState = sqlState;
    }
    public boolean isSQLState(SQLState sqlState)
    {
        return this.sqlState == sqlState;
    }

    public static Main getInstance() {  return Main.instance; }
    public static Plugin getPlugin() { return null; }
}
