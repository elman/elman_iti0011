package proovid;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
public class WriteXMLFile {
 
	public static void main(String argv[]) {
 
		Document doc =  Jsoup.parse("");
		doc.html(""); // clears html

		Element e = doc.appendElement("body").appendElement("translatoin").attr("language", "en");

	

	
		for (int i = 0; i < 10; i++) {
		    Element sub = e.appendElement("item");
		    sub.attr("number", Integer.toString(i));
		    sub.appendElement("name").text("blahh");
		}
		System.out.println(doc.children());

	}
}