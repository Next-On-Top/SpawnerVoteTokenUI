package com.kyloka.voteTokenUI.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Created by Matthew on 9/15/2016.
 */
public class openUI {

    public static void open(Player target){
        Inventory inv = Bukkit.createInventory(null,45, ChatColor.AQUA + "VoteToken");

        ItemStack placeholder = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 8);
        ItemMeta placeholderMeta = placeholder.getItemMeta();
        placeholderMeta.setDisplayName(" ");
        placeholder.setItemMeta(placeholderMeta);
        List<ItemStack> itemsD;
        itemsD = itemsGUI.guiItems(target);
        for (int i = 0; i < 45; i++){
            if(itemsD.get(i) == null){
                itemsD.set(i,placeholder);

            }
            inv.setItem(i,itemsD.get(i));
        }

        itemsD = itemsGUI.guiItems(target);
        target.openInventory(inv);
    }
    public static void refresh(Player target){
        target.getOpenInventory().close();
        openUI.open(target);

    }

}
