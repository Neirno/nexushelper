package com.holo.nexushelper.proxy;

import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import net.minecraft.client.settings.KeyBinding;

public class ClientProxy extends CommonProxy
{
    public static KeyBinding keyBindingsNC;
    public static KeyBinding keyBindingsGui;
    
    @Override
    public void preInit(final FMLPreInitializationEvent event) {
        super.preInit(event);
    }
    
    @Override
    public void init(final FMLInitializationEvent event) { 
        super.init(event);
    }
    
    @Override
    public void postInit(final FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
