package com.develop.p2s.sifray;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Rinov on 7/16/2016.
 */
public class AdapterLogin {
    public String login(String uname, String password)
    {

        InputStream is = null;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://jtp-guide.esy.es/api/signin");

            nameValuePairs.add(new BasicNameValuePair("username", uname));
            nameValuePairs.add(new BasicNameValuePair("password", password));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        }
        catch (Exception e)
        {
            Log.e("log_tag", "Error in http connection " + e.toString());
        }

        // convert response to string
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader( is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                sb.append(line).append("\n");
            }

            is.close();
            result = sb.toString();

            Log.v("log","Result: " + result);
        }
        catch (Exception e)
        {
            Log.v("log", "Error converting result " + e.toString());
        }
        //return "ayam";
        return result;
    }
}
