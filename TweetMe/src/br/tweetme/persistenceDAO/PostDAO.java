package br.tweetme.persistenceDAO;

import java.util.List;

import br.tweetme.entities.Post;

public interface PostDAO {
	public void insert(Post post) throws Exception;

	public Post retrieve(double id) throws Exception;
	
	public void update(Post post) throws Exception;

	public void delete(Post post) throws Exception;

	public List<Post> list() throws Exception;
}
