import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ThreeParamsServlet")
public class ThreeParamsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ThreeParamsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		
		String firstNumber = request.getParameter("n1");
		String secondNumber = request.getParameter("n2");
		String operation = request.getParameter("operation");
		
		if(operation.equals("sum")) {
			float sum = Float.parseFloat(firstNumber) + Float.parseFloat(secondNumber);
			
			pw.println("Answer: " + sum);
		} else if(operation.equals("div")) {
			if(secondNumber.equals("0")) {
				pw.println("Cannot divide by zero!");
				return;
			}
			
			float div = Float.parseFloat(firstNumber) / Float.parseFloat(secondNumber);
			
			pw.println("Answer: " + div);
		} else if(operation.equals("sub")) {
			float sub = Float.parseFloat(firstNumber) - Float.parseFloat(secondNumber);
			
			pw.println("Answer: " + sub);
		} else if(operation.equals("avg")) {
			float sum = Float.parseFloat(firstNumber) + Float.parseFloat(secondNumber);
			float avg = sum / 2;
			
			pw.println("Answer: " + avg);
		} else {
			pw.println("Operation is not valid!");
		}
		
		pw.close();
	}
}
