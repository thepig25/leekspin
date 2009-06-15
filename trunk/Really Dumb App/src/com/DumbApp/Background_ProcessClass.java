package com.DumbApp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.List;

import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.message.BasicNameValuePair;
import org.xml.sax.XMLReader;

import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.Html.TagHandler;




public class Background_ProcessClass  {

	private static final TagHandler TagHandlerMe = null;

	/**
	 * @param args
	 * @throws IOException 
	 */

	
public void backGround_process() throws IOException{



 
		ClientHttpRequest test = null;
		InputStream t=null;
		
 try {
	//test = new ClientHttpRequest("http://www.irishrail.ie/realtime/index.asp");
	test = new ClientHttpRequest("http://irishrail.ie/realtime/index.asp?action=search&direction=A&mins=30&station_name=DKDN,DKUP~Dalkey");

	
	t = test.post();
	
	
 } catch (SocketException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
 for (double i=0;i<1.5;){
		i=i+0.001;
		System.out.println(i);
	}
 String temp = readAll(t);
 
 
	 
 
 Spanned tds =   Html.fromHtml(temp,null,TagHandlerMe);
 
	 
 

 //System.out.println(tds);
 String result = "";
 String temmp = tds.toString();
 String temmp2 = stripPunctuation(temmp);
 temmp = temmp2;
 
 StringBuffer yak = new StringBuffer(temmp);
 String [] possibles = {"Dalkey", "Killiney", "to", "howth",":", "<td>Howth to Greystones</td>"};
 Scanner s = new Scanner(temmp).useDelimiter("[￼]//n//r");
 while(s.hasNext()){
	 String sTemp = s.next();
	 System.out.println(sTemp);
	 if(sTemp.equalsIgnoreCase("￼"))
	 {
		 System.out.println("hit");
	 }
	 /*for(int i=0;i<possibles.length;i++){
		 if(sTemp.equalsIgnoreCase(possibles[i])){
			 System.out.println("hit");
			 result = result + sTemp;
		 }

	 }*/
	/*if(sTemp.equalsIgnoreCase("<table>")){
		s.close();
	}*/
	
	 
	 
 }
 
 System.out.println(result);
}
 public String readAll(InputStream inputStream) {
	 ByteArrayOutputStream bout = new ByteArrayOutputStream();
	 byte [] buffer = new byte[1024];
	 while(true) {
	 int len = 0;
	try {
		len = inputStream.read(buffer);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 if(len < 0) {
	 break;
	 }
	 bout.write(buffer, 0, len);
	 }
	 byte [] data = bout.toByteArray();
	 return new String(data);
	 }
 	
	// String a = readAll();
 /*catch (SocketException e1){
	 
 }*/




 public String stripPunctuation(String s) {

	 StringBuffer sb = new StringBuffer();

	 for (int i = 0; i < s.length(); i++) {
	 if ((s.charAt(i) >= 65 && s.charAt(i) <= 90) || (s.charAt(i) >= 97 &&
	 s.charAt(i) <= 122)||s.charAt(i)==32||(s.charAt(i) >= 48 &&
	 s.charAt(i)<= 58)) {

	 sb = sb.append(s.charAt(i));
	 }
	 }

	 return sb.toString();
	 }
 
 
	
}

