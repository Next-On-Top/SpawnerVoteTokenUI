package com.kyloka.voteTokenUI;



import com.kyloka.voteTokenUI.references.essentialNames;
import javafx.scene.control.Alert;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;


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

	}

	@Override
	public void onDisable() {

	}
	public static ItemStack voteTokenItem() throws NullPointerException{

			String test = main.getConfigConfig().getString("main.voteToken.item").replace(" ", "_");

			Material string2Material = Material.matchMaterial(test);
			ItemStack voteToken = new ItemStack(string2Material);

			return voteToken;
	}
	public static int getSpawnerPrice(String mobName){
		Bukkit.broadcastMessage("ohai");
		int price = 0;
		for (int i=0; i < essentialNames.nameSpawn.length;i++){
			Bukkit.broadcastMessage("hai");
			if (essentialNames.nameSpawn[i].equals(mobName)){
				Bukkit.broadcastMessage("ohi");
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
					target.sendMessage(getPricesConfig().get("main.voteToken.price.Blaze")+ "");

				}
				catch(NullPointerException e ){
					target.sendMessage("Check Console");
					getLogger().info("Error in config at voteToken material!");
					getLogger().info(getConfigConfig().get("main") + "");
				}
					target.sendMessage(getPricesConfig().get("main.voteToken.price.Zombie Pigman")+ "");
			}
			else{
				sender.sendMessage("You don't have permission to access this command");
			}
		}
		if (command.getName().equalsIgnoreCase("balVote")){

			sender.sendMessage("§6[§4VoteTokenBalance§6]§f: §c" + main.playerDataConfig.getInt("main." + sender.getName() + ".voteTokenCount"));

			return true;


		}
		if (command.getName().equalsIgnoreCase("rng")){



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

			try {
					configFile.createNewFile();
					configConfig.set("main.voteToken.price",96);
					configConfig.set("main.voteToken.item","bedrock");
					configConfig.set("main.version",1.0);

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
}

