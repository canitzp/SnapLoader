package de.canitzp.snaploader.generic;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;


public class GenericSword extends Item {

    private final float attackDamage, toolStageFloat;
    private final int toolStage, durability, enchantibility;
    private final String name;
    private final Item repairItem;

    public GenericSword(String name, int toolStage, int durability, float attackDamage, float toolStageFloat, int enchantibility, Item repairItem) {
        this.attackDamage = attackDamage;
        this.toolStageFloat = toolStageFloat;
        this.toolStage = toolStage;
        this.durability = durability;
        this.enchantibility = enchantibility;
        this.name = name;
        this.repairItem = repairItem;
        this.maxStackSize = 1;
        this.setMaxDamage(durability);
        this.setCreativeTab(CreativeTabs.tabCombat);
    }

    public float g() {
        return this.toolStageFloat;
    }

    public float a(ItemStack param0, IBlockState param1) {
        Block var0 = param1.getBlock();
        if(var0 == Blocks.G) {
            return 15.0F;
        } else {
            Material var1 = param1.a();
            return var1 != Material.k && var1 != Material.l && var1 != Material.v && var1 != Material.j && var1 != Material.C?1.0F:1.5F;
        }
    }

    public boolean a(ItemStack param0, EntityLivingBase param1, EntityLivingBase param2) {
        param0.damageItem(1, param2);
        param1.setPositionAndUpdate(1, 70, 1);
        return true;
    }

    public boolean a(ItemStack param0, World param1, IBlockState param2, BlockPos param3, EntityLivingBase param4) {
        if((double)param2.b(param1, param3) != 0.0D) {
            param0.damageItem(2, param4);
        }

        return true;
    }

    public boolean x_() {
        return true;
    }

    public boolean b(Block param0) {
        return param0 == Blocks.G;
    }

    public int getItemEnchantability() {
        return this.enchantibility;
    }

    public String h() {
        return this.name;
    }

    public boolean a(ItemStack param0, ItemStack param1) {
        return this.repairItem == param1.getItem() || super.a(param0, param1);
    }

    public String getItemStackDisplayName(ItemStack param0) {
        return this.name;
    }

}
