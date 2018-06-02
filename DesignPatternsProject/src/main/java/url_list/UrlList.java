package url_list;

import java.util.ArrayList;
import java.util.List;

public class UrlList {

	List<String> urls = new ArrayList<String>();
	
	public void addUrl(String url) {
		urls.add(url);
	}
	
	@Override
	public String toString() {
		String ans = "";
		for (String string : urls) {
			ans += string + "\n";
		}
		return ans;
	}
}
