import org.junit.Test;

import static org.junit.Assert.*;

/**/
public class FileWriteTest {
    @Test
    public void getOpts() throws Exception {
        String[] args = {"-u","-s","1024"};
        FileWrite fw_success = new FileWrite();
        fw_success.getOpts(args);
        assertEquals(fw_success.isBuffered(),false);
        assertEquals(fw_success.getWriteSize(),1024);
        /*
        FileWrite fw_failed  = new FileWrite();
        fw_failed.getOpts(args);
        assertEquals(fw_failed.isBuffered(),true);
        assertEquals(fw_failed.getWriteSize(),1025*count);
        */

    }
}
