package org.example;

public class Conta {

    private String numero;


    private Integer saldo;

    public Conta(String numero, Integer saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public String getNumero() {
        return numero;
    }
}
