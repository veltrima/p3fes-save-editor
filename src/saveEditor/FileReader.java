package saveEditor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReader {
	
	String fileName, inDirectory;

    public FileReader(String inFileName, String inDir) {
        fileName = inFileName;
        inDirectory = inDir;
    }

    public byte[] readFile() throws IOException {
        Path path = Paths.get(inDirectory + fileName);
        return Files.readAllBytes(path);
    }
    
    void writeFile(byte[] data) throws IOException {
    	Files.createDirectories(Paths.get("./output"));
    	Path path = Paths.get("./output/" + fileName);
    	Files.write(path, data);
    }
}
