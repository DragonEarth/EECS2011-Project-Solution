package eecs2011.project;

import java.util.Iterator;

public class GraphCycle {


    private boolean marked[];
    private boolean hasCycle;

    public GraphCycle(LinkedList[] graph, int source){

        marked = new boolean[graph.length];
        hasCycle = false;
        depthFirstSearch(graph ,source);

    }
    private void depthFirstSearch(LinkedList[] graph, int source){


        LinkedList stack = new LinkedList();
        boolean[] inStack = new boolean[graph.length];

        stack.addFirst(source);
        marked[source] = true;

        Iterator<Integer>[] itr = (Iterator<Integer>[])new Iterator[graph.length];

        for(int v = 0; v < graph.length ; v++){
            itr[v] = graph[v].iterator();
        }

        while(!stack.isEmpty()){
            int v = stack.first();

            inStack[v] = true;

            if(itr[v].hasNext()) {
                int to = itr[v].next();
                if (!marked[to]) {
                    marked[to] = true;
                    stack.addFirst(to);
                }
                else if(inStack[to]){

                    hasCycle = true;
                    return;

                }
            }
            else{
                stack.removeFirst();
                inStack[v] = false;
            }

        }

    }

    public boolean hasCycle(){
        return this.hasCycle;
    }

}
