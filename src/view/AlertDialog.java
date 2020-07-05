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


//����ģʽ
public class AlertDialog extends JDialog{
   //����ģʽ
	public static final int OVER = 0 ;//��Ϸ�����ĵ���
	public static final int LOGI = 1 ;//��½ע��ĵ���
	public static final int SETT = 2 ;//���õĵ���

	 // ���л�
	private static final long serialVersionUID = 1L;
	//ʵ�����Լ�
    static AlertDialog  alertDialog = null;
    //����ģʽ��ʵ�ָ���
    static Changer changer;
    //��Ҫ�޸ĵĶ���
    JLabel  buttonLabel;
    JPanel  mainpanel;
    GameData gameData;
	TetrisMainWin  mainWin;
    //˽�л����캯��
    AlertDialog( TetrisMainWin mainWin,GameData gameData) {//mainWin��λ���ǵĵ���gameData��Ҫ����Ϸ����
    	super(mainWin,true);//true��ʾ�Ƿ���������Ĵ���
    	setSize(340,241);
    	setLocationRelativeTo(mainWin);//����λ����Ϣ������ᱻ��λ����������
    	this.gameData = gameData;
    	this.mainWin = mainWin;
    	//���ñ���
    	JLabel bgLabel = new JLabel(new ImageIcon("image/alert.png"));
    	getContentPane().add(bgLabel);//���ݴ���������ͼƬ
    	setUndecorated(true);//ȥ��������������
    	//���ð�ť����
    	buttonLabel = new JLabel("Ĭ��",JLabel.CENTER);
    	buttonLabel.setFont(new Font("���Ĳ���",Font.BOLD,30));
    	buttonLabel.setForeground(new Color(163,40,28));
    	buttonLabel.setBounds(215,174,111,59);
    	getLayeredPane().add(buttonLabel);
    	//������뻭��
        mainpanel = new JPanel();
    	mainpanel.setBounds(0,57,340,115);
    	mainpanel.setLayout(null);
    	mainpanel.setOpaque(false);
    	getLayeredPane().add(mainpanel);
    	
    	//���������¼�
    	addMouseListener(new MouseListener() {
			@Override
			//�жϵ��λ���ǲ����ڰ�ť��
			//���水ť����ж�  X
			public void mouseClicked(MouseEvent e) {
				if( e.getX()>243&&e.getX()<326) {
					if(e.getY()>12 && e.getY()<57) {
						closeDialog();
					}
				}
				//���水ť����ж�   ȷ��/��½ע��
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
    
    //����ӿ�
    public static AlertDialog getInstance(TetrisMainWin mainwin ,GameData gameData,int model) {//model��־���ֲ�ͬ�ĵ���
		synchronized(AlertDialog.class){//Ϊ�˷�ֹ��������¹ʣ���synchronized�ؼ�������ס�����
			if(alertDialog == null) {//���Ϊ�գ���ʵ����
				alertDialog = new AlertDialog(mainwin,gameData); 
			}
			switch(model) {
			case OVER :
				changer = new OverChanger(alertDialog); //��changer����һ����ת��
				break;
			case LOGI :
				changer = new LogiChanger(alertDialog);
				break;
			case SETT :
			}
			changer.changerView();
			return alertDialog;	//��֤ÿһ�λ�ȡ�Ķ���AlertDialog��һ��ʵ��
	    }
		}
    
    //�򿪵���
    	public void openDialog() {
    		setVisible(true);
    	}
    	
    	//�رյ���
    	public void closeDialog() {
    		setVisible(false);
    	}
}


interface changeable{
	public void onclick();//����¼�
	public void changerView();//�ı���ͼ
}

abstract class  Changer  implements changeable{};

//��Ϸ��������������
class OverChanger extends Changer {
	AlertDialog ad;
	OverChanger(AlertDialog ad){
		this.ad = ad;
	}
	
	//��Ϸ����ʱ�ĵ��ȷ���������
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
		ad.buttonLabel.setText("ȷ��");
        JLabel overLabel = new JLabel("��Ϸ������������" + ad.gameData.score,JLabel.CENTER);
        overLabel.setFont(new Font("���Ĳ���",Font.BOLD,30));
    	overLabel.setForeground(new Color(255,255,255));
    	overLabel.setBounds(0,0,340,115);
    	ad.mainpanel.removeAll();
    	ad.mainpanel.add(overLabel);                                         
	}
}

//��¼�ĵ�������
class LogiChanger extends Changer {
	AlertDialog ad;
	JLabel noteLabel;
	JTextField nickField = new JTextField();     
 	JPasswordField passField = new JPasswordField(); 
	LogiChanger(AlertDialog ad){
		this.ad = ad;	
	}
	
	//�жϵ�¼�ķ���
	@Override
	public void onclick() {
    if(nickField.getText().isEmpty()) {
    	JOptionPane.showMessageDialog(null, "�˺�Ϊ��");
	}else {
		if (ad.gameData.playerData.Login(nickField.getText(), new String(passField.getPassword()))) {
			ad.gameData.nick = nickField.getText();
			JOptionPane.showMessageDialog(null, "���Ѿ��ɹ���¼,�˺���"+nickField.getText()+"������"+new String(passField.getPassword()),null, JOptionPane.INFORMATION_MESSAGE);
			ad.closeDialog();
		}else {
			JOptionPane.showMessageDialog(null, "��������ȷ����",null, JOptionPane.INFORMATION_MESSAGE);
			noteLabel.setText("���˺��ѱ�ע�ᣬ��������ȷ�����¼");
			noteLabel.setForeground(Color.RED);
	        noteLabel.setFont(new Font("����",Font.BOLD,12));
	    	noteLabel.setBounds(30,85,80,40);
	    	ad.closeDialog();
			
		}
	}
	}
	@Override
	//���õ�¼���浯��������Ϣ
	public void changerView() {
		ad.buttonLabel.setText("��¼/ע��");
		ad.buttonLabel.setFont(new Font("����",Font.BOLD,20));
		
		//�˺ź�����
        JLabel nickLabel = new JLabel("�˺�",JLabel.CENTER);
        nickLabel.setForeground(new Color(255,255,255));
        nickLabel.setFont(new Font("���Ĳ���",Font.BOLD,20));
    	nickLabel.setBounds(41,30,80,30);
    	
    	JLabel passLabel = new JLabel("����",JLabel.CENTER);
    	passLabel.setForeground(new Color(255,255,255));
        passLabel.setFont(new Font("���Ĳ���",Font.BOLD,20));
     	passLabel.setBounds(41,73,80,30);
    	//�˺�����������,
     	 nickField = new JTextField(ad.gameData.nick,20);
     	 nickField.setBounds(110,30,135,30);
     	 passField = new JPasswordField(20);
     	 passField.setBounds(110,73,135,30);
    	//��ʾ��
     	noteLabel = new JLabel(ad.gameData.nick.equals("")?"":"�˺�"+nickField.getText()+"�ѵ�¼");
     	noteLabel.setForeground(Color.RED);
        noteLabel.setFont(new Font("����",Font.BOLD,12));
    	noteLabel.setBounds(0,85,200,40);
    	ad.mainpanel.removeAll();
    	ad.mainpanel.add(nickLabel);
    	ad.mainpanel.add(passLabel);
    	ad.mainpanel.add(nickField);
    	ad.mainpanel.add(passField );
    	ad.mainpanel.add(noteLabel);
    	
	}
}
