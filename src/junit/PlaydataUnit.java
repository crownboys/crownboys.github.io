package junit;

import java.util.ArrayList;

public interface PlaydataUnit {

	void add(int score);

	//���ݲ�ѯ
	void getInfo();

	//��������
	ArrayList<String> getNicks();

	//���ط���
	ArrayList<Integer> getScore();

	//��¼����,��ѯ��¼����
	boolean Login(String nick, String pass);

}