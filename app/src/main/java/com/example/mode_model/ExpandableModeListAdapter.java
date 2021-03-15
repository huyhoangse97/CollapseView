package com.example.mode_model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.collapseview.R;

public class ExpandableModeListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private com.example.mode_model.ModeContainer modeContainer;

    public ExpandableModeListAdapter(Context context, com.example.mode_model.ModeContainer modeContainer) {
        this.context = context;
        this.modeContainer = modeContainer;
    }

    @Override
    public int getGroupCount() {
        return modeContainer.getSizeOfModes();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return modeContainer.getModesCount(groupPosition);
    }

    @Override
    public Object getGroup(int groupPosition) {
        return modeContainer.getMode(groupPosition);
    }

    @Override
    public Object getChild(int groupIndex, int childIndex) {
        return modeContainer.getChildrenByIndex(groupIndex, childIndex);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View convertView, ViewGroup viewGroup) {
        final String modeName = ((com.example.mode_model.Mode)getGroup(groupPosition)).getName();

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.mode_list, null);
        }

        int imageId = context.getResources().getIdentifier(modeName, "drawable", (context).getPackageName());
//        ImageButton ib_mode = (ImageButton)convertView.findViewById(R.id.ib_mode);
//        ib_mode.setBackgroundResource(imageId);

        ImageView tv_mode = convertView.findViewById(R.id.ib_mode);
        tv_mode.setBackgroundResource(imageId);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View convertView, ViewGroup viewGroup) {
        final String roundName = ((com.example.mode_model.Round) getChild(groupPosition, childPosition)).getName();
        int roundStars = ((com.example.mode_model.Round) getChild(groupPosition, childPosition)).getStar();
        String imageName = roundName + "_" + Integer.toString(roundStars) +  "star";


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.round_list, null);
        }

        ImageView tv_round = convertView.findViewById(R.id.ib_round);
        int imageId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        if (imageId != 0){
            tv_round.setBackgroundResource(imageId);
        }
        else {
            tv_round.setBackgroundResource(R.drawable.unfound);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
