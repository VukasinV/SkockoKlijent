
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Klijent extends Window implements Runnable {
	static Socket soketZaKomunikaciju = null;
	static PrintStream izlazniTokKaServeru = null;
	static BufferedReader ulazniTokOdServera = null;
	static BufferedReader ulazKonzola = null;
	public static String temp;
	public static boolean loginZatvoren = false;
	static boolean kraj = false;

	// Ovo je poslednji kod

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
			ListaOnlineIgraca listaIgraca = new ListaOnlineIgraca();
			login.setVisible(true);
			while (!loginZatvoren) {
				System.out.println("");
				if (loginZatvoren) {
					listaIgraca.setVisible(true);
					System.out.println("Promenjen status");
					break;
				}
			}
			while (!kraj) {
				// izlazniTokKaServeru.println(ulazKonzola.readLine());
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

				if (linijaOdServera.startsWith("Ime") || linijaOdServera.startsWith("Opet")) {
					System.out.println("Uvatio je u promenjivu");
					Login.odgovor = linijaOdServera;
				}
				if (linijaOdServera.startsWith("Lista") || linijaOdServera.startsWith("PLista")) {
					ListaOnlineIgraca.odgovor = linijaOdServera;
				}
				if (linijaOdServera.startsWith("Izabrao ")) {
					ListaOnlineIgraca.odgovor = linijaOdServera;
				}
				if (linijaOdServera.startsWith("Izazvao Vas je")) {
					String pom = linijaOdServera.split("@")[1];
					int opcion = JOptionPane.showConfirmDialog(null, "Izazvao vase je igrac: " + pom, "Obavestenje!", JOptionPane.YES_NO_OPTION);

					if (opcion == 0) { //The ISSUE is here
					   System.out.print("si");
					} else {
					   System.out.print("no");
					}
				}
			}
		} catch (IOException e) {
			System.err.println("IOException " + e);
		}
	}

}
