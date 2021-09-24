package model_InputManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model_user.User;

public class ReadInformUserFromFileText {
	public ReadInformUserFromFileText() {
		// TODO Auto-generated constructor stub
	}

	public List<User> readInformUser() {
		Path p = Path.of("./src/dataUsersOfGame/User_inform.txt");
		File file = new File(p.toString());

		// important (don't forget)
		FileReader fr;
		BufferedReader br;
		//
		List<User> list = new LinkedList<>();
		try {
			// initial
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			// read
			while (true) {
				String temp = "";
				temp = br.readLine();
				if (temp != null&&!temp.isEmpty()) {
					String[] data = temp.split("`");
					String userName = data[0];
					Integer age = Integer.parseInt(data[1]);
					String lag = data[2];
					String country = data[3];
					Integer rank = Integer.parseInt(data[4]);
					Integer level = Integer.parseInt(data[5]);
					Integer score = Integer.parseInt(data[6]);
					String desciption = data[7];
					String pathAvatar="" ;
					if(data.length==9)
					 pathAvatar = data[8];
						
					//
					User user = new User(userName,age,lag,country, rank, level, score, desciption);
					user.setPathAvatar(pathAvatar);
					list.add(user);
				} else
					break;
			}

			// close
			br.close();
			fr.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
}
