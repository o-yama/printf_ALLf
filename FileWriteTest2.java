
import org.junit.Test;

import static org.junit.Assert.*;

/*
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