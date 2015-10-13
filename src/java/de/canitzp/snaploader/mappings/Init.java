package de.canitzp.snaploader.mappings;

import net.fybertech.dynamicmappings.DynamicMappings;

public class Init {

    public static void init(){
        DynamicMappings.registerMappingsClass(BlockMappings.class);
        DynamicMappings.registerMappingsClass(ItemMappings.class);
        DynamicMappings.registerMappingsClass(ItemStackMappings.class);
    }

}
