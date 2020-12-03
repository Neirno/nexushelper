package com.holo.nexushelper.reference;

public enum ModuleCategory 
{
    NONE("None"),
    FARM("Farm"),
    KEYBIND("Keybind");

    ModuleCategory(String name) 
    {
        this.name = name;
    }

    private final String name;

    public String getName() 
    {
        return this.name;
    }
}
