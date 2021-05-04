package it.uniroma2.dicii.isw2.jcs.paramTests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value=Parameterized.class)
public class JCSLightLoadUnitTestparam {
	
	private int items;
	
	@BeforeClass
	public static void setFiles() throws CacheException {
		JCS.setConfigFilename( "/TestSimpleLoad.ccf" );
        JCS.getInstance( "testCache1" );
	}
	
	@Parameters
	public static Collection getParam() {
		return Arrays.asList(new Integer[][] {
			{20000},
			{10000},
			{3000},
		});
	}
	
	/*public int configure() {
		return 20000;
	}*/
	
	
	public JCSLightLoadUnitTestparam(int items) {
		this.items = items;
	}
	
	/*public JCSLightLoadUnitTestparam() {
		this.items = configure();
	}*/
	
	
	@Test
	public void testSimpleLoad()
	        throws Exception
	    {
	        JCS jcs = JCS.getInstance( "testCache1" );
	        //        ICompositeCacheAttributes cattr = jcs.getCacheAttributes();
	        //        cattr.setMaxObjects( 20002 );
	        //        jcs.setCacheAttributes( cattr );
	        
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
