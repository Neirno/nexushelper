package com.holo.nexushelper.events;

import com.holo.nexushelper.reference.ModuleCategory;
import com.holo.nexushelper.reference.Reference;
import com.holo.nexushelper.reference.Wrapper;
import com.holo.nexushelper.util.AuthUtils;
import com.holo.nexushelper.util.HWID;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import com.google.common.collect.Maps;
import com.holo.nexushelper.api.Module;

import net.minecraft.client.gui.GuiBossOverlay;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.BossInfo;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;

public class AuthEvent extends Module{
	
	public AuthEvent() {
		super(ModuleCategory.NONE);
		// TODO Auto-generated constructor stub
	}
	
    @Override
    public String getName() {
        return "Donate buyer";
    }

    @Override
    public String getDescription() {
        return "Buys donate.";
    }

    @Override
	@SubscribeEvent
	public void onPlayerSendChatMessage(ClientChatEvent event) 
	{
		if (event.getMessage().startsWith("/nh"))
		{
			String message = event.getMessage();
			message = message.substring(3).trim();
			switch (message)
			{
			case ("help"):
				Wrapper.INSTANCE.sendMessageToPlayer("Запуск автопокупки §o/nh start");
				Wrapper.INSTANCE.sendMessageToPlayer("Включить анти афк §o/nh antiafk");
				Wrapper.INSTANCE.sendMessageToPlayer("Отключить автопоккупку §o/nh stop");
				Wrapper.INSTANCE.sendMessageToPlayer("Получить свой HWID §o/nh hwid");
				Wrapper.INSTANCE.sendMessageToPlayer("§eЕсли ты будешь находиться в PVP режиме или у тебя не будет хватать денег - автопокупка отключиться");
				Wrapper.INSTANCE.sendMessageToPlayer("§6§lПриятной игры! ;]");
				break;
			case ("start"):
				AuthUtils.startNH();
			case ("acc"):
				// TODO : chto-to sdelat'
				break;
			case ("antiafk"):
				if (Reference.identified)
				{
					if (!Reference.antiAFK) {
						Wrapper.INSTANCE.sendMessageToPlayer("§2§lАнти АФК запущен.");
						Reference.antiAFK = true;
					}
					else
					{
						Wrapper.INSTANCE.sendMessageToPlayer("§2§lАнти АФК выключен.");
						Reference.antiAFK = false;
					}
				}
				break;
			case ("stop"):
				if (Reference.identified)
				{
					Reference.identified = false;
					Wrapper.INSTANCE.sendMessageToPlayer("§2§lТы вышел из системы!");
				}
				else
				{
					Wrapper.INSTANCE.sendMessageToPlayer("§4§lТы и не заходил.");
				}
				break;
			case ("hwid"):
				Wrapper.INSTANCE.sendMessageToPlayer("§2§lHWID скопирован.");
				StringSelection stringSelection = new StringSelection(HWID.getHWID());
	        	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	        	clipboard.setContents(stringSelection, null);
				break;
			default:
				Wrapper.INSTANCE.sendMessageToPlayer("§4§lТы не ввел ни одной комманды!");
				Wrapper.INSTANCE.sendMessageToPlayer("§4§lЧтобы узнать список всех комманд напишите §o/nh help");
				break;
			}
			event.setCanceled(true);
		}//https://forums.minecraftforge.net/topic/64730-solved-1122-how-to-detect-when-player-joins-singleplayer-vs-multiplayer/
	}
	
    
	@SuppressWarnings("unchecked")
	@Override
	@SubscribeEvent
	public void onTicks(TickEvent.PlayerTickEvent event)
	{
		if (event.player.ticksExisted % 50 == 0)
		{
			try 
			{
				boolean isPVPText = false;
				GuiBossOverlay bossOverlay = Wrapper.INSTANCE.mc().ingameGUI.getBossOverlay();
				List<String> names = new ArrayList<String>();
				Map<UUID, BossInfo> mapBossInfos = (Map<UUID, BossInfo>) ReflectionHelper.getPrivateValue(GuiBossOverlay.class, bossOverlay, "mapBossInfos", "field_184060_g");
				for (BossInfo bI : mapBossInfos.values())
					names.add(bI.getName().getFormattedText());
				if (names.isEmpty())
				{
					Reference.isInPVP = false;
				}
				else 
				{
					// TODO: Make it readable
					for (String str : names)
					{
						if (str.trim().startsWith("§fPvP-режим будет отключен через"))
						{
							isPVPText = true;
						}
					}
					if(isPVPText && Reference.identified)
					{
						Reference.isInPVP = true;
						Reference.identified = false;
						Wrapper.INSTANCE.sendMessageToPlayer("§e§lТы находишься в PVP режиме и по этому автопокупка доната отключена.");
						Wrapper.INSTANCE.sendMessageToPlayer("§e§lЧтобы снова войти напиши §o/nh start");
					}
					else 
					{
						Reference.isInPVP = false;
					}
				}
			}
			catch (Exception e)
			{
				//e = null;
                //e.printStackTrace();
			}
		
		}
	}
	
	@SuppressWarnings("unchecked")
	@SubscribeEvent
	public void onTicks1(TickEvent.PlayerTickEvent event)
	{
		if (event.player.ticksExisted % 50 == 0)
		{
			try 
			{
				GuiPlayerTabOverlay playerOverlay = Wrapper.INSTANCE.mc().ingameGUI.getTabList();
				//ITextComponent itc = (ITextComponent) ReflectionHelper.getPrivateValue(GuiPlayerTabOverlay.class, playerOverlay, "footer", "field_175255_h");
				ITextComponent header = (ITextComponent) ReflectionHelper.getPrivateValue(GuiPlayerTabOverlay.class, playerOverlay, "header", "field_175256_i");
				String data;
				//AuthEvent.Wrapper.INSTANCE.sendMessageToPlayer(header.getFormattedText());
				if (header.getUnformattedText().contains("баланс")  && Reference.isNexusGrief)
				{
					data = header.getUnformattedText();
					data = data.substring(data.indexOf("Игровой баланс: §e") + "Игровой баланс: §e".length(), data.indexOf("$"));
					String newString = "";
					for (String str : data.split(","))
					{
						newString = newString + str;
					}
					if (newString != "" && AuthUtils.isFullBalanceForDonate(Integer.parseInt(newString)))
						Reference.isFullBalance = true;
					else
					{
						Reference.isFullBalance = false;
						Reference.identified = false;
					}
				}
				else
				{
					Reference.isFullBalance = false;
					Reference.identified = false;
				}
			}
			catch (Exception e)
			{
				//e = null;
				//e.printStackTrace();
			}
		}
	}
    
    @SubscribeEvent
    public void onDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event)
    {
    	Reference.isNexusGrief = false;
    	Reference.identified = false;
    }
    
    @SubscribeEvent
    public void onConnect(FMLNetworkEvent.ClientConnectedToServerEvent event)
    {
    	if (Wrapper.INSTANCE.mc().getCurrentServerData().serverIP.equals("ngrief.su"))
    	{
    		Reference.isNexusGrief = true;
    		AuthUtils.initPrices();
    	}
    	//Wrapper.INSTANCE.sendMessageToPlayer("§l§eТы вошел на сервер. Чтобы подключиться к системе напиши §o/nh start");
    }
}
