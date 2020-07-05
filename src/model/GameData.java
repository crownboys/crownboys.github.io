package model;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import dao.PlaydataUnit;
import dao.PlayerData;
import model.Block;

public class GameData {
	public Block blocks;//显示在游戏区的方块
	 public Block [] BLOCKS = new Block [] {
			new Block(new int [] {-1,0,1,1},new int [] {0,0,0,1}),
			new Block(new int [] {-1,0,1,2},new int [] {0,0,0,0}),
			new Block(new int [] {-1,-1,0,1},new int [] {0,1,0,0}),
			new Block(new int [] {-1,0,0,1},new int [] {0,0,1,1}),
			new Block(new int [] {0,0,1,1},new int [] {0,1,0,1}),
			new Block(new int [] {-1,0,0,1},new int [] {1,0,1,0}),
			new Block(new int [] {-1,0,0,1},new int [] {0,0,1,0}),
	};
    
	//配置颜色
	public Color [] colors = new Color[] {
			new Color(255,0,0),
			new Color(0,255,0),
			new Color(0,0,255),
			new Color(255,255,0),
			new Color(255,0,255),
			new Color(0,255,255),
			new Color(255,106,106),
	};
	
	//按键文本信息
	public String[] stst_text = new String[] {"开始","暂停","继续","重来"};
	
	//偏移量
	public int x  ;//横向偏移量
	public int y  ;//纵向偏移量
	//存放格子的数组
	public int[][] existBlocks;
	//存放删除格数的数组
	int[] deletnum;
	//随机因子
	Random random;
	//下一个
	public int next;
	//当前的格子编号
	public int current;
	//记录分数
	public int score;
	//标记游戏状态（开始，暂停。继续，重来）
	public int state;
	//游戏记录
  public PlaydataUnit playerData;
  public String nick;
   public String pass;
//登录状态
   public GameData(){	

	init();  
	playerData = new PlayerData();
	nick ="";
	pass ="";
}
public void init () {
	existBlocks = new int [20][22];
	random = new Random();
	next = random.nextInt(7);
    initBlocks();
}

//方块的移动，边界判断
public boolean move(boolean isH,int step) {
	boolean isdelet = false;
	if(isH) {
		for(Point point:blocks.points) {
			if(point.x + x + step < 0 || point.x + x + step > 19 
					|| existBlocks[point.x + x + step][point.y + y + 2 ]!=0) {
				return false;
			}
		}	
		x+=step;	
	}else {
		for(Point point:blocks.points) {
			if(point.y+y+step> 19 
			 || existBlocks[point.x + x][point.y + y + 2 + step]!=0) {
				saveBlocks();
				isdelet = deletTest();
				if(isdelet) {
					deletLine();
				}
				if(isgameover()) {
					state = 3 ;
				}
				initBlocks();
				return true;		
			}
		}		
			y+=step;
	}
	return isdelet;
}

//重置下落的方块
private void initBlocks() {
	x = 4;//初始位置
	y = -2;
	deletnum = new int [22];
	blocks = new Block(BLOCKS [next]);
	current = next ; 
	next = random.nextInt(7);
	
}

//旋转方法，x = -y, y = x
public void rote() {
	
	//边界旋转的判断，对每个点进行模拟旋转，如果会超过边界，则return
	for(Point point:blocks.points) {
		int _x = -point.y + x;
		int _y = point.x + y ;	
		if(_x < 0 || _x> 19) {
			return ;
		}
		if(_y > 19 || _y<-2) {
			return ;
		}
	if(existBlocks[_x][_y + 2 ]!=0) {
		return;
	}
	if(current == 4) {
		return;
	}
	}
	//遍历每个方块，更改X坐标和Y坐标  x = -y, y = x
	for(Point point:blocks.points) {
		int temp = point.x;
		point.x = -point.y;
		point.y = temp;	
	}
}

//保存方块流
void saveBlocks() {
	for(Point point:blocks.points) {
		existBlocks[point.x + x][point.y + y + 2] = current + 1;
	}	
}

//检测消行
boolean deletTest() {
	boolean isdelet = false;
	boolean isEmpty;
	for(int i = 21 ; i >= 2 ; i--) {
		isEmpty = false ;
		for(int j = 0 ; j < 20 ; j++) {
			if(existBlocks[j][i]==0) {
				isEmpty = true;
				break;
			}
		}
		if(!isEmpty) {
			isdelet = true;
			deletnum[i-1] = deletnum[i]+1;
		}else {
			deletnum[i-1] = deletnum[i];
		}
	}
	return isdelet;
}

//消除行
void  deletLine() {
	for(int i = 21 ; i >= 2 ; i--) {
		for(int j = 0 ; j < 20 ; j++) {
			existBlocks[j][i + deletnum[i]] = existBlocks[j][i];
       }
	}
	score += deletnum[2]*10;
 }

//结束判断
boolean isgameover() {
for(int j = 0 ; j < 20 ; j++) {
	if(existBlocks[j][2]!=0) {
		return true;
	}
  }
return false;
}

public String getScore() {
	return "" + score;
}
}
