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
		//背景透明
		setContentAreaFilled(false);
		//更改图片
		setIcon(imgic);
		//去除按钮的边框
		setBorder(null);
		//取消截获按键
		setFocusable(false);
		//加添按键检测，opreation关联
		addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				onClick();	//点击事件
			}
		});
	}
	public  abstract void onClick();
}
