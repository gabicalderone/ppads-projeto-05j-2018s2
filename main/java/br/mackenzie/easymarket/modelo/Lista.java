package br.mackenzie.easymarket.modelo;

public class Lista {
    private String name;
    private int ID;

    public Lista(int ID, String nome){
        this.ID = ID;
        this.name = nome;
    }

    public String getNome(){
        return name;
    }

    public int getID(){
        return ID;
    }
}
