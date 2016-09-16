package com.kyloka.voteTokenUI.references;

import com.kyloka.voteTokenUI.main;
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
        for (int i =0; i < essentialNames.materialNames.length;i++){
            mobItems.add(i,new ItemStack(essentialNames.materialNames[i]));
            mobItemsMeta.add(i,mobItems.get(i).getItemMeta());

            mobItemsMeta.get(i).setDisplayName(ChatColor.RESET + "Buy " + ChatColor.YELLOW + essentialNames.nameSpawn[i] + ChatColor.RESET + " Spawner");

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
        yellowGlass1Meta.setDisplayName("§e96 Bedrock for Spawner");
        limeGlassMeta.setDisplayName(" §4" + player.getName());
        greenGlassMeta.setDisplayName("§9" + main.playerDataConfig.getInt("main." + player.getName() + ".voteTokenCount"));
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
        /*arr1.add(0, mobItems.get(0));
        arr1.add(1,mobItems.get(1));
        arr1.add(2,mobItems.get(2));
        arr1.add(9,mobItems.get(3));
        arr1.add(10,mobItems.get(4));
        arr1.add(11,mobItems.get(5));
        arr1.add(27,mobItems.get(6));
        arr1.add(28,mobItems.get(7));
        arr1.add(29,mobItems.get(8));
        arr1.add(36,mobItems.get(9));
        */
        for(int i = 0; i < essentialNames.materialNames.length; i++){
            itemsF[essentialNames.itemSlot[i]] = mobItems.get(i);

        }



        List <ItemStack> arr1 = Arrays.asList(itemsF);


        return arr1;
    }



}
