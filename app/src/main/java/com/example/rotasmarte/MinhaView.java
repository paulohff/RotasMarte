package com.example.rotasmarte;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MinhaView extends View {

    Paint paint;
    private Bitmap mapa;


    public MinhaView(Context context) {
        super(context);
    }
    public MinhaView(Context context, AttributeSet attrs){
        super(context, attrs);
    }
    public MinhaView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int mapaTotalX = 1460;
        int mapaTotalY = 730;

//Trabalhando com drawBitmap()
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();

        Bitmap bitmap =  BitmapFactory.decodeFile(String.valueOf(mapa), bmOptions);

         canvas = new Canvas(bitmap);

/*
        Bitmap setaMapa = BitmapFactory.decodeResource(getResources(),R.drawable.mapa);
        this.mapa = Bitmap.createScaledBitmap(setaMapa, mapaTotalX, mapaTotalY , false);

//canvas.drawBitmap(MyBitmap, boardPosX, boardPosY, paint)
        canvas.drawBitmap(mapa, 0,0, null);

 */
        //Trabalhando com drawLine()
        paint = new Paint();

//Propriedades da linha
        paint.setColor(Color.RED);
        Float espessura = 5.0f; //espessura da linha
        paint.setStrokeWidth(espessura);

        float cidadeInicioX = 0.11353f * mapaTotalX; //BarcelonaX
        float cidadeInicioY = 0.28857f * mapaTotalY; //BarcelonaY

        float cidadeFimX = 0.68726f * mapaTotalX; //CordobaX
        float cidadeFimY = 0.29248f * mapaTotalY; //CordobaY

        canvas.drawLine(cidadeInicioX , cidadeInicioY, cidadeFimX, cidadeFimY, paint);
    }
}
