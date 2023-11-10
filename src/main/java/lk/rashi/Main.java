package lk.rashi;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        String [][] SidName=new String[2][100]; // create array for id and name
        int[][] marks=new int[3][100]; // create array for prf marks and dbms marks and total
        double[] avg=new double[100]; // array for get average
        int[] rank=new int[100]; // array for store ranks
        int nextIndex=0; //next index the data adding

        System.out.print("> ");//start application
        String start=input.next();
        cls();

        while(true)	{
            int option=homePage();
            cls();
            if(option==1){ nextIndex=addStudent(SidName,marks,avg,rank,nextIndex);				//1. Add New Student
            }else if(option==2){ nextIndex=addNewStudentWithMarks(SidName,marks,avg,rank,nextIndex);	//2. Add New Student with marks
            }else if(option==3){ addMarks(SidName,marks,avg);									//3. Add Marks
            }else if(option==4){ updateStudentDetails(SidName);									//4. Update Student Details
            }else if(option==5){ updateMarks(SidName,marks,avg);								//5. Update Marks
            }else if(option==6){ delectStudent(SidName,marks,avg);								//6. Delete Student
            }else if(option==7){ printStudentDetails(SidName,marks,avg,rank,nextIndex);				//7. Print Student Details
            }else if(option==8){ printStudentRanks(SidName,marks,avg,rank,nextIndex);				//8. Print Student Ranks
            }else if(option==9){ bestInProgrammingFundamentals(SidName,marks,nextIndex);			//9. Best in Programming Fundementals
            }else if(option==10){ bestInDbms(SidName,marks,nextIndex);								// 10. Best in Datbase management system
            }
        }
    }
    //homepage method
    public static int homePage() {
        Scanner input = new Scanner(System.in);

        System.out.println("-----------------------------------------------------------------------------------------\n|\t\tWELCOME TO GDSE MARTKS MANAGEMENT SYSTEM\t\t\t\t|\n-----------------------------------------------------------------------------------------\n");
        System.out.println("[1] Add New Student\t\t\t\t[2] Add new student with marks\n[3] Add marks\t\t\t\t\t[4] Upadate student details\n[5] Upadate Marks\t\t\t\t[6] Delete student\n[7] Print Student Details\t\t\t[8] print student ranks\n[9] Best in programing Fundamentals\t\t[10] Best in database management\n");
        System.out.print("Enter an option to continue > ");
        int option=input.nextInt(); //enter option
        return option;
    }
    //Student Id and Name method
    public static void studentIdName(String[][]idName,int nextIndex){
        Scanner input = new Scanner(System.in);
        boolean studentIdFound ;
        String temporySidName;
        do{
            studentIdFound=false;
            System.out.print("Input the id for    : ");
            temporySidName = input.next();
            // check the id already exists
            for (int tempSecondIndexNumber = 0; tempSecondIndexNumber < idName[0].length; tempSecondIndexNumber++) {
                if (temporySidName.equals(idName[0][tempSecondIndexNumber])) {
                    System.out.println("The Student is already exists");
                    System.out.println();
                    studentIdFound = true;
                }
            }
        }while(studentIdFound==true);
        idName[0][nextIndex]=temporySidName;
        System.out.print("Enter Student Name: ");
        idName[1][nextIndex]=input.next();
    }
    //Marks method
    public static void prfandDbmsmarks(int[][] marks, int nextIndex){
        Scanner input=new Scanner(System.in);

        System.out.println();
        boolean repeatPrfMark;
        do{
            repeatPrfMark=false;
            System.out.print("Programming Fundementals Marks : ");
            int prfMark=input.nextInt();
            if(prfMark<=100 && prfMark>=0){ // get valid marks
                marks[0][nextIndex]=prfMark;
            }else{
                System.out.println("Invalid Marks. please enter correct Mark.");
                repeatPrfMark=true;
            }
        }while(repeatPrfMark);
        // input dbms marks
        boolean repeatDbmsMark;
        do{
            repeatDbmsMark=false;
            System.out.print("Database Management System Marks : ");
            int dbmsMark=input.nextInt();
            if(dbmsMark<=100 && dbmsMark>=0){ // valid marks
                marks[1][nextIndex]=dbmsMark;
            }else{
                System.out.println("Invalid Marks. please enter correct Marks.");
                repeatDbmsMark=true;
            }
        }while(repeatDbmsMark);
    }
    //Get Second Index Number Method
    public static int getSecondIndexNumber(String[][] idName){
        Scanner input=new Scanner(System.in);
        boolean b;
        int secondIndexNumber=-1;
        do{
            b=false;
            System.out.print("Enter Student ID : ");
            String id=input.next();
            boolean validIdNumber=false;
            for(int i=0; i<idName[0].length; i++){   // check valid student ID
                if(id.equals(idName[0][i])){
                    secondIndexNumber=i;
                    validIdNumber=true;
                }
            }
            if(!validIdNumber){ // id not exists
                System.out.print("Invalid Student ID. Do you want Search again(Y/n): ");
                char l=input.next().charAt(0);
                if(l=='Y' || l=='y'){
                    b=true;
                }else if(l=='N' || l=='n'){
                    cls();
                }
            }
        }while(b);
        return secondIndexNumber;
    }
    // calculate total and Average
    public static void calcTotalandAvg(int[][] marks, double[] avg, int secondIndexNumber){
        marks[2][secondIndexNumber]=marks[0][secondIndexNumber]+marks[1][secondIndexNumber];
        avg[secondIndexNumber]=marks[2][secondIndexNumber]/2.0;
    }
    // find rank
    public static int getRank(int[][] marks, int[] rank, int secondIndexNumber, int nextIndex){
        int[] newMarkArray= new int[marks[2].length];
        for (int i = 0; i < marks[2].length; i++){
            newMarkArray[i]= marks[2][i];
        }
        sortArray(newMarkArray);	// sort the copied array

        // reverse
        for (int i = 0; i < marks[2].length; i++){
            rank[i]= newMarkArray[newMarkArray.length-(i+1)];
        }
        // find the rank of the student
        int ranked= 0;
        for (int i = 0; i < nextIndex; i++){
            if (rank[i]== marks[2][secondIndexNumber]){
                ranked= i+1;
            }
        }
        return ranked;

    }
    // sort array
    public static void sortArray(int[]newMarkArray){
        // bubble sort(ascending)
        for (int i = 0; i < newMarkArray.length; i++){
            for (int j = 0; j < newMarkArray.length-1; j++){
                if (newMarkArray[j]>newMarkArray[j+1]){
                    int tempArray= newMarkArray[j];
                    newMarkArray[j]= newMarkArray[j+1];
                    newMarkArray[j+1]= tempArray;
                }
            }
        }

    }
    //Clear Console method
    public final static void cls() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            // Handle any exceptions.
        }
    }
    // 1. Add new Student method
    public static int addStudent(String[][]idName, int[][] marks, double[] avg,int[] rank,int nextIndex){
        Scanner input=new Scanner(System.in);

        boolean b;
        do{
            System.out.println("---------------------------------------------------------\n|		ADD NEW STUDENT				|\n---------------------------------------------------------\n");
            b=false;
            studentIdName(idName,nextIndex);
            System.out.println();
            System.out.print("Student has been added Successfully. Do you want to add another Student(Y/n): ");
            char yesOrNo=input.next().charAt(0);
            nextIndex++;
            if (yesOrNo == 'y') {
                b=true;
                cls();
            }else if (yesOrNo == 'n'){
                cls();
                return nextIndex;
            }
        }while(b==true);
        return homePage();
    }
    //2. Add Student with Marks method
    public static int addNewStudentWithMarks(String[][] idName, int [][]marks, double[] avg,int[] rank, int nextIndex){
        Scanner input=new Scanner(System.in);

        boolean b;
        do{
            System.out.println("---------------------------------------------------------\n|		ADD NEW STUDENT WITH MARKS		|\n---------------------------------------------------------\n");
            b=false;
            studentIdName(idName,nextIndex);
            prfandDbmsmarks(marks,nextIndex);
            calcTotalandAvg(marks,avg,nextIndex);
            System.out.print("Student has been added successfully. Do you want to add a new student (y/n) :");
            nextIndex++;
            char yesOrNo=input.next().charAt(0);
            if (yesOrNo == 'y') {
                b=true;
                cls();
            }else if (yesOrNo == 'n'){
                cls();
                return nextIndex;
            }
        }while(b);
        return nextIndex;
    }
    // 3. Add Marks method
    public static void addMarks(String[][] idName, int[][] marks, double[] avg){
        Scanner input=new Scanner(System.in);

        boolean b;
        do{
            System.out.println("-------------------------------------------------------------------------\n|				ADD MARKS				|\n-------------------------------------------------------------------------\n");
            b=false;
            boolean repeat;
            do{
                repeat=false;
                int secondIndexNumber=getSecondIndexNumber(idName);  // check the valid id
                if(secondIndexNumber==-1){
                    return;
                }
                if(marks[0][secondIndexNumber]==0 && marks[1][secondIndexNumber]==0){
                    System.out.print("Student Name :"+idName[1][secondIndexNumber]);  // display name of student
                    prfandDbmsmarks(marks,secondIndexNumber); // marks input by user
                    calcTotalandAvg(marks,avg,secondIndexNumber); // calculate total and average
                }else{
                    System.out.println("Student name :"+idName[1][secondIndexNumber]);
                    System.out.println("\nThis student's marks have been already added.\n\nIf you want to update the marks, please use [4] Update Marks option.Do you want to add marks for another student? (Y/n)  ");
                    char yesOrNo=input.next().charAt(0);

                    if (yesOrNo == 'y') {
                        repeat=true;
                        cls();
                    }else if(yesOrNo == 'n'){
                        cls();
                        return;
                    }
                }
            }while(repeat);

            System.out.print("Marks has been added Successfully. Do yo want to add marks for another Student(Y/n): ");
            char yesOrNo=input.next().charAt(0);
            if (yesOrNo == 'y') {
                b=true;
                cls();
            }else if(yesOrNo == 'n'){
                cls();
                return;
            }
        }while(b);
    }
    // 4. Update Student Details method
    public static void updateStudentDetails(String [][]idName){
        Scanner input=new Scanner(System.in);

        boolean b;
        do{
            System.out.println("-----------------------------------------------------------------\n|			 UPDATE STUDENT DETAILS			|\n-----------------------------------------------------------------\n");
            b=false;
            int secondIndexNumber=getSecondIndexNumber(idName);
            if(secondIndexNumber==-1){
                return;
            }
            System.out.println("Student Name :"+idName[1][secondIndexNumber]); // Print Name
            System.out.print("Enter Student Name :");  // get a new name
            idName[1][secondIndexNumber]=input.next();
            System.out.print("Student details has been updated Successfully. Do you want to update another Student detail(Y/n) :");
            char yesOrNo=input.next().charAt(0);

            if (yesOrNo == 'y') {
                b=true;
                cls();
            }else if (yesOrNo == 'n'){
                cls();
                return ;
            }
        }while(b);
    }
    // 5. Update Marks
    public static void updateMarks(String[][] idName, int[][] marks, double[] avg){
        Scanner input=new Scanner(System.in);
        boolean b;
        do{
            System.out.println("---------------------------------------------------------\n|		UPDATE MARKS				|\n---------------------------------------------------------\n");
            b=false;
            int secondIndexNumber=getSecondIndexNumber(idName);
            if(secondIndexNumber==-1){
                return;
            }
            System.out.println("Student Name :"+idName[1][secondIndexNumber]);  // display name of student
            if(marks[0][secondIndexNumber]==0 && marks[1][secondIndexNumber]==0){
                System.out.println(" This Student marks yet to be added");
            }else{
                System.out.println("Programming Fundamentals Marks :"+marks[0][secondIndexNumber]+"\n"+"Database Management System Marks  :"+marks[1][secondIndexNumber]+"\n"); //print marks of prf and  marks of dbms
                System.out.print("Enter new programming Fundementals Marks : ");	 // get a new prf marks
                marks[0][secondIndexNumber]=input.nextInt();
                System.out.print("Enter new Database Management System Marks : "); // get a new dbms marks
                marks[1][secondIndexNumber]=input.nextInt();

                calcTotalandAvg(marks,avg,secondIndexNumber);
                System.out.println("Marks has been updated Successfully");
            }
            System.out.print("\nDo you want to update marks for another Student? (Y/n) :");
            char yesOrNo=input.next().charAt(0);
            if (yesOrNo == 'y') {
                cls();
                b=true;
            }else if(yesOrNo == 'n'){
                cls();
                return;
            }
        }while(b);
    }
    //6. delete Student
    public static void delectStudent(String[][] idName,int[][] marks,double[] avg){
        Scanner input=new Scanner(System.in);
        boolean b;
        do{
            System.out.println("------------------------------------------------\n|		DELECT STUDENT			|\n-------------------------------------------------\n");
            b=false;
            int secondIndexNumber=getSecondIndexNumber(idName);
            if(secondIndexNumber==-1){
                return;
            }
            idName[0][secondIndexNumber]=null;
            idName[1][secondIndexNumber]=null;
            marks[0][secondIndexNumber]=0;
            marks[1][secondIndexNumber]=0;
            marks[2][secondIndexNumber]=0;
            avg[secondIndexNumber]=0;

            System.out.print("Student has been deleted Successfully. Do you want to delete another Student?(Y/n): ");
            char yesOrNo=input.next().charAt(0);
            if (yesOrNo == 'y') {
                cls();
                b=true;
            }else if (yesOrNo == 'n'){
                cls();
                return ;
            }
        }while(b);
    }
    //7. Print Student Details
    public static void printStudentDetails(String[][] idName, int[][] marks, double[] avg, int[] rank, int nextIndex){
        Scanner input=new Scanner(System.in);

        boolean b;
        do{
            System.out.println("-----------------------------------------------------------------\n|		PRINT STUDENT DETAILS				|\n-----------------------------------------------------------------\n");
            b= true;
            // check if id valid and get id's position
            int secondIndexNumber= getSecondIndexNumber(idName);
            if (secondIndexNumber==-1){
                return;
            }
            // print student name
            System.out.println("Student Name : "+idName[1][secondIndexNumber]);

            // check if marks have added
            if (marks[2][secondIndexNumber]==0){		// check if total = 0
                System.out.println("\nMarks yet to be added");
            }else{
                // print the details
                System.out.println("+-------------------------------------------+-------------------+");
                System.out.printf("|%-43s|%19d|\n","Programming Fundamentals Marks",marks[0][secondIndexNumber]);
                System.out.printf("|%-43s|%19d|\n","Database Management System Marks",marks[1][secondIndexNumber]);
                System.out.printf("|%-43s|%19d|\n","Total Marks",marks[2][secondIndexNumber]);
                System.out.printf("|%-43s|%19.2f|\n","Avg. Marks",avg[secondIndexNumber]);
                // find the rank
                int ranked= getRank(marks,rank,secondIndexNumber,nextIndex);
                // print the rank
                int lastRank= 0;
                for (int i = 0; i < rank.length-1; i++){
                    if (rank[i+1]==0 || i==rank.length-1){
                        lastRank= i+1;
                        break;
                    }
                }
                if (ranked==lastRank){
                    System.out.printf("|%-43s|%12d (Last)|\n","Rank",ranked);
                }else{
                    switch (ranked){
                        case 1: System.out.printf("|%-43s|%11d (First)|\n","Rank",ranked); break;
                        case 2: System.out.printf("|%-43s|%10d (Second)|\n","Rank",ranked); break;
                        case 3: System.out.printf("|%-43s|%11d (Third)|\n","Rank",ranked); break;
                        default: System.out.printf("|%-43s|%19d|\n","Rank",ranked);
                    }
                }
                System.out.println("+-------------------------------------------+-------------------+");
            }
            // check if have to print another student
            System.out.print("\nDo you want to update marks for another student? (Y/n)  ");
            char yesOrNo=input.next().charAt(0);
            if (yesOrNo == 'y') {
                cls();
                b=true;
            }else if(yesOrNo == 'n'){
                cls();
                return;
            }
        } while (b);
    }
    //8. Print Student Ranks
    public static void printStudentRanks(String[][]idName,int[][]marks,double[]avg,int[]rank,int nextIndex){
        Scanner input= new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------\n|			PRINT STUDENT RANKS			|\n-----------------------------------------------------------------");
        System.out.println("+-----+---------+--------------------------------------+-----------+-----------+\n|Rank |ID       |Name                                  |Total Marks|Avg. Marks |\n+-----+---------+--------------------------------------+-----------+-----------+");
        // calc the ranked array
        getRank(marks,rank,0,nextIndex);
        // table body
        for (int i = 0; i < nextIndex; i++){
            int secondIndexNumber1= -1;
            for (int j = 0; j <100; j++){
                if (rank[i]==marks[2][j]){
                    secondIndexNumber1= j;
                    break;
                }
            }
            if (marks[2][secondIndexNumber1]==0){
                continue;
            }
            System.out.printf("|%-5d|%-9s|%-38s|%11d|%11.2f|\n",(i+1),idName[0][secondIndexNumber1],idName[1][secondIndexNumber1],marks[2][secondIndexNumber1],avg[secondIndexNumber1]); // one by one row
        }
        System.out.println("+-----+---------+--------------------------------------+-----------+-----------+");
        System.out.print("\nDo you want to go back to main menu? (Y/n): ");
        char yesOrNo=input.next().charAt(0);
        if (yesOrNo == 'y') {
            cls();
            return;
        }else if(yesOrNo == 'n'){
            cls();
            System.exit(0);
        }
    }
    //9. Best in programming Fundementals
    public static void bestInProgrammingFundamentals(String[][]idName,int[][]marks,int nextIndex){
        Scanner input= new Scanner(System.in);
        System.out.println("----------------------------------------------------------------\n|             BEST IN PROGRAMMING FUNDEMENTALS                 |\n----------------------------------------------------------------\n");
        System.out.println("+------+----------------------+--------+----------+\n|ID    |Name                  |PF Marks|DBMS Marks|\n+------+----------------------+--------+----------+");
        // make a ascending sorted prf marks array
        int[] sortPrfMarks= new int[marks[0].length];
        for (int i = 0; i < sortPrfMarks.length; i++){
            sortPrfMarks[i]= marks[0][i];
        }
        sortArray(sortPrfMarks);

        // table body
        int j= sortPrfMarks.length-1;
        do{

            int secondIndexNumber1= -1;
            for (int i = 0; i < nextIndex; i++){
                if (sortPrfMarks[j]==marks[0][i]){
                    secondIndexNumber1= i;
                    break;
                }
            }

            System.out.printf("|%-6s|%-22s|%8d|%10d|\n",idName[0][secondIndexNumber1],idName[1][secondIndexNumber1],marks[0][secondIndexNumber1],marks[1][secondIndexNumber1]);
            j--;
        } while (sortPrfMarks[j]!=0);

        System.out.println("+------+----------------------+--------+----------+");
        System.out.print("\nDo you want to go back to main menu? (Y/n): ");
        char yesOrNo=input.next().charAt(0);
        if (yesOrNo == 'y') {
            cls();
            return;
        }else if(yesOrNo == 'n'){
            cls();
            System.exit(0);

        }

    }
    //10. best in databse management system
    public static void bestInDbms(String[][]idName,int[][]marks,int nextIndex){
        Scanner input= new Scanner(System.in);
        System.out.println("----------------------------------------------------------------\n|             BEST IN DATABASE MANAGEMENT SYSTEM               |\n----------------------------------------------------------------");
        // table title
        System.out.println("\n+------+----------------------+----------+----------+\n|ID    |Name                  |DBMS Marks|PF Marks  |\n+------+----------------------+----------+----------+");
        // make a ascending sorted prf marks array
        int[] sortDbmsMarks= new int[marks[1].length];
        for (int i = 0; i < sortDbmsMarks.length; i++){
            sortDbmsMarks[i]= marks[1][i];
        }
        sortArray(sortDbmsMarks);

        // table body
        int y= sortDbmsMarks.length-1;
        do{

            int secondIndexNumber1= -1;
            for (int i = 0; i < nextIndex; i++){
                if (sortDbmsMarks[y]==marks[1][i]){
                    secondIndexNumber1= i;
                    break;
                }
            }

            System.out.printf("|%-6s|%-22s|%10d|%10d|\n",idName[0][secondIndexNumber1],idName[1][secondIndexNumber1],marks[1][secondIndexNumber1],marks[0][secondIndexNumber1]);
            y--;
        } while (sortDbmsMarks[y]!=0);
        System.out.println("+------+----------------------+----------+----------+");
        System.out.print("\nDo you want to go back to main menu? (Y/n): ");
        char yesOrNo=input.next().charAt(0);
        if (yesOrNo == 'y') {
            cls();
            return;
        }else if(yesOrNo == 'n'){
            cls();
            System.exit(0);

        }
    }
}
