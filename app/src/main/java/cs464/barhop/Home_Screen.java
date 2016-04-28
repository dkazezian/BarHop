package cs464.barhop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class Home_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__screen);
        final Button search = (Button) findViewById(R.id.search);
        final Button nearbyme = (Button) findViewById(R.id.nearbyMe);
        final Button favorites = (Button) findViewById(R.id.favorites);
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(Home_Screen.this, Search.class);
                Home_Screen.this.startActivity(activityChangeIntent);
            }
        });

        nearbyme.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(Home_Screen.this, Searchresults.class);
                Home_Screen.this.startActivity(activityChangeIntent);
            }
        });

        favorites.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(Home_Screen.this, Favorites.class);
                Home_Screen.this.startActivity(activityChangeIntent);
            }
        });
    }

}
