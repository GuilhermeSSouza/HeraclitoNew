/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unipampa.edu.heraclito;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 *
 * @author jcgluz
 */
public class NetClientPost {
    
	public static boolean Post(String urlstr, String content) {

	  try {

		URL url = new URL(urlstr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "text/plain");

		OutputStream os = conn.getOutputStream();
		os.write(content.getBytes());
		os.flush();

		if (conn.getResponseCode() != HttpURLConnection.HTTP_ACCEPTED) {
                        return false;
		}

                conn.disconnect();

                return true;

	  } catch (MalformedURLException e) {

		e.printStackTrace();
                return false;

	  } catch (IOException e) {

		e.printStackTrace();
                return false;

	 }

	}

}
