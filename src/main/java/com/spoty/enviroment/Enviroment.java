package com.spoty.enviroment;

public interface Enviroment {
	
	String CHARSET = "UTF-8";
	
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String CONEXION = "jdbc:mysql://localhost:3306/spoty?characterEncoding=UTF-8";
	String USUARIO = "root";
	String CONTRASENA = "Akiraeva_01";
	
	String GET_PERMISOS = "call lista_permisos();";
	String POST_USUARIO = "call anadir_usuario(?,?,?,?,?);";
	String GET_USUARIO = "call obtener_usuario(?);";
	String POST_CANCION = "call anadir_cancion(?,?,?,?,?,?);";
	String POST_BLOQUEAR = "call bloquear_usuario(?);";
	String DELETE_USUARIO = "call borrar_usuario(?);";
	String DELETE_CANCION = "call borrar_cancion(?);";
	String GET_CANCIONES = "call mostrar_canciones();";
	String GET_MIS_CANCIONES = "call mostrar_mis_canciones(?);";
	String GET_USUARIOS = "call lista_usuarios();";
	String GET_GENEROS = "call obtener_generos();";
	String GET_BUSQUEDA_CANCIONES = "call buscar_canciones(?)";
	String POST_EDITAR_CANCION = "call editar_cancion(?,?,?,?);";
	String POST_EDITAR_USUARIO = "call editar_usuario(?,?,?,?,?,?)";
	String GET_CAMBIAR_NIVEL = "call cambiar_nivel(?,?)";
	String POST_IMPORT_USUARIO = "call import_usuario(?,?,?,?,?,?,?,?,?,?,?)";
	String POST_IMPORT_CANCIONES = "call import_cancion(?,?,?,?,?,?,?,?,?,?)";
	String POST_MARCAR_FAV = "call marcar_fav(?,?);";
	String GET_FAVS = "call mostrar_favs(?);";
	
	String PER_BLOQUEO_USUARIO = "BLOQUEAR_USUARIO";
	String PER_BORRAR_USUARIO = "BORRAR_USUARIO";
	String PER_ANADIR_USUARIO = "ANADIR_USUARIO";
	String PER_BORRAR_MUSICA = "BORRAR_MUSICA";
	String PER_EDITAR_CANCION = "EDITAR_MUSICA";
	String PER_EDITAR_USUARIO = "EDITAR_USUARIO";
	String PER_CAMBIAR_PERMISOS = "CAMBIAR_ROL";
	String PER_ANADIR_FAVORITOS = "ANADIR_FAV";
	
	String LOGIN_VIEW = "login.jsp";
	String ADMIN_VIEW = "admin.jsp";
	
	String RED_INICIO = "./Inicio";
	String RED_INICIO_ADMIN = "./Admin";
	String RED_COMODIN = "./Login";
	
	String ADMIN = "admin";
	String USER = "usuario";
	
	String BASE_URL = "http://localhost:8080/SpotifyClone/";
	String UPLOADS = "/assets/uploads/";
	String UPLOADS_PATH = "C:\\Users\\jafet\\Desktop\\IFCT079PO - PROCESAMIENTO DE DATOS CON JAVA\\html\\java\\SpotifyClone\\src\\main\\webapp\\assets\\uploads";
	String BASE_PATH = "C:\\Users\\jafet\\Desktop\\IFCT079PO - PROCESAMIENTO DE DATOS CON JAVA\\html\\java\\SpotifyClone\\src\\main\\webapp\\";
	String ADMIN_PATH = "assets\\admin\\";
}
