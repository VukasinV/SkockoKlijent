
package main;

import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JDialog;

public class Klijent extends Window implements Runnable {
	static Socket soketZaKomunikaciju = null;
	static PrintStream izlazniTokKaServeru = null;
	static BufferedReader ulazniTokOdServera = null;
	static BufferedReader ulazKonzola = null;
	public static String temp;

	static boolean kraj = false;

	public static void main(String[] args) {
		try {
			int port = 2222;
			if (args.length > 0) {
				port = Integer.parseInt(args[0]);
			}
			soketZaKomunikaciju = new Socket("localhost", port);
			ulazKonzola = new BufferedReader(new InputStreamReader(System.in));
			izlazniTokKaServeru = new PrintStream(soketZaKomunikaciju.getOutputStream());
			ulazniTokOdServera = new BufferedReader(new InputStreamReader(soketZaKomunikaciju.getInputStream()));
			new Thread(new Klijent()).start();
			Window window = new Window();
			Login login = new Login();
			login.setVisible(true);
			window.setVisible(true);
			while (!kraj) {
				//izlazniTokKaServeru.println(ulazKonzola.readLine());
			}
			soketZaKomunikaciju.close();
		} catch (UnknownHostException e) {
			System.err.println("DONT KNOW ABOUT HOST " + e);
		} catch (IOException e) {
			System.err.println("IOException :" + e);
		}
	}

	public void run() {
		String linijaOdServera;
		try {
			while ((linijaOdServera = ulazniTokOdServera.readLine()) != null) {
				System.out.println(linijaOdServera);
				if (linijaOdServera.indexOf("***") == 0) {
					kraj = true;
					return;
				}
				
				if (linijaOdServera.startsWith("Ime")||linijaOdServera.startsWith("Opet")) {
					System.out.println("Uvatio je u promenjivu");
					Login.odgovor = linijaOdServera;
				}
			}
		} catch (IOException e) {
			System.err.println("IOException " + e);
		}
	}

}
