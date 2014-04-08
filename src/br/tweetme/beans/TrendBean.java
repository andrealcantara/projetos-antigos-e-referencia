package br.tweetme.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.tweetme.controllers.PostController;

@ManagedBean(name = "trendBean")
@SessionScoped
public class TrendBean {

	public final static int TM_TRENDS = 5;
	protected static int count = TM_TRENDS;
	private List<String> topics;

	public void setTopics(List<String> topics) {
		this.topics = topics;
	}

	public List<String> getTopics() {

		if (count++ < TM_TRENDS) {
			count = 0;
			return topics ;
		}
		
		PostController pc = new PostController();
		
		topics = pc.trendtopics();
		
		List<String> ts = new ArrayList<String>();
		
		int i = 0;
		
		for (String post : topics) {
			if (i > 3) break;
			System.out.println(post);
			ts.add(post);
			i++;
		}

		return ts;
	}
}
