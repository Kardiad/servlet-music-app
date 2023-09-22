<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.HashMap" %>
<% 
	Map<String, Object> usuario = new HashMap<String, Object>();
	List<Map<String, Object>> usuarios = new ArrayList<Map<String, Object>>();
	if(request.getAttribute("usuario")!=null){
		usuario = (Map<String, Object>) request.getAttribute("usuario");
	}else{
		usuario = null;
	}
	
	if(request.getAttribute("usuarios")!=null){
		usuarios = (List<Map<String, Object>>) request.getAttribute("usuarios");
	}
	
	List<Map<String, Object>> canciones = new ArrayList<Map<String, Object>>();
	if(request.getAttribute("canciones")!=null){
		canciones = (List<Map<String, Object>>) request.getAttribute("canciones");
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
</head>
<body>
<!-- If no session pues login, if session pues panel admin -->
<header>
		<nav class="navbar navbar-expand-md navbar-dark bg-dark">
		<div class="navbar-brand d-flex flex-column flex-md-row justify-content-around align-items-center">
			<img class="logo" alt="Icono de la plataforma" src="http://localhost:8080/SpotifyClone/assets/img/icono_musica.png">
			<h1 class="brand-logo"><span class="text-success">PLAY.MP3<span><span class="text-danger fs-2">Admin</span></h1>
		</div>
		 <button class="navbar-toggler m-auto" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse d-md-flex justify-content-center aling-items-center" id="navbarSupportedContent">
	      <ul class="mt-4 col-12 d-flex justify-content-around align-items-center flex-wrap flex-sm-nowrap flex-grow-1">
			<% if(usuario!=null) { %>
			<!-- START SIGN IN -->
				<li class="col-12 col-sm-2 m-auto me-1 text-center"> ¿Ordenes? <%=usuario.get("nombre") %> </li>
				<li class="p-1">					
					<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal1">
					  <i class="fa-solid fa-user-plus"></i>
					</button>
					
					
					<div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel1" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h1 class="modal-title fs-5" id="exampleModalLabel1">Añadir Usuario</h1>
					      </div>
					      <div class="modal-body">
					       	<form action="./Sign" class="form" method="POST">
					       		<label for="nombre" class="form-label">Nombre de acceso</label>
					       		<input type="text" id="nombre" class="form-control" name="nombre" placeholder="nombre de usuario">
					       		<label for="username" class="form-label">Nombre de usuario</label>
					       		<input type="text" id="username" class="form-control" name="usenrame" placeholder="username (el que se verá)">
					       		<label for="contrasena" class="form-label">Contraseña</label>
					       		<input type="password" id="contrasena" class="form-control" name="contrasena" placeholder="contraseña">
					       		<label for="mail" class="form-label">Correo</label>
					       		<input type="mail" id="mail" class="form-control" name="mail" placeholder="mail del usuario">
					       		<br>
					       		<button type="submit" class="btn btn-success">Registrarse</button>
					       	</form>
					      </div>
					    </div>
					  </div>
					</div>
				</li>
			  <!--  END SIGN IN -->
			  <!-- START ADD USER -->
				<li class="p-1">
					
					<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal">
					  <i class="fa-solid fa-lock"></i>
					</button>
					
					
					<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h1 class="modal-title fs-5" id="exampleModalLabel1">Añadir Administrador</h1>
					      </div>
					      <div class="modal-body">
					       	<form action="./SignAdmin" class="form" method="POST">
					       		<label for="nombre" class="form-label">Nombre de acceso</label>
					       		<input type="text" id="nombre" class="form-control" name="nombre" placeholder="nombre de usuario">
					       		<label for="username" class="form-label">Nombre de usuario</label>
					       		<input type="text" id="username" class="form-control" name="usenrame" placeholder="username (el que se verá)">
					       		<label for="contrasena" class="form-label">Contraseña</label>
					       		<input type="password" id="contrasena" class="form-control" name="contrasena" placeholder="contraseña">
					       		<label for="mail" class="form-label">Correo</label>
					       		<input type="mail" id="mail" class="form-control" name="mail" placeholder="mail del usuario">
					       		<br>
					       		<button type="submit" class="btn btn-success">Registrarse</button>
					       	</form>
					      </div>
					    </div>
					  </div>
					</div>
				</li>
			  <!--  END ADD USER -->
			  	<li class="p-1">
					<a class="btn btn-success" href="./LogOut"><i class="fa-solid fa-right-from-bracket"></i></a>
				</li>
			<% } else {%>
				<li class="p-1"><a href="./Inicio" class="btn btn-outline-success"> 
					<i class="fa-solid fa-music"></i>
				</a></li>
			<% } %>
			</ul>
	    </div>
			
			
		</nav>
	</header>
	<main class="container-md d-flex flex-column justify-content-center mt-5">
		<% if(usuario == null){ %>
		<article class="col-12 col-sm-6 m-auto">
			<form action="./Login" class="form" method="POST">
	       		<label for="usuario" class="form-label">Nombre de usuario</label>
	       		<input type="text" id="usuario" class="form-control" name="usuario" placeholder="nombre de usuario">
	       		<label for="contrasena" class="form-label">Contraseña</label>
	       		<input type="password" id="contrasena" class="form-control" name="contrasena" placeholder="contraseña">
	       		<br>
	       		<button type="submit" class="btn btn-success w-100">Entrar</button>
	       	</form>
		</article>
		<% } else if(usuarios.size()>0) { %>
		<article class="table-responsive">
			<h3>Listado de usuarios</h3>
			<table class="table table-dark">
				<thead>
					<tr>
						<th>Id</th>
						<th>Nombre</th>
						<th>Username</th>
						<th>Activo</th>
						<th>Correo</th>
						<th>Rol</th>
						<th>Acciones</th>		
					</tr>
				</thead>
				<tbody>
					<%for(Map<String, Object> o : usuarios) { %>
						<tr>
							<td><%=o.get("id_usuario") %></td>
							<td><%=o.get("nombre") %></td>
							<td><%=o.get("username") %></td>
							<td><%=o.get("activo") %></td>
							<td><%=o.get("mail") %></td>
							<td><%=o.get("id_permisos") %></td>
							<td>
								<a class="btn btn-danger" href="./BorrarUsuario?id=<%= o.get("id_usuario") %>">
									<i class="fa-solid fa-trash"></i>
								</a>
								<a class="btn btn-warning" href="./BloquearUsuario?id=<%= o.get("id_usuario") %>">
									<i class="fa-solid fa-shield-halved"></i>
								</a>
								<a class="btn btn-outline-warning" href="./DescenderRol?id=<%= o.get("id_usuario")%>&rol=<%= o.get("id_permisos")%>">
									<i class="fa-solid fa-arrow-down"></i>
								</a>
								<% if((int)o.get("id_permisos")!=2) { %>
								<a class="btn btn-outline-success" href="./AscenderRol?id=<%= o.get("id_usuario")%>&rol=<%= o.get("id_permisos")%>">
									<i class="fa-solid fa-arrow-up"></i>
								</a>
								<% } %>
							</td>
						</tr>
					<% } %>
				</tbody>
			</table>
		</article>
		<article class="py-3">
			<h3>Acciones bbdd</h3>
			<div class="d-flex justify-content-around">
				<a href="./BBDDJson" class="btn btn-success" data-trigger="hover" title="Hacer copia de la base de datos"><i class="fa-solid fa-copy"></i></a>
				<a href="./DescargarJSONUsuarios" class="btn btn-success" data-trigger="hover" title="Descargar JSON usuarios"><i class="fa-solid fa-file-download"></i><i class="fa-solid fa-user"></i></a>
				<a href="./DescargarJSONCanciones" class="btn btn-success" data-trigger="hover" title="Descargar JSON música"><i class="fa-solid fa-file-download"></i><i class="fa-solid fa-music"></i></a>			
				<button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#exampleModa1Imp" data-trigger="hover" title="Importar JSON usuarios">
				  <i class="fa-solid fa-file-import"></i><i class="fa-solid fa-user"></i>
				</button>
					<div class="modal fade" id="exampleModa1Imp" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h1 class="modal-title fs-5" id="exampleModalLabel1">Importar Usuarios</h1>
					      </div>
					      <div class="modal-body">
					       	<form action="./ImportUsuarios" class="form" method="POST" enctype="multipart/form-data">
					       		<label class="form-label" for="json">Json</label>
					       		<input type="file" class="form-control" id="json" name="json">
					       		<br>
					       		<button type="submit" class="btn btn-success">Registrarse</button>
					       	</form>
					      </div>
					    </div>
					  </div>
					</div>
				<button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#exampleModa2Imp" data-trigger="hover" title="Importar JSON música">
				  <i class="fa-solid fa-file-import"></i><i class="fa-solid fa-music"></i>
				</button>
					<div class="modal fade" id="exampleModa2Imp" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h1 class="modal-title fs-5" id="exampleModalLabel1">Importar Canciones</h1>
					      </div>
					      <div class="modal-body">
					       	<form action="./ImportCanciones" class="form" method="POST" enctype="multipart/form-data">
					       		<label class="form-label" for="json">Json</label>
					       		<input type="file" class="form-control" id="json" name="json">
					       		<br>
					       		<button type="submit" class="btn btn-success">Registrarse</button>
					       	</form>
					      </div>
					    </div>
					  </div>
					</div>
				</div>
		</article>
		<% } %>
		<% if(canciones.size()>0) { %>
		<article class="table-responsive">
			<h3>Listado de Canciones</h3>
			<form action="./Buscar" class="d-flex flex-column">
			<div class="d-flex justify-content-end mb-3">
				<input type="text" name="busca" class="form-control w-50" placeholder="busca canciones"> 
			</div>
			<table class="table table-dark">
				<thead>
					<tr>
						<th>Título</th>
						<th>Grupo</th>
						<th>Duracion</th>
						<th>Género</th>
						<th>Contribuyente</th>
						<th>Acciones</th>		
					</tr>
				</thead>
				<tbody>
					<%for(Map<String, Object> o : canciones) { %>
						<tr>
							<td><%=o.get("titulo") %></td>
							<td><%=o.get("grupo") %></td>
							<td><%=o.get("duracion") %></td>
							<td><%=o.get("nombre") %></td>
							<td><%=o.get("usuario") %></td>
							<td>
								<a class="btn btn-danger" href="./BorrarCancion?id=<%= o.get("id_musica") %>">
									<i class="fa-solid fa-trash"></i>
								</a>
							</td>
						</tr>
					<% } %>
				</tbody>
			</table>
			</form>
		</article>
		<% } %>
	</main>
	<footer>
		
	</footer>
</body>
</html>
