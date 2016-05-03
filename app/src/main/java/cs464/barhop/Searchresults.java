package cs464.barhop;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by kazezian on 4/28/16.
 */
public class Searchresults extends AppCompatActivity {
    int linecount = 0;
    int barnamelinenumber=0;
    BufferedReader input = null;
    String barname = "";
    String distance = "";
    Bundle b = getIntent().getExtras();
    ArrayList<Integer> results = b.getIntegerArrayList("searchresults");
    //AssetManager assetManager = getAssets();
    private InputStream reader;


    public static boolean isNumeric(String str){
        try{
            int d = Integer.parseInt(str);
        }
        catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresults);

            try {
                reader = getAssets().open("barpageexample");
                input = new BufferedReader(new InputStreamReader(reader));


                //ArrayAdapter<Button> baradapter =
                ListView barlist = (ListView) findViewById(R.id.testlistview);
                for (int i =0; i<results.size(); i++) {
                    Button barbutton = (Button) new Button(this);
                    int go = 0;
                    int linenumber = results.get(i);

                    while (go < linenumber) {
                        input.readLine();
                        go++;
                    }
                    String barname = input.readLine();
                    input.readLine();
                    String hours = input.readLine();
                    input.readLine();
                    input.readLine();
                    input.readLine();
                    input.readLine();
                    input.readLine();
                    input.readLine();
                    String distance = input.readLine();
                    String bartext=barname+"\n Hours: "+hours+ "\n Distance: "+distance;
                    barbutton.setText(bartext);
                    //barlist.add

                    input = new BufferedReader(new InputStreamReader(reader));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        Intent intent = new Intent(Searchresults.this, Bar_profile.class);
        Bundle b = new Bundle();
        b.putInt("bar", 1);
        intent.putExtras(b);

        final Button examplebutt = (Button) findViewById(R.id.exampleButt);
        examplebutt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(Searchresults.this, Bar_profile.class);
                Searchresults.this.startActivity(activityChangeIntent);
            }
        });
    }
    public void searchmethod(View veiw){
        Intent activityChangeIntent = new Intent(Searchresults.this, Search.class);
        Searchresults.this.startActivity(activityChangeIntent);
    }
    public void nearbymethod(View veiw){
        Intent intent = new Intent(Searchresults.this, Searchresults.class);
        ArrayList<Integer> results= new ArrayList<Integer>();
        results.add(2);
        results.add(12);
        results.add(22);
        Bundle b = new Bundle();
        b.putIntegerArrayList("searchresults", results);
        intent.putExtras(b);
        Searchresults.this.startActivity(intent);
        finish();
    }
    public void favemethod(View veiw){
        Intent activityChangeIntent = new Intent(Searchresults.this, Favorites.class);
        Searchresults.this.startActivity(activityChangeIntent);
    }

    public void populate(){



    }
    public void searchmethod(View veiw){
        Intent activityChangeIntent = new Intent(Searchresults.this, Search.class);
        Searchresults.this.startActivity(activityChangeIntent);
    }
    public void nearbymethod(View veiw){
        Intent intent = new Intent(Searchresults.this, Searchresults.class);
        ArrayList<Integer> results= new ArrayList<Integer>();
        results.add(2);
        results.add(12);
        results.add(22);
        Bundle b = new Bundle();
        b.putIntegerArrayList("searchresults", results);
        intent.putExtras(b);
        Searchresults.this.startActivity(intent);
        finish();
    }
    public void favemethod(View veiw){
        Intent activityChangeIntent = new Intent(Searchresults.this, Favorites.class);
        Searchresults.this.startActivity(activityChangeIntent);
    }



}
