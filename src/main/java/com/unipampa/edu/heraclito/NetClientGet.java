/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unipampa.edu.heraclito;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author jcgluz
 */
public class NetClientGet {

	// http://localhost:8090/RESTfulExample/json/product/get
	public static String Get(String urlstr) {

	  try {

		URL url = new URL(urlstr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "text/plain");

		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        return null;
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String output="";
                String line;
		while ((line = br.readLine()) != null) {
			output += line+"\n";
		}

		conn.disconnect();
                return output;

	  } catch (MalformedURLException e) {

		e.printStackTrace();
                return null;

	  } catch (IOException e) {

		e.printStackTrace();
                return null;

	  }

	}

}

