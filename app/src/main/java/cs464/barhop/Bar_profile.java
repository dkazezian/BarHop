package cs464.barhop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Bar_profile extends AppCompatActivity {

    private String barname="";
    private String address="";
    private boolean favorited=false;
    private String hours="";
    private String phone="";
    private String tags="";
    private String reviews="";
    private String specevents="";
    private String menu="";
    private InputStream reader;
    private BufferedReader input;


    private void parsebarinfo(int linenumber){
        int go=0;
        try {
            while (go < linenumber) {
                input.readLine();
                go++;
            }
            barname=input.readLine();
            address=input.readLine();
            hours=input.readLine();
            phone=input.readLine();
            tags=input.readLine();
            reviews=input.readLine();
            specevents=input.readLine();
            menu=input.readLine();

            String[] speceventsplit=specevents.split(";");
            specevents="Monday: "+speceventsplit[0]+"\n"+"Tuesday: "+speceventsplit[1]+"\n"+"Wednesday: "+speceventsplit[2]+"\n"+"Thursday: "+speceventsplit[3]+"\n"+"Friday: "+speceventsplit[4]+"\n"+"Saturday: "+speceventsplit[5]+"\n"+"Sunday: "+speceventsplit[6]+"\n";

            ArrayList<Integer> serviceall=new ArrayList<Integer>();
            double service=0;
            ArrayList<Integer> foodall=new ArrayList<Integer>();
            double food=0;
            ArrayList<Integer> drinksall=new ArrayList<Integer>();
            double drinks=0;
            ArrayList<Integer> atmosphereall=new ArrayList<Integer>();
            double atmosphere=0;
            ArrayList<Integer> specialsall=new ArrayList<Integer>();
            double specials=0;

            String[] reviewsplit=reviews.split(",");
            for (int i=0; i<reviewsplit.length; i++){
                String[] reviewsplitsplit=reviewsplit[i].split(" ");
                serviceall.add(Integer.parseInt(reviewsplitsplit[0]));
                foodall.add(Integer.parseInt(reviewsplitsplit[1]));
                drinksall.add(Integer.parseInt(reviewsplitsplit[2]));
                atmosphereall.add(Integer.parseInt(reviewsplitsplit[3]));
                specialsall.add(Integer.parseInt(reviewsplitsplit[4]));
                }
            for (int i=0; i<serviceall.size();i++){
                service=service+serviceall.get(i);
            }
            service=service/((double)serviceall.size());
            for (int i=0; i<foodall.size();i++){
                food=food+foodall.get(i);
            }
            food=food/((double)foodall.size());
            for (int i=0; i<drinksall.size();i++){
                drinks=drinks+drinksall.get(i);
            }
            drinks=drinks/((double)drinksall.size());
            for (int i=0; i<atmosphereall.size();i++){
                atmosphere=atmosphere+atmosphereall.get(i);
            }
            atmosphere=atmosphere/((double)atmosphereall.size());
            for (int i=0; i<specialsall.size();i++){
                specials=specials+specialsall.get(i);
            }
            specials=specials/((double)specialsall.size());

            reviews="Service: "+ Double.toString(service) +"/n"+"Food: "+ Double.toString(food) +"/n"+"Drinks: "+ Double.toString(drinks) +"/n"+"Atmosphere: "+ Double.toString(atmosphere) +"/n"+"Specials: "+ Double.toString(specials) +"/n";

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_profile);
        try {
            reader = getAssets().open("barpageexample");
            input = new BufferedReader(new InputStreamReader(reader));
            parsebarinfo(1);
            final TextView namebox = (TextView) findViewById(R.id.textView);
            final TextView addressbox = (TextView) findViewById(R.id.textView6);
            final TextView hoursbox = (TextView) findViewById(R.id.textView9);
            final TextView phonebox = (TextView) findViewById(R.id.textView11);
            final TextView tagsbox = (TextView) findViewById(R.id.textView13);
            final TextView infobox = (TextView) findViewById(R.id.InfoBox);
            namebox.setText(barname);
            addressbox.setText(address);
            hoursbox.setText(hours);
            phonebox.setText(phone);
            tagsbox.setText(tags);
            infobox.setText(specevents);



        }
        catch(IOException e){
            e.printStackTrace();
        }
    }



}
