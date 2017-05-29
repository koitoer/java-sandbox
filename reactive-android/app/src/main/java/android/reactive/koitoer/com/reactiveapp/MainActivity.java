package android.reactive.koitoer.com.reactiveapp;

import android.nfc.Tag;
import android.os.Bundle;
import android.reactive.koitoer.com.reactiveapp.api.MeetupApi;
import android.reactive.koitoer.com.reactiveapp.dto.Cities;
import android.reactive.koitoer.com.reactiveapp.dto.City;
import android.reactive.koitoer.com.reactiveapp.service.GeoNameService;
import android.reactive.koitoer.com.reactiveapp.service.MeetupService;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    private Button button;
    private MeetupService meetupService;
    private GeoNameService geoNameService;
    private ListView listView;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        button = (Button)findViewById(R.id.buttonPanel);
        text = (TextView)findViewById(R.id.textView);
        meetupService = new MeetupService();
        meetupService.initApi();
        geoNameService =  new GeoNameService();
        geoNameService.initApi();


        button.setOnClickListener(view -> meetupService.meetupApi.listCities(19.432608, -99.133209)
                .concatMapIterable(extractCities())
                .map(toCityName())
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(putLisView(), displayError()));

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            String value = (String)adapterView.getItemAtPosition(i);
            geoNameService.populationOf(value)
                    .map(x -> String.valueOf(x))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(putTextView(), displayError());

        });

    }

    private Action1<String> putTextView() {
        return integer -> text.setText("Pop: " + integer);
    }

    private Action1<Throwable> displayError() {
        return throwable -> {
            Log.e("TAG", "Error", throwable);
            Toast.makeText(MainActivity.this, "Unable to load cities", Toast.LENGTH_LONG).show();
        };
    }

    private Action1<List<String>> putLisView() {
        return cities -> listView.setAdapter(new ArrayAdapter<String>(MainActivity.
                this,android.R.layout.simple_list_item_1, cities));
    }

    private Func1<City, String> toCityName() {
        return city -> city.getCity();
    }

    private Func1<Cities, Iterable<City>> extractCities() {
        return cities -> cities.getResults();
    }



}
