package model;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Closeable;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import mian.TetrisMainWin;
import view.AlertDialog;
import view.ImageButton;

public class Operation {
	TetrisMainWin mainWin;
	 GameData gameData;
	 //实例化
	 public OpButton left ;
	 public OpButton right;
	 public OpButton down ;
	 public OpButton rota;
	 public JButton  stst ;
	 //public JButton flaydown = new JButton();
	 public ImageButton sett ;
	 public ImageButton logi;
	
	abstract class OpButton extends ImageButton{

		private static final long serialVersionUID = 1L;

		OpButton(ImageIcon imgic) {
			super(imgic);	
		}
		@Override
		public void onClick() {
			if(gameData.state == 1) {
				doClick();
			}
		}
	}
	
	//屏幕上的操作按钮设置（旋转，向下，向左，向右）
	public Operation(){
		//向左
		 left = new  OpButton(new ImageIcon("image/left.png"))   {
			public void doClick() {
				gameData.move(true, -1);//向左移动为true，步长为1
				mainWin.getGamePanel().repaint();//每次移动，重新绘制
			}} ;
			//向右
		 right = new  OpButton(new ImageIcon("image/right.png")) {
			public void doClick() {
				gameData.move(true, 1);
				mainWin.getGamePanel().repaint();
			}} ; 
			//向下
		 down = new  OpButton(new ImageIcon("image/down.png"))   {
			public void doClick() {
				if(gameData.move(false, 1)) {
					mainWin.getScoreNext().repaint();
				}
				mainWin.getGamePanel().repaint();
			}} ;
			//旋转
		 rota = new  OpButton(new ImageIcon("image/rota.png"))   {
			public void doClick() {
				gameData.rote();
				mainWin.getGamePanel().repaint();
				
			}} ; 
			//开始按钮
		 stst = new JButton("开始") ;
			 //开始和结束      
		 
		 //暂停，继续判断
		 stst.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					if(gameData.state == 1) {
						gameData.state = 2;
					}else {
						gameData.state = 1;
					}
					 stst.setText(gameData.stst_text[gameData.state]);
				}
			});
		//设置按钮的贴图与按键
		 sett = new ImageButton(new ImageIcon("image/Message.png")){
			public void onClick() {
				LeavingMessageView p = new LeavingMessageView();
				p.setVisible(true);
			}} ;
			//注册按钮的贴图与按键
		 logi = new ImageButton(new ImageIcon("image/logi.png"))   {
			public void onClick() {
			//AlertDialog.getInstance(mainWin, gameData, AlertDialog.LOGI).openDialog();
			mainWin.alert( AlertDialog.LOGI);
			}} ;   
	}                                                              
	
	//视图关联
	public void setWin(TetrisMainWin mainwin) {
		this.mainWin = mainwin;
		this.mainWin.addKeyListener(new KeyListener() {
         
			//键盘按键判断
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			    	down.onClick();
			      }else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			    	  left.onClick();
			      }else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			    	  right.onClick();
			      }else if(e.getKeyCode()==KeyEvent.VK_UP) {
			    	  rota.onClick();
			      }else if(e.getKeyCode() == KeyEvent.VK_0) {
			    	  
			      }
			}
			@Override
			public void keyReleased(KeyEvent e) {  
			}
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
		});
	}

	//数据关联
	public void setData(GameData gameData) {
		this.gameData=gameData;
		
	}

}

