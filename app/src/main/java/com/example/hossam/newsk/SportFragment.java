package com.example.hossam.newsk;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Hossam on 5/7/2017.
 */

public class SportFragment extends Fragment {
    String url;
    JSONObject jsono;
    Listitem item;
    ArrayList<Listitem> arrayitems;
    GridView gridView;
    NewsAdapter newsAdapter;
    ProgressDialog progressDialog;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.sport_fragment, container, false);

        gridView = (GridView) rootView.findViewById(R.id.gridView2);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout2);
        arrayitems = new ArrayList<Listitem>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getInstance().getReference().child("sport");
        myRef.keepSynced(true);
        myRef.removeValue();
        url = "https://api.myjson.com/bins/e69s9";
        StringRequest postrequst = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jsono = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    JSONArray jarray = jsono.getJSONArray("results");
                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject object = jarray.getJSONObject(i);
                        item = new Listitem();
                        try {
                            item.setAuthor(object.getString("author"));
                            item.setTitle(object.getString("title"));
                            item.setDescription(object.getString("description"));
                            item.setUrlToImage(object.getString("urlToImage"));
                            item.setPublishedAt(object.getString("publishedAt"));
                            item.setSource(object.getString("source"));
                            item.setUrl(object.getString("url"));
                            item.setId(object.getInt("id"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        arrayitems.add(item);
                        myRef.push().setValue(item);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                newsAdapter = new NewsAdapter(getActivity(), R.layout.item, arrayitems);
                gridView.setAdapter(newsAdapter);
                progressDialog.dismiss();
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Snackbar.make(rootView, getResources().getString(R.string.Snackbar), Snackbar.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(getActivity()).add(postrequst);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getResources().getString(R.string.Dialog));
        progressDialog.show();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                newsAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        return rootView;
    }
}
