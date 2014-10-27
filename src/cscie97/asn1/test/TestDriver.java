package cscie97.asn1.test;

import cscie97.asn1.knowledge.engine.ImportException;
import cscie97.asn1.knowledge.engine.Importer;
import cscie97.asn1.knowledge.engine.QueryEngine;
import cscie97.asn1.knowledge.engine.QueryEngineException;

/**
 * TestDriver implements a static main() method
 * @author APGalush
 *
 */
public class TestDriver
{
	/**
	 * The main() method accepts 2 parameters, an input Triple file, and an Input Query file, it calls the Importer.importFile() method,
	 * passing in the name of the provided triple file, after loading the input triples, the main() method invokes the executeQuery() method passing 
     * in the provided query file name
	 * @param args - type: Array of Strings
	 * @throws ImportException
	 */
    public static void main(String[] args) throws ImportException
    {
    	Importer myImp = new Importer();
    	QueryEngine myQueryEng = new QueryEngine();
    	
    	//allows only 2 arguments
    	if ( args.length < 2 )
    	{
    		throw new ImportException ( "Less than 2 arguments provided. Exactly 2 arguments required." );
    	}
    	if ( args.length > 2 )
    	{
    		throw new ImportException ( "Greater than 2 arguments provided. Exactly 2 arguments required." );
    	}
    	String tripleFile = args [0]; //inputTriples.nt;
    	String queryFile = args[1]; //sampleQuery.nt;
    	
		boolean exceptionFlag = false;
    	
    	try
    	{
    		myImp.importTripleFile( tripleFile );
    	}
    	catch ( ImportException e )
    	{
    		exceptionFlag = true;
    		String out = "Exception has occured: " + e.toString();
    		System.out.println( out );
    	}
    	
    	if ( !exceptionFlag )
    	{
	    	try 
	    	{
	    		myQueryEng.executeQueryFile( queryFile );
	    	}
	    	catch ( QueryEngineException e )
	    	{
	    		String out = "Exception has occured: " + e.toString();
	    		System.out.println( out );
	    	}
    	}
    	
    }
}
