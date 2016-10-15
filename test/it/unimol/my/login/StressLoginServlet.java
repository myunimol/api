package it.unimol.my.login;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mashape.unirest.http.exceptions.UnirestException;


@WebServlet(name = "StressTest", urlPatterns = { "/stresstest" })
public class StressLoginServlet extends HttpServlet {
	private static final String[] CREDENTIAL1 = new String[] {"username", "password"};
	private static final String[] CREDENTIAL2 = new String[] {"username", "password"};
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		stressLogin();
	}
	
	@Test
	public void stressLogin() {
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Thread t1 = new MyStresser(CREDENTIAL1[0], CREDENTIAL1[1], new Random().nextInt(10));
			Thread t2 = new MyStresser(CREDENTIAL2[0], CREDENTIAL2[1], new Random().nextInt(10));
			
			threads.add(t1);
			threads.add(t2);
		}
		

		for (Thread thread : threads)
			thread.start();

		boolean oneAlive = true;
		while (oneAlive) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				assertFalse(true);
			}
			
			oneAlive = false;
			for (Thread thread : threads)
				if (thread.isAlive())
					oneAlive = true;
		}
	}
}

class MyStresser extends Thread {

	private String username;
	private String password;
	private int times;

	public MyStresser(String username, String password, int times) {
		this.username = username;
		this.password = password;
		this.times = times;
	}

	@Override
	public void run() {
		for (int i = 0; i < times; i++) {
			try {
				System.out.println("Go for " + username + "@" + i);
				LoginParser parser = LoginParserManager.getLoginParser();
				UserInformation logInfo = parser.getLoginInformation(username, password, null);
				if (logInfo == null) {
					System.out.println("Login null for " + username + i); 
				}

			} catch (UnirestException e) {
				System.out.println("Exception for " + username + i); 
			}
		}
	}
}