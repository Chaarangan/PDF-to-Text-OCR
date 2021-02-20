import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Writer {
//     ெசவ்வ ாய்க்கிழ ைம, பி.ப. 1.00 மணிக்கு
// வாய்ழூல வி ைடக்கான வின ாக்கள்

    public String edit(String text){
        text=text.replaceAll(" ","");
        text=text.replaceAll("ஈ", "ா");
        text=text.replaceAll(" ா", " ஈ");
        text=text.replaceAll("ழூ", "மூ");
        text=text.replaceAll(" ழு", " மு");
        text=text.replaceAll("ஒ்", "ி");


        text=text.replaceAll(",ர்", ",- ");
        for (int i=0; i<text.length();i++){
            if( text.charAt(i)=='ே'||text.charAt(i)=='ெ'||text.charAt(i)=='ை' ){
                text=text.substring(0,i)+text.charAt(i+1)+text.charAt(i)+text.substring(i+2);
                i++;
            }
            else if( text.charAt(i)=='ன'&&  "கஙஹஷஸஷவழளலறரயமபனநதஞஜசடண".indexOf(text.charAt(i+1))!=-1 && "்ைேெூுீிாொோௌ".indexOf(text.charAt(i+2))==-1) {
                text=text.substring(0,i)+text.charAt(i+1)+"ை"+text.substring(i+2);
                i= i+2;
            }
            else if( text.charAt(i)=='\''&&  "கஙஹஷஸஷவழளலறரயமபனநதஞஜசடண".indexOf(text.charAt(i-1))!=-1 ) {
                text=text.substring(0,i)+"்"+text.substring(i+1);
                i++;
            }
            else if (text.charAt(i)=='ே'&&  "கஙஹஷஸஷவழளலறரயமபனநதஞஜசடண".indexOf(text.charAt(i-1))!=-1 ){
                text=text.substring(0,i)+text.charAt(i+1)+"ே"+text.substring(i+2);
                i++;
            }
            else if (text.charAt(i)=='ை'&&  "கஙஹஷஸஷவழளலறரயமபனநதஞஜசடண".indexOf(text.charAt(i-1))!=-1 ){
                text=text.substring(0,i)+text.charAt(i+1)+"ை"+text.substring(i+2);
                i++;
            }
        }

//        for (int i=0; i<text.length();i++){
//            if( text.charAt(i)=='ම' && text.charAt(i+1)=='්'&&  "කඛගඝඞඟචඡජඣඤඥඦටඨඩඪණඬතථදධනඳපඵබභමඹයරලවශෂසහළෆ".indexOf(text.charAt(i+2))!=-1 ){
//                text=text.substring(0,i)+text.charAt(i+2)+"ෙ"+text.substring(i+3);
//                i++;
//            }
//            else if( text.charAt(i)=='3'){
//                if("කඛගඝඞඟචඡජඣඤඥඦටඨඩඪණඬතථදධනඳපඵබභමඹයරලවශෂසහළෆ".indexOf(text.charAt(i-1))!=-1) {
//                    text=text.substring(0,i)+"ා"+text.substring(i+1);
//                }
//                else if (text.charAt(i-1)=='ෙ'){
//                    text=text.substring(0,i-1)+"ො"+text.substring(i+1);
//                }
//                else if (text.charAt(i-1)=='ේ'){
//                    text=text.substring(0,i-1)+"ෝ"+text.substring(i+1);
//                }
//            }
//
//            }
//        for (int i=1; i<text.length();i++){
//            if( text.charAt(i)=='3'&&  " 0123456789,-/*".indexOf(text.charAt(i-1))==-1 ) {
//                text=text.substring(0,i)+"ා"+text.substring(i+1);
//                i++;
//            }
//        }
////
//        text=text.replaceAll("டூ", " ே");
//        text=text.replaceAll("டு", " ெ");
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

//ெ
//




        return text;
    }

    public void write(String text,String filepath) throws IOException {
        BufferedWriter outputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath)));
        outputStream.write(text);

        outputStream.close();
        System.out.println("Written to the file Successfully...");

    }
}
