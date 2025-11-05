package com.cligest.restservice;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
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
    public static final int INSURANCE_NOSSA_SEGUROS = 541;
    public static final int INSURANCE_ENSA_SEGUROS = 197;

    public static final String DB_LUANDA    = "jdbc/CligestSI";
    public static final String DB_VIANA     = "jdbc/CligestSI_VIANA";
    public static final String DB_CMU       = "jdbc/CligestSI_CMU";
    public static final String DB_ZANGO     = "jdbc/CligestSI_ZANGO";
    public static final String DB_BENFICA   = "jdbc/CligestSI_BENFICA";
    public static final String DB_PANGUILA  = "jdbc/CligestSI_PANGUILA"; // Renamed to Cacuaco
    public static final String DB_CABINDA   = "jdbc/CligestSI_CABINDA";
    public static final String DB_AVENNIDA  = "jdbc/CligestSI_AVENNIDA";

    public static final String DB_UTENTE = "jdbc/OracleUtente";

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
            String query =
                    "Select fe.[Nº de Processo] from FE where fe.[ID entidade]=" + entidade + " and Data='" + data +
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
    //    if ((entidade != INSURANCE_BIC_SEGUROS) && (entidade != INSURANCE_GLOBAL_SEGUROS) && (entidade != INSURANCE_NOSSA_SEGUROS)) {
    //        throw new Exception("Input vars validation failed: Unauthorized insurance id.");
    //    }
    }

    public void close() throws Exception {
        try {
            dbCon.close();
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }

    public Utente[] searchUtenteByName(String name, String birthDate) throws SQLException {

        ArrayList<Utente> utenteArrayList = new ArrayList<Utente>();

        System.out.println("name = " + name);
        System.out.println("birthDate = " + birthDate);

        if ((name.length() > 3) && (birthDate.length() == 10)) {

            String[] nameArray = name.split(" ",0);

            String selectQuery = "select * from UTENTE where ( ";

            int n = 0;
            while (n < nameArray.length ) {
                selectQuery = selectQuery + " \"Nome\" like '%" + nameArray[n] + "%' collate binary_ai ";
                n++;
                if (n < nameArray.length) {
                    selectQuery = selectQuery + " or ";
                }
            }

            selectQuery = selectQuery + " ) and ( \"Data Nascimento\" like '%" + birthDate + "%'"
                    + " or \"Data Nascimento\" like '%" + birthDate.substring(5,6)  + "/" + birthDate.substring(8,9) + "/" + birthDate.substring(2,3)+ "%') ";

            System.out.println("selectQuery = " + selectQuery);

            Statement statement = dbCon.createStatement();
            ResultSet rs = statement.executeQuery(selectQuery);

            while (rs.next()) {
                Utente tmp = new Utente(rs.getString("Nome"));
                tmp.setId_Utente(rs.getString(1));
                tmp.setId_Utente_Externo(rs.getString(2));
                tmp.setId_Entidade(rs.getString(3));
                tmp.setId_Titular(rs.getString(4));
                tmp.setTelefone(rs.getString(6));
                tmp.setNome_Do_Titular(rs.getString(9));
                tmp.setE_Mail(rs.getString(10));
                tmp.setBi(rs.getString(11));
                tmp.setData_Nascimento(rs.getString(12));

                utenteArrayList.add(tmp);
            }
        }

        return utenteArrayList.toArray(new Utente[0]);
    }
}
