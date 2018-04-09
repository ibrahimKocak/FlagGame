package com.example.ibrahim.flaggame;

import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ImageView question_image;
    ArrayList<ImageButton> buttons;
    TextView score;

    Toast toast;
    int used_flags_number = 0;                              //oyun bitimini icin secilen bayragı kontrol eden deger
    int random,answer,true_answer_count = 10;               //random=rastgele sayi uretme, answer=rastlege uretilen kita indexi, true_answer_count = siklarda olacak dogru sayisi
    TypedArray flags_africa,flags_asia,flags_europa,flags_n_amerika,flags_ocenica,flags_s_america,flags_continents,image;   //kitalarla ulke bayraklarinin resimleri tutan degiskenler
    boolean[] flags_true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = (TextView) findViewById(R.id.textView);
        question_image = (ImageView) findViewById(R.id.imageView);

        buttons = new ArrayList<>();
        buttons.add((ImageButton) findViewById(R.id.imageButton));
        buttons.add((ImageButton) findViewById(R.id.imageButton2));
        buttons.add((ImageButton) findViewById(R.id.imageButton3));
        buttons.add((ImageButton) findViewById(R.id.imageButton4));
        buttons.add((ImageButton) findViewById(R.id.imageButton5));
        buttons.add((ImageButton) findViewById(R.id.imageButton6));
        buttons.add((ImageButton) findViewById(R.id.imageButton7));
        buttons.add((ImageButton) findViewById(R.id.imageButton8));
        buttons.add((ImageButton) findViewById(R.id.imageButton9));
        buttons.add((ImageButton) findViewById(R.id.imageButton10));
        buttons.add((ImageButton) findViewById(R.id.imageButton11));
        buttons.add((ImageButton) findViewById(R.id.imageButton12));
        buttons.add((ImageButton) findViewById(R.id.imageButton13));
        buttons.add((ImageButton) findViewById(R.id.imageButton14));
        buttons.add((ImageButton) findViewById(R.id.imageButton15));

        flags_true = new boolean[15];                                       //bayraklarin sorulan kitaya aitligini kotrol edecegimiz dogruluk dizisi
        flags_africa = getResources().obtainTypedArray(R.array.africa);
        flags_asia = getResources().obtainTypedArray(R.array.asia);         //kitalara ait ulke bayraklarinin resimleri
        flags_europa = getResources().obtainTypedArray(R.array.europa);
        flags_n_amerika = getResources().obtainTypedArray(R.array.n_america);
        flags_ocenica = getResources().obtainTypedArray(R.array.ocenica);
        flags_s_america = getResources().obtainTypedArray(R.array.s_america);
        flags_continents = getResources().obtainTypedArray(R.array.continents);     //kitalarin resimleri (soru icin)
        image = getResources().obtainTypedArray(R.array.image);

        game_info();
        question();
    }



    private void question() {

        used_flags_number = 0;
        random = (int) (Math.random() * 6);
        answer = random;        //rastgele kita secimi
        Collections.shuffle(buttons);

        switch (answer){        //rastgele secilen kitaya gore soru resmi atamasi

            case 0: question_image.setImageResource(flags_continents.getResourceId(flags_continents.length()-6,0)); break;
            case 1: question_image.setImageResource(flags_continents.getResourceId(flags_continents.length()-5,0)); break;
            case 2: question_image.setImageResource(flags_continents.getResourceId(flags_continents.length()-4,0)); break;
            case 3: question_image.setImageResource(flags_continents.getResourceId(flags_continents.length()-3,0)); break;
            case 4: question_image.setImageResource(flags_continents.getResourceId(flags_continents.length()-2,0)); break;
            case 5: question_image.setImageResource(flags_continents.getResourceId(flags_continents.length()-1,0)); break;
        }

        for(int i=0;i<buttons.size();i++){                          //tuslara bayrak atamasi icin islemi

            buttons.get(i).setClickable(true);                      //bir onceki oyunda tiklanabilirligi kapanan tusları tiklanabilir yapma

            if(i < true_answer_count)                               //cevaplarda sorulan kitaya ait kac ülke bayrağı olacagi (dogru secenekler)
            {
                switch (answer){                                    //buttonlara ratgele belirlenen kitaya ait rastgele ulke bayragi atamasi

                    case 0: buttons.get(i).setImageResource(flags_africa.getResourceId((int) (Math.random() * flags_africa.length()), 0));    break;
                    case 1: buttons.get(i).setImageResource(flags_asia.getResourceId((int) (Math.random() * flags_asia.length()), 0));    break;
                    case 2: buttons.get(i).setImageResource(flags_europa.getResourceId((int) (Math.random() * flags_europa.length()), 0));    break;
                    case 3: buttons.get(i).setImageResource(flags_n_amerika.getResourceId((int) (Math.random() * flags_n_amerika.length()), 0));    break;
                    case 4: buttons.get(i).setImageResource(flags_ocenica.getResourceId((int) (Math.random() * flags_ocenica.length()), 0));    break;
                    case 5: buttons.get(i).setImageResource(flags_s_america.getResourceId((int) (Math.random() * flags_s_america.length()), 0));    break;
                }
                flags_true[i] = true;                               //bayrak dogru oldugu icin dogruluk  dizisine true degeri atiyoruz
            }
            else                                                    //dogru olamayan bayraklarin tuslara atanmasi
            {
                do {
                    random = (int) (Math.random() * 6);             //rastgele bayrak atiyoruz ancak atanan bayrak dogru cevap yani sorulan kitaya ait olmamali
                }while (random == answer);


                switch (random){                                    //rastgele secilen kitadan rastgele ulke bayragi atamasi

                    case 0: buttons.get(i).setImageResource(flags_africa.getResourceId((int) (Math.random() * flags_africa.length()), 0));    break;
                    case 1: buttons.get(i).setImageResource(flags_asia.getResourceId((int) (Math.random() * flags_asia.length()), 0));    break;
                    case 2: buttons.get(i).setImageResource(flags_europa.getResourceId((int) (Math.random() * flags_europa.length()), 0));    break;
                    case 3: buttons.get(i).setImageResource(flags_n_amerika.getResourceId((int) (Math.random() * flags_n_amerika.length()), 0));    break;
                    case 4: buttons.get(i).setImageResource(flags_ocenica.getResourceId((int) (Math.random() * flags_ocenica.length()), 0));    break;
                    case 5: buttons.get(i).setImageResource(flags_s_america.getResourceId((int) (Math.random() * flags_s_america.length()), 0));    break;
                }
                flags_true[i] = false;                              //bayrak yanlis oldugu icin dogruluk  dizisine false degeri atiyoruz
            }
        }
    }

    public void flag_onclick(View view) {

        try {
            toast.cancel();                          //eger toast aktif ise kapatiyoruz cunku tıklama sonu yeni toast aktif olacak, eskisinib bitmesini beklemek zorunda kalmasin
        }catch (Exception e){}                       //ilk tiklama da toast yok ve olmayan toastı kapatamadıgı icin cokuyor, o yuzden try-catch

        used_flags_number++;                        //tiklana bayrak sayisi her bayrak tiklamasinda artiyor
        view.setClickable(false);

        for(int i=0;i<buttons.size();i++)           //tiklanan butonu buttons listesinde ariyoruz
        {
            if(view == buttons.get(i))              //tiklanan butonu buttons listesinde buluyoruz
            {
                if(flags_true[i] == true)                                                           //bayrak sorulan kitaya ait ise scora 100 puan ekle ve resmini dogru olarak ayarla
                {
                    toast = Toast.makeText(this,"+100",Toast.LENGTH_SHORT);
                    buttons.get(i).setImageResource(R.drawable.aatrue);
                    score.setText(String.valueOf(Integer.parseInt((String) score.getText()) + 100));
                }
                else                                                                                //bayrak sorulan kitaya ait degil ise..
                {
                    toast = Toast.makeText(this,"-50",Toast.LENGTH_SHORT);
                    buttons.get(i).setImageResource(R.drawable.aafalse);

                    if(Integer.parseInt((String) score.getText()) -50 < 0 )                         //puandan 50 cikarinca puan negatif oluyorsa oyun bitsin ve yenisi baslasin
                    {
                        toast = Toast.makeText(this,"LOOSER!!",Toast.LENGTH_SHORT);
                    }else                                                                           //puandan 50 puan cikarabiliyor isek scordan 50 puan düs
                        score.setText(String.valueOf(Integer.parseInt((String) score.getText()) - 50));
                }
            }
        }
        toast.show();

        if(used_flags_number == true_answer_count)                                                                 //10 bayrak secildikten sonra oyunu bitir ve yeni oyuna gec
        {
            game_end_newgame();
        }
    }

    private void game_end_newgame(){                                                                //oyunu sonlandirma ve yenisine baslama

        for(int i=0;i<buttons.size();i++)                                                           //oyun bitti buttonlarin tiklanabilirligi false
            buttons.get(i).setClickable(false);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {                                                                 //2sn bekle ve yeni oyuna basla (son secimin dogrulugu gorunmesi icin bekleme yapimali)
                question();
            }
        }, 2000);
    }

    public void game_info(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("How to play ??")
                .setMessage("There is a continent and 15 countries flag. 10 countries are located in this continent." +
                        "Find and select the flags of these 10 countries. Do not forget you can only choose 10 flags." +
                        "There is no winning limit for developing flag information as you want! Conguralations!")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }
}
