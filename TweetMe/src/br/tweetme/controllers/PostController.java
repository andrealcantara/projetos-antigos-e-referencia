package br.tweetme.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

import br.tweetme.entities.Post;
import br.tweetme.factories.FactoryDAOJdbc;
import br.tweetme.persistenceDAO.FactoryDAO;
import br.tweetme.persistenceDAO.PostDAO;

public class PostController {
	protected FactoryDAO factory = null;

	protected List<String> topics = new ArrayList<String>();
	protected List<Double> values = new ArrayList<Double>();
	protected HashMap<String, List<Post>> mapTrends = new HashMap<String, List<Post>>();
	protected HashMap<String, Double> trends = new HashMap<String, Double>();

	public PostController() {
		// this.factory = new FactoryDAOHibernate();
		this.factory = new FactoryDAOJdbc();
	}

	public void newPost(Post post) throws Exception {
		PostDAO pd = factory.createPostPersistence();

		pd.insert(post);
	}

	public Post retrieve(double id) throws Exception {
		PostDAO pd = factory.createPostPersistence();

		Post post = pd.retrieve(id);

		return post;
	}

	public List<Post> list() {

		PostDAO pd = factory.createPostPersistence();

		List<Post> posts = null;
		try {
			posts = pd.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return posts;
	}

	public List<String> trendtopics() {

		populateTrends();

		sortTrends();

		topics = new ArrayList<String>(trends.keySet());

		return topics;
	}

	private void sortTrends() {
		trends = sortHashMapByValuesD(trends);
	}

	protected void populateTrends() {

		List<Post> posts = list();

		for (Post post : posts) {
			String txt = post.getText();
			String word;
			StringTokenizer st = new StringTokenizer(txt, " ");

			while (st.hasMoreTokens()) {
				word = st.nextToken().toLowerCase();

				String pattern = "^#(.*)$";

				if (!word.matches(pattern)) {
					System.out.println("ta errado aqui!");
					continue;
				}

				if (trends.get(word) != null) {
					double i = trends.get(word).doubleValue();
					i++;
					trends.remove(word);
					trends.put(word, i);
				} else {
					trends.put(word, 1.0);
				}

			}
		}

	}

	public LinkedHashMap<String, Double> sortHashMapByValuesD(
			HashMap<String, Double> passedMap) {
		List<String> mapKeys = new ArrayList<String>(passedMap.keySet());
		List<Double> mapValues = new ArrayList<Double>(passedMap.values());
//		Collections.sort(mapValues);
//		Collections.sort(mapKeys);
		Collections.sort(mapValues, Collections.reverseOrder());
		Collections.sort(mapKeys, Collections.reverseOrder());

		LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<String, Double>();

		Iterator<Double> valueIt = mapValues.iterator();
		while (valueIt.hasNext()) {
			Object val = valueIt.next();
			Iterator<String> keyIt = mapKeys.iterator();

			while (keyIt.hasNext()) {
				Object key = keyIt.next();
				String comp1 = passedMap.get(key).toString();
				String comp2 = val.toString();

				if (comp1.equals(comp2)) {
					passedMap.remove(key);
					mapKeys.remove(key);
					sortedMap.put((String) key, (Double) val);
					break;
				}

			}

		}
		return sortedMap;
	}
}
