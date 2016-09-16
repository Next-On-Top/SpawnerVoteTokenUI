package com.kyloka.voteTokenUI.events;

import com.kyloka.voteTokenUI.gui.openUI;
import com.kyloka.voteTokenUI.main;
import com.kyloka.voteTokenUI.references.essentialNames;
import de.dustplanet.util.SilkUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Matthew on 9/15/2016.
 */
public class inventoryClickEvent implements Listener {
    private static SilkUtil su;

    @SuppressWarnings("deprecation")
    public static ItemStack createSpawner(EntityType type, int amount) {
        if (su == null) su = SilkUtil.hookIntoSilkSpanwers();
        short entityID = type.getTypeId();
        return su.newSpawnerItem(entityID, su.getCustomSpawnerName(su.eid2MobID.get(entityID)), amount, false);
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("VoteToken"))
            return;
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR|| !event.getCurrentItem().hasItemMeta() ) {
            player.closeInventory();
            player.updateInventory();
            return;
        }
        if (event.getCurrentItem().getType() == Material.REDSTONE_BLOCK && event.getCurrentItem().getAmount() == 1){
            int hi = 0;
            Inventory inv = player.getInventory();

            for ( ItemStack it : inv) {
                if (it != null) {
                    if (it.getType() == null || it.getAmount() < 0 || inv.getType().equals(Material.AIR) || it == null) {
                        return;
                    }


                    if (it.getType().equals(main.voteTokenItem().getType())) {


                        if (it.getItemMeta().equals(main.voteTokenItem().getItemMeta())) {
                            inv.removeItem(main.voteTokenItem());


                            main.playerDataConfig.set("main." + player.getName() + ".voteTokenCount", main.playerDataConfig.getInt("main." + player.getName() + ".voteTokenCount") + 1);
                            player.sendMessage(ChatColor.BLUE + "Successfully deposited 1 votetoken!");
                            try {
                                main.playerDataConfig.save(main.playerDataFile);
                            } catch (Exception ex) {
                            }
                            hi = hi +1;
                            player.updateInventory();
                            break;

                        } else {


                        }
                    } else {

                    }

                }

            }


            if (hi == 0){
                player.sendMessage(ChatColor.RED + "You dont have vote tokens in your inventory!");
                openUI.refresh(player);
            }

        }
        if (event.getCurrentItem().getType() == Material.REDSTONE_BLOCK && event.getCurrentItem().getAmount() == 16){
            int hi = 0;
            Inventory inv = player.getInventory();

            for ( ItemStack it : inv) {
                if (it != null) {
                    if (it.getType() == null || it.getAmount() < 0 || inv.getType().equals(Material.AIR) || it == null) {
                        return;
                    }


                    if (it.getType().equals(Material.BEDROCK)&& it.getAmount() >= 16) {
                        ItemStack gecko = main.voteTokenItem();
                        gecko.setAmount(16);


                        if (it.getItemMeta().equals(gecko.getItemMeta())) {
                            inv.removeItem(gecko);


                            main.playerDataConfig.set("main." + player.getName() + ".voteTokenCount", main.playerDataConfig.getInt("main." + player.getName() + ".voteTokenCount") + 16);
                            player.sendMessage(ChatColor.BLUE + "Successfully deposited §e16 §9VoteToken!");
                            try {
                                main.playerDataConfig.save(main.playerDataFile);
                            } catch (Exception ex) {
                            }
                            hi = hi +1;
                            player.updateInventory();
                            break;

                        } else {


                        }
                    } else {

                    }

                }

            }
            if (hi == 0){
                player.sendMessage(ChatColor.RED + "You dont §e16 §chave vote tokens in your inventory!");
                player.closeInventory();
                openUI.open(player);

            }

        }
        else if(event.getCurrentItem().getType() == Material.LAPIS_BLOCK && event.getCurrentItem().getAmount() == 1){
            if(main.playerDataConfig.getInt("main." + player.getName() + ".voteTokenCount") == 0){
                player.sendMessage("You dont have any VoteToken stored");
                player.closeInventory();
                openUI.open(player);

            }
            else if (main.playerDataConfig.getInt("main." + player.getName() + ".voteTokenCount") >= event.getCurrentItem().getAmount()){
                main.playerDataConfig.set("main." + player.getName() + ".voteTokenCount",main.playerDataConfig.getInt("main." + player.getName() + ".voteTokenCount") - 1);
                player.getInventory().addItem(main.voteTokenItem());
                player.sendMessage(ChatColor.BLUE + "Withdrew "+ChatColor.YELLOW +  event.getCurrentItem().getAmount()+ ChatColor.BLUE + " Vote Token");
                player.updateInventory();
                try {
                    main.playerDataConfig.save(main.playerDataFile);
                } catch (Exception ex) {
                }


            }


        }
        else if(event.getCurrentItem().getType() == Material.LAPIS_BLOCK && event.getCurrentItem().getAmount() == 16){
            if(main.playerDataConfig.getInt("main." + player.getName() + ".voteTokenCount") < 16){
                player.sendMessage("You dont have any VoteToken stored");
                player.closeInventory();
                openUI.open(player);

            }
            else if (main.playerDataConfig.getInt("main." + player.getName() + ".voteTokenCount") >= event.getCurrentItem().getAmount()){
                main.playerDataConfig.set("main." + player.getName() + ".voteTokenCount",main.playerDataConfig.getInt("main." + player.getName() + ".voteTokenCount") - 16);
                ItemStack test = main.voteTokenItem();
                ItemMeta testMeta = test.getItemMeta();
                test.setAmount(event.getCurrentItem().getAmount());

                player.getInventory().addItem(test);

                player.sendMessage(ChatColor.BLUE + "Withdrew "+ChatColor.YELLOW +  event.getCurrentItem().getAmount()+ ChatColor.BLUE + " Vote Token");
                player.updateInventory();
                try {
                    main.playerDataConfig.save(main.playerDataFile);
                }
                catch (Exception ex) {
                }


            }


        }
        else if (event.getCurrentItem().getType() == Material.STAINED_GLASS_PANE && event.getCurrentItem().getDurability() == 13){
            player.closeInventory();
            openUI.open(player);
        }
        for(int i = 0; i < essentialNames.materialNames.length; i++){

            if (event.getCurrentItem().getType() == essentialNames.materialNames[i]){
                if(player.getInventory().firstEmpty() == -1){
                    player.sendMessage(ChatColor.RED + "Cannot insert spawner in your inventory, please clear some space");
                    player.closeInventory();
                    break;
                }
                int cost = main.getPricesConfig().getInt("main.voteToken.price." + essentialNames.nameSpawn[i]);
                if (su == null) su = SilkUtil.hookIntoSilkSpanwers();
                if (main.playerDataConfig.getInt("main." + player.getName() + ".voteTokenCount") >= cost){
                    ItemStack mhspawner = createSpawner(essentialNames.entityNames[i],1);
                    player.getInventory().addItem(mhspawner);
                    main.playerDataConfig.set("main." + player.getName() + ".voteTokenCount",main.playerDataConfig.getInt("main." + player.getName() + ".voteTokenCount") - cost);
                    try {
                        main.playerDataConfig.save(main.playerDataFile);
                    } catch (Exception ex) {
                    }
                    player.closeInventory();
                    openUI.open(player);
                    break;
                }
                else{
                    int hia = cost - main.playerDataConfig.getInt("main." + player.getName() + ".voteTokenCount");
                    player.sendMessage(ChatColor.RED + "You need " + hia + " more VoteTokens");
                    player.closeInventory();
                    break;
                }

            }

        }
}
}
