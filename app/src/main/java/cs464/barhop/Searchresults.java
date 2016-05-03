package cs464.barhop;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by kazezian on 4/28/16.
 */
public class Searchresults extends AppCompatActivity {
    int linecount = 0;
    int barnamelinenumber=0;
    Scanner s = null;
//    Scanner s2 = null;


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

        String barname = "";
        String distance = "";

        Bundle b = getIntent().getExtras();
        ArrayList<Integer> results = b.getIntegerArrayList("searchresults");
        AssetManager assetManager = getAssets();
        InputStream tempIS = null;
        try {
            tempIS = assetManager.open("barpageexample");

            s = new Scanner(new BufferedReader(new FileReader(String.valueOf(tempIS))));
            while (s.hasNextLine()) {//The whole file
                String fullline = s.nextLine();
                if(isNumeric(fullline)) {
                    int number = Integer.parseInt(fullline);
                    if (results.contains(number)) {
                        barname = s.next();
                        for(int i = 0; i < 7; i++){
                            s.next();
                            if(i == 7){
                                distance = s.next();
                            }
                        }
                    }
                    TextView myTextView= (TextView) findViewById(R.id.textView2);
                    myTextView.setText(barname + "\t" + distance);
                }
                else s.next();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

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



}
