package com.Backend;

import java.util.TreeSet;

class Room implements Comparable<Room>{
     protected long jCode;
     protected TreeSet<String> urls;
     @SuppressWarnings("unchecked")
	public Room(long jCode,TreeSet< String > s) {
    	 urls=(TreeSet<String>) s.clone();
    	 this.jCode=jCode;
     }
     public Room(long jCode) {
    	 urls=new TreeSet<String>();
    	 this.jCode=jCode;
     }
     protected void setjCode(long arg) {
    	 this.jCode=arg;
     }
     protected void addURL(String s) {
    	 urls.add(s);
     }
	public int compareTo(Room o) {
		return Long.compare(jCode, o.jCode);
	}
     
}
