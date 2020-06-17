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

import java.util.*;
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


    List<Comment> cList = new ArrayList<Comment>();
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

	String json = convertToJson(cList);
	response.setContentType("application/json;");
	response.getWriter().println(json);
	
    }




    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
	
	// Get the input from the form.
	String fName = request.getParameter("fName");
	String lName = request.getParameter("lName");
	String comment = request.getParameter("comment");

	//create comment from content and add it to list 
	Comment nc = new Comment(comment, fName, lName); 
	cList.add(nc);
	    
	// Redirect back to the HTML page.
	response.sendRedirect("/index.html");
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
    private String convertToJson(List<Comment> list) {
	Gson gson = new Gson();
	String json = gson.toJson(list);
	return json;
    }
}


