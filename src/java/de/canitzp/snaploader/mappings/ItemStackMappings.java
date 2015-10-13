package de.canitzp.snaploader.mappings;

import net.fybertech.dynamicmappings.Mapping;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.List;

public class ItemStackMappings extends BaseMapping {

    @Mapping(
            depends = {itemStackClass, entityLivingBaseClass},
            providesMethods = {itemStackClass + " " + "damageItem" + " (IL" + entityLivingBaseClass + ";)V"}
    )
    public boolean damageItem(){
        ClassNode cn1 = this.getClassNode(this.getClassMapping(itemStackClass));
        ClassNode cn2 = this.getClassNode(this.getClassMapping(entityLivingBaseClass));
        String method = "(IL" + cn2.name + ";)V";
        List methods = this.getMatchingMethods(cn1, null, method);
        if (methods.size() == 1) {
            this.addMethodMapping(itemStackClass + " " + "damageItem" + " (IL" + entityLivingBaseClass + ";)V", cn1.name + " " + ((MethodNode) methods.get(0)).name + " " + ((MethodNode) methods.get(0)).desc);
            addingMethodSuccess("damageItem");
        }

        return true;
    }
}
