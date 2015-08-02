package de.canitzp.snaploader;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

import java.io.File;
import java.util.List;

public class SnapLoader implements ITweaker, IClassTransformer {

    private byte[] transformEntityItem(byte[] classbytes){
        Strings.logger.info("Started!");
        return classbytes;
    }

    @Override
    public void acceptOptions(List<String> list, File file, File file1, String s) {

    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader launchClassLoader) {
        launchClassLoader.registerTransformer(SnapLoader.class.getName());
    }

    @Override
    public String getLaunchTarget() {
        return null;
    }

    @Override
    public String[] getLaunchArguments() {
        return new String[0];
    }

    @Override
    public byte[] transform(String s, String s1, byte[] bytes) {
        return transformEntityItem(bytes);
    }
}
