package masterarbeit.com.androidclienttest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ProgressDialog prgDialog;
    String datum = "2011-01-01";
    String uhrzeit = "13:37";
    String wartezeit = "12";
    String uhrzeitAnfang = "11:00:00";
    String uhrzeitEnde = "14:00:00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prgDialog = new ProgressDialog(this);
        prgDialog.setMessage("Bitte warten...");
        prgDialog.setCancelable(false);
    }

    public void Update(View view) {
        RequestParams params = new RequestParams();
        params.put("datum", datum);
        params.put("uhrzeit", uhrzeit);
        params.put("wartezeit", wartezeit);
        update(params);
    }


    public void update(RequestParams params){
        prgDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://192.168.2.111:8080/wartezeiten/update/doupdate", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(String response) {
                prgDialog.hide();
                try {
                    JSONObject obj = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), obj.getString("antwort"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                prgDialog.hide();
                Toast.makeText(getApplicationContext(), "Unexpected Error occcured!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void Select(View view) {
        RequestParams params = new RequestParams();
        params.put("datum", datum);
        select(params);
    }

    public void select(RequestParams params){
        prgDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://192.168.2.111:8080/wartezeiten/select/doselect", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(String response) {
                // Hide Progress Dialog
                prgDialog.hide();

                try {
                    JSONObject obj = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), obj.getString("13:37:00"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {

                prgDialog.hide();
                Toast.makeText(getApplicationContext(), "Unexpected Error occcured!", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void Vorschlag(View view) {
        RequestParams params = new RequestParams();
        params.put("datum", datum);
        params.put("uhrzeitAnfang", uhrzeitAnfang);
        params.put("uhrzeitEnde", uhrzeitEnde);
        vorschlag(params);
    }

    public void vorschlag(RequestParams params){
        prgDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://192.168.2.111:8080/wartezeiten/vorschlag/dovorschlag", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(String response) {
                prgDialog.hide();

                try {
                    JSONObject obj = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), obj.getString("uhrzeit") + " " + obj.getString("wartezeit"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                prgDialog.hide();
                Toast.makeText(getApplicationContext(), "Unexpected Error occcured!", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void VorschlagAnnahme(View view) {
        RequestParams params = new RequestParams();
        params.put("datum", datum);
        params.put("uhrzeitAnfang", uhrzeitAnfang);
        params.put("uhrzeitEnde", uhrzeitEnde);
        vorschlagAnnahme(params);
    }

    public void vorschlagAnnahme(RequestParams params){
        prgDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://192.168.2.111:8080/wartezeiten/vorschlagannahme/dovorschlagannahme", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(String response) {
                prgDialog.hide();

                try {
                    JSONObject obj = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), obj.getString("antwort"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                prgDialog.hide();
                Toast.makeText(getApplicationContext(), "Unexpected Error occcured!", Toast.LENGTH_LONG).show();
            }
        });
    }








}