package com.kyloka.voteTokenUI;



import com.avaje.ebean.LogLevel;
import com.kyloka.voteTokenUI.references.essentialNames;
import com.kyloka.voteTokenUI.references.itemsGUI;
import de.dustplanet.silkspawners.SilkSpawners;
import javafx.scene.control.Alert;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


public class main extends JavaPlugin{

	public static File configFolder;
	public static File configFile;
	public static File playerDataFile;
	public static File priceFile;
	public static FileConfiguration configConfig;
	public static FileConfiguration playerDataConfig;
	public static FileConfiguration priceConfig;



	@Override
	public void onEnable() {
		PluginManager pm = this.getServer().getPluginManager();
		registerConfigs();
		if (pm.getPlugin("SilkSpawners") == null){
			getLogger().log(Level.WARNING,"SilkSpawners is not installed, VTSpawnerGUI is disabled");
			pm.disablePlugin(this);

		}
	}

	@Override
	public void onDisable() {

	}
	public boolean dependency(){
		PluginManager pm = this.getServer().getPluginManager();




		return false;
	}
	public static ItemStack voteTokenItem() throws NullPointerException{

		String test = main.getConfigConfig().getString("main.voteToken.item").replace(" ", "_");

		Material string2Material = Material.matchMaterial(test);
		ItemStack voteToken = new ItemStack(string2Material);
		ItemMeta voteTokenMeta = voteToken.getItemMeta();
		voteTokenMeta.setDisplayName(format(getConfigConfig().getString("main.voteToken.name")));
		List <String> ayy = getConfigConfig().getStringList("main.voteToken.lore");
		if (getConfigConfig().getStringList("main.voteToken.lore") == null){

		}

		else{
			voteTokenMeta.setLore(formatList(ayy));
		}
		voteToken.setItemMeta(voteTokenMeta);
		return voteToken;
	}
	public static int getSpawnerPrice(String mobName){

		int price = 0;
		for (int i=0; i < essentialNames.nameSpawn.length;i++){
			if (essentialNames.nameSpawn[i].equalsIgnoreCase(mobName)){

				try {
					price = getPricesConfig().getInt("main.voteToken.price." + essentialNames.nameSpawn[i]);
					return price;
				}
				catch(NullPointerException e){

				}
			}

			else{}

		}
		return 500;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (command.getName().equalsIgnoreCase("vtdebug")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Command cannot be performed in console");
			}
			if (sender.isOp()){
				Player target = (Player) sender;
				try {
					target.getInventory().addItem(voteTokenItem());
					target.sendMessage(getSpawnerPrice("Witch") + "");
					target.sendMessage(format("&6What is up bitches?!?"));

				}
				catch(NullPointerException e ){
					target.sendMessage("Check Console");
					getLogger().info("Error in config at voteToken material!");
					getLogger().info(getConfigConfig().get("main") + "");
				}
					target.sendMessage(getPricesConfig().get("main.voteToken.price.Zombie Pigman")+ "");
			}
			else{
				sender.sendMessage(ChatColor.RED + "You don't have permission to access this command");
			}
		}
		if (command.getName().equalsIgnoreCase("balVote")){

			sender.sendMessage("§6[§4VoteTokenBalance§6]§f: §c" + main.playerDataConfig.getInt("main." + sender.getName() + ".voteTokenCount"));

			return true;


		}
		if (command.getName().equalsIgnoreCase("rng")){
			if (!(sender instanceof Player)){
				return true;
			}
			Player target = (Player) sender;
			Inventory inv = Bukkit.createInventory(null,45,ChatColor.AQUA + "VoteToken");
			/*for(int i = 0; i<itemsGUI.guiItems(target).indexOf(); i++){
				if (itemsGUI.guiItems(target).get(i) == null){
				}
				else {
					inv.setItem(i, itemsGUI.guiItems(target).get(i));
				}
			}*/
			ItemStack placeholder = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 8);
			List <ItemStack> itemsD;
			itemsD = itemsGUI.guiItems(target);
			for (int i = 0; i < 45; i++){
				if(itemsD.get(i) == null){
					itemsD.set(i,placeholder);

				}
				inv.setItem(i,itemsD.get(i));
			}
			itemsD = itemsGUI.guiItems(target);

			target.sendMessage(""+ itemsGUI.guiItems(target).size());
			target.openInventory(inv);
		}


		return false;
	}
	public void registerConfigs(){

		loadconfigConfig();
		loadplayerConfig();
		loadPricesConfig();
	}


	public void loadplayerConfig(){
		configFolder = getDataFolder();
		playerDataFile = new File(configFolder, "user.yml");
		playerDataConfig = new YamlConfiguration();
		if(!playerDataFile.exists()){
			try {
				playerDataFile.createNewFile();


			}catch(Exception e){

			}
		}
	}
	public void loadconfigConfig(){
		configFolder = getDataFolder();
		configFile = new File(configFolder, "config.yml");
		configConfig = new YamlConfiguration();

		if (!configFile.exists()){
			if(!configFolder.exists())
			{
				try {

					configFolder.mkdir();


				}
				catch (Exception ex){
					getLogger().info(Alert.AlertType.ERROR + "Unable to create plugin configuration folder!");
				}
			}
			List <String> defaultLore = new ArrayList<String>();
			defaultLore.add(0,"&5Thanks for voting!");
			try {
					configFile.createNewFile();
					configConfig.set("main.voteToken.name","&4&ka&r &eVoteToken &4&ka&r");
					configConfig.set("main.voteToken.item","bedrock");
					configConfig.set("main.version",1.0);
				    configConfig.set("main.voteToken.lore",defaultLore);

				}
				catch(Exception e){
					getLogger().info(Alert.AlertType.ERROR + "Unable to create or edit config.yml!");
				}
				try{
					configConfig.save(configFile);
				}
				catch(Exception e){
					getLogger().info(Alert.AlertType.ERROR + "Cannot save the config.yml in the config folder!");
				}

			}


	}
	public void loadPricesConfig(){
		priceFile = new File(configFolder, "price.yml");
		priceConfig = new YamlConfiguration();

		if (!priceFile.exists()) {
			try{
				priceFile.createNewFile();
			}
			catch(Exception e){}
			for (int i = 0; i < essentialNames.nameSpawn.length;i++) {
				priceConfig.set("main.voteToken.price." + essentialNames.nameSpawn[i],96);

			}
			try{
				priceConfig.save(priceFile);
			}catch(Exception e){}
			return;
		}
		return;
	}
	public static FileConfiguration getConfigConfig(){
		configConfig = new YamlConfiguration();
		if(configConfig == null){
			return null;
		}
		try {
			configConfig.load(configFile);
		}catch(Exception e){

		}

		return configConfig;
	}

	public static FileConfiguration getPricesConfig(){
		priceConfig = new YamlConfiguration();
		if(priceConfig == null){
			return null;
		}
		try {
			priceConfig.load(priceFile);
		}catch(Exception e){

		}
		return priceConfig;

	}
	public static FileConfiguration getPlayerDataConfig(){
		playerDataConfig = new YamlConfiguration();
		if(playerDataConfig == null){
			return null;
		}

		try{
			playerDataConfig.load(playerDataFile);
		}
		catch(Exception e){}
		return playerDataConfig;
	}

	public static String format(String form1){
		String form2 = form1.replace("&","§");
		return form2;
	}
	public static List<String> formatList(List<String> form1){
		List<String> form2 = new ArrayList<String>();
		for(int i=0;i <form1.size();i++){
			String form3 = form1.get(i).replace("&","§");
			form2.add(i,form3);

		}
		return form2;
	}
}

