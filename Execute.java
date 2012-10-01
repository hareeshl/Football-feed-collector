import java.util.List;

public class Execute {

	public static void main(String[] args){
		
		FeedWarmer feed = new FeedWarmer("http://www.dailymail.co.uk/sport/index.rss");
		
		List<FeedData> dataList = feed.warmFeed();
		
		String mailContent = feed.getFeedContent(dataList);
				
		dataList = new FeedWarmer("http://www.goal.com/en-india/feeds/news?fmt=atom").warmFeed();
		
		mailContent += "<b>GOAL.COM</b><br/>";
		
		mailContent += feed.getFeedContent(dataList);
		
		SendMail mailRef = new SendMail();
		mailRef.setMailContent(mailContent);
		
		mailRef.sendMail();
		
	}
	
}
