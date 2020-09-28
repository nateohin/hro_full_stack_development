package nl.caladus.hro.repository;


import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class WordRepository {

    private HashMap<String, String> memory;

    public WordRepository(HashMap<String, String> memory) {
        this.memory = memory;
    }

    public HashMap<String, String> getMemory() {
        return memory;
    }

    public void setMemory(String key, String value) {
        memory.put(key, value);
    }
}
