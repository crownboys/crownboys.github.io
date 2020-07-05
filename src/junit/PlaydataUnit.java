package junit;

import java.util.ArrayList;

public interface PlaydataUnit {

	void add(int score);

	//数据查询
	void getInfo();

	//返回密码
	ArrayList<String> getNicks();

	//返回分数
	ArrayList<Integer> getScore();

	//登录方法,查询登录密码
	boolean Login(String nick, String pass);

}