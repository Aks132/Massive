package Detection;

import java.util.ArrayList;
import static org.opencv.imgcodecs.Imgcodecs.*;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.Scanner;



public class modified {
    //public static ArrayList<Integer> arrayx = new ArrayList<Integer>();
    //public static ArrayList<Integer> arrayy = new ArrayList<>();
    public static void main(String[] args) {
        // TODO Auto-generated method stub
       System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Scanner myObj = new Scanner(System.in);
        String path = myObj.nextLine();
        System.out.println(path);

        //edgeDetection();
        //LoadImage loadImage = new LoadImage();
		//loadImage.displayimage();
        System.out.println("Working...");

        doProcessing2(path);
        System.out.println("Finished");

    }
    private static void doProcessing2(String path)
    {
        //System.out.println(path);
        Mat m2 = imread(path + "source1.png", IMREAD_GRAYSCALE);
        Imgproc.GaussianBlur(m2, m2, new Size(0, 0), 2);
        Imgproc.threshold(m2, m2, 45, 255, Imgproc.THRESH_BINARY);//150
        removeAlone(m2, 1);
        imwrite(path + "testm2.png", m2);
        //source2

        Mat m22 = imread(path + "source2.png",
                IMREAD_COLOR);
        removeAlone(m22, 4);

        Mat blur = new Mat(m22.rows(), m22.cols(), m22.type());
        imwrite(path + "testbl.png", blur);
        Imgproc.GaussianBlur(m22, blur, new Size(0, 0), 2);
        removeAlone(blur, 4);
        Size size = m2.size();
        // only diamonds are remained in source 2
        //Size size = m2.size();
        for (int i = 1; i < size.height - 1; i++)
            for (int j = 1; j < size.width - 1; j++) {

                double[] data = m2.get(i, j);
                double[] data2 = blur.get(i, j);
                if (data[0] == 0) {
                    data2[0] = 0;
                    data2[1] = 0;
                    data2[2] = 0;
                    blur.put(i, j, data2);
                }
            }

        removeAlone(blur, 4);
        imwrite(path + "testblur.png", blur);
        // source 2 image processing
        for (int i = 1; i < size.height - 1; i++)
            for (int j = 1; j < size.width - 1; j++) {
                double[] data2 = blur.get(i, j);
                if((data2[0] !=0 && data2[1]!=0 && data2[2] != 0) // not black
                                &&
                        data2[0] <= 254 // blue below 85

                ){//System.out.println("loop 1 is passed");
                    if(!((120 <= data2[0] && data2[0] <= 195) && (55 <= data2[1] && data2[1] <= 145) && (20 <= data2[2] && data2[2] <= 100)))
                    {//System.out.println("loop 2 is passed");

                        if(/*(data2[1] >= data2[2] && data2[1] >= data2[0] && data2[1] <= 255 && data2[2] <=110 && data2[0] <=200)||*/(data2[0] >= data2[1] && data2[2]>= 22 && data2[0] <= 55 && data2[1] <=10)||(101 <= data2[1] && data2[1] <= 115 && 79 <= data2[0] && data2[0] <= 138)||( 62 <= data2[2] && data2[2] <= 155 && 98 <= data2[1] && data2[1] <= 230 )||((34 <= data2[0] && data2[0] <= 92) && (18 <= data2[1] && data2[1] <= 70) && (30 <= data2[2] && data2[2] <= 85))||((47 <= data2[0] && data2[0] <= 102) && (28 <= data2[1] && data2[1] <= 80) && (21 <= data2[2] && data2[2] <= 71))||(data2[0] >= 32 && (data2[0] <= data2[1] + 1 && data2[0] <= data2[2] + 1))) // blue is less then red and green
                        {    //..........................................green..................................................../..............................red.........................................../........................red new......................................../..........................green new.........................................../....................................................Purple New..................................................../)
                            //System.out.println("loop 3 is passed");
                            data2[0] = 255;
                            data2[1] = 255;
                            data2[2] = 255;
                            blur.put(i, j, data2);
                        }
                    }
                }
                else
                {
                    data2[0] = 0;
                    data2[1] = 0;
                    data2[2] = 0;
                    blur.put(i, j, data2);
                }
            }
        removeAlone(blur, 4);

        imwrite(path + "testblur1.png", blur);
        //source3

        Mat m23 = imread(
                path + "source3.png",
                IMREAD_COLOR);
        removeAlone(m23, 4);

        Mat blur2 = new Mat(m23.rows(), m23.cols(), m23.type());
        Imgproc.GaussianBlur(m23, blur2, new Size(0, 0), 2);
        removeAlone(blur2, 4);

        // only diamonds are remained in source 3
        for (int i = 1; i < size.height - 1; i++)
            for (int j = 1; j < size.width - 1; j++) {

                double[] data = m2.get(i, j);
                double[] data2 = blur2.get(i, j);
                if (data[0] == 0) {
                    data2[0] = 0;
                    data2[1] = 0;
                    data2[2] = 0;
                    blur2.put(i, j, data2);
                }
            }

        removeAlone(blur2, 4);
        imwrite(path + "testblur2.png", blur2);

        // source 3 image processing
        for (int i = 1; i < size.height - 1; i++)
            for (int j = 1; j < size.width - 1; j++) {
                double[] data2 = blur2.get(i, j);
                if (
                        /*((data2[0] >= data2[2] && data2[0] >= 25 && data2[1] >= 12) || */((data2[1] >= 148 && data2[2] >= 148) || (data2[0] >= 122 ) ||  ((90 <= data2[0] && data2[0] <= 110) && (80 <= data2[1] && data2[1] <= 100) && (35 <= data2[2] && data2[2] <= 60))||( 35<= data2[0] && data2[0] >= 120 && 25<= data2[1] && data2[1] >= 105 && 0<= data2[2] && data2[2] >= 65))
                          // ....................hpht trited and hpht miner............/............new hpht........................................//...............ul hpht....................................................................................................../
                )
                {
                    data2[0] = 255;
                    data2[1] = 255;
                    data2[2] = 255;
                    blur2.put(i, j, data2);
                }
                else
                {
                    data2[0] = 0;
                    data2[1] = 0;
                    data2[2] = 0;
                    blur2.put(i, j, data2);
                }
            }
        removeAlone(blur2, 4);

        imwrite(path + "testblur3.png", blur2);


        Mat result = imread(path + "source1.png", IMREAD_COLOR);
//        int x = countDiomand(blur);
//        System.out.println(x);
        replacement(blur,result, 255, 0);
        replacement(blur2,result, 255, 2);

        imwrite(path + "test.png", result);

    }
    private static void removeAlone(Mat A,int size) {
        // TODO Auto-generated method stub
        ArrayList<Integer> Bi = new ArrayList<>();
        ArrayList<Integer> Bj = new ArrayList<>();
        double[] BlackDot = A.get(0, 0);
        BlackDot[0] = 0;

        boolean condition = true;
        for (int i = size; i < A.rows() - size; i++)
            for (int j = size; j < A.cols() - size; j++) {
                double[] dataO = A.get(i, j);
                condition = true;
                if(dataO[0] == 255){
                    for (int k = i-size; k <= i+size; k++) {
                        for (int k2 = j-size; k2 <= j+size; k2++) {
                            double[] data = A.get(k, k2);
                            if((k == i && k2 == j) || data[0] == 0 )
                                continue;
                            else condition = false;

                        }
                    }

                    if(condition == true)
                    {
                        Bi.add(i);
                        Bj.add(j);
                    }


                }

            }
        for (int i = 0; i < Bj.size(); i++) {
            A.put(Bi.get(i),Bj.get(i), BlackDot);
        }

    }

    private static void replacement(Mat A, Mat B, int sscale, int dc) {
        // TODO Auto-generated method stub

        Size sizeA = A.size();
        ArrayList<Integer> redi = new ArrayList<>();
        ArrayList<Integer> redj = new ArrayList<>();

        double[] datadc = B.get(0, 0);

        for (int i = 1; i < sizeA.height - 1; i++)
            for (int j = 1; j < sizeA.width - 1; j++) {

                double[] data = A.get(i, j);
                if (data[0] == sscale) {
                    redi.add(i);
                    redj.add(j);
                }
            }
        if (dc == 0) {
            datadc[0] = 255;
            datadc[1] = 0;
            datadc[2] = 0;
        } else if (dc == 1) {
            datadc[0] = 0;
            datadc[1] = 255;
            datadc[2] = 0;
        } else if (dc == 2) {
            datadc[0] = 0;
            datadc[1] = 0;
            datadc[2] = 255;
        }

        for (int i = 0; i < redj.size(); i++) {

            B.put(redi.get(i), (int) redj.get(i), datadc);
        }
    }
    private static int countDiomand(Mat A) {
        // TODO Auto-generated method stub
        int count = 0, size = 1;
        System.out.println(A.cols());
        for (int i = size; i < A.rows() - size; i++) {
            for (int j = size; j < A.cols() - size; j++) {
                double[] dataO = A.get(i, j);
                if (dataO[0] == 0) {
                } else {
                    goOnVisit(i, j, A);
                    count++;
                }
            }
        }
        return count;
    }
    private static void goOnVisit(int i, int j, Mat A) {
        // TODO Auto-generated method stub
        double[] dataO = A.get(i, j);
        if (dataO[0] == 0) {
        } else {
            A.put(i, j, 0);
            goOnVisit(i - 1, j - 1, A);
            goOnVisit(i - 1, j, A);
            goOnVisit(i - 1, j + 1, A);
            goOnVisit(i, j + 1, A);
            goOnVisit(i + 1, j + 1, A);
            goOnVisit(i + 1, j, A);
            goOnVisit(i + 1, j - 1, A);
            goOnVisit(i, j - 1, A);

        }
    }


}
