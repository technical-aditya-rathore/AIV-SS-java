package Start;



import com.sun.corba.se.spi.orbutil.fsm.Input;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;
import java.lang.String;
import java.util.Arrays;

/**
 * Created by Alex on 17.02.14.
 */

public class Task3 {

    public static boolean checkString(String string) {            //есть ли в строке что-то кроме цифр?
        if (string == null || string.length() == 0) return false;

        int i = 0;
        if (string.charAt(0) == '-') {
            if (string.length() == 1) {
                return false;
            }
            i = 1;
        }

        char c;
        for (; i < string.length(); i++) {
            c = string.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkStringWithDouble(String string){     //проверка на наличие символов в строке кроме цифр и точки
        if (string == null || string.length() == 0) return false;
        if(string.length()==2){if (string.substring(0,2).equals("-0")) return false;}
        if(string.length()>2){if (string.substring(0,2).equals("-0") && !string.substring(2,3).equals(".")) return false;}
        int i = 0;
        if (string.charAt(0) == '-') {
            if (string.length() == 1) {
                return false;
            }
            i = 1;
        }

        char c;
        for (; i < string.length(); i++) {
            c = string.charAt(i);
            if(c=='.' && string.charAt(i+1)>='0' && string.charAt(i+1)<='9'){return  true;} else
            if (!(c >= '0' && c <= '9')){
                return false;
            }
        }
        return true;
    }

    public static int EnterSize(){ //вводим размерность массива

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите размерность массива: ");
        int razm=0;

        String strRazm;
        BigInteger IntMaxValue = new BigInteger("922372036854775807");
        BigInteger IntMinValue = new BigInteger("-922372036854775808");

        while(razm<1){

            strRazm = sc.nextLine();

            if(strRazm.length()>1 && strRazm.substring(0,1).equals("0")){
                System.out.println("Data type error..try again");
            }
            else if(!checkString(strRazm)){
                System.out.println("Data type error..enter the number");
            }
            else if(checkString(strRazm)){
                BigInteger newBI = new BigInteger(strRazm);

                if(newBI.compareTo(IntMaxValue)==1 || newBI.compareTo(IntMinValue)==-1){ //Допускаем ввод чисел только в пределах диапазона  integer
                    System.out.println("Number too long or too short.. try again");
                } else {
                    razm=new Integer(strRazm);
                    if(razm<=0){
                        System.out.println("Data type error..the number has to be positive and not zero");
                        razm=0;
                    }
                  }
            }
        }
        return razm; //вернуть размерность
        }





    public static double[] InitArray(int razm){   //инициализация массива
        System.out.println("Введите элементы массива: ");

        double[] mas = new double[razm]; //собственно массив
        String InputData;                //предварительно считываем данные в строку потом фильтруем и вводим в массив
        boolean flag=true;           // flag - если флаг == false то выходим из цикла, массив заполнен.
        boolean excaptionFlag=false; //excaptionFlag - если в строке найдены недопустимые символы то excaptionFlag==true и элемент массива вводится снова
        Scanner sc = new Scanner(System.in); //поток ввода

        int dotCounter=0; //считаем количество точек в числе, во избежание ошибок

        while(flag){

        for(int i=0; i<razm; i++){
            int elem=i+1;
            System.out.print("Mas["+elem+"]=");
            InputData = sc.nextLine(); //считываем элемент массива как строку для последующей фильтрации

            if(InputData.equals(".")){
                System.out.println("Error! Invalid type of arguments.. try again");
                dotCounter=1; i--; excaptionFlag=true;
            }

            if(InputData.length()==0){
                System.out.println("Error! Invalid type of arguments.. try again");
                dotCounter=1; i--; excaptionFlag=true;
            }

            if(InputData.length()>1){
            for(int d=0; d<InputData.length(); d++){
                if(InputData.substring(d, d+1).equals(".")){dotCounter++;} //считаем точки
            }
            }

            if(InputData.length()>1){
                if(InputData.substring(InputData.length()-1).equals(".") || (InputData.substring(0,1).equals("0") && !InputData.substring(1,2).equals("."))){
                System.out.println("Error! Invalid type of arguments.. try again");
                dotCounter=1; i--; excaptionFlag=true;
                }
            }
            //если точек боьлше одной то ошибка
            if(dotCounter>1){System.out.println("Error! Invalid type of arguments.. try again"); dotCounter=0; i--; excaptionFlag=true;}

            if(InputData.length()>=1){
                if(excaptionFlag==false){ //если не возникло ошибок во время предварительной фильтрации то дальше выполняем код
                    if(!checkStringWithDouble(InputData)){ //если число не прошло проверку на содержание недопустимых символов то ошибка
                      i--;
                      excaptionFlag=true;
                      InputData="";
                      System.out.println("Error! Invalid type of arguments.. try again");
                    }
                    else if(checkStringWithDouble(InputData)){ //если проверка пройдена то выполняем дальше

                     //if(excaptionFlag==false){
                     mas[i]=Double.parseDouble(InputData);
                     if(i==razm-1 && excaptionFlag==false){
                         flag=false;
                    //}
                     }
                     }
                }
            }
            //после ввода элемента массива обнуляем счетчики и флаги
                excaptionFlag=false;
                dotCounter=0;

        }
        }
        return mas; //возвращаем инициализированный массив
    }






    public static double[] Sort(double[] M){ //сортировка
        double buffer=0;
        int razmer = M.length;
        double[] Arr = M;
        for(int i=0; i<razmer; i++){
            for(int j=0; j<razmer-1; j++){
                if(Arr[j+1]<Arr[j]){ buffer=Arr[j]; Arr[j]=Arr[j+1]; Arr[j+1]=buffer; }
            }
        }
        return Arr;
    }

    public static void PrintArray(double[] finalMass){  //вывод массива на экран
        boolean flagDouble=false;
        double ostatok=0;
        for(int i=0; i<finalMass.length; i++){
            ostatok = finalMass[i] % 1.0;
            if(ostatok!=0){
                flagDouble=true;
                System.out.println("Отсортированный массив:"+Arrays.toString(finalMass));
                break;}
            if(ostatok==0){flagDouble=false;}
        }
        if(flagDouble==false){
            System.out.println("Отсортированный массив:");
            for(int i=0; i<finalMass.length; i++){
                System.out.print(String.format("%6.0f", finalMass[i])+" ");
            }
        }
    }

    public static void main(String[] args) {  //точка ввхода в программу
        double[] massiv = InitArray(EnterSize());
        PrintArray(Sort(massiv));
    }
}




