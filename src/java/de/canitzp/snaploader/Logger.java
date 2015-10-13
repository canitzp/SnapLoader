package de.canitzp.snaploader;

public class Logger {

    private String id;

    public Logger(String id){
        this.id = id;
    }

    public void info(String write){
        System.out.println("[" + id + "]: " + write);
    }

    public void error(String write){
        System.out.println("[" + id + "] [ERROR]: " + write);
    }

}
