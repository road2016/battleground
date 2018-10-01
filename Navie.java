package ccsp2018;

import java.util.ArrayList;
import java.util.Scanner;

class Person{
    int x1;
    int y1;
    int h;   //��ҳ�ʼ����
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
			System.out.println("����Ǳ�Խ��");
			return;
		}
		if(obstacles[xnew][ynew]==1){
			System.out.println("�����ϰ�����");
			return;
		}
		if(Math.abs(xnew-xold)+Math.abs(ynew-yold)==2){
			if(obstacles[xold][ynew]==1||obstacles[xnew][yold]==1){
				System.out.println("======�����ϰ���֮�䲻�ܴ���===========");return;
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
        /*��ȡ�����һ��*/
          System.out.println("������n,m,j,k�� ");
        int n = sin.nextInt(); //���̴�С
        int m = sin.nextInt(); //��Ҹ���
        int j = sin.nextInt();//�ϰ������
        int k = sin.nextInt();//�غ�����
        /*¼���������*/
        Person[] persons = new Person[m]; //���������������
        int[][] obstacles=new int[n][n];

        for(int i=0;i<j;i++){//¼���ϰ��������
        	System.out.println("�������ϰ���x1,y1�� ");
            int x1=sin.nextInt();
        	int y1=sin.nextInt();
        	obstacles[x1][y1]=1;
        }
        for(int i=0;i<m;i++){//¼����ҳ�ʼ��Ϣ
        	System.out.println("���������x1,y1,��ʼ����ֵ�� ");
            persons[i] = new Person();
            persons[i].x1 = sin.nextInt();
            persons[i].y1 = sin.nextInt();
            persons[i].h = sin.nextInt();   //��ҳ�ʼ����
        }
        for(int i=0;i<k;i++){
        	System.out.println("�����밲ȫ��x1,y1,��ȫ���뾶�� ");
        	int safeAreaX=sin.nextInt();
        	int safeAreaY=sin.nextInt();
        	int radius=sin.nextInt();
        	for(int ii=0;ii<m;ii++){     //m�غ�s
        		System.out.println("��ǰ�غ����� "+ii);
        		System.out.println("������x1,y1�� ");
        		persons[ii].x1 = sin.nextInt();
                persons[ii].y1 = sin.nextInt();
                persons[ii]=bfs(persons[ii],safeAreaX,safeAreaY,radius,obstacles,n);
                System.out.println(persons[ii].x1+" "+persons[ii].y1+" "+persons[ii].h);
        	}
        }
	}
}
