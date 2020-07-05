package model;

import java.awt.Point;

public class Block {    
	
	//四个方块，四个位置信息
	public Point [] points;
	public Block(int [] xs , int [] ys){  //横坐标数组x，纵坐标数组y
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
