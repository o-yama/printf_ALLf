package jp.ac.uryukyu.ie.e155730;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by e155730 on 11/4/16.
 */
public class FileWriteTest {
    @Test
    public void getOpts() throws Exception {

        FileWrite   fileWrite = new FileWrite();
        String[] args = {"-b","--size","1234"};
        fileWrite.getOpts(args);
        assertEquals(fileWrite.isBuffered(),true);
        assertEquals(fileWrite.getWriteSize(),1444);

    }

}