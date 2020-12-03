package com.holo.nexushelper.events;

import java.util.Timer;
import java.util.TimerTask;

import com.holo.nexushelper.reference.ModuleCategory;
import com.holo.nexushelper.reference.Wrapper;
import com.holo.nexushelper.util.DonateUtils;
import com.holo.nexushelper.api.Module;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ClickType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class DonateEvent extends Module {
	
	public DonateEvent() {
        super(ModuleCategory.NONE);
    }

    @Override
    public String getName() {
        return "Donate buyer";
    }

    @Override
    public String getDescription() {
        return "Buys donate.";
    }
	
	private boolean isActiv = true;
	private boolean donatePurchaseActivation = false;
	
	@Override
	@SubscribeEvent
	public void onTicks(TickEvent.PlayerTickEvent event) 
	{
        if (!Wrapper.INSTANCE.mc().inGameHasFocus && Wrapper.INSTANCE.mc().currentScreen instanceof GuiChest) 
        {
        	if (donatePurchaseActivation)
    		{
            	int slotID = DonateUtils.INSTANCE.getDonateSlot(Wrapper.INSTANCE.player().openContainer);
            	int confirmSlot = DonateUtils.INSTANCE.confirmBuy(Wrapper.INSTANCE.player().openContainer);
            	if (confirmSlot != -1)
            	{
            		donatePurchaseActivation = false;
            		Wrapper.INSTANCE.controller().windowClick(Minecraft.getMinecraft().player.openContainer.windowId, confirmSlot, 0, ClickType.PICKUP, Minecraft.getMinecraft().player);

            	}
            	else if (slotID != -1) 
            	{
	            	if (isActiv) 
	            	{
	            		isActiv = false;
	            		Wrapper.INSTANCE.controller().windowClick(Minecraft.getMinecraft().player.openContainer.windowId, slotID, 0, ClickType.PICKUP, Minecraft.getMinecraft().player);
	            		new Timer().schedule(new TimerTask() 
	            		{
		                    @Override
		                    public void run() 
		                    {
		                    	isActiv = true;
		                    }
		                }, 700);
	            	}
            	}
    		}
        }
	}
	
	@SubscribeEvent
    public void messageEvent(ClientChatReceivedEvent event)
    {
    	String msg = event.getMessage().getUnformattedText().trim();
    
    	if (msg.startsWith("Игрок") && msg.endsWith("$")) 
    	{
    		if (msg.contains("MINIST"))
    		{
    			DonateUtils.INSTANCE.moveToGrant();
    			donatePurchaseActivation = true;
    		}
    		else if (msg.contains("PREMIUM"))
    		{
    			
    		}
    		
    	}
    }
}
