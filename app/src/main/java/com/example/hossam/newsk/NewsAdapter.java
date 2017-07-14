package com.example.hossam.newsk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Hossam on 5/7/2017.
 */

public class NewsAdapter extends BaseAdapter {
    ArrayList<Listitem> arrayitems;
    LayoutInflater vi;
    int Resource;
    ViewHolder viewHolder;
    Listitem listitem;
    Context c;

    NewsAdapter() {
    }

    public NewsAdapter(Context context, int resource, ArrayList<Listitem> objects) {
        //super(context, resource, objects);
        vi = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        arrayitems = objects;
        c = context;
    }

    @Override
    public int getCount() {
        return arrayitems.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayitems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            viewHolder = new ViewHolder();
            v = vi.inflate(Resource, null);
            viewHolder.thumbnail = (ImageView) v.findViewById(R.id.imageView);
            viewHolder.article_title1 = (TextView) v.findViewById(R.id.article_title);
            viewHolder.article_subtitle1 = (TextView) v.findViewById(R.id.article_subtitle);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }
        listitem = arrayitems.get(position);
        Glide.with(c).load(listitem.getUrlToImage()).into(viewHolder.thumbnail);
        viewHolder.article_title1.setText(arrayitems.get(position).getTitle());
        viewHolder.article_subtitle1.setText(arrayitems.get(position).getPublishedAt());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Postions) c).postion(arrayitems.get(position));
            }
        });
        return v;
    }

    public class ViewHolder {
        public ImageView thumbnail;
        public TextView article_title1;
        public TextView article_subtitle1;

    }
}
