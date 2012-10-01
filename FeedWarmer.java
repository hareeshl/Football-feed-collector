import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.XmlReader;


public class FeedWarmer {

	private SyndFeedInput input;
	private SyndFeedOutput output;
	private URL feedUrl;
	
	public FeedWarmer(String url) {

		input = new SyndFeedInput();
		output = new SyndFeedOutput();
		
		try {
			feedUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<FeedData> warmFeed(){
		
		List<FeedData> feedList = new ArrayList<FeedData>();
		
		try {
			SyndFeed feed = input.build(new XmlReader(feedUrl));
			@SuppressWarnings("unused")
			List entryList = feed.getEntries();
			
			for(Object entryItem: entryList){
				
				SyndEntry syndEntry = (SyndEntry)entryItem;

				FeedData data = new FeedData();
				data.setDescription(syndEntry.getDescription().getValue());
				data.setLink(syndEntry.getLink());
				
				feedList.add(data);
							

			}
			
			
						
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FeedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return feedList;
		
	}
	
	public String getFeedContent(List<FeedData> dataList){
		
		String mailContent = "";
		
		for(FeedData feedDescription : dataList){
			
			mailContent += "<p>";
			mailContent += feedDescription.getDescription();
			mailContent += "<br/>";
			mailContent += "<b><u>Link : </u></b><a href=\""+ feedDescription.getLink() +"\">Click to read more</a>";
			mailContent += "</p><br/>";
			
		}
		
		return mailContent;
		
		
	}
	
	
	public static void main(String[] args){
		
		new FeedWarmer("http://www.dailymail.co.uk/sport/index.rss").warmFeed();
		
		System.out.println("end");
		
	}
	
}
