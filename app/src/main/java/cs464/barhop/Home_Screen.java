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
        final Button button = (Button) findViewById(R.id.);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(view v){
                startActivity(new Intent(Home_Screen.this, MainActivity.this));
            }

        }
    }

}
