package com.ecs.sample;

import java.util.List;

import twitter4j.Status;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HomeTimelineAdapter extends ArrayAdapter<Status> {

        private LayoutInflater inflater;
        private int tvrId;
        private List<Status> listItems;

        public HomeTimelineAdapter(Context context, int textViewResourceId, List<Status> objects) {
                super(context, textViewResourceId, objects);
                inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                tvrId = textViewResourceId;
                listItems = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
                try {
                        ViewHolder holder;
                        Status item = listItems.get(position);
                        if(convertView == null) {
                                convertView = inflater.inflate(tvrId, null);
                                
                                holder = new ViewHolder();
                                holder.text = (TextView)convertView.findViewById(R.id.user_text);
                                
                                convertView.setTag(holder);
                        } else {
                                holder = (ViewHolder)convertView.getTag();
                        }
                        
                        holder.text.setText(item.getText());
                        
                } catch(Exception e) {
                        e.printStackTrace();
                }
                return convertView;
        }
        
        private static class ViewHolder {

                public TextView text;
                
        }
        
}