package de.canitzp.snaploader.mappings;

import net.fybertech.dynamicmappings.Mapping;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.List;

public class ItemMappings extends BaseMapping {

    @Mapping(
            depends = {itemStackClass, itemClass, entityLivingBaseClass},
            providesMethods = {itemClass + " hitEntity (L" + itemStackClass + ";L" + entityLivingBaseClass + ";L" + entityLivingBaseClass + ";)Z"}
    )
    public boolean hitEntity(){
        ClassNode cn1 = this.getClassNode(this.getClassMapping(itemClass));
        ClassNode cn2 = this.getClassNode(this.getClassMapping(itemStackClass));
        ClassNode cn3 = this.getClassNode(this.getClassMapping(entityLivingBaseClass));
        String method = "(L" + cn2.name + ";L" + cn3.name + ";L" + cn3.name + ";)Z";
        List methods = this.getMatchingMethods(cn1, null, method);
        if (methods.size() == 1) {
            this.addMethodMapping(itemClass + " " + "hitEntity" + " (L" + itemStackClass + ";L" + entityLivingBaseClass + ";L" + entityLivingBaseClass + ";)Z", cn1.name + " " + ((MethodNode) methods.get(0)).name + " " + ((MethodNode) methods.get(0)).desc);
            addingMethodSuccess("hitEntity");
        }
        return true;
    }
}
