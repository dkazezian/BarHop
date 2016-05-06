
package cs464.barhop;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class reviewbar extends Activity {

    Bundle b = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewbar);

        b = getIntent().getExtras();
        final int lineNumber = b.getInt("lineNum");
        boolean showreview = b.getBoolean("showreview");

        final Button addReview = (Button) findViewById(R.id.addReviewButton);
        addReview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(reviewbar.this, Bar_profile.class);
                Bundle bundle = new Bundle();
                bundle.putInt("lineNum", lineNumber);
                bundle.putBoolean("showreview", false);
                intent.putExtras(bundle);
                String submitted = "Review has been submitted.";
                Toast.makeText(reviewbar.this, submitted, LENGTH_LONG).show();
                reviewbar.this.startActivity(intent);
                finish();
            }
        });

        final Button cancelReview = (Button) findViewById(R.id.cancelButton);
        cancelReview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(reviewbar.this, Bar_profile.class);
                Bundle bundle = new Bundle();
                bundle.putInt("lineNum", lineNumber);
                bundle.putBoolean("showreview", true);
                intent.putExtras(bundle);
                reviewbar.this.startActivity(intent);
                finish();
            }
        });


    }
}
