package com.holo.nexushelper.util;

import com.holo.nexushelper.reference.Donate;
import com.holo.nexushelper.reference.Wrapper;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class DonateUtils extends InventoryUtils
{
    public static int getDonateSlot(Container container) 
    {
        int slotAmount = numberSlotsInventory(container);
        for (int i = 0; i < slotAmount; ++i) 
        {
        	// item.swordWood - VIP
        	// item.swordStone - PREMIUM
        	// item.swordIron - MASTER
        	// item.swordGold - HERO
        	// item.swordDiamond - LEGEND
        	// item.totem - TITAN
        	// item.end_crystal - MINIST
        	// item.appleGold - DIVINE
            if (container.getInventory().get(i) == null)
            {
            	continue;
            }
            if (Donate.isDIVINE && container.getInventory().get(i).getUnlocalizedName().equals("item.appleGold"))
            {
            	return i;
            }
            else if (Donate.isMINIST && container.getInventory().get(i).getUnlocalizedName().equals("item.end_crystal"))
            {
            	return i;
            }
            else if (Donate.isTITAN && container.getInventory().get(i).getUnlocalizedName().equals("item.totem"))
            {
            	return i;
            }
            else if (Donate.isLEGEND && container.getInventory().get(i).getUnlocalizedName().equals("item.swordDiamond"))
            {
            	return i;
            }
            else if (Donate.isHERO && container.getInventory().get(i).getUnlocalizedName().equals("item.swordGold"))
            {
            	return i;
            }
            else if (Donate.isMASTER && container.getInventory().get(i).getUnlocalizedName().equals("item.swordIron"))
            {
            	return i;
            }
            else if (Donate.isPREMIUM && container.getInventory().get(i).getUnlocalizedName().equals("item.swordStone"))
            {
            	return i;
            }
            else if (Donate.isVIP && container.getInventory().get(i).getUnlocalizedName().equals("item.swordWood"))
            {
            	return i;
            }
            else if (container.getInventory().get(i).getDisplayName().equals("§fВперед"))
            {
            	return i;
            }
        }
        return -1;
    }
    
    public static int confirmBuy(Container container)
    {
    	int slotAmount = numberSlotsInventory(container);
        for (int i = 0; i < slotAmount; ++i)
        {
            if (container.getInventory().get(i) == null)
            {
            	continue;
            }
            //System.out.println(container.getInventory().get(i).getDisplayName());
            if (container.getInventory().get(i).getDisplayName().trim().startsWith("§aКупить"))
            {
            	return i;
            }
        }
        return -1;
    }
    
    public static void moveToGrant()
    {
    	Wrapper.INSTANCE.player().sendChatMessage("/warp ah");
		Wrapper.INSTANCE.player().sendChatMessage("/grantah");
    }
    
}
