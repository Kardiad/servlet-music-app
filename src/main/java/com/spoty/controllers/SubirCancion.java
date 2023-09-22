package com.spoty.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sound.sampled.AudioFileFormat;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import com.spoty.enviroment.Enviroment;

/**
 * Servlet implementation class SubirCancion
 */
@MultipartConfig
@WebServlet("/SubirCancion")
public class SubirCancion extends MainController implements Enviroment {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubirCancion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//añadir en el formulario una forma de obtener los siguientes campos: grupo, duracion, y genero
		this.init(request, response);
		this.parameterHandle();
		this.paramsStatus();
		this.fileHandle("cancion");
		int duracion = 0;
		if(this.file!=null && this.validFields(3)) {
			File cancion = new File(UPLOADS_PATH+"\\"+this.utf8_conversion(this.file.getSubmittedFileName()));
			try (FileOutputStream w = new FileOutputStream(cancion)) {
				w.write(this.file.getInputStream().readAllBytes());
				w.close();
			}
			try {
				AudioFile f = AudioFileIO.read(cancion);
				duracion = f.getAudioHeader().getTrackLength();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.parameters.put("4", duracion);
			this.parameters.put("5", this.getSession().get("id_usuario"));
			this.parameters.put("6", BASE_URL+UPLOADS+this.utf8_conversion(this.file.getSubmittedFileName()));
			this.paramsStatus();
			this.db.procedure(POST_CANCION, this.parameters, null, false);
			this.request.setAttribute("canciones", this.db.procedure(GET_CANCIONES, null, null, true));
			this.request.setAttribute("usuario", this.getSession());
			this.request.setAttribute("categorias", this.db.procedure(GET_GENEROS, null, null, true));
			this.fileGarbage();
			this.viewLoader(LOGIN_VIEW);
		}else if(this.sessionDone()) {
			this.redirect(this.getViewLoad(this.getSession().get("rol").toString()));			
		}else {
			this.redirect(RED_INICIO);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
