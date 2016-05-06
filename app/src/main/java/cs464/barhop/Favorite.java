package cs464.barhop;

import java.util.ArrayList;
import java.util.List;
import android.app.Application;
import android.widget.CheckBox;

public class Favorite extends Application
{
    public ArrayList<String> Barnames=new ArrayList<String>();
    public ArrayList<String> LineNums=new ArrayList<String>();
    public ArrayList<Boolean> isFave=new ArrayList<Boolean>();
    public ArrayList<CheckBox> checks=new ArrayList<CheckBox>();

    public ArrayList<String> getname() {
        return Barnames;
    }
    public ArrayList<String> getlinenum() {
        return LineNums;
    }
    public ArrayList<CheckBox> getchecks() {
        return checks;
    }
    public void addbartofave(String linenum, String barname){
        if(!(Barnames.contains(barname))){
            Barnames.add(barname);
            LineNums.add(linenum);
            isFave.add(true);
        }
    }
    public void removefave(String linenum, String barname){
        if(Barnames.contains(barname)){
            isFave.remove(Barnames.indexOf(barname));
            Barnames.remove(barname);
            LineNums.remove(linenum);
        }
    }
    public Boolean isfave(String barname){
        if (Barnames.contains(barname)){
            return true;
        }
        return false;
    }

}