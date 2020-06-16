package com.google.sps.data;


public final class Comment {
    
    String messege;
    String user; 
    
    

    public Comment(String m) {
	messege = m;
	user = "anon";
    }

    public Comment(String m, String u){
	messege = m;
	user = u;
    }

    public String getMessege(){
	return messege;
    }

    public String getUser(){
	return user;
    }
}

