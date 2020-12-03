package com.holo.nexushelper.api;

import com.holo.nexushelper.reference.ModuleCategory;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public abstract class Module {

    protected String name = "unknown";
    protected String p = "ehacks:";
    protected String description = "unknown";
    protected int keybind = 0;
    protected boolean enabled;
    protected ModuleCategory category;

    public Module(ModuleCategory category) {
        this.category = category;
    }

    public void setKeybinding(int key) {
        this.keybind = key;
    }

    public void setCategory(ModuleCategory category) {
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public String getAlias() {
        return this.p + this.getName().toLowerCase().replaceAll(" ", "");
    }

    public int getKeybind() {
        return this.keybind;
    }

    public String getDescription() {
        return this.description;
    }

    public ModuleCategory getCategory() {
        return this.category;
    }

    public boolean isActive() {
        return this.enabled;
    }

    public void onWorldUpdate() {
    }

    public void onTicks(TickEvent.PlayerTickEvent event) {
    }

    public void onWorldRender(RenderWorldLastEvent event) {
    }

    public void onModuleEnabled() {
    }

    public void onModuleDisabled() {
    }

    public void onMouse(MouseEvent event) {
    }

    public void onClick(PlayerInteractEvent event) {
    }

    public void onKeyBind() {
    }

    public void reset() {
        this.onModuleEnabled();
        this.onModuleDisabled();
    }

    public void on() {
        this.enabled = true;
        this.onModuleEnabled();
    }

    public void off() {
        this.enabled = false;
        this.onModuleDisabled();
    }

    public void toggle() {
        this.enabled = !this.enabled;
        if (this.isActive()) {
            this.onModuleEnabled();
        } else {
            this.onModuleDisabled();
        }
    }

    public String getModName() {
        return "Minecraft";
    }

    public void onLiving(LivingEvent.LivingUpdateEvent event) {
    }


    public void onGameOverlay(RenderGameOverlayEvent.Text event) {
    }

    public int getDefaultKeybind() {
        return 0;
    }
}