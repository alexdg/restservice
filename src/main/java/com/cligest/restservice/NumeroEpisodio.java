package com.cligest.restservice;

import java.util.ArrayList;
import java.util.Arrays;

public class NumeroEpisodio {

    private final long entidade;
    private final String data;
    private final String morada;

    private static String[] CACHE_LUANDA;
    private static String[] CACHE_CMU;
    private static String[] CACHE_VIANA;
    private static String[] CACHE_ZANGO;

    private static String ADDR_LUANDA = "Rua Albano Machado, 29 e 31";
    private static String ADDR_CMU = "Rua s/n, Casa s/n, Distrito Urbano do Camama, Município de Belas";
    private static String ADDR_VIANA = "Municipio de Viana Q. D. 18 Projecto Morar N.7";
    private static String ADDR_ZANGO = "Municipio de Viana Bairro Mundimba Zango O";
    private static String ADDR_CABINDA = "Rua António Brissac, 12 Edif. C -Sub-Cave";
    private static String ADDR_PANGUILA = "Municipo do Dande Rua C Casa 356 Sector 2";

    private static String[][] dbList  = {
            {"Luanda", DatabaseLib.DB_LUANDA, ADDR_LUANDA},
            {"CMU", DatabaseLib.DB_CMU, ADDR_CMU},
            {"Viana", DatabaseLib.DB_VIANA, ADDR_VIANA},
            {"Zango", DatabaseLib.DB_ZANGO, ADDR_ZANGO},
    };

    static {
        // do nothing
    }

    public NumeroEpisodio(long entidade, String data, String morada) {

        System.out.println("morada =" + morada);

        this.entidade=entidade;
        this.data=data;
        this.morada=morada;
    }

    public String[] getEntidades() {
        String[] result = null;
        ArrayList<String> list = new ArrayList<String>();
        boolean sendCache = false;
        DatabaseLib dblib;
        String[] tmp = null;

        for (int i = 0; i < dbList.length; i++) {

            if (dbList[i][2].equals(morada) || morada.equals("")) {
                try {
                    dblib = new DatabaseLib(dbList[i][1]);
                    tmp = dblib.getNumeroProcesso(entidade, data);
                    list.addAll(Arrays.asList(tmp));
                    dblib.close();
                    // CACHE_LUANDA = tmp.clone();
                } catch (Exception e) {
                    System.err.println("NumeroEpisodio.getEntidades: Exception: " + dbList[i][0] + e.getMessage());
                    //  sendCache = true;
                }
            }
        }

/*
        try {
            dblib = new DatabaseLib(DatabaseLib.DB_LUANDA);
            tmp = dblib.getNumeroProcesso(entidade,data);
            list.addAll(Arrays.asList(tmp));
            dblib.close();
            CACHE_LUANDA = tmp.clone();
        } catch (Exception e) {
            System.err.println("NumeroEpisodio.getEntidades: Exception: DB_LUANDA " + e.getMessage());
            sendCache = true;
        }

        try {
            dblib = new DatabaseLib(DatabaseLib.DB_CMU);
            tmp = dblib.getNumeroProcesso(entidade,data);
            list.addAll(Arrays.asList(tmp));
            dblib.close();
            CACHE_CMU = tmp.clone();
        } catch (Exception e) {
            System.err.println("NumeroEpisodio.getEntidades: Exception: DB_CMU " + e.getMessage());
            sendCache = true;
        }

        try {
            dblib = new DatabaseLib(DatabaseLib.DB_VIANA);
            tmp = dblib.getNumeroProcesso(entidade,data);
            list.addAll(Arrays.asList(tmp));
            dblib.close();
            CACHE_VIANA = tmp.clone();
        } catch (Exception e) {
            System.err.println("NumeroEpisodio.getEntidades: Exception DB_VIANA " + e.getMessage());
            sendCache = true;
        }

        try {
            dblib = new DatabaseLib(DatabaseLib.DB_ZANGO);
            tmp = dblib.getNumeroProcesso(entidade,data);
            list.addAll(Arrays.asList(tmp));
            dblib.close();
            CACHE_ZANGO = tmp.clone();
        } catch (Exception e) {
            System.err.println("NumeroEpisodio.getEntidades: Exception DB_ZANGO " + e.getMessage());
            sendCache = true;
        }

        if (sendCache) {
            // something went wrong, use the cache
            System.err.println("Send the cache.");
            list = new ArrayList<String>();
            list.addAll(Arrays.asList(CACHE_LUANDA));
            list.addAll(Arrays.asList(CACHE_CMU));
            list.addAll(Arrays.asList(CACHE_VIANA));
        }

 */

        result = list.toArray(new String[0]);

        return result;
    }
}
