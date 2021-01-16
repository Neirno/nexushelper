package com.holo.nexushelper.util;

import net.minecraft.inventory.Container;

public class InventoryUtils {
	
	public static int startOfSlotsInInventor = 9;
	
	public static int endOfSlotInInventory = 44;
	
    public static boolean isContainerEmpty(Container container) 
    {
        int iSlotAmount = numberSlotsInventory(container);
        for (int i = 0; i < iSlotAmount; ++i) 
        {
            if (!container.getSlot(i).getHasStack()) 
            {
                continue;
            }
            return false;
        }
        return true;
    }
    
    // Number of slots in inventory = 46
    // Slots in the chest without crafting = 36
    public static int numberSlotsInventory(Container container)
    {
    	int slots = container.inventorySlots.size();
        switch (slots)
        {
        // Double chest
        case 90:
        	slots = 54;
        	break;
        // One chest
        case 63:
        	slots = 27;
        	break;
        // One line of chest
        case 45:
        	slots = 9;
        	break;
        default:
        	break;
        }
        return slots;
    }
    
    public static int getEmptySlotInInventory(Container container)
    {
    	int slotAmount = numberSlotsInventory(container);
        for (int i = slotAmount; i < container.inventorySlots.size(); ++i) 
        {
            if (!container.getSlot(i).getHasStack()) 
            {
                return i;
            }
        }
        return -1;
    }
}
