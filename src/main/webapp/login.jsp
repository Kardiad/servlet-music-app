<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.HashMap" %>
<% 
	Map<String, Object> usuario = new HashMap<String, Object>();
	if(request.getAttribute("usuario")!=null){
		usuario = (Map<String, Object>) request.getAttribute("usuario");
	}else{
		usuario = null;		
	}
	List<Map<String, Object>> generos = new ArrayList<Map<String, Object>>();
	if(request.getAttribute("categorias")!=null){
		generos = (List<Map<String, Object>>) request.getAttribute("categorias");
	}
	List<Map<String, Object>> canciones = new ArrayList<Map<String, Object>>();
	if(request.getAttribute("canciones")!=null){
		canciones = (List<Map<String, Object>>) request.getAttribute("canciones");
	}
	//Añadir el de favoritos
	List<Map<String, Object>> favoritos = new ArrayList<Map<String, Object>>();
	if(request.getAttribute("favs")!=null){
		favoritos = (List<Map<String, Object>>) request.getAttribute("favs");
	}
	boolean fav = false;
	if(request.getAttribute("fav_mode")!=null && request.getAttribute("fav_mode").toString().length()>0){
		fav = true;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SpotyClone</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	<link rel="stylesheet" href="http://localhost:8080/SpotifyClone/assets/style.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</head>
<body>
	<header>
	<nav class="navbar navbar-expand-md navbar-dark bg-dark">
	  <div class="container-fluid">
	    <div class="col-7 col-md-8 d-flex flex-column flex-md-row justify-content-around align-items-center">
				<img class="logo" alt="Icono de la plataforma" src="http://localhost:8080/SpotifyClone/assets/img/icono_musica.png">
				<h1 class="brand-logo"><span class="text-success">PLAY.MP3<span></h1>
			</div>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse col-3" id="navbarSupportedContent">
	      <ul class="mt-4 mt-4 col-12 d-flex justify-content-around align-items-center flex-wrap flex-sm-nowrap flex-grow-1">
			<!-- START LOGIN MODAL -->
			<% if(usuario==null) { %>
				<li>
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal">
					  <i class="fa-solid fa-right-to-bracket"></i>
					</button>
					
					<!-- Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h1 class="modal-title fs-5" id="exampleModalLabel">Iniciar Sesion</h1>
					      </div>
					      <div class="modal-body">
					       	<form action="./Login" class="form" method="POST">
					       		<label for="usuario" class="form-label">Nombre de usuario</label>
					       		<input type="text" id="usuario" class="form-control" name="usuario" placeholder="nombre de usuario">
					       		<label for="contrasena" class="form-label">Contraseña</label>
					       		<input type="password" id="contrasena" class="form-control" name="contrasena" placeholder="contraseña">
					       		<br>
					       		<button type="submit" class="btn btn-success">Entrar</button>
					       	</form>
					      </div>
					    </div>
					  </div>
					</div>
				</li>
				<li>
					<a href="./Admin" class="btn btn-outline-danger"><i class="fa-solid fa-lock"></i></a>
				</li>
			<% }else { %>
				<li class="col-12 col-sm-2 m-auto me-1 text-center"> Hola! <%=usuario.get("nombre") %> </li>
				<li>
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal">
					  <i class="fa-solid fa-user"></i>
					</button>
					
					<!-- Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h1 class="modal-title fs-5" id="exampleModalLabel">Modificar perfil</h1>
					      </div>
					      <div class="modal-body">
					       	<form action="./EditarUsuario" class="form" method="POST">
					       		<label for="nombre" class="form-label">Nombre de acceso</label>
					       		<input type="text" id="nombre" class="form-control" name="nombre" value="<%= usuario.get("nombre") %>" placeholder="nombre de usuario">
					       		<label for="username" class="form-label">Nombre de usuario</label>
					       		<input type="text" id="username" class="form-control" name="usenrame" value="<%= usuario.get("username") %>" placeholder="username (el que se verá)">
					       		<label for="contrasena" class="form-label">Contraseña</label>
					       		<input type="password" id="contrasena" class="form-control" name="contrasena" placeholder="escribe algo distnito para cambiarla">
					       		<label for="mail" class="form-label">Correo</label>
					       		<input type="mail" id="mail" class="form-control" name="mail" value="<%= usuario.get("mail") %>" placeholder="mail del usuario">
					       		<input type="hidden" name="id" value="<%= usuario.get("id") %>">
					       		<br>
					       		<button type="submit" class="btn btn-success">Registrarse</button>
					       	</form>
					      </div>
					    </div>
					  </div>
					</div>
				</li>
				<li>
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal1">
					  <i class="fa-solid fa-file-audio"></i>
					</button>
					
					<!-- Modal -->
					<div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h1 class="modal-title fs-5" id="exampleModalLabel">Añadir canción</h1>
					      </div>
					      <div class="modal-body">
					       	<form action="./SubirCancion" class="form" method="POST" enctype="multipart/form-data">
					       		<label for="titulo" class="form-label">Titulo Cancion</label>
					       		<input type="text" id="titulo" class="form-control" name="titulo" placeholder="Titulo cancion">
					       		<label for="grupo" class="form-label">Banda</label>
					       		<input type="text" id="grupo" class="form-control" name="grupo" placeholder="Grupo musicar">
					       		<!-- <label for="duracion" class="form-label">Duracion</label>
					       		<input type="number" id="duracion" class="form-control" name="duracion" placeholder="duracion"> -->
					       		<label for="genero">Genero</label>
					       		<select name="genero" id="genero" class="form-select">
					       			<%for(Map<String, Object> g : generos) { %>
					       				<option value="<%= g.get("nombre") %>"><%= g.get("nombre") %></option>	
					       			<% } %>
					       		</select>
					       		<label for="cancion">Cancion</label>
					       		<input type="file" class="form-control" id="cancion" name="cancion" accept=".mp3">
					       		<br>	
					       		<button type="submit" class="btn btn-success">Registrarse</button>
					       	</form>
					      </div>
					    </div>
					  </div>
					</div>
				</li>
				<li>
					<a class="btn btn-success" href="./LogOut"><i class="fa-solid fa-right-from-bracket"></i></a>
				</li>
			<% } %>
			<!-- END LOGIN MODAL -->
			</ul>
		</div>	
		</nav>
		<% if(usuario!=null) { %>
		<nav class="d-flex justify-content-center pt-3 bg-dark">
			<ul class="d-flex justify-content-around w-100">
				<li class="col-3 text-center"> 
					<a href="./VerMias" class="btn btn-outline-success">
						Mías
					</a>
				</li>
				<li class="col-3 text-center"> 
					<a href="./Login" class="btn btn-outline-success">
						<i class="fa-solid fa-earth-americas"></i>
					</a>
				</li>
				<li class="col-3">
					<form action="./Buscar" method="POST">
						<input type="text" class="form-control" name="buscar" placeholder="busca canciones (titulo, genero o grupo)">
					</form>
				</li>
				<li class="col-3 text-center"> 
					<a href="./MostrarFavoritos" class="btn btn-outline-danger">
						<i class="fa-regular fa-heart"></i>
					</a>
				</li>
			</ul>
		 </nav>
		<% } %>
<!-- COMIENZA TODO LO QUE HAY QUE ARREGLAR, POR FAVOR NO ASUSTARSE QUE HAY HTML DE MIERDA -->		
	</header>
	<main class="d-flex justify-content-around align-items-baseline flex-wrap flex-grow-1 mt-5">
		<!-- START MUSIC CARD -->
		<% for(Map<String, Object> c : canciones) { %>
			<div class="card">
			  <div class="card-body">
			    <h5 class="card-title"><%= c.get("titulo") %></h5>
			    <h6 class="card-subtitle mb-2"> <%= c.get("grupo") %></h6>
			   	<p><%= c.get("duracion") %> s</p>
			   	<p>Genero: <%= c.get("nombre") %></p>
			    <audio controls>
				    <source src="<%= c.get("url_path") %>" type="audio/mp3">
				        Tu navegador no soporta audio HTML5.
				</audio>
			    <p class="card-text"><b>Contribuyente: </b><%= c.get("username") %></p>
			  </div>
			  <% if(usuario!= null && usuario.get("username").equals(c.get("username"))) { %>
				  <div class="card-footer">
				  	<button class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#musica<%= c.get("id_musica")%>">
				  		<i class="fa-solid fa-pen-to-square"></i>
				  	</button>					
					<!-- Modal -->
					<div class="modal fade" id="musica<%= c.get("id_musica")%>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h1 class="modal-title fs-5" id="exampleModalLabel">Editar Canción</h1>
					      </div>
					      <div class="modal-body">
					       	<form action="./EditarCancion" class="form" method="POST">
					       		<label for="titulo" class="form-label">Titulo Cancion</label>
					       		<input type="text" id="titulo" class="form-control" name="titulo" placeholder="Titulo cancion" value="<%= c.get("titulo")%>">
					       		<label for="grupo" class="form-label">Banda</label>
					       		<input type="text" id="grupo" class="form-control" name="grupo" placeholder="Grupo musica" value="<%= c.get("grupo")%>">
					       		<label for="genero">Genero</label>
					       		<select name="genero" id="genero" class="form-select">
					       			<%for(Map<String, Object> g : generos) { %>
					       				<option value="<%= g.get("nombre") %>" <%= c.get("id_genero")==g.get("id")?"selected":"" %>><%= g.get("nombre") %></option>	
					       			<% } %>
					       		</select>
					       		<input type="hidden" name="id" value="<%= c.get("id_musica")%>">
					       		<br>
					       		<button type="submit" class="btn btn-success">Registrarse</button>
					       	</form>
					      </div>
					    </div>
					  </div>
					</div>
				  	<a href="./BorrarCancion?id=<%= c.get("id_musica") %>" class="btn btn-danger">
				  		<i class="fa-solid fa-trash"></i>
				  	</a>
				  </div>
			  <% } %>
			  <% if(usuario!=null && !fav) { %>
			  	  <a href="./AnadirFavoritos?id=<%= c.get("id_musica") %>" class="btn btn-outline-danger">
					<i class="fa-regular fa-heart"></i>
				</a>
			  <% } else if(usuario != null && fav) { %>
			  		<a href="./AnadirFavoritos?id=<%= c.get("id_musica") %>" class="btn btn-outline-danger">
					<i class="fa-solid fa-heart"></i>
				</a>
			  <% } %>
			</div>
		<% } %>
		<!-- END MUSIC CARD -->
	</main>
	<footer>
		
	</footer>
</body>
</html>