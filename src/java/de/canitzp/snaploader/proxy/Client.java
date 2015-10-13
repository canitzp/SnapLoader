package de.canitzp.snaploader.proxy;

import de.canitzp.snaploader.GameRegistry;
import de.canitzp.snaploader.Snaploader;
import de.canitzp.snaploader.mappings.BaseMapping;
import net.fybertech.dynamicmappings.DynamicMappings;
import net.fybertech.meddle.Meddle;
import net.fybertech.meddleapi.MeddleAPI;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

import java.lang.reflect.Field;

public class Client extends Common {

    static FontRenderer fontRenderer = null;

    public Client(){

    }

    public static void drawMainMenuBranding(GuiScreen gui) {
        if(fontRenderer == null) {
            String modCount = DynamicMappings.getFieldMapping("net/minecraft/client/gui/GuiScreen fontRendererObj Lnet/minecraft/client/gui/FontRenderer;");
            if(modCount == null) {
                return;
            }

            Field modOrMods = null;

            try {
                modOrMods = GuiScreen.class.getDeclaredField(modCount.split(" ")[1]);
                modOrMods.setAccessible(true);
                fontRenderer = (FontRenderer)modOrMods.get(gui);
            } catch (Exception var4) {
                ;
            }

            if(fontRenderer == null) {
                return;
            }
        }
        String oneOrTwo;

        oneOrTwo = GameRegistry.loadedBlocks == 1?" Block":" Blocks";
        gui.drawString(fontRenderer, "  " + GameRegistry.loadedBlocks + oneOrTwo + " loaded", 2, gui.height - 90, 11184810);
        oneOrTwo = GameRegistry.loadedItems == 1?" Item":" Items";
        gui.drawString(fontRenderer, "  " + GameRegistry.loadedItems + oneOrTwo + " loaded", 2, gui.height - 80, 11184810);
        oneOrTwo = BaseMapping.loadedMappings == 1?" Mapping":" Mappings";
        gui.drawString(fontRenderer, "  " + BaseMapping.loadedMappings + oneOrTwo + " loaded", 2, gui.height - 70, 11184810);
        gui.drawString(fontRenderer, "SnapLoader " + Snaploader.VERSION, 2, gui.height - 100, 16777215);
    }

}
