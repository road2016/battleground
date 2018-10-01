package ccsp2018;

import java.util.ArrayList;
import java.util.Scanner;

class Person{
    int x1;
    int y1;
    int h;   //玩家初始生命
    public Person(int x1,int y1,int h){
    	this.x1=x1;
    	this.y1=y1;
    	this.h=h;
    }
    public Person(){
    	super();
    }
}

public class Navie {
	private static void addPerson(ArrayList temp,int xold,int yold,int xnew,int ynew,int[][] obstacles,int h,int n){
		if(xnew<0||xnew>=n||ynew<0||ynew>=n){
			System.out.println("数组角标越界");
			return;
		}
		if(obstacles[xnew][ynew]==1){
			System.out.println("遇到障碍物了");
			return;
		}
		if(Math.abs(xnew-xold)+Math.abs(ynew-yold)==2){
			if(obstacles[xold][ynew]==1||obstacles[xnew][yold]==1){
				System.out.println("======你穿不过障碍物的===========");return;
			}
		}
		temp.add(new Person(xnew,ynew,h-1));
	}
	private static Person bfs(Person person,int safeAreaX,int safeAreaY,int radius,int[][] obstacles,int n){
		ArrayList<Person> toCheck = new ArrayList<Person>();
		toCheck.add(person);
		int aa=0;
		while(!toCheck.isEmpty()){
			ArrayList temp = new ArrayList();
			for(Person p:toCheck){
				int x1=p.x1;
				int y1=p.y1;
				int h=p.h;
				if((x1-safeAreaX)*(x1-safeAreaX)+(y1-safeAreaY)*(y1-safeAreaY)<=radius*radius){
					return p;
				}
				else{
					addPerson(temp,x1,y1,x1-1,y1+1,obstacles,h,n);
					addPerson(temp,x1,y1,x1,y1+1,obstacles,h,n);
					addPerson(temp,x1,y1,x1+1,y1+1,obstacles,h,n);
					addPerson(temp,x1,y1,x1+1,y1,obstacles,h,n);
					addPerson(temp,x1,y1,x1+1,y1-1,obstacles,h,n);
					addPerson(temp,x1,y1,x1,y1-1,obstacles,h,n);
					addPerson(temp,x1,y1,x1-1,y1-1,obstacles,h,n);
					addPerson(temp,x1,y1,x1-1,y1,obstacles,h,n);
				}	
				toCheck=temp;
			}//for end
		}
		return null;
	}
	public static void main(String args[]){
        Scanner sin = new Scanner(System.in);
        /*获取输入第一行*/
          System.out.println("请输入n,m,j,k： ");
//        System.out.println("请输入n： ");
        int n = sin.nextInt(); //棋盘大小
//        System.out.println("请输入m： ");
        int m = sin.nextInt(); //玩家个数
//        System.out.println("请输入j： ");
        int j = sin.nextInt();//障碍物个数
//        System.out.println("请输入k： ");
        int k = sin.nextInt();//回合数量
        /*录入玩家坐标*/
        Person[] persons = new Person[m]; //声明玩家坐标数组
        int[][] obstacles=new int[n][n];

        for(int i=0;i<j;i++){//录入障碍物的坐标
        	System.out.println("请输入障碍物x1,y1： ");
//            System.out.println("请输入障碍物x1： ");
            int x1=sin.nextInt();
//            System.out.println("请输入障碍物y1： ");
        	int y1=sin.nextInt();
        	obstacles[x1][y1]=1;
        }
        for(int i=0;i<m;i++){//录入玩家初始信息
        	System.out.println("请输入玩家x1,y1,初始生命值： ");
            persons[i] = new Person();
//            System.out.println("请输入玩家初始x1： ");
            persons[i].x1 = sin.nextInt();
//            System.out.println("请输入玩家初始y1： ");
            persons[i].y1 = sin.nextInt();
//            System.out.println("请输入玩家初始生命值： ");
            persons[i].h = sin.nextInt();   //玩家初始生命
        }
        for(int i=0;i<k;i++){
        	System.out.println("请输入安全区x1,y1,安全区半径： ");
//        	System.out.println("请输入安全区x1： ");
        	int safeAreaX=sin.nextInt();
//        	System.out.println("请输入安全区y1： ");
        	int safeAreaY=sin.nextInt();
//        	System.out.println("请输入安全区半径： ");
        	int radius=sin.nextInt();
        	for(int ii=0;ii<m;ii++){     //m回合s
        		System.out.println("m = "+m);
        		System.out.println("请输入x1,y1： "+ii);
//        		System.out.println("请输入x1： "+ii);
        		persons[ii].x1 = sin.nextInt();
//        		System.out.println("请输入y1： ");
                persons[ii].y1 = sin.nextInt();
                persons[ii]=bfs(persons[ii],safeAreaX,safeAreaY,radius,obstacles,n);
                System.out.println("最后的结果是："+persons[ii].h);
                System.out.println(persons[ii].x1+" "+persons[ii].y1+" "+persons[ii].h);
        	}
        }
	}
}
