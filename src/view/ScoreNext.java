package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

import model.GameData;


public class ScoreNext extends JPanel{
	private static final long serialVersionUID = 1L;
	GameData gameData;
	int[] offset = new int [] {0,-10,0,0,-10,0,0};
	 public ScoreNext(GameData gameData){
		this.gameData = gameData;
		setOpaque(false);//背景透明
		setBounds(582, 30,200, 470);
		setLayout(null);
	}
	
	@Override
	//分数绘制
	public void paintComponent(Graphics g) {
		g.setFont(new Font("黑体",Font.PLAIN,30));
		g.setColor(Color.GREEN);
		g.drawString(gameData.getScore(), 95, 60);
		for(Point point:gameData.BLOCKS[gameData.next].points) {
			g.setColor(gameData.colors[gameData.next]);
			g.fill3DRect((point.x)*26+100+offset[gameData.next],(point.y)*26+150, 26, 26,false);
		}	
		
	}
	
}