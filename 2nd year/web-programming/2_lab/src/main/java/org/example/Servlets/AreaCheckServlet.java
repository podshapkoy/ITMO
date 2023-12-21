package org.example.Servlets;

import org.example.ResultData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/areaCheck")
public class AreaCheckServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String xParameter = request.getParameter("X");
        String yParameter = request.getParameter("Y");
        String rParameter = request.getParameter("R");
        yParameter = yParameter.replace(",", ".");

        double x = Double.parseDouble(xParameter);
        BigDecimal y = new BigDecimal(yParameter);
        double r = Double.parseDouble(rParameter);

        boolean insideRectangle = isInsideRectangle(x, y, r);
        boolean insideCircleQuarter = isInsideCircleQuarter(x, y, r);
        boolean insideTriangle = isInsideTriangle(x, y, r);

        boolean isInside = insideRectangle || insideCircleQuarter || insideTriangle;

        String scriptExecutionTimeParam = request.getParameter("scriptExecutionTime");
        double scriptExecutionTime = (scriptExecutionTimeParam != null) ? Double.parseDouble(scriptExecutionTimeParam) : 0.0;

        storeResultInContext(request, xParameter, yParameter, rParameter, isInside, scriptExecutionTime);
        request.getRequestDispatcher("/results_lab_2.jsp").forward(request, response);
    }

    private void storeResultInContext(HttpServletRequest request, String x, String y, String r, boolean isInside, double scriptExecutionTime) {
        List<ResultData> resultTable = (List<ResultData>) request.getServletContext().getAttribute("resultTable");

        if (resultTable == null) {
            resultTable = new ArrayList<>();
            request.getServletContext().setAttribute("resultTable", resultTable);
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        ResultData resultData = new ResultData(
                Double.parseDouble(x),
                new BigDecimal(y),
                Double.parseDouble(r),
                isInside,
                scriptExecutionTime,
                localDateTime
        );
        resultData.setFormattedScriptExecutionTime(String.format("%.3f ", scriptExecutionTime));

        resultTable.add(0, resultData);
    }

    private boolean isInsideRectangle(double x, BigDecimal y, double r) {
        return (x >= -r && x <= 0 && y.compareTo(new BigDecimal("-" + r / 2)) >= 0 && y.compareTo(BigDecimal.ZERO) <= 0);
    }

    private boolean isInsideCircleQuarter(double x, BigDecimal y, double r) {
        return (x >= 0) && (y.compareTo(BigDecimal.ZERO) <= 0) && (x * x + y.pow(2).doubleValue() <= r * r);
    }

    private boolean isInsideTriangle(double x, BigDecimal y, double r) {
        BigDecimal x1 = BigDecimal.ZERO;
        BigDecimal y1 = BigDecimal.ZERO;
        BigDecimal x2 = BigDecimal.ZERO;
        BigDecimal y2 = new BigDecimal(r);
        BigDecimal x3 = new BigDecimal(r);
        BigDecimal y3 = BigDecimal.ZERO;

        BigDecimal alpha = y2.subtract(y3).multiply(BigDecimal.valueOf(x)).add(x3.subtract(x2).multiply(y.subtract(y3))).divide(y2.subtract(y3).multiply(x1.subtract(x3)).add(x3.subtract(x2).multiply(y1.subtract(y3))), 15, BigDecimal.ROUND_HALF_UP);

        BigDecimal beta = y3.subtract(y1).multiply(BigDecimal.valueOf(x)).add(x1.subtract(x3).multiply(y.subtract(y3))).divide(y2.subtract(y3).multiply(x1.subtract(x3)).add(x3.subtract(x2).multiply(y1.subtract(y3))), 15, BigDecimal.ROUND_HALF_UP);

        BigDecimal gamma = BigDecimal.ONE.subtract(alpha).subtract(beta);

        return alpha.compareTo(BigDecimal.ZERO) >= 0 && beta.compareTo(BigDecimal.ZERO) >= 0 && gamma.compareTo(BigDecimal.ZERO) >= 0;
    }
}