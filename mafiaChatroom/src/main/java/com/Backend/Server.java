package com.Backend;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.TreeSet;

import com.sun.net.httpserver.HttpServer;
import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class Server {
     @SuppressWarnings("restriction")
     protected static TreeSet<Room> rooms;
	public static void main(String[] args) {
    	 try {
    		 rooms=new TreeSet<Room>();
    		 //Load Rooms from startup.json
			HttpServer server=HttpServer.create(new InetSocketAddress(8000), 100);
			server.createContext("/newRoom",new RoomCreator());
			server.createContext("/deleteRoom",new RoomDestroyer());
			server.createContext("/newMessage",new MessageReciever());
			server.createContext("/deleteMessage",new MessageDestroyer());
			
		} catch (IOException e) {
			
		}
    	 
     }
	@SuppressWarnings("restriction")
	static class RoomCreator implements HttpHandler{
    	 public void handle(HttpExchange t) throws IOException {
    		Gson gson=new Gson();
    		int rcode=200;
    		String message="Accepted";
    		try
    		{
				String json=new String(ByteStreams.toByteArray(t.getRequestBody()));
				@SuppressWarnings("unchecked")
				Map<String,String > map=gson.fromJson(json, Map.class);
				Room r1=new Room((long)(Math.random()*1e18));
				while(!rooms.contains(r1)) {
					r1.setjCode((long)(Math.random()*1e18));
				}
				rooms.add(r1);
				rcode=200;
				message="Room Create. Enter code "+r1.jCode+" to enter room.";
    		}
    		catch(IOException e) {
    			rcode=400;
    			message="Rejected";
    		}
    		catch(JsonSyntaxException e) {
                rcode=400;    		
                message="Rejected";
    		}
			catch(RuntimeException e) {
			}
    		t.getResponseHeaders().add("Content-Type","application/text-plain");
    		t.sendResponseHeaders(rcode, message.length());
    		OutputStream os=t.getResponseBody();
    		os.write(message.getBytes("UTF-8"));
    		os.close();
    		System.out.println("Room Created");
		}
     }
}
