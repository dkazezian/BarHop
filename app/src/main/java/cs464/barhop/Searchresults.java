package cs464.barhop;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
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
    BufferedReader reader = null;
    String barname = "";
    String distance = "";
    ArrayList<Integer> results;
    Bundle b = null;
    //AssetManager assetManager = getAssets();
    private InputStream input;

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
        b = getIntent().getExtras();
        results=b.getIntegerArrayList("searchresults");
        ArrayList <Button> buttonarray=new ArrayList<Button>();
        try {
                input = getAssets().open("bars.txt");
                reader = new BufferedReader(new InputStreamReader(input));


                //ArrayAdapter<Button> baradapter =
                //ListView barlist = (ListView) findViewById(R.id.testlistview);
                LinearLayout linlayout = (LinearLayout) findViewById(R.id.searchlayout);
                for (int i =0; i<results.size(); i++) {
                    Button barbutton = (Button) new Button(this);
                    barbutton.setId(i);
                    int go = 0;
                    int linenumber = results.get(i);

                    while (go < linenumber) {
                        reader.readLine();
                        go++;
                    }
                    String barname = reader.readLine();
                    reader.readLine();
                    String hours = reader.readLine();
                    reader.readLine();
                    reader.readLine();
                    reader.readLine();
                    reader.readLine();
                    reader.readLine();
                    String distance = reader.readLine();
                    String bartext=barname+"\n Hours: "+hours+ "\n Distance: "+distance;
                    barbutton.setText(bartext);
                    //barlist.add
                    final int templinenum = linenumber;
                    barbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Searchresults.this, Bar_profile.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("lineNum", templinenum);
                            intent.putExtras(bundle);
                            Searchresults.this.startActivity(intent);
                            finish();

                        }
                    });

                    linlayout.addView(barbutton);
                    input = getAssets().open("bars.txt");
                    reader = new BufferedReader(new InputStreamReader(input));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        Intent intent = new Intent(Searchresults.this, Bar_profile.class);
        Bundle b = new Bundle();
        b.putInt("bar", 1);
        intent.putExtras(b);




    }

    public void searchmethod(View veiw){
        Intent activityChangeIntent = new Intent(Searchresults.this, Search.class);
        Searchresults.this.startActivity(activityChangeIntent);
    }
    public void nearbymethod(View veiw){
                Intent intent = new Intent(Searchresults.this, Searchresults.class);
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
                Searchresults.this.startActivity(intent);
                finish();
}
    public void favemethod(View veiw){
        Intent activityChangeIntent = new Intent(Searchresults.this, Favorites.class);
        Searchresults.this.startActivity(activityChangeIntent);
    }




}
