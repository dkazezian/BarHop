package cs464.barhop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by smann17 on 4/29/16.
 */

public class FavoritesAdapter extends ArrayAdapter<Favorite> {
    private List<Favorite> favoriteList;
    private Context context;

    public FavoritesAdapter(List<Favorite> favoriteList, Context context) {
        super(context, R.layout.single_listview_item, favoriteList);
        this.favoriteList= favoriteList;
        this.context = context;
    }

    private static class FavoriteHolder{
        public TextView favoriteName;
        public CheckBox chkBox;
    }
    /*@Override
    public View getView(int position, View convertView, ViewGroup parent){

        View v = convertView;

        FavoriteHolder holder = new FavoriteHolder();
        if(convertView == null){
            LayoutInflater inflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflator.inflate(R.layout.single_listview_item,null);

            holder.favoriteName = (TextView) v.findViewById(R.id.name);
            holder.chkBox = (CheckBox)v.findViewById((R.id.chk_box));

            holder.chkBox.setOnCheckedChangeListener((Favorites)context);
        }else{
            holder = (FavoriteHolder) v.getTag();
        }
        Favorite p = favoriteList.get(position);
        holder.favoriteName.setText(p.getName());
        holder.chkBox.setChecked(p.isSelected());
        holder.chkBox.setTag(p);
        return v;

    }*/
}
