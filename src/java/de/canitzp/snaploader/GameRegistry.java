package de.canitzp.snaploader;

import com.google.common.collect.UnmodifiableIterator;
import net.fybertech.dynamicmappings.DynamicMappings;
import net.fybertech.meddleapi.MeddleAPI;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.Method;
import java.util.Map;

public class GameRegistry {

    private static int itemID = 6000;
    private static int blockID = 230;
    private static SaveIDs save = new SaveIDs();
    public static Map<String, Integer> idMap = save.read();
    public static int loadedBlocks = 0, loadedItems = 0;

    public static void registerItem(Item item, String name) {
        if (idMap.containsKey("item." + name)) {
            Snaploader.LOGGER.info("Register Item: " + name + " with ID: " + idMap.get("item." + name));
            MeddleAPI.registerItem(idMap.get("item." + name), name, item);
        } else {
            boolean b = false;
            while (!b) {
                if (!idMap.containsValue(itemID)) {
                    Snaploader.LOGGER.info("Register new Item: " + name + " with ID: " + itemID);
                    idMap.put("item." + name, itemID);
                    MeddleAPI.registerItem(itemID, name, item);
                    b = true;
                } else itemID++;
            }
        }
        save.save();
    }

    public static void registerBlock(Block block, String name) {
        if (idMap.containsKey("block." + name)) {
            Snaploader.LOGGER.info("Register Block: " + name + " with ID: " + idMap.get("block." + name));
            //MeddleAPI.registerBlock(idMap.get("block." + name), name, block);
            registerBlock(idMap.get("block." + name), name, block);
        } else {
            boolean b = false;
            while (!b) {
                if (!idMap.containsValue(blockID)) {
                    Snaploader.LOGGER.info("Register new Block: " + name + " with ID: " + blockID);
                    idMap.put("block." + name, blockID);
                    //MeddleAPI.registerBlock(blockID, name, block);
                    registerBlock(blockID, name, block);
                    b = true;
                } else blockID++;
            }
        }
        save.save();
    }

    public static void registerBlock(Block block, ItemBlock itemBlock, String name) {
        registerBlock(block, name);
        registerItemBlock(block, itemBlock);
        loadedBlocks++;
    }

    public static void addRecipe(ItemStack itemStack, Object... objects) {
        //CraftingManager.getInstance().addRecipe(itemStack, objects);
    }

    public static void addRecipe(Item item, Object... objects) {
        addRecipe(new ItemStack(item), objects);
    }

    public static void addRecipe(Block block, Object... objects) {
        addRecipe(new ItemStack(block), objects);
    }

    @Deprecated
    public static void addSmelting(ItemStack input, ItemStack output, float exp) {
        //FurnaceRecipes.instance().addSmeltingRecipe(input, output, exp);
    }

    @Deprecated
    public static void addSmelting(ItemStack input, ItemStack output) {
        addSmelting(input, output, 0);
    }

    private static void registerItem(int itemID, ResourceLocation resourceLocation, Item item) {
        Method registerItemMethod = null;
        if (registerItemMethod == null) {
            String e = DynamicMappings.getClassMapping("net.minecraft.item.Item");
            Class itemClass = null;

            try {
                itemClass = Class.forName(e);
            } catch (ClassNotFoundException var12) {
                var12.printStackTrace();
            }

            Method[] methods = itemClass.getDeclaredMethods();
            Method[] var6 = methods;
            int var7 = methods.length;

            for (int var8 = 0; var8 < var7; ++var8) {
                Method method = var6[var8];
                Class[] types = method.getParameterTypes();
                if (types.length == 3 && types[0] == Integer.TYPE && types[1] == ResourceLocation.class && types[2] == itemClass) {
                    method.setAccessible(true);
                    registerItemMethod = method;
                }
            }
        }

        try {
            registerItemMethod.invoke((Object) null, new Object[]{Integer.valueOf(itemID), resourceLocation, item});
        } catch (Exception var11) {
            var11.printStackTrace();
        }

    }

    private static void registerBlock(int blockID, String name, Block block) {
        Method registerBlockMethod = null;
        if (registerBlockMethod == null) {
            String e = DynamicMappings.getClassMapping("net.minecraft.block.Block");
            Class blockClass = null;

            try {
                blockClass = Class.forName(e);
            } catch (ClassNotFoundException var12) {
                var12.printStackTrace();
            }

            Method[] methods = blockClass.getDeclaredMethods();
            Method[] var6 = methods;
            int var7 = methods.length;

            for (int var8 = 0; var8 < var7; ++var8) {
                Method method = var6[var8];
                Class[] types = method.getParameterTypes();
                if (types.length == 3 && types[0] == Integer.TYPE && types[1] == String.class && types[2] == blockClass) {
                    method.setAccessible(true);
                    registerBlockMethod = method;
                }
            }
        }

        try {
            registerBlockMethod.invoke((Object) null, new Object[]{Integer.valueOf(blockID), name, block});
            UnmodifiableIterator it = block.getBlockState().getValidStates().iterator();
            while (it.hasNext()) {
                IBlockState ibs = (IBlockState) it.next();
                int var23 = Block.blockRegistry.getIDForObject(block) << 4 | block.getMetaFromState(ibs);
                Block.BLOCK_STATE_IDS.put(ibs, var23);
            }
        } catch (Exception var11) {
            var11.printStackTrace();
        }

    }

    private static void registerItemBlock(Block block, Item item) {
        Method registerItemBlockMethod = null;
        if (registerItemBlockMethod == null) {
            String e = DynamicMappings.getClassMapping("net.minecraft.item.Item");
            Class itemClass = null;

            try {
                itemClass = Class.forName(e);
            } catch (ClassNotFoundException var12) {
                var12.printStackTrace();
            }

            Method[] methods = itemClass.getDeclaredMethods();
            Method[] var6 = methods;
            int var7 = methods.length;

            for (int var8 = 0; var8 < var7; ++var8) {
                Method method = var6[var8];
                Class[] types = method.getParameterTypes();
                if (types.length == 2 && types[0] == Block.class && types[1] == Item.class) {
                    method.setAccessible(true);
                    registerItemBlockMethod = method;
                }
            }
        }

        try {
            registerItemBlockMethod.invoke((Object) null, new Object[]{block, item});
        } catch (Exception var11) {
            var11.printStackTrace();
        }
    }
}
