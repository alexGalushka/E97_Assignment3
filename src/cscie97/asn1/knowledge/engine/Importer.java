package cscie97.asn1.knowledge.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * The Importer class is responsible for reading triples from input files using N­Triple format
 * @author APGalush
 *
 */
public class Importer
{
	
	private List<Triple> listOfTriples;
	private Utilities util; 	
	private BufferedReader br;
	
	public Importer()
	{
		listOfTriples = new LinkedList<Triple>();
		util = new Utilities();
	}
	
	/**
	 * Imports triples from N_Triple formatted file into the KnowledgeGraph, checks for valid input file name, throws ImportException on error 
     * accessing or processing the input Triple File
	 * @param fileName - type: String
	 * @throws ImportException
	 */
	
	public void importTripleFile ( String fileName ) throws ImportException
	{

		String line = null;
		try
		{
			br = new BufferedReader( new FileReader( fileName ) );
			// read input file line by line
			while ( ( line = br.readLine() ) != null )
			{
				String[] tempResult = line.split( " " );
				// check each line for the format correctness: input can not contain "?" string 
				if ( !util.arrayContains( tempResult, "?" ) )
				{
					// process lines only with 3 strings and nonempty strings
					if ( tempResult.length == 3 &&  !util.arrayContains( tempResult, "" ) )
					{
						// each string has to be trimmed normalized and also "period character" has to be removed from the query (particularly from object string)
						Node mySubject = new Node ( tempResult[0].trim().toLowerCase() );
						Predicate myPredicate = new Predicate ( tempResult[1].trim().toLowerCase() );
						Node myObbject = new Node ( util.removeLastChar ( tempResult[2].trim().toLowerCase() ) );
						Triple myTriple = new Triple ( mySubject, myPredicate, myObbject );
						listOfTriples.add( myTriple );	
					}
				}
				else
				{
					throw new ImportException ( "Input format is incorrect" );
				}
				
			 }	
			br.close();
		} 
		catch ( IOException e )
		{

			throw new ImportException ( e.toString() );
		}
	
		KnowledgeGraph.getInstance().importTriples ( listOfTriples );
	}
}
