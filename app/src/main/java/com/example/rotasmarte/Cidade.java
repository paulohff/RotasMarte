package com.example.rotasmarte;

import java.util.List;

public class Cidade {

    private String nomeCidade;
    private Double coordenadaX, coordenadaY;
    List<String> mensagens;
/*
    public Cidade() throws Exception {
        nomeCidade = "";
        coordenadaX = 0.0;
        coordenadaY = 0.0;
    }

    public Cidade(String nome) throws Exception {
        this.nomeCidade = nome;
        coordenadaX = 0.0;
        coordenadaY = 0.0;
    }

    public Cidade(String nome, double coordX, double coordY) throws Exception {
        this.nomeCidade = nome;
        this.coordenadaX = coordX;
        this.coordenadaY = coordY;
    }
*/
    public String Nome()
    {
        return nomeCidade;
    }

    public double CoordX()
    {
       return coordenadaX;
    }

    public double CoordY()
    {
        return  coordenadaY;
    }

    public List<String> getMessages() {
        return mensagens;
    }

    public void setMessages(List<String> messages) {
        this.mensagens = mensagens;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + nomeCidade + '\'' +
                ", X=" + coordenadaX +
                ", Y=" +  coordenadaY +
                '}';
    }
    /*
    public int CompareTo(Cidade outraCidade)
    {
        // Compara apenas o nome das instâncias
        int valorComparado = Nome.Trim().CompareTo(outraCidade.Nome.Trim());
        return valorComparado;
    }

     */

}
