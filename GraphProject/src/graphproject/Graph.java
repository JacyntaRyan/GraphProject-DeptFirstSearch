/*
 * Stringo change this license header, choose License Headers in Project Properties.
 * Stringo change this template file, choose Stringools | Stringemplates
 * and open the template in the editor.
 */
package graphproject;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Jacynta
 */
public class Graph implements IGraph {

    private int numVertices;
    private int maxVertices;
    String[] vertices;
    PriorityQueue<String> mainQue = new PriorityQueue<>(50);
    Stack<String> mainStack;
    private int[][] edges = new int[50][50];
    private boolean[] marks; //marks[i] is mark for vertices[i]

    /**
     * @param args the command line arguments
     */
    Graph() {
        numVertices = 0;
        maxVertices = 50;
        vertices = new String[50];
        marks = new boolean[50];
    }

    Graph(int maxV) {
        numVertices = 0;
        maxVertices = maxV;
        vertices = new String[maxV];
        marks = new boolean[maxV];
    }

    public void PrintAirports() {
        int i = 0;
        System.out.println("aiports");
        while (i < vertices.length) {

            if (vertices[i] != null) {
                System.out.println(vertices[i]);
            }
            i++;
        }

    }

    public String AddVertex(String vertex) //post:vertex has been stored in vertices
    //corresponding row and column set to null_edge
    //numVertices incremented
    {
        String output = "";
        if (!VertexExists(vertex)) {
        //Adds vertext to the graph
            //graph must not be full
            vertices[numVertices] = vertex;
            for (int i = 0; i < numVertices; i++) {
                edges[numVertices][i] = 0;
                edges[i][numVertices] = 0;
            }
            numVertices++;
        } else {
            output = "airport " + vertex + " already exists";
        }
        return output;
    }

    @Override
    public int IndexIs(String[] vertices, String vertex) {
        //post: return index of vertex in vertices array
        int index = 0;
        while (!(vertex.equalsIgnoreCase(vertices[index]))) {
            index++;
        }
        return index;

    }

    @Override
    public String AddEdge(String fromVertex, String toVertex, int weight) {//adds edge with specified weight from fromVertex to toVertex.
        //Pre:fromVertex and toVertex must be in V(graph)
        //post:(fromVertex, toVertex)is now in E(graph) with specified weight

        String output = "";
        int row;
        int col;
        if (VertexExists(fromVertex) && VertexExists(toVertex)) {
            row = IndexIs(vertices, fromVertex);
            col = IndexIs(vertices, toVertex);
            edges[row][col] = weight;
            output = "Flight Added";
        } else {
            output = "could not add flight airport doesnt exist";
        }

        return output;
    }

    @Override
    public int WeightIs(String fromVertex, String toVertex) {//Determines the weigth of the egde from fromVertex to toVertex.
        //pre:fromVertex and toVertex must be in V(graph)
        int row;
        int col;
        row = IndexIs(vertices, fromVertex);
        col = IndexIs(vertices, toVertex);
        return edges[row][col];

    }

    @Override
    public PriorityQueue<String> GetToVertices(String vertex, PriorityQueue<String> que) {//returns a queue of the vertices that are adjacent from vertex
        //pre:vertex is in graph
        //post:vertexQ contains names of all the vertices that are heads of edges for which the vertex is tail

        int fromIndex;
        int toIndex;
        fromIndex = IndexIs(vertices, vertex);
        for (toIndex = 0; toIndex < numVertices; toIndex++) {
            if (edges[fromIndex][toIndex] != 0) {
                que.add(vertices[toIndex]);
            }
        }
//        Iterator<String> through = que.iterator() ;
//        while(through.hasNext() ) {
//                System.out.println("que = "+through.next() + " ") ;
//        }

        return que;
    }

    @Override
    public void MakeEmpty() {
        vertices = new String[50];
        marks = new boolean[50];
        edges = new int[50][50];
        numVertices = 0;
        mainQue.clear();

    }
    //function:initialize graph to empty state

    @Override
    public boolean IsEmpty() {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] != null) {
                return false;
            }
        }
        return true;
    }
    //function:determines if the graph is empty

    @Override
    public boolean IsFull() {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] == null) {
                return false;

            }
        }
        return true;

    }

    //function:Determines if the graph is full
    @Override
    public void ClearMarks() {
        for (int i = 0; i < marks.length; i++) {
            marks[i] = false;
        }
    }
    //setd all marks for vertices to false

    @Override
    public void MarkVertex(String vertex) {
        int markIndex = IndexIs(vertices, vertex);
        marks[markIndex] = true;
    }
    //sets mark for vertex to true

    @Override
    public boolean IsMarked(String vertex) {
        int markIndex = IndexIs(vertices, vertex);
        if (marks[markIndex] == true) {
            return true;
        }
        return false;

    }
    //determines if vertex has been marked

    public boolean VertexExists(String vertex) {
        boolean exists = false;
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] != null && vertices[i].equalsIgnoreCase(vertex)) {
                exists = true;
            }
        }
        return exists;
    }

    public String PrintFlights() {
        String output = " ";
        int row;
        int col;

        for (int i = 0; i < vertices.length; i++) {
            row = i;

            for (int j = 0; j < vertices.length; j++) {
                col = j;
                if (vertices[row] != null && vertices[col] != null) {
                    //System.out.println("edges" +edges[row][j]);
                    if (edges[row][j] != 0) {
                   // System.out.print(vertices[i]+"\t\t\t to \t\t\t"+ vertices[j]+ " \t\t\t\t ");

                        //System.out.println(WeightIs(vertices[i], vertices[j]));
                        output += "\n" + vertices[i] + "\t to\t" + vertices[j] + "\t" + WeightIs(vertices[i], vertices[j]);

                    }

                }
            }
        }
        return output;
    }

    public boolean FlightExists(String vertex) {
        boolean flightExits = false;
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] != null) {
                int weight = WeightIs(vertices[i], vertex);
                if (weight > 0) {
                    return flightExits = true;

                }
            }
        }
        return flightExits;
    }

    //deptFirstSearch 
    //Graph is a String Graph
    public String Search(Graph graph, String startVertex, String endVertex) {
        Stack<String> stack = new Stack();
        PriorityQueue<String> vertexQue = new PriorityQueue();
        boolean found = false;
        String vertex;
        String item;
        Stack<String> trackFlight = new Stack();
        Stack<String> changes = new Stack();
        ClearMarks();
        stack.push(startVertex);
        int count = 1;
        boolean changeOver = false;
        String output = "";

        if (!VertexExists(startVertex)) {
            //System.out.println(startVertex + " airport not found");
            return "" + startVertex + " airport does not exist";

        } else if (!VertexExists(endVertex)) {
            //System.out.println(endVertex + " airport not found");
            return " " + endVertex + "airport does not exist";
        } else {
            do {
                vertex = stack.pop();
                if (vertex.equalsIgnoreCase(endVertex)) {

                    //  System.out.println("flight from " + startVertex + " to " + endVertex + " found");
                    output += "flight " + startVertex + " to " + endVertex + " AVAILABLE";

                    String lastVertex = endVertex;
                    while (!trackFlight.isEmpty()) {
                        String previousVertex = trackFlight.pop();

                        int distance = WeightIs(previousVertex, lastVertex);
                        if (distance != 0) {
                            changeOver = true;

                            changes.add(lastVertex);
                            lastVertex = previousVertex;

                        }

                    }
                    if (changeOver) {

                        while (!changes.isEmpty()) {
                            String change = changes.pop();
                            if (!change.equalsIgnoreCase(endVertex)) {
                                // System.out.println("change at " + change);
                                output += "\n change at " + change;
                            }

                        }
                    }
                    // System.out.println("flight from "+startVertex+" to "+vertex);
                    //System.out.println(count + " "+vertex);
                    found = true;
                } else {
                    if (!IsMarked(vertex)) {
                        MarkVertex(vertex);
                        trackFlight.add(vertex);
                        //System.out.println(count+" "+vertex);
                        vertexQue = GetToVertices(vertex, vertexQue);
                        count++;
                        while (!vertexQue.isEmpty()) {
                            item = vertexQue.peek();
                            vertexQue.remove(item);
                            if (!IsMarked(item)) {
                                stack.push(item);
                            }
                        }
                    }

                }
            } while (!stack.isEmpty() && !found);
            if (!found) {
                //System.out.println("flight from " + startVertex + " to " + endVertex + " not found");
                output = "flight " + startVertex + " to " + endVertex + " NOT FOUND";
            }
//        catch(Exception E){
//            System.out.println(startVertex+" to "+endVertex+" not found in list");
//            PrintAirports();
//            
//        }
        }
        return output;
    }

    public String RemoveFlight(String fromVertex, String toVertex) {//adds edge with specified weight from fromVertex to toVertex.
        //Pre:fromVertex and toVertex must be in V(graph)
        //post:(fromVertex, toVertex)is now in E(graph) with specified weight
        String output = "";

        try {
            PriorityQueue<String> que = new PriorityQueue<>(50);
            int row;
            int col;
            row = IndexIs(vertices, fromVertex);
            col = IndexIs(vertices, toVertex);
            edges[row][col] = 0;
            output += "flight deleted\n";
            que.clear();
            que = GetToVertices(toVertex, que);
            if (que.isEmpty() && !FlightExists(toVertex)) {
                int index = IndexIs(vertices, toVertex);
                //System.out.println("delete " + index);
                output += "No connections to " + toVertex + " airport removed\n";
                vertices[index] = null;

            }
            que.clear();
            que = GetToVertices(fromVertex, que);
            if (que.isEmpty() && !FlightExists(fromVertex)) {
                int index = IndexIs(vertices, fromVertex);
                //System.out.println("delete " + index);
                output += " no connections to " + fromVertex + " airport removed\n";
                vertices[index] = null;

            }

        } catch (Exception E) {
            //System.out.println("Airport must already exist could not and path from " + fromVertex + " to " + toVertex);
            output += "Airport must already exist could not and path from " + fromVertex + " to " + toVertex;
        }
        return output;
    }

    public void InitialiseAirports() {

        AddVertex("Atlanta");
        AddVertex("Austin");
        AddVertex("Chicago");
        AddVertex("Dallas");
        AddVertex("Denver");
        AddVertex("Houston");
        AddVertex("Washington");

        AddEdge("Austin", "Dallas", 200);
        AddEdge("Dallas", "Austin", 200);
        AddEdge("Dallas", "Denver", 780);
        AddEdge("Austin", "Houston", 160);
        AddEdge("Dallas", "Chicago", 900);
        AddEdge("Denver", "Chicago", 1000);
        AddEdge("Chicago", "Denver", 1000);
        AddEdge("Houston", "Atlanta", 800);
        AddEdge("Atlanta", "Houston", 800);
        AddEdge("Denver", "Atlanta", 1400);
        AddEdge("Atlanta", "Washington", 600);
        AddEdge("Washington", "Atlanta", 600);
        AddEdge("Washington", "Dallas", 1300);

    }

    public static void main(String[] args) {
     // Graph airport = new Graph();
//
//        System.out.println("the graph is empty " + airport.IsEmpty());
//        airport.AddVertex("Atlanta");
//        airport.AddVertex("Austin");
//        airport.AddVertex("Chicago");
//        airport.AddVertex("Dallas");
//        airport.AddVertex("Denver");
//        airport.AddVertex("Houston");
//        airport.AddVertex("Washington");
//
//        System.out.println("the graph is empty " + airport.IsEmpty());
//        airport.AddEdge("Austin", "Dallas", 200);
//        airport.AddEdge("Dallas", "Austin", 200);
//        airport.AddEdge("Dallas", "Denver", 780);
//        airport.AddEdge("Austin", "Houston", 160);
//        airport.AddEdge("Dallas", "Chicago", 900);
//        airport.AddEdge("Denver", "Chicago", 1000);
//        airport.AddEdge("Chicago", "Denver", 1000);
//        airport.AddEdge("Houston", "Atlanta", 800);
//        airport.AddEdge("Atlanta", "Houston", 800);
//        airport.AddEdge("Denver", "Atlanta", 1400);
//        airport.AddEdge("Atlanta", "Washington", 600);
//        airport.AddEdge("Washington", "Atlanta", 600);
//        airport.AddEdge("Washington", "Dallas", 1300);

        //System.out.println("Austin to dallas " + airport.WeightIs("Atlanta", "Houston") + " km");
        // airport.InitialiseAirports();
        //airport.Search(airport, "Austin", "Dallas");
        //airport.Search(airport, "Atlanta", "Chicago");
        // airport.Search(airport, "Washington", "Dallas");
//        airport.Search(airport, "Houston", "Atlanta");
//       airport.RemoveFlight("Washington", "Atlanta");
//        airport.RemoveFlight("Atlanta", "Washington");
//        airport.RemoveFlight("Denver", "Atlanta");
//        airport.RemoveFlight("Atlanta", "Houston");
//        //airport.Search(airport,"Houston", "Atlanta");
//        //airport.RemoveFlight( "Houston","Atlanta");
//
//        airport.Search(airport, "Atlanta", "Washington");
//        System.out.println("  ");
//        airport.PrintAirports();
//        System.out.println("");
//       airport.PrintFlights();
    }

}
