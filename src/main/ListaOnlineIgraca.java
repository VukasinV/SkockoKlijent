package main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListaOnlineIgraca extends JFrame {

	private JPanel contentPane;
	private JList list;
	private JButton btnZahtevaj;
	private JButton btnZapocniIgru;

	DefaultListModel<String> listmodel = new DefaultListModel<String>();

	public static String odgovor;
	private JPanel panelce;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaOnlineIgraca frame = new ListaOnlineIgraca();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ListaOnlineIgraca() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanelce());
		contentPane.add(getBtnZahtevaj());
		contentPane.add(getBtnZapocniIgru());
		contentPane.add(getList());
	}

	private JList getList() {
		if (list == null) {
			list = new JList(listmodel);
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					Klijent.izlazniTokKaServeru.println("Izabrao igraca");
					while (ListaOnlineIgraca.odgovor == null) {
						System.out.println("Cekam odgovor od servera!");
					}
					String pom = list.getSelectedValue().toString();
					JOptionPane.showMessageDialog(new JFrame(),
							  pom+" <3");
					
					System.out.println(pom);
					
					Klijent.izlazniTokKaServeru.println("Zelim da igram sa:" + pom);				
				}
			});
			list.setBounds(197, 11, 198, 218);
			list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			list.setVisibleRowCount(-1);

		}
		return list;
	}

	private JButton getBtnZahtevaj() {
		if (btnZahtevaj == null) {
			btnZahtevaj = new JButton("ZahtevajListu");
			btnZahtevaj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Klijent.izlazniTokKaServeru.println("posalji listu");
					while (ListaOnlineIgraca.odgovor == null) {
						System.out.println("Cekam odgovor od servera!");
					}
					if (ListaOnlineIgraca.odgovor == null || odgovor.equals("PLista")) {
						JOptionPane.showMessageDialog(new JFrame(), "Trenutno nema igraca online, moraces da sacekas.");
					}

					if (ListaOnlineIgraca.odgovor != null && odgovor.startsWith("Lista")) {
						String[] zaListu = odgovor.split(",");
						for (int i = 1; i < zaListu.length; i++) {
							listmodel.addElement(zaListu[i]);
						}

						/*
						 * JOptionPane.showMessageDialog(new JFrame(),
						 * listmodel);
						 */
					}
				}
			});
			btnZahtevaj.setBounds(26, 11, 119, 102);
		}
		return btnZahtevaj;
	}

	private JButton getBtnZapocniIgru() {
		if (btnZapocniIgru == null) {
			btnZapocniIgru = new JButton("ZapocniIgru");
			btnZapocniIgru.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					for (int i = 0; i < listmodel.size(); i++) {
						String pom = list.getSelectedValue().toString();
						if(listmodel.getElementAt(i).equals(pom)){
							Klijent.izlazniTokKaServeru.println(pom);
							
						}
					}
					
				}
			});
			btnZapocniIgru.setBounds(26, 123, 118, 106);
		}
		return btnZapocniIgru;
	}

	private JPanel getPanelce() {
		if (panelce == null) {
			panelce = new JPanel();
			panelce.setBounds(0, 0, 0, 0);
			panelce.setLayout(null);
		}
		return panelce;
	}
}
