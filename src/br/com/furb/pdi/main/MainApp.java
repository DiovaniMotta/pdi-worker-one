package br.com.furb.pdi.main;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class MainApp {

	public static void main(String[] args) {
		System.out.println("Oi");
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		/*
		 * VideoCapture camera = new VideoCapture(0);
		 * 
		 * if(!camera.isOpened()){ System.out.println("Erro"); } else { Mat
		 * frame = new Mat(); camera.read(frame); while(true){
		 * if(camera.read(frame)){ System.out.println("Width " + frame.width() +
		 * ", height " + frame.height());
		 * Imgcodecs.imwrite("/pdi-worker-one/images/imagem1.jpg", frame);
		 * break; } } } camera.release();
		 */
		/**
		 * 1�) As imagens contendo as fei��es de interesse foram submetidas
		 * primeiramente � etapa de pr�-processamento, na qual foram utilizados
		 * os operadores: mmaddm, mmhbasin e mmhdome - O primeiro operador
		 * morfol�gico utilizado foi o mmaddm, o qual consiste numa opera��o de
		 * adi��o. O limiar utilizado foi 70;
		 */
		Mat im1 = Imgcodecs.imread(
				"C:/Users/User/Desktop/Diovani/Faculdade/Processamento de Imagem/pdi-worker-one/images/imagem1.jpg");
		Mat res = new Mat();
		Imgproc.threshold(im1, res, 70, 255, 0);
		Imgcodecs.imwrite(
				"C:/Users/User/Desktop/Diovani/Faculdade/Processamento de Imagem/pdi-worker-one/images/passeOne.jpg",
				res);
		/**
		 * - Em seguida, foi aplicado o operador mmhdome. A finalidade deste
		 * operador � remover picos com contraste maior que o de um valor
		 * estipulado, removendo ru�dos aleat�rios distribu�dos na imagem.O que
		 * se mostrou mais adequado para a �rea de estudo foi 10;
		 */

		/**
		 * - Na seq��ncia, aplicou-se o operador mmhbasin, com limiar 20 para
		 * cada imagem. Este operador tem a fun��o de remover valores com
		 * contraste menor que o limiar escolhido por meio da reconstru��o da
		 * imagem em tons de cinza
		 * 
		 */
		Mat passeOne = Imgcodecs.imread(
				"C:/Users/User/Desktop/Diovani/Faculdade/Processamento de Imagem/pdi-worker-one/images/passeOne.jpg");
		Mat passeOneLimiar = new Mat();
		Imgproc.threshold(passeOne, passeOneLimiar, 20, 255, 0);
		Imgcodecs.imwrite(
				"C:/Users/User/Desktop/Diovani/Faculdade/Processamento de Imagem/pdi-worker-one/images/passeOneLimiar.jpg",
				passeOneLimiar);
		/**
		 * - Em seguida, a opera��o de gradiente foi aplicada, aumentando a
		 * varia��o de tons de cinza nas bordas das crateras.
		 * 
		 */

		/**
		 * 2�) Em seguida as imagens foram binarizadas, utilizando o operador
		 * mmbinary - as imagens foram binarizadas com limiar 32. Os valores de
		 * pixels que estavam abaixo do limiar estipulado assumiram o valor 0
		 * Anais XV Simp�sio Brasileiro de (preto) e os valores que estavam
		 * acima desse limiar receberam valor 1 (branco)
		 * 
		 */

		/**
		 * 3�) Na seq��ncia, aplicou-se o operador mmclose, que preencheu alguns
		 * pequenos ru�dos presentes no interior da fei��o - Visando ent�o,
		 * recuperar tais crateras, aplicou-se a opera��o de fechamento, que
		 * consiste da dilata��o da imagem, seguida da eros�o. Desta forma os
		 * pixels que comp�em as crateras foram conectados. O elemento
		 * estruturante utilizado foi o disco com raio 2
		 * 
		 */
		Mat kernel = Mat.ones(2, 2, CvType.CV_8UC1);
		Mat dst = new Mat(img.size(), img.type());
		Imgproc.morphologyEx(img, dst, Imgproc.MORPH_CLOSE, kernel);
		/**
		 * 4�) Por fim o operador mmopen que eliminou grande parte dos ru�dos
		 * presentes na imagem - Com a finalidade de eliminar tais segmentos
		 * indesej�veis, aplicou-se a opera��o de abertura.O elemento
		 * estruturante utilizado foi o disco de raio 2.
		 * 
		 */
		Mat kernel = Mat.ones(17, 17, CvType.CV_8UC1);
		Mat dst = new Mat(img.size(), img.type());
		Imgproc.morphologyEx(img, dst, Imgproc.MORPH_OPEN, kernel);
		/**
		 * 5�) Terminada a etapa de detec��o, para validar a metodologia
		 * desenvolvida, o resultado obtido foi sobreposto �s imagens originais
		 * 
		 */

		/*
		 * Mat im2 = Imgcodecs.imread("/pdi-worker-one/images/imagem1conv.jpg");
		 * Mat im3 = new Mat(); Core.absdiff(im1, im2, im3);
		 * Imgcodecs.imwrite("/pdi-worker-one/images/subtracao.jpg", im3);
		 * 
		 * Mat mama = Imgcodecs.imread("/pdi-worker-one/images/imagem1.jpg");
		 * Mat erodido = new Mat(); Imgproc.erode(mama, erodido, new Mat());
		 * Imgcodecs.imwrite("/pdi-worker-one/images/erodido.png", erodido);
		 * 
		 * Mat dilatado = new Mat(); Imgproc.dilate(erodido, dilatado, new
		 * Mat()); Imgcodecs.imwrite("/pdi-worker-one/images/dilatado.png",
		 * dilatado); Imgcodecs.imwrite("/pdi-worker-one/images/dilatado1.png",
		 * mama);
		 * 
		 * Mat sub1 = new Mat(); Mat sub2 = new Mat(); Core.absdiff(mama,
		 * erodido, sub1); Core.absdiff(mama, dilatado, sub2);
		 * 
		 * Mat res = new Mat(); //thresh Imgproc.threshold(sub2, res, 14, 255,
		 * 0);
		 * 
		 * Imgcodecs.imwrite("/pdi-worker-one/images/sub1.jpg", sub1);
		 * Imgcodecs.imwrite("/pdi-worker-one/images/sub2.jpg", sub2);
		 * Imgcodecs.imwrite("/pdi-worker-one/images/res.jpg", res);
		 * 
		 * Mat en = new Mat(); Imgproc.equalizeHist(mama, en);
		 * Imgcodecs.imwrite("/pdi-worker-one/images/en.pgn", en);
		 */
	}
}
