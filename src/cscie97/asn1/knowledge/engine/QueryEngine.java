package cscie97.asn1.knowledge.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * The QueryEngine class supports the execution of Knowledge Graph queries. 
 * @author APGalush
 *
 */
public class QueryEngine
{
	
	private Utilities util;
	
	private List<Triple> listOfTriples;
	
	private BufferedReader br;
	
	public QueryEngine()
	{
		util = new Utilities();
		listOfTriples = new LinkedList<Triple>();
		
	}
	
	/**
	 * Executes a single query on the knowledge graph, checks for non null and well formed query string, throws QueryEngineException
     * on error. 
	 * @param query - type: String
	 * @throws QueryEngineException
	 */
	public void executeQuery ( String query ) throws QueryEngineException
	{
		if ( query.equals( null ) )
		{
			throw new QueryEngineException( "Query is null" );
		}
		
		String[] tempResult = query.split( " " );
		//make sure the query is correct
		if ( tempResult.length == 3 )
		{
			Node subject_obj = new Node ( tempResult[0] );
			Predicate predicate_obj = new Predicate( tempResult[1] );
			Node object_obj = new Node ( tempResult[2] );
			Triple queryTriple = new Triple ( subject_obj, predicate_obj, object_obj );

			Set<Triple> setOfTriples = KnowledgeGraph.getInstance().executeQuery( queryTriple ) ;
			
			System.out.println( query.toLowerCase() );
			
			if ( setOfTriples.isEmpty() )
			{
				System.out.println( "<null>" );
			}
			else
			{
		        for ( Triple triple : setOfTriples )
		        {
		        	System.out.println( triple.getIdentifier() );
		        }
			}
	        
		}
		else
		{
			throw new QueryEngineException( "Incorrect query" );
		}
	}
	
	
	/**
	 * Public method for executing a set of queries read from a file, checks for valid file name,  
     * delegates to executeQuery for processing individual queries, throws QueryEngineException on error.   
	 * @param fileName -type: String
	 * @throws QueryEngineException
	 */
	public void executeQueryFile ( String fileName ) throws QueryEngineException	
	{	
		if ( !fileName.contains(".nt") )
		{
			throw new QueryEngineException( "File Name is incorrect" );
		}
		String line = null;
		try
		{  
			br = new BufferedReader( new FileReader( fileName ) );
			// read query file line by line
			while ( ( line = br.readLine() ) != null )
			{
				String[] tempResult = line.split( " " );
				// check each line for the format correctness: query has to be 3 strings, no empty stings allowed 
				if ( tempResult.length == 3 && !util.arrayContains( tempResult, "" ) )
				{
					// each string has to be trimmed normalized and also "period character" has to be removed from the query (particularly from object string)
					Node mySubject = new Node ( tempResult[0].trim().toLowerCase() );
					Predicate myPredicate = new Predicate ( tempResult[1].trim().toLowerCase() );
					Node myObbject = new Node ( util.removeLastChar ( tempResult[2].trim().toLowerCase() ) );
					
					Triple myTriple = new Triple ( mySubject, myPredicate, myObbject );
					listOfTriples.add( myTriple );				
				}
				else
				{
					throw new QueryEngineException( "Query format is incorrect" );
				}
				
			}
			
			br.close();
		} 
		catch ( IOException e )
		{
			throw new QueryEngineException( e.toString() );
		}
		
		for ( Triple triple: listOfTriples )
		{
			executeQuery ( triple.getIdentifier() );
		}
	}

}
