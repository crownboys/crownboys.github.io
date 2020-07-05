package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import javax.swing.JPanel;

import mian.TetrisMainWin;
import model.GameData;
import view.AlertDialog;


public class GamePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	GameData gameData;
	
	public GamePanel(GameData gameData){
		this.gameData = gameData;
		setOpaque(false);//设置背景透明
		setBounds(30, 30,520, 520);
		setLayout(null);
	}
	
	//绘制方块
	@Override
	public void paintComponent(Graphics g) {
		for(Point point:gameData.blocks.points) {
			g.setColor(gameData.colors[gameData.current]);
			//相对偏移量加上绝对偏移量
			g.fill3DRect((point.x+gameData.x)*26,(point.y+gameData.y)*26, 26, 26,false);
		}	
		
		//i纵坐标，j横坐标
		for(int i = 21 ; i>= 2 ; i --) {
			for(int j = 0 ; j < 20 ; j++) {
				if(gameData.existBlocks[j][i]!=0) {
					g.setColor(gameData.colors[gameData.existBlocks[j][i]-1]);
					g.fill3DRect(j*26,(i-2)*26, 26, 26,false);
				}
			}
		}
	}
	
}
	
