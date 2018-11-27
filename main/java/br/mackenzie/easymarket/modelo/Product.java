package br.mackenzie.easymarket.modelo;

public class Product {
    private String nome;
    private int ID;
    private boolean checked = false;

    public Product(int ID, String conteudo){
        this.nome = conteudo;
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String conteudo) {
        this.nome = conteudo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
