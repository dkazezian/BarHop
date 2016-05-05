package cs464.barhop;

import java.util.ArrayList;
import java.util.List;
import android.app.Application;
import android.widget.CheckBox;

public class Favorite extends Application
{
    public ArrayList<String> Barnames=new ArrayList<String>();
    public ArrayList<String> LineNums=new ArrayList<String>();
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
}