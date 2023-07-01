package fr.edminecoreteam.store.account;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;


public class AccountInfo 
{
	private Map<Player, AccountInfo> accountInfo;
	private Player p;
	private AccountData accountData;
	private EcoSysData ecoSysData;
	//accountInfo = new HashMap<Player, AccountInfo>();
	
	public AccountInfo(Player p) {
        this.p = p;
        this.accountInfo = new HashMap<Player, AccountInfo>();
        this.accountData = new AccountData(p);
        this.ecoSysData = new EcoSysData(p);
        this.accountInfo.put(p, this);
    }
	
	
	
	public AccountInfo getAccountInfos(Player player) { return accountInfo.get(player); }
	
	public Player getPlayer() { return p; }
	
	public int getAccountID() { return accountData.getAccountID(); }
	
	public String getPlayerName() { return p.getName(); }
	
	public void createAccount() { accountData.createAccount(); }
	public boolean hasAccount() { return accountData.hasaccount(); }
	public void updateUUIDAccount() { accountData.updateUUIDAccount(); }
	public String isOnline() { return accountData.isOnline(); }
	
	public Float getFragmentsDames() { return ecoSysData.getFragmentsDames(); }
	
	public Float getEclatsDivins() { return ecoSysData.getEclatsDivins(); }
	
	public Float getArgent() { return ecoSysData.getArgent(); }
    
}
