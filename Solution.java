package eecs2011.project;

import java.util.Iterator;
import java.util.Scanner;

public class Solution {

    /**
     * Solution to Part 1
     */
    public void check_validity() { // no loops, no cycles, no disconnected vertices
        ; // read from standard input
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(); // number of functions
        int[][] matrix = new int[n + 2][n + 2]; // workflow graph
        int[] rt = new int[n]; // response time of functions
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++)
                matrix[i][j] = input.nextInt(); // read workflow graph from standard input
        }
        for (int i = 0; i < n; i++) {
            rt[i] = input.nextInt(); // read response time from standard input
        }
        input.close();


        ; // do something

        for (int i = 0; i < matrix.length; i++){
            if(matrix[i][i] == 1){
                System.out.println("False"); // write your answer to standard output
                return;
            }
        }

        boolean isAllConnectedToEnd = true;
        for (int i = 1; i < matrix.length-1; i++){
            if(matrix[i][matrix.length-1] == 0){
                isAllConnectedToEnd = false;
                break;
            }
        }

        LinkedList[] graph = GraphConstruction.ConstructGraph(matrix); // graph is constructed

        GraphTraversal g = new GraphTraversal(graph,0); // Start vertex is always 0


        boolean isConnected = g.isConnected() && isAllConnectedToEnd;
        if(!isConnected){
            System.out.println("False"); // write your answer to standard output
            return;
        }


        GraphCycle c = new GraphCycle(graph,0);
        boolean hasCycle = c.hasCycle();

        if(hasCycle) {
            System.out.println("False"); // write your answer to standard output
            return;
        }


        System.out.println("True"); // write your answer to standard output

    }

    /**
     * Solution Part 2
     */
    public void schedule_1(){
        ; // read from standard input

        Scanner input = new Scanner(System.in);
        int n = input.nextInt(); // number of functions
        int[][] matrix = new int[n + 2][n + 2]; // workflow graph
        int[] responseTime = new int[n]; // response time of functions
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++)
                matrix[i][j] = input.nextInt(); // read workflow graph from standard input
        }
        for (int i = 0; i < n; i++) {
            responseTime[i] = input.nextInt(); // read response time from standard input
        }
        input.close();



        ; // do something

        LinkedList[] graph = GraphConstruction.ConstructGraph(matrix);
        GraphTraversal g = new GraphTraversal(graph,0);

        LinkedList scheduleList = g.getScheduleList();

        ; // write your answer to standard output

        int time = 0;

        Iterator<Integer> itr = scheduleList.iterator();
        itr.next();

        while(itr.hasNext()){
            int vertex = itr.next();
            if(itr.hasNext()){
                System.out.println(vertex + " " + time);
                time += responseTime[vertex - 1];
            }

        }

        System.out.println(time);
    }

    /**
     * Solution to Part 3
     */
    public void schedule_x() {
        ; // read from standard input
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(); // number of functions
        int[][] matrix = new int[n + 2][n + 2]; // workflow graph
        int[] responseTime = new int[n]; // response time of functions
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++)
                matrix[i][j] = input.nextInt(); // read workflow graph from standard input
        }
        for (int i = 0; i < n; i++) {
            responseTime[i] = input.nextInt(); // read response time from standard input
        }
        input.close();

        ; // do something

        LinkedList[] graph = GraphConstruction.ConstructGraph(matrix);

        GraphTraversal g = new GraphTraversal(graph,0);

        LinkedList scheduleList = g.getScheduleList();


        int[] availability_time = new int[graph.length];

        int[] endTime = new int[graph.length - 2];

        Iterator<Integer> itr = scheduleList.iterator();
        itr.next();



        while(itr.hasNext()){
            int current_function = itr.next();

            if(itr.hasNext()){

                endTime[current_function-1] = availability_time[current_function] + responseTime[current_function-1];

                for(int w : graph[current_function]) {

                    if(endTime[current_function - 1] > availability_time[w]){
                        availability_time[w] = endTime[current_function - 1];
                    }
                }

            }

        }

        ; // write your answer to standard output

        for(int i = 1; i < availability_time.length-1; i++)
            System.out.println(i + " " + availability_time[i]);
        System.out.println(availability_time[graph.length-1]);


    }

    /**
     * Solution to Part 4 (optional)
     */
    public void schedule_2() {
        ; // read from standard input

        Scanner input = new Scanner(System.in);
        int n = input.nextInt(); // number of functions
        int[][] matrix = new int[n + 2][n + 2]; // workflow graph
        int[] responseTime = new int[n]; // response time of functions
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++)
                matrix[i][j] = input.nextInt(); // read workflow graph from standard input
        }
        for (int i = 0; i < n; i++) {
            responseTime[i] = input.nextInt(); // read response time from standard input
        }
        input.close();

        ; // do something

        LinkedList[] graph = GraphConstruction.ConstructGraph(matrix);

        int[] endTime = new int[graph.length - 2];

        int[] in_degree = new int[graph.length]; // when in_degree is 0, the function can be executed.


        for (int v = 0; v < graph.length; v++)
        {
            for (int w : graph[v])
                in_degree[w]++;
        }

        LinkedList queue = new LinkedList();

        for (int w : graph[0]) {

            in_degree[w]--;

                if (in_degree[w] == 0) { // then it is available to execute
                    queue.addLast(w);
                }
        }

        int[] machine_time = new int[2];

        int[] availability_time = new int[graph.length];

        while(!queue.isEmpty()){

            int function = queue.removeFirst();

            int min_index = 0;
            int max_index = 1;

            if(machine_time[0] > machine_time[1]){
                min_index = 1;
                max_index = 0;
            }

            int startingTime = 0;

            if(availability_time[function] < machine_time[min_index]){

                startingTime = machine_time[min_index];
                machine_time[min_index] += responseTime[function-1];
            }
            else if(availability_time[function] > machine_time[min_index] && availability_time[function] < machine_time[max_index]){

                startingTime = availability_time[function];
                machine_time[min_index] = availability_time[function] + responseTime[function-1];
            }
            else if (availability_time[function] == machine_time[0]) {

                startingTime = availability_time[function];
                machine_time[0] = availability_time[function] + responseTime[function-1];
            }
            else if(availability_time[function] == machine_time[1]){

                startingTime = availability_time[function];
                machine_time[1] = availability_time[function] + responseTime[function-1];
            }


            endTime[function - 1] = startingTime + responseTime[function - 1];

            for (int w : graph[function]) {

                if (w != graph.length - 1) {
                    in_degree[w]--;

                    if(endTime[function -1] > availability_time[w]){
                        availability_time[w] = endTime[function - 1];
                    }

                    if (in_degree[w] == 0) { // then it is available to be executed
                        queue.addLast(w);
                    }
                }
            }


    }
        ; // write your answer to standard output

        for(int i = 0; i < endTime.length; i++)
            System.out.println((i + 1) + " " + (endTime[i]-responseTime[i]));

        System.out.println(Math.max(machine_time[0], machine_time[1]));

    }

}