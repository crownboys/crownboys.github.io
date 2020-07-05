package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mian.TetrisMainWin;
import model.GameData;


//单例模式
public class AlertDialog extends JDialog{
   //弹窗模式
	public static final int OVER = 0 ;//游戏结束的弹框
	public static final int LOGI = 1 ;//登陆注册的弹框
	public static final int SETT = 2 ;//设置的弹框

	 // 序列化
	private static final long serialVersionUID = 1L;
	//实例化自己
    static AlertDialog  alertDialog = null;
    //工厂模式，实现父类
    static Changer changer;
    //需要修改的对象
    JLabel  buttonLabel;
    JPanel  mainpanel;
    GameData gameData;
	TetrisMainWin  mainWin;
    //私有化构造函数
    AlertDialog( TetrisMainWin mainWin,GameData gameData) {//mainWin定位我们的弹框，gameData必要的游戏数据
    	super(mainWin,true);//true表示是否阻塞后面的窗口
    	setSize(340,241);
    	setLocationRelativeTo(mainWin);//设置位置信息，弹框会被定位在主窗口中
    	this.gameData = gameData;
    	this.mainWin = mainWin;
    	//设置背景
    	JLabel bgLabel = new JLabel(new ImageIcon("image/alert.png"));
    	getContentPane().add(bgLabel);//内容窗格来承载图片
    	setUndecorated(true);//去除弹框多余的修饰
    	//设置按钮字体
    	buttonLabel = new JLabel("默认",JLabel.CENTER);
    	buttonLabel.setFont(new Font("华文彩云",Font.BOLD,30));
    	buttonLabel.setForeground(new Color(163,40,28));
    	buttonLabel.setBounds(215,174,111,59);
    	getLayeredPane().add(buttonLabel);
    	//添加中央画布
        mainpanel = new JPanel();
    	mainpanel.setBounds(0,57,340,115);
    	mainpanel.setLayout(null);
    	mainpanel.setOpaque(false);
    	getLayeredPane().add(mainpanel);
    	
    	//添加鼠标点击事件
    	addMouseListener(new MouseListener() {
			@Override
			//判断点击位置是不是在按钮上
			//上面按钮点击判断  X
			public void mouseClicked(MouseEvent e) {
				if( e.getX()>243&&e.getX()<326) {
					if(e.getY()>12 && e.getY()<57) {
						closeDialog();
					}
				}
				//下面按钮点击判断   确定/登陆注册
				if( e.getX()>215&&e.getX()<326) {
					if(e.getY()>174 && e.getY()<233) {
						changer.onclick();
						closeDialog();
					}
				}
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent  e ) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
    	});
    }
    
    //对外接口
    public static AlertDialog getInstance(TetrisMainWin mainwin ,GameData gameData,int model) {//model标志三种不同的弹框
		synchronized(AlertDialog.class){//为了防止并发造成事故，用synchronized关键字来锁住这个类
			if(alertDialog == null) {//如果为空，就实例化
				alertDialog = new AlertDialog(mainwin,gameData); 
			}
			switch(model) {
			case OVER :
				changer = new OverChanger(alertDialog); //让changer发生一次上转型
				break;
			case LOGI :
				changer = new LogiChanger(alertDialog);
				break;
			case SETT :
			}
			changer.changerView();
			return alertDialog;	//保证每一次获取的都是AlertDialog这一个实例
	    }
		}
    
    //打开弹窗
    	public void openDialog() {
    		setVisible(true);
    	}
    	
    	//关闭弹窗
    	public void closeDialog() {
    		setVisible(false);
    	}
}


interface changeable{
	public void onclick();//点击事件
	public void changerView();//改变视图
}

abstract class  Changer  implements changeable{};

//游戏结束弹窗的设置
class OverChanger extends Changer {
	AlertDialog ad;
	OverChanger(AlertDialog ad){
		this.ad = ad;
	}
	
	//游戏结束时的点击确定后的设置
	@Override
	public void onclick() {
		ad.gameData.playerData.add(ad.gameData.score);
		ad.gameData.playerData.getInfo();
		ad.gameData.score = 0 ;
		ad.mainWin.repaint();
		ad.closeDialog();
	}
	@Override
	public void changerView() {
		ad.buttonLabel.setText("确定");
        JLabel overLabel = new JLabel("游戏结束，分数：" + ad.gameData.score,JLabel.CENTER);
        overLabel.setFont(new Font("华文彩云",Font.BOLD,30));
    	overLabel.setForeground(new Color(255,255,255));
    	overLabel.setBounds(0,0,340,115);
    	ad.mainpanel.removeAll();
    	ad.mainpanel.add(overLabel);                                         
	}
}

//登录的弹窗设置
class LogiChanger extends Changer {
	AlertDialog ad;
	JLabel noteLabel;
	JTextField nickField = new JTextField();     
 	JPasswordField passField = new JPasswordField(); 
	LogiChanger(AlertDialog ad){
		this.ad = ad;	
	}
	
	//判断登录的方法
	@Override
	public void onclick() {
    if(nickField.getText().isEmpty()) {
    	JOptionPane.showMessageDialog(null, "账号为空");
	}else {
		if (ad.gameData.playerData.Login(nickField.getText(), new String(passField.getPassword()))) {
			ad.gameData.nick = nickField.getText();
			JOptionPane.showMessageDialog(null, "您已经成功登录,账号是"+nickField.getText()+"密码是"+new String(passField.getPassword()),null, JOptionPane.INFORMATION_MESSAGE);
			ad.closeDialog();
		}else {
			JOptionPane.showMessageDialog(null, "请输入正确密码",null, JOptionPane.INFORMATION_MESSAGE);
			noteLabel.setText("该账号已被注册，请输入正确密码登录");
			noteLabel.setForeground(Color.RED);
	        noteLabel.setFont(new Font("黑体",Font.BOLD,12));
	    	noteLabel.setBounds(30,85,80,40);
	    	ad.closeDialog();
			
		}
	}
	}
	@Override
	//设置登录界面弹框的相关消息
	public void changerView() {
		ad.buttonLabel.setText("登录/注册");
		ad.buttonLabel.setFont(new Font("黑体",Font.BOLD,20));
		
		//账号和密码
        JLabel nickLabel = new JLabel("账号",JLabel.CENTER);
        nickLabel.setForeground(new Color(255,255,255));
        nickLabel.setFont(new Font("华文彩云",Font.BOLD,20));
    	nickLabel.setBounds(41,30,80,30);
    	
    	JLabel passLabel = new JLabel("密码",JLabel.CENTER);
    	passLabel.setForeground(new Color(255,255,255));
        passLabel.setFont(new Font("华文彩云",Font.BOLD,20));
     	passLabel.setBounds(41,73,80,30);
    	//账号密码的输入匡,
     	 nickField = new JTextField(ad.gameData.nick,20);
     	 nickField.setBounds(110,30,135,30);
     	 passField = new JPasswordField(20);
     	 passField.setBounds(110,73,135,30);
    	//提示匡
     	noteLabel = new JLabel(ad.gameData.nick.equals("")?"":"账号"+nickField.getText()+"已登录");
     	noteLabel.setForeground(Color.RED);
        noteLabel.setFont(new Font("黑体",Font.BOLD,12));
    	noteLabel.setBounds(0,85,200,40);
    	ad.mainpanel.removeAll();
    	ad.mainpanel.add(nickLabel);
    	ad.mainpanel.add(passLabel);
    	ad.mainpanel.add(nickField);
    	ad.mainpanel.add(passField );
    	ad.mainpanel.add(noteLabel);
    	
	}
}
