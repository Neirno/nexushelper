package com.holo.nexushelper.events;

import java.util.Timer;
import java.util.TimerTask;

import com.holo.nexushelper.reference.Donate;
import com.holo.nexushelper.reference.ModuleCategory;
import com.holo.nexushelper.reference.Reference;
import com.holo.nexushelper.reference.Wrapper;
import com.holo.nexushelper.util.AuthUtils;
import com.holo.nexushelper.util.DonateUtils;
import com.holo.nexushelper.util.HWID;
import com.holo.nexushelper.api.Module;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ClickType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class DonateEvent extends Module
{
	
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
	
	private boolean donatePurchaseActivation = false;
	
	// TODO Create 'getdonateslot' for next pages of Donat
	@Override
	@SubscribeEvent
	public void onTicks(TickEvent.PlayerTickEvent event) 
	{
		if (event.player.ticksExisted % 7 == 0)
		{
	        if (donatePurchaseActivation && Wrapper.INSTANCE.mc().currentScreen instanceof GuiChest) 
	        {
	        	int donateSlot = DonateUtils.getDonateSlot(Wrapper.INSTANCE.player().openContainer);
	        	int confirmSlot = DonateUtils.confirmBuy(Wrapper.INSTANCE.player().openContainer);
	        	if (confirmSlot != -1)
	        	{
	        		donatePurchaseActivation = false;
	        		Wrapper.INSTANCE.controller().windowClick(Minecraft.getMinecraft().player.openContainer.windowId, confirmSlot, 0, ClickType.PICKUP, Minecraft.getMinecraft().player);
	        	}
	        	else if (donateSlot != -1) 
	        	{
	        		Wrapper.INSTANCE.controller().windowClick(Minecraft.getMinecraft().player.openContainer.windowId, donateSlot, 0, ClickType.PICKUP, Minecraft.getMinecraft().player);
	        		
	        	}
	        }
	    }
	}
	
	@Override
	@SubscribeEvent
    public void onNewMessage(ClientChatReceivedEvent event)
    {
    	String msg = event.getMessage().getUnformattedText().trim();
    	if (msg.startsWith("Игрок") && msg.endsWith("$") && Reference.identified) 
    	{
    		if (Donate.isDIVINE && msg.contains(Donate.DIVINE.getName()))
    		{
    			DonateUtils.moveToGrant();
    			donatePurchaseActivation = true;
    		}
    		else if (Donate.isMINIST && msg.contains(Donate.MINIST.getName()))
    		{
    			DonateUtils.moveToGrant();
    			donatePurchaseActivation = true;
    		}
    		else if (Donate.isTITAN && msg.contains(Donate.TITAN.getName()))
    		{
    			DonateUtils.moveToGrant();
    			donatePurchaseActivation = true;
    		}
    		else if (Donate.isLEGEND && msg.contains(Donate.LEGEND.getName()))
    		{
    			DonateUtils.moveToGrant();
    			donatePurchaseActivation = true;
    		}
    		else if (Donate.isHERO && msg.contains(Donate.HERO.getName()))
    		{
    			DonateUtils.moveToGrant();
    			donatePurchaseActivation = true;
    		}
    		else if (Donate.isMASTER && msg.contains(Donate.MASTER.getName()))
    		{
    			DonateUtils.moveToGrant();
    			donatePurchaseActivation = true;
    		}
    		else if (Donate.isPREMIUM && msg.contains(Donate.PREMIUM.getName()))
    		{
    			DonateUtils.moveToGrant();
    			donatePurchaseActivation = true;
    		}
    		else if (Donate.isVIP && msg.contains(Donate.VIP.getName()))
    		{
    			DonateUtils.moveToGrant();
    			donatePurchaseActivation = true;
    		}
    		
    	}
    	else if((msg.startsWith("Вы получили привилегию") ||  msg.startsWith("Вам записали на счет привилегию")) && Reference.identified)
    	{
    		if (msg.endsWith(Donate.DIVINE.getName()) && Donate.isDIVINE)
    		{
    			String result = AuthUtils.sendToServer("hwid=" + HWID.getHWID() + 
    					"&name=" + Wrapper.INSTANCE.player().getName() + "&command=clear");
    			Reference.identified = false;
    		}
    		else if(msg.endsWith(Donate.MINIST.getName()) && Donate.isMINIST)
    		{
    			String result = AuthUtils.sendToServer("hwid=" + HWID.getHWID() + 
    					"&name=" + Wrapper.INSTANCE.player().getName() + "&command=clear");
    			Reference.identified = false;
    		}
    		else if(msg.endsWith(Donate.TITAN.getName()) && Donate.isTITAN)
    		{
    			String result = AuthUtils.sendToServer("hwid=" + HWID.getHWID() + 
    					"&name=" + Wrapper.INSTANCE.player().getName() + "&command=clear");
    			Reference.identified = false;
    		}
    		else if(msg.endsWith(Donate.LEGEND.getName()) && Donate.isLEGEND)
    		{
    			String result = AuthUtils.sendToServer("hwid=" + HWID.getHWID() + 
    					"&name=" + Wrapper.INSTANCE.player().getName() + "&command=clear");
    			Reference.identified = false;
    		}
    		else if(msg.endsWith(Donate.HERO.getName()) && Donate.isHERO)
    		{
    			String result = AuthUtils.sendToServer("hwid=" + HWID.getHWID() + 
    					"&name=" + Wrapper.INSTANCE.player().getName() + "&command=clear");
    			Reference.identified = false;
    		}
    		else if(msg.endsWith(Donate.MASTER.getName()) && Donate.isMASTER)
    		{
    			String result = AuthUtils.sendToServer("hwid=" + HWID.getHWID() + 
    					"&name=" + Wrapper.INSTANCE.player().getName() + "&command=clear");
    			Reference.identified = false;
    		}
    		else if(msg.endsWith(Donate.PREMIUM.getName()) && Donate.isPREMIUM)
    		{
    			String result = AuthUtils.sendToServer("hwid=" + HWID.getHWID() + 
    					"&name=" + Wrapper.INSTANCE.player().getName() + "&command=clear");
    			Reference.identified = false;
    		}
    		else if(msg.endsWith(Donate.VIP.getName()) && Donate.isVIP)
    		{
    			String result = AuthUtils.sendToServer("hwid=" + HWID.getHWID() + 
    					"&name=" + Wrapper.INSTANCE.player().getName() + "&command=clear");
    			Reference.identified = false;
    		}
    	}
    }
}
