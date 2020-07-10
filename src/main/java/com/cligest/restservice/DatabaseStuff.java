package com.cligest.restservice;



import org.springframework.boot.autoconfigure.quartz.QuartzProperties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class DatabaseStuff {

    public static final int INSURANCE_BIC_SEGUROS = 416;
    public static final int INSURANCE_GLOBAL_SEGUROS = 415;

    private Context initContext;
    private DataSource ds;
    private Connection dbCon;

    public DatabaseStuff() throws Exception {
        try {
            initContext = new InitialContext();
            Context webContext = (Context) initContext.lookup("java:/comp/env");

            ds = (DataSource) webContext.lookup("jdbc/CligestSI");
            dbCon = ds.getConnection();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String[] getNumeroProcesso(long entidade, String data) throws Exception {
        String[] result;
        try {

            // validate vars first
            validateVars(entidade, data);

            //todo escape char \\ on result string
            String query = "Select fe.[Nº de Processo] from FE where fe.[ID entidade]=" + entidade + " and Data='" + data + "'";

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
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return result;
    }

    public void validateVars (long entidade, String data) throws Exception {
        if (data.length() != 10) {
            throw new Exception("Input vars validation failed: Date field is malformed.");
        }
        if ((entidade != INSURANCE_BIC_SEGUROS) && (entidade != INSURANCE_GLOBAL_SEGUROS)) {
            throw new Exception("Input vars validation failed: Unauthorized insurance id.");
        }
    }

}
