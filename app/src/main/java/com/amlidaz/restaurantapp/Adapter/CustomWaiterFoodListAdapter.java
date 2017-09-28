package com.amlidaz.restaurantapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amlidaz.restaurantapp.R;
import com.amlidaz.restaurantapp.model.FoodType;

import java.util.ArrayList;

/**
 * Created by admin on 28/09/17.
 */

public class CustomWaiterFoodListAdapter extends BaseExpandableListAdapter {


    ImageView img;
    private Context c;
    private ArrayList<FoodType> team;
    private LayoutInflater inflater;

    public CustomWaiterFoodListAdapter(Context c, ArrayList<FoodType> team) {
        this.c = c;
        this.team = team;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return team.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return team.get(i).players.size();
    }

    @Override
    public Object getGroup(int i) {
        return team.get(i);
    }

    @Override
    public String getChild(int i, int i1) {
        return team.get(i).players.get(i);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View convertView, ViewGroup viewGroup) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.waiter_item_parent, null);
        }
        //GET GROUP/TEAM ITEM
        FoodType t = (FoodType) getGroup(i);
        //SET GROUP NAME
        TextView nameTv = (TextView) convertView.findViewById(R.id.textView1);
        img = (ImageView) convertView.findViewById(R.id.imageView1);
        String name = t.Name;
        nameTv.setText(name);
        //ASSIGN TEAM IMAGES ACCORDING TO TEAM NAME
        if (name == "Man Utd") {
            img.setImageResource(R.mipmap.ic_launcher);
        } else if (name == "Chelsea") {
            img.setImageResource(R.mipmap.ic_launcher);
        } else if (name == "Arsenal") {
            img.setImageResource(R.mipmap.ic_launcher);
        }
        //SET TEAM ROW BACKGROUND COLOR
        convertView.setBackgroundColor(Color.LTGRAY);
        return convertView;


    }

    @Override
    public View getChildView(int i, int i1, boolean b, View convertView, ViewGroup viewGroup) {


        //ONLY INFLATER XML ROW LAYOUT IF ITS NOT PRESENT,OTHERWISE REUSE IT
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.waiter_items, null);
        }
        //GET CHILD/PLAYER NAME
        String child = getChild(i, i1);
        //SET CHILD NAME
        TextView nameTv = (TextView) convertView.findViewById(R.id.textView1);
        img = (ImageView) convertView.findViewById(R.id.imageItem);
        Log.e("Mytag", "child" + child);
        // nameTv.setText(child);
        //GET TEAM NAME
        String teamName = getGroup(i).toString();
        //ASSIGN IMAGES TO PLAYERS ACCORDING TO THEIR NAMES AN TEAMS
        if (teamName == "Man Utd") {
            if (child == "Wayne Rooney") {
                img.setImageResource(R.mipmap.ic_launcher);
                ;

            } else if (child == "Ander Herera") {
                img.setImageResource(R.mipmap.ic_launcher);
            } else if (child == "Van Persie") {
                img.setImageResource(R.mipmap.ic_launcher);
            } else if (child == "Juan Mata") {
                img.setImageResource(R.mipmap.ic_launcher);
            }
        } else if (teamName == "Chelsea") {
            if (child == "John Terry") {
                img.setImageResource(R.mipmap.ic_launcher);
            } else if (child == "Eden Hazard") {
                img.setImageResource(R.mipmap.ic_launcher);
            } else if (child == "Oscar") {
                img.setImageResource(R.mipmap.ic_launcher);
            } else if (child == "Diego Costa") {
                img.setImageResource(R.mipmap.ic_launcher);
            }
        } else if (teamName == "Arsenal") {
            if (child == "Jack Wilshere") {
                img.setImageResource(R.mipmap.ic_launcher);
            } else if (child == "Alexis Sanchez") {
                img.setImageResource(R.mipmap.ic_launcher);
            } else if (child == "Aaron Ramsey") {
                img.setImageResource(R.mipmap.ic_launcher);
            } else if (child == "Mesut Ozil") {
                img.setImageResource(R.mipmap.ic_launcher);

            }
        }
        return convertView;
    }

    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
