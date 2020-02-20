package br.tweetme.support;

import java.util.List;

import br.tweetme.entities.User;

public class SupportUserList {

	List<User> users = null;
	
	public SupportUserList(List<User> users) {
		this.users = users;
	}
	
	public List<User> getList() {
		return users;
	}
	
}
