/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author furibe
 */
public class ConverterInputStream {

    public static final String PREFIX = "stream2file";
    public static final String SUFFIX = ".tmp";

    public String InputStreamToString(InputStream is) throws IOException {

        return IOUtils.toString(is, "UTF-8");

    }

    public File InputStreamToFile(InputStream is) throws IOException {

        File tempFile = File.createTempFile(PREFIX, SUFFIX);
        tempFile.deleteOnExit();
        FileOutputStream out = new FileOutputStream(tempFile);
        IOUtils.copy(is, out);
        return tempFile;
    }

}
