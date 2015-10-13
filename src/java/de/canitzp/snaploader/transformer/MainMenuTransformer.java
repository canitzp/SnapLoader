package de.canitzp.snaploader.transformer;


import org.objectweb.asm.ClassReader;
import net.fybertech.dynamicmappings.DynamicMappings;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;

public class MainMenuTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String s, String s1, byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassNode cn = new ClassNode();
        reader.accept(cn, 0);
        String guiScreen = DynamicMappings.getClassMapping("net/minecraft/client/gui/GuiScreen");
        String drawScreenMapping = DynamicMappings.getMethodMapping("net/minecraft/client/gui/GuiScreen drawScreen (IIF)V");
        MethodNode drawScreen = DynamicMappings.getMethodNode(cn, drawScreenMapping);
        if(drawScreen == null) {
            return basicClass;
        } else {
            boolean matchedFirst = false;

            for(AbstractInsnNode writer = drawScreen.instructions.getLast(); writer != null; writer = writer.getPrevious()) {
                if(!matchedFirst && writer instanceof MethodInsnNode) {
                    MethodInsnNode list = (MethodInsnNode)writer;
                    if(drawScreenMapping.equals(list.owner + " " + list.name + " " + list.desc)) {
                        matchedFirst = true;
                        continue;
                    }
                }

                if(matchedFirst && writer.getOpcode() == 25) {
                    InsnList list1 = new InsnList();
                    list1.add(new VarInsnNode(25, 0));
                    list1.add(new MethodInsnNode(184, "de/canitzp/snaploader/proxy/Client", "drawMainMenuBranding", "(L" + guiScreen + ";)V", false));
                    drawScreen.instructions.insertBefore(writer, list1);
                    break;
                }
            }

            ClassWriter writer1 = new ClassWriter(0);
            cn.accept(writer1);
            String guiMainMenu = DynamicMappings.getClassMapping("net/minecraft/client/gui/GuiMainMenu");
            return s.equals(guiMainMenu)?writer1.toByteArray():basicClass;
        }
    }
}
