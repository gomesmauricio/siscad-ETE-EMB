package view;

import java.awt.EventQueue;
//import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JTextPane;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import Excecao.BancoVazioException;
import controller.PessoaController;
import model.Pessoa;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.awt.event.ActionEvent;

public class TelaPrincipal {

	private JFrame frmEteemb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmEteemb.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEteemb = new JFrame();
		frmEteemb.setResizable(false);
		frmEteemb.setTitle("SISCAD - Sistema de Cadastro");
		frmEteemb.setBounds(100, 100, 732, 408);
		frmEteemb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEteemb.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 726, 21);
		frmEteemb.getContentPane().add(menuBar);

		JMenu Manutenção = new JMenu("Manuten\u00E7\u00E3o");
		menuBar.add(Manutenção);

		JMenuItem mntmNewMenuItem = new JMenuItem("Cadastro");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastro cad = new TelaCadastro();
				cad.setModal(true);
				cad.setVisible(true);

			}
		});
		Manutenção.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Altera\u00E7\u00E3o/Exclus\u00E3o");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAlterar altera = new TelaAlterar();
				altera.setModal(true);
				altera.setVisible(true);
			}
		});
		Manutenção.add(mntmNewMenuItem_1);

		JMenu Consultas = new JMenu("Consultas");
		menuBar.add(Consultas);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Listagem");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListar listar = new TelaListar();
				listar.setModal(true);
				listar.setVisible(true);
			}
		});
		Consultas.add(mntmNewMenuItem_3);

		JMenu Relatorios = new JMenu("Relatorios");
		menuBar.add(Relatorios);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Cadastros Ativos");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final Font BOLD = new Font(FontFamily.TIMES_ROMAN, 16, Font.BOLD);
				final Font BOLD_12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);
				Document document = null;
				
				GregorianCalendar gc = new GregorianCalendar();
				SimpleDateFormat sdf = new SimpleDateFormat("EEEEEE dd/MM/yyyy hh:mm");
				String hora = sdf.format(gc.getTime());

				PessoaController controller = new PessoaController();
				List<Pessoa> listaPessoa;

				try {
					document = new Document(PageSize.A4);
					document.setMargins(60, 45, 60, 90);
					String path = "C:\\Users\\José Mauricio\\Desktop\\" + "Relatorio-Pessoas-Ativas-SISCAD.pdf";

					File file = new File(path);

					if (!file.exists()) {
						file.createNewFile();
					}

					PdfWriter.getInstance(document, new FileOutputStream(file.getAbsoluteFile()));

					document.open();

					listaPessoa = controller.relatorioPessoaAtiva();

					Paragraph cabecalho = new Paragraph();
					cabecalho.add(new Phrase("SISCAD - Sistema de Cadastro\n", BOLD));
					cabecalho.add(new Phrase("Relatorio de Pessoas Ativas\n\n"));

					// Adicionando Imagem no cabeçalho
					Image figura = Image
							.getInstance("C:\\Users\\José Mauricio\\eclipse-workspace\\ETE-EMB\\img\\etemb.png");

					// Seta tamanho da imagem
					figura.scaleToFit(110, 110);

					// Adiciona Imgem
					document.add(figura);
			

					cabecalho.setAlignment(Element.ALIGN_CENTER);
					cabecalho.setIndentationLeft(18);
					cabecalho.setFirstLineIndent(-18);
					cabecalho.add(" ");
					document.add(cabecalho);

					Paragraph rotulosCampos = new Paragraph();
					rotulosCampos.add(new Chunk((new DottedLineSeparator())));
					rotulosCampos.add(new Chunk("\nNome", BOLD_12));
					rotulosCampos.setTabSettings(new TabSettings(230f));
					rotulosCampos.add(Chunk.TABBING);
					rotulosCampos.add(new Chunk("Endereço\n", BOLD_12));
					rotulosCampos.add(new Chunk((new DottedLineSeparator())));

					document.add(rotulosCampos);

					Paragraph registroPessoa = new Paragraph();

					for (Pessoa pessoa : listaPessoa) {

						registroPessoa.add(new Chunk(pessoa.getNome()));
						registroPessoa.setTabSettings(new TabSettings(230f));
						registroPessoa.add(Chunk.TABBING);
						registroPessoa.add(new Chunk(pessoa.getEndereco() + "\n"));

					}

					document.add(registroPessoa);
					document.add(Chunk.NEWLINE);
					
					// Incluir Linha Pontilhada após o ultimo registro do relatório
					Paragraph rodape = new Paragraph();
					rodape.add(new Chunk((new DottedLineSeparator())));
					document.add(rodape);
					
					Paragraph horario = new Paragraph();
					horario.add(new Chunk(new Chunk(hora)));
					document.add(horario);
					
					
					
				        

				} catch (DocumentException ez) {
					ez.printStackTrace();
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				} catch (IOException es) {
					es.printStackTrace();
				} catch (BancoVazioException e1) {
					e1.printStackTrace();
				} finally {
					document.close();
					JOptionPane.showMessageDialog(null, "Documento Criado Com Sucesso");
				}

			}
		});

		Relatorios.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Geral Completo");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final Font BOLD = new Font(FontFamily.TIMES_ROMAN, 16, Font.BOLD);
				final Font BOLD_11 = new Font(FontFamily.TIMES_ROMAN, 11, Font.BOLD);
				Document document = null;
				
				GregorianCalendar gc = new GregorianCalendar();
				SimpleDateFormat sdf = new SimpleDateFormat("EEEEEE dd/MM/yyyy hh:mm");
				String hora = sdf.format(gc.getTime());

				PessoaController controller = new PessoaController();
				List<Pessoa> listaPessoa;

				try {
					document = new Document(PageSize.A3);
					document.setMargins(80, 65, 80, 110);
					String path = "C:\\Users\\José Mauricio\\Desktop\\" + "Relatorio-Geral-SISCAD.pdf";

					File file = new File(path);

					if (!file.exists()) {
						file.createNewFile();
					}

					PdfWriter.getInstance(document, new FileOutputStream(file.getAbsoluteFile()));

					document.open();

					listaPessoa = controller.relatorioGeral();

					Paragraph cabecalho = new Paragraph();
					cabecalho.add(new Phrase("SISCAD - Sistema de Cadastro\n", BOLD));
					cabecalho.add(new Phrase("Relatorio Geral de Pessoas\n\n"));

					// Adicionando Imagem no cabeçalho
					Image figura = Image
							.getInstance("C:\\Users\\José Mauricio\\eclipse-workspace\\ETE-EMB\\img\\etemb.png");

					// Seta tamanho da imagem
					figura.scaleToFit(100, 80);

					// Adiciona Imgem
					document.add(figura);

					cabecalho.setAlignment(Element.ALIGN_CENTER);
					cabecalho.setIndentationLeft(18);
					cabecalho.setFirstLineIndent(-18);
					cabecalho.add(" ");
					document.add(cabecalho);

					Paragraph rotulosCampos = new Paragraph();
					rotulosCampos.add(new Chunk((new DottedLineSeparator()) ));
					rotulosCampos.add(new Chunk("\nCód." +"     ", BOLD_11));
					rotulosCampos.setTabSettings(new TabSettings(70f));
					
					rotulosCampos.add(new Chunk("Nome   "+ "                          ", BOLD_11));
					rotulosCampos.add(Chunk.TABBING);
					rotulosCampos.add(new Chunk(" "+"Sexo"+"   ", BOLD_11));
					rotulosCampos.add(new Chunk("Endereço " +"                        ", BOLD_11));
					rotulosCampos.add(Chunk.TABBING);
					rotulosCampos.add(new Chunk("Cargo" +" ", BOLD_11));
					rotulosCampos.add(Chunk.TABBING);
					rotulosCampos.add(new Chunk("Status" +"    ", BOLD_11));
//					rotulosCampos.add(Chunk.TABBING);
					rotulosCampos.add(new Chunk("Nascimento" + "     ", BOLD_11));
//					rotulosCampos.add(Chunk.TABBING);
					rotulosCampos.add(new Chunk("Cadastro \n", BOLD_11));
					rotulosCampos.add(new Chunk((new DottedLineSeparator()  )   ));

					document.add(rotulosCampos);

					Paragraph registroPessoa = new Paragraph();

					for (Pessoa pessoa : listaPessoa) {
						
						registroPessoa.setTabSettings(new TabSettings(70f));
						registroPessoa.add(new Chunk(Integer.toString(pessoa.getId()) +"     " ));

						registroPessoa.add(new Chunk(pessoa.getNome() +"                      "));
						registroPessoa.add(Chunk.TABBING);
						
						registroPessoa.add(new Chunk(pessoa.getSexo() +"    "));

						registroPessoa.add(new Chunk(pessoa.getEndereco() +"                 " ));
						registroPessoa.add(Chunk.TABBING);
						registroPessoa.add(new Chunk(pessoa.getCargo() + "      "         ));
						registroPessoa.add(Chunk.TABBING);
						registroPessoa.add(new Chunk(" "+Integer.toString(pessoa.getAtivo()) +"        " ));
						
						rotulosCampos.add(Chunk.TABBING);
						//Converte data de Nascimento para String e Imprime				       
						
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				        String dataFormatada = dateFormat.format(pessoa.getDataNascimento());
						registroPessoa.add(new Chunk(dataFormatada)+"    ");
						rotulosCampos.add(Chunk.TABBING);
						DateFormat dateFormatcad1 = new SimpleDateFormat("dd/MM/yyyy");
						String dataFormatadacad = dateFormatcad1.format(pessoa.getDataCadastro());
						registroPessoa.add(new Chunk(dataFormatadacad)+"           ");
						
						

					}

					document.add(registroPessoa);
					document.add(Chunk.NEWLINE);

					// Incluir Linha Pontilhada após o ultimo registro do relatório
					Paragraph rodape = new Paragraph();
					rodape.add(new Chunk((new DottedLineSeparator())));
					document.add(rodape);
					
					Paragraph horario = new Paragraph();
					horario.add(new Chunk(new Chunk(hora)));
					document.add(horario);

				} catch (DocumentException ez) {
					ez.printStackTrace();
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				} catch (IOException es) {
					es.printStackTrace();
				} catch (BancoVazioException e1) {
					e1.printStackTrace();
				} finally {
					document.close();
					JOptionPane.showMessageDialog(null, "Documento Criado Com Sucesso");
				}

			}
		});
		Relatorios.add(mntmNewMenuItem_5);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Aniversariantes");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final Font BOLD = new Font(FontFamily.TIMES_ROMAN, 16, Font.BOLD);
				final Font BOLD_12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);
				Document document = null;
				
				GregorianCalendar gc = new GregorianCalendar();
				SimpleDateFormat sdf = new SimpleDateFormat("EEEEEE dd/MM/yyyy hh:mm");
				String hora = sdf.format(gc.getTime());
				

				PessoaController controller = new PessoaController();
				List<Pessoa> listaPessoa;

				try {
					document = new Document(PageSize.A4);
					document.setMargins(60, 45, 60, 90);
					String path = "C:\\Users\\José Mauricio\\Desktop\\" + "Relatorio-Aniversariantes-SISCAD.pdf";
					File file = new File(path);

					if (!file.exists()) {
						file.createNewFile();
					}

					PdfWriter.getInstance(document, new FileOutputStream(file.getAbsoluteFile()));

					document.open();

					listaPessoa = controller.relatorioPessoaAniversario();

					Paragraph cabecalho = new Paragraph();
					cabecalho.add(new Phrase("SISCAD - Sistema de Cadastro\n", BOLD));
					cabecalho.add(new Phrase("Relatorio Aniversariantes do Mês\n\n"));
					
					// Adicionando Imagem no cabeçalho
					Image figura = Image
							.getInstance("C:\\Users\\José Mauricio\\eclipse-workspace\\ETE-EMB\\img\\etemb.png");

					// Seta tamanho da imagem
					figura.scaleToFit(100, 80);

					// Adiciona Imgem
					document.add(figura);
					
					cabecalho.setAlignment(Element.ALIGN_CENTER);
					cabecalho.setIndentationLeft(18);
					cabecalho.setFirstLineIndent(-18);
					cabecalho.add(" ");
					document.add(cabecalho);

					Paragraph rotulosCampos = new Paragraph();
					rotulosCampos.add(new Chunk((new DottedLineSeparator())));
					rotulosCampos.add(new Chunk("\nNome", BOLD_12));
					rotulosCampos.setTabSettings(new TabSettings(160f));
					rotulosCampos.add(Chunk.TABBING);
					rotulosCampos.add(new Chunk("Aniversário", BOLD_12));
					rotulosCampos.add(Chunk.TABBING);
					rotulosCampos.add(new Chunk("Cargo\n", BOLD_12));

					rotulosCampos.add(new Chunk((new DottedLineSeparator())));

					document.add(rotulosCampos);

					Paragraph registroPessoa = new Paragraph();
					String cargo = " ";
					for (Pessoa pessoa : listaPessoa) {

						switch (pessoa.getCargo()) {

						case "1":
							cargo = " Administrador";

							break;

						case "2":
							cargo = " Analista de Sistemas";

							break;

						case "3":
							cargo = " Contador";

							break;

						case "4":
							cargo = " Desenvolvedor";

							break;

						case "5":
							cargo = " Eng. Sivil";

							break;

						}

						registroPessoa.add(new Chunk(pessoa.getNome()));
						registroPessoa.setTabSettings(new TabSettings(160f));
						registroPessoa.add(Chunk.TABBING);
						registroPessoa.add(new Chunk(pessoa.getDataNascimento() + ""));
						registroPessoa.add(Chunk.TABBING);
						registroPessoa.add(new Chunk(pessoa.getCargo() + " " + cargo + "\n"));

					}

					document.add(registroPessoa);
					document.add(Chunk.NEWLINE);

					// Incluir Linha Pontilhada após o ultimo registro do relatório
					Paragraph rodape = new Paragraph();
					rodape.add(new Chunk((new DottedLineSeparator())));
					document.add(rodape);
					
					Paragraph horario = new Paragraph();
					horario.add(new Chunk(new Chunk(hora)));
					document.add(horario);

				} catch (DocumentException ez) {
					ez.printStackTrace();
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				} catch (IOException es) {
					es.printStackTrace();
				} catch (BancoVazioException e1) {
					e1.printStackTrace();
				} finally {
					document.close();
					JOptionPane.showMessageDialog(null, "Documento Criado Com Sucesso");
				}

			}
		});
		Relatorios.add(mntmNewMenuItem_2);

		JMenu Sobre = new JMenu("Sobre");
		Sobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListar telaListar = new TelaListar();
				telaListar.setVisible(true);
			}
		});
		menuBar.add(Sobre);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Sobre");
		Sobre.add(mntmNewMenuItem_6);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Ajuda");
		Sobre.add(mntmNewMenuItem_7);

		JLabel Logo = new JLabel("");
		Logo.setIcon(new ImageIcon("C:\\Users\\Jos\u00E9 Mauricio\\eclipse-workspace\\ETE-EMB\\img\\etemb.png"));
		Logo.setBounds(203, 105, 300, 170);
		frmEteemb.getContentPane().add(Logo);
	}
}
