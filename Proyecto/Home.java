import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Home extends JFrame implements ActionListener
{
	public JButton boton, boton2, boton3, boton4, continuar, continuarEl, regresar, regresar1, regresar2, consulta1, consulta2, consulta3, bm, eliminar, salir;
	public JPanel panel;
	public JTextField user;
	public JPasswordField pas;
	public JLabel x, y, a, j, e, i, z,t1,t2,t3,t4,t5,t6,t7,t8;
	public JComboBox tablaInser, options;
	public Container contenedor, contenedor2, contenedor3;
	private JRadioButton r1,r2;
	private ButtonGroup grupo;
	public JTextField inserta,insertb,insertc,insertd,inserte,insertf, insertg, inserth, insertRif;
	public JLabel insertx, inserty, insertz, insertw, insertww, insertu, insertv, insertt, insertr, inserts;
	public JComboBox insertTipo;
	public String sql = "", sql2 = "", prueba;
	JFrame ventana;

	//Listas de opciones desplegables
	String[] opciones = {"Insertar", "Eliminar"};
	String[] tablas = {"SELECCIONE LA TABLA","PRODUCTO", "EMPRESA", "MAQUINARIA", "CLIENTE", "CURSO", "MAESTRO", "FACTURA"};
	String[] tipos = {"Seleccione el tipo","Procesadora", "Chocolatera", "Tienda"};
	String[] tipop = {"Producto", "Ingrediente"};
	String[] rifemp = {"abcd", "acdb", "bdgs"};

	/***************************************************/

	public static final String BD_NAME = "proyecto_bd";
	public ConexionBD dbc = null;

	ResultSet Conexion(String sql)
	{
		Statement st;
		ResultSet x = null;
		try
		{
			st = this.dbc.getConnection().createStatement();
			x = st.executeQuery(sql);
		}
		catch(SQLException e)
		{
	  		JOptionPane.showMessageDialog (null, "Error: " + e.getMessage(), "Error Conexion", JOptionPane.ERROR_MESSAGE);
		}
		return x;
	}

	void borrar(String sql)
	{
		Statement st;
		try
		{
			st = this.dbc.getConnection().createStatement();
			st.executeQuery(sql);
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog (null, "Eliminacion Correcta");
		}
  }

	void guardar(String sql)
	{
		Statement st;
		try
		{
	  	st = this.dbc.getConnection().createStatement();
			st.executeQuery(sql);
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog (null, "Insercion Correcta");
		}
  }

	public static void main(String args[])
	{
		Home ventana = new Home();
		ventana.setVisible(true);
	}

	public Home()
	{
		inicio();
	}

	void ventana ()
	{
		this.setBounds(100,100,800,500); //tamaño de la ventana(eje x, eje y, ancho, largo)
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		//setOpacity(0.9f); //poner transparente la ventana
	}

	void inicio ()
	{
		ventana();
		contenedor = new Container();
		setContentPane(contenedor);
		try
		{
			this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("img/6.jpg")))));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		setTitle("--.Inicio.--"); ///coloca el nombre a la ventana
		a= new JLabel ("Ingrese Sus Datos de Acceso");
		a.setBounds(210, 90, 400,60);
		a.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		a.setFont(new java.awt.Font ("Times New Roman",1 , 24)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(a);

		x = new JLabel ("Nombre de usuario");
		x.setBounds(190, 190, 180,25);
		x.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		x.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(x);

		user = new JTextField();
		user.setBounds(360, 190, 150,25);
		this.getContentPane().add(user);

		y = new JLabel ("Contrase\u00f1a "); 							//\u00f1 para la 'ñ'
		y.setBounds(256, 220, 130,25);
		y.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		y.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(y);

		pas = new JPasswordField();
		pas.setBounds(360, 220, 150,25);
		this.getContentPane().add(pas);

		boton3 = new JButton("Iniciar"); 								//crea el boton
		boton3.setBounds(290,280,110,40);
		boton3.setForeground(Color.black);
		//boton3.setBackground(java.awt.Color.red);
		this.getContentPane().add(boton3); 								//agrega el boton a la ventana
		boton3.addActionListener(this);

		boton = new JButton("Salir"); 									//crea el boton
		boton.setBounds(420,280,110,40);
		boton.setForeground(Color.black);
		this.getContentPane().add(boton); 								//agrega el boton a la ventana
		boton.addActionListener(this);

		z = new JLabel ("G.J.W.O.");
		z.setBounds(720, 448, 180,25);
		z.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		z.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(z);
		repaint();
	}

  void homeTrabajador()
	{
		contenedor2=new Container();
		setContentPane(contenedor2);
		try
		{
			this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("img/6.jpg")))));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		String k = user.getText();
		setTitle("Sesion iniciada como: "+k); 							//coloca el nombre a la ventana
		this.setVisible(true);

		j = new JLabel ("Mostrar el Producto más costoso que se produce  en la “Chocolatera Cimarrón”,");
		j.setBounds(120, 20, 800,35);
		j.setForeground(Color.white);
		j.setFont(new java.awt.Font ("Times New Roman",1 , 12));
		this.getContentPane().add(j);
		t1 = new JLabel ("sus  distintas  etapas de producción y qué trabajadores están");
		t1.setBounds(170, 35, 800,35);
		t1.setForeground(Color.white);
		t1.setFont(new java.awt.Font ("Times New Roman",1 , 12));
		this.getContentPane().add(t1);
		t2 = new JLabel ("vinculados en cada etapa");
		t2.setBounds(300, 50, 800,35);
		t2.setForeground(Color.white);
		t2.setFont(new java.awt.Font ("Times New Roman",1 , 12));
		this.getContentPane().add(t2);
		consulta1 = new JButton("Consultar"); 									//crea el boton
		consulta1.setBounds(290,80,200,40);
		consulta1.setForeground(Color.black);
		this.getContentPane().add(consulta1); 								//agrega el boton a la ventana
		consulta1.addActionListener(this);

		/*Referente a consulta 2*/
		t3 = new JLabel ("Productos vendidos en el mes de mayo de 2016 y ganancias de cada Tienda");
		t3.setBounds(120, 135, 800,35);
		t3.setForeground(Color.white);
		t3.setFont(new java.awt.Font ("Times New Roman",1 , 12));
		this.getContentPane().add(t3);
		t4 = new JLabel ("“Cacao Venezolano” clasificadas por estado y ordenados por la clave");
		t4.setBounds(125, 150, 800,35);
		t4.setForeground(Color.white);
		t4.setFont(new java.awt.Font ("Times New Roman",1 , 12));
		this.getContentPane().add(t4);
		t5 = new JLabel ("del producto en forma ascendente y por la ganancia de forma descendente");
		t5.setBounds(120, 165, 800,35);
		t5.setForeground(Color.white);
		t5.setFont(new java.awt.Font ("Times New Roman",1 , 12));
		this.getContentPane().add(t5);
		consulta2 = new JButton("Consultar"); 									//crea el boton
		consulta2.setBounds(290,195,200,40);
		consulta2.setForeground(Color.black);
		this.getContentPane().add(consulta2); 								//agrega el boton a la ventana
		consulta2.addActionListener(this);

		/*Referente a consulta 3*/
		t6 = new JLabel ("Producto que ha ganado mayor cantidad de premios y toda la informacion");
		t6.setBounds(155, 250, 800,35);
		t6.setForeground(Color.white);
		t6.setFont(new java.awt.Font ("Times New Roman",1 , 12));
		this.getContentPane().add(t6);
		t7 = new JLabel ("del evento y de los premios otorgados");
		t7.setBounds(260, 260, 800,35);
		t7.setForeground(Color.white);
		t7.setFont(new java.awt.Font ("Times New Roman",1 , 12));
		this.getContentPane().add(t7);
		consulta3 = new JButton("Consultar"); 									//crea el boton
		consulta3.setBounds(290,295,200,40);
		consulta3.setForeground(Color.black);
		//boton.setBackground(java.awt.Color.red);
		this.getContentPane().add(consulta3); 								//agrega el boton a la ventana
		consulta3.addActionListener(this);

		boton2 = new JButton("Cerrar Sesion"); //crea el boton
		boton2.setBounds(317,380,150,40);
		this.getContentPane().add(boton2); //agrega el boton a la ventana
		boton2.addActionListener(this);

		z = new JLabel ("G.J.W.O.");
		z.setBounds(720, 448, 180,25);
		z.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		z.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(z);

		repaint();
	}

	void homeAdministrador()
	{
		contenedor3 = new Container();
		setContentPane(contenedor3);

		try
		{
	  		this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("img/8.jpg")))));
		}
		catch(IOException e)
		{
	  		e.printStackTrace();
		}

		String k = user.getText();
		setTitle("Sesion iniciada como: "+k); //coloca el nombre a la ventana
		this.setVisible(true);

		j = new JLabel ("Bienvenido");
		j.setBounds(350, 100, 150,80);
		j.setForeground(Color.white);
		j.setFont(new java.awt.Font ("Times New Roman",1 , 24)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(j);

		r1=new JRadioButton("Insertar");
		r1.setBounds(360,200,100,30);
		//r1.setBackground(java.awt.Color.gray);
		r1.setForeground(Color.red);
		r1.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(r1);
		r1.addActionListener(this);

		r2=new JRadioButton("Eliminar");
		r2.setBounds(360,230,100,30);
		r2.setForeground(Color.red);
		//r2.setBackground(java.awt.Color.gray);
		r2.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(r2);
		r2.addActionListener(this);

		grupo = new ButtonGroup();
		grupo.add(r1);
		grupo.add(r2);

		boton4 = new JButton("Continuar"); //crea el boton
		boton4.setBounds(280,300,110,40);
		boton4.setForeground(Color.black);
		//boton4.setBackground(java.awt.Color.blue);
		this.getContentPane().add(boton4); //agrega el boton a la ventana
		boton4.addActionListener(this);

		boton2 = new JButton("Cerrar sesion"); //crea el boton
		boton2.setBounds(420,300,130,40);
		boton2.setForeground(Color.black);
		//boton.setBackground(java.awt.Color.blue);
		this.getContentPane().add(boton2); //agrega el boton a la ventana
		boton2.addActionListener(this);

		z = new JLabel ("G.J.W.O.");
		z.setBounds(720, 448, 180,25);
		z.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		z.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(z);
	}

  void insercion ()
	{
		insertw = new JLabel ("Seleccione Donde Desea Insertar");
		insertw.setBounds(145, 170, 600,60);
		insertw.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertw.setFont(new java.awt.Font ("Times New Roman",1 , 24)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertw);
		insertw.setVisible(true);

    tablaInser = new JComboBox(tablas);
		tablaInser.setBounds(300, 270, 150,25);
		tablaInser.setForeground(Color.red); /// CAMBIAR COLOR DE LETRA
		tablaInser.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(tablaInser);
		tablaInser.addActionListener(this);

		regresar1 = new JButton("Regresar"); //crea el boton
		regresar1.setBounds(330,350,110,40);
		regresar1.setForeground(Color.black);
		//boton4.setBackground(java.awt.Color.blue);
		this.getContentPane().add(regresar1); //agrega el boton a la ventana
		regresar1.addActionListener(this);
	}

  void eliminacion ()
	{
		insertw = new JLabel ("Seleccione Donde Desea eliminar");
		insertw.setBounds(145, 170, 600,60);
		insertw.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertw.setFont(new java.awt.Font ("Times New Roman",1 , 24)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertw);
		insertw.setVisible(true);

    tablaInser = new JComboBox(tablas);
		tablaInser.setBounds(300, 270, 150,25);
		tablaInser.setForeground(Color.red); /// CAMBIAR COLOR DE LETRA
		tablaInser.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(tablaInser);
		tablaInser.addActionListener(this);

	  regresar1 = new JButton("Regresar"); //crea el boton
		regresar1.setBounds(330,350,110,40);
	  regresar1.setForeground(Color.black);
	  //boton4.setBackground(java.awt.Color.blue);
	  this.getContentPane().add(regresar1); //agrega el boton a la ventana
	  regresar1.addActionListener(this);
	}

  void maquinaria ()
	{
		regresar1.setVisible(false);

		insertw = new JLabel ("Ingrese todos los datos");
		insertw.setBounds(200, 110, 600,60);
		insertw.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertw.setFont(new java.awt.Font ("Times New Roman",1 , 24)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertw);
		insertw.setVisible(true);

		insertx = new JLabel ("Serial");
		insertx.setBounds(50, 210, 100,25);
		insertx.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertx.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertx);
		inserta = new JTextField();
		inserta.setBounds(110, 210, 150,25);
		this.getContentPane().add(inserta);

		inserty = new JLabel ("Precio");
		inserty.setBounds(280, 210, 100,25);
		inserty.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		inserty.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(inserty);
		insertb = new JTextField();
		insertb.setBounds(340, 210, 150,25);
		this.getContentPane().add(insertb);

		insertz = new JLabel ("Pais");
		insertz.setBounds(520, 210, 100,25);
		insertz.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertz.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertz);
		insertc = new JTextField();
		insertc.setBounds(570, 210, 150,25);
		this.getContentPane().add(insertc);

		continuar = new JButton("Continuar"); //crea el boton
		continuar.setBounds(280,300,110,40);
		continuar.setForeground(Color.black);
		//boton4.setBackground(java.awt.Color.blue);
		this.getContentPane().add(continuar); //agrega el boton a la ventana
		continuar.addActionListener(this);

		regresar = new JButton("Regresar"); //crea el boton
		regresar.setBounds(430,300,110,40);
		regresar.setForeground(Color.black);
		//boton4.setBackground(java.awt.Color.blue);
		this.getContentPane().add(regresar); //agrega el boton a la ventana
		regresar.addActionListener(this);
	}

  void producto ()
	{
		regresar1.setVisible(false);

		insertw = new JLabel ("Ingrese todos los datos");
		insertw.setBounds(220, 110, 600,60);
		insertw.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertw.setFont(new java.awt.Font ("Times New Roman",1 , 24)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertw);
		insertw.setVisible(true);

		insertx = new JLabel ("Codigo");
		insertx.setBounds(45, 200, 100,25);
		insertx.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertx.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertx);
		inserta = new JTextField();
		inserta.setBounds(110, 200, 150,25);
		this.getContentPane().add(inserta);

		inserty = new JLabel ("Nombre");
		inserty.setBounds(275, 200, 100,25);
		inserty.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		inserty.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(inserty);
		insertb = new JTextField();
		insertb.setBounds(350, 200, 150,25);
		this.getContentPane().add(insertb);

		insertz = new JLabel ("Peso");
		insertz.setBounds(520, 200, 100,25);
		insertz.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertz.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertz);
		insertc = new JTextField();
		insertc.setBounds(570, 200, 150,25);
		this.getContentPane().add(insertc);

		insertu = new JLabel ("Precio");
		insertu.setBounds(185, 250, 100,25);
		insertu.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertu.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertu);
		insertd = new JTextField();
		insertd.setBounds(260, 250, 150,25);
		this.getContentPane().add(insertd);

		insertv = new JLabel ("Tipo");
		insertv.setBounds(435, 250, 100,25);
		insertv.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertv.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertv);
		inserte = new JTextField();
		inserte.setBounds(480, 250, 150,25);
		this.getContentPane().add(inserte);

		continuar = new JButton("Continuar"); //crea el boton
		continuar.setBounds(280,300,110,40);
		continuar.setForeground(Color.black);
		//boton4.setBackground(java.awt.Color.blue);
		this.getContentPane().add(continuar); //agrega el boton a la ventana
		continuar.addActionListener(this);

		regresar = new JButton("Regresar"); //crea el boton
		regresar.setBounds(430,300,110,40);
		regresar.setForeground(Color.black);
		//boton4.setBackground(java.awt.Color.blue);
		this.getContentPane().add(regresar); //agrega el boton a la ventana
		regresar.addActionListener(this);
	}

  void empresa()
	{
		regresar1.setVisible(false);

		insertw = new JLabel ("Ingrese todos los datos");
		insertw.setBounds(250, 110, 600,60);
		insertw.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertw.setFont(new java.awt.Font ("Times New Roman",1 , 24)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertw);
		insertw.setVisible(true);

		insertx = new JLabel ("RIF");
		insertx.setBounds(60, 200, 100,25);
		insertx.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertx.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertx);
		inserta = new JTextField();
		inserta.setBounds(110, 200, 150,25);
		this.getContentPane().add(inserta);

		inserty = new JLabel ("Tipo");
		inserty.setBounds(280, 200, 100,25);
		inserty.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		inserty.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(inserty);
		insertTipo = new JComboBox(tipos);
		insertTipo.setBounds(325, 200, 170,25);
		insertTipo.setForeground(Color.red); /// CAMBIAR COLOR DE LETRA
		insertTipo.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertTipo);
		insertTipo.addActionListener(this);

		insertz = new JLabel ("Nombre");
		insertz.setBounds(510, 200, 100,25);
		insertz.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertz.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertz);
		insertc = new JTextField();
		insertc.setBounds(590, 200, 150,25);
		this.getContentPane().add(insertc);

		insertu = new JLabel ("Direccion");
		insertu.setBounds(50, 250, 100,25);
		insertu.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertu.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertu);
		insertd = new JTextField();
		insertd.setBounds(140, 250, 150,25);
		this.getContentPane().add(insertd);

		insertv = new JLabel ("Ciudad");
		insertv.setBounds(300, 250, 100,25);
		insertv.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertv.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertv);
		inserte = new JTextField();
		inserte.setBounds(370, 250, 150,25);
		this.getContentPane().add(inserte);

		insertt = new JLabel ("Estado");
		insertt.setBounds(540, 250, 100,25);
		insertt.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertt.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertt);
		insertf = new JTextField();
		insertf.setBounds(605, 250, 150,25);
		this.getContentPane().add(insertf);

		continuar = new JButton("Continuar"); //crea el boton
		continuar.setBounds(280,300,110,40);
		continuar.setForeground(Color.black);
		//boton4.setBackground(java.awt.Color.blue);
		this.getContentPane().add(continuar); //agrega el boton a la ventana
		continuar.addActionListener(this);

		regresar = new JButton("Regresar"); //crea el boton
		regresar.setBounds(430,300,110,40);
		regresar.setForeground(Color.black);
		//boton4.setBackground(java.awt.Color.blue);
		this.getContentPane().add(regresar); //agrega el boton a la ventana
		regresar.addActionListener(this);
	}

  void cliente ()
	{
		regresar1.setVisible(false);

		insertw = new JLabel ("Ingrese todos los datos");
		insertw.setBounds(250, 110, 600,60);
		insertw.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertw.setFont(new java.awt.Font ("Times New Roman",1 , 24)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertw);
		insertw.setVisible(true);

		insertx = new JLabel ("Cedula");
		insertx.setBounds(30, 210, 100,25);
		insertx.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertx.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertx);
		inserta = new JTextField();
		inserta.setBounds(95, 210, 150,25);
		this.getContentPane().add(inserta);

		inserty = new JLabel ("Nombre");
		inserty.setBounds(255, 210, 100,25);
		inserty.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		inserty.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(inserty);
		insertb = new JTextField();
		insertb.setBounds(330, 210, 150,25);
		this.getContentPane().add(insertb);

		insertz = new JLabel ("Fecha Ncto");
		insertz.setBounds(495, 210, 100,25);
		insertz.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertz.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertz);
		insertc = new JTextField();
		insertc.setBounds(600, 210, 150,25);
		this.getContentPane().add(insertc);

		continuar = new JButton("Continuar"); //crea el boton
		continuar.setBounds(280,300,110,40);
		continuar.setForeground(Color.black);
		//boton4.setBackground(java.awt.Color.blue);
		this.getContentPane().add(continuar); //agrega el boton a la ventana
		continuar.addActionListener(this);

		regresar = new JButton("Regresar"); //crea el boton
		regresar.setBounds(430,300,110,40);
		regresar.setForeground(Color.black);
		//boton4.setBackground(java.awt.Color.blue);
		this.getContentPane().add(regresar); //agrega el boton a la ventana
		regresar.addActionListener(this);
	}

  void curso ()
	{
		regresar1.setVisible(false);

		insertw = new JLabel ("Ingrese todos los datos");
		insertw.setBounds(250, 110, 600,60);
		insertw.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertw.setFont(new java.awt.Font ("Times New Roman",1 , 24)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertw);
		insertw.setVisible(true);

		insertx = new JLabel ("Codigo");
		insertx.setBounds(50, 200, 100,25);
		insertx.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertx.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertx);
		inserta = new JTextField();
		inserta.setBounds(115, 200, 150,25);
		this.getContentPane().add(inserta);

		inserty = new JLabel ("Nombre");
		inserty.setBounds(280, 200, 100,25);
		inserty.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		inserty.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(inserty);
		insertb = new JTextField();
		insertb.setBounds(355, 200, 150,25);
		this.getContentPane().add(insertb);

		insertz = new JLabel ("Horario");
		insertz.setBounds(510, 200, 100,25);
		insertz.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertz.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertz);
		insertc = new JTextField();
		insertc.setBounds(585, 200, 150,25);
		this.getContentPane().add(insertc);

		insertv = new JLabel ("Ci Maestro");
		insertv.setBounds(270, 250, 120,25);
		insertv.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertv.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertv);
		insertd = new JTextField();
		insertd.setBounds(370, 250, 150,25);
		this.getContentPane().add(insertd);

		continuar = new JButton("Continuar"); //crea el boton
		continuar.setBounds(280,320,110,40);
		continuar.setForeground(Color.black);
		//boton4.setBackground(java.awt.Color.blue);
		this.getContentPane().add(continuar); //agrega el boton a la ventana
		continuar.addActionListener(this);

		regresar = new JButton("Regresar"); //crea el boton
		regresar.setBounds(430,320,110,40);
		regresar.setForeground(Color.black);
		//boton4.setBackground(java.awt.Color.blue);
		this.getContentPane().add(regresar); //agrega el boton a la ventana
		regresar.addActionListener(this);
	}

  void factura()
	{
		regresar1.setVisible(false);

		insertw = new JLabel ("Ingrese todos los datos");
		insertw.setBounds(250, 110, 600,60);
		insertw.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertw.setFont(new java.awt.Font ("Times New Roman",1 , 24)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertw);
		insertw.setVisible(true);

		insertx = new JLabel ("RIF");
		insertx.setBounds(65, 200, 100,25);
		insertx.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertx.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertx);

		insertRif = new JTextField();
		insertRif.setBounds(110, 200, 150,25);
		this.getContentPane().add(insertRif);

		inserty = new JLabel ("Codigo");
		inserty.setBounds(280, 200, 100,25);
		inserty.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		inserty.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(inserty);
		insertb = new JTextField();
		insertb.setBounds(350, 200, 150,25);
		this.getContentPane().add(insertb);

		insertz = new JLabel ("Cedula");
		insertz.setBounds(510, 200, 100,25);
		insertz.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertz.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertz);
		insertc = new JTextField();
		insertc.setBounds(575, 200, 150,25);
		this.getContentPane().add(insertc);

		insertu = new JLabel ("Cantidad");
		insertu.setBounds(45, 250, 100,25);
		insertu.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertu.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertu);
		insertd = new JTextField();
		insertd.setBounds(130, 250, 150,25);
		this.getContentPane().add(insertd);

		insertv = new JLabel ("Fecha");
		insertv.setBounds(290, 250, 100,25);
		insertv.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertv.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertv);
		inserte = new JTextField();
		inserte.setBounds(350, 250, 150,25);
		this.getContentPane().add(inserte);

		insertt = new JLabel ("Costo");
		insertt.setBounds(520, 250, 100,25);
		insertt.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertt.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertt);
		insertf = new JTextField();
		insertf.setBounds(580, 250, 150,25);
		this.getContentPane().add(insertf);

		continuar = new JButton("Continuar"); //crea el boton
		continuar.setBounds(280,330,110,40);
		continuar.setForeground(Color.black);
		//boton4.setBackground(java.awt.Color.blue);
		this.getContentPane().add(continuar); //agrega el boton a la ventana
		continuar.addActionListener(this);

		regresar = new JButton("Regresar"); //crea el boton
		regresar.setBounds(430,330,110,40);
		regresar.setForeground(Color.black);
		//boton4.setBackground(java.awt.Color.blue);
		this.getContentPane().add(regresar); //agrega el boton a la ventana
		regresar.addActionListener(this);
	}

  void trabajador()
	{
		regresar1.setVisible(false);

		insertw = new JLabel ("Ingrese todos los datos");
		insertw.setBounds(250, 100, 600,60);
		insertw.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertw.setFont(new java.awt.Font ("Times New Roman",1 , 24)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertw);
		insertw.setVisible(true);

		insertx = new JLabel ("Cedula");
		insertx.setBounds(40, 190, 100,25);
		insertx.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertx.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertx);
		inserta = new JTextField();
		inserta.setBounds(105, 190, 150,25);
		this.getContentPane().add(inserta);

		inserty = new JLabel ("Nombre");
		inserty.setBounds(270, 190, 100,25);
		inserty.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		inserty.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(inserty);
		insertb = new JTextField();
		insertb.setBounds(345, 190, 150,25);
		this.getContentPane().add(insertb);

		insertz = new JLabel ("Fecha Nac.");
		insertz.setBounds(500, 190, 100,25);
		insertz.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertz.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertz);
		insertc = new JTextField();
		insertc.setBounds(600, 190, 150,25);
		this.getContentPane().add(insertc);

		insertu = new JLabel ("Cargo");
		insertu.setBounds(50, 240, 100,25);
		insertu.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertu.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertu);
		insertd = new JTextField();
		insertd.setBounds(110, 240, 150,25);
		this.getContentPane().add(insertd);

		insertv = new JLabel ("Sueldo");
		insertv.setBounds(280, 240, 100,25);
		insertv.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertv.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertv);
		inserte = new JTextField();
		inserte.setBounds(345, 240, 150,25);
		this.getContentPane().add(inserte);

		insertt = new JLabel ("Fecha In.");
		insertt.setBounds(510, 240, 100,25);
		insertt.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertt.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertt);
		insertf = new JTextField();
		insertf.setBounds(590, 240, 150,25);
		this.getContentPane().add(insertf);

		insertr = new JLabel ("Fecha Fin");
		insertr.setBounds(150, 290, 100,25);
		insertr.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		insertr.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(insertr);
		insertg = new JTextField();
		insertg.setBounds(250, 290, 150,25);
		this.getContentPane().add(insertg);

		inserts = new JLabel ("RIF");
		inserts.setBounds(430, 290, 100,25);
		inserts.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
		inserts.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
		this.getContentPane().add(inserts);

		insertRif = new JTextField();
		insertRif.setBounds(470, 290, 150,25);
		this.getContentPane().add(insertRif);

		continuar = new JButton("Continuar"); //crea el boton
		continuar.setBounds(280,350,110,40);
		continuar.setForeground(Color.black);
		//boton4.setBackground(java.awt.Color.blue);
		this.getContentPane().add(continuar); //agrega el boton a la ventana
		continuar.addActionListener(this);

		regresar = new JButton("Regresar"); //crea el boton
		regresar.setBounds(430,350,110,40);
		regresar.setForeground(Color.black);
		//boton4.setBackground(java.awt.Color.blue);
		this.getContentPane().add(regresar); //agrega el boton a la ventana
		regresar.addActionListener(this);
	}

	void consultaEliminar(String sql)
	{
		JTable tabla = new JTable();
		DefaultTableModel modelo = new DefaultTableModel();
		//para mquinaria
		if (prueba == "MAQUINARIA")
		{
			String[] columnas = {"Serial", "Precio", "Pais"};

			modelo.setColumnIdentifiers(columnas);
			tabla.setModel(modelo);

			ResultSet st = Conexion(sql);
			String serial, precio, pais;
	    try
			{
			//	if (st.getRow() != 0)
				//{
					while(st.next())
					{
							serial = st.getString("serial");
							precio = st.getString("precio");
							pais = st.getString("pais");
							modelo.addRow(new Object[]{serial, precio, pais});
					}
					/*ventana = new JFrame(prueba);
					ventana.setLayout(new FlowLayout());
					ventana.setSize(1200,600);
					ventana.setVisible(true);
					JScrollPane scroll = new JScrollPane(tabla);
					scroll.setPreferredSize(new Dimension(1000,1000));
					ventana.add(scroll);*/
				//}
				//else
				//{
					//JOptionPane.showMessageDialog(null, "No se encuentra");
				//}

			}
			catch(Exception ex)
			{
				int ERROR_MESSAGE = 0;
				JOptionPane.showMessageDialog(null, ex, "ERROR", ERROR_MESSAGE);
			}
		}
		//para PRODUCTO
		if (prueba == "PRODUCTO")
		{
			String[] columnas = {"Codigo", "Nombre", "Peso", "Precio", "Tipo"};
			modelo.setColumnIdentifiers(columnas);
			tabla.setModel(modelo);
			ResultSet st = Conexion(sql);
			String codigo, nombre, peso, precio, tipo;
	    try
			{
				/*if (st.getRow() != 0)
				{*/
					while(st.next())
					{
						codigo = st.getString("codigo");
						nombre = st.getString("nombre");
						peso = st.getString("peso");
						precio = st.getString("precio");
						tipo = st.getString("tipo");
						modelo.addRow(new Object[]{codigo, nombre, peso, precio, tipo});
					}
				/*	ventana = new JFrame(prueba);
					ventana.setLayout(new FlowLayout());
					ventana.setSize(1200,600);
					ventana.setVisible(true);
					JScrollPane scroll = new JScrollPane(tabla);
					scroll.setPreferredSize(new Dimension(1000,1000));
					ventana.add(scroll);*/
			/*	}
				else
				{
					JOptionPane.showMessageDialog(null, "No se encuentra");
				}*/
			}
			catch(Exception ex)
			{
				int ERROR_MESSAGE = 0;
				JOptionPane.showMessageDialog(null, ex, "ERROR", ERROR_MESSAGE);
			}
		}
		//para EMPRESA
		if (prueba == "EMPRESA")
		{
				String[] columnas = {"RIF", "Tipo", "Nombre", "Direccion", "Ciudad", "Estado"};
				modelo.setColumnIdentifiers(columnas);
				tabla.setModel(modelo);
				ResultSet st = Conexion(sql);
				String rif, tipo, nombre, direccion, ciudad, estado;
		    try
				{
				/*	if (st.getRow() != 0)
					{*/
						while(st.next())
						{
							rif = st.getString("rif");
							tipo = st.getString("tipo");
							nombre = st.getString("nombre");
							direccion = st.getString("direccion");
							ciudad = st.getString("ciudad");
							estado = st.getString("estado");
							modelo.addRow(new Object[]{rif, tipo, nombre, direccion, ciudad, estado});
						}
					/*	ventana = new JFrame(prueba);
						ventana.setLayout(new FlowLayout());
						ventana.setSize(1200,600);
						ventana.setVisible(true);
						JScrollPane scroll = new JScrollPane(tabla);
						scroll.setPreferredSize(new Dimension(1000,1000));
						ventana.add(scroll);*/
				/*  }
					else
					{
						JOptionPane.showMessageDialog(null, "No se encuentra");
					}*/
				}
				catch(Exception ex)
				{
					int ERROR_MESSAGE = 0;
					JOptionPane.showMessageDialog(null, ex, "ERROR", ERROR_MESSAGE);
				}
		}

		if (prueba == "CURSO")
		{
			String[] columnas = {"Codigo", "Nombre", "Horario", "Cedula Maestro"};
			modelo.setColumnIdentifiers(columnas);
			tabla.setModel(modelo);
			ResultSet st = Conexion(sql);
			String codigo, nombre, horario, ci_maestro;
			try
			{
				/*if (st.getRow() != 0)
				{*/
					while(st.next())
					{
						codigo = st.getString("codigo");
						nombre = st.getString("nombre");
						horario = st.getString("horario");
						ci_maestro = st.getString("ci_maestro");
						modelo.addRow(new Object[]{codigo, nombre, horario, ci_maestro});
					}
					/*ventana = new JFrame(prueba);
					ventana.setLayout(new FlowLayout());
					ventana.setSize(1200,600);
					ventana.setVisible(true);
					JScrollPane scroll = new JScrollPane(tabla);
					scroll.setPreferredSize(new Dimension(1000,1000));
					ventana.add(scroll);*/
				/*}
				else
				{
					JOptionPane.showMessageDialog(null, "No se encuentra");
				}*/
			}
			catch(Exception ex)
			{
				int ERROR_MESSAGE = 0;
				JOptionPane.showMessageDialog(null, ex, "ERROR", ERROR_MESSAGE);
			}
		}

		//para CLIENTE
		if (prueba == "CLIENTE")
		{
			String[] columnas = {"Cedula", "Nombre", "Fecha de Nacimiento"};
			modelo.setColumnIdentifiers(columnas);
			tabla.setModel(modelo);
			ResultSet st = Conexion(sql);
			String ci, nombre, fecha_nacimiento;
			try
			{
				/*if (st.getRow() != 0)
				{*/
					while(st.next())
					{
						ci = st.getString("ci");
						nombre = st.getString("nombre");
						fecha_nacimiento = st.getString("fecha_nacimiento");
						modelo.addRow(new Object[]{ci, nombre,fecha_nacimiento});
					}
					/*ventana = new JFrame(prueba);
					ventana.setLayout(new FlowLayout());
					ventana.setSize(1200,600);
					ventana.setVisible(true);
					JScrollPane scroll = new JScrollPane(tabla);
					scroll.setPreferredSize(new Dimension(1000,1000));
					ventana.add(scroll);*/
				/*}
			/*	else
				{
					JOptionPane.showMessageDialog(null, "No se encuentra");
				}*/
			}
			catch(Exception ex)
			{
				int ERROR_MESSAGE = 0;
				JOptionPane.showMessageDialog(null, ex, "ERROR", ERROR_MESSAGE);
			}
		}

		if (prueba == "MAESTRO")
		{
			String[] columnas = {"Cedula", "Nombre", "Fecha de Nacimiento", "Cargo", "Sueldo", "Fecha de Inicio", "Fecha Fin", "RIF de Empresa"};
			modelo.setColumnIdentifiers(columnas);
			tabla.setModel(modelo);
			ResultSet st = Conexion(sql);
			String ci, nombre, fecha_nacimiento,cargo,sueldo,fecha_inicio,fecha_fin,rif;
			try
			{
				/*if (st.getRow() != 0)
				{*/
					while(st.next())
					{
						ci = st.getString("ci");
						nombre = st.getString("nombre");
						fecha_nacimiento = st.getString("fecha_nacimiento");
						cargo = st.getString("cargo");
						sueldo = st.getString("sueldo");
						fecha_inicio = st.getString("fecha_inicio");
						fecha_fin = st.getString("fecha_fin");
						rif = st.getString("rif");
						modelo.addRow(new Object[]{ci, nombre,fecha_nacimiento,cargo,sueldo,fecha_inicio,fecha_fin,rif});
					}
					/*ventana = new JFrame(prueba);
					ventana.setLayout(new FlowLayout());
					ventana.setSize(1200,600);
					ventana.setVisible(true);
					JScrollPane scroll = new JScrollPane(tabla);
					scroll.setPreferredSize(new Dimension(1000,1000));
					ventana.add(scroll);*/
			/*	}
			/*	else
				{
					JOptionPane.showMessageDialog(null, "No se encuentra");
				}*/
			}
			catch(Exception ex)
			{
				int ERROR_MESSAGE = 0;
				JOptionPane.showMessageDialog(null, ex, "ERROR", ERROR_MESSAGE);
			}
		}

		if (prueba == "FACTURA")
		{
			String[] columnas = {"RIF", "Codigo", "Cedula", "Cantidad", "Fecha", "Costo"};
			modelo.setColumnIdentifiers(columnas);
			tabla.setModel(modelo);
			ResultSet st = Conexion(sql);
			String rif, codigo, ci, cantidad, fecha, costo;
			try
			{
			/*	if (st.getRow() != 0)
				{*/
					while(st.next())
					{
						rif = st.getString("rif");
						codigo = st.getString("codigo");
						ci = st.getString("ci");
						cantidad = st.getString("cantidad");
					 	fecha = st.getString("fecha");
						costo = st.getString("costo");
						modelo.addRow(new Object[]{rif,codigo,ci,cantidad,fecha,costo});
					}
					/*ventana = new JFrame(prueba);
					ventana.setLayout(new FlowLayout());
					ventana.setSize(1200,600);
					ventana.setVisible(true);
					JScrollPane scroll = new JScrollPane(tabla);
					scroll.setPreferredSize(new Dimension(1000,1000));
					ventana.add(scroll);*/
			/*	}
			/*	else
				{
					JOptionPane.showMessageDialog(null, "No se encuentra");
				}*/
			}
			catch(Exception ex)
			{
				int ERROR_MESSAGE = 0;
				JOptionPane.showMessageDialog(null, ex, "ERROR", ERROR_MESSAGE);
			}
		}

		ventana = new JFrame(prueba);
		ventana.setLayout(new FlowLayout());
		ventana.setSize(1200,600);
		ventana.setVisible(true);
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setPreferredSize(new Dimension(1000,1000));
		ventana.add(scroll);


		//botn para elminar en la ventana de las tablas
	 	eliminar= new JButton("Eliminar"); //crea el boton
		eliminar.setBounds(0,0,110,40);
		eliminar.setForeground(Color.black);
		ventana.getContentPane().add(eliminar); //agrega el boton a la ventana
		eliminar.addActionListener(this);
	}

	/***********************************************/
	public void actionPerformed(ActionEvent e)
  {
		String u = user.getText();
		String p = pas.getText();
		String ut;

		// elimina de una tabla
		if(e.getSource() == eliminar)
		{
			borrar(sql2);
			ventana.setVisible(false);
		}

		/// salida del programa
	  if(e.getSource() == boton)
			System.exit(0);

	 	//validacion de campos de inicio de sesion
  	if(e.getSource() == boton3)
  	{
			if (u.equals("") && p.equals(""))
				JOptionPane.showMessageDialog(rootPane, "Ingrese el nombre de usuario y Contase\u00f1a");
			else
			{
				if(u.equals("")||u == null)
					JOptionPane.showMessageDialog(rootPane, "Ingrese el nombre del usuario");
				else
				{
					if(p.equals("")||p == null)
						JOptionPane.showMessageDialog(rootPane, "Ingrese la contrase\u00f1a");
				}
			}
  	}

  	/**************************************/
  	/***** Inicio como administrador ******/
    if (e.getSource() == boton3)
		{
	  	this.dbc = new ConexionBD(BD_NAME, u, p);
	  	if(dbc.connect())
			{
	  		ut = dbc.getUserType();
	  		System.out.println(ut);
	  		if(ut.equals("administrador"))
				{
					homeAdministrador();
		  		repaint();
		  	}
				if(ut.equals("trabajador"))
				{
					homeTrabajador();
					repaint();
				}
		  	/*else
				{
					//dbc.disconnect();
				}*/
	  	}
	  	else
			{
  				JOptionPane.showMessageDialog(null, "Datos Incorrectos, Verifique");
	  	}
	  }

	  /********** Cierre de sesion **********/
	  if(e.getSource() == boton2)
		{
			j.setVisible(false);
			boton2.setVisible(false);
			ut = dbc.getUserType();
			System.out.println(ut);
			if(ut.equals("administrador"))
			{
				r1.setVisible(false);
				r2.setVisible(false);
				boton4.setVisible(false);
				repaint();
			}
		  if(ut.equals("trabajador"))
			{
				consulta1.setVisible(false);
				consulta2.setVisible(false);
				consulta3.setVisible(false);
				t1.setVisible(false);
				t2.setVisible(false);
				t3.setVisible(false);
				t4.setVisible(false);
				t5.setVisible(false);
				t6.setVisible(false);
				t7.setVisible(false);
				repaint();
			}

			setTitle("Inicio"); //coloca el nombre a la ventana
			add(a);
			a.setVisible(true);
			add(x);
			x.setVisible(true);
			add(user);
			user.setText(null);
			user.setVisible(true);
			add(y);
			y.setVisible(true);
			add(pas);
			pas.setText(null);
			pas.setVisible(true);
			add(boton);
			boton.setVisible(true);
			add(boton3);
			boton3.setVisible(true);
			repaint();
			/* Se desconecta de la BD */
			//dbc.disconnect();
		}

    /// continuar en la insercion o eliminacion
    if (e.getSource()== boton4)
		{
      if(!r1.isSelected() && !r2.isSelected())
        JOptionPane.showMessageDialog(rootPane, "Debe seleccionar una opcion");
      else
      {
        if(r1.isSelected())
        {
          j.setVisible(false);
    			boton2.setVisible(false);
    			boton4.setVisible(false);
    			r1.setVisible(false);
    			r2.setVisible(false);
    			insercion();
    			repaint();
        }
        if(r2.isSelected())
        {
          j.setVisible(false);
    			boton2.setVisible(false);
    			boton4.setVisible(false);
    			r1.setVisible(false);
    			r2.setVisible(false);
    			eliminacion();
    			repaint();
        }
      }
		}

    /// regresar en insercion o eliminacion
    if (e.getSource() == regresar1)
    {
      tablaInser.setVisible(false);
      regresar1.setVisible(false);
      r1.setVisible(true);
      r2.setVisible(true);
      z.setVisible(true);
      z.setVisible(true);
      boton4.setVisible(true);
      boton2.setVisible(true);
      homeAdministrador();
      repaint();
    }

		/// regresar a seleccion de tabla a eliminar
		if (e.getSource() == regresar2)
    {
			String prueba = (String)tablaInser.getSelectedItem();
			if (prueba == "MAQUINARIA" || prueba == "PRODUCTO" || prueba == "EMPRESA" || prueba == "CURSO" || prueba == "CLIENTE" || prueba == "MAESTRO")
			{
				insertww.setVisible(false);
				insertx.setVisible(false);
				inserta.setVisible(false);
				bm.setVisible(false);
				regresar2.setVisible(false);
				eliminacion();
				repaint();
			}
			if (prueba == "FACTURA")
			{
				insertww.setVisible(false);
				bm.setVisible(false);
				regresar2.setVisible(false);
				insertx.setVisible(false);
				insertRif.setVisible(false);
				inserty.setVisible(false);
				insertb.setVisible(false);
				insertz.setVisible(false);
				insertc.setVisible(false);
				eliminacion();
				repaint();
			}
    }

    /// Seleccion de tabla a Insertar
    if (e.getSource()== tablaInser && r1.isSelected())
		{
			String prueba = (String)tablaInser.getSelectedItem();
			if (prueba == "MAQUINARIA")
			{
				insertw.setVisible(false);
				tablaInser.setVisible(false);
				maquinaria();
				repaint();
			}
			if (prueba == "PRODUCTO")
			{
				insertw.setVisible(false);
				tablaInser.setVisible(false);
				producto();
				repaint();
			}
			if (prueba == "EMPRESA")
			{
				insertw.setVisible(false);
				tablaInser.setVisible(false);
				empresa();
				repaint();
			}
			if (prueba == "CURSO")
			{
				insertw.setVisible(false);
				tablaInser.setVisible(false);
				curso();
				repaint();
			}
			if (prueba == "CLIENTE")
			{
				insertw.setVisible(false);
				insertw.setVisible(false);
				tablaInser.setVisible(false);
				cliente();
				repaint();
			}
			if (prueba == "FACTURA")
			{
				insertw.setVisible(false);
				tablaInser.setVisible(false);
				factura();
				repaint();
			}
			if (prueba == "MAESTRO")
			{
				//regresar1.setVisible(false);
				insertw.setVisible(false);
				tablaInser.setVisible(false);
				trabajador();
				repaint();
			}
		}

    /// seleccion de tabla a Eliminar
    if (e.getSource()== tablaInser && r2.isSelected())
		{
			prueba = (String)tablaInser.getSelectedItem();
			if (prueba == "MAQUINARIA")
			{
				regresar1.setVisible(false);
				insertw.setVisible(false);
				tablaInser.setVisible(false);
				insertww = new JLabel ("Ingrese el Serial de La Maquinaria");
				insertww.setBounds(180, 110, 600,60);
				insertww.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
				insertww.setFont(new java.awt.Font ("Times New Roman",1 , 24)); /// CAMBIAR TIPO LETRA
				this.getContentPane().add(insertww);
				insertww.setVisible(true);

				insertx = new JLabel ("Serial");
				insertx.setBounds(290, 210, 100,25);
				insertx.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
				insertx.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
				this.getContentPane().add(insertx);
				inserta = new JTextField();
				inserta.setBounds(356, 210, 150,25);
				this.getContentPane().add(inserta);

				bm = new JButton("continuar"); //crea el boton
				bm.setBounds(300,300,110,40);
				bm.setForeground(Color.black);
				//boton4.setBackground(java.awt.Color.blue);
				this.getContentPane().add(bm); //agrega el boton a la ventana
				bm.addActionListener(this);

				regresar2 = new JButton("Regresar"); //crea el boton
				regresar2.setBounds(450,300,110,40);
				regresar2.setForeground(Color.black);
				//boton4.setBackground(java.awt.Color.blue);
				this.getContentPane().add(regresar2); //agrega el boton a la ventana
				regresar2.addActionListener(this);
				repaint();
			}
			if (prueba == "PRODUCTO")
			{
				regresar1.setVisible(false);
				insertw.setVisible(false);
				tablaInser.setVisible(false);
				insertww = new JLabel ("Ingrese el Codigo del Producto");
				insertww.setBounds(200, 110, 600,60);
				insertww.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
				insertww.setFont(new java.awt.Font ("Times New Roman",1 , 24)); /// CAMBIAR TIPO LETRA
				this.getContentPane().add(insertww);
				insertww.setVisible(true);

				insertx = new JLabel ("Codigo");
				insertx.setBounds(286, 210, 100,25);
				insertx.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
				insertx.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
				this.getContentPane().add(insertx);
				inserta = new JTextField();
				inserta.setBounds(356, 210, 150,25);
				this.getContentPane().add(inserta);

				bm = new JButton("continuar"); //crea el boton
				bm.setBounds(300,300,110,40);
				bm.setForeground(Color.black);
				//boton4.setBackground(java.awt.Color.blue);
				this.getContentPane().add(bm); //agrega el boton a la ventana
				bm.addActionListener(this);

				regresar2 = new JButton("Regresar"); //crea el boton
				regresar2.setBounds(450,300,110,40);
				regresar2.setForeground(Color.black);
				//boton4.setBackground(java.awt.Color.blue);
				this.getContentPane().add(regresar2); //agrega el boton a la ventana
				regresar2.addActionListener(this);
				repaint();
			}
			if (prueba == "EMPRESA")
			{
				regresar1.setVisible(false);
				insertw.setVisible(false);
				tablaInser.setVisible(false);
				insertww = new JLabel ("Ingrese el RIF de la Empresa");
				insertww.setBounds(210, 110, 600,60);
				insertww.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
				insertww.setFont(new java.awt.Font ("Times New Roman",1 , 24)); /// CAMBIAR TIPO LETRA
				this.getContentPane().add(insertww);
				insertww.setVisible(true);

				insertx = new JLabel ("RIF");
				insertx.setBounds(290, 210, 100,25);
				insertx.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
				insertx.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
				this.getContentPane().add(insertx);
				inserta = new JTextField();
				inserta.setBounds(356, 210, 150,25);
				this.getContentPane().add(inserta);

				bm = new JButton("continuar"); //crea el boton
				bm.setBounds(300,300,110,40);
				bm.setForeground(Color.black);
				//boton4.setBackground(java.awt.Color.blue);
				this.getContentPane().add(bm); //agrega el boton a la ventana
				bm.addActionListener(this);

				regresar2 = new JButton("Regresar"); //crea el boton
				regresar2.setBounds(450,300,110,40);
				regresar2.setForeground(Color.black);
				//boton4.setBackground(java.awt.Color.blue);
				this.getContentPane().add(regresar2); //agrega el boton a la ventana
				regresar2.addActionListener(this);
				repaint();
			}
			if (prueba == "CURSO")
			{
				regresar1.setVisible(false);
				insertw.setVisible(false);
				tablaInser.setVisible(false);
				insertww = new JLabel ("Ingrese Codigo del Curso");
				insertww.setBounds(200, 110, 600,60);
				insertww.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
				insertww.setFont(new java.awt.Font ("Times New Roman",1 , 24)); /// CAMBIAR TIPO LETRA
				this.getContentPane().add(insertww);
				insertww.setVisible(true);

				insertx = new JLabel ("Codigo");
				insertx.setBounds(286, 210, 100,25);
				insertx.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
				insertx.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
				this.getContentPane().add(insertx);
				inserta = new JTextField();
				inserta.setBounds(362, 210, 150,25);
				this.getContentPane().add(inserta);

				bm = new JButton("continuar"); //crea el boton
				bm.setBounds(300,300,110,40);
				bm.setForeground(Color.black);
				//boton4.setBackground(java.awt.Color.blue);
				this.getContentPane().add(bm); //agrega el boton a la ventana
				bm.addActionListener(this);

				regresar2 = new JButton("Regresar"); //crea el boton
				regresar2.setBounds(450,300,110,40);
				regresar2.setForeground(Color.black);
				//boton4.setBackground(java.awt.Color.blue);
				this.getContentPane().add(regresar2); //agrega el boton a la ventana
				regresar2.addActionListener(this);
				repaint();
			}
			if (prueba == "CLIENTE")
			{
				regresar1.setVisible(false);
				insertw.setVisible(false);
				tablaInser.setVisible(false);
				insertww = new JLabel ("Ingrese la Cedula del Cliente");
				insertww.setBounds(200, 110, 600,60);
				insertww.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
				insertww.setFont(new java.awt.Font ("Times New Roman",1 , 24)); /// CAMBIAR TIPO LETRA
				this.getContentPane().add(insertww);
				insertww.setVisible(true);

				insertx = new JLabel ("Cedula");
				insertx.setBounds(286, 210, 100,25);
				insertx.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
				insertx.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
				this.getContentPane().add(insertx);
				inserta = new JTextField();
				inserta.setBounds(366, 210, 150,25);
				this.getContentPane().add(inserta);

				bm = new JButton("continuar"); //crea el boton
				bm.setBounds(300,300,110,40);
				bm.setForeground(Color.black);
				//boton4.setBackground(java.awt.Color.blue);
				this.getContentPane().add(bm); //agrega el boton a la ventana
				bm.addActionListener(this);

				regresar2 = new JButton("Regresar"); //crea el boton
				regresar2.setBounds(450,300,110,40);
				regresar2.setForeground(Color.black);
				//boton4.setBackground(java.awt.Color.blue);
				this.getContentPane().add(regresar2); //agrega el boton a la ventana
				regresar2.addActionListener(this);
				repaint();
			}
			if (prueba == "FACTURA")
			{
				regresar1.setVisible(false);
				insertw.setVisible(false);
				tablaInser.setVisible(false);

				insertww = new JLabel ("Ingrese los datos de la Factura");
				insertww.setBounds(220, 110, 600,60);
				insertww.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
				insertww.setFont(new java.awt.Font ("Times New Roman",1 , 24)); /// CAMBIAR TIPO LETRA
				this.getContentPane().add(insertww);
				insertww.setVisible(true);

				insertx = new JLabel ("RIF");
				insertx.setBounds(65, 240, 100,25);
				insertx.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
				insertx.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
				this.getContentPane().add(insertx);

				insertRif = new JTextField();
				insertRif.setBounds(110, 240, 150,25);
				this.getContentPane().add(insertRif);

				inserty = new JLabel ("Codigo");
				inserty.setBounds(280, 240, 100,25);
				inserty.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
				inserty.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
				this.getContentPane().add(inserty);
				insertb = new JTextField();
				insertb.setBounds(350, 240, 150,25);
				this.getContentPane().add(insertb);

				insertz = new JLabel ("Cedula");
				insertz.setBounds(510, 240, 100,25);
				insertz.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
				insertz.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
				this.getContentPane().add(insertz);
				insertc = new JTextField();
				insertc.setBounds(575, 240, 150,25);
				this.getContentPane().add(insertc);

				bm = new JButton("continuar"); //crea el boton
				bm.setBounds(300,300,110,40);
				bm.setForeground(Color.black);
				//boton4.setBackground(java.awt.Color.blue);
				this.getContentPane().add(bm); //agrega el boton a la ventana
				bm.addActionListener(this);

				regresar2 = new JButton("Regresar"); //crea el boton
				regresar2.setBounds(450,300,110,40);
				regresar2.setForeground(Color.black);
				//boton4.setBackground(java.awt.Color.blue);
				this.getContentPane().add(regresar2); //agrega el boton a la ventana
				regresar2.addActionListener(this);
				repaint();
			}
			if (prueba == "MAESTRO")
			{
				regresar1.setVisible(false);
				insertw.setVisible(false);
				tablaInser.setVisible(false);
				insertww = new JLabel ("Ingrese la Cedula del Maestro");
				insertww.setBounds(200, 110, 600,60);
				insertww.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
				insertww.setFont(new java.awt.Font ("Times New Roman",1 , 24)); /// CAMBIAR TIPO LETRA
				this.getContentPane().add(insertww);
				insertww.setVisible(true);

				insertx = new JLabel ("Cedula");
				insertx.setBounds(286, 210, 100,25);
				insertx.setForeground(Color.white); /// CAMBIAR COLOR DE LETRA
				insertx.setFont(new java.awt.Font ("Times New Roman",1 , 16)); /// CAMBIAR TIPO LETRA
				this.getContentPane().add(insertx);
				inserta = new JTextField();
				inserta.setBounds(366, 210, 150,25);
				this.getContentPane().add(inserta);

				bm = new JButton("continuar"); //crea el boton
				bm.setBounds(300,300,110,40);
				bm.setForeground(Color.black);
				//boton4.setBackground(java.awt.Color.blue);
				this.getContentPane().add(bm); //agrega el boton a la ventana
				bm.addActionListener(this);

				regresar2 = new JButton("Regresar"); //crea el boton
				regresar2.setBounds(450,300,110,40);
				regresar2.setForeground(Color.black);
				//boton4.setBackground(java.awt.Color.blue);
				this.getContentPane().add(regresar2); //agrega el boton a la ventana
				regresar2.addActionListener(this);
				repaint();
			}
		}

		/// muestra de insercion de valores para eliminar tablas
		if (e.getSource() == bm)
		{
			if (prueba == "MAQUINARIA")
			{
				String a = inserta.getText();
				sql="SELECT * FROM p."+prueba+" WHERE serial ='"+a+"'";
				sql2 = "DELETE FROM p."+prueba+" WHERE serial ='"+a+"'";
				System.out.println(sql);
				consultaEliminar(sql);
			}
			if (prueba == "PRODUCTO")
			{
				String b = inserta.getText();
				System.out.println(b);
				sql ="SELECT * FROM p."+prueba+" WHERE codigo = '"+b+"'";
				sql2 = "DELETE FROM p."+prueba+" WHERE codigo = '"+b+"'";
				consultaEliminar(sql);
			}
			if (prueba == "EMPRESA")
			{
				String a = inserta.getText();
				sql="SELECT * FROM p."+prueba+" WHERE rif = '"+a+"'";
				sql2 = "DELETE FROM p."+prueba+" WHERE rif = '"+a+"'";
				System.out.println(sql);
				consultaEliminar(sql);
			}
			if (prueba == "CURSO")
			{
				String a = inserta.getText();
				sql="SELECT * FROM p."+prueba+" WHERE codigo = '"+a+"'";
				sql2 = "DELETE FROM p."+prueba+" WHERE codigo = '"+a+"'";
				System.out.println(sql);
				consultaEliminar(sql);
			}
			if (prueba == "CLIENTE")
			{
				String a = inserta.getText();
				sql="SELECT * FROM p.persona WHERE ci = '"+a+"'";
				sql2 = "DELETE FROM p.persona WHERE ci = '"+a+"'";
				System.out.println(sql);
				consultaEliminar(sql);
			}
			if (prueba == "MAESTRO")
			{
				String a = inserta.getText();
				sql="SELECT * FROM p.trabajador WHERE ci = '"+a+"'";
				sql2 = "DELETE FROM p.trabajador WHERE ci = '"+a+"'";
				System.out.println(sql);
				consultaEliminar(sql);
			}
			if (prueba == "FACTURA")
			{
				String a = insertRif.getText();
				String b = insertb.getText();
				String c = insertc.getText();

				sql="SELECT * FROM p.vende WHERE rif = '"+a+"'" + "and codigo = '"+b+"'"+ "and ci = '"+c+"'";
				sql2 = "DELETE FROM p.vende WHERE rif = '"+a+"'" + "and codigo = '"+b+"'"+ "and ci = '"+c+"'";
				consultaEliminar(sql);
			}
		}

	  /// regresar a seleccion de tabla
    if (e.getSource() == regresar)
		{
			String prueba = (String)tablaInser.getSelectedItem();
			if (prueba == "MAQUINARIA")
			{
				insertw.setVisible(false);
				regresar.setVisible(false);
				continuar.setVisible(false);
				insertx.setVisible(false);
				inserta.setVisible(false);
				inserty.setVisible(false);
				insertb.setVisible(false);
				insertc.setVisible(false);
				insertz.setVisible(false);
				insercion();
				repaint();
			}
			if (prueba == "PRODUCTO")
			{
				insertw.setVisible(false);
				regresar.setVisible(false);
				continuar.setVisible(false);
				insertx.setVisible(false);
				inserta.setVisible(false);
				insertb.setVisible(false);
				inserty.setVisible(false);
				insertc.setVisible(false);
				insertz.setVisible(false);
				insertu.setVisible(false);
				insertd.setVisible(false);
				insertv.setVisible(false);
				inserte.setVisible(false);
				insercion();
				repaint();
			}
			if (prueba == "EMPRESA")
			{
				insertw.setVisible(false);
				regresar.setVisible(false);
				continuar.setVisible(false);
				insertx.setVisible(false);
				inserta.setVisible(false);
				insertTipo.setVisible(false);
				inserty.setVisible(false);
				insertc.setVisible(false);
				insertz.setVisible(false);
				insertu.setVisible(false);
				insertd.setVisible(false);
				insertv.setVisible(false);
				inserte.setVisible(false);
				insertt.setVisible(false);
				insertf.setVisible(false);
				insercion();
				repaint();
			}
			if (prueba == "CURSO")
			{
				insertw.setVisible(false);
				regresar.setVisible(false);
				continuar.setVisible(false);
				insertx.setVisible(false);
				inserta.setVisible(false);
				insertb.setVisible(false);
				inserty.setVisible(false);
				insertc.setVisible(false);
				insertz.setVisible(false);
				insertd.setVisible(false);
				insertv.setVisible(false);
				insercion();
				repaint();
			}
			if (prueba == "CLIENTE")
			{
				insertw.setVisible(false);
				regresar.setVisible(false);
				continuar.setVisible(false);
				insertx.setVisible(false);
				inserta.setVisible(false);
				inserty.setVisible(false);
				insertb.setVisible(false);
				insertc.setVisible(false);
				insertz.setVisible(false);
				insercion();
				repaint();
			}
			if (prueba == "FACTURA")
			{
				insertw.setVisible(false);
				regresar.setVisible(false);
				continuar.setVisible(false);
				insertx.setVisible(false);
				insertRif.setVisible(false);
				inserty.setVisible(false);
				insertb.setVisible(false);
				insertc.setVisible(false);
				insertz.setVisible(false);
				insertu.setVisible(false);
				insertd.setVisible(false);
				insertv.setVisible(false);
				inserte.setVisible(false);
				insertt.setVisible(false);
				insertf.setVisible(false);
				insercion();
				repaint();
			}
			if (prueba == "MAESTRO")
			{
				insertw.setVisible(false);
				regresar.setVisible(false);
				continuar.setVisible(false);
				insertx.setVisible(false);
				inserta.setVisible(false);
				inserty.setVisible(false);
				insertb.setVisible(false);
				insertc.setVisible(false);
				insertz.setVisible(false);
				insertu.setVisible(false);
				insertd.setVisible(false);
				insertv.setVisible(false);
				inserte.setVisible(false);
				insertt.setVisible(false);
				insertf.setVisible(false);
				insertr.setVisible(false);
				inserts.setVisible(false);
				insertg.setVisible(false);
				insertRif.setVisible(false);
				insercion();
				repaint();
			}
		}

    /// continuar con el llenado de tablas
    if (e.getSource() == continuar)
		{
			String prueba = (String)tablaInser.getSelectedItem();

			if (prueba == "MAQUINARIA")
			{
				if ((inserta.getText().length() > 0) && (insertb.getText().length() > 0) && (insertc.getText().length() > 0))
				{
					String b = insertb.getText();
					float pp = Float.parseFloat(b);

					if(pp > 0)
					{
						String a = inserta.getText();
						//String b = insertb.getText();
						String c = insertc.getText();
						sql = "INSERT INTO p."+prueba+" VALUES ('"+a+"',"+b+",'"+c+"')";
						System.out.println("sql a insertar: "+sql);
						guardar(sql);
					}
					else
					{
						JOptionPane.showMessageDialog(rootPane, "Precio debe ser mayor a cero");
					}
				}
				else
				{
					if(inserta.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Serial");

					if (insertb.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "PorFavor, llenar el campo Precio");

					if (insertc.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Pais");
				}
			}
			if (prueba == "PRODUCTO")
			{
				if ((inserta.getText().length() > 0) && (insertb.getText().length() > 0) && (insertc.getText().length() > 0) && (insertd.getText().length() > 0) && (inserte.getText().length() > 0))
				{
					String a = inserta.getText();
					String b = insertb.getText();
					String c = insertc.getText();
					String d = insertd.getText();
					String f = inserte.getText();

					sql = "INSERT INTO p."+prueba+" VALUES ('"+a+"','"+b+"',"+c+","+d+",'"+f+"')";
					System.out.println("sql a insertar: "+sql);
					guardar(sql);
				}
				else
				{
					if(inserta.getText().length() == 0)
						 JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Codigo");

					if (insertb.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Nombre");

					if (insertc.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Peso");

					if (insertd.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Precio");

					if (inserte.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Tipo");
				}
			}

			if (prueba == "EMPRESA")
			{
				if ((inserta.getText().length() > 0) && (insertc.getText().length() > 0) && (insertd.getText().length() > 0) && (inserte.getText().length() > 0) && (insertf.getText().length() > 0))
				{
					String a = inserta.getText();
					String t = (String)insertTipo.getSelectedItem();
					String c = insertc.getText();
					String d = insertd.getText();
					String g = inserte.getText();
					String f = insertf.getText();

					sql = "INSERT INTO p."+prueba+" VALUES ('"+a+"','"+t+"','"+c+"','"+d+"','"+g+"','"+f+"')";
					System.out.println("sql a insertar: "+sql);
					guardar(sql);
				}
				else
				{
					if(inserta.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo RIF");

					if (insertc.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Nombre");

					if (insertd.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Direccion");

					if (inserte.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Ciudad");

					if (insertf.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Estado");
				}
			}

			if (prueba == "CURSO")
			{
				if ((inserta.getText().length() > 0) && (insertb.getText().length() > 0) && (insertc.getText().length() > 0) && (insertd.getText().length() > 0) )
				{
					String a = inserta.getText();
					String b = insertb.getText();
					String c = insertc.getText();
					String d = insertd.getText();

					sql = "INSERT INTO p."+prueba+" VALUES ('"+a+"','"+b+"','"+c+"','"+d+"')";
					System.out.println("sql a insertar: "+sql);
					guardar(sql);
				}
				else
				{
					if(inserta.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Codigo");

					if (insertb.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Nombre");

					if (insertc.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Horario");

					if (insertd.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo ci Maestro");
				}
			}

			if (prueba == "CLIENTE")
			{
				if ((inserta.getText().length() > 0) && (insertb.getText().length() > 0) && (insertc.getText().length() > 0))
				{
					String a = inserta.getText();
					String b = insertb.getText();
					String c = insertc.getText();
					sql = "INSERT INTO p.persona VALUES ('"+a+"','"+b+"','"+c+"')";
					System.out.println("sql a insertar: "+sql);
					guardar(sql);
				}
				else
				{
					if(inserta.getText().length() == 0)
					 JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Cedula");

					if (insertb.getText().length() == 0)
					 JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Nombre");

					if (insertc.getText().length() == 0)
					 JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Fecha Nac.");
				}

			}

			if (prueba == "FACTURA")
			{
				if ((insertb.getText().length() > 0) && (insertc.getText().length() > 0) && (insertd.getText().length() > 0) && (inserte.getText().length() > 0) && (insertf.getText().length() > 0))
				{
					String a = insertRif.getText();
					String b = insertb.getText();
					String c = insertc.getText();
					String d = insertd.getText();
					String f = inserte.getText();
					String g = insertf.getText();

					sql = "INSERT INTO p.vende VALUES ('"+a+"','"+b+"','"+c+"',"+d+",'"+f+"',"+g+")";
					System.out.println("sql a insertar: "+sql);
					guardar(sql);
				}
				else
				{
					if(insertb.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Codigo");

					if (insertc.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Cedula");

					if (insertd.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Cantidad");

					if (inserte.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Fecha");

					if (insertf.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Costo");
				}
			}

			if (prueba == "MAESTRO")
			{
				if ((inserta.getText().length() > 0) && (insertb.getText().length() > 0) && (insertc.getText().length() > 0) && (insertd.getText().length() > 0) && (inserte.getText().length() > 0) && (insertf.getText().length() > 0) && (insertg.getText().length() > 0))
				{
					String a = inserta.getText();
					String b = insertb.getText();
					String c = insertc.getText();
					String d = insertd.getText();
					String f = inserte.getText();
					String g = insertf.getText();
					String h = insertg.getText();
					String i = insertRif.getText();

					sql = "INSERT INTO p.trabajador VALUES ('"+a+"','"+b+"','"+c+"','"+d+"',"+f+",'"+g+"','"+h+"','"+i+"')";
					System.out.println("sql a insertar: "+sql);
					guardar(sql);
				}
				else
				{
					if(inserta.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Cedula");

					if(insertb.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Nombre");

					if (insertc.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Fecha Nac.");

					if (insertd.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Cargo");

					if (inserte.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Sueldo");

					if (insertf.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Fecha In.");

					if(insertg.getText().length() == 0)
						JOptionPane.showMessageDialog(rootPane, "Por Favor, llenar el campo Fecha Fin");
				}
			}
		}

    /// consulta 1
		if (e.getSource() == consulta1)
		{
			JTable tablaConsulta1 = new JTable();
			DefaultTableModel modelo = new DefaultTableModel();
			String[] columnas = {"Codigo", "Nombre", "Peso", "Precio", "Tipo", "Etapa", "Trabajador de la Etapa"};
			modelo.setColumnIdentifiers(columnas);
			tablaConsulta1.setModel(modelo);
			String sql = "select pmax.codigo, pmax.nombre, pmax.peso, pmax.precio, pmax.tipo, et.nombre as etapa, t.nombre as trabajador from p.etapa et, p.trabajador t,p.procesa pcs,p.labora l,(select pr.codigo,pr.nombre, pr.peso, pr.precio, pr.tipo,e.rif from p.producto pr, p.procesa pc, p.empresa e where pr.codigo = pc.codigo and pc.rif = e.rif and e.tipo = 'Chocolatera' and e.nombre = 'El Cimarron' and pr.precio = (select max(precio) from p.producto pr, p.procesa pc, p.empresa e where pr.codigo = pc.codigo and pc.rif = e.rif and e.tipo = 'Chocolatera' and e.nombre = 'El Cimarron')) pmax where pcs.codigo = pmax.codigo and pcs.id = et.id and pcs.rif = pmax.rif and et.id = l.id and l.ci = t.ci";
			ResultSet st = Conexion(sql);
			String codigo, nombre, peso, precio, tipo, nombre_e, nombre_t;
			try
			{
				while(st.next())
				{
					codigo = st.getString("codigo");
					nombre = st.getString("nombre");
					peso = st.getString("peso");
					precio = st.getString("precio");
					tipo = st.getString("tipo");
					nombre_e = st.getString("etapa");
					nombre_t = st.getString("trabajador");
					modelo.addRow(new Object[]{codigo, nombre, peso, precio, tipo, nombre_e, nombre_t});
				}
			}
			catch(Exception ex)
			{
				int ERROR_MESSAGE = 0;
				JOptionPane.showMessageDialog(null, ex, "ERROR", ERROR_MESSAGE);
			}
			ventana = new JFrame("Consulta 1º");
			ventana.setLayout(new FlowLayout());
			ventana.setSize(1400,500);
			ventana.setVisible(true);
			JScrollPane scroll = new JScrollPane(tablaConsulta1);
			scroll.setPreferredSize(new Dimension(1300,70));
			ventana.add(scroll);
			salir = new JButton("Salir");
			salir.setBounds(400,400,130,40);
			salir.setForeground(Color.black);
			ventana.getContentPane().add(salir); //agrega el boton a la ventana
			salir.addActionListener(this);
		}

    //consulta 2
		if (e.getSource() == consulta2)
		{
			JTable tablaConsulta2 = new JTable();
			DefaultTableModel modelo = new DefaultTableModel();
			String[] columnas = {"Codigo", "Ganancia", "Estado"};
			modelo.setColumnIdentifiers(columnas);
			tablaConsulta2.setModel(modelo);
			String sql = "select v.codigo, sum(v.costo - pro.precio * v.cantidad ) over(partition by e.estado) as ganancia, e.estado from p.vende v, p.empresa e, p.producto pro where e.tipo = 'Tienda' and  e.nombre = 'Cacao Venezolano' and v.rif = e.rif and v.fecha >= '2016-05-01' and v.fecha <= '2016-05-31' and pro.codigo = v.codigo order by v.codigo asc, ganancia desc";
			ResultSet st = Conexion(sql);
			String codigo, ganancia, estado;
			try
			{
				while(st.next())
				{
					codigo = st.getString("codigo");
					ganancia = st.getString("ganancia");
					estado = st.getString("estado");
					modelo.addRow(new Object[]{codigo,ganancia,estado});
				}
			}
			catch(Exception ex)
			{
				int ERROR_MESSAGE = 0;
				JOptionPane.showMessageDialog(null, ex, "ERROR", ERROR_MESSAGE);
			}
			ventana = new JFrame("Consulta 2º");
			ventana.setLayout(new FlowLayout());
			ventana.setSize(1000,500);
			ventana.setVisible(true);
			JScrollPane scroll = new JScrollPane(tablaConsulta2);
			scroll.setPreferredSize(new Dimension(800,70));
			ventana.add(scroll);
			salir = new JButton("Salir");
			salir.setBounds(400,400,130,40);
			salir.setForeground(Color.black);
			ventana.getContentPane().add(salir); //agrega el boton a la ventana
			salir.addActionListener(this);
		}

    //consulta 3
    if (e.getSource() == consulta3)
    {
    	JTable tablaConsulta3 = new JTable();
			DefaultTableModel modelo = new DefaultTableModel();
			String[] columnas = {"Codigo", "Organizador", "Nombre", "Año", "Premio", "Participante", "Delegado"};
			modelo.setColumnIdentifiers(columnas);
			tablaConsulta3.setModel(modelo);
			String sql = "SELECT x.codigo, x.organizador, x.nombre, x.anho, x.premio, pr.participante, pr.delegado FROM (SELECT codigo FROM p.premio_conc GROUP BY codigo HAVING COUNT(*)=(SELECT MAX(COUNT(codigo)) OVER() FROM p.premio_conc GROUP BY codigo LIMIT 1)) pre, p.premio_conc x, p.concursa c, p.evento e, p.participante_ev pr WHERE x.codigo = pre.codigo and x.codigo = c.codigo and x.nombre = c.nombre and x.organizador = c.organizador and x.anho = c.anho and e.nombre = c.nombre and e.organizador = c.organizador and e.anho = c.anho and pr.participante = 'Venezuela' and pr.nombre = e.nombre and pr.organizador = e.organizador and pr.anho = e.anho";
			ResultSet st = Conexion(sql);
			String codigo, organizador, nombre, anho, premio, participante, delegado;
			try
			{
				while(st.next())
				{
					codigo = st.getString("codigo");
					organizador = st.getString("organizador");
					nombre = st.getString("nombre");
					anho = st.getString("anho");
					premio = st.getString("premio");
					participante = st.getString("participante");
					delegado = st.getString("delegado");
					modelo.addRow(new Object[]{codigo,organizador,nombre,anho,premio,participante,delegado});
				}
			}
			catch(Exception ex)
			{
				int ERROR_MESSAGE = 0;
				JOptionPane.showMessageDialog(null, ex, "ERROR", ERROR_MESSAGE);
			}
			ventana = new JFrame("Consulta 3º");
			ventana.setLayout(new FlowLayout());
			ventana.setSize(1300,500);
			ventana.setVisible(true);
			JScrollPane scroll = new JScrollPane(tablaConsulta3);
			scroll.setPreferredSize(new Dimension(1200,100));
			ventana.add(scroll);
			salir = new JButton("Salir");
			salir.setBounds(400,400,130,40);
			salir.setForeground(Color.black);
			ventana.getContentPane().add(salir); //agrega el boton a la ventana
			salir.addActionListener(this);
    }
		if(e.getSource() == salir)
		{
			salir.setVisible(false);
			ventana.setVisible(false);
		}
  }
}
