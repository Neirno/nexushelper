package com.holo.nexushelper.reference;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.GameSettings;

public class Wrapper 
{

    public static volatile Wrapper INSTANCE = new Wrapper();

    public Minecraft mc() 
    {
        return Minecraft.getMinecraft();
    }

    public EntityPlayerSP player() 
    {
        return Wrapper.INSTANCE.mc().player;
    }
    
    public PlayerControllerMP controller()
    {
    	return Wrapper.INSTANCE.mc().playerController;
    }
    
    public WorldClient world() 
    {
        return Wrapper.INSTANCE.mc().world;
    }

    public GameSettings mcSettings() 
    {
        return Wrapper.INSTANCE.mc().gameSettings;
    }

    public FontRenderer fontRenderer()
    {
        return Wrapper.INSTANCE.mc().fontRenderer;
    }

    public void copy(String str) 
    {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(str), null);
    }
}
