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
	 //ʵ����
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
	
	//��Ļ�ϵĲ�����ť���ã���ת�����£��������ң�
	public Operation(){
		//����
		 left = new  OpButton(new ImageIcon("image/left.png"))   {
			public void doClick() {
				gameData.move(true, -1);//�����ƶ�Ϊtrue������Ϊ1
				mainWin.getGamePanel().repaint();//ÿ���ƶ������»���
			}} ;
			//����
		 right = new  OpButton(new ImageIcon("image/right.png")) {
			public void doClick() {
				gameData.move(true, 1);
				mainWin.getGamePanel().repaint();
			}} ; 
			//����
		 down = new  OpButton(new ImageIcon("image/down.png"))   {
			public void doClick() {
				if(gameData.move(false, 1)) {
					mainWin.getScoreNext().repaint();
				}
				mainWin.getGamePanel().repaint();
			}} ;
			//��ת
		 rota = new  OpButton(new ImageIcon("image/rota.png"))   {
			public void doClick() {
				gameData.rote();
				mainWin.getGamePanel().repaint();
				
			}} ; 
			//��ʼ��ť
		 stst = new JButton("��ʼ") ;
			 //��ʼ�ͽ���      
		 
		 //��ͣ�������ж�
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
		//���ð�ť����ͼ�밴��
		 sett = new ImageButton(new ImageIcon("image/Message.png")){
			public void onClick() {
				LeavingMessageView p = new LeavingMessageView();
				p.setVisible(true);
			}} ;
			//ע�ᰴť����ͼ�밴��
		 logi = new ImageButton(new ImageIcon("image/logi.png"))   {
			public void onClick() {
			//AlertDialog.getInstance(mainWin, gameData, AlertDialog.LOGI).openDialog();
			mainWin.alert( AlertDialog.LOGI);
			}} ;   
	}                                                              
	
	//��ͼ����
	public void setWin(TetrisMainWin mainwin) {
		this.mainWin = mainwin;
		this.mainWin.addKeyListener(new KeyListener() {
         
			//���̰����ж�
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

	//���ݹ���
	public void setData(GameData gameData) {
		this.gameData=gameData;
		
	}

}

