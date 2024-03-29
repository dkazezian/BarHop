package cs464.barhop;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import static android.widget.Toast.LENGTH_LONG;

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
    private String finallinenumber;
    private InputStream reader;
    private BufferedReader input;
    Bundle b = null;
    private boolean showreview=true;


    private void parsebarinfo(int linenumber){
        int go=0;
        linenumber=linenumber-1;
        try {
            while (go < linenumber) {
                input.readLine();
                go++;
            }
            finallinenumber=input.readLine();
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

            reviews="Service: "+ Double.toString(service) +"\n"+"Food: "+ Double.toString(food) +"\n"+"Drinks: "+ Double.toString(drinks) +"\n"+"Atmosphere: "+ Double.toString(atmosphere) +"\n"+"Specials: "+ Double.toString(specials) +"\n";
            String[] menusplit=menu.split(",");
            menu="";
            for (int i=0; i<menusplit.length; i++){
                menu=menu+menusplit[i]+"\n";
            }

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
            reader = getAssets().open("bars.txt");
            input = new BufferedReader(new InputStreamReader(reader));
            b = getIntent().getExtras();
            int lineNumber = b.getInt("lineNum");
            final int lineNumberForReview = lineNumber;
            boolean tempboolean = b.getBoolean("showreview");
            showreview = tempboolean;
            parsebarinfo(lineNumber);
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


            final Button menubutt = (Button) findViewById(R.id.button5);
            menubutt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    infobox.setText(menu);
                    final Button reviewbutt = (Button) findViewById(R.id.button4);
                    final Button speceventbutt = (Button) findViewById(R.id.button3);
                    final Button menubutt = (Button) findViewById(R.id.button5);
                    menubutt.setBackgroundResource(R.color.gray);
                    speceventbutt.setBackgroundResource(R.color.lightgray);
                    reviewbutt.setBackgroundResource(R.color.lightgray);
                    final Button makereview=(Button)findViewById(R.id.makereview);
                    makereview.setVisibility(View.INVISIBLE);
                }
            });
            final Button speceventbutt = (Button) findViewById(R.id.button3);
            speceventbutt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    infobox.setText(specevents);
                    final Button reviewbutt = (Button) findViewById(R.id.button4);
                    final Button speceventbutt = (Button) findViewById(R.id.button3);
                    final Button menubutt = (Button) findViewById(R.id.button5);
                    speceventbutt.setBackgroundResource(R.color.gray);
                    menubutt.setBackgroundResource(R.color.lightgray);
                    reviewbutt.setBackgroundResource(R.color.lightgray);
                    final Button makereview=(Button)findViewById(R.id.makereview);
                    makereview.setVisibility(View.INVISIBLE);
                }
            });
            final Button reviewbutt = (Button) findViewById(R.id.button4);
            reviewbutt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    infobox.setText(reviews);
                    final Button reviewbutt = (Button) findViewById(R.id.button4);
                    final Button speceventbutt = (Button) findViewById(R.id.button3);
                    final Button menubutt = (Button) findViewById(R.id.button5);
                    reviewbutt.setBackgroundResource(R.color.gray);
                    speceventbutt.setBackgroundResource(R.color.lightgray);
                    menubutt.setBackgroundResource(R.color.lightgray);
                    final Button makereview=(Button)findViewById(R.id.makereview);
                    makereview.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Intent reviewIntent = new Intent(Bar_profile.this, reviewbar.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("lineNum", lineNumberForReview);
                            bundle.putBoolean("showreview", false);
                            reviewIntent.putExtras(bundle);
                            Bar_profile.this.startActivity(reviewIntent);
                            finish();
                        }
                    });
                    if(showreview==true) {
                        makereview.setVisibility(View.VISIBLE);
                    }
                    else{
                        makereview.setVisibility(View.INVISIBLE);

                    }
                }
            });
            speceventbutt.setBackgroundResource(R.color.gray);
            menubutt.setBackgroundResource(R.color.lightgray);
            reviewbutt.setBackgroundResource(R.color.lightgray);
            final ToggleButton togButton = (ToggleButton) findViewById(R.id.toggleButton);
            Favorite gs=  (Favorite) getApplication();
            if(gs.isfave(barname)){
                togButton.setChecked(true);
            }
            togButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if (togButton.isChecked()){
                        Favorite gs=  (Favorite) getApplication();
                        gs.addbartofave(finallinenumber, barname);
                    }
                    else{
                        Favorite gs=  (Favorite) getApplication();
                         gs.removefave(finallinenumber, barname);
                    }
                }
            });
            final Button gonow = (Button) findViewById(R.id.gonow);
            gonow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String newaddress= address.replace(" ", "+");
                    String newbarname=barname.replace(" ", "+");
                    String query = "http://maps.google.com/?q="+newbarname+"+"+newaddress;
                    Uri uri = Uri.parse(query);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void searchmethod(View veiw){
        Intent activityChangeIntent = new Intent(Bar_profile.this, Search.class);
        Bar_profile.this.startActivity(activityChangeIntent);
    }
    public void nearbymethod(View veiw){
        Intent intent = new Intent(Bar_profile.this, Searchresults.class);
        ArrayList<Integer> results= new ArrayList<Integer>();
        results.add(1);
        results.add(11);
        results.add(21);
        results.add(31);
        results.add(41);
        results.add(51);
        results.add(61);
        results.add(71);
        results.add(81);
        results.add(91);
        results.add(101);
        results.add(111);
        results.add(121);
        Bundle b = new Bundle();
        b.putIntegerArrayList("searchresults", results);
        intent.putExtras(b);
        Bar_profile.this.startActivity(intent);
        finish();
    }
    public void favemethod(View veiw){
        Intent activityChangeIntent = new Intent(Bar_profile.this, Favorites.class);
        Bar_profile.this.startActivity(activityChangeIntent);
    }



}
