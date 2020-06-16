// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.sps.data.Comment;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
//the link inside webservelet determines the URL that loads what data





@WebServlet("/data")
public class DataServlet extends HttpServlet {


    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
	//String name = "Mau's Portfolio!"; 
	//response.setContentType("text/html;");
	//response.getWriter().println(name);

	// Convert the server stats to JSON
	Comment c1 = new Comment("i like this thing ");
	Comment c2 = new Comment("i dont like this thing ");
	Comment c3 = new Comment("i am indifferent about  this thing ");

	//String c1s = "i like this thing "; //convertToJson(c1);
	//String c2s = "i dont like this thing "; // convertToJson(c2);
	//String c3s = "i am indifferent about this thing "; //convertToJson(c3);
	
	Comment[] cList = {c1,c2,c3};
	String json = convertToJson(cList);
	    

	// Send the JSON as the response
	response.setContentType("application/json;");
	response.getWriter().println(json);


	
    }




    //-------------------------------------------------JSON CONVERTERS-------------------------------
    /**
     * Converts a ServerStats instance into a JSON string using manual String concatentation.
     */
    private String convertToJson(Comment c) {
	String json = "{";
	json += "\"username\": ";
	json += "\"" + c.getUser() + "\"";
	json += ", ";
	json += "\"messege\": ";
	json += "\"" + c.getMessege();
	json += "}";
	return json;
    }


  

    /**
     * Converts a ServerStats instance into a JSON string using the Gson library. Note: We first added
     * the Gson library dependency to pom.xml.
     */
    private String convertToJson(Comment[] list) {
	Gson gson = new Gson();
	String json = gson.toJson(list);
	return json;
    }
}


