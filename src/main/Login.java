package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textIme;
	private String ime;
	public String privremenoIme;
	public static String odgovor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		setBounds(100, 100, 456, 188);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textIme = new JTextField();
			textIme.setBounds(168, 58, 86, 20);
			contentPanel.add(textIme);
			textIme.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Unesite ime");
			lblNewLabel.setBounds(182, 33, 55, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							privremenoIme = textIme.getText();
							Klijent.izlazniTokKaServeru.println("Proveri ime: " + privremenoIme);
							System.out.println("Stigao dovde");
							
							// odgovor = Klijent.ulazniTokOdServera.readLine();
							System.out.println("Odgovor je : " + odgovor);
							System.out.println("Primio je odgovor od servera");
							if (odgovor == null) {
								dispose();
							} else {
								JOptionPane.showMessageDialog(new JFrame(), "Ime vec postoji, izaberite novo: ");
							}
						} catch (Exception greskaKodKlikaNaOK) {
							System.out.println("Puko kod OK tastera");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(1);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrivremenoIme() {
		return ime;
	}

	public void setPrivremenoIme(String ime) {
		this.ime = ime;
	}

}
