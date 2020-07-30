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
	

	GraphNode(int x, int y, int step, int move) {
		
		this.x = x;
		this.y = y;
		this.step = step;
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
	GraphNode[][] nodes = new GraphNode[numNodes*3][numNodes*3];
	ArrayList leaves;
	
	int totalnodes = 0;
	
	knightGraph() {
		rootGraphNode = null;
		leaves = new ArrayList();
		for(int i = 0; i < numNodes*3; i++) {
			nodes[i] = new GraphNode[numNodes*3];
			for(int j = 0; j < numNodes*3; j++) {
				nodes[i][j] = new GraphNode(i, j, 0,0);
			}
		}
	}

	public double stdev(int moves, double maxDistance, double totalDistance) {
		double distance = 0;

		int count = 0;
		
		if(maxDistance > 0) {
			for(int i = 0; i < leaves.size(); i++) {
				GraphNode aNode = (GraphNode)leaves.get(i);
				double euclidian = aNode.p*Math.sqrt((Math.pow(aNode.x,2)+Math.pow(aNode.y,2)));
				if( maxDistance > 0 && euclidian > maxDistance)
					count++;
			}
			
			double mean = totalDistance /count;
			for(int i = 0; i < leaves.size(); i++) {
				GraphNode aNode = (GraphNode)leaves.get(i);
				double euclidian = aNode.p*Math.sqrt((Math.pow(aNode.x,2)+Math.pow(aNode.y,2)));
				if( maxDistance > 0 && euclidian > maxDistance)
					distance +=Math.pow((euclidian -mean), 2);
			}

			distance = Math.sqrt(distance/count);
			
		}
		else {
			count = leaves.size();
			double mean = totalDistance/count;
			for(int i = 0; i < leaves.size(); i++) {
				GraphNode aNode = (GraphNode)leaves.get(i);
				double euclidian = aNode.p*Math.sqrt((Math.pow(aNode.x,2)+Math.pow(aNode.y,2)));
				
				distance += Math.pow((euclidian-mean),2);
				
			}
			
			distance = Math.sqrt(distance/count);
		}
		
		
		return distance;
	}
	
	public double expectedDistances(GraphNode aNode, int moves, double maxDistance) {
		double distance = 0;
		double p = 0;
		//for(int i = 0; i < leaves.size(); i++) {
			
			double euclidian = aNode.p*Math.sqrt((Math.pow(aNode.x,2)+Math.pow(aNode.y,2)));
			if( maxDistance > 0 && euclidian > maxDistance)
				distance += euclidian;
			else if(maxDistance < 0) {
				distance += euclidian;
			}
		//}
		
		
		return distance;
	}
	
	public void totalDistances(int numMoves) {
		double total = 0;
		
		//try {
		//BufferedWriter out = new BufferedWriter(new FileWriter("knightsgraph-moves-" + numMoves + ".csv"));
		//out.write("x, y, p \n");
		for(int i = 0; i < numNodes*3; i++) {
			for(int j = 0; j < numNodes*3; j++) {
				//if(nodes[i][j].visit && nodes[i][j].move == numMoves ) {
				if( nodes[i][j].move == numMoves ) {
					double x = (double)nodes[i][j].x;
					double y = (double)nodes[i][j].y;
					double p = nodes[i][j].p;
					total += p*Math.sqrt(x*x+y*y);
					System.out.println("distance(x,y,p) " + x + "," + y + "," + p);
					//out.write(x + "," + y + "," + p + "\n");
					//out.flush();
				}
			}
		}
		System.out.println("moves=" + numMoves + ", total = " + total);
		//out.close();
		
		//}
		//catch(IOException ioe) {}
	}
	


	void buildAllPath( int numMoves) {
		GraphNode root = nodes[0][0];
		Queue q = new LinkedList();
		GraphNode  aNode =null ;

		int step = 0;
		int move = 0;
		int count = 0;
		BufferedReader in = null;
		BufferedWriter  out = null;
		

		try {
			

			out = new BufferedWriter(new FileWriter("queue.csv"));
			out.write("NodeX, NodeY, Move, p, Direction \n");
			out.flush();
			in = new BufferedReader(new FileReader("queue.csv"));
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
						nodes[root.x + 2][root.y + 1].move = move;
						nodes[root.x + 2][root.y + 1].p = 1.0/count;
						//nodes[root.x + 2][root.y + 1].visit =true;
						nodes[root.x + 2][root.y + 1].N = 1;
						out.write((root.x+2) + "," + (root.y+1) + "," + move +"," + (1.0/count) + ",N" + "\n");

						out.flush();
						q.add(nodes[root.x + 2][root.y + 1]);
						System.out.println("visit(x, y, p) from ("+root.x + "," + root.y +"," + root.p+") to(" +nodes[root.x + 2][root.y + 1].x + "," + nodes[root.x + 2][root.y + 1].y + "," + nodes[root.x + 2][root.y + 1].p + "," + nodes[root.x + 2][root.y + 1].move);
						
					}
					//south
					if(root.y - 1 > 0){

						nodes[root.x + 2][root.y - 1].move = move;
						nodes[root.x + 2][root.y - 1].p = 1.0/count;
						//nodes[root.x + 2][root.y - 1].visit =true;
						nodes[root.x + 2][root.y - 1].S =1;
						out.write((root.x+2) + "," + (root.y-1) + "," + move +"," + (1.0/count) + ", S" + "\n");

						out.flush();
						q.add(nodes[root.x + 2][root.y - 1]);
						System.out.println("visit(x, y, p)  from ("+root.x + "," + root.y +"," + root.p+") to(" +nodes[root.x + 2][root.y - 1].x + "," + nodes[root.x - 2][root.y - 1].y + "," + nodes[root.x + 2][root.y - 1].p + "," + nodes[root.x + 2][root.y - 1].move);
						
					}
				}

				//west
				if(root.x - 2 >0) {

					//north
					if(root.y + 1 > 0){
						nodes[root.x - 2][root.y + 1].move = move;
						nodes[root.x - 2][root.y + 1].p = 1.0/count;
						//nodes[root.x - 2][root.y + 1].visit =true;
						nodes[root.x - 2][root.y + 1].N =1;
						out.write((root.x-2) + "," + (root.y+1) + "," + move +"," + (1.0/count) + ",N" + "\n");

						out.flush();
						q.add(nodes[root.x - 2][root.y + 1]);
						System.out.println("visit(x, y, p)  from ("+root.x + "," + root.y +"," + root.p+") to(" +nodes[root.x - 2][root.y + 1].x + "," + nodes[root.x - 2][root.y + 1].y + "," + nodes[root.x - 2][root.y + 1].p + "," + nodes[root.x - 2][root.y + 1].move);
						
					}
					//south
					if(root.y - 1 > 0){
						nodes[root.x - 2][root.y - 1].move = move;
						nodes[root.x - 2][root.y - 1].p = 1.0/count;
						//nodes[root.x - 2][root.y - 1].visit =true;
						nodes[root.x - 2][root.y - 1].S =1;
						out.write((root.x-2) + "," + (root.y-1) + "," + move +"," + (1.0/count) + ", S" + "\n");

						out.flush();
						q.add(nodes[root.x - 2][root.y - 1]);
						System.out.println("visit(x, y, p)  from ("+root.x + "," + root.y +"," + root.p+") to(" +nodes[root.x - 2][root.y - 1].x + "," + nodes[root.x - 2][root.y - 1].y + "," + nodes[root.x - 2][root.y - 1].p + "," + nodes[root.x - 2][root.y - 1].move);
						
					}
				}
				
				//north
				if(root.y+2 >0) {
					//east
					if(root.x +1 > 0){
						nodes[root.x + 1][root.y + 2].move = move;
						nodes[root.x + 1][root.y + 2].p = 1.0/count;
						//nodes[root.x + 1][root.y + 2].visit =true;
						nodes[root.x + 1][root.y + 2].E =1;
						out.write((root.x+1) + "," + (root.y+2) + "," + move +"," + (1.0/count) + ",E" + "\n");

						out.flush();
						q.add(nodes[root.x + 1][root.y + 2]);
						System.out.println("visit(x, y, p)  from ("+root.x + "," + root.y +"," + root.p+") to(" +nodes[root.x + 1][root.y + 2].x + "," + nodes[root.x + 1][root.y + 2].y + "," + nodes[root.x + 1][root.y + 2].p+ "," + nodes[root.x + 1][root.y + 2].move);
						
					}
					//west
					if(root.x -1>0){
						nodes[root.x - 1][root.y + 2].move = move;
						nodes[root.x - 1][root.y + 2].p = 1.0/count;
						//nodes[root.x - 1][root.y + 2].visit =true;
						nodes[root.x - 1][root.y + 2].W =1;
						out.write((root.x-1) + "," + (root.y+2) + "," + move +"," + (1.0/count) + ",W" + "\n");

						out.flush();
						q.add(nodes[root.x - 1][root.y + 2]);
						System.out.println("visit(x, y, p)  from ("+root.x + "," + root.y +"," + root.p+") to(" +nodes[root.x -1][root.y + 2].x + "," + nodes[root.x -1][root.y + 2].y + "," + nodes[root.x -1][root.y + 2].p + "," + nodes[root.x -1][root.y + 2].move);
						
					}
				}
				
				//south
				if(root.y - 2 >0) {
					//east
					if(root.x +1 > 0){
						nodes[root.x + 1][root.y - 2].move = move;
						nodes[root.x + 1][root.y - 2].p = 1.0/count;
						//nodes[root.x + 1][root.y - 2].visit =true;
						nodes[root.x + 1][root.y - 2].E =1;
						out.write((root.x+1) + "," + (root.y-2) + "," + move +"," + (1.0/count) + ",E" + "\n");

						out.flush();
						q.add(nodes[root.x + 1][root.y - 2]);
						System.out.println("visit(x, y, p)  from ("+root.x + "," + root.y +"," + root.p+") to(" +nodes[root.x + 1][root.y -2].x + "," + nodes[root.x + 1][root.y -2].y + "," + nodes[root.x + 1][root.y -2].p + "," + nodes[root.x + 1][root.y -2].move);
						
					}
					//west
					if(root.x -1>0){
						nodes[root.x - 1][root.y - 2].move = move;
						nodes[root.x - 1][root.y - 2].p = 1.0/count;
						//nodes[root.x - 1][root.y - 2].visit =true;
						nodes[root.x - 1][root.y - 2].W =1;
						out.write((root.x-1) + "," + (root.y-2) + "," + move +"," + (1.0/count) + ",W" + "\n");

						out.flush();
						q.add(nodes[root.x - 1][root.y - 2]);
						System.out.println("visit(x, y, p)  from ("+root.x + "," + root.y +"," + root.p+") to(" +nodes[root.x -1][root.y -2].x + "," + nodes[root.x -1][root.y -2].y + "," + nodes[root.x -1][root.y -2].p+ "," + nodes[root.x -1][root.y -2].move);
						
					}
				}
			}

		
		
		
		while(!q.isEmpty()) {
			aQueue = in.readLine();
			aNode = (GraphNode)q.poll();
			StringTokenizer st = new StringTokenizer(aQueue, ",");
			int currentX = Integer.parseInt(st.nextToken().toString());
			int currentY = Integer.parseInt(st.nextToken().toString());
			int currentMove = Integer.parseInt(st.nextToken().toString());
			double currentP = Double.parseDouble(st.nextToken().toString());
			String currentDirection = st.nextToken().toString();
			if(aNode.x == currentX && aNode.y == currentY) {
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
			}
			
			
			if(aNode!=null) {
				
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
						if(aNode.y + 1 >= 0 && !nodes[aNode.x + 2][aNode.y + 1].visit) {
							nodes[aNode.x + 2][aNode.y + 1].move = move;
							nodes[aNode.x + 2][aNode.y + 1].p = aNode.p/count;
							//nodes[aNode.x + 2][aNode.y + 1].visit =true;
							nodes[aNode.x + 2][aNode.y + 1].N =1;
							out.write((aNode.x+2) + "," + (aNode.y+1) + "," +move +"," + (aNode.p/count) + ",N" + "\n");

							out.flush();
							q.add(nodes[aNode.x + 2][aNode.y + 1]);
							System.out.println("visit(x, y, p)  from ("+aNode.x + "," + aNode.y +"," + aNode.p+") to(" +nodes[aNode.x + 2][aNode.y + 1].x + "," + nodes[aNode.x + 2][aNode.y + 1].y + "," + nodes[aNode.x + 2][aNode.y + 1].p + "," + nodes[aNode.x + 2][aNode.y + 1].move);
							
						}
						if(aNode.y - 1 >= 0 && !nodes[aNode.x + 2][aNode.y - 1].visit){

							nodes[aNode.x + 2][aNode.y - 1].move = move;
							nodes[aNode.x + 2][aNode.y - 1].p = aNode.p/count;
							//nodes[aNode.x + 2][aNode.y - 1].visit =true;
							nodes[aNode.x + 2][aNode.y - 1].S =1;
							out.write((aNode.x+2) + "," + (aNode.y-1) + "," + move +"," + (aNode.p/count) + ", S" + "\n");

							out.flush();
							q.add(nodes[aNode.x + 2][aNode.y - 1]);
							System.out.println("visit(x, y, p)   from ("+aNode.x + "," + aNode.y +"," + aNode.p+") to(" +nodes[aNode.x + 2][aNode.y - 1].x + "," + nodes[aNode.x + 2][aNode.y - 1].y + "," + nodes[aNode.x + 2][aNode.y - 1].p + "," +nodes[aNode.x + 2][aNode.y - 1].move);
							
						}
					}

					if(aNode.x - 2 >= 0 && (aNode.N==1 || aNode.S==1)) {

						if(aNode.y + 1 >= 0  && !nodes[aNode.x - 2][aNode.y + 1].visit){
							nodes[aNode.x - 2][aNode.y + 1].move = move;
							nodes[aNode.x - 2][aNode.y + 1].p = aNode.p/count;
							//nodes[aNode.x - 2][aNode.y + 1].visit =true;
							nodes[aNode.x - 2][aNode.y + 1].N =1;
							out.write((aNode.x-2) + "," + (aNode.y+1) + "," + move +"," + (aNode.p/count) + ",N" + "\n");

							out.flush();
							q.add(nodes[aNode.x - 2][aNode.y + 1]);
							System.out.println("visit(x, y, p)   from ("+aNode.x + "," + aNode.y +"," + aNode.p+") to(" +nodes[aNode.x - 2][aNode.y + 1].x + "," + nodes[aNode.x - 2][aNode.y + 1].y + "," + nodes[aNode.x - 2][aNode.y + 1].p + "," + nodes[aNode.x - 2][aNode.y + 1].move);
							
						}
						if(aNode.y - 1 >= 0 && !nodes[aNode.x - 2][aNode.y - 1].visit){
							nodes[aNode.x - 2][aNode.y - 1].move = move;
							nodes[aNode.x - 2][aNode.y - 1].p = aNode.p/count;
							//nodes[aNode.x - 2][aNode.y - 1].visit =true;
							nodes[aNode.x - 2][aNode.y - 1].S =1;
							out.write((aNode.x-2) + "," + (aNode.y-1) + "," + move +"," + (aNode.p/count) + ", S" + "\n");

							out.flush();
							q.add(nodes[aNode.x - 2][aNode.y - 1]);
							System.out.println("visit(x, y, p)   from ("+aNode.x + "," + aNode.y +"," + aNode.p+") to(" +nodes[aNode.x - 2][aNode.y - 1].x + "," + nodes[aNode.x - 2][aNode.y - 1].y + "," + nodes[aNode.x - 2][aNode.y - 1].p  + "," + nodes[aNode.x - 2][aNode.y - 1].move);
							
						}
					}
					
					if(aNode.y+2 >=0 && (aNode.E==1 || aNode.W==1)) {
						if(aNode.x +1 >= 0  && !nodes[aNode.x + 1][aNode.y + 2].visit){
							nodes[aNode.x + 1][aNode.y + 2].move = move;
							nodes[aNode.x + 1][aNode.y + 2].p = aNode.p/count;
							//nodes[aNode.x + 1][aNode.y + 2].visit =true;
							nodes[aNode.x + 1][aNode.y + 2].E =1;
							out.write((aNode.x+1) + "," + (aNode.y+2) + "," + move +"," + (aNode.p/count) + ",E" + "\n");

							out.flush();
							q.add(nodes[aNode.x + 1][aNode.y + 2]);
							System.out.println("visit(x, y, p)  from ("+aNode.x + "," + aNode.y +"," + aNode.p+") to(" +nodes[aNode.x + 1][aNode.y + 2].x + "," + nodes[aNode.x + 1][aNode.y + 2].y + "," + nodes[aNode.x + 1][aNode.y + 2].p + "," + nodes[aNode.x + 1][aNode.y + 2].move);
							
						}
						if(aNode.x -1>= 0 && !nodes[aNode.x - 1][aNode.y + 2].visit){
							nodes[aNode.x - 1][aNode.y + 2].move = move;
							nodes[aNode.x - 1][aNode.y + 2].p = aNode.p/count;
							//nodes[aNode.x - 1][aNode.y + 2].visit =true;
							nodes[aNode.x - 1][aNode.y + 1].W =1;
							out.write((aNode.x-1) + "," + (aNode.y+1) + "," + move +"," + (aNode.p/count) + ",W" + "\n");

							out.flush();
							q.add(nodes[aNode.x - 1][aNode.y + 2]);
							System.out.println("visit(x, y, p)  from ("+aNode.x + "," + aNode.y +"," + aNode.p+") to(" +nodes[aNode.x -1][aNode.y + 2].x + "," + nodes[aNode.x -1][aNode.y + 2].y + "," + nodes[aNode.x -1][aNode.y + 2].p + "," + nodes[aNode.x + 1][aNode.y + 2].move);
							
						}
					}
					
					if(aNode.y - 2 >=0 && (aNode.E==1 || aNode.W==1)) {
						if(aNode.x +1 >= 0 && !nodes[aNode.x + 1][aNode.y - 2].visit){
							nodes[aNode.x + 1][aNode.y - 2].move = move;
							nodes[aNode.x + 1][aNode.y - 2].p = aNode.p/count;
							//nodes[aNode.x + 1][aNode.y - 2].visit =true;
							nodes[aNode.x + 1][aNode.y - 2].E =1;
							out.write((aNode.x+1) + "," + (aNode.y-2) + "," + move +"," + (aNode.p/count) + ",E" + "\n");

							out.flush();
							q.add(nodes[aNode.x + 1][aNode.y - 2]);
							System.out.println("visit(x, y, p)  from ("+aNode.x + "," + aNode.y +"," + aNode.p+") to(" +nodes[aNode.x + 1][aNode.y -2].x + "," + nodes[aNode.x + 1][aNode.y -2].y + "," + nodes[aNode.x + 1][aNode.y -2].p + "," + nodes[aNode.x + 1][aNode.y + 2].move);
							
						}
						if(aNode.x -1>= 0 && !nodes[aNode.x - 1][aNode.y - 2].visit){
							nodes[aNode.x - 1][aNode.y - 2].move = move;
							nodes[aNode.x - 1][aNode.y - 2].p = aNode.p/count;
							//nodes[aNode.x - 1][aNode.y - 2].visit =true;
							nodes[aNode.x - 1][aNode.y - 2].W =1;
							out.write((aNode.x-1) + "," + (aNode.y-2) + "," + move +"," + (aNode.p/count) + ",W" + "\n");

							out.flush();
							q.add(nodes[aNode.x - 1][aNode.y - 2]);
							System.out.println("visit(x, y, p)  from ("+aNode.x + "," + aNode.y +"," + aNode.p+") to(" +nodes[aNode.x -1][aNode.y -2].x + "," + nodes[aNode.x -1][aNode.y -2].y + "," + nodes[aNode.x -1][aNode.y -2].p + "," + nodes[aNode.x + 1][aNode.y + 2].move);
							
						}
					}
					out.flush();
				}
					
				
				
			}
		}
		
		out.close();
		in.close();
		
		}
		catch(IOException ioe){}

	}
	
	
	public static void main(String[] args) {
		
		int moves = 100;
		double maxDistance = -1;
		knightGraph app = new knightGraph();
		int total = 0; 
		double totaldistance = 0;
		/*
		for(int i = 2; i < 10; i++) {
			moves = i;
			app.rootGraphNode = null;
			GraphNode aNode = app.buildOnePath(0,0, moves);
			
			double distance = app.expectedDistances(aNode, moves, maxDistance);
			//double stdev = app.stdev(moves, maxDistance, distance);
			
			totaldistance += distance;
			
			
			total += app.totalnodes;
			System.out.println("Finish moves= " + moves + " distance=" + (total));
		}
		
		*/

		app.buildAllPath(moves);
		
		app.totalDistances(moves);
	}

}
