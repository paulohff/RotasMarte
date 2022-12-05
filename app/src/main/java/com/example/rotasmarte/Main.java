package com.example.rotasmarte;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rotasmarte.dataStructures.graphs.Grafo;

import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Main extends AppCompatActivity {



    public static  final String jsonCidades = "{\"cidades\":"+
    "[" +
    "{"+
        "\"nomeCidade\": \"Acheron\"," +
            "\"coordenadaX\": 0.11353," +
            "\"coordenadaY\": 0.28857"+
    "}," +
    "{"+
          "\"nomeCidade\": \"Arena\"," +
           "\"coordenadaX\":0.68726," +
          "\"coordenadaY\": 0.29248"+
    "}," +
    "{"+
            "\"nomeCidade\": \"Arrakeen\"," +
            "\"coordenadaX\":0.05298," +
            "\"coordenadaY\":0.48682"+
    "}," +
   "{"+
            "\"nomeCidade\": \"BakhucoordenadaYsen\"," +
            "\"coordenadaX\":0.67456," +
            "\"coordenadaY\":0.52002"+
   "}," +
   "{"+
            "\"nomeCidade\": \"BradburcoordenadaY\"," +
            "\"coordenadaX\":0.40747," +
            "\"coordenadaY\":0.74365"+
  "}," +
  "{"+
            "\"nomeCidade\": \"Burroughs\"," +
            "\"coordenadaX\": 0.71265," +
            "\"coordenadaY\":0.37939"+
  "}," +
            "]" +
            "}";

    /////////////////////////////////
    //////////////////////////////////
    ///////////////////////////////////

    public static  final String jsonCaminhos = "{\"caminhos\":" +
    "["+


    "{\"cidadeDeOrigem\": \"Acheron\"," +
            "\"cidadeDeDestino\": \"Arrakeen\","+ //"\"cidadeDeDestino\": \"Echus Overlook\","+
            "\"distanciaCaminho\": 1300," +
            "\"tempoCaminho\": 9,"+
            "\"custoCaminho\": 500" +
     "}," +
     "{\"cidadeDeOrigem\": \"Arrakeen\"," +
            "\"cidadeDeDestino\": \"Arena\","+ //"\"cidadeDeDestino\": \"Echus Overlook\","+
            "\"distanciaCaminho\": 1300," +
            "\"tempoCaminho\": 9,"+
            "\"custoCaminho\": 500" +
     "}," +
      "{\"cidadeDeOrigem\": \"Arena\"," +
            "\"cidadeDeDestino\": \"BakhucoordenadaYsen\","+ //"\"cidadeDeDestino\": \"Echus Overlook\","+
            "\"distanciaCaminho\": 1300," +
            "\"tempoCaminho\": 9,"+
            "\"custoCaminho\": 500" +
     "}," +
/*
    "{\"cidadeDeOrigem\": \"Arrakeen\"," +
            "\"cidadeDeDestino\": \"Gondor\","+
            "\"distanciaCaminho\": 3500," +
            "\"tempoCaminho\": 17,"+
            "\"custoCaminho\": 100" +
    "}," +
    "{\"cidadeDeOrigem\": \"Bradbury\"," +
            "\"cidadeDeDestino\": \"Odessa\","+
            "\"distanciaCaminho\": 4800," +
            "\"tempoCaminho\": 27,"+
            "\"custoCaminho\": 160" +
    "}," +
    "{\"c\": \"Bradbury\"," +
            "\"cidadeDeDestino\": \"Underhill\","+
            "\"distanciaCaminho\": 3000," +
            "\"tempoCaminho\": 18,"+
            "\"custoCaminho\": 110" +
    "}," +
    "{\"cidadeDeOrigem\": \"Burroughs\"," +
            "\"cidadeDeDestino\": \"Arena\"," +
            "\"distanciaCaminho\": 1000," +
            "\"tempoCaminho\": 8,"+
            "\"custoCaminho\": 60" +
    "}," +
*/

     "]" +
     "}";
     Spinner spinnerPara;
     Spinner spinnerDe;
    EditText edtCaminhos;
    ImageView imgMapa;

    Bitmap bitmap;
    Canvas canvas;
    Paint paint;
   // MinhaView minhaview;

   Grafo oGrafo = new Grafo();

    Vector<Cidade> vetorCidades = new Vector<Cidade>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela);
        ////////////////////////////////
        String jsonCidadess = Utils.getJsonFromAssets(getApplicationContext(), "cidades.json");
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<Cidade>>() { }.getType();

        List<Cidade> users = gson.fromJson(jsonCidadess, listUserType);

        for(int i = 0; i < users.size() ; i++) {
            Log.i("data", "> Item " + i + "\n" + users.get(i));
        }
      // minhaview = new MinhaView(this);
       //setContentView(minhaview);
        imgMapa = (ImageView)findViewById(R.id.imgMapa);
        spinnerDe = (Spinner)findViewById(R.id.spinnerDe);
        spinnerPara = (Spinner)findViewById(R.id.spinnerPara);

        Bitmap setaMapa = BitmapFactory.decodeResource(getResources(),R.drawable.mapa);
        bitmap= Bitmap.createScaledBitmap(setaMapa, 1460, 730 , false);
       // bitmap = Bitmap.createBitmap(1460, 730, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        paint.setColor(Color.GREEN);
       imgMapa.setImageBitmap(bitmap);
        canvas.drawLine(1000, 100, 2000, 200, paint);

        getSupportActionBar().hide();

        ArrayList<String> adapterCidades = new ArrayList<String>();
        try {
            JSONObject cidades = new JSONObject(jsonCidades);
            JSONArray arrayCidades = cidades.getJSONArray("cidades");
            for (int i = 0; i < arrayCidades.length() - 1; i++) {
                JSONObject cidade = arrayCidades.getJSONObject(i);
               // Log.d("Main", cidade.getString("nomeCidade"));
               // Log.d("Main", Double.toString(cidade.getDouble("coordenadaX")));
              //  Log.d("Main", Double.toString(cidade.getDouble("coordenadaY")));

                adapterCidades.add(cidade.getString("nomeCidade"));

               // Cidade novaCidade = new Cidade(nome, coordX, coordY, indice);
                //Cidade novaCidade = new Cidade(cidade.getString("nomeCidade"), cidade.getDouble("coordenadaX"), cidade.getDouble("coordenadaY"));
                //vetorCidades.add(novaCidade);
                oGrafo.NovoVertice(cidade.getString("nomeCidade"));
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, adapterCidades);
            spinnerDe.setAdapter(adapter);
            spinnerPara.setAdapter(adapter);
        }
        catch
        (Exception e) {
            e.printStackTrace();
        }

        try {
            JSONObject caminhos = new JSONObject(jsonCaminhos);
            JSONArray arrayCaminhos = caminhos.getJSONArray("caminhos");
            Log.d("Main", Integer.toString(vetorCidades.size()));
            int indiceOrigem = -1, indiceDestino = -1;
            for (int i = 0; i != arrayCaminhos.length(); i++) {
                JSONObject caminho = arrayCaminhos.getJSONObject(i);
                for(int k = 0; k != vetorCidades.size(); k++)
                {
                    Log.d("Main", caminho.getString("cidadeDeOrigem"));
                    Log.d("Main", vetorCidades.get(i).Nome());
                    if (caminho.getString("cidadeDeOrigem").equals(vetorCidades.get(k).Nome()))
                        indiceOrigem = k;
                    if (caminho.getString("cidadeDeDestino").equals( vetorCidades.get(k).Nome()))
                        indiceDestino = k;
                    if (indiceDestino >= 0 && indiceOrigem >= 0)
                    {
                        oGrafo.NovaAresta(indiceOrigem, indiceDestino, caminho.getInt("distanciaCaminho"));
                        break;
                        //Log.d("Main","BUAAA BUAA");
                    }
                }
                indiceOrigem = -1;
                indiceDestino = -1;

            }
        } catch
        (Exception e) {
            e.printStackTrace();
        }
        edtCaminhos = (EditText)findViewById(R.id.edtCaminhos);
        oGrafo.Caminho(0, 1, edtCaminhos);
    }

/*
    public void Desenhar()
    {
        imgMapa = (ImageView)findViewById(R.id.imgMapa);

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)[
                ]
        Paint caneta = new Paint();
        caneta.setColor(Color.RED);
        if (textHeight == 0) {
            textHeight = textPaint.getTextSize();
        } else {
            textPaint.setTextSize(textHeight);
        }

        piePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        piePaint.setStyle(Paint.Style.FILL);
        piePaint.setTextSize(textHeight);

        shadowPaint = new Paint(0);
        shadowPaint.setColor(0xff101010);
        shadowPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));
    }7

 */

}

    /*
     Log.d("Main" , caminho.getString("cidadeDeOrigem"));
                    Log.d("Main" , caminho.getString("cidadeDeDestino"));
                    Log.d("Main" , Integer.toString(caminho.getInt("distanciaCaminho")));

     */

