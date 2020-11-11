import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import junit.framework.TestCase;

public class LCATest {

	 LCA bst = new LCA(); 
	
	@Test
	public void lca () {
		LCA bst = new LCA();
		
		assertSame("nothing entered", null, bst.lca(bst.root, 1, 2));
		
		bst.insert(15);		
		assertSame("bst with only root", null,bst.lca(bst.root, 1, 2));
		
		bst.insert(10); 
	    	bst.insert(6); 
	    	bst.insert(14); 
	    	bst.insert(4); 
	    	bst.insert(8); 
	    	bst.insert(12); 
	    	bst.insert(16); 
        	assertSame("full bst", 10 ,bst.lca(bst.root,14,5).data);
        	
	}
 /**		LCADAG UNIT TESTING		**/
	    
		 //test to see if correct number of vertices are returned
		 @Test
		 public void testVrt()
		 {
			 	LCADAG DAG = new LCADAG(10);
				assertEquals("valid Vrt() test failed", 10, DAG.Vrt());
		 }
		
		//test addition of edges
		 @Test
			public void testEdge(){
				LCADAG DAG = new LCADAG(7);
				
				assertEquals("test for non-existing vertices failed", false, DAG.addEdge(2, 7));
				assertEquals("test for non-existing vertices failed", false, DAG.addEdge(7, 2));
				assertEquals("test for non-existing vertices failed", false, DAG.addEdge(10, 50));
				assertEquals("test for vertice with negative value failed", false, DAG.addEdge(-10, -3));
				
				assertEquals("addition of valid edge failed", true, DAG.addEdge(0, 1));
				assertEquals("addition of valid edge failed", true, DAG.addEdge(1, 2));
				assertEquals("addition of pre-existing edge failed", false, DAG.addEdge(1, 2));
				assertEquals("self-addition of edge to node passed incorrectly", false, DAG.addEdge(0, 0));
							
			}	
		 		 
		 //tests to see if correct vertices are returned
		 @Test
		 public void testAdjacent()
		 {
			 	LCADAG DAG = new LCADAG(10);
				assertEquals("test Vrt() failed", 10, DAG.Vrt());
				ArrayList<Integer> expectedResult = new ArrayList<Integer>();
				
				//add data to LCADAG
				DAG.addEdge(1,5);
				DAG.addEdge(5,10);
				
				//test with adjacent node
				expectedResult.add(5);
				assertEquals("test with adjacent node failed", expectedResult, DAG.adjArray(1));
								
		 }
		 

		 	@Test
			public void testLCADAG(){
				LCADAG DAG = new LCADAG(5);
				
				DAG.addEdge(0, 1);	
				DAG.addEdge(0, 2);
				DAG.addEdge(2, 3);
				DAG.addEdge(3, 4);
				
				ArrayList<Integer> expectedResult = new ArrayList<Integer>();
				expectedResult.add(0);
						
				assertTrue("incorrect size returned", DAG.lowestCommonAncestor(4,1).size() == expectedResult.size());
				for(int er : expectedResult)
				{
					assertTrue("single LCA return", DAG.lowestCommonAncestor(4,1).contains(er));
				}
				
				//testing a DAG that returns multiple lca's
				LCADAG dag2 = new LCADAG(7);

				dag2.addEdge(0, 10);			
				dag2.addEdge(5, 8);
				dag2.addEdge(10, 4);
				dag2.addEdge(2, 5);
				dag2.addEdge(2, 6);
				dag2.addEdge(3, 5);
				dag2.addEdge(3, 6);
				
				expectedResult.clear();
				expectedResult.add(2);
				expectedResult.add(3);
						
				assertTrue("lcas size different from expected results size", dag2.lowestCommonAncestor(5,6).size() == expectedResult.size());
				for(int er : expectedResult)
				{
					assertTrue("multiple LCA return", dag2.lowestCommonAncestor(5,6).contains(er));
				}
				
				//testing non-existent vertices
				assertTrue("Testing negative inputs", dag2.lowestCommonAncestor(-2, -1).isEmpty());		
				
				//testing input outside scope 
				assertTrue("Testing out of range inputs", dag2.lowestCommonAncestor(1000, 257).isEmpty());
		 	}		 
		 
}


