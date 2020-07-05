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
		
		//��λ��ť��������ð�ť��С
		left.setBounds(580,340,50,50);
		right.setBounds(680,340,50,50);
		down.setBounds(580,390,50,50);
		rota.setBounds(680,390,50,50);
		stst.setBounds(590,450,180,80);
		sett.setBounds(660,655,50,50);
		logi.setBounds(720,655,50,50);
		
		//����ť��ӵ�������
		add(left);
		add(right);
		add(down);
		add(rota);
		add(stst);
		add(sett);
		add(logi);
	}
	
	//���ð�ť͸��
	private void setstst() {
		stst.setContentAreaFilled(false);
		stst.setFocusPainted(false);
		stst.setFont(new  Font("������κ",Font.PLAIN,30));
		stst.setForeground(Color.WHITE);
		stst.setFocusable(false);
	}
	
	@Override
	public void paintComponent(Graphics g) {
	//�̳и�����Ʒ���
		super.paintComponent(g);
		//���ñ���͸��
		g.setColor(new Color(150,150,150,70));
		//��Ϸ��
		g.fillRect(30, 30,520, 520);
		//������
		g.fillRect(30, 560, 520,140 );
		//�Ҳ��Ű�
		g.fillRect(580, 30, 200,80 );
		g.setColor(new Color(2,2,2,30));
		//�÷���
		g.fillRect(580, 120, 200,200 );
		//��ʾ��
		g.fillRect(580, 340, 200,100 );
		//������
		g.fillRect(580, 450, 200,100 );
		//�߿�
		g.setColor(Color.white);
		((Graphics2D)g).setStroke(new BasicStroke(2L));//ת��Ϊ2ά���ʣ����ñ߿�Ŀ��
		g.drawRect(28, 28, 523, 523);
		g.drawRect(28, 558, 523, 143);
		//����
		g.setFont(new Font("������κ",Font.PLAIN,30));
		g.setColor(Color.YELLOW);
		//�÷�����
		g.drawString("�÷�", 655, 60);
		//��һ������
		g.drawString("��һ��", 650, 150);
		//����������
		g.setFont(new Font("������κ",Font.PLAIN,50));//���������С
		g.drawString("������", 60, 640);
		//�˺źͷ�������
		g.setFont(new Font("������κ",Font.PLAIN,30));
		g.drawString("�˺�",250 , 585);
		g.drawString("����", 380, 585);
		
	}

}
