package com.cligest.restservice;


public class Utente {

  public Utente(String nome) {
    this.nome = nome;
  }

  public Utente(String id_Utente, String id_Utente_Externo, String id_Entidade, String id_Titular, String telefone, String nome, String nome_Do_Titular, String e_Mail,
                String bi, String data_Nascimento) {
    this.id_Utente = id_Utente;
    this.id_Utente_Externo = id_Utente_Externo;
    this.id_Entidade = id_Entidade;
    this.id_Titular = id_Titular;
    this.telefone = telefone;
    this.nome = nome;
    this.nome_Do_Titular = nome_Do_Titular;
    this.e_Mail = e_Mail;
    this.bi = bi;
    this.data_Nascimento = data_Nascimento;
  }

  private String id_Utente;
  private String id_Utente_Externo;
  private String id_Entidade;
  private String id_Titular;
  private String telefone;
  private String nome;
  private String nome_Do_Titular;
  private String e_Mail;
  private String bi;
  private String data_Nascimento;

  public String getId_Utente() {
    return id_Utente;
  }

  public void setId_Utente(String id_Utente) {
    this.id_Utente = id_Utente;
  }

  public String getId_Utente_Externo() {
    return id_Utente_Externo;
  }

  public void setId_Utente_Externo(String id_Utente_Externo) {
    this.id_Utente_Externo = id_Utente_Externo;
  }

  public String getId_Entidade() {
    return id_Entidade;
  }

  public void setId_Entidade(String id_Entidade) {
    this.id_Entidade = id_Entidade;
  }

  public String getId_Titular() {
    return id_Titular;
  }

  public void setId_Titular(String id_Titular) {
    this.id_Titular = id_Titular;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getNome_Do_Titular() {
    return nome_Do_Titular;
  }

  public void setNome_Do_Titular(String nome_Do_Titular) {
    this.nome_Do_Titular = nome_Do_Titular;
  }

  public String getE_Mail() {
    return e_Mail;
  }

  public void setE_Mail(String e_Mail) {
    this.e_Mail = e_Mail;
  }

  public String getBi() {
    return bi;
  }

  public void setBi(String bi) {
    this.bi = bi;
  }

  public String getData_Nascimento() {
    return data_Nascimento;
  }

  public void setData_Nascimento(String data_Nascimento) {
    this.data_Nascimento = data_Nascimento;
  }

}
