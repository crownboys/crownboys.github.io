package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Operation;

public class StaticPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	    JButton left ;
		JButton right;
		JButton down ;
		JButton rota ;
	    public JButton stst ;
		JButton sett ;
		JButton logi ;
	public StaticPanel(Operation operation){
		
		left  =operation.left ;
		right =operation.right;
		down  =operation.down ;
		rota  =operation.rota ;
		stst  =operation.stst ;
		sett  =operation.sett ;
		logi  =operation.logi ;
		setBounds(0,0,800,740);
		setLayout(null);
		setOpaque(false);
		setstst();
		
		//定位按钮坐标和设置按钮大小
		left.setBounds(580,340,50,50);
		right.setBounds(680,340,50,50);
		down.setBounds(580,390,50,50);
		rota.setBounds(680,390,50,50);
		stst.setBounds(590,450,180,80);
		sett.setBounds(660,655,50,50);
		logi.setBounds(720,655,50,50);
		
		//将按钮添加到画布上
		add(left);
		add(right);
		add(down);
		add(rota);
		add(stst);
		add(sett);
		add(logi);
	}
	
	//设置按钮透明
	private void setstst() {
		stst.setContentAreaFilled(false);
		stst.setFocusPainted(false);
		stst.setFont(new  Font("华文新魏",Font.PLAIN,30));
		stst.setForeground(Color.WHITE);
		stst.setFocusable(false);
	}
	
	@Override
	public void paintComponent(Graphics g) {
	//继承父类绘制方法
		super.paintComponent(g);
		//设置背景透明
		g.setColor(new Color(150,150,150,70));
		//游戏区
		g.fillRect(30, 30,520, 520);
		//排名区
		g.fillRect(30, 560, 520,140 );
		//右侧排版
		g.fillRect(580, 30, 200,80 );
		g.setColor(new Color(2,2,2,30));
		//得分区
		g.fillRect(580, 120, 200,200 );
		//提示区
		g.fillRect(580, 340, 200,100 );
		//操作区
		g.fillRect(580, 450, 200,100 );
		//边框
		g.setColor(Color.white);
		((Graphics2D)g).setStroke(new BasicStroke(2L));//转化为2维画笔，设置边框的宽度
		g.drawRect(28, 28, 523, 523);
		g.drawRect(28, 558, 523, 143);
		//文字
		g.setFont(new Font("华文新魏",Font.PLAIN,30));
		g.setColor(Color.YELLOW);
		//得分文字
		g.drawString("得分", 655, 60);
		//下一个文字
		g.drawString("下一个", 650, 150);
		//荣誉榜文字
		g.setFont(new Font("华文新魏",Font.PLAIN,50));//设置字体大小
		g.drawString("荣誉榜", 60, 640);
		//账号和分数文字
		g.setFont(new Font("华文新魏",Font.PLAIN,30));
		g.drawString("账号",250 , 585);
		g.drawString("分数", 380, 585);
		
	}

}
