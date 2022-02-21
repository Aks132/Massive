package Detection;
//http://blog.gtiwari333.com/2011/12/javacv-simple-color-detection-using.html


import java.util.ArrayList;

import static org.opencv.imgcodecs.Imgcodecs.*;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;







public class IPTest {

	public static ArrayList<Integer> arrayx = new ArrayList<Integer>();
	public static ArrayList<Integer> arrayy = new ArrayList<Integer>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		GeneralConfig.baspath = "/Users/achyutsapariya/Desktop/MASSIVE/result/";
		doProcessing2();
		//edgeDetection();
	/*	LoadImage loadImage = new LoadImage();
		
		loadImage.displayimage();*/
	}
	
	private static void edgeDetection()
	{
		Mat mOrigional = imread("/Users/achyutsapariya/Desktop/MASSIVE/source/source1/source.png",
				IMREAD_GRAYSCALE);
		Rect roi = new Rect(290, 0, 1130, mOrigional.rows() - 70);
		Mat m2 = new Mat(mOrigional, roi);
		circularCrop(m2, m2.cols() / 2, m2.cols() / 2, m2.cols() / 2 - 40);
		imwrite(GeneralConfig.baspath + "result\\" + "croped"+ ".png", m2);


		Mat dst, detected_edges;
		int lowThreshold = 80;
		int ratio = 3;
		int kernel_size = 3;
		detected_edges = new Mat(mOrigional, roi);

		Imgproc.blur( m2, detected_edges, new Size(3,3) );

		Mat detected = new Mat(mOrigional, roi);
		Imgproc.Canny(detected, detected_edges, 10, 100,3, true);

		  /// Using Canny's output as a mask, we display our result
		/*  dst = new Mat(mOrigional, roi);



		 Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3,3), new Point(1,1));


		  Imgproc.erode(detected, dst, element);*/

		imwrite(GeneralConfig.baspath + "result\\" + "BW"+ ".png", detected_edges);
//		Imgproc.GaussianBlur(m2, m2, new Size(0, 0), 2);
//
//		Imgproc.threshold(m2, m2, 200, 255, Imgproc.THRESH_BINARY);
//		removeAlone(m2, 1);


	}
	private static void doProcessing2()
	{
		Mat m2 = imread("/Users/achyutsapariya/Desktop/MASSIVE/source/source1/source.png", IMREAD_GRAYSCALE);
		Imgproc.GaussianBlur(m2, m2, new Size(0, 0), 2);
		Imgproc.threshold(m2, m2, 150, 255, Imgproc.THRESH_BINARY);
		removeAlone(m2, 1);
		 imwrite(GeneralConfig.baspath + "result\\" + "testm2"
	                + ".png", m2);
		//source2

		Mat m22 = imread(
				"/Users/achyutsapariya/Desktop/MASSIVE/source/source2/source.png",
				IMREAD_COLOR);
		removeAlone(m22, 4);

		Mat blur = new Mat(m22.rows(), m22.cols(), m22.type());
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
		 imwrite(GeneralConfig.baspath + "result\\" + "testblur"
	                + ".png", blur);
		// source 2 image processing
		for (int i = 1; i < size.height - 1; i++)
			for (int j = 1; j < size.width - 1; j++) {
				double[] data2 = blur.get(i, j);
				if(
						(data2[0] !=0 && data2[1]!=0 && data2[2] != 0) // not black
						&&
						data2[0] <= 254 // blue below 85
						&&
						((data2[1] >= data2[2] && data2[1] >= data2[0] && data2[1] <= 255 && data2[2] <=110 && data2[0] <=200)||(data2[0] >= data2[1] && data2[2]>= 22 && data2[0] <= 55 && data2[1] <=10)||(data2[0] >= 32 && (data2[0] <= data2[1] + 1 && data2[0] <= data2[2] + 1))) // blue is less then red and green
						//..........................................green..................................................../..............................red.........................................../........................red trited......................../..........................yellow................................./............................................................................/

					){
					data2[0] = 255;
					data2[1] = 255;
					data2[2] = 255;
					blur.put(i, j, data2);
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

		 imwrite(GeneralConfig.baspath + "result\\" + "testblur1"
	                + ".png", blur);
		//source3

				Mat m23 = imread(
						"/Users/achyutsapariya/Desktop/MASSIVE/source/source3/source.png",
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
				imwrite(GeneralConfig.baspath + "result\\" + "testblur2"
		                + ".png", blur2);

				// source 3 image processing
				for (int i = 1; i < size.height - 1; i++)
					for (int j = 1; j < size.width - 1; j++) {
						double[] data2 = blur2.get(i, j);
						if (
							((data2[0] >= data2[2] && data2[0] >= 25 && data2[1] >= 12) || (data2[1] >= 148 && data2[2] >= 148) )
						// ....................hpht trited and hpht miner............/............strong hpht............. ..//...............ul hpht....................................................................................................../
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

				 imwrite(GeneralConfig.baspath + "result\\" + "testblur3"
			                + ".png", blur2);


				Mat result = imread("/Users/achyutsapariya/Desktop/MASSIVE/source/source1/source.png", IMREAD_COLOR);
				int x = countDiomand(blur2);
				System.out.println(x);
				replacement(blur,result, 255, 0);
				replacement(blur2,result, 255, 2);

				 imwrite(GeneralConfig.baspath + "result\\" + "test"
			                + ".png", result);

	}
	

    private static void replacement(Mat A, Mat B, int sscale, int dc) {
        // TODO Auto-generated method stub

		Size sizeA = A.size();
		ArrayList<Integer> redi = new ArrayList<Integer>();
		ArrayList<Integer> redj = new ArrayList<Integer>();

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

			B.put((int) redi.get(i), (int) redj.get(i), datadc);
		}
    }


	private static Mat addimage(Mat m2, Mat destination3) {
		// TODO Auto-generated method stub
		for (int i = 0; i < m2.rows(); i++) {
			for (int j = 0; j < m2.cols(); j++) {
				double[] datam2 = m2.get(i, j);
				double[] datadest = destination3.get(i, j);
				double[] dest = m2.get(i, j);
				if (datam2[0] == 255 && datadest[0] == 255)
					dest[0] = 255;
				else
					dest[0] = 0;

				destination3.put(i, j, dest);

			}
		}
		return destination3;
	}

	public static Mat equilizer(Mat b , Mat g , Mat r)
	{
		for (int i = 0; i < b.rows(); i++)
			for (int j = 0; j < b.cols(); j++) {
				
				
				double[] Blue = b.get(i, j);
				double[] Green = g.get(i, j);
				double[] Red = r.get(i, j);
				
				
				b.put(i, j, ((int)(((((int)(Blue[0]/2.0))*2.0) + 2 ) / 4.0))*4);
				r.put(i, j, ((int)(((((int)(Red[0]/2.0))*2.0) + 2 ) / 4.0))*4);
				g.put(i, j, ((int)(((((int)(Green[0]/2.0))*2.0) + 2 ) / 4.0))*4);
			}
	ArrayList<Mat> bb = new ArrayList<Mat>();
		
		bb.add(b);
		bb.add(g);
		bb.add(r);
		Mat destinationmid = new Mat(bb.get(0).rows(), bb.get(0).cols(), bb.get(0).type());
		Core.merge(bb, destinationmid);
		
		return destinationmid;

	}
	
	public static Mat spliterfilter(Mat b , Mat g , Mat r)
	{
		
		
		for (int i = 0; i < b.rows(); i++)
			for (int j = 0; j < b.cols(); j++) {
				
				
				double[] Blue = b.get(i, j);
				double[] Green = g.get(i, j);
				double[] Red = r.get(i, j);
				
				double temp = 0;
				
				temp =  Math.max(Math.max(Red[0],Green[0]),Blue[0]);
				
				if(Green[0] == temp &&
				Blue[0] == temp &&
				Red[0] == temp)
					{
						
						b.put(i, j, 0);
						g.put(i, j, 0);
						r.put(i, j, 0);
					
					}
				else if(temp == Blue[0])
				{
					b.put(i, j, 0);
					g.put(i, j, 0);
					r.put(i, j, 0);
				}
				else if (Red[0] == Green[0])
				{
					b.put(i, j, 0);
					if(temp < 40)
					{
					r.put(i, j, 0);
					g.put(i, j, 0);
					}
					
				}
				else if(temp == Red[0])
				{
					b.put(i, j, 0);
					g.put(i, j, 0);
					if(Red[0] < 35)
					r.put(i, j, 0);
				}
				else if(temp == Green[0])
				{
					b.put(i, j, 0);
					r.put(i, j, 0);
					if(Green[0] < 30)
					g.put(i, j, 0);
				}
				if(r.get(i, j)[0] > 0 || g.get(i, j)[0] > 0)
				{
					b.put(i, j, 255);
					r.put(i, j, 255);
					g.put(i, j, 255);
				}
				
				
			}

		ArrayList<Mat> bb = new ArrayList<Mat>();
		
		bb.add(b);
		bb.add(g);
		bb.add(r);
		Mat destinationmid = new Mat(bb.get(0).rows(), bb.get(0).cols(), bb.get(0).type());
		Core.merge(bb, destinationmid);
		
		return destinationmid;
		
	}
	
	public void filter(Mat image)
    {  
        int totalBytes = (int)(image.total() * image.elemSize());  
        byte buffer[] = new byte[totalBytes]; 
        image.get(0, 0,buffer); 
        for(int i=0;i<totalBytes;i=i+3)
        {
            if(buffer[i] > 200 && buffer[i+1] > 200 && buffer[i+2] > 200)
            {
            	buffer[i] = (byte) 255;
            	buffer[i+1] = (byte) 255;
            	buffer[i+2] = (byte) 255;
                
            }
            else
            {
            	buffer[i] = (byte) 0;
            	buffer[i+1] = (byte) 0;
            	buffer[i+2] = (byte) 0;
            }
        }
        image.put(0, 0, buffer); 
            System.out.println("filter");
    } 

	private static void circularCrop(Mat m2, int x, int y, int r) {
		// TODO Auto-generated method stub
		double[] BlackDot = m2.get(0, 0);
		BlackDot[0] = 0;
		for (int i = 0; i < m2.rows(); i++)
			for (int j = 0; j < m2.cols(); j++) {
				double xy = Math.sqrt((x - i) * (x - i) + (y - j) * (y - j));
				if (xy > r)
					m2.put(i, j, BlackDot);
			}

		// Imgproc.circle(m2, new Point(x,y), r, new Scalar(255), 5);

	}

	private static int countDiomand(Mat A) {
		// TODO Auto-generated method stub
		int count = 0, size = 1;
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
	
	

	public static void resize(String src, String dst, float xf, float yf) {
		Mat all_size = imread(src, IMREAD_COLOR);
		int xff = (int) (1 / xf);
		int yff = (int) (1 / yf);
		Mat destination = new Mat(all_size.rows() / xff, all_size.cols() / yff,
				all_size.type());
		Imgproc.resize(all_size, destination, destination.size(), xf, yf,
				Imgproc.INTER_NEAREST);
		imwrite(dst, destination);
	}
	private static void goOnVisit2(int i, int j, Mat A) {
		// TODO Auto-generated method stub
		
		double[] dataO = A.get(i, j);
		if (dataO[0] == 0) {
		} else {
			A.put(i, j, 0);
			
			arrayx.add(i);
			arrayy.add(j);
			
			goOnVisit2(i - 1, j - 1, A);
			goOnVisit2(i - 1, j, A);
			goOnVisit2(i - 1, j + 1, A);
			goOnVisit2(i, j + 1, A);
			goOnVisit2(i + 1, j + 1, A);
			goOnVisit2(i + 1, j, A);
			goOnVisit2(i + 1, j - 1, A);
			goOnVisit2(i, j - 1, A);

		}
	}
	private static void in1(Mat A ,int s)
	{
		
		int size = s;
		for (int i = s; i < A.rows() - s; i++) {
			for (int j = s; j < A.cols() - s; j++) {
				double[] dataO = A.get(i, j);
				if (dataO[0] == 0) {
				} else {
					//System.out.print(i+" "+j+" ");
					goOnVisit2(i, j, A);
					int xmin = arrayx.get(0);
					int xmax = arrayx.get(0);

					for(Integer t: arrayx) {
					    if(t < xmin) xmin = t;
					    if(t > xmax) xmax = t;
					}
					
					int ymin = arrayy.get(0);
					int ymax = arrayy.get(0);

					for(Integer t: arrayy) {
					    if(t < ymin) ymin = t;
					    if(t > ymax) ymax = t;
					}
					int x = (xmin + xmax) / 2;
					int y = (ymin + ymax) / 2;
					
					for (int xi = x - size; xi <= x + size; xi++) {
						for (int yj = y - size; yj <= y + size; yj++) {
							A.put(xi, yj,255);
						}
					}
					arrayx.clear();
					arrayy.clear();
				}
			}
		}
	}
	
	private static void replacement2(Mat A, Mat B,Mat C, int sscale, int dc)
	{

		Size sizeC = C.size();
		for (int i = 1; i < sizeC.height - 1; i++)
			for (int j = 1; j < sizeC.width - 1; j++) {

				double[] data = C.get(i, j);
				if (data[0] == sscale) {
					C.put(i, j, 100);
				}
			}
		
		
		
	}

	private static void replacement(Mat A, Mat B,Mat C, int sscale, int dc) {
		// TODO Auto-generated method stub

		Size sizeA = A.size();
		ArrayList<Integer> redi = new ArrayList<Integer>();
		ArrayList<Integer> redj = new ArrayList<Integer>();

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

			B.put((int) redi.get(i), (int) redj.get(i), datadc);
		}

	}
	
	
	private static void overlayImage(Mat A, Mat B) {
		// TODO Auto-generated method stub
		Size sizeA = A.size();
		for (int i = 1; i < sizeA.height - 1; i++)
			for (int j = 1; j < sizeA.width - 1; j++) {

				double[] data = A.get(i, j);
				if (data[0] == 255) {
					B.put(i, j, 255);
				}
			}
	}
	
	public static void resizeDots(Mat A, int size)
	{
		ArrayList<Integer> Bi = new ArrayList<Integer>();
		ArrayList<Integer> Bj = new ArrayList<Integer>();
		double[] BlackDot = A.get(0, 0);
		BlackDot[0] = 255;
		Boolean condition = true;
		for (int i = size; i < A.rows() - size; i++)
			for (int j = size; j < A.cols() - size; j++) {
				double[] dataO = A.get(i, j);
				condition = true;
				if (dataO[0] == 255) {
					for (int k = i - size; k <= i + size; k++) {
						for (int k2 = j - size; k2 <= j + size; k2++) {
							double[] data = A.get(k, k2);
							if (data[0] == 255) {
							} else
							{
								Bi.add(k);
								Bj.add(k2);
							}

						}
					}
				}

			}
		for (int i = 0; i < Bj.size(); i++) {
			A.put(Bi.get(i), Bj.get(i), BlackDot);
		}	
	}

	public static void circleCustomBinary(Mat A, int size) {
		ArrayList<Integer> Bi = new ArrayList<Integer>();
		ArrayList<Integer> Bj = new ArrayList<Integer>();
		double[] BlackDot = A.get(0, 0);
		BlackDot[0] = 0;
		Boolean condition = true;
		for (int i = size; i < A.rows() - size; i++)
			for (int j = size; j < A.cols() - size; j++) {
				double[] dataO = A.get(i, j);
				condition = true;
				if (dataO[0] == 255) {
					for (int k = i - size; k <= i + size; k++) {
						for (int k2 = j - size; k2 <= j + size; k2++) {
							double[] data = A.get(k, k2);
							if (data[0] == 255) {
							} else
								condition = false;

						}
					}

					if (condition == true) {
						Bi.add(i);
						Bj.add(j);
					}

				}

			}
		for (int i = 0; i < Bj.size(); i++) {
			A.put(Bi.get(i), Bj.get(i), BlackDot);
		}

	}

	private static void removeAlone(Mat A,int size) {
		// TODO Auto-generated method stub
    	 ArrayList<Integer> Bi = new ArrayList<Integer>();
         ArrayList<Integer> Bj = new ArrayList<Integer>();
         double[] BlackDot = A.get(0, 0);
         BlackDot[0] = 0;
         
    	Boolean condition = true;
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
	
	private static Mat orimage(Mat m2, Mat destination3) {
		// TODO Auto-generated method stub
		for (int i = 0; i < m2.rows(); i++) {
			for (int j = 0; j < m2.cols(); j++) {
				double[] datam2 = m2.get(i, j);
				double[] datadest = destination3.get(i, j);
				double[] dest = m2.get(i, j);
				if (datam2[0] == 255 || datadest[0] == 255)
					dest[0] = 255;
				else
					dest[0] = 0;

				destination3.put(i, j, dest);

			}
		}
		return destination3;
	}


}
