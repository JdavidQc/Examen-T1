package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.util.List;

import model.Autor;
import model.Libro;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class App_Quisuruco_Ciprian_Consulta extends JFrame implements ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtNombre;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JButton btnConsultar;

	public static void main(String[] args) {
		App_Quisuruco_Ciprian_Consulta frame = new App_Quisuruco_Ciprian_Consulta();
		frame.setVisible(true);

	}

	public App_Quisuruco_Ciprian_Consulta() {
		setBounds(0,0,373,500);
		setLocationRelativeTo(null);
		
		JPanel conatiner = new JPanel();
	
		getContentPane().add(conatiner);
		conatiner.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 56, 337, 61);
		conatiner.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Nombre de Libro:");
		lblNewLabel.setBounds(10, 24, 95, 14);
		panel.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(this);
		txtNombre.setBounds(115, 18, 106, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(238, 13, 89, 36);
		panel.add(btnConsultar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 128, 337, 322);
		conatiner.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		dtm.addColumn("Nombre");
		dtm.addColumn("Categoria");
		dtm.addColumn("Hoja");
		dtm.addColumn("Cod.Autor");
		dtm.addColumn("Edicion");
		table.setModel(dtm);
		
		lblNewLabel_1 = new JLabel("Consulta de Libro");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 11, 337, 34);
		conatiner.add(lblNewLabel_1);
	
		listarLibro();
		
	}
	private DefaultTableModel dtm = new DefaultTableModel();
	private EntityManagerFactory fabrica=Persistence.createEntityManagerFactory("mysql");
	
	
	private int fila;
	private JLabel lblNewLabel_1;
	private void listarLibro() {
		EntityManager em=fabrica.createEntityManager();
		
		TypedQuery<Libro> query=em.createQuery("Select l  from Libro l", Libro.class);
		List<Libro> lstAutor= query.getResultList();
		dtm.setRowCount(0);
		if(lstAutor.size()>0) {
			for(Libro l: lstAutor) {
			  Object[] fila= {l.getNombre(),l.getCate(),l.getN_hoja(),l.getId_autor(),l.getFecha_edicion()};
			  dtm.addRow(fila);
 
			}
			fila=lstAutor.size()+1;
		}
		em.close();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnConsultar) {
			actionPerformedBtnAgregar(e);
		}
	}
	protected void actionPerformedBtnAgregar(ActionEvent e) {
		EntityManager em=fabrica.createEntityManager();

		String sql = "Select l  from Libro l where l.nombre= :nombre";
		TypedQuery<Libro> query3=em.createQuery(sql,Libro.class);
		query3.setParameter("nombre",txtNombre.getText().trim());
		List<Libro> lstAutor= query3.getResultList();
		if(lstAutor.size()==0) {
		listarLibro();
		}else {
			dtm.setRowCount(0);
			for(Libro l: lstAutor) {
				  Object[] fila= {l.getNombre(),l.getCate(),l.getN_hoja(),l.getId_autor(),l.getFecha_edicion()};
				  dtm.addRow(fila);
	 
				}
		}
		

	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtNombre) {
			keyTypedTxtNombre(e);
		}
	}
	protected void keyTypedTxtNombre(KeyEvent e) {
		if(!Character.isLetter(e.getKeyChar())|| txtNombre.getText().length()>60) {
			e.consume();
			}
	}
}
