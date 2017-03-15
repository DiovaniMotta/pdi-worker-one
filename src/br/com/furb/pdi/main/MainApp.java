package br.com.furb.pdi.main;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class MainApp {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		/**
		 * 1°) As imagens contendo as feições de interesse foram submetidas
		 * primeiramente à etapa de pré-processamento, na qual foram utilizados
		 * os operadores: mmaddm, mmhbasin e mmhdome - O primeiro operador
		 * morfológico utilizado foi o mmaddm, o qual consiste numa operação de
		 * adição. O limiar utilizado foi 70;
		 */
		Mat im1 = Imgcodecs.imread(
				"C:/Users/User/Desktop/Diovani/Faculdade/Processamento de Imagem/pdi-worker-one/images/imagem1.jpg");
		Mat res = new Mat();
		Imgproc.threshold(im1, res, 70, 70, Imgproc.THRESH_TOZERO);
		Imgcodecs.imwrite(
				"C:/Users/User/Desktop/Diovani/Faculdade/Processamento de Imagem/pdi-worker-one/images/passeOne.jpg",
				res);
		/**
		 * - Em seguida, foi aplicado o operador mmhdome. A finalidade deste
		 * operador é remover picos com contraste maior que o de um valor
		 * estipulado, removendo ruídos aleatórios distribuídos na imagem.O que
		 * se mostrou mais adequado para a área de estudo foi 10;
		 */
		im1 = Imgcodecs.imread(
				"C:/Users/User/Desktop/Diovani/Faculdade/Processamento de Imagem/pdi-worker-one/images/passeOne.jpg");
		res = new Mat();
		Imgproc.threshold(im1, res, 10, 10, Imgproc.THRESH_TOZERO);
		Imgcodecs.imwrite(
				"C:/Users/User/Desktop/Diovani/Faculdade/Processamento de Imagem/pdi-worker-one/images/passeOneLimiar10.jpg",
				res);
		
		/**
		 * - Na seqüência, aplicou-se o operador mmhbasin, com limiar 20 para
		 * cada imagem. Este operador tem a função de remover valores com
		 * contraste menor que o limiar escolhido por meio da reconstrução da
		 * imagem em tons de cinza
		 * 
		 */
		Mat passeOne = Imgcodecs.imread(
				"C:/Users/User/Desktop/Diovani/Faculdade/Processamento de Imagem/pdi-worker-one/images/passeOneLimiar10.jpg");
		Mat passeOneLimiar = new Mat();
		Imgproc.threshold(passeOne, passeOneLimiar, 20, 20, Imgproc.THRESH_TOZERO);
		Imgcodecs.imwrite(
				"C:/Users/User/Desktop/Diovani/Faculdade/Processamento de Imagem/pdi-worker-one/images/passeOneLimiar20.jpg",
				passeOneLimiar);
		/**
		 * - Em seguida, a operação de gradiente foi aplicada, aumentando a
		 * variação de tons de cinza nas bordas das crateras.
		 * 
		 */
		Mat filtrado = new Mat();
		Mat kernel = new Mat(1, 3, CvType.CV_64F);
		double[] kernel_v = { -1.0, 2, -1.0 };
		kernel.put(0, 0, kernel_v);
		Imgproc.filter2D(passeOneLimiar, filtrado, -1, kernel);
		Imgcodecs.imwrite(
				"C:/Users/User/Desktop/Diovani/Faculdade/Processamento de Imagem/pdi-worker-one/images/marte.jpg",
				filtrado);
		/**
		 * 2º) Em seguida as imagens foram binarizadas, utilizando o operador
		 * mmbinary - as imagens foram binarizadas com limiar 32. Os valores de
		 * pixels que estavam abaixo do limiar estipulado assumiram o valor 0
		 * Anais XV Simpósio Brasileiro de (preto) e os valores que estavam
		 * acima desse limiar receberam valor 1 (branco)
		 * 
		 */
		Mat binary = new Mat();
		Imgproc.threshold(filtrado, binary, 32, 32, Imgproc.THRESH_BINARY);
		Imgcodecs.imwrite(
				"C:/Users/User/Desktop/Diovani/Faculdade/Processamento de Imagem/pdi-worker-one/images/binary.jpg",
				binary);
		
		/**
		 * 3°) Na seqüência, aplicou-se o operador mmclose, que preencheu alguns
		 * pequenos ruídos presentes no interior da feição - Visando então,
		 * recuperar tais crateras, aplicou-se a operação de fechamento, que
		 * consiste da dilatação da imagem, seguida da erosão. Desta forma os
		 * pixels que compõem as crateras foram conectados. O elemento
		 * estruturante utilizado foi o disco com raio 2
		 * 
		 */
		/*Mat filtrado = new Mat();
		Mat kernel = new Mat(1, 3, CvType.CV_64F);
		double[] kernel_v = { -1.0, 2, -1.0 };
		kernel.put(0, 0, kernel_v);
		// Imgproc.filter2D(marte, filtrado, -1, kernel);
		/**
		 * 4°) Por fim o operador mmopen que eliminou grande parte dos ruídos
		 * presentes na imagem - Com a finalidade de eliminar tais segmentos
		 * indesejáveis, aplicou-se a operação de abertura.O elemento
		 * estruturante utilizado foi o disco de raio 2.
		 * 
		 */
		// Mat filtrado = new Mat();
		// Mat kernel = new Mat(1, 3, CvType.CV_64F);
		/// double[] kernel_v = { -1.0, 2, -1.0 };
		// kernel.put(0, 0, kernel_v);
		// Imgproc.filter2D(marte, filtrado, -1,kernel);
		/**
		 * /** 5°) Terminada a etapa de detecção, para validar a metodologia
		 * desenvolvida, o resultado obtido foi sobreposto às imagens originais
		 * 
		 */
	}
}
