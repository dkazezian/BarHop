package cs464.barhop;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by kazezian on 5/2/16.
 */
public class AppToolbar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar);

        final Button search = (Button) findViewById(R.id.bottom_bar_search);
        final Button nearbyme = (Button) findViewById(R.id.bottom_bar_nearby);
        final Button favorites = (Button) findViewById(R.id.bottom_bar_favorites);
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(AppToolbar.this, Search.class);
                AppToolbar.this.startActivity(activityChangeIntent);
            }
        });

        nearbyme.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AppToolbar.this, Searchresults.class);
                ArrayList<Integer> results= new ArrayList<Integer>();
                results.add(2);
                results.add(12);
                results.add(22);
                Bundle b = new Bundle();
                b.putIntegerArrayList("searchresults", results);
                intent.putExtras(b);
                AppToolbar.this.startActivity(intent);
                finish();
            }
        });

        favorites.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(AppToolbar.this, Favorites.class);
                AppToolbar.this.startActivity(activityChangeIntent);
            }
        });
    }

}
