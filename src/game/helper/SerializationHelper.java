package game.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.thoughtworks.xstream.XStream;

public class SerializationHelper {

	public static void writeObjectToXml(Object obj, String filename){
		XStream xstream = new XStream();
		
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		xstream.toXML(obj, stream);
	}
	
	public static Object readObjectFromXml(String filename){
		XStream xstream = new XStream();
		
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			return null;
		}
		
		return xstream.fromXML(stream);
	}
}
