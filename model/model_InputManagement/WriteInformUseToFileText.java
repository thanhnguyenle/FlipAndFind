package model_InputManagement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import model_user.IUserObservable;
import model_user.IUserObserver;
import model_user.ListUsers;
import model_user.User;

public class WriteInformUseToFileText implements IUserObserver {
	private ListUsers listUsers;
	private int level;

	public WriteInformUseToFileText(IUserObservable userData) {
		// TODO Auto-generated constructor stub
		userData.registerObserver(this);

	}

	private void writeUsingBufferedWriter(Path path, String data) {
		File file = new File(path.toString());
		// important
		FileWriter fr = null;
		BufferedWriter br = null;
		//
		String s = data + System.getProperty("line.separator");
		try {
			fr = new FileWriter(file);
			br = new BufferedWriter(fr);

			// write
			br.write(s);

			// close
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeInformUser() {
		String s = "";
		for (User user : this.listUsers.getUsers()) {
			s += user.getUserName() + "`" + user.getAge() + "`" + user.getLanguages() + "`" + user.getCountries() + "`"
					+ user.getRank() + "`" + user.getLevel() + "`" + user.getScore() + "`" + user.getDescription() + "`"
					+ user.getPathAvatar() + "\n";
		}
		Path p = Path.of("./src/dataUsersOfGame/User_inform.txt");
		writeUsingBufferedWriter(p, s.trim());
	}

	@Override
	public void update(ListUsers listUsers, int level) {
		// TODO Auto-generated method stub
		this.listUsers = listUsers;
		this.level = level;
		writeInformUser();
	}
}
