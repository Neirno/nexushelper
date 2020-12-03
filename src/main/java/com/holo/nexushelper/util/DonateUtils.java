package com.holo.nexushelper.util;

import com.holo.nexushelper.reference.Wrapper;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class DonateUtils extends InventoryUtils
{
	public static volatile DonateUtils INSTANCE = new DonateUtils();
	
    public int getDonateSlot(Container container) 
    {
        int slotAmount = this.numberSlotsInventory(container);
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
        	Wrapper.INSTANCE.player().sendMessage((ITextComponent)new TextComponentTranslation(Minecraft.getMinecraft().player.openContainer.getSlot(i).getStack().getUnlocalizedName() + " " + i));
            if (container.getInventory().get(i) == null)
            {
            	continue;
            }
            
            if (container.getInventory().get(i).getUnlocalizedName().equals("item.end_crystal"))
            {
            	return i;
            }	
            //else if ()
        }
        return -1;
    }
    
    public int confirmBuy(Container container)
    {
    	int slotAmount = this.numberSlotsInventory(container);
        for (int i = 0; i < slotAmount; ++i)
        {
            if (container.getInventory().get(i) == null)
            {
            	continue;
            }
            
            if (container.getInventory().get(i).getUnlocalizedName().equals("tile.thinStainedGlass.lime"))
            {
            	return i;
            }
        }
        return -1;
    }
    
    public void moveToGrant()
    {
    	Wrapper.INSTANCE.player().sendChatMessage("/warp ah");
		Wrapper.INSTANCE.player().sendChatMessage("/grantah");
    }
}
