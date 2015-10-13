package de.canitzp.snaploader;

import net.fybertech.meddle.MeddleMod;

@MeddleMod(name = "SnapLoader", author = "canitzp", depends = {"dynamicmappings", "meddleapi"}, id = "snaploader", version = Snaploader.VERSION)
public class Snaploader {

    public static Logger LOGGER = new Logger("SnapLoader");
    public static final String VERSION = "15.42.1";


    public Snaploader(){
        preInit();
    }

    public void preInit(){
        LOGGER.info("PreInit");
    }

    public void init(){
        LOGGER.info("Init");
    }
}
