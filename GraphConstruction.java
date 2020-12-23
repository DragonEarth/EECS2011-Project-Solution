package eecs2011.project;

public class GraphConstruction {

    public  static LinkedList[] ConstructGraph(int[][] matrix){

        LinkedList[] graph = new LinkedList[matrix.length];

        for(int v = 0; v < matrix.length; v++){
            graph[v] = new LinkedList();
            
            for(int w = 0; w < matrix.length; w++){
                if(matrix[v][w] == 1)
                    graph[v].addLast(w);
            }
        }

        return graph;
    }

}