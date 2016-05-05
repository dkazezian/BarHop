package cs464.barhop;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Favorites extends AppCompatActivity {
    ListView lv;
    ArrayList<Favorite> favoritesList;
    //FavoritesAdapter favAdapter;
    public int lineNumber;
    public String BarName;
    //  public String[] Bars;
    // private List<Favorite>;
    public ArrayList<String> values;
    Favorite gs;


   /* @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
        int pos = lv.getPositionForView(buttonView);
        if(pos != ListView.INVALID_POSITION){
            Favorite p = favorites.get(pos);
            p.setSelected(isChecked);
        }


    }
*/

    public void addbartofave(String linenum, String barname){
        gs.getname().add(barname);
        gs.getlinenum().add(linenum);
        displayfave();



    }
    private void displayfave(){

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(0, 0, 0, 50);
        LinearLayout favlayout = (LinearLayout) findViewById(R.id.favoritelayout);
        LinearLayout chkboxlinlayout = (LinearLayout) findViewById(R.id.checkboxlayout);
        if(((LinearLayout) favlayout).getChildCount() > 0)
            ((LinearLayout) favlayout).removeAllViews();
        if(((LinearLayout) chkboxlinlayout).getChildCount() > 0)
            ((LinearLayout) chkboxlinlayout).removeAllViews();
        for (int i =0; i<gs.getname().size(); i++) {
            Button favbutton = (Button) new Button(this);
            CheckBox cb = (CheckBox) new CheckBox(this);
            favbutton.setId(i);
            cb.setId(i);
            String bartext=gs.getname().get(i);
            favbutton.setText(bartext);
            //barlist.add
            favlayout.addView(favbutton);
            chkboxlinlayout.addView(cb,layoutParams);
            gs.getchecks().add(cb);
        }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gs = (Favorite) getApplication();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        displayfave();
        final Button remove = (Button) findViewById(R.id.buttonremove);
        remove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (int i=0; i<gs.getchecks().size(); i++){
                    if (gs.getchecks().get(i).isChecked()){
                        gs.getname().remove(i);
                        gs.getlinenum().remove(i);
                        gs.getchecks().remove(i);
                        i--;
                    }
                }
                displayfave();
            }
        });



    }
    public void searchmethod(View veiw){
                Intent activityChangeIntent = new Intent(Favorites.this, Search.class);
                Favorites.this.startActivity(activityChangeIntent);
    }
    public void nearbymethod(View veiw){
        Intent intent = new Intent(Favorites.this, Searchresults.class);
        ArrayList <Integer> finalresults=new ArrayList();
        finalresults.add(1);
        finalresults.add(11);
        finalresults.add(21);
        finalresults.add(31);
        finalresults.add(41);
        finalresults.add(51);
        finalresults.add(61);
        finalresults.add(71);
        finalresults.add(81);
        finalresults.add(91);
        finalresults.add(101);
        finalresults.add(111);
        finalresults.add(121);
        finalresults.add(131);
        finalresults.add(141);
        finalresults.add(151);
        finalresults.add(161);
        finalresults.add(171);
        finalresults.add(181);
        finalresults.add(191);
        Bundle b = new Bundle();
        b.putIntegerArrayList("searchresults", finalresults);
        intent.putExtras(b);
        Favorites.this.startActivity(intent);
        finish();
    }
    public void favemethod(View veiw){
        //donothing
    }



}