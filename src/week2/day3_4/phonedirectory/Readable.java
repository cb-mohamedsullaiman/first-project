/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week2.day3_4.phonedirectory;

import java.io.IOException;
import java.nio.file.Path;

/**
 *
 * @author cb-mohamedsullaiman
 */
public interface Readable {
    
    public void read(Path filePath) throws IOException;
    
    
}
