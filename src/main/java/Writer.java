import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Writer {

    public String edit(String text){
//        text=text.replaceAll(" ","");
//        text=text.replaceAll("ஈ", "ா");
//        text=text.replaceAll(" ா", " ஈ");
//        text=text.replaceAll("ழூ", "மூ");
//        text=text.replaceAll(" ழு", " மு");
//        text=text.replaceAll("ஒ்", "ி");
//        text=text.replaceAll(",ர்", ",- ");

        System.out.println(text);

        text=text.replace("ேக", "கே");
        text=text.replace("ேங", "ஙே");
        text=text.replace("ேச", "சே");
        text=text.replace("ேஜச", "ஜே");
        text=text.replace("ேஞஜச", "ஞே");
        text=text.replace("ேஞஜசட", "டே");
        text=text.replace("ேஞஜசடண", "ணே");
        text=text.replace("ேதஞஜசடண", "தே");
        text=text.replace("ேநதஞஜசடண", "நே");
        text=text.replace("ேனநதஞஜசடண", "னே");
        text=text.replace("ேபனநதஞஜசடண", "பே");
        text=text.replace("ேமபனநதஞஜசடண", "மே");
        text=text.replace("ேயமபனநதஞஜசடண", "யே");
        text=text.replace("ேரயமபனநதஞஜசடண", "ரே");
        text=text.replace("ேறரயமபனநதஞஜசடண", "றே");
        text=text.replace("ேலறரயமபனநதஞஜசடண", "லே");
        text=text.replace("ேளலறரயமபனநதஞஜசடண", "ளே");
        text=text.replace("ேழளலறரயமபனநதஞஜசடண", "ழே");
        text=text.replace("ேவழளலறரயமபனநதஞஜசடண", "வே");
        text=text.replace("ேஷவழளலறரயமபனநதஞஜசடண", "ஷே");
        text=text.replace("ேஸஷவழளலறரயமபனநதஞஜசடண", "ஸே");
        text=text.replace("ேஷஸஷவழளலறரயமபனநதஞஜசடண", "ஷே");
        text=text.replace("ேஹஷஸஷவழளலறரயமபனநதஞஜசடண", "ஹே");

        return text;
    }

    public void write(String text,String filepath) throws IOException {
        BufferedWriter outputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath)));
        outputStream.write(text);

        outputStream.close();
        System.out.println("Written to the file Successfully...");

    }
}
