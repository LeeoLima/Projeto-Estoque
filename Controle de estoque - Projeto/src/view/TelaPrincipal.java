package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;	
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		setTitle("CONEST - Sistema de controle de estoque");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/icones/pc.png")));
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				// evento que é "disparado" quando a janela do JFRAME é ativada
				status();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(10, 502, 764, 48);
		contentPane.add(panel);
		panel.setLayout(null);

		lblDataLabel = new JLabel("");
		lblDataLabel.setForeground(SystemColor.textHighlightText);
		lblDataLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDataLabel.setBounds(427, 11, 297, 26);
		panel.add(lblDataLabel);
		
		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/dbof.png")));
		lblStatus.setBounds(10, 11, 32, 32);
		panel.add(lblStatus);

		JButton btnProduto = new JButton("");
		btnProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaProduto produto = new TelaProduto();
				produto.setVisible(true);
			}
		});
		btnProduto.setToolTipText("Entrada");
		btnProduto.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/entrada.png")));
		btnProduto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnProduto.setBounds(106, 71, 128, 128);
		contentPane.add(btnProduto);

		JButton btnSaida = new JButton("");
		btnSaida.setToolTipText("Saida");
		btnSaida.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/saida.png")));
		btnSaida.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSaida.setBounds(540, 71, 128, 128);
		contentPane.add(btnSaida);

		JButton btnConsulta = new JButton("");
		btnConsulta.setToolTipText("Consulta");
		btnConsulta.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/consulta.png")));
		btnConsulta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConsulta.setBounds(322, 71, 128, 128);
		contentPane.add(btnConsulta);

		JButton btnFornecedores = new JButton("");
		btnFornecedores.setToolTipText("Fornecedor");
		btnFornecedores.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/fornecedor.png")));
		btnFornecedores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFornecedores.setBounds(106, 288, 128, 128);
		contentPane.add(btnFornecedores);

		JButton btnRelatorio = new JButton("");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRelatorio.setToolTipText("Relatorios");
		btnRelatorio.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/report.png")));
		btnRelatorio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorio.setBounds(322, 288, 128, 128);
		contentPane.add(btnRelatorio);

		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Evento clicar no botão sobre
				TelaSobre sobre = new TelaSobre();
				sobre.setVisible(true);
			}
		});
		btnSobre.setToolTipText("Sobre");
		btnSobre.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/about.png")));
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setBounds(540, 288, 128, 128);
		contentPane.add(btnSobre);
	} // fim do construtor

	// Instaciar(criar) um objeto para acessar a classe DAO

	DAO dao = new DAO();
	private JLabel lblDataLabel;
	private JLabel lblStatus;

	/**
	 * Status da conexão
	 */
	private void status() {
		try {
			// estabelecer uma conexão
			Connection con = dao.conectar();
			// status
			// System.out.println(con);
			// trocando o ícone do banco(status de conexão)
			if (con != null) {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dbon.png")));
			} else {
				System.out.println(new javax.swing.ImageIcon(getClass().getResource("/icones/dbof.png")));
			}
			// encerrar conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		// Modificar a label do rodapé para a data atual
		Date datalabel = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
		lblDataLabel.setText(formatador.format(datalabel));
	}
}
