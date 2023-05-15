import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Scanner;

public class SignUpAndIn {

    private RandomAccessFile rfile;

    {
        try {
            rfile = new RandomAccessFile("Sign.dat","rw");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //this method is for sign up users
    public void singUp(){
        String userName;
        String passWord;
        Scanner input = new Scanner(System.in);
        System.out.println("please enter your username :");
        userName = input.next();
        while (searchUsers(userName)){
            System.out.println("this username is taken before please take another username!");
            System.out.println("please enter your username :");
            userName = input.next();
        }
        System.out.println("please enter your password :");
        passWord = input.next();
        userName = fixString(userName,10);
        passWord = fixString(passWord,10);
        try {
            rfile.seek(rfile.length());
            rfile.writeChars(userName);
            rfile.writeChars(passWord);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    //this method is for sign in and return a string that if there is such user return the name else return null
    public String singIn(){
        String userName;
        String tempUserName;
        String passWord;
        String tempPassword;
        Scanner input = new Scanner(System.in);
        System.out.println("please enter your username :");
        userName = input.next();
        System.out.println("please enter your password :");
        passWord = input.next();

        if (userName.equals("admin") && passWord.equals("admin")){
            return "admin";
        }
        char [] ch = new char[10];
        try {
            rfile.seek(0);
            while (rfile.getFilePointer() < rfile.length()){
                for (int i = 0; i < 10; i++) {
                    ch[i] = rfile.readChar();
                }
                tempUserName = charToString(ch);
                tempUserName =tempUserName.trim();
                if (userName.equals(tempUserName)){
                    for (int i = 0; i < 10; i++) {
                        ch[i] = rfile.readChar();
                    }
                    tempPassword = charToString(ch);
                    tempPassword = tempPassword.trim();
                    if (passWord.equals(tempPassword)){
                        return tempUserName;
                    }
                }else {
                    rfile.seek(rfile.getFilePointer()+20);
                }

            }
        }catch (Exception e){
            System.out.println("there is an error");
        }

        return null;
    }
    //this method is for convert an array of char to string
    private String charToString(char [] ch){
        String username = "";
        for (int i = 0; i < ch.length; i++) {
            username = username + ch[i];
        }
        return username;
    }
    //this method is for fix username and password
    private String fixString(String smple , int length){
        if (smple.length()<length){
            while (smple.length()<length){
                smple = smple + " ";
            }
        }else {
            smple = smple.substring(0,length);
        }
        return smple;
    }

    // this method is for search in users
    public boolean searchUsers(String userName){
        RandomAccessFile rfile2 = null;
        try {
            rfile2 = new RandomAccessFile("sign.dat","rw");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String checkUserName;
        char [] ch =  new char[10];
        try {
            rfile2.seek(0);
            while (rfile2.getFilePointer() < rfile2.length()){
                for (int i = 0; i < 10; i++) {
                    ch[i] = rfile2.readChar();
                }
                checkUserName = charToString(ch);
                checkUserName = checkUserName.trim();
                if (checkUserName.equals(userName)){
                    return true;
                }
                rfile2.seek(rfile2.getFilePointer()+20);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
