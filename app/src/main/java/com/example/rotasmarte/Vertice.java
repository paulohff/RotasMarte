package com.example.rotasmarte;

public class Vertice {
    public boolean foiVisitado;
    public String rotulo;
    public boolean estaAtivo;

    public Vertice(String label)
    {
        rotulo = label;
        foiVisitado = false;
        estaAtivo = true;
    }
}
