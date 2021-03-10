package com.holo.nexushelper.util;

import javax.net.ssl.HttpsURLConnection;

import com.holo.nexushelper.reference.Donate;
import com.holo.nexushelper.reference.Reference;
import com.holo.nexushelper.reference.Wrapper;
import com.ibm.icu.text.ChineseDateFormat.Field;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiBossOverlay;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.BossInfo;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class AuthUtils {
	
	public static String sendToServer(String data)
	{
		try {
	        String url = "http://url_to_website/minecraft/index?hash=bf1822f170dc384c42f12a98535610ca3c5ff17af9c1aeff343224cec863c382&" + data;
	
			URL obj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
	
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
	
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
			    response.append(inputLine);
			}
			in.close();
	
			String body = response.toString();
			/*int start = 6 + body.indexOf("<body>");
			int end = body.indexOf("</body>");
			
			body = body.substring(start, end);*/
			return body;
		}
		catch(Exception e)
		{
			return "errore";
		}
	}
	
	public static void initPrices()
	{
		String result = AuthUtils.sendToServer("command=prices");
		result = result.substring(result.indexOf("DIVINE"), result.length());
		result = result.substring(0, result.length() - 1);
		if (result.startsWith("DIVINE"))
		{
			String[] array = result.split(" ");
			for (String str : array)
			{
				String donate = str.substring(0, str.indexOf("="));
				String price = str.substring(str.indexOf("=") + 1, str.length());
				if (Donate.DIVINE.getName().equals(donate))
					Donate.priceDivine = Integer.parseInt(price);
				else if (Donate.MINIST.getName().equals(donate))
					Donate.priceMinist = Integer.parseInt(price);
				else if (Donate.TITAN.getName().equals(donate))
					Donate.priceTitan = Integer.parseInt(price);
				else if (Donate.LEGEND.getName().equals(donate))
					Donate.priceLegend = Integer.parseInt(price);
				else if (Donate.HERO.getName().equals(donate))
					Donate.priceHero = Integer.parseInt(price);
				else if (Donate.MASTER.getName().equals(donate))
					Donate.priceMaster = Integer.parseInt(price);
				else if (Donate.PREMIUM.getName().equals(donate))
					Donate.pricePremium = Integer.parseInt(price);
				else if (Donate.VIP.getName().equals(donate))
					Donate.priceVip = Integer.parseInt(price);
			}
		}
	}
	
	public static void startNH()
	{
		String result = AuthUtils.sendToServer("hwid=" + HWID.getHWID() + 
				"&name=" + Wrapper.INSTANCE.player().getName() + "&command=start");
		if (result.startsWith("ok") && !Reference.identified)
		{
			Wrapper.INSTANCE.sendMessageToPlayer("§4§lОжидай...");
			result = result.substring("ok".length());
			AuthUtils.boughtDonat(result);
			// Ya hui znaet kak po drugomu sdelat', TODO: fix crutch!
			new Timer().schedule(new TimerTask() 
			{
                @Override
                public void run() 
                {
					if (Reference.isFullBalance && Reference.isNexusGrief && !Reference.isInPVP)
					{
						Reference.identified = true;
						Wrapper.INSTANCE.sendMessageToPlayer("§2§lТы зашел! ;]");
					}
					else 
					{
						Wrapper.INSTANCE.sendMessageToPlayer("§2§lЧто-то пошло не так! Видимо не соблюден один или несколько из пунктов:");
						Wrapper.INSTANCE.sendMessageToPlayer("§2 1. Ты не на сервере NexusGrief;");
						Wrapper.INSTANCE.sendMessageToPlayer("§2 2. У тебя не хвататет денег на купленный тобой донат;");
						Wrapper.INSTANCE.sendMessageToPlayer("§2 3. Ты находишься в PVP режиме.");
					}
                }
            }, 5000);
		}
		else if (result.startsWith("no nick but hwid"))
		{
			result.substring("no nick but hwid".length());
			Wrapper.INSTANCE.sendMessageToPlayer("§4§lты зарегистрированы, но зашел с другого аккаунта.");
			Wrapper.INSTANCE.sendMessageToPlayer("§4§lДоступные аккаунты для твоего ПК: §r§a" + result);
		}
		else if (result.startsWith("no nick no hwid"))
		{
			Wrapper.INSTANCE.sendMessageToPlayer("§4§lТы не зарегистрирован и у тебя ничего не куплено!");
			Wrapper.INSTANCE.sendMessageToPlayer("§4§lЧтобы купить что-то перейди: vk.com/chatmodng");
		}
		else 
		{
			Wrapper.INSTANCE.sendMessageToPlayer("§4§lВидимо что-то пошло не так. Повтори запрос позже. Возможно ты уже подключен.");
		}
	}
	
	public static void boughtDonat(String donate)
	{
		if(donate.equals(Donate.DIVINE.getName()))
			Donate.isDIVINE = true;
		else if(donate.equals(Donate.MINIST.getName()))
			Donate.isMINIST = true;
		else if(donate.equals(Donate.TITAN.getName()))
			Donate.isTITAN = true;
		else if(donate.equals(Donate.LEGEND.getName()))
			Donate.isLEGEND = true;
		else if(donate.equals(Donate.HERO.getName()))
			Donate.isHERO = true;
		else if(donate.equals(Donate.MASTER.getName()))
			Donate.isMASTER = true;
		else if(donate.equals(Donate.PREMIUM.getName()))
			Donate.isPREMIUM = true;
		else if(donate.equals(Donate.VIP.getName()))
			Donate.isVIP = true;
	}
	
	public static boolean isFullBalanceForDonate(int balance)
	{
		if (Donate.isDIVINE && balance >= Donate.priceDivine)
			return true;
		else if (Donate.isMINIST && balance >= Donate.priceMinist)
			return true;
		else if (Donate.isTITAN && balance >= Donate.priceTitan)
			return true;
		else if (Donate.isLEGEND && balance >= Donate.priceLegend)
			return true;
		else if (Donate.isHERO && balance >= Donate.priceHero)
			return true;
		else if (Donate.isMASTER && balance >= Donate.priceMaster)
			return true;
		else if (Donate.isPREMIUM && balance >= Donate.pricePremium)
			return true;
		else if (Donate.isVIP && balance >= Donate.priceVip)
			return true;
		else
			return false;
	}
}
