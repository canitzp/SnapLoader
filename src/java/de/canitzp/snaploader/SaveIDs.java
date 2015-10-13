package de.canitzp.snaploader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SaveIDs {

    private File path = new File("");
    private File id = new File(path.getAbsolutePath() + File.separator + "snaploader" + File.separator + "ids.cfg");

    public SaveIDs(){}

    public void save() {
        id.getParentFile().mkdirs();
        try {
            if (!id.exists()) id.createNewFile();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(id));
            out.writeObject(GameRegistry.idMap);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map read(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(id));
            Map<String, Integer> idMap = (Map) ois.readObject();
            ois.close();
            return idMap;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}
