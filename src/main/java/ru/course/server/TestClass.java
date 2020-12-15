package ru.course.server;

public class TestClass {


    public boolean isPlain(int number){
        for(int i =2; i<number; i++){
            if(number%i==0){
                return false;
            }
        } return true;
    }

    public double[] getSimpleNumbers(int N){
        String tempResult="";
        for(int i=2;i<N;i++){
            if(isPlain(i))
            {
                tempResult+=i+" ";
            }
        }
        String[] arrayOfPlainNumbers = tempResult.trim().split(" ");
        double[] result = new double[arrayOfPlainNumbers.length];
        int i=0;
        for(String number : arrayOfPlainNumbers){
            result[i]=Double.parseDouble(number);
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        for(double var : testClass.getSimpleNumbers(10))
            System.out.println(var);
    }
}
