package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;

public class TelaProduto extends JDialog {
	private JTextField txtBarcode;
	private JTextField txtProduto;
	private JTextField txtFabricante;
	private JTextField txtQuantidade;
	private JTextField txtValor;
	private JTextField txtEstoqueMinimo;
	private JTextField txtLocalizacao;
	private JDateChooser dateChooser;
	private JComboBox cboUnidade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaProduto dialog = new TelaProduto();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public TelaProduto() {
		setTitle("CONEST-Produtos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaProduto.class.getResource("/icones/pc.png")));
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaProduto.class.getResource("/icones/barcode.png")));
		lblNewLabel.setBounds(46, 58, 64, 45);
		getContentPane().add(lblNewLabel);

		txtBarcode = new JTextField();
		txtBarcode.setBounds(120, 83, 310, 20);
		getContentPane().add(txtBarcode);
		txtBarcode.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Produto:");
		lblNewLabel_1.setBounds(46, 139, 64, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Fabricante:");
		lblNewLabel_2.setBounds(46, 190, 72, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Validade:");
		lblNewLabel_3.setBounds(502, 184, 56, 27);
		getContentPane().add(lblNewLabel_3);

		txtProduto = new JTextField();
		txtProduto.setBounds(120, 136, 605, 20);
		getContentPane().add(txtProduto);
		txtProduto.setColumns(10);

		txtFabricante = new JTextField();
		txtFabricante.setBounds(120, 184, 296, 20);
		getContentPane().add(txtFabricante);
		txtFabricante.setColumns(10);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(565, 184, 160, 20);
		getContentPane().add(dateChooser);

		JLabel lblNewLabel_4 = new JLabel("Quantidade:");
		lblNewLabel_4.setBounds(46, 237, 77, 20);
		getContentPane().add(lblNewLabel_4);

		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(120, 237, 108, 20);
		getContentPane().add(txtQuantidade);
		txtQuantidade.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Unidade de Medida:");
		lblNewLabel_5.setBounds(251, 240, 117, 14);
		getContentPane().add(lblNewLabel_5);

		cboUnidade = new JComboBox();
		cboUnidade.setModel(new DefaultComboBoxModel(new String[] { "", "Un", "Pct", "Cx", "Kg" }));
		cboUnidade.setBounds(378, 236, 117, 22);
		getContentPane().add(cboUnidade);

		JLabel lblNewLabel6 = new JLabel("Estoque Minimo:");
		lblNewLabel6.setBounds(533, 239, 96, 17);
		getContentPane().add(lblNewLabel6);

		JLabel lblNewLabel_6 = new JLabel("Valor:");
		lblNewLabel_6.setBounds(46, 297, 46, 14);
		getContentPane().add(lblNewLabel_6);

		txtValor = new JTextField();
		txtValor.setBounds(121, 294, 125, 20);
		getContentPane().add(txtValor);
		txtValor.setColumns(10);

		txtEstoqueMinimo = new JTextField();
		txtEstoqueMinimo.setBounds(639, 237, 86, 20);
		getContentPane().add(txtEstoqueMinimo);
		txtEstoqueMinimo.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Localiza\u00E7\u00E3o:");
		lblNewLabel_7.setBounds(298, 297, 72, 14);
		getContentPane().add(lblNewLabel_7);

		txtLocalizacao = new JTextField();
		txtLocalizacao.setBounds(379, 294, 346, 20);
		getContentPane().add(txtLocalizacao);
		txtLocalizacao.setColumns(10);

		JButton btnAdicionarProduto = new JButton("");
		btnAdicionarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Evento clicar no botão adicionar produto
				inserirProduto();
			}
		});
		btnAdicionarProduto.setToolTipText("Adicionar produto");
		btnAdicionarProduto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionarProduto.setIcon(new ImageIcon(TelaProduto.class.getResource("/icones/create.png")));
		btnAdicionarProduto.setBorder(null);
		btnAdicionarProduto.setBackground(SystemColor.control);
		btnAdicionarProduto.setBounds(120, 356, 64, 64);
		getContentPane().add(btnAdicionarProduto);

		// Validacao dos campos com a biblioteca ATxy2k

		// Txtbarcode
		RestrictedTextField Barcode = new RestrictedTextField(txtBarcode);
		Barcode.setLimit(50);

		// Txtproduto
		RestrictedTextField produto = new RestrictedTextField(txtProduto);
		produto.setLimit(100);

		// Txtfabricante
		RestrictedTextField fabricante = new RestrictedTextField(txtFabricante);
		fabricante.setLimit(100);

		// TxtQuantidade
		RestrictedTextField quantidade = new RestrictedTextField(txtQuantidade);
		quantidade.setOnlyNums(true);
		quantidade.setLimit(9);

		// TxtEstoqueMinimo
		RestrictedTextField estoquemin = new RestrictedTextField(txtEstoqueMinimo);
		estoquemin.setOnlyNums(true);
		estoquemin.setLimit(9);
		// Obs:(dentro de parentes escolhemos os caracteres permitidos)
		RestrictedTextField valor = new RestrictedTextField(txtValor, "0123456789.");
		valor.setLimit(8);

		// txtLocalizacao
		RestrictedTextField localizacao = new RestrictedTextField(txtLocalizacao);
		fabricante.setLimit(100);

	}// Fim do construtor

	// Criar um objeto para acessar o metodo conectar
	DAO dao = new DAO();

	// CRUD
	/**
	 * Inserir Produto (CRUD Creat)
	 */

	private void inserirProduto() {
		// Validação de campos obrigatorio

		if (txtProduto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo do produto");

			// Linha abaixo retorna o cursor ao campo txtProduto
			txtProduto.requestFocus();

		} else if (txtFabricante.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do Fabricante");
			txtFabricante.requestFocus();

		} else if (dateChooser.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Preencha a data de validade");
			dateChooser.requestFocus();

		} else if (cboUnidade.getSelectedItem() == "") {
			JOptionPane.showMessageDialog(null, "Preencha a unidade de medida");
			cboUnidade.requestFocus();

		} else if (txtQuantidade.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Preencha o campo quantidade");

		} else if (txtEstoqueMinimo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Estoque minimo");
			txtEstoqueMinimo.requestFocus();

		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o valor do produto");
			txtValor.requestFocus();

		} else if (txtLocalizacao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe a localização do produto");
			txtLocalizacao.requestFocus();

		} else {
			// Validacao bem sucedida -> inserir os dados no banco Query
			String insertProdutos = "insert into estoque (barcode,produto,fabricante,dataval,quantidade,estoquemin,medida,valor,localizacao) values (?,?,?,?,?,?,?,?,?)";
			// tratamento excessoes
			try {
				// Estabelecer a conexão com o banco
				Connection con = dao.conectar();
				// Preparar a conexão substituindo os parametros"?" pelo conteudo do objetos
				// (caixas de texto combobox etc.)
				PreparedStatement pst = con.prepareStatement(insertProdutos);
				pst.setString(1, txtBarcode.getText());
				pst.setString(2, txtProduto.getText());
				pst.setString(3, txtFabricante.getText());
				// Formatar o valor do JCalendario para inserção correta do banco
				SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
				String dataValidade = formatador.format(dateChooser.getDate());
				pst.setString(4, dataValidade);
				pst.setString(5, txtQuantidade.getText());
				pst.setString(6, txtEstoqueMinimo.getText());
				// Captura do Combobox
				pst.setString(7, cboUnidade.getSelectedItem().toString());
				pst.setString(8, txtValor.getText());
				pst.setString(9, txtLocalizacao.getText());
				// Executar a query (confirmação)
				int sucesso = pst.executeUpdate();
				if (sucesso == 1) {
					JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
				}
				// limpar campos
				txtBarcode.setText(null);
				txtProduto.setText(null);
				txtFabricante.setText(null);
				dateChooser.setDate(null);
				txtQuantidade.setText(null);
				txtEstoqueMinimo.setText(null);
				cboUnidade.setSelectedItem(null);
				txtValor.setText(null);
				txtLocalizacao.setText(null);
				con.close();
			} catch (SQLIntegrityConstraintViolationException e) {
				JOptionPane.showMessageDialog(null, "Código de barras Duplicado");
				txtBarcode.setText(null);
				txtBarcode.requestFocus();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}