package com.cligest.restservice;

public class NumeroEpisodio {

    private final long entidade;
    private final String data;

    public NumeroEpisodio(long entidade, String data) {
        this.entidade=entidade;
        this.data=data;
    }

    public String[] getEntidades() {
        String[] result = null;
        try {
            DatabaseStuff dbstuff = new DatabaseStuff();
            result = dbstuff.getNumeroProcesso(entidade,data);
        } catch (Exception e) {
            System.err.println("NumeroEpisodio.getEntidades: Exception" + e.getMessage());
        }
        return result;
    }


}
