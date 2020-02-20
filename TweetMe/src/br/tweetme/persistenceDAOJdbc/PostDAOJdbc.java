package br.tweetme.persistenceDAOJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.tweetme.entities.Post;
import br.tweetme.persistenceDAO.PostDAO;

public class PostDAOJdbc implements PostDAO {

	@Override
	public void insert(Post post) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ConnectionDAOJdbc.getConnection(false);

			/**
			 * Tem que ver timestamp com java
			 */
			pstmt = con
					.prepareStatement(
							"INSERT INTO posts (id_user, textPost, datePost, id_author, shared) VALUES (?, ?, ?, ?, ?)",
							Statement.RETURN_GENERATED_KEYS);

			pstmt.setDouble(1, post.getOwner().getId());
			pstmt.setString(2, post.getText());
			pstmt.setTimestamp(3, new Timestamp(post.getDate().getTime()));
			pstmt.setDouble(4, post.getAuthor().getId());
			pstmt.setBoolean(5, post.isShared());

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
	public Post retrieve(double id) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Post post = new Post();
		UserDAOJdbc udj = new UserDAOJdbc();

		try {
			con = ConnectionDAOJdbc.getConnection(false);
			String select = "SELECT * FROM posts WHERE id = ?";
			pstmt = con.prepareStatement(select);
			pstmt.setDouble(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				post.setId(rs.getDouble("id"));
				post.setAuthor(udj.retrieve(rs.getDouble("id_author")));
				post.setOwner(udj.retrieve(rs.getDouble("id_user")));
				post.setShared(rs.getBoolean("shared"));
				post.setDate(new Date(rs.getTimestamp("datePost").getTime()));
				post.setText(rs.getString("textpost"));
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

		return post;
	}

	@Override
	public void update(Post post) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Post post) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Post> list() throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Post> posts = new ArrayList<Post>();
		
		UserDAOJdbc udj = new UserDAOJdbc();

		try {
			con = ConnectionDAOJdbc.getConnection(false);
			String select = "SELECT * FROM posts";
			pstmt = con.prepareStatement(select);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				Post post = new Post();
				
				post.setId(rs.getDouble("id"));
				post.setAuthor(udj.retrieve(rs.getDouble("id_author")));
				post.setOwner(udj.retrieve(rs.getDouble("id_user")));
				post.setShared(rs.getBoolean("shared"));
				post.setDate(new Date(rs.getTimestamp("datePost").getTime()));
				post.setText(rs.getString("textpost"));
				
				posts.add(post);
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

		return posts;
	}

}
