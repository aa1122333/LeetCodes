package common;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
public class Main{
    /*public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        HashSet<String> set = new HashSet<>();
        while(in.hasNext()){
        	String s = in.next();
        	set.add(s);
        }
        System.out.println(set.size());
    }*/
	
	
	public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        while(in.hasNext()){
        	int x = in.nextInt();
        	int y = in.nextInt();
        	char [][]map = new char[x][y];
        	for(int i=0;i<x;i++){
        		map[i] = in.next().toCharArray();
        	}
        	int sum = 0;
        	int x0=in.nextInt();
        	int y0=in.nextInt();
        	int steps = in.nextInt();
        	int [][] step = new int[steps][2];
        	for(int i=0;i<steps;i++){
        		step[i][0] = in.nextInt();
        		step[i][1] = in.nextInt();
        	}
        	Queue<point> s = new LinkedList<point>();
        	boolean [][]visited = new boolean[x][y];
        	s.add(new point(x0,y0,0));
        	visited[x0][y0] = true;
        	while(!s.isEmpty()){
        		point t= s.poll();
        		for(int i=0;i<steps;i++){
        			int currx = t.x+step[i][0];
        			int curry = t.y+step[i][1];
        			if(currx<x &&curry<y && currx>=0 && curry>=0 && map[currx][curry]=='.' && !visited[currx][curry]){
        				visited[currx][curry] = true;
        				if(t.s+1>sum)
        					sum = t.s+1;
        				s.add(new point(currx,curry,t.s+1));
        			}
        		}
        	}
        	boolean flag = false;
        	for(int i=0;i<x;i++){
        		for(int j=0;j<y;j++){
        			if(visited[i][j]==false && map[i][j]=='.'){
        				System.out.println(-1);
        				flag = true;
        				break;
        			}
        		}
        		if(flag)
        			break;
        	}
        	if(!flag)
        		System.out.println(sum);
        	else 
        		System.out.println(-1);
        }
    }
	/*public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        while(in.hasNext()){
        	
        }
    }*/
}
class point{
	public int x;
	public int y;
	public int s;
	public point(int x,int y,int s){
		this.x = x;
		this.y = y;
		this.s = s;
	}
}
