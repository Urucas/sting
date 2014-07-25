package com.urucas.sting.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.urucas.sting.R;
import com.urucas.sting.model.SlideNamespace;

import java.util.ArrayList;

/**
 * Created by Urucas on 7/23/14.
 */
public class SlideAdapter extends ArrayAdapter<SlideNamespace>{

    public SlideAdapter(Context context, int resource, ArrayList<SlideNamespace> objects) {
        super(context, resource, objects);
    }

    private static class ViewHolder {

        public TextView desc;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) this.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = convertView;
        if(rowView == null) {
            rowView = inflater.inflate(R.layout.list_item, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.desc = (TextView) rowView.findViewById(R.id.slideDesc);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        SlideNamespace nsp = getItem(position);

        holder.desc.setText(nsp.getDesc());

        return rowView;
    }

    public void addAll(ArrayList<SlideNamespace> nsp) {
        for(int i=0; i<nsp.size(); i++) {
            insert(nsp.get(i), getCount());
        }
    }

}
