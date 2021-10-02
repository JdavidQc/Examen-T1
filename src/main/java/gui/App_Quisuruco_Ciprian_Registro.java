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

public class App_Quisuruco_Ciprian_Registro extends JFrame implements ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JLabel lblFechaEdicion;
	private JTextField txtFecha;
	private JComboBox<String> cboAutor;
	private JTextField txtHojas;
	private JTextField txtCategoria;
	private JTextField txtNombre;
	private JPanel panel;
	private JLabel lblAutor;
	private JLabel lblHojas;
	private JLabel lblCategoria;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JButton btnAgregar;

	public static void main(String[] args) {
		App_Quisuruco_Ciprian_Registro frame = new App_Quisuruco_Ciprian_Registro();
		frame.setVisible(true);

	}

	public App_Quisuruco_Ciprian_Registro() {
		setBounds(0,0,373,500);
		setLocationRelativeTo(null);
		
		JPanel conatiner = new JPanel();
	
		getContentPane().add(conatiner);
		conatiner.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 56, 337, 208);
		conatiner.add(panel);
		panel.setLayout(null);
		
		lblFechaEdicion = new JLabel("Fecha Edicion:");
		lblFechaEdicion.setBounds(10, 176, 95, 14);
		panel.add(lblFechaEdicion);
		
		lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(10, 138, 95, 14);
		panel.add(lblAutor);
		
		lblHojas = new JLabel("Hojas:");
		lblHojas.setBounds(10, 100, 95, 14);
		panel.add(lblHojas);
		
		lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(10, 62, 95, 14);
		panel.add(lblCategoria);
		
		lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(10, 24, 46, 14);
		panel.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(this);
		txtNombre.setBounds(115, 18, 106, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCategoria = new JTextField();
		txtCategoria.addKeyListener(this);
		txtCategoria.setBounds(115, 56, 106, 20);
		panel.add(txtCategoria);
		txtCategoria.setColumns(10);
		
		txtHojas = new JTextField();
		txtHojas.addKeyListener(this);
		txtHojas.setBounds(115, 94, 106, 20);
		panel.add(txtHojas);
		txtHojas.setColumns(10);
		
		txtFecha = new JTextField();
		txtFecha.addKeyListener(this);
		txtFecha.setBounds(115, 174, 106, 20);
		panel.add(txtFecha);
		txtFecha.setColumns(10);
		
		cboAutor = new JComboBox<String>();
		cboAutor.setBounds(115, 132, 106, 24);
		panel.add(cboAutor);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(238, 89, 89, 36);
		panel.add(btnAgregar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 275, 337, 175);
		conatiner.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		dtm.addColumn("Nombre");
		dtm.addColumn("Categoria");
		dtm.addColumn("Hoja");
		dtm.addColumn("Cod.Autor");
		dtm.addColumn("Edicion");
		table.setModel(dtm);
		
		lblNewLabel_1 = new JLabel("Registro de Libro");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 11, 337, 34);
		conatiner.add(lblNewLabel_1);
		
		listaCombo();
		listarLibro();
		
	}
	private DefaultTableModel dtm = new DefaultTableModel();
	private EntityManagerFactory fabrica=Persistence.createEntityManagerFactory("mysql");
	
	private void listaCombo() {
		cboAutor.addItem("Seleccione");

		//2.Obtener el DAO
		EntityManager em=fabrica.createEntityManager();
	
		TypedQuery<Autor> query=em.createQuery("Select a  from Autor a", Autor.class);
		List<Autor> lstAutor= query.getResultList();
		if(lstAutor.size()>0) {
			for(Autor a: lstAutor) {
				cboAutor.addItem(a.getNombreAutor());

			}
		}
		em.close();
		
		
	}
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
		if (e.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(e);
		}
	}
	protected void actionPerformedBtnAgregar(ActionEvent e) {
		EntityManager em=fabrica.createEntityManager();
		if(txtNombre.getText().length()<1 || txtCategoria.getText().length()<1 || txtHojas.getText().length()<1 || txtFecha.getText().length()<1 || cboAutor.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(null,"Ingrese Todo los campos");
		}else {
			if(txtFecha.getText().matches("^\\\\d{4}-\\\\d{2}-\\\\d{2}$")) {
				//procesos...registrar un nuevo usuario
				Libro a= new Libro(fila,txtNombre.getText(),txtCategoria.getText(),Integer.parseInt(txtHojas.getText()),
						txtFecha.getText(),Integer.parseInt(String.valueOf(cboAutor.getSelectedIndex())));
			
				
			
				em.getTransaction().begin();
				em.persist(a);
				em.getTransaction().commit();
				JOptionPane.showMessageDialog(null,"Registrado ok");
				em.close();
				listarLibro();
			}else {
				JOptionPane.showMessageDialog(null,"Fecha de Formato YYYY-MM-DD");
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
		if (e.getSource() == txtHojas) {
			keyTypedTxtHojas(e);
		}
		if (e.getSource() == txtFecha) {
			keyTypedTxtFecha(e);
		}
		if (e.getSource() == txtCategoria) {
			keyTypedTxtCategoria(e);
		}
	}
	protected void keyTypedTxtCategoria(KeyEvent e) {
		if(!Character.isLetter(e.getKeyChar()) || txtCategoria.getText().length()>20) {
			e.consume();
		}
	}
	protected void keyTypedTxtFecha(KeyEvent e) {
		if(!Character.isDigit(e.getKeyChar()) || txtFecha.getText().length()>=10) {
			e.consume();
		}
	}
	protected void keyTypedTxtHojas(KeyEvent e) {
		if(!Character.isDigit(e.getKeyChar())|| txtHojas.getText().length()>4) {
		e.consume();
		}
	}
	protected void keyTypedTxtNombre(KeyEvent e) {
		if(!Character.isLetter(e.getKeyChar())|| txtNombre.getText().length()>60) {
			e.consume();
			}
	}
}
