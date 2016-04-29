package cs464.barhop;
import java.io.*;
import java.util.Scanner;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.content.Intent;
//Example found here: http://www.tutorialspoint.com/android/android_spinner_control.htm
public class Search extends AppCompatActivity {
    EditText etext;
    Spinner spinner;
    String selection;
    Button btn;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        etext = (EditText) findViewById(R.id.editText);
        btn = (Button) findViewById(R.id.search_button);
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.day_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        etext.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (event != null&& (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                    // NOTE: In the author's example, he uses an identifier
                    // called searchBar. If setting this code on your EditText
                    // then use v.getWindowToken() as a reference to your
                    // EditText is passed into this callback as a TextView

                    in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //userValidateEntry();
                    // Must return true here to consume event
                    return true;

                }
                return false;
            }
        });




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                selection = spinner.getSelectedItem().toString();
                //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position)+" selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){
                selection="-1";
            }
        });

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Search.this, Searchresults.class);
                ArrayList<Integer> results= searchalgorithm(selection);
                Bundle b = new Bundle();
                b.putIntegerArrayList("searchresults", results);
                intent.putExtras(b);
                Search.this.startActivity(intent);
                finish();
            }
        });




    }
    public ArrayList<Integer> searchalgorithm(String day){
        ArrayList<Integer> results=new ArrayList<Integer>();
        String filename = "barpageexample"; //Database filename
        String line = null;
        String searchstring = etext.getText().toString();
        System.out.print("The text is " + searchstring);
        String delims = "[ ]+";
        String[] tokens = searchstring.split(delims);
        for (int i = 0; i<tokens.length; i++){//Get search token so have to search database
            System.out.print("token is " + tokens[i]);
            int linecount = 0;
            int barnamelinenumber=0;
            Scanner s = null;
            Scanner s2 = null;
            try {
                s = new Scanner(new BufferedReader(new FileReader(filename)));
                while (s.hasNextLine()) {
                    String fullline=s.nextLine();
                    s2 = new Scanner(fullline);
                    barnamelinenumber=((linecount/10)*10)+1;
                    while(s2.hasNext()){
                        String word= s2.next();
                        if (tokens[i].equals(word)) {
                            if (!results.contains(barnamelinenumber)) {
                                if(day.equals("-1")) {
                                    results.add(barnamelinenumber);
                                }
                                else{
                                    int specialslinenumber=barnamelinenumber+7;
                                    int daynumber=0;
                                    switch(day) {
                                        case "Mon":
                                            daynumber=1;
                                            if (findspecialOnDay(specialslinenumber, daynumber)) results.add(barnamelinenumber);
                                            break;
                                        case "Tue":
                                            daynumber=2;
                                            if (findspecialOnDay(specialslinenumber, daynumber)) results.add(barnamelinenumber);
                                            break;
                                        case "Wed":
                                            daynumber=3;
                                            if (findspecialOnDay(specialslinenumber, daynumber)) results.add(barnamelinenumber);
                                            break;
                                        case "Thur":
                                            daynumber=4;
                                            if (findspecialOnDay(specialslinenumber, daynumber)) results.add(barnamelinenumber);
                                            break;
                                        case "Fri":
                                            daynumber=5;
                                            if (findspecialOnDay(specialslinenumber, daynumber)) results.add(barnamelinenumber);
                                            break;
                                        case "Sat":
                                            daynumber=6;
                                            if (findspecialOnDay(specialslinenumber, daynumber)) results.add(barnamelinenumber);
                                            break;
                                        case "Sun":
                                            daynumber=7;
                                            if (findspecialOnDay(specialslinenumber, daynumber)) results.add(barnamelinenumber);
                                            break;
                                    }
                                }
                            }
                        }
                    }
                    linecount++;
                }
            } catch(FileNotFoundException e){
                e.printStackTrace();
            }

        }
        return results;
    }
    private boolean findspecialOnDay(int linenumber, int day){
        boolean bool=false;
        String specialsline="";
        String filename = "barpageexample";
        Scanner s = null;
        Scanner s2=null;
        try {
            s = new Scanner(new BufferedReader(new FileReader(filename)));
            for (int i=0; i<linenumber; i++){
                s.nextLine();
            }
            specialsline=s.nextLine();
            s2 = new Scanner(specialsline).useDelimiter(";");
            for (int k = 0; k<day; k++){
                s2.next();
            }
            if (s2.next().equals("None")){
                bool = false;
            }
            else bool = true;
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return bool;
    }

    private String findBarNameByLineNumber(int linenumber){
        String barname="";
        String filename = "/BarHop/app/assets/barpageexample";
        Scanner s = null;
        try {
            s = new Scanner(new BufferedReader(new FileReader(filename)));
            for (int i=0; i<linenumber; i++){
                s.nextLine();
            }
            barname=s.nextLine();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return barname;
    }
}