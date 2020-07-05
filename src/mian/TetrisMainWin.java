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
	   //设置背景
		setBack();
		//绘制区域
		 staticpanel = new StaticPanel(operation);
		mainpane.add(staticpanel);
		//添加游戏方块
		setGamePanel();
		//添加分数提示
		setScoreNext();
		//添加游戏记录
		playerPanel = new PlayerPanel(gameData);
		mainpane.add(playerPanel);
		mainpane.setComponentZOrder(playerPanel, 0);
		
	}
	//设置获得按键的能力   
			//setFocusable(true);
private void setScoreNext() {
		scoreNext = new ScoreNext(gameData);
		mainpane.add(scoreNext);
		
	}
	//设置背景
	void setBack() {
		
		ImageIcon imgic = new ImageIcon("image/bg.jpg");
		JLabel j1 = new JLabel(imgic);
	     setTitle("俄罗斯方块");
		j1.setBounds(0, 0, 800, 740);
		getContentPane().add(j1);
	}
	
	//添加游戏方块
	void setGamePanel() {
		gamepanel = new GamePanel(gameData);
		mainpane.add(gamepanel);//将方块放到画布上
	}
	
	//获取游戏区
	public GamePanel getGamePanel() {
		return gamepanel;
	}
	
	//获取分数提示区
	public ScoreNext getScoreNext() {
		return scoreNext;
		
	}
	  //获取流程控制按钮
	public JButton getStst() {
		return staticpanel.stst;
	}
	
	//弹窗，提示游戏结束，完成对弹框的调用
	public void alert(int model) {
		int _state = gameData.state ;
		gameData.state = 2 ;
		AlertDialog.getInstance(this, gameData, model).openDialog();
		gameData.state = _state;
		
	}
}
