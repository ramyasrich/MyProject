import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


package MyPackage;

public class JsonReader {
	//open and read the url file.
		private static String get_URLFile(String url){
			if (null == url | "" == url ){//check input.
				return null;
			}
			String buffer = "";//main file buffer.
		    
			try {
				byte[] b=new byte[1024];//buffer 
				int  byteread=0;//byte counter
				
				
				URL urlFile = new URL(url);
				InputStream input = urlFile.openStream();
				
				
				while ((byteread = input.read(b)) != -1){
					String reads = new String(b, 0, byteread , "UTF-8"); 
					buffer = buffer + reads;//attach to file buffer.
					}
				
				input.close();
				return buffer; 
			} catch (IOException e) {
				return null;
			}
			
		}
		
		public static void main(String[] args) {
			
			int index 		= 1;
			int hd_flags_true 	= 0;
			int hd_flags_false 	= 0;
		    String urlPath = "http://api.viki.io/v4/videos.json?app=100250a&per_page=10&page=";
		    boolean more = true;
		    while(more == true){
		    	
		    	String urlfile = "";
		    	urlfile = get_URLFile(urlPath + index++);
		    	if ( null == urlfile){
		    		more = false;
		    	}
		    	else{
		    		JSONObject jsonObj= new JSONObject(urlfile);
		    		more = jsonObj.getBoolean("more");
		    		if(more == true){
		    			try{
		    				JSONArray jsonResponse = jsonObj.getJSONArray("response");
		    				boolean get_hd = false;
		    				for(int i = 0;i<jsonResponse.length() ;i++){
		    					JSONObject item = jsonResponse.getJSONObject(i);
		    					if (item.has("flags")){
		    						get_hd = true;
		    						boolean hd = item.getJSONObject("flags").getBoolean("hd");
		    						if( hd ){
		    	    					hd_flags_true++;
		    	    				}
		    	    				else
		    	    				{
		    	    					hd_flags_false++;
		    	    				}
		    					}
		    				}
		    				if(false == get_hd){
		    					throw new JSONException(" flags section lost! ") ;
		    				}
		    				
		    			}
		    			catch(JSONException e)
		    			{
		    				System.out.println(e);
		    				more = false;
		    			}
		    		}
		    	}
		    }
		    System.out.println("Total " + hd_flags_true +"'hd'flag mark as true." );
		    System.out.println("Total " + hd_flags_false +"'hd'flag mark as false." );
		}

}
