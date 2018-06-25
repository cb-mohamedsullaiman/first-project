package week2.day3_4;
import java.nio.file.Path;
import java.io.File;
import java.nio.file.Files;
import java.util.Scanner;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Iterator;
import java.nio.file.DirectoryStream;

public class DirectoryScanWithMove{
	public static void moveFiles(Path sourcePath,Path destinationPath)throws IOException{
		DirectoryStream<Path> paths=Files.newDirectoryStream(sourcePath);
		Iterator iterator=paths.iterator();
		while(iterator.hasNext()){
			Path path=(Path)iterator.next();
			
			if(Files.isDirectory(path)){
				moveFiles(path,destinationPath);
			}
			else if(Files.isRegularFile(path)){
				try{
					String fileName=path.getFileName().toString();
					System.out.println(fileName);
					File parentDirectoryFile=new File(destinationPath.toString());
					System.out.println(parentDirectoryFile.getName());
					File newFile=new File(parentDirectoryFile,fileName);
					System.out.println(newFile.getName());
					if(!newFile.exists()){
						System.out.println(destinationPath.toString());
						Files.move(path,destinationPath.resolve(fileName));
					}
					else{
						Boolean isMoved=false;
						Integer numberOfFiles=1;
						do{
							Integer extensionIndex=fileName.lastIndexOf(".");
							if(extensionIndex==-1){
								extensionIndex=fileName.length();
							}
							String fileNameWithoutExtension=fileName.substring(0,extensionIndex);
							String fileExtension=fileName.substring(extensionIndex);
							Integer fileCount=fileName.lastIndexOf("-");
							if(fileCount==-1){
								fileName=fileNameWithoutExtension+'-'+numberOfFiles+fileExtension;
							}
							else{
								numberOfFiles=Integer.parseInt(fileNameWithoutExtension.substring(fileCount+1))+1;
								fileName=fileNameWithoutExtension.substring(0,fileCount)+'-'+numberOfFiles+fileExtension;
							}							
							File newFile1=new File(parentDirectoryFile,fileName);
							if(!newFile1.exists()){
								Files.move(path,destinationPath.resolve(fileName));
								isMoved=true;
							}
						}while(!isMoved);
					}
				}
				catch(IOException e){
					System.out.println("There is a problem in moving the file");
				}
			}
		}
	}
	public static void main(String args[]) throws IOException{
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the directory you want to move");
		String sourceDirectory=scanner.nextLine();
		System.out.println("Enter the destination directory");
		String destinationDirectory=scanner.nextLine();
		Path destination;
		try{
			moveFiles(Paths.get("/Users/cb-mohamedsullaiman/"+sourceDirectory+"/").toAbsolutePath(),Paths.get("/Users/cb-mohamedsullaiman/"+destinationDirectory+"/").toAbsolutePath());
		}
		catch(IOException e){
			System.out.println("There is a problem with either source file or destination file");
		}

	}
}