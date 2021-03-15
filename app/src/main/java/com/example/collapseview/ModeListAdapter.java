package com.example.collapseview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;

public class ModeListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ModeContainer modeContainer;

    public ModeListAdapter(Context context, ModeContainer modeContainer) {
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
        final String modeName = ((Mode)getGroup(groupPosition)).getName();

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.mode_list, null);
        }

        int imageId = context.getResources().getIdentifier(modeName, "drawable", (context).getPackageName());
        ImageButton ib_mode = (ImageButton)convertView.findViewById(R.id.ib_mode);
        ib_mode.setBackgroundResource(imageId);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View convertView, ViewGroup viewGroup) {
        final String roundName = ((Round) getChild(groupPosition, childPosition)).getName();
        int roundStars = ((Round) getChild(groupPosition, childPosition)).getStar();
        String imageName = "round_" + Integer.toString(childPosition) + "_" + Integer.toString(roundStars)
                + "star";


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.round_list, null);
        }

        int imageId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        ImageButton ib_round = (ImageButton) convertView.findViewById(R.id.ib_round);
        ib_round.setBackgroundResource(imageId);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
