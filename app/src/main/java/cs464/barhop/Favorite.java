package cs464.barhop;

/**
 * Created by nickzeus on 5/3/16.
 */
public class Favorite {
    public String Name;
    public int LineNum;
    boolean selected= false;



    Favorite(String name, int line){
        super();
        Name = name;
        LineNum = line;

    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getLineNum() {
        return LineNum;
    }

    public void setLineNum(int lineNum) {
        LineNum = lineNum;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}