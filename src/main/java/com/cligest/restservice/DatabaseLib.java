package com.cligest.restservice;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DatabaseLib {

    public static final int INSURANCE_BIC_SEGUROS = 416;
    public static final int INSURANCE_GLOBAL_SEGUROS = 415;

    public static final String DB_LUANDA = "jdbc/CligestSI";
    public static final String DB_VIANA = "jdbc/CligestSI_VIANA";
    public static final String DB_CMU = "jdbc/CligestSI_CMU";
    public static final String DB_ZANGO = "jdbc/CligestSI_ZANGO";

    private Context initContext;
    private DataSource ds;
    private Connection dbCon;

    private static List<String> memIIsList = new ArrayList<String>();

    public DatabaseLib(String datasource) throws Exception {
        try {
            initContext = new InitialContext();
            Context webContext = (Context) initContext.lookup("java:/comp/env");

            ds = (DataSource) webContext.lookup(datasource);
            dbCon = ds.getConnection();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void addMemIDtoList(String memID) {
        memIIsList.add(memID);
    }

    public static String[] getMemIDs() {
        String[] result = new String[memIIsList.size()];
        result = memIIsList.toArray(result);
        Arrays.sort(result);
        return result;
    }

    public String[] getNumeroProcesso(long entidade, String data) throws Exception {
        String[] result;
        try {

            // validate vars first
            validateVars(entidade, data);

            //todo escape char \\ on result string
            String query = "Select fe.[Nº de Processo] from FE where fe.[ID entidade]=" + entidade + " and Data='" + data +
                    "' order by [Nº de Processo] ASC";

            Statement statement = dbCon.createStatement();
            ResultSet rs = statement.executeQuery(query);

            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }

            rs = statement.executeQuery(query);
            result = new String[rowCount];
            int count = 0;
            while (rs.next()) {
                result[count] = rs.getString(1);
                count++;
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return result;
    }

    private void validateVars (long entidade, String data) throws Exception {
        if (data.length() != 10) {
            throw new Exception("Input vars validation failed: Date field is malformed.");
        }
        if ((entidade != INSURANCE_BIC_SEGUROS) && (entidade != INSURANCE_GLOBAL_SEGUROS)) {
            throw new Exception("Input vars validation failed: Unauthorized insurance id.");
        }
    }

    public void close() throws Exception {
        try {
            dbCon.close();
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }

}
