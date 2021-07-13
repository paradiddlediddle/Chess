package Game;

import java.util.Formatter;

public class Export {

public Formatter file;

String string = "File Copied Successfully";



public Export () {
    openFile();
    createFile();
    closeFile();
}



private void openFile () {
    try{ file = new Formatter("GameRecording.txt"); }
    catch (Exception exception ){ System.out.println(exception); }
}


private void createFile () {


    file.format("%s", string);

}

private void closeFile () { file.close(); }






}
