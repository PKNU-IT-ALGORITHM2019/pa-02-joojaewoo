package 알고리즘2주차;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class prog_assign02 {
	int x,y,path;
	static int count=0;
	public static min min;
	public prog_assign02(int a,int b){x=a;y=b;path=-1;}
	static public int N;
	static prog_assign02 [] list;
	public static boolean dist(int index,double dist,int num) {
		if(min.dist!=0&&min.dist<dist)
		{count++;return false;}
		else if(num==N-1)
		{int k=0;count++;
		for(;k<N;k++) {if(list[k].path==0)break;}
		dist+=calc(k,index);
		if(min.dist==0||dist<min.dist) {
		min.dist=dist;
		for(int i=0;i<N;i++) {
			if(list[i].path==-1)
				min.seq[N-1]=index;
			else
			min.seq[list[i].path]=i;
		}
		}return true;
		}
		list[index].path=num;
			int b=0;
			double temp=0;	
			for(int a=0;a<N;a++) {
				if(a!=index &&list[a].path==-1) {
					temp=calc(index,a);
					b=a;
					dist(b,dist+temp,num+1);					
				}
			}list[index].path=-1; num--;
			return false;
		}
	public static double calc(int i, int j)
	{double x=list[i].x-list[j].x;
	double y=list[i].y-list[j].y;
	return Math.sqrt(x*x+y*y);}
	public static void main(String[] args) {
		for(int i=0;i<7;i++) {
			try {
				Scanner file = new Scanner(new File("input"+i+".txt"));
				N=file.nextInt();
				list=new prog_assign02[N];
				min= new min(N);
				int j=0;
				while(j<N) {
					int buffer=file.nextInt();
					int buffer1=file.nextInt();
					list[j]=new prog_assign02(buffer,buffer1); 
					j++;
				}
				long start = System.currentTimeMillis();
				dist(0,0,0);
				file.close();
				System.out.println("input"+i+".txt");
				System.out.println(min.dist);
				for(int v=0;v<N;v++)
					System.out.print(min.seq[v]+" ");
				System.out.println();
				long end = System.currentTimeMillis();
				System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
				min.dist=0;count=0;
			}catch(FileNotFoundException e) {System.out.println("No file");
			System.exit(0);}
		}

	}
}
class min{
	double dist;
	int seq[];
	min(int n) {dist=0;seq=new int[n];}
}