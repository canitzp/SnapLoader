package de.canitzp.snaploader.mappings;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import de.canitzp.snaploader.Snaploader;
import org.objectweb.asm.tree.*;

import java.util.Iterator;
import java.util.List;

public class BaseMapping extends AllClassStrings{

    public static int loadedMappings = 0;

    public BaseMapping(){}

    public boolean addMethod(@NotNull String mainClass, @Nullable String methodIn, @Nullable String methodReturnType, @NotNull String methodName){
        ClassNode classNode = this.getClassNode(this.getClassMapping(mainClass));
        ClassNode classNode2 = null, classNode3 = null;
        String method;
        if(methodIn != null){
            classNode2 = this.getClassNode(this.getClassMapping(methodIn));
        }
        if (methodReturnType != null){
            classNode3 = this.getClassNode(this.getClassMapping(methodReturnType));
        }
        if(methodIn != null && methodReturnType != null){
            method = "(L" + classNode2.name + ";)L" + classNode3.name + ";";
            List methods = this.getMatchingMethods(classNode, null, method);
            if (methods.size() == 1){
                this.addMethodMapping(mainClass + " " + methodName + " (L" + methodIn + ";)L" + methodReturnType + ";", classNode.name + " " + ((MethodNode) methods.get(0)).name + " " + ((MethodNode) methods.get(0)).desc);
                addingMethodSuccess(methodName);
            } else errorIncorrectReports(methodName);
        }
        return true;
    }

    public boolean addMethod(@NotNull String mainClass, @NotNull String methodIn, @NotNull String methodIn2, @Nullable String methodReturnType, @NotNull String methodName){
        ClassNode classNode = this.getClassNode(this.getClassMapping(mainClass));
        ClassNode classNodeMethodIn1, classNode3 = null, classNodeMethodIn2;
        String method;
        classNodeMethodIn1 = this.getClassNode(this.getClassMapping(methodIn));
        classNodeMethodIn2 = this.getClassNode(this.getClassMapping(methodIn2));
        if (methodReturnType != null){
            classNode3 = this.getClassNode(this.getClassMapping(methodReturnType));
        }
        if(methodReturnType != null){
            method = "(L" + classNodeMethodIn1.name + ";L" + classNodeMethodIn2.name + ";)L" + classNode3.name + ";";
            List methods = this.getMatchingMethods(classNode, null, method);
            if (methods.size() == 1){
                this.addMethodMapping(mainClass + " " + methodName + " (L" + methodIn + ";L" + methodIn2 + ";)L" + methodReturnType + ";", classNode.name + " " + ((MethodNode) methods.get(0)).name + " " + ((MethodNode) methods.get(0)).desc);
                addingMethodSuccess(methodName);
            } else if(methods.size() >= 2) errorIncorrectReports(methodName);
            else if(methods.size() == 0) errorNoMethod(methodName);
        } else {
            method = "(L" + classNodeMethodIn1.name + ";L" + classNodeMethodIn2.name + ";)V";
            List methods = this.getMatchingMethods(classNode, null, method);
            if(methods.size() == 1){
                this.addMethodMapping(mainClass + " " + methodName + " (L" + methodIn + ";L" + methodIn2 + ";)V", classNode.name + " " + ((MethodNode) methods.get(0)).name + " " + ((MethodNode) methods.get(0)).desc);
                addingMethodSuccess(methodName);
            } else if(methods.size() >= 2) errorIncorrectReports(methodName);
            else if(methods.size() == 0) errorNoMethod(methodName);
        }
        return true;
    }

    public boolean addMethod(@NotNull String mainClass, @NotNull String methodIn, @NotNull String methodIn2, @NotNull String methodIn3, @Nullable String methodReturnType, @NotNull String methodName){
        ClassNode classNode = this.getClassNode(this.getClassMapping(mainClass));
        ClassNode classNodeMethodIn1, classNode3 = null, classNodeMethodIn2, classNodeMethodIn3;
        String method;
        classNodeMethodIn1 = this.getClassNode(this.getClassMapping(methodIn));
        classNodeMethodIn2 = this.getClassNode(this.getClassMapping(methodIn2));
        classNodeMethodIn3 = this.getClassNode(this.getClassMapping(methodIn3));
        if (methodReturnType != null){
            classNode3 = this.getClassNode(this.getClassMapping(methodReturnType));
        }
        if(methodReturnType != null){
            method = "(L" + classNodeMethodIn1.name + ";L" + classNodeMethodIn2.name + ";L" + classNodeMethodIn3 + ";)L" + classNode3.name + ";";
            List methods = this.getMatchingMethods(classNode, null, method);
            if (methods.size() == 1){
                this.addMethodMapping(mainClass + " " + methodName + " (L" + methodIn + ";L" + methodIn2 + ";L" + methodIn3 + ";)L" + methodReturnType + ";", classNode.name + " " + ((MethodNode) methods.get(0)).name + " " + ((MethodNode) methods.get(0)).desc);
                addingMethodSuccess(methodName);
            } else if(methods.size() >= 2) errorIncorrectReports(methodName);
            else if(methods.size() == 0) errorNoMethod(methodName);
        } else {
            method = "(L" + classNodeMethodIn1.name + ";L" + classNodeMethodIn2.name + ";L" + classNodeMethodIn3.name + ";)L" + classNode3.name + ";";
            List methods = this.getMatchingMethods(classNode, null, method);
            if(methods.size() == 1){
                this.addMethodMapping(mainClass + " " + methodName + " (L" + methodIn + ";L" + methodIn2 + ";L" + methodIn3 + ";)L" + ";", classNode.name + " " + ((MethodNode) methods.get(0)).name + " " + ((MethodNode) methods.get(0)).desc);
                addingMethodSuccess(methodName);
            } else if(methods.size() >= 2) errorIncorrectReports(methodName);
            else if(methods.size() == 0) errorNoMethod(methodName);
        }
        return true;
    }

    public boolean addField(@NotNull String mainClass, @NotNull String type, @NotNull String newName){
        ClassNode mainNode = this.getClassNodeFromMapping(mainClass);
        List list = this.getMatchingFields(mainNode, null, type);
        if(list.size() == 1){
            this.addFieldMapping(mainClass + " " + newName + " " + type, mainNode.name + " " + ((FieldNode)list.get(0)).name + " " + ((FieldNode)list.get(0)).desc);
            addingFieldSuccess(newName);
        }
        return true;
    }






    protected static void addingMethodSuccess(String methodName){
        Snaploader.LOGGER.info("Added new Method: \"" + methodName + "\" to DynamicMappings.");
        loadedMappings++;
    }
    protected static void addingFieldSuccess(String fieldName){
        Snaploader.LOGGER.info("Added new Field: \"" + fieldName + "\" to DynamicMappings.");
        loadedMappings++;
    }
    protected static void errorIncorrectReports(String methodName){
        Snaploader.LOGGER.error("There is more than one Method with the Constructor: \"" + methodName + "\"!");
    }
    protected static void errorNoMethod(String methodName){
        Snaploader.LOGGER.error("There is no Method with the Constructor: \"" + methodName + "\"!");
    }
}
