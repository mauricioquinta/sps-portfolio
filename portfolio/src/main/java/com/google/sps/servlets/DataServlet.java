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
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
// The link inside webservelet determines the URL that loads what data




/**--------------------------------------------allows for comment implememntation----------------------------*/
@WebServlet("/data")
public class DataServlet extends HttpServlet {


    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

	// Use query to access data in datastore 
	Query query = new Query("Comment");
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	PreparedQuery results = datastore.prepare(query);

	// List of comments to be converted to JSON object 
	List<Comment> cList = new ArrayList<>();

	// Parse entitys in datastore ie comments 
	for (Entity entity : results.asIterable()) {

	    // Retrive information from dtastore 
	    long id = entity.getKey().getId();
	    String user = (String) entity.getProperty("user");
	    String comment = (String) entity.getProperty("comment");

	    // Create comment element and append to list
	    Comment nc = new Comment(comment, user);
	    cList.add(nc);
	
	}

	// Convert to JSON and send to function call
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

	// Create comment from content
	Comment nc = new Comment(comment, fName, lName);

	// Create entity equivalent to comment object 
	Entity commentEnt = new Entity("Comment");
	commentEnt.setProperty("user", nc.getUser());
	commentEnt.setProperty("comment", nc.getMessage());

	// Adding to database
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	datastore.put(commentEnt);
	    
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
	json += "\"" + c.getMessage();
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


