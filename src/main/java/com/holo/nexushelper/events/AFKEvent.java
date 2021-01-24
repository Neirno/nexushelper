package com.holo.nexushelper.events;

import com.holo.nexushelper.reference.ModuleCategory;
import com.holo.nexushelper.reference.Reference;
import com.holo.nexushelper.reference.Wrapper;

import java.util.Timer;
import java.util.TimerTask;

import com.holo.nexushelper.api.Module;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AFKEvent<dirz> extends Module {
	public AFKEvent() {
		super(ModuleCategory.NONE);
		// TODO Auto-generated constructor stub
	}
	
    @Override
    public String getName() {
        return "afk";
    }

    @Override
    public String getDescription() {
        return "AntiAFK";
    }
    
    private boolean isAntiAFK = false;
    
    @Override
    @SubscribeEvent
    public void onTicks(TickEvent.PlayerTickEvent e) {
        {
            if (Reference.identified && Reference.antiAFK && !isAntiAFK) {
                isAntiAFK = true;
                Wrapper.INSTANCE.player().sendChatMessage("/near");
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isAntiAFK = false;
                    }
                }, 50000 + (int) (Math.random() * 100000.0));
            }
        }
        if (e.phase.equals(TickEvent.Phase.START))
        {
            int x = 0;
            int y = 60;
            int z = 0;

            EntityPlayerSP player = Wrapper.INSTANCE.player();

            double dirx = player.posX - 0;
            double diry = player.posY - 60;
            double dirz = player.posZ - 0;

            double len = Math.sqrt(dirx * dirx + diry * diry + dirz * dirz);

            dirx /= len;
            diry /= len;
            dirz /= len;

            double pitch = Math.asin(diry);
            double yaw = Math.atan2(dirz, dirx);

            //to degree
            pitch = pitch * 180.0 / Math.PI;
            yaw = yaw * 180.0 / Math.PI;

            yaw += 90f;

            if (yaw > player.rotationYaw) {
                player.rotationYaw++;
            } else if (yaw < player.rotationYaw) {
                player.rotationYaw--;
            }
        }
    }
}
