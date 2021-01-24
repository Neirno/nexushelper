/*package com.holo.nexushelper.events;

import com.holo.nexushelper.reference.Reference;
import com.holo.nexushelper.reference.Wrapper;
import com.holo.nexushelper.util.Connect;

import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatEvent {
	//ClientChatEvent
	@SubscribeEvent
	public void onTicks(ClientChatEvent event) 
	{
        if (Reference.identified)
        {
        	String message = event.getMessage();
        	if (message.startsWith("/nh"))
        	{
        		Connect.sendMessage("Anon: " + message);
        	}
        }
	}
}
*/