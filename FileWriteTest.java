package jp.ac.uryukyu.ie.e145735;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by e145735 on 2015/12/01.
 */
public class FileWriteTest {
    FileWrite fw;

    @Before
    public void setUp() throws Exception{
        fw = new FileWrite();
    }

    @Test
    public void testGetopts_b() throws Exception {
        String[] args = {"-b","--size","1234"};
        fw.getopts(args);
        assertEquals(fw.getWriteSize(),1234);
        assertEquals(fw.isBuffered(),true);
    }

    @Test
    public void testGetOpts_u() throws Exception {
        String[] args = {"-b","--size","3968"};
        fw.getopts(args);
        assertEquals(fw.getWriteSize(),3968);
        assertEquals(fw.isBuffered(),false);
    }

    @Test
    public void testGetOpts_h() throws Exception {
        String[] args = {"-h"};
        fw.getopts(args);
        assertEquals(fw.isHelped(),true);
    }

    @Test
    public void testGetOpts_help() throws Exception {
        String[] args = {"--help"};
        fw.getopts(args);
        assertEquals(fw.isHelped(),true);
    }
}