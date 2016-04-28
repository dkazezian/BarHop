package cs464.barhop;
import java.io.*;
import java.util.Scanner;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
//Example found here: http://www.tutorialspoint.com/android/android_spinner_control.htm
public class Search extends Activity {
    EditText etext;
    Spinner spinner;
    String selection;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        etext = (EditText) findViewById(R.id.editText);
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.day_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                selection = spinner.getSelectedItem().toString();
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position)+" selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){
                selection="-1";
            }
        });

    }
    public void searchalgorithm(String day){
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