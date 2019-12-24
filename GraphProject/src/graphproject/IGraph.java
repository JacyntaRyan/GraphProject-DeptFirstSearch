/*
 * Stringo change this license header, choose License Headers in Project Properties.
 * Stringo change this template file, choose Stringools | Stringemplates
 * and open the template in the editor.
 */
package graphproject;

import java.util.PriorityQueue;

/**
 *
 * @author Jacynta
 */
public interface IGraph {
    
    
    
     public abstract int IndexIs(String[] vertices,String vertex);
     //returns index of vetex in vertices array
     
    public abstract void MakeEmpty();
    //function:initialize graph to empty state
    
    public abstract boolean IsEmpty();
    //function:determines if the graph is empty
    
    public abstract boolean IsFull();
    //function:Determines if the graph is full
    
    public abstract String AddVertex(String vertex);
    //Adds vertext to the graph
    //graph must not be full
    
    public abstract String AddEdge(String fromVertex,String toVertex,int x);
    //function:adds edge with specified weight
    //from fromVertex to toVertex.
    //Pre:fromVertex and toVertex must be in V(graph)
    //post:(fromVertex, toVertex)is now in E(graph) with specified weight
    
    public abstract String RemoveFlight(String fromVertex,String toVertex);
    
    public abstract int WeightIs(String fromVertex,String toVertex);
    //function: Determines the weigth of the egde
    //from fromVertex to toVertex.
    //pre:fromVertex and toVertex must be in V(graph)
    
    public abstract PriorityQueue<String> GetToVertices(String vertex, PriorityQueue<String> que);
    //function:returns a queue of the vertices that are adjacent from vertex
    //pre:vertex is in graph
    //post:vertexQ contains names of all the vertices
    //that are heads of edges for which the vertex is tail
    
    public abstract void ClearMarks();
    //setd all marks for vertices to false
    
    public abstract void MarkVertex(String vertex);
    //sets mark for vertex to true
    
    public abstract boolean IsMarked(String vertex);
    //determines if vertex has been marked
    
}
