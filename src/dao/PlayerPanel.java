package dao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import junit.PlayerPanelUnit;
import model.GameData;




public class PlayerPanel extends JPanel implements PlayerPanelUnit{
	/**序列化*/
	private static final long serialVersionUID = 1L;
	GameData gameData;
   
   public PlayerPanel(GameData gameData){
	   this.gameData = gameData;
	   setOpaque(false);//设置背景透明
	   setBounds(28, 590, 523, 143);	
   }
   
   /* (non-Javadoc)
 * @see dao.PlayerPanelUnit#paintComponent(java.awt.Graphics)
 */
@Override
public void paintComponent(Graphics g) {
	   super.paintComponent(g);
	   g.setFont(new Font("黑体",Font.PLAIN,30));
	   g.setColor(Color.RED);
	   int _y = 35 ;
	   for(String nick:gameData.playerData.getNicks()) {
		   g.drawString(""+nick, 225, _y);
		   _y+=35;
	   }
	   
	   //排行榜分数显示
	   _y = 35 ;
	   for(int score:gameData.playerData.getScore()) {
		   g.drawString(""+ score, 370, _y);
		   _y+=35;
	   }
   }
}
