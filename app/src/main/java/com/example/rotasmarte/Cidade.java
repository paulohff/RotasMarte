package com.example.rotasmarte;

public class Cidade {

    private String nome;
    private Double cordX, cordY;

    public Cidade() throws Exception {
        nome = "";
        cordX = 0.0;
        cordY = 0.0;
    }

    public Cidade(String nome) throws Exception {
        this.nome = nome;
        cordX = 0.0;
        cordY = 0.0;
    }

    public Cidade(String nome, double coordX, double coordY) throws Exception {
        this.nome = nome;
        this.cordX = coordX;
        this.cordY = coordY;
    }

    public String Nome()
    {
        return nome;
    }

    public double CoordX()
    {
       return cordX;
    }

    public double CoordY()
    {
        return cordY;
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
