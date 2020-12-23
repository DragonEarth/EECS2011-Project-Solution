package eecs2011.project;

import java.util.Iterator;


public class GraphTraversal {

    private boolean[] marked;

    private final int vertexNumber;

    private LinkedList scheduleList;

    public GraphTraversal(LinkedList[] graph, int source){

        vertexNumber = graph.length;

        marked = new boolean[vertexNumber];

        scheduleList = new LinkedList();

        depthFirstSearch(graph, source);
    }

    private void depthFirstSearch(LinkedList[] graph, int source){


        LinkedList stack = new LinkedList();

        stack.addFirst(source);
        marked[source] = true;

        Iterator<Integer>[] itr = (Iterator<Integer>[])new Iterator[graph.length];

        for(int v = 0; v < graph.length ; v++){
            itr[v] = graph[v].iterator();
        }


        while(!stack.isEmpty()){
            int v = stack.first();


            if(itr[v].hasNext()) {
                int to = itr[v].next();
                if (!marked[to]) {
                    marked[to] = true;
                    stack.addFirst(to);
                }

            }
            else{
                int popped = stack.removeFirst();
                scheduleList.addFirst(popped);
            }

        }

    }

    public boolean isConnected(){

        for(int v = 0 ; v < vertexNumber; v++){
            if(!marked[v])
                return false;
        }
        return true;
    }



    public LinkedList getScheduleList() {
        return scheduleList;
    }

}
