package de.canitzp.snaploader.transformer;

import de.canitzp.snaploader.Snaploader;
import de.canitzp.snaploader.mappings.Init;
import net.fybertech.meddle.MeddleUtil;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

import java.io.File;
import java.util.List;

public class Tweaker implements ITweaker {
    @Override
    public void acceptOptions(List<String> list, File file, File file1, String s) {
        Snaploader.LOGGER.info("You are using Snaploader 1.9-" + Snaploader.VERSION);
        Init.init();
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader launchClassLoader) {
        if(MeddleUtil.isClientJar()) {
            launchClassLoader.registerTransformer(MainMenuTransformer.class.getName());
        }
    }

    @Override
    public String getLaunchTarget() {
        return null;
    }

    @Override
    public String[] getLaunchArguments() {
        return new String[0];
    }
}
