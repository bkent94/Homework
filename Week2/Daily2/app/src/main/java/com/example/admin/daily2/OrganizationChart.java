package com.example.admin.daily2;

public class OrganizationChart {

   public static String[][] createOrganizationChartRepresentation(String[] orgChart){


       String[][] orgChartRepresentation =new String[orgChart.length*4][orgChart.length*4];

       sortOrgChart(orgChart);


       for(int i = 0; i< orgChartRepresentation.length; i++){
           for(int j = 0; j< orgChartRepresentation.length; j++){
               orgChartRepresentation[i][j]="\t";
           }
       }


       String manager=orgChart[0].split(",")[0];
       orgChartRepresentation[0][0]=manager;

       for(int i=0;i<orgChart.length;i++){

           int extraSpace=0;

           int X=0;
           int Y=0;

           manager=orgChart[i].split(",")[0];

           for(int y=0;y<orgChartRepresentation.length;y++){
               for(int x=0;x<orgChart.length;x++){
                   if(orgChartRepresentation[y][x].equals(manager)){
                       X=x;
                       Y=y;
                       break;
                   }
               }
           }

           for (int j=1;j<orgChart[i].split(",").length;j++){

               String employee=orgChart[i].split(",")[j];





               orgChartRepresentation[Y+j+extraSpace][X+1]=employee;

               if(i<orgChart.length-1) {
                   if (employee.equals(orgChart[i + 1].split(",")[0])) {
                       extraSpace = orgChart[i + 1].split(",").length-1;
                   }
               }
           }

       }

       return orgChartRepresentation;



    }

    public static void sortOrgChart(String[] orgChart){
       for(int i=0;i<orgChart.length-1;i++){


            for(int j=i+1;j<orgChart.length;j++){
                if(orgChart[i].charAt(0)>orgChart[j].charAt(0)){
                    String str=orgChart[i];
                    orgChart[i]=orgChart[j];
                    orgChart[j]=str;
                }

            }
       }
    }

    public static void printOrgChartRepresentation(String[][]orgChartRepresentation){
       for(int i=0;i<orgChartRepresentation.length;i++){
           System.out.print("* ");
           for(int j=0;j<orgChartRepresentation.length;j++){
               System.out.print(orgChartRepresentation[i][j]);
           }
           System.out.println();
       }
    }

    public static void main(String[] args) {

       String[]orgChart={"B2,E5,F6","A1,B2,C3,D4","D4,G7,I9","G7,H8"};

      String[][]orgChartRepresentation=  createOrganizationChartRepresentation(orgChart);

        printOrgChartRepresentation(orgChartRepresentation);

    }
}
