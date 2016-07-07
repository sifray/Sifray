package com.develop.p2s.sifray;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class TestGetdata extends ListActivity {

    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> productsList;

    // url to get all products list
    private static String url_all_products = "http://jtp-guide.esy.es/api";

    // JSON Node names
    private static final String TAG_SUCCESS = "id";
    private static final String TAG_PRODUCTS = "nama";
    private static final String TAG_PID = "id";
    private static final String TAG_NAME = "nama";

    private static final String TAG_ID = "id";
    private static final String TAG_NAMA = "nama";
    private static final String TAG_LaT = "longtitude";
    private static final String TAG_LoT = "latitude";
    private static final String TAG_DES = "deskripsi";
    private static final String TAG_UC = "user_create";
    private static final String TAG_TC = "tgl_create";
    private static final String TAG_UU = "user_update";
    private static final String TAG_TU = "tgl_update";
    // products JSONArray
    JSONArray products = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_getdata);
        // Hashmap for ListView
        //productsList = new ArrayList<HashMap<String, String, String, String, String, String, String, String, String>>();
        productsList = new ArrayList<HashMap<String, String>>();

        // Loading products in Background Thread
        new LoadAllProducts().execute();

        // Get listview
        ListView lv = getListView();

        // on seleting single product
        // launching Edit Product Screen
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
//                String pid = ((TextView) view.findViewById(R.id.pid)).getText()
//                        .toString();

                // Starting new intent
//                Intent in = new Intent(getApplicationContext(),
//                        EditProductActivity.class);
                // sending pid to next activity
//                in.putExtra(TAG_PID, pid);
//
//                // starting new activity and expecting some response back
//                startActivityForResult(in, 100);
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    // Response from Edit Product Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if result code 100
        if (resultCode == 100) {
            // if result code 100 is received
            // means user edited/deleted product
            // reload this screen again
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "TestGetdata Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.

        Uri.parse("http://jtp-guide.esy.es/api"),
//                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.develop.p2s.sifray/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "TestGetdata Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.develop.p2s.sifray/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    /**
     * Background Async Task to Load all product by making HTTP Request
     */
    class LoadAllProducts extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(TestGetdata.this);
            pDialog.setMessage("Loading products. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All products from url
         */
        String nama, lot, lat, des, uc, tc, uu, tu;
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);


            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());

            try {
                Log.d("MASOK: ", json.toString());
                //test
                nama = json.getString(TAG_NAMA);
                Log.d("MASOK nama: ", nama+"");
                lot = json.getString(TAG_LoT);
                Log.d("MASOK longtitude: ", lot+"");
                lat = json.getString(TAG_LaT);
                Log.d("MASOK latitude: ", lat+"");
                des = json.getString(TAG_DES);
                Log.d("MASOK deskripsi: ", des+"");
                uc = json.getString(TAG_UC);
                Log.d("MASOK user_create: ", uc+"");
                tc = json.getString(TAG_TC);
                Log.d("MASOK tgl_create: ", tc+"");
                uu = json.getString(TAG_UU);
                Log.d("MASOK user_update: ", uu+"");
                tu = json.getString(TAG_TU);
                Log.d("MASOK tgl_update: ", tu+"");
                //test
//                // Checking for SUCCESS TAG
//                int success = json.getInt(TAG_SUCCESS);
//                Log.d("MASOK: 1", success+"");


                int success = 1;
                if (success == 1) {
//                    Log.d("MASOK: ", json.toString());
                    // products found
                    // Getting Array of Products
                    //products = json.getJSONArray(TAG_PRODUCTS);
                    //products = json.getJSONArray(TAG_PRODUCTS);

                    // looping through All Products
//                    for (int i = 0; i < products.length(); i++) {
//                        JSONObject c = products.getJSONObject(i);
//
//                        // Storing each json item in variable
//                        String id = c.getString(TAG_PID);
//                        String name = c.getString(TAG_NAME);
//
//                        // creating new HashMap
                        //HashMap<String, String, String, String, String, String, String, String, String> map = new HashMap<String, String, String, String, String, String, String, String, String>();
                    HashMap<String, String> map = new HashMap<String, String>();
//                        // adding each child node to HashMap key => value
                        map.put(TAG_NAMA, nama+"");
                        map.put(TAG_DES, des+"");
//
//                        // adding HashList to ArrayList
                        productsList.add(map);
//                    }
                } else {
                    // no products found
                    // Launch Add New product Activity
//                    Intent i = new Intent(getApplicationContext(),
//                            NewProductActivity.class);
//                    // Closing all previous activities
//                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    String tes = "tes";
                    Log.d("MASOK : 1", tes);
                    ListAdapter adapter = new SimpleAdapter(
                            TestGetdata.this, productsList,
                            R.layout.list_item, new String[]{TAG_NAMA, lot, lat, TAG_DES,
                                uc, tc, uu, tu},
                            new int[]{R.id.nama,R.id.longitude,R.id.latitude,
                                    R.id.deskripsi,R.id.userCreate,R.id.tglCreate,
                                    R.id.userUpdate,R.id.tglUpdate});
                    Log.d("MASOK : 2", tes);
                    // updating listview
                    setListAdapter(adapter);
                    Log.d("MASOK : 3", tes);
                }
            });

        }

    }
}
