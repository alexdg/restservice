package com.cligest.restservice;

public class Fe2Ne {

    private final long ne;
    private final String fe;
    private final String memid;
    private final String user;
    private final String date;

    public Fe2Ne(long ne, String fe, String memid, String user, String date) {
        this.ne=ne;
        this.fe=fe;
        this.memid = memid;
        this.user = user;
        this.date=date;
    }

    public String[] getResult() {
        String[] result = null;
        try {
            //DatabaseLib dblib = new DatabaseLib();

            // find

            //dblib.close();
        } catch (Exception e) {
            System.err.println("Fe2Ne.getResult: Exception" + e.getMessage());
        }
        return result;
    }


}
