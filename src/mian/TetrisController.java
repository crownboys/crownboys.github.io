package mian;

import dao.PlayerData;
import model.GameData;
import model.Operation;
import view.AlertDialog;

//������
public class TetrisController {
  public static void main(String [] args) {
	  //ʵ��������
	    Operation operation = new Operation();
	    //���ز���
	   GameData gameData = new GameData();
	    //�����ݺʹ��ڽ��й��������캯��
	    TetrisMainWin mainwin = new TetrisMainWin(operation,gameData);
	    //�����ںͲ���������
	    operation.setWin(mainwin);
	    //�����ݺͲ���������
	    operation.setData(gameData);
	    //�����߳�
	    AutoDowm autoDowm = new AutoDowm(gameData,mainwin);
	    autoDowm.start();
	    //new AutoDowm(gameData,mainwin).start();
	    mainwin.setVisible(true);
   }
}

//�̲߳���
class AutoDowm extends Thread {
	private GameData gameData;
	private TetrisMainWin mainwin;
	AutoDowm(GameData gamedata,TetrisMainWin mainwin) {
		this.gameData = gamedata;
		this.mainwin = mainwin;
	}
	
	//���Ʒ�������
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

































