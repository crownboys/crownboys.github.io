package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class ImageButton extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ImageButton(ImageIcon imgic){
		//����͸��
		setContentAreaFilled(false);
		//����ͼƬ
		setIcon(imgic);
		//ȥ����ť�ı߿�
		setBorder(null);
		//ȡ���ػ񰴼�
		setFocusable(false);
		//��������⣬opreation����
		addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				onClick();	//����¼�
			}
		});
	}
	public  abstract void onClick();
}
