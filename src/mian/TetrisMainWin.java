package mian;



import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.PlayerPanel;
import model.GameData;
import model.Operation;
import view.AlertDialog;
import view.GamePanel;
import view.ScoreNext;
import view.StaticPanel;

public class TetrisMainWin extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Operation operation;
	GameData gameData;
	GamePanel gamepanel;
	Container mainpane;
	ScoreNext scoreNext;
	StaticPanel staticpanel;
	PlayerPanel playerPanel ;
	public TetrisMainWin(Operation operation, GameData gameData) {
		this.gameData = gameData;
		mainpane = getLayeredPane();
		setBounds(100, 50, 800, 740);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   //���ñ���
		setBack();
		//��������
		 staticpanel = new StaticPanel(operation);
		mainpane.add(staticpanel);
		//�����Ϸ����
		setGamePanel();
		//��ӷ�����ʾ
		setScoreNext();
		//�����Ϸ��¼
		playerPanel = new PlayerPanel(gameData);
		mainpane.add(playerPanel);
		mainpane.setComponentZOrder(playerPanel, 0);
		
	}
	//���û�ð���������   
			//setFocusable(true);
private void setScoreNext() {
		scoreNext = new ScoreNext(gameData);
		mainpane.add(scoreNext);
		
	}
	//���ñ���
	void setBack() {
		
		ImageIcon imgic = new ImageIcon("image/bg.jpg");
		JLabel j1 = new JLabel(imgic);
	     setTitle("����˹����");
		j1.setBounds(0, 0, 800, 740);
		getContentPane().add(j1);
	}
	
	//�����Ϸ����
	void setGamePanel() {
		gamepanel = new GamePanel(gameData);
		mainpane.add(gamepanel);//������ŵ�������
	}
	
	//��ȡ��Ϸ��
	public GamePanel getGamePanel() {
		return gamepanel;
	}
	
	//��ȡ������ʾ��
	public ScoreNext getScoreNext() {
		return scoreNext;
		
	}
	  //��ȡ���̿��ư�ť
	public JButton getStst() {
		return staticpanel.stst;
	}
	
	//��������ʾ��Ϸ��������ɶԵ���ĵ���
	public void alert(int model) {
		int _state = gameData.state ;
		gameData.state = 2 ;
		AlertDialog.getInstance(this, gameData, model).openDialog();
		gameData.state = _state;
		
	}
}
