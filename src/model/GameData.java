package model;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import dao.PlaydataUnit;
import dao.PlayerData;
import model.Block;

public class GameData {
	public Block blocks;//��ʾ����Ϸ���ķ���
	 public Block [] BLOCKS = new Block [] {
			new Block(new int [] {-1,0,1,1},new int [] {0,0,0,1}),
			new Block(new int [] {-1,0,1,2},new int [] {0,0,0,0}),
			new Block(new int [] {-1,-1,0,1},new int [] {0,1,0,0}),
			new Block(new int [] {-1,0,0,1},new int [] {0,0,1,1}),
			new Block(new int [] {0,0,1,1},new int [] {0,1,0,1}),
			new Block(new int [] {-1,0,0,1},new int [] {1,0,1,0}),
			new Block(new int [] {-1,0,0,1},new int [] {0,0,1,0}),
	};
    
	//������ɫ
	public Color [] colors = new Color[] {
			new Color(255,0,0),
			new Color(0,255,0),
			new Color(0,0,255),
			new Color(255,255,0),
			new Color(255,0,255),
			new Color(0,255,255),
			new Color(255,106,106),
	};
	
	//�����ı���Ϣ
	public String[] stst_text = new String[] {"��ʼ","��ͣ","����","����"};
	
	//ƫ����
	public int x  ;//����ƫ����
	public int y  ;//����ƫ����
	//��Ÿ��ӵ�����
	public int[][] existBlocks;
	//���ɾ������������
	int[] deletnum;
	//�������
	Random random;
	//��һ��
	public int next;
	//��ǰ�ĸ��ӱ��
	public int current;
	//��¼����
	public int score;
	//�����Ϸ״̬����ʼ����ͣ��������������
	public int state;
	//��Ϸ��¼
  public PlaydataUnit playerData;
  public String nick;
   public String pass;
//��¼״̬
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

//������ƶ����߽��ж�
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

//��������ķ���
private void initBlocks() {
	x = 4;//��ʼλ��
	y = -2;
	deletnum = new int [22];
	blocks = new Block(BLOCKS [next]);
	current = next ; 
	next = random.nextInt(7);
	
}

//��ת������x = -y, y = x
public void rote() {
	
	//�߽���ת���жϣ���ÿ�������ģ����ת������ᳬ���߽磬��return
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
	//����ÿ�����飬����X�����Y����  x = -y, y = x
	for(Point point:blocks.points) {
		int temp = point.x;
		point.x = -point.y;
		point.y = temp;	
	}
}

//���淽����
void saveBlocks() {
	for(Point point:blocks.points) {
		existBlocks[point.x + x][point.y + y + 2] = current + 1;
	}	
}

//�������
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

//������
void  deletLine() {
	for(int i = 21 ; i >= 2 ; i--) {
		for(int j = 0 ; j < 20 ; j++) {
			existBlocks[j][i + deletnum[i]] = existBlocks[j][i];
       }
	}
	score += deletnum[2]*10;
 }

//�����ж�
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
