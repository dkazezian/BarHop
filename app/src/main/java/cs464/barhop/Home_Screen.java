package cs464.barhop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;

public class Home_Screen extends AppCompatActivity {


    private Toolbar apptoolbar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__screen);
        final ImageButton search = (ImageButton) findViewById(R.id.search);
        final ImageButton nearbyme = (ImageButton) findViewById(R.id.nearbyMe);
        final ImageButton favorites = (ImageButton) findViewById(R.id.favorites);
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(Home_Screen.this, Search.class);
                Home_Screen.this.startActivity(activityChangeIntent);
            }
        });

        nearbyme.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Home_Screen.this, Searchresults.class);
                ArrayList<Integer> results= new ArrayList<Integer>();
                results.add(2);
                results.add(12);
                results.add(22);
                results.add(32);
                results.add(42);
                results.add(52);
                results.add(62);
                results.add(72);
                results.add(82);
                results.add(92);
                results.add(102);
                results.add(112);
                results.add(122);
                Bundle b = new Bundle();
                b.putIntegerArrayList("searchresults", results);
                intent.putExtras(b);
                Home_Screen.this.startActivity(intent);
                finish();
            }
        });

        favorites.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(Home_Screen.this, Favorites.class);
                Home_Screen.this.startActivity(activityChangeIntent);
            }
        });

        /*apptoolbar=(AppToolbar)findViewById((R.id.toolbar));
        setSupportActionBar(apptoolbar);*/

    }

}
