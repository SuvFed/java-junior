package com.acme.edu;

public class ControllingExecution {
    public static void main(String[] args) {

        //region if-then-else
        int[] argss = {0};


        //Guard Clause for alternate flows
//        if(false){
//            throw new RuntimeException();
//        }
        //Main flow
        if (argss.length > 0) {
            System.out.println("Have args");
        } else if (true) {

        } else {
            //extracting parts of long logical sentence
            boolean a = true, d = true, c = true;
            boolean isWritable = a && d;
            boolean isAccessible = !c;
            if (isWritable || isAccessible) {

            } else {

            }
        }
        //endregion

        //region Case
        int i = 1;
        switch (i) {
            case 0:
                System.out.println(1);
                break;
            case 1: {
                System.out.println(1 + 1);
                break;
            }
            default:
                System.out.println("default");

        }
        //endregion

        //region fori
        for (int j = 0, c = 2; j < 10; j++, c++) {

        }
        //endregion

        //region while
        outer:while (true){
            //...
            inner:while (args.length<0) {
                //...
                if (true) break inner;
                if (true) continue outer;//прерывание текущей итерации и дальше по циклу
            }
            break;
        }
        //endregion

        //region foreach

        //можно изменять состаяния объектов
        for (String arg : args){
            System.out.println(arg);
        }
        //endregion


    }

    //region vararg

    //vararg тольк один и только в конце
    public static void loveAllCats(Object ... catsToLove){
        for (Object tmp: catsToLove){

        }
    }

    public static void loveAllInts(int[] ... intToLove){

    }
    //endregion
}

