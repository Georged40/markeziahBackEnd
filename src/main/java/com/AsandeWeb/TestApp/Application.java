package com.AsandeWeb.TestApp;

import com.AsandeWeb.TestApp.model.Invoice;
import com.AsandeWeb.TestApp.model.Stock_Item;;
import com.AsandeWeb.TestApp.repository.InvoiceRepo;
import com.AsandeWeb.TestApp.repository.stockItemRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootApplication
public class Application {

	static Connection connection = null;
	static String url = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7717834";
	static String username = "sql7717834";
	static String password = "MSFgDEpNyV";

	public Application() throws SQLException {
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		System.setProperty("server.servlet.context-path", "/api/storeroom");
		System.setProperty("server.port", "8088");

		SpringApplication.run(Application.class, args);
	}

}





