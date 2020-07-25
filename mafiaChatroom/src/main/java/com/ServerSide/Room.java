package com.ServerSide;

import java.util.TreeSet;

class Room implements Comparable<Room>{
     protected long uCode;
     protected TreeSet<String> urls;
     @SuppressWarnings("unchecked")
	public Room(long uCode,TreeSet< String > s) {
    	 urls=(TreeSet<String>) s.clone();
    	 this.uCode=uCode;
     }
     public Room(long uCode) {
    	 urls=new TreeSet<String>();
    	 this.uCode=uCode;
     }
     protected void setuCode(long arg) {
    	 this.uCode=arg;
     }
     protected void addURL(String s) {
    	 urls.add(s);
     }
	public int compareTo(Room o) {
		return Long.compare(uCode, o.uCode);
	}
}
