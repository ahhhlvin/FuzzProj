package ahhhlvin.c4q.nyc.fuzzproj;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static String url = "http://quizzes.fuzzstaging.com/quizzes/mobile/1/data.json";
    JSONArray fuzzArray = null;
    ArrayList<FuzzItem> fuzzItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fuzzItems = new ArrayList<>();

        new GetData().execute();


        // TEST METHOD
        for (int i = 0; i < fuzzItems.size(); i++) {
            Log.i("ALVIN", fuzzItems.get(i).getData());
        }


    }


    private class GetData extends AsyncTask<Void, Void, ArrayList<FuzzItem>> {

        OkHttpClient client = new OkHttpClient();

        String run(String jsonUrl) throws IOException {
            Request request = new Request.Builder().url(jsonUrl).build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }


        @Override
        protected ArrayList<FuzzItem> doInBackground(Void... arg0) {

            try {
                fuzzArray = new JSONArray(run(url));

                for (int i = 0; i < fuzzArray.length(); i++) {
                    JSONObject fuzzObj = fuzzArray.getJSONObject(i);


                    FuzzItem fuzzItemNew = new FuzzItem();


                    if (fuzzObj.isNull("data")) {
                        fuzzItemNew.setData("NULL");
                    } else {
                        fuzzItemNew.setData(fuzzObj.getString("data"));
                    }

                    if (fuzzObj.isNull("date")) {
                        fuzzItemNew.setDate("NULL");
                    } else {
                        fuzzItemNew.setDate(fuzzObj.getString("date"));
                    }

                    if (fuzzObj.isNull("id")) {
                        fuzzItemNew.setId("NULL");
                    } else {
                        fuzzItemNew.setId(fuzzObj.getString("id"));
                    }

                    if (fuzzObj.isNull("type")) {
                        fuzzItemNew.setType("NULL");
                    } else {
                        fuzzItemNew.setType(fuzzObj.getString("type"));
                    }


                    fuzzItems.add(fuzzItemNew);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                Log.e("ServiceHandler", "Error retrieving data from URL");
            }

            return fuzzItems;
        }


        @Override
        protected void onPostExecute(ArrayList<FuzzItem> fuzzItems) {
            super.onPostExecute(fuzzItems);

            ListView list = (ListView) findViewById(R.id.list_tv);
            FuzzAdapter adapter = new FuzzAdapter(getApplicationContext(), R.layout.list_fuzz_item, fuzzItems);
            list.setAdapter(adapter);

        }


    }
}
