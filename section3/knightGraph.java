import java.util.List;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.StringTokenizer;

class GraphNode {

	int move;
	int step;
	boolean visit;
	double p;
	int x;
	int y;
	List<Integer> values;
	GraphNode[] children;
	GraphNode north;
	GraphNode south;
	GraphNode east;
	GraphNode west;
	

	int N = 0;
	int E = 0;
	int S = 0;
	int W = 0;
	

	GraphNode(int x, int y, int move) {
		
		this.x = x;
		this.y = y;
		this.move = move;
		this.p = 0.0;
		this.visit = false;
		
	}
	GraphNode(){
		
		
	}
}

public class knightGraph {
	
	GraphNode rootGraphNode;
	final static int numNodes = 100;
	
	int totalnodes = 0;
	
	knightGraph() {
		rootGraphNode = null;
	}


	void buildAllPath( int numMoves) {
		GraphNode root = new GraphNode(0,0,0);
		//Queue q = new LinkedList();
		GraphNode  aNode =null ;

		int step = 0;
		int move = 0;
		int count = 0;
		BufferedReader in = null;
		BufferedWriter  out = null;
		

		try {
			

			out = new BufferedWriter(new FileWriter("queue-" + numMoves+ ".csv"));
			out.write("NodeX, NodeY, Move, p, Direction \n");
			out.flush();
			in = new BufferedReader(new FileReader("queue-" + numMoves+ ".csv"));
			String aQueue = in.readLine();
		// initialize root
			step = 2;
			
			
			
			move = 0;
			count = 0;
			
			root.p = 1.0;
			root.move = move;
			//root.visit = true;

			if(root.x + 2 > 0) {
				if(root.y + 1 > 0)
					count++;
				if(root.y - 1 > 0)
					count++;
			}

			if(root.x - 2 >0) {

				if(root.y + 1 > 0)
					count++;
				if(root.y - 1 > 0)
					count++;
			}
			
			if(root.y+2 >0) {
				if(root.x +1 > 0)
					count++;
				if(root.x -1>0)
					count++;
			}
			
			if(root.y - 2 >0) {
				if(root.x +1 > 0)
					count++;
				if(root.x -1>0)
					count++;
			}
			
			if(count >0) {
				move++;
				//east
				if(root.x + 2 > 0) {
					//north
					if(root.y + 1 > 0) {
						
						out.write((root.x+2) + "," + (root.y+1) + "," + move +"," + (1.0/count) + ",N" + "\n");

						out.flush();
						//q.add(nodes[root.x + 2][root.y + 1]);
						//System.out.println("visit(x, y, p) from ("+root.x + "," + root.y +"," + root.p+") to(" +nodes[root.x + 2][root.y + 1].x + "," + nodes[root.x + 2][root.y + 1].y + "," + nodes[root.x + 2][root.y + 1].p + "," + nodes[root.x + 2][root.y + 1].move);
						
					}
					//south
					if(root.y - 1 > 0){

						out.write((root.x+2) + "," + (root.y-1) + "," + move +"," + (1.0/count) + ", S" + "\n");

						out.flush();
						//q.add(nodes[root.x + 2][root.y - 1]);
						//System.out.println("visit(x, y, p)  from ("+root.x + "," + root.y +"," + root.p+") to(" +nodes[root.x + 2][root.y - 1].x + "," + nodes[root.x - 2][root.y - 1].y + "," + nodes[root.x + 2][root.y - 1].p + "," + nodes[root.x + 2][root.y - 1].move);
						
					}
				}

				//west
				if(root.x - 2 >0) {

					//north
					if(root.y + 1 > 0){
						
						out.write((root.x-2) + "," + (root.y+1) + "," + move +"," + (1.0/count) + ",N" + "\n");

						out.flush();
						//q.add(nodes[root.x - 2][root.y + 1]);
						//System.out.println("visit(x, y, p)  from ("+root.x + "," + root.y +"," + root.p+") to(" +nodes[root.x - 2][root.y + 1].x + "," + nodes[root.x - 2][root.y + 1].y + "," + nodes[root.x - 2][root.y + 1].p + "," + nodes[root.x - 2][root.y + 1].move);
						
					}
					//south
					if(root.y - 1 > 0){
						
						out.write((root.x-2) + "," + (root.y-1) + "," + move +"," + (1.0/count) + ", S" + "\n");

						out.flush();
						//q.add(nodes[root.x - 2][root.y - 1]);
						//System.out.println("visit(x, y, p)  from ("+root.x + "," + root.y +"," + root.p+") to(" +nodes[root.x - 2][root.y - 1].x + "," + nodes[root.x - 2][root.y - 1].y + "," + nodes[root.x - 2][root.y - 1].p + "," + nodes[root.x - 2][root.y - 1].move);
						
					}
				}
				
				//north
				if(root.y+2 >0) {
					//east
					if(root.x +1 > 0){
						
						out.write((root.x+1) + "," + (root.y+2) + "," + move +"," + (1.0/count) + ",E" + "\n");

						out.flush();
						//q.add(nodes[root.x + 1][root.y + 2]);
						//System.out.println("visit(x, y, p)  from ("+root.x + "," + root.y +"," + root.p+") to(" +nodes[root.x + 1][root.y + 2].x + "," + nodes[root.x + 1][root.y + 2].y + "," + nodes[root.x + 1][root.y + 2].p+ "," + nodes[root.x + 1][root.y + 2].move);
						
					}
					//west
					if(root.x -1>0){
						
						out.write((root.x-1) + "," + (root.y+2) + "," + move +"," + (1.0/count) + ",W" + "\n");

						out.flush();
						//q.add(nodes[root.x - 1][root.y + 2]);
						//System.out.println("visit(x, y, p)  from ("+root.x + "," + root.y +"," + root.p+") to(" +nodes[root.x -1][root.y + 2].x + "," + nodes[root.x -1][root.y + 2].y + "," + nodes[root.x -1][root.y + 2].p + "," + nodes[root.x -1][root.y + 2].move);
						
					}
				}
				
				//south
				if(root.y - 2 >0) {
					//east
					if(root.x +1 > 0){
						
						out.write((root.x+1) + "," + (root.y-2) + "," + move +"," + (1.0/count) + ",E" + "\n");

						out.flush();
						//q.add(nodes[root.x + 1][root.y - 2]);
						//System.out.println("visit(x, y, p)  from ("+root.x + "," + root.y +"," + root.p+") to(" +nodes[root.x + 1][root.y -2].x + "," + nodes[root.x + 1][root.y -2].y + "," + nodes[root.x + 1][root.y -2].p + "," + nodes[root.x + 1][root.y -2].move);
						
					}
					//west
					if(root.x -1>0){
						
						out.write((root.x-1) + "," + (root.y-2) + "," + move +"," + (1.0/count) + ",W" + "\n");

						out.flush();
						//q.add(nodes[root.x - 1][root.y - 2]);
						//System.out.println("visit(x, y, p)  from ("+root.x + "," + root.y +"," + root.p+") to(" +nodes[root.x -1][root.y -2].x + "," + nodes[root.x -1][root.y -2].y + "," + nodes[root.x -1][root.y -2].p+ "," + nodes[root.x -1][root.y -2].move);
						
					}
				}
			}

		
		
		
		while(true) {
			aQueue = in.readLine();
			//aNode = (GraphNode)q.poll();
			
			StringTokenizer st = new StringTokenizer(aQueue, ",");
			int currentX = Integer.parseInt(st.nextToken().toString());
			int currentY = Integer.parseInt(st.nextToken().toString());
			int currentMove = Integer.parseInt(st.nextToken().toString());
			double currentP = Double.parseDouble(st.nextToken().toString());
			String currentDirection = st.nextToken().toString();
			//if(aNode.x == currentX && aNode.y == currentY) {
			aNode = new GraphNode(currentX, currentY, currentMove);
				aNode.move = currentMove;
				aNode.p = currentP;
				
				if(currentDirection.equals("E")) 
					aNode.E = 1;
				if(currentDirection.equals("S")) 
					aNode.S = 1;
				if(currentDirection.equals("W")) 
					aNode.W = 1;
				if(currentDirection.equals("N")) 
					aNode.N = 1;
			//}
			
			
			//if(aNode!=null) {
				
				move = aNode.move;
				count = 0;
				
				if(aNode.x + 2 >= 0 && (aNode.N==1 || aNode.S==1)) {
					if(aNode.y + 1 > 0)
						count++;
					if(aNode.y - 1 > 0)
						count++;
				}

				if(aNode.x - 2 >= 0 && (aNode.N==1 || aNode.S==1)) {

					if(aNode.y + 1 > 0)
						count++;
					if(aNode.y - 1 > 0)
						count++;
				}
				
				if(aNode.y+2 >= 0 && (aNode.E==1 || aNode.W==1)) {
					if(aNode.x +1 > 0)
						count++;
					if(aNode.x -1>0)
						count++;
				}
				
				if(aNode.y - 2 >= 0 && (aNode.E==1 || aNode.W==1)) {
					if(aNode.x +1 > 0)
						count++;
					if(aNode.x -1>0)
						count++;
				}
				
				if(count >0) {
					move++;
					if(move > numMoves)
						break;
					
					if(aNode.x + 2 >= 0 && (aNode.N==1 || aNode.S==1)) {
						if(aNode.y + 1 >= 0 ) {
							
							out.write((aNode.x+2) + "," + (aNode.y+1) + "," +move +"," + (aNode.p/count) + ",N" + "\n");

							out.flush();
							//q.add(nodes[aNode.x + 2][aNode.y + 1]);
							//System.out.println("visit(x, y, p)  from ("+aNode.x + "," + aNode.y +"," + aNode.p+") to(" +nodes[aNode.x + 2][aNode.y + 1].x + "," + nodes[aNode.x + 2][aNode.y + 1].y + "," + nodes[aNode.x + 2][aNode.y + 1].p + "," + nodes[aNode.x + 2][aNode.y + 1].move);
							
						}
						if(aNode.y - 1 >= 0 ){

							out.write((aNode.x+2) + "," + (aNode.y-1) + "," + move +"," + (aNode.p/count) + ", S" + "\n");

							out.flush();
							//q.add(nodes[aNode.x + 2][aNode.y - 1]);
							//System.out.println("visit(x, y, p)   from ("+aNode.x + "," + aNode.y +"," + aNode.p+") to(" +nodes[aNode.x + 2][aNode.y - 1].x + "," + nodes[aNode.x + 2][aNode.y - 1].y + "," + nodes[aNode.x + 2][aNode.y - 1].p + "," +nodes[aNode.x + 2][aNode.y - 1].move);
							
						}
					}

					if(aNode.x - 2 >= 0 && (aNode.N==1 || aNode.S==1)) {

						if(aNode.y + 1 >= 0  ){
							
							out.write((aNode.x-2) + "," + (aNode.y+1) + "," + move +"," + (aNode.p/count) + ",N" + "\n");

							out.flush();
							//q.add(nodes[aNode.x - 2][aNode.y + 1]);
							//System.out.println("visit(x, y, p)   from ("+aNode.x + "," + aNode.y +"," + aNode.p+") to(" +nodes[aNode.x - 2][aNode.y + 1].x + "," + nodes[aNode.x - 2][aNode.y + 1].y + "," + nodes[aNode.x - 2][aNode.y + 1].p + "," + nodes[aNode.x - 2][aNode.y + 1].move);
							
						}
						if(aNode.y - 1 >= 0 ){
							
							out.write((aNode.x-2) + "," + (aNode.y-1) + "," + move +"," + (aNode.p/count) + ", S" + "\n");

							out.flush();
							//q.add(nodes[aNode.x - 2][aNode.y - 1]);
							//System.out.println("visit(x, y, p)   from ("+aNode.x + "," + aNode.y +"," + aNode.p+") to(" +nodes[aNode.x - 2][aNode.y - 1].x + "," + nodes[aNode.x - 2][aNode.y - 1].y + "," + nodes[aNode.x - 2][aNode.y - 1].p  + "," + nodes[aNode.x - 2][aNode.y - 1].move);
							
						}
					}
					
					if(aNode.y+2 >=0 && (aNode.E==1 || aNode.W==1)) {
						if(aNode.x +1 >= 0  ){
							
							out.write((aNode.x+1) + "," + (aNode.y+2) + "," + move +"," + (aNode.p/count) + ",E" + "\n");

							out.flush();
							//q.add(nodes[aNode.x + 1][aNode.y + 2]);
							//System.out.println("visit(x, y, p)  from ("+aNode.x + "," + aNode.y +"," + aNode.p+") to(" +nodes[aNode.x + 1][aNode.y + 2].x + "," + nodes[aNode.x + 1][aNode.y + 2].y + "," + nodes[aNode.x + 1][aNode.y + 2].p + "," + nodes[aNode.x + 1][aNode.y + 2].move);
							
						}
						if(aNode.x -1>= 0 ){
							
							out.write((aNode.x-1) + "," + (aNode.y+1) + "," + move +"," + (aNode.p/count) + ",W" + "\n");

							out.flush();
							//q.add(nodes[aNode.x - 1][aNode.y + 2]);
							//System.out.println("visit(x, y, p)  from ("+aNode.x + "," + aNode.y +"," + aNode.p+") to(" +nodes[aNode.x -1][aNode.y + 2].x + "," + nodes[aNode.x -1][aNode.y + 2].y + "," + nodes[aNode.x -1][aNode.y + 2].p + "," + nodes[aNode.x + 1][aNode.y + 2].move);
							
						}
					}
					
					if(aNode.y - 2 >=0 && (aNode.E==1 || aNode.W==1)) {
						if(aNode.x +1 >= 0 ){
							
							out.write((aNode.x+1) + "," + (aNode.y-2) + "," + move +"," + (aNode.p/count) + ",E" + "\n");

							out.flush();
							//q.add(nodes[aNode.x + 1][aNode.y - 2]);
							//System.out.println("visit(x, y, p)  from ("+aNode.x + "," + aNode.y +"," + aNode.p+") to(" +nodes[aNode.x + 1][aNode.y -2].x + "," + nodes[aNode.x + 1][aNode.y -2].y + "," + nodes[aNode.x + 1][aNode.y -2].p + "," + nodes[aNode.x + 1][aNode.y + 2].move);
							
						}
						if(aNode.x -1>= 0 ){
							
							out.write((aNode.x-1) + "," + (aNode.y-2) + "," + move +"," + (aNode.p/count) + ",W" + "\n");

							out.flush();
							//q.add(nodes[aNode.x - 1][aNode.y - 2]);
							//System.out.println("visit(x, y, p)  from ("+aNode.x + "," + aNode.y +"," + aNode.p+") to(" +nodes[aNode.x -1][aNode.y -2].x + "," + nodes[aNode.x -1][aNode.y -2].y + "," + nodes[aNode.x -1][aNode.y -2].p + "," + nodes[aNode.x + 1][aNode.y + 2].move);
							
						}
					}
					out.flush();
				}
					
				
				
			}
		//}
		
		out.close();
		in.close();
		
		}
		catch(IOException ioe){}

	}
	
	
	public double[] distance(String filename, int moves, int condition) {
		
		BufferedReader in = null;

		double distTotal = 0;
		long count = 0;
		
		try {
			in = new BufferedReader(new FileReader(filename));
			String data = in.readLine();
			data = in.readLine();
			while(data!=null && data.length() >0) {
				StringTokenizer st = new StringTokenizer(data, ",");
				double x = Double.parseDouble(st.nextToken().toString());
				double y = Double.parseDouble(st.nextToken().toString());
				int m = Integer.parseInt(st.nextToken().toString());
				double p = Double.parseDouble(st.nextToken().toString());
				if(m >= moves ) {
					double dist = Math.sqrt(x*x+y*y);
					if(dist >= condition) {
						distTotal += dist*p;
						count++;
					}
				}
				data = in.readLine();
				
			}
			
			
			
			in.close();
		}
		catch(IOException ex) {
		}
		
		double[] results = {distTotal, (double)count};
		
		return results;
	}
	
	
	public double stdev(String filename, int moves, int condition, double distance, double count) {
		
		BufferedReader in = null;

		double devTotal = 0;
		double mean = distance/count;
		
		try {
			in = new BufferedReader(new FileReader(filename));
			String data = in.readLine();
			data = in.readLine();
			while(data!=null && data.length() >0) {
				StringTokenizer st = new StringTokenizer(data, ",");
				double x = Double.parseDouble(st.nextToken().toString());
				double y = Double.parseDouble(st.nextToken().toString());
				int m = Integer.parseInt(st.nextToken().toString());
				double p = Double.parseDouble(st.nextToken().toString());
				
				if(m >= moves ) {
					double dist = Math.sqrt(x*x+y*y);
					if(dist >= condition) {
						
						devTotal += Math.pow(dist*p-mean, 2);
						
					}
				}
				
				
				data = in.readLine();
				
			}
			
			
			
			in.close();
		}
		catch(IOException ex) {
		}
		
		double result = Math.sqrt(devTotal/count);
		return result;
	}
	
	
	public static void main(String[] args) {
		
		int moves = 2;
		int condition = 10;
		knightGraph app = new knightGraph();
		int total = 0; 
		double totaldistance = 0;
		
		for(moves = 2; moves < 18; moves++) {
			//app.buildAllPath(moves);
			//System.out.println("finished moves " + moves);
			
			String filename = "queue-"+ moves +".csv";
			double[] distResults = app.distance(filename, moves, condition);
			double stdev = app.stdev(filename, moves, condition, distResults[0], distResults[1]);
			
			System.out.println("moves:" + moves + ", condition:" + condition + ", #records:" + distResults[1]+ ", distance:" + (distResults[0]/distResults[1]) + ", stdev:" + stdev);
			
		}
		
	}

}
