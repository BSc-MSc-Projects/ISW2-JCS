package it.uniroma2.dicii.isw2.jcs.paramTests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.junit.Before;
import org.junit.Test;

public class TestJCSLightLoadUnitParam {
	
	private int items;
	private JCS jcs;
	
	@Before
	public void configure() throws CacheException {
		this.items = 3000;
		JCS.setConfigFilename( "/TestSimpleLoad.ccf" );
        JCS.getInstance( "testCache1" );
        //this.jcs = JCS.getInstance( "testCache1" );
	}
	
	
	@Test
	public void testSimpleLoad()
	        throws Exception
	    {
	        //JCS jcs = JCS.getInstance( "testCache1" );
	        //        ICompositeCacheAttributes cattr = jcs.getCacheAttributes();
	        //        cattr.setMaxObjects( 20002 );
	        //        jcs.setCacheAttributes( cattr );
			this.jcs = JCS.getInstance( "testCache1" );
		
	        System.out.println("Running with items: " + items);
	        
	        for ( int i = 1; i <= items; i++ )
	        {
	            jcs.put( i + ":key", "data" + i );
	        }

	        for ( int i = items; i > 0; i-- )
	        {
	            String res = (String) jcs.get( i + ":key" );
	            if ( res == null )
	            {
	                assertNotNull( "[" + i + ":key] should not be null", res );
	            }
	        }

	        // test removal
	        jcs.remove( "300:key" );
	        assertNull( jcs.get( "300:key" ) );

	    }
}
