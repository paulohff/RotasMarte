package com.example.rotasmarte;

import android.widget.EditText;

import java.util.Stack;

public class Grafo {
    private static final int NUM_VERTICES = 20;
    private Vertice[] vertices;
    private int[][] adjMatrix;
    int numVerts;

    /// DIJKSTRA
    DistOriginal[] percurso;
    int infinity = -2147481349;
    int verticeAtual; // global que indica o vértice atualmente sendo visitado
    int doInicioAteAtual; // global usada para ajustar menor caminho com Djikstra
    int nTree;

    public Grafo() {
        vertices = new Vertice[NUM_VERTICES];
        adjMatrix = new int[NUM_VERTICES][NUM_VERTICES];

        nTree = 0;
        numVerts = 0;
        for (int j = 0; j < NUM_VERTICES; j++) // zera toda a matriz
            for (int k = 0; k < NUM_VERTICES; k++)
                adjMatrix[j][k] = infinity;

        percurso = new DistOriginal[NUM_VERTICES];
    }

    public void NovoVertice(String label) {
        vertices[numVerts] = new Vertice(label);
        numVerts++;
    }
    public void NovaAresta(int origem, int destino, int peso)
    {
        adjMatrix[origem][destino] = peso; // adjMatrix[eend, start] = 1; ISSO GERA CICLOS!!!
    }
    public void NovaAresta(int start, int eend) {
        adjMatrix[start][eend] = 1; // adjMatrix[eend, start] = 1; ISSO GERA CICLOS!!!
    }

    /*
    public void ExibirVertice(int v)
    {
        Console.Write(vertices[v].rotulo + " ");
    }
    public void ExibirVertice(int v, TextBox txt)
    {
        txt.Text += vertices[v].rotulo + " ";
    }
    */
    public int SemSucessores() // encontra e retorna a linha de um vértice sem sucessores
    {
        boolean temAresta;
        for (int linha = 0; linha < numVerts; linha++) {
            temAresta = false;
            for (int col = 0; col < numVerts; col++) {
                if (adjMatrix[linha][col] > 0) {
                    temAresta = true;
                    break;
                }
                if (!temAresta)
                    return linha;
            }
        }
        return -1;
    }

    public void RemoverVertice(int vert) {
            /*
            if (dgv != null) {
                MessageBox.Show($"Matriz de Adjacências antes de remover vértice {vert}");
                ExibirAdjacencias();
            }

             */

        if (vert != numVerts - 1) {
            for (int j = vert; j < numVerts - 1; j++)// remove vértice do vetor
                vertices[j] = vertices[j + 1];
// remove vértice da matriz
            for (int row = vert; row < numVerts; row++)
                MoverLinhas(row, numVerts - 1);
            for (int col = vert; col < numVerts; col++)
                MoverColunas(col, numVerts - 1);
        }

        numVerts--;
        /*
        if (dgv != null)
        {
            MessageBox.Show($"Matriz de Adjacências após remover vértice {vert}");
            ExibirAdjacencias();
            MessageBox.Show("Retornando à ordenação");
        }

         */
    }

    /*
    public void ExibirAdjacencias()
    {
        dgv.RowCount = numVerts+1;
        dgv.ColumnCount = numVerts+1;
        for (int j = 0; j < numVerts; j++)
        {
            dgv.Rows[j + 1].Cells[0].Value = vertices[j].rotulo;
            dgv.Rows[0].Cells[j+1].Value = vertices[j].rotulo;
            for (int k = 0; k < numVerts; k++)
                dgv.Rows[j + 1].Cells[k + 1].Value = Convert.ToString(adjMatrix[j, k]);
        }
    }
    */
    public String OrdenacaoTopologica() {
        Stack<String> gPilha = new Stack<String>(); //guarda a sequência de vértices
        int origVerts = numVerts;
        while (numVerts > 0) {
            int currVertex = SemSucessores();
            if (currVertex == -1)
                return "Erro: grafo possui ciclos.";
            gPilha.push(vertices[currVertex].rotulo); // empilha vértice
            RemoverVertice(currVertex);
        }
        String resultado = "Sequência da Ordenação Topológica: ";
        while (gPilha.size() > 0)
            resultado += gPilha.pop() + " "; // desempilha para exibir
        return resultado;
    }

    private void MoverColunas(int col, int length) {
        if (col != numVerts - 1)
            for (int row = 0; row < length; row++)
                adjMatrix[row][col] = adjMatrix[row][col + 1]; // desloca para excluir
    }

    private void MoverLinhas(int row, int length) {
        if (row != numVerts - 1)
            for (int col = 0; col < length; col++)
                adjMatrix[row][col] = adjMatrix[row + 1][col]; // desloca para excluir
    }

    public String Caminho(int inicioDoPercurso, int finalDoPercurso, EditText lista)
    {
        for (int j = 0; j < numVerts; j++)
            vertices[j].foiVisitado = false;
        vertices[inicioDoPercurso].foiVisitado = true;
        for (int j = 0; j < numVerts; j++)
        {
        // anotamos no vetor percurso a distância entre o inicioDoPercurso e cada vértice
            // se não há ligação direta, o valor da distância será infinity
            int tempDist = adjMatrix[inicioDoPercurso][j];
            percurso[j] = new DistOriginal(inicioDoPercurso, tempDist);
        }
        for (int nTree = 0; nTree < numVerts; nTree++)
        {
            // Procuramos a saída não visitada do vértice inicioDoPercurso com a menor distância
            int indiceDoMenor = ObterMenor();
           // e anotamos essa menor distância
            int distanciaMinima = percurso[indiceDoMenor].distancia;


              // o vértice com a menor distância passa a ser o vértice atual
             // para compararmos com a distância calculada em AjustarMenorCaminho()
            verticeAtual = indiceDoMenor;
            doInicioAteAtual = percurso[indiceDoMenor].distancia;
            // visitamos o vértice com a menor distância desde o inicioDoPercurso
            vertices[verticeAtual].foiVisitado = true;
            AjustarMenorCaminho(lista);
        }
        return ExibirPercursos(inicioDoPercurso, finalDoPercurso, lista);
    }
    public int ObterMenor()
    {
        int distanciaMinima = infinity;
        int indiceDaMinima = 0;
        for (int j = 0; j < numVerts; j++)
            if (!(vertices[j].foiVisitado) && (percurso[j].distancia <distanciaMinima))
            {
                distanciaMinima = percurso[j].distancia;
                indiceDaMinima = j;
            }
        return indiceDaMinima;
    }
    public void AjustarMenorCaminho(EditText lista)
    {
        for (int coluna = 0; coluna < numVerts; coluna++)
            if (!vertices[coluna].foiVisitado) // para cada vértice ainda não visitado
            {
                // acessamos a distância desde o vértice atual (pode ser infinity)
                int atualAteMargem = adjMatrix[verticeAtual][coluna];
                 // calculamos a distância desde inicioDoPercurso passando por vertice atual
                 // até esta saída
                int doInicioAteMargem = doInicioAteAtual + atualAteMargem;
                 // quando encontra uma distância menor, marca o vértice a partir do
                // qual chegamos no vértice de índice coluna, e a soma da distância
               // percorrida para nele chegar
                int distanciaDoCaminho = percurso[coluna].distancia;
                if (doInicioAteMargem < distanciaDoCaminho)
                {
                    percurso[coluna].verticePai = verticeAtual;
                    percurso[coluna].distancia = doInicioAteMargem;
                }
            }
        lista.setText("==================Caminho ajustado==============");
        lista.setText(" ");
    }
    public String ExibirPercursos(int inicioDoPercurso, int finalDoPercurso,
                                  EditText lista)
    {
        String resultado = "";
        for (int j = 0; j < numVerts; j++)
        {
            resultado += vertices[j].rotulo + "=";
            if (percurso[j].distancia == infinity)
                resultado += "inf";
            else
                resultado += percurso[j].distancia+" ";
            String pai = vertices[percurso[j].verticePai].rotulo;
            resultado += "(" + pai + ") ";
        }
        lista.setText(resultado);
        lista.setText("");
        lista.setText("");
        lista.setText("Caminho entre " + vertices[inicioDoPercurso].rotulo +
                " e " + vertices[finalDoPercurso].rotulo);
        lista.setText(" ");
        int onde = finalDoPercurso;
        Stack<String> pilha = new Stack<String>();

        int cont = 0;
        while (onde != inicioDoPercurso)
        {
            onde = percurso[onde].verticePai;
            pilha.push(vertices[onde].rotulo);
            cont++;
        }
        resultado = "";
        while (pilha.size() != 0)
        {
            resultado += pilha.pop();
            if (pilha.size() != 0)
                resultado += " --> ";
        }
        if ((cont == 1) && (percurso[finalDoPercurso].distancia == infinity))
            resultado = "Não há caminho";
        else
            resultado += " --> " + vertices[finalDoPercurso].rotulo;
        lista.setText(resultado);
        return resultado;
    }
}
