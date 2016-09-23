package com.bayarbogdanov.incorrect_data_access.lesson11;

import java.util.HashMap;
import java.util.Map;

public class FakeDB {

    private Map<String, String> usersMap = new HashMap<>();

    public Map<String, String> getUsers() {
        loadUsers();
        return usersMap;
    }

    private void loadUsers() {
        usersMap.put("Crild", "ghjcnjnfr219532");
        usersMap.put("crd", "219532");
        usersMap.put("marybog18", "18121967mib");
    }
}
