package de.canitzp.snaploader.mappings;

import net.fybertech.dynamicmappings.Mapping;

public class BlockMappings extends BaseMapping{

    public BlockMappings(){}

    @Mapping(
            depends = {blockClass, creativeTabsClass},
            providesMethods = {"net/minecraft/block/Block setCreativeTab (Lnet/minecraft/creativetab/CreativeTabs;)Lnet/minecraft/block/Block;"}
    )
    public boolean setCreativeTab(){
        return addMethod(blockClass, creativeTabsClass, blockClass, "setCreativeTab");
    }

    @Mapping(
            depends = {blockClass, iBlockStateClass, itemStackClass},
            providesMethods = {blockClass + " createStackedBlock (L" + iBlockStateClass + ";)L" + itemStackClass + ";"}
    )
    public boolean createStackedBlock(){
        return addMethod(blockClass, iBlockStateClass, itemStackClass, "createStackedBlock");
    }

}
