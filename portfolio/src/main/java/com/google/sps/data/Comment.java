package com.google.sps.data;


public final class Comment {
    
    String message;
    String user; 
    
    

    public Comment(String m) {
	message = m;
	user = "anon";
    }

    public Comment(String m, String u){
	message = m;
	user = u;
    }

    public Comment(String m, String f, String l){
	message = m;
	String lwSpcace = " ".concat(l);
	user = f.concat(lwSpcace);
    }

    public String getMessage(){
	return message;
    }

    public String getUser(){
	return user;
    }
}

