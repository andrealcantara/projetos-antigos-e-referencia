package br.tweetme.beans;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.myfaces.custom.fileupload.UploadedFile;

import br.tweetme.controllers.UserController;
import br.tweetme.entities.User;

public class UserBean extends User {

	private UploadedFile arquivo = null;
	private static final long serialVersionUID = 1L;

	public void setCurrentUser(User currentUser) {
		setName(currentUser.getName());
		setEmail(currentUser.getEmail());
		setLogin(currentUser.getLogin());
		setPhoto(currentUser.getPhoto());
		setDescription(currentUser.getDescription());
		setId(currentUser.getId());
	}

	public String change() {
		System.out.println("Change");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);
		UserController uc = new UserController();

		String photo = getPhoto();
		if (arquivo != null) {
			photo = upload();
		}

		User current = (User) session.getAttribute("user");

		current.setName(getName());
		current.setDescription(getDescription());
		current.setPhoto(photo);
		uc.edit(current);

		setCurrentUser(current);

		session.setAttribute("user", current);

		arquivo = null;

		return "settings";
	}

	public String upload() {
		System.out.println("Nome do arquivo enviado: " + arquivo.getName());
		System.out.println("Tipo do arquivo enviado: "
				+ arquivo.getContentType());
		System.out.println("Tamanho do arquivo enviado: " + arquivo.getSize());

		StringTokenizer st = new StringTokenizer(arquivo.getName(), ".");
		String ext = ".jpg";
		while (st.hasMoreElements()) {
			ext = (String) st.nextElement();
		}

		String sImgDirP = "imgs//profiles//" + getId();

		String sDir = System.getProperty("user.home")
				+ "//workspace//ProjetoWeb3//WebContent//" + sImgDirP;

		System.out.println("dir: " + sDir);

		File dir = new File(sDir);

		if (!dir.exists()) {
			dir.mkdirs();
		}

		File file = new File(sDir + "//" + "me." + ext);

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Criação arquivo.");
			}
		}

		BufferedOutputStream out = null;

		try {
			out = new BufferedOutputStream(new FileOutputStream(file));
			out.write(arquivo.getBytes());
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Criação do out");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Alguma das 3 operações do out");
		}

		return "/imgs/profiles/" + getId() + "/" + file.getName();
	}

	public UploadedFile getArquivo() {

		return arquivo;
	}

	public UploadedFile getArquivo(String login) {

		System.out.println(login);
		
		return arquivo;
	}

	public void setArquivo(UploadedFile arquivo) {
		this.arquivo = arquivo;
	}

}
