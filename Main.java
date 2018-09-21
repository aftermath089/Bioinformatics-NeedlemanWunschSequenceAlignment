import java.util.LinkedList;
import java.util.Scanner;

import sun.tools.jar.resources.jar;

class Main extends EntryValue{
    public static void main(String[] args) {
        //INIT VAR//
        String mString1, mString2;
        int mLength1, mLength2;
        char[] mString1char, mString2char;
        Scanner scanner = new Scanner(System.in);
        EntryValue setEntryValue = new EntryValue();
        ////////////////////

        //INPUT//
        System.out.println("input 1: ");
        mString2=scanner.nextLine();
        mLength2=mString2.length();
        mString2char=mString2.toCharArray();

        System.out.println("input 2: ");
        mString1=scanner.nextLine();
        mLength1=mString1.length();
        mString1char=mString1.toCharArray();
        ////////////////////

        //PREPARE MATRICES//
        int[][] mArrayValue = new int[mLength1+1][mLength2+1];
        int[][] mArrayTracing = new int[mLength1+1][mLength2+1];
        String[][] mArrayArrows = new String[mLength1+1][mLength2+1];
        int[][] mArrayFlag = new int[mLength1][mLength2];
        for(int i=0; i<mLength1+1;i++){
            for(int j=0;j<mLength2+1;j++){
                if(i==0){
                    mArrayValue[i][j]=i+(j*(-6));
                    mArrayTracing[i][j]=i+(j*(-6));
                }else if(j==0){
                    //
                    mArrayValue[i][j]=j-(i*6);
                    mArrayTracing[i][j]=j-(i*6);
                }else{
                    mArrayValue[i][j]=setEntryValue.setValue(mString1char[i-1], mString2char[j-1]);
                    mArrayTracing[i][j]=setEntryValue.setValue(mString1char[i-1], mString2char[j-1]);
                    if(mString1char[i-1]==mString2char[j-1]){
                        mArrayFlag[i-1][j-1]=1;
                    }
                }
            }
        }
        /////////////////////

        //DOT MATRICE//
        System.out.println("\nDot matrice representation: ");
        for(int i=0; i<mLength1;i++){
            for(int j=0;j<mLength2;j++){
                System.out.print(mArrayFlag[i][j]+" ");
            }
            System.out.println();
        }
        ///////////////////

        //SCORING MATRICE//
        System.out.println("\nScoring matrice representation: ");
        for(int i=0; i<mLength1+1;i++){
            for(int j=0;j<mLength2+1;j++){
                System.out.print(mArrayValue[i][j]+"\t");
            }
            System.out.println();
        }
        //////////////////

        //PUTTING SCORE//
        for(int i=1; i<mLength1+1;i++){
            for(int j=1;j<mLength2+1;j++){
                int atas, kiri, diagonal,largest;

                diagonal=mArrayTracing[i][j]+mArrayTracing[i-1][j-1];
                kiri=mArrayTracing[i-1][j]-6;
                atas=mArrayTracing[i][j-1]-6;

                if((atas >= kiri) && (atas >= diagonal)){
                    //atas;
                    mArrayTracing[i][j]=atas;
                }else if ((diagonal > atas) && (diagonal >= kiri)){
                    //diagonal;
                    mArrayTracing[i][j]=diagonal;
                }else{
                   //kiri;   
                   mArrayTracing[i][j]=kiri; 
                }
            }
        }

        //OUTPUT SCORE//
        System.out.println("\nOutput score matrice representation: ");
        for(int i=0; i<mLength1+1;i++){
            for(int j=0;j<mLength2+1;j++){
                System.out.print(mArrayTracing[i][j]+"\t");
            }
            System.out.println();
        }
        ////////////////
     
        //ARROW PATH//
        for(int i=mLength1;i>0;i--){
            for(int j=mLength2;j>0;j--){

                int atas, kiri, diagonal,comparator;
                atas=mArrayTracing[i][j]+6-mArrayTracing[i-1][j];
                kiri=mArrayTracing[i][j]+6-mArrayTracing[i][j-1];
                diagonal=mArrayTracing[i][j]-mArrayValue[i][j]-mArrayTracing[i-1][j-1];

                if(atas==0){
                    mArrayArrows[i][j]="atas";
                }else if(diagonal==0){
                    mArrayArrows[i][j]="diag";
                }else if(kiri==0){
                    mArrayArrows[i][j]="kiri";
                }
            }
        }

        System.out.println("\nOutput Arrow matrice representation: ");
        for(int i=0; i<mLength1+1;i++){
            for(int j=0;j<mLength2+1;j++){
                System.out.print(mArrayArrows[i][j]+"  ");
            }
            System.out.println("\n");
        }
        ////////////////


        //BEST ALIGNMENT//
        LinkedList linkedList1=new LinkedList<Character>();
        LinkedList linkedList2=new LinkedList<Character>();
        int i2=mLength1;
        int j2=mLength2;
      
        while(i2!=0 && j2!=0){
            if(mArrayArrows[i2][j2]=="diag"){
                linkedList1.push(mString2char[j2-1]);
                i2=i2-1;
                j2=j2-1;
               
            }else if(mArrayArrows[i2][j2]=="atas"){
                i2=i2-1;
                val=val-6;
                linkedList1.push('-');

            }else if(mArrayArrows[i2][j2]=="kiri"){
                j2=j2-1;
                val=val-6;
                linkedList1.push('-');

            }

            if(i2==0 && j2!=0){
                for(int i=mLength1-1;i>=0;i--){
                    linkedList2.push(mString1char[i]);
                }
                for(int i=0;i<j2;i++){
                    linkedList2.push('-');
                }
            }
            
        }
        linkedList1.push(mString2char[j2-1]);
        
        System.out.println("Generated Alignment: ");
        while(!linkedList1.isEmpty()){
            System.out.print(linkedList1.pop());
        }
        System.out.println();
        while(!linkedList2.isEmpty()){
            System.out.print(linkedList2.pop());
        }
        ////////////////
    
    }

}