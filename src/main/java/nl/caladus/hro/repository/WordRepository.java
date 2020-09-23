package nl.caladus.hro.repository;


import java.util.HashMap;

public class WordRepository {

    private HashMap<String, String> memory = new HashMap<>();

    public HashMap<String, String> getMemory() {
        return memory;
    }

    public void setMemory(String key, String value) {
        memory.put(key, value);
    }
}
