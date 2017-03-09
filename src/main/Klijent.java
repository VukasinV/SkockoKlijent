package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Klijent {
	static int port;
	static Socket soketZaKomunikaciju = null;
	static PrintStream izlazKaServeru = null;
	static BufferedReader ulazKaServeru = null;
	static BufferedReader konzola = null;
	
	public static void main(String[] args) {
		try {
			
		soketZaKomunikaciju = new Socket("localhost", port);
		konzola = new BufferedReader(new InputStreamReader(System.in));
		ulazKaServeru = new BufferedReader(new InputStreamReader(soketZaKomunikaciju.getInputStream()));
		izlazKaServeru = new PrintStream(soketZaKomunikaciju.getOutputStream());
		// e sad ovde treba da nastane komunikacija
		// i treba da se pozovu metode iz klase "Igra"
		
		
		
		} catch (Exception e) {
			System.out.println("Negde si se ukanalio!");
		}
	}
}
