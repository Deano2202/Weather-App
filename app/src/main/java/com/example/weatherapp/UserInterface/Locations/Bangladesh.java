package com.example.weatherapp.UserInterface.Locations;
//s2026679 - Dean Mcardle
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.weatherapp.Adapters.WeatherAdapter;
import com.example.weatherapp.Models.WeatherModel;
import com.example.weatherapp.R;

import org.jsoup.Jsoup;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Bangladesh extends Fragment {

    OkHttpClient client = new OkHttpClient();
    String BASE_URL = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/1185241",
            responseTxt ="", titleTxt = "", descriptionTxt = "", pubDateTxt = "", linkTxt = "", latLongTxt = "";

    ArrayList<WeatherModel> accidentList;
    WeatherAdapter adapter;

    RecyclerView recyclerView;
    ProgressBar progressBar ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_locations, container, false);

        recyclerView=view.findViewById(R.id.LocationsRecyclerView);
        progressBar=view.findViewById(R.id.LocationsProgressBar);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        accidentList = new ArrayList<>();

        apiRequest();

        return view;
    }

    private void apiRequest() {
        Request request = new Request.Builder()
                .url(BASE_URL)
                .build();

        client.newCall(request).enqueue(new Callback(){

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                responseTxt = response.body().string();
                fetchItems();
                if(accidentList!=null)
                    setValuesOnAdapter();
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("error", e.toString());
            }
        });
    }

    private void setValuesOnAdapter() {
        if (getActivity() == null)
            return;

        getActivity().runOnUiThread(() -> {
            adapter = new WeatherAdapter(accidentList,getActivity());
            if (adapter.getItemCount() > 0) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setAdapter(adapter);
                //emptyList.setVisibility(View.GONE);
            } else {
                recyclerView.setVisibility(View.GONE);
                //emptyList.setVisibility(View.VISIBLE);
            }
        });
    }

    private void fetchItems() {
        try {

            boolean foundItem = false;
            String tagValue = "";

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput( new StringReader ( responseTxt ) );
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xpp.getName();
                if(eventType == XmlPullParser.START_DOCUMENT) {

                } else if(eventType == XmlPullParser.START_TAG) {
                    if(tagName.equalsIgnoreCase("item"))
                        foundItem = true;
                } else if(eventType == XmlPullParser.TEXT) {
                    tagValue = xpp.getText();
                } else if(eventType == XmlPullParser.END_TAG) {
                    if(tagName.equalsIgnoreCase("item")) {
                        foundItem = false;
                        accidentList.add(new WeatherModel(titleTxt,descriptionTxt,pubDateTxt,latLongTxt,linkTxt));
                    } else if (foundItem && tagName.equals("title")) {
                        titleTxt = tagValue;
                    } else if (foundItem && tagName.contains("description")) {
                        descriptionTxt = Jsoup.parse(tagValue).text();
                    } else if (foundItem && tagName.equals("link")) {
                        linkTxt = tagValue;
                    } else if (foundItem && tagName.contains("point")) {
                        latLongTxt = tagValue;
                    } else if (foundItem && tagName.equals("pubDate")) {
                        pubDateTxt = tagValue;
                    }
                }
                eventType = xpp.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}