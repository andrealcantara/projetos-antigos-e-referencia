package br.tweetme.persistenceDAOJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.tweetme.entities.Post;
import br.tweetme.entities.User;
import br.tweetme.persistenceDAO.UserDAO;

public class UserDAOJdbc implements UserDAO {

	@Override
	public void insert(User user) throws Exception {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			if (user.getName() == null || user.getName() == "")
				throw new Exception();
			if (user.getEmail() == null || user.getEmail() == "")
				throw new Exception();
			if (user.getLogin() == null || user.getLogin() == "")
				throw new Exception();

			con = ConnectionDAOJdbc.getConnection(false);

			pstmt = con
					.prepareStatement(
							"INSERT INTO users (name, email, pass, login) VALUES (?, ?, ?, ?)",
							Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getLogin());

			System.out.println("TESTE = " + user.getLogin());

			pstmt.executeUpdate();
			con.commit();

			// ResultSet rs = pstmt.getGeneratedKeys();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public User retrieve(double id) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;

		try {
			con = ConnectionDAOJdbc.getConnection(false);
			String select = "SELECT id, email, pass, name, login, description, photo FROM users WHERE id = ?";
			pstmt = con.prepareStatement(select);
			pstmt.setDouble(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getDouble("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("pass"));
				user.setName(rs.getString("name"));
				user.setLogin(rs.getString("login"));
				user.setDescription(rs.getString("description"));
				user.setPhoto(rs.getString("photo"));
			}
			
			System.out.println(user);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return user;

	}

	@SuppressWarnings("resource")
	@Override
	public User retrieve(String login) throws Exception {

		if (login == null || login == "")
			return null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;

		try {
			con = ConnectionDAOJdbc.getConnection(false);
			String select = "SELECT id, email, pass, name, login, description, photo FROM users WHERE login = ?";
			pstmt = con.prepareStatement(select);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getDouble("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("pass"));
				user.setName(rs.getString("name"));
				user.setLogin(rs.getString("login"));
				user.setDescription(rs.getString("description"));
				user.setPhoto(rs.getString("photo"));

				select = "SELECT id, name, email, pass, login, description, photo FROM users, follow "
						+ "WHERE id = id_follower AND id_followed = ?";

				pstmt = con.prepareStatement(select);
				pstmt.setDouble(1, user.getId());
				rs = pstmt.executeQuery();

				while (rs.next()) {
					User follower = new User();
					follower.setId(rs.getDouble("id"));
					follower.setEmail(rs.getString("email"));
					follower.setPassword(rs.getString("pass"));
					follower.setName(rs.getString("name"));
					follower.setLogin(rs.getString("login"));
					follower.setDescription(rs.getString("description"));
					follower.setPhoto(rs.getString("photo"));

					user.addFollower(follower);
				}

				select = "SELECT id, name, email, pass, login, description, photo FROM users, follow "
						+ "WHERE id = id_followed AND id_follower = ?";

				pstmt = con.prepareStatement(select);
				pstmt.setDouble(1, user.getId());
				rs = pstmt.executeQuery();

				while (rs.next()) {
					User following = new User();
					following.setId(rs.getDouble("id"));
					following.setEmail(rs.getString("email"));
					following.setPassword(rs.getString("pass"));
					following.setName(rs.getString("name"));
					following.setLogin(rs.getString("login"));
					following.setDescription(rs.getString("description"));
					following.setPhoto(rs.getString("photo"));

					user.addFollowing(following);
				}

				select = "SELECT * FROM posts AS p, ( "
						+ "(SELECT id, login, name, photo FROM users, follow AS f "
						+ "WHERE id = f.id_followed AND f.id_follower = ? ) "
						+ "UNION ( "
						+ "SELECT id, login, name, photo FROM users WHERE id = ? ) ORDER BY id "
						+ ") AS owner WHERE p.id_user = owner.id "
						+ "ORDER BY datePost DESC";
				
				pstmt = con.prepareStatement(select);
				pstmt.setDouble(1, user.getId());
				pstmt.setDouble(2, user.getId());
				rs = pstmt.executeQuery();

				while (rs.next()) {
					Post post = new Post();

					post.setId(rs.getDouble("id"));
					post.setText(rs.getString("textpost"));
					
					User u = new User();
					u.setId(rs.getDouble("id"));
					u.setName(rs.getString("name"));
					u.setLogin(rs.getString("login"));
					u.setPhoto(rs.getString("photo"));
					
					post.setOwner(u);
					post.setDate(new Date(rs.getTimestamp("datePost").getTime()));
					
					u = retrieve(rs.getDouble("id_author"));
					post.setAuthor(u);
					
					post.setShared(rs.getBoolean("shared"));

					System.out.println(post);

					user.addPost(post);
				}

				System.out.println(user);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return user;
	}

	@SuppressWarnings("resource")
	@Override
	public User retrieveByEmail(String email) throws Exception {

		if (email == null || email == "")
			return null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;

		try {
			con = ConnectionDAOJdbc.getConnection(false);
			String select = "SELECT id, email, pass, name, login, description, photo FROM users WHERE email = ?";
			pstmt = con.prepareStatement(select);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getDouble("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("pass"));
				user.setName(rs.getString("name"));
				user.setLogin(rs.getString("login"));
				user.setDescription(rs.getString("description"));
				user.setPhoto(rs.getString("photo"));

				select = "SELECT id, name, email, pass, login, description, photo FROM users, follow WHERE id = id_follower AND id_followed = ?";

				pstmt = con.prepareStatement(select);
				pstmt.setDouble(1, user.getId());
				rs = pstmt.executeQuery();

				while (rs.next()) {
					User follower = new User();
					follower.setId(rs.getDouble("id"));
					follower.setEmail(rs.getString("email"));
					follower.setPassword(rs.getString("pass"));
					follower.setName(rs.getString("name"));
					follower.setLogin(rs.getString("login"));
					follower.setDescription(rs.getString("description"));
					follower.setPhoto(rs.getString("photo"));

					user.addFollower(follower);
				}

				select = "SELECT id, name, email, pass, login, description, photo FROM users, follow WHERE id = id_followed AND id_follower = ?";

				pstmt = con.prepareStatement(select);
				pstmt.setDouble(1, user.getId());
				rs = pstmt.executeQuery();

				while (rs.next()) {
					User following = new User();
					following.setId(rs.getDouble("id"));
					following.setEmail(rs.getString("email"));
					following.setPassword(rs.getString("pass"));
					following.setName(rs.getString("name"));
					following.setLogin(rs.getString("login"));
					following.setDescription(rs.getString("description"));
					following.setPhoto(rs.getString("photo"));

					user.addFollowing(following);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println(user);

		return user;
	}

	@Override
	public void update(User user) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ConnectionDAOJdbc.getConnection(false);
			String update = "UPDATE users SET login = ?, pass = ?, name = ?, description = ?, "
					+ "email = ?, photo = ? WHERE id = ?";
			pstmt = con.prepareStatement(update);

			pstmt.setString(1, user.getLogin());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getDescription());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getPhoto());
			pstmt.setDouble(7, user.getId());

			pstmt.executeUpdate();

			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

	}

	@Override
	public void delete(User user) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> list() throws Exception {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();

		try {
			con = ConnectionDAOJdbc.getConnection(false);
			String select = "SELECT id, name, email, pass, login, description, photo FROM users";
			pstmt = con.prepareStatement(select);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getDouble("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("pass"));
				user.setName(rs.getString("name"));
				user.setLogin(rs.getString("login"));
				user.setDescription(rs.getString("description"));
				user.setPhoto(rs.getString("photo"));

				select = "SELECT id, name, email, pass, login, description, photo FROM users, follow WHERE id = id_follower AND id_followed = ?";

				PreparedStatement subpstmt = con.prepareStatement(select);
				subpstmt.setDouble(1, user.getId());
				ResultSet subrs = subpstmt.executeQuery();

				while (subrs.next()) {
					User follower = new User();
					follower.setId(subrs.getDouble("id"));
					follower.setEmail(subrs.getString("email"));
					follower.setPassword(subrs.getString("pass"));
					follower.setName(subrs.getString("name"));
					follower.setLogin(subrs.getString("login"));
					follower.setDescription(rs.getString("description"));
					follower.setPhoto(rs.getString("photo"));

					user.addFollower(follower);
				}

				select = "SELECT id, name, email, pass, login, description, photo FROM users, follow "
						+ "WHERE id = id_followed AND id_follower = ?";

				subpstmt = con.prepareStatement(select);
				subpstmt.setDouble(1, user.getId());
				subrs = subpstmt.executeQuery();

				while (subrs.next()) {
					User following = new User();
					following.setId(subrs.getDouble("id"));
					following.setEmail(subrs.getString("email"));
					following.setPassword(subrs.getString("pass"));
					following.setName(subrs.getString("name"));
					following.setLogin(subrs.getString("login"));
					following.setDescription(rs.getString("description"));
					following.setPhoto(rs.getString("photo"));

					user.addFollowing(following);
				}

				System.out.println(user);

				users.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return users;
	}

	@Override
	public void follow(User follower, User followed) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ConnectionDAOJdbc.getConnection(false);

			pstmt = con
					.prepareStatement("INSERT INTO follow (id_followed, id_follower) VALUES (?, ?)");
			pstmt.setDouble(1, followed.getId());
			pstmt.setDouble(2, follower.getId());

			pstmt.executeUpdate();
			con.commit();

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void unfollow(User follower, User followed) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ConnectionDAOJdbc.getConnection(false);

			pstmt = con
					.prepareStatement("DELETE FROM follow WHERE id_followed = ? AND id_follower = ?");
			pstmt.setDouble(1, followed.getId());
			pstmt.setDouble(2, follower.getId());

			pstmt.executeUpdate();
			con.commit();

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
