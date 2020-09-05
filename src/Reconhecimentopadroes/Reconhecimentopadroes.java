package Reconhecimentopadroes;

import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Reconhecimentopadroes {
	public static void main(String[] args){
		int linha; 
		int coluna; 
		int linhaMarcada = 0; 
		int colunaMarcada = 0; 
		int indice = 2;
		double[] valoresDouble; 
		double[] somatorioLinha; 
		double[] somatorioColuna; 
		double linhaMaior; 
		double colunaMaior; 
		double[][] dadosTabulados; 
		String enderecoArquivo = "/home/eduardo/NetBeansProjects/Reconhecimentopadores/src/Reconhecimentopadroes/dados.txt";
		String dados = null; 
		String linhaLida; 
		String[] valoresString; 
		Scanner leitor = new Scanner(System.in);
		
		//Tratamento de exceções
		try{
			FileReader leitorArquivo = new FileReader(enderecoArquivo); 
			BufferedReader buffer = new BufferedReader(leitorArquivo);
			
			linhaLida = buffer.readLine(); 
			dados = linhaLida; 
			
			while(linhaLida != null){ 
				linhaLida = buffer.readLine(); 
				dados += linhaLida; 
			}
			leitorArquivo.close(); 
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
		valoresDouble = new double[valoresString.length - 1]; 
		
		
		for(int i = 0; i < (valoresDouble.length); i++){
			valoresDouble[i] = Double.valueOf(valoresString[i]).doubleValue();
		}
			
		linha = (int) valoresDouble[0];
		coluna = (int) valoresDouble[1];
		
		dadosTabulados = new double[linha][coluna];
		somatorioLinha = new double[linha];
		somatorioColuna = new double[coluna];
		
		System.out.println("\nDados lidos e convertidos para double");
		for(int i = 0; i < valoresDouble.length; i++){
			System.out.print(valoresDouble[i] + "\n");
		}
			
		
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