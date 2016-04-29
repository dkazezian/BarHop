package cs464.barhop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by kazezian on 4/28/16.
 */
public class Searchresults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresults);


        final Button examplebutt = (Button) findViewById(R.id.exampleButt);
        examplebutt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(Searchresults.this, Bar_profile.class);
                Searchresults.this.startActivity(activityChangeIntent);
            }
        });

    }



}
