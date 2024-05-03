package cinema;

import java.util.Scanner;

public class Cinema {
    static int totalPurchasedTickets=0;
    static int currentIncome=0;
    static int totalIncome=0;
    public static void fillSeats(char[][] seats){
        for(int i=0;i<seats.length;i++){
            for (int j=0;j<seats[i].length;j++){
                if(i==0 && j==0){
                    seats[i][j]=' ';
                }else if (i==0) {
                    seats[i][j]=(char)((int)'0'+j);
                } else if (j==0) {
                    seats[i][j]=(char)((int)'0'+i);
                }else{
                    seats[i][j]='S';
                }
            }
        }
    }
    public static void printSeats(char[][] seats){
        System.out.println("Cinema:");
        for(char[] ch:seats){
            for (int i=0;i<ch.length;i++){
                if(i==(ch.length-1)){
                    System.out.printf("%c\n",ch[i]);
                }else {
                    System.out.printf("%c ", ch[i]);
                }
            }

        }
    }
    public static int printTotalIncome(int noOfSeats,int row,int col){
        int income=0;
        if((noOfSeats)<=60){
            //System.out.println("Total income:");
            income = noOfSeats*10;
            //System.out.println("$"+income);
        }else{
            //System.out.println("Total income:");
            income = (row/2)*col*10 +(row-row/2)*col*8;
           // System.out.println("$"+income);
        }
        return income;
    }
    public static void printTicketPrice(int row,int col,int seatRow,int seatCol,int noOfSeats){
        //System.out.println("row="+row);
        //System.out.println("seatRow="+seatRow);
       // System.out.println("noOfseats="+noOfSeats);
        if(noOfSeats<=60) {
            System.out.println("Ticket price: $" + 10);
            currentIncome+=10;
        } else if (seatRow<=(row/2-1)) {
            System.out.println("Ticket price: $"+10);
            currentIncome+=10;
        }else{
            System.out.println("Ticket price: $"+8);
            currentIncome+=8;
        }
    }
    public static void bookSeat(char[][] seats,int seatRow,int seatCol){

        seats[seatRow][seatCol]='B';
        totalPurchasedTickets+=1;
    }
    public static double percentageOfTicketsPurchases(int noOfSeats ){
        double percentage =(totalPurchasedTickets*1.00/noOfSeats)*100;
        return (double) Math.round( percentage * 100 ) /100;
    }
    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int row = scanner.nextInt()+1;
        System.out.println("Enter the number of seats in each row:");
        int col = scanner.nextInt()+1;
        char[][] seats = new char[row][col];
        fillSeats(seats);
        boolean exit= false;
        int noOfSeats =(row-1)*(col-1);
        totalIncome =printTotalIncome(noOfSeats,row-1,col-1);
       while (true) {
           if(exit==true){
               break;
           }
           System.out.println("1. Show the seats");
           System.out.println("2. Buy a ticket");
           System.out.println("3. Statistics");
           System.out.println("0. Exit");
           int choice=scanner.nextInt();
           switch (choice) {
               case 0:
                   exit=true;
                   break;
               case 1:
                   printSeats(seats);
                   break;
               case 2:
                   int seatRow;
                   int seatCol;
                   while (true) {
                       System.out.println("Enter a row number:");
                        seatRow = scanner.nextInt();
                       System.out.println("Enter a seat number in that row:");
                        seatCol = scanner.nextInt();
                       if ((seatRow < 1 || seatRow > row-1) || (seatCol < 1 || seatCol > col-1)) {
                           System.out.println("Wrong input!");
                       } else if (seats[seatRow][seatCol] == 'B') {
                           System.out.println("That ticket has already been purchased!");
                       }else{
                           break;
                       }

                   }
                   bookSeat(seats, seatRow,seatCol);
                   printTicketPrice(row,col,seatRow,seatCol,noOfSeats);
                   break;
               case 3:
                   System.out.println("Number of purchased tickets: "+totalPurchasedTickets);
                   double percentage =percentageOfTicketsPurchases(noOfSeats);
                   System.out.printf("Percentage: %.2f%%\n", percentage);
                   System.out.println("Current income: "+"$"+currentIncome);
                   System.out.println("Total income: "+"$"+totalIncome);



           }
       }






    }


}