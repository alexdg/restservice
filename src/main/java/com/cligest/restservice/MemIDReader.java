package com.cligest.restservice;

public class MemIDReader {

    private final String memID;


    public MemIDReader(String memID) {
        this.memID = memID;
        DatabaseLib.addMemIDtoList(memID);
    }

    public String[] getMemIDs() {
        String[] result = DatabaseLib.getMemIDs();

        return result;
    }
}