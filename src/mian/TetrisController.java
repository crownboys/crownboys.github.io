package mian;

import dao.PlayerData;
import model.GameData;
import model.Operation;
import view.AlertDialog;

//主窗口
public class TetrisController {
  public static void main(String [] args) {
	  //实例化操作
	    Operation operation = new Operation();
	    //加载操作
	   GameData gameData = new GameData();
	    //将数据和窗口进行关联，构造函数
	    TetrisMainWin mainwin = new TetrisMainWin(operation,gameData);
	    //将窗口和操作区关联
	    operation.setWin(mainwin);
	    //将数据和操作区关联
	    operation.setData(gameData);
	    //启用线程
	    AutoDowm autoDowm = new AutoDowm(gameData,mainwin);
	    autoDowm.start();
	    //new AutoDowm(gameData,mainwin).start();
	    mainwin.setVisible(true);
   }
}

//线程并发
class AutoDowm extends Thread {
	private GameData gameData;
	private TetrisMainWin mainwin;
	AutoDowm(GameData gamedata,TetrisMainWin mainwin) {
		this.gameData = gamedata;
		this.mainwin = mainwin;
	}
	
	//控制方块下落
	public void run() {
		while(true) {
			try {
			if(gameData.state==1) {
				if(gameData.move(false, 1)) {
					mainwin.getScoreNext().repaint();
				};
				mainwin.getGamePanel().repaint();	
					sleep(1000);	
			}else if(gameData.state == 3){
				gameData.init();
				mainwin.alert(AlertDialog.OVER);
				 mainwin.getStst().setText(gameData.stst_text[gameData.state]);
				 gameData.state = 4; 
			}
			else {
				sleep(150);
			    }		  
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
   }
}

































