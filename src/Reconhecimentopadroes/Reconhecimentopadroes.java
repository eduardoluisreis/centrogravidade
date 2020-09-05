package Reconhecimentopadroes;

import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Reconhecimentopadroes {
	public static void main(String[] args){
		int linha; //Armazenha a linha informada no txt
		int coluna; //Armazenha a coluna informada no txt
		int linhaMarcada = 0; //Armazena a linha que compõe o centro de gravidade
		int colunaMarcada = 0; //Armazena a coluna que compõe o centro de gravidade
		int indice = 2; //Indice do vetor de doubles, descontados os valores informados para a linha e a coluna
		double[] valoresDouble; //Armazena os valores convertidos de String para double
		double[] somatorioLinha; //Armazena as somas de cada linha
		double[] somatorioColuna; //Armazena as somas de cada coluna
		double linhaMaior; //Valor arbitrário para a linha que compõe o centro de gravidade
		double colunaMaior; //Valor arbitrário para a coluna que compõe o centro de gravidade
		double[][] dadosTabulados; //Armazena os valores em formato matricial
		String enderecoArquivo = "/home/eduardo/NetBeansProjects/Reconhecimentopadores/src/Reconhecimentopadroes/dados.txt";
		String dados = null; //Armazena os valores de todas as linhas lidas
		String linhaLida; //Armazena cada linha lida
		String[] valoresString; //Armazena os valores da String dados
		Scanner leitor = new Scanner(System.in); //Caso queira informar o caminho do arquivo pelo console
		
		//Tratamento de exceções
		try{
			FileReader leitorArquivo = new FileReader(enderecoArquivo); //Lê um arquivo de texto
			BufferedReader buffer = new BufferedReader(leitorArquivo); //Lê um fluxo de caracteres armazenando-os em um buffer
			
			linhaLida = buffer.readLine(); //Lê uma linha
			dados = linhaLida; //Coloca a linha lida na String dados
			
			while(linhaLida != null){ 
				linhaLida = buffer.readLine(); //Lê uma linha
				dados += linhaLida; //Contaena a primeira linha lida com as demais
			}
			leitorArquivo.close(); //Fecha o FileReader
		}catch(IOException ioex){
			System.out.println("ERRO: " + ioex.getMessage());
		}
		
		//Mostra o tamanho dos dados se houver
		if(dados != null)
			System.out.print("\tTamanho da linha: " + dados.length() + "\n");
		else
			System.out.println("\tTamanho da linha: " + 0 + "\n");
		
		//Separa os valores da String dados com base em espaços
		valoresString = dados.split(" ");
		valoresDouble = new double[valoresString.length - 1]; //Determina o tamanho do vetor valoresDouble
		
		//Converte double e adiciona cada valor de do vetor de Strings valoresString em valoresDouble
		for(int i = 0; i < (valoresDouble.length); i++){
			valoresDouble[i] = Double.valueOf(valoresString[i]).doubleValue();
		}
			
		linha = (int) valoresDouble[0]; //Obtém o valor da linha informada no txt
		coluna = (int) valoresDouble[1]; //Obtém o valor da coluna informada no txt
		
		dadosTabulados = new double[linha][coluna]; //Define o tamanho da matriz dadosTabulados
		somatorioLinha = new double[linha]; //Define o tamanho da matriz somatorioLinha
		somatorioColuna = new double[coluna]; //Define o tamanho da matriz somatorioColuna
		
		System.out.println("\nDados lidos e convertidos para double");
		for(int i = 0; i < valoresDouble.length; i++){
			System.out.print(valoresDouble[i] + "\n");
		}
			
		//Passando os dados do vetor valoresDouble para  a matriz dadosTabulados
		for(int i = 0; i < linha; i++){
			for(int j = 0; j < coluna; j++){
				dadosTabulados[i][j] = valoresDouble[indice++];
			}
		}
		
		System.out.println("\nDados em formato matricial");
		for(int i = 0; i < linha; i++){
			for(int j = 0; j < coluna; j++){
				System.out.print(dadosTabulados[i][j] + " ");
			}
			System.out.println("");
		}
		
		System.out.println("\nObtendo os somatórios...");
		for(int i = 0; i < linha; i++){
			for(int j = 0; j < coluna; j++){
				somatorioLinha[i] += dadosTabulados[i][j];
				somatorioColuna[i] += dadosTabulados[j][i];
			}
			System.out.println("Linha["+ i +"]-> " + somatorioLinha[i]);
			System.out.println("Coluna["+ i +"]-> " + somatorioColuna[i]);
		}
		
		linhaMaior = dadosTabulados[0][0];
		colunaMaior = dadosTabulados[0][0];
		//Obtém a linha e a coluna de maior peso, bem como o índice da linha e da coluna
		for(int i = 0; i < somatorioLinha.length; i++){
			if(linhaMaior < somatorioLinha[i]){
				linhaMaior = somatorioLinha[i];
				linhaMarcada = i;
			}
			
			if(colunaMaior < somatorioColuna[i]){
				colunaMaior = somatorioColuna[i];
				colunaMarcada = i;
			}
		}
		
		System.out.println("\nLinha de maior peso: " + linhaMaior + " -  Coluna de maior peso: " + colunaMaior);
		System.out.println("\nCentro de gravidade = [" + linhaMarcada + "," + colunaMarcada + "]");
	}
}