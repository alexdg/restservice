package com.cligest.restservice;

public class UtenteID {
    private String queryName;
    private String queryBirthDate;

    public UtenteID (String name, String birthDate) {
        queryName = name;
        queryBirthDate = birthDate;
    }

    public Utente[] getUtenteID() throws Exception {

        DatabaseLib db = new DatabaseLib(DatabaseLib.DB_UTENTE);

        Utente[] result = db.searchUtenteByName(queryName, queryBirthDate);

        db.close();

        return result;
    }

}
