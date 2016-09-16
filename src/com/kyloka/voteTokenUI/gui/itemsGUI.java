package com.kyloka.voteTokenUI.gui;

import com.kyloka.voteTokenUI.main;
import com.kyloka.voteTokenUI.references.essentialNames;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Matthew on 9/15/2016.
 */
public class itemsGUI {
    public static List <ItemStack> guiItems(Player player) {
        ItemStack yellowGlass1 = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 4);
        ItemStack yellowGlass2 = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 4);
        ItemStack limeGlass = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 5);
        ItemStack greenGlass = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 13);
        ItemStack lapis = new ItemStack(Material.LAPIS_BLOCK,1);
        ItemStack lapis16 = new ItemStack(Material.LAPIS_BLOCK,16);
        ItemStack redstone = new ItemStack(Material.REDSTONE_BLOCK,1);
        ItemStack redstone16 = new ItemStack(Material.REDSTONE_BLOCK,16);
        ArrayList<ItemStack> mobItems = new ArrayList<ItemStack>();
        ArrayList<ItemMeta> mobItemsMeta = new ArrayList<ItemMeta>();




        for (int i = 0; i < essentialNames.materialNames.length; i++){
            mobItems.add(i,new ItemStack(essentialNames.materialNames[i]));
            mobItemsMeta.add(i,mobItems.get(i).getItemMeta());
            String ayy = String.valueOf(main.getPricesConfig().getInt("main.voteToken.price." + essentialNames.nameSpawn[i]));
            ArrayList<String> priceLore = new ArrayList<>();
            priceLore.add(0,ChatColor.BLUE + "Price: "+ChatColor.GOLD + ayy + " Votetoken");
            mobItemsMeta.get(i).setDisplayName(ChatColor.RESET + "Buy " + ChatColor.YELLOW + essentialNames.nameSpawn[i] + ChatColor.RESET + " Spawner");
            mobItemsMeta.get(i).setLore(priceLore);
            mobItems.get(i).setItemMeta(mobItemsMeta.get(i));
        }

        //StainedClay Item meta Register
        ItemMeta yellowGlass1Meta = yellowGlass1.getItemMeta();
        ItemMeta yellowGlass2Meta = yellowGlass2.getItemMeta();
        ItemMeta limeGlassMeta = limeGlass.getItemMeta();
        ItemMeta greenGlassMeta = greenGlass.getItemMeta();
        ItemMeta lapisMeta = lapis.getItemMeta();
        ItemMeta lapis16Meta = lapis.getItemMeta();
        ItemMeta redstoneMeta = redstone.getItemMeta();
        ItemMeta redstone16Meta = redstone16.getItemMeta();


        //Setting the names
        ArrayList<String> balmeta = new ArrayList<>();
        balmeta.add(0,"" + ChatColor.YELLOW + main.getPlayerDataConfig().getInt("main."+player.getName()+".voteTokenCount"));
        balmeta.add(1,ChatColor.GREEN + "Click here to refresh your balance!");
        yellowGlass1Meta.setDisplayName("§e■■■");
        yellowGlass2Meta.setDisplayName("§e■■■");
        limeGlassMeta.setDisplayName(" §4" + player.getName());

        greenGlassMeta.setDisplayName(ChatColor.GOLD + "Current Balance:");
        greenGlassMeta.setLore(balmeta);
        lapisMeta.setDisplayName("§cWithdraw VoteToken");
        lapis16Meta.setDisplayName("§cWithdraw §e16 VoteToken");
        redstoneMeta.setDisplayName("§9Deposit VoteToken");
        redstone16Meta.setDisplayName("§9Deposit §e16 VoteToken!");
        //Registering the meta to the item
        yellowGlass1.setItemMeta(yellowGlass1Meta);
        yellowGlass2.setItemMeta(yellowGlass2Meta);
        limeGlass.setItemMeta(limeGlassMeta);
        greenGlass.setItemMeta(greenGlassMeta);
        lapis.setItemMeta(lapisMeta);
        lapis16.setItemMeta(lapis16Meta);
        redstone.setItemMeta(redstoneMeta);
        redstone16.setItemMeta(redstone16Meta);
        //Creating all this crap

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) skull.getItemMeta();

        meta.setOwner(player.getName());
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + player.getName());
        skull.setItemMeta(meta);


        ItemStack[] itemsF = new ItemStack[45];



        for(int i = 0; i < essentialNames.materialNames.length; i++){
            itemsF[essentialNames.itemSlot[i]] = mobItems.get(i);
        }

        itemsF[3] = yellowGlass1;
        itemsF[4] = skull;
        itemsF[5] = yellowGlass2;
        itemsF[12] = redstone;
        itemsF[13] = greenGlass;
        itemsF[14] = lapis;
        itemsF[21] = redstone16;
        itemsF[23] = lapis16;


        List <ItemStack> arr1 = Arrays.asList(itemsF);


        return arr1;
    }



}
