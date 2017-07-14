package com.example.hossam.newsk.widget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.hossam.newsk.Listitem;
import com.example.hossam.newsk.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Hossam on 5/2/2017.
 */

public class NewsWidgetRemoteViewsService extends RemoteViewsService {
    public ArrayList<Listitem> arrayitems;
    Listitem list;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getInstance().getReference().child("result");

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            @Override
            public void onCreate() {
                arrayitems=new ArrayList<Listitem>();
            }

            @Override
            public void onDataSetChanged() {

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            list = ds.getValue(Listitem.class);
                            arrayitems.add(list);
                        }

                        //Log.d(TAG, "Value is: " + value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        // Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            }

            @Override
            public void onDestroy() {

            }

            @Override
            public int getCount() {
                return arrayitems == null ? 0 : arrayitems.size();

            }

            @Override
            public RemoteViews getViewAt(int position) {
                RemoteViews views = new RemoteViews(getPackageName(), R.layout.list_widget);
                String title = list.getTitle();
                views.setTextViewText(R.id.textView, title);

                return views;
            }

            @Override
            public RemoteViews getLoadingView() {
                return null;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                return arrayitems.get(position).getId();

            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };

    }
}
