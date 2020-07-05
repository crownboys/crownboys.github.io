package model;

import java.awt.Point;

public class Block {    
	
	//�ĸ����飬�ĸ�λ����Ϣ
	public Point [] points;
	public Block(int [] xs , int [] ys){  //����������x������������y
		points = new Point[4];
		for(int i = 0 ; i < 4 ; i++) {
			points[i] = new Point(xs[i],ys[i]);
		}
			
	}
	
	public Block(Block blocks) {
		points = new Point[4];
		for(int i = 0 ; i < 4 ; i++) {
			points[i] = new Point(blocks.points[i].x,blocks.points[i].y);
		}
	}
}
